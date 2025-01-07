import "./RouteCatalog.css"
import {useEffect, useState} from "react";
import {getAllRoutes} from "../api/api.js";
import Route from "./Route.jsx";

function RouteCatalog(props) {

    const [routes, setRoutes] = useState([]);

    useEffect(() => {
        getAllRoutes().then(res => {
            setRoutes(res);
        })
    }, []);

    return (
        <div>
            <h1>Маршруты</h1>
            <table>
                <thead>
                <tr>
                    <th className="table-header">Наименование</th>
                    <th className="table-header">Автор</th>
                    <th className="table-header">Количество точек</th>
                    <th className="table-header">Перепад высот</th>
                </tr>
                </thead>
                <tbody>
                {routes.map(
                    (route) => <Route key={route.id} id={route.id} name={route.name} owner={route.owner}
                                      points={route.points}
                                      elevations={route.elevations}/>
                )}
                </tbody>
            </table>
            <div className="buttonDiv">
                <button className="createButton" ><a href="/create">Создать маршрут</a></button>
            </div>
        </div>
    )
}

export default RouteCatalog
