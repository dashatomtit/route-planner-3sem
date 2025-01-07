import MapWidget from "../map-widget/MapWidget.jsx";
import {useEffect, useState} from "react";
import {getElevation, getRoute, saveRoute} from "../api/api.js";
import {LineChart} from "@mui/x-charts";
import "./MapPage.css"
import {Box, TextField} from "@mui/material";
import route from "../route-catalog/Route.jsx";

function MapPage(props) {

    const [width, setWidth] = useState(window.innerWidth);
    const [height, setHeight] = useState(window.innerHeight);

    useEffect(() => {
        const needsLoading = props.needsLoading;
        if (needsLoading) {
            const splitResult = window.location.href.split("/");
            const id = splitResult[splitResult.length - 1];
            getRoute(id).then(route => {
                console.log(route);

                setSelectedPoints(route.points);
                setPointsCounter(route.points.length);
                setElevations(route.elevations);

                setRouteName(route.name)
                setRouteOwner(route.owner)
            });
        }
    }, [props.needsLoading]);

    useEffect(() => {
        const handleResize = () => {
            setWidth(window.innerWidth);
            setHeight(window.innerHeight)
        }
        window.addEventListener("resize", handleResize);
        return () => window.removeEventListener("resize", handleResize);
    }, []);

    const [routeName, setRouteName] = useState("");
    const [routeOwner, setRouteOwner] = useState("");

    const [pointsCounter, setPointsCounter] = useState(0);
    const [selectedPoints, setSelectedPoints] = useState([]);

    const [elevations, setElevations] = useState([]);

    function countElevation(coords) {
        getElevation(coords[0], coords[1]).then(r => {
            setElevations([...elevations, r]);
        })
    }

    function addNewPoint(coords) {
        setSelectedPoints([...selectedPoints, {id: pointsCounter, latitude: coords[0], longitude: coords[1]}]);
        setPointsCounter(pointsCounter + 1)

        countElevation(coords)
    }

    function clearPoints() {
        setSelectedPoints([]);
        setPointsCounter(0);
        setElevations([]);
    }

    function removeLastPoint() {
        if (selectedPoints.length === 0) {
            return;
        }
        setSelectedPoints(selectedPoints.slice(0, -1));
        setPointsCounter(pointsCounter - 1);
        setElevations(elevations.slice(0, -1));
    }

    function handleTextInputChange(event) {
        setRouteName(event.target.value);
    }

    function createRoute() {
        if (routeName.length === 0 || selectedPoints.length === 0) {
            return;
        }
        saveRoute(routeName, selectedPoints, elevations).then(r => console.log("Создал"));
    }

    return (
        <div>
            {props.needsLoading && <h1 className="routeName">{routeName}</h1>}
            {props.needsLoading && <p className="username">Автор: {routeOwner}</p>}
            {!props.needsLoading && <h1 className="routeName">Создание нового маршрута</h1>}
            {!props.needsLoading &&
                <Box sx={{ width: width > 950 ? width / 2 : width * 0.95 }}>
                    <TextField fullWidth error={routeName.length === 0} label="Название маршрута" id="routeName" onChange={handleTextInputChange} />
                </Box>
            }
            <div className="container">
                <MapWidget
                    editable={props.editable}
                    width={width > 950 ? "50vw" : "95vw"}
                    height="75vh"
                    selectedPoints={selectedPoints}
                    clearPoints={clearPoints}
                    removeLastPoint={removeLastPoint}
                    addNewPoint={addNewPoint}
                />
                <LineChart
                    xAxis={[{data: Array.from(Array(elevations.length).keys())}]}
                    series={[
                        {
                            data: elevations,
                        },
                    ]}
                    width={width > 950 ? width / 2 : width * 0.95}
                    height={width > 950 ? 3 * height / 4 : height * 0.33}
                />
            </div>
            <div className="button-container">
                {props.editable && <button onClick={clearPoints}>Очистить маршрут</button>}
                {props.editable && <button onClick={removeLastPoint}>Удалить последнюю точку</button>}
                {props.editable && <button onClick={createRoute}>Сохранить маршрут</button>}
            </div>
        </div>
    )
}

export default MapPage
