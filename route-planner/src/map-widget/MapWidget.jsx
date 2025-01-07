import {FullscreenControl, Map, Placemark, Polyline, TypeSelector, YMaps} from "@pbe/react-yandex-maps";
import './MapWidget.css'

function MapWidget(props) {

    const {width, height, selectedPoints, addNewPoint, editable} = props;

    function getDefaultState() {

        if (editable) {
            return {center: [55.75, 37.57], zoom: 9};
        }

        if (selectedPoints.length > 0) {
            return {
                center: [selectedPoints[0].latitude, selectedPoints[0].longitude],
                zoom: 15
            }
        }

        return {center: [55.75, 37.57], zoom: 9};
    }

    if (editable || selectedPoints.length > 0) {
        return (
            <div>
                <YMaps>
                    <Map
                        defaultState={getDefaultState()}
                        width={width}
                        height={height}
                        onClick={(event) => {
                            if (editable) {
                                const coords = event.get("coords");
                                addNewPoint(coords)
                            }
                        }}
                    >
                        {selectedPoints.map(
                            (point) => <Placemark key={point.key} geometry={[point.latitude, point.longitude]}/>
                        )}

                        <Polyline
                            geometry={selectedPoints.map((point) => [point.latitude, point.longitude])}
                            options={{
                                balloonCloseButton: false,
                                strokeColor: "#ff0000",
                                strokeWidth: 4,
                                strokeOpacity: 0.5,
                            }}
                        />
                        <FullscreenControl/>
                        <TypeSelector options={{float: "left"}}/>
                    </Map>
                </YMaps>
            </div>
        )
    } else {
        return <></>
    }

}

export default MapWidget
