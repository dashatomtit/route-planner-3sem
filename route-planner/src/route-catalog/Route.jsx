import './Route.css'

function Route(props) {

    const {id, name, owner, points, elevations} = props;

    function getElevationDifference(elevations) {
        const max = Math.max.apply(Math, elevations);
        const min = Math.min.apply(Math, elevations);
        return Math.abs(max - min);
    }

    return (
        <tr className="route-div">
            <th><a href={"/routes/" + id}>{name}</a></th>
            <th>{owner}</th>
            <th>{points.length}</th>
            <th>{getElevationDifference(elevations)}</th>
        </tr>
    )
}

export default Route;