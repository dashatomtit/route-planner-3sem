const BACKEND_URL = "http://localhost:8080";

export async function getElevation(latitude, longitude) {
    const url = BACKEND_URL + "/elevation?latitude=" + latitude + "&longitude=" + longitude;
    try {
        const response = await fetch(url);
        const json = await response.json();
        return json.elevation;
    } catch (e) {
        console.log(e);
        return null;
    }
}

export async function getAllRoutes() {
    const url = BACKEND_URL + "/routes";
    try {
        const response = await fetch(url);
        const json = await response.json();
        return json.routes;
    } catch (e) {
        console.log(e);
        return null;
    }
}

export async function getRoute(id) {
    const url = BACKEND_URL + "/routes/" + id;
    try {
        const response = await fetch(url);
        const json = await response.json();
        return json;
    } catch (e) {
        console.log(e);
        return null;
    }
}

export async function saveRoute(name, selectedPoints, elevations) {
    const newRouteBody = {
        name: name,
        owner: localStorage.getItem("user"),
        points: selectedPoints,
        elevations: elevations
    }

    const url = BACKEND_URL + "/routes";
    try {
        const response = await fetch(
            url,
            {
                method: 'POST',
                headers: {'Accept': 'application/json', 'Content-Type': 'application/json'},
                body: JSON.stringify(newRouteBody)
            }
        );
        const json = await response.json();
        return json;
    } catch (e) {
        console.log(e);
        return null;
    }
}

export async function authorizeUser(username, password) {
    const loginDate = {
        username: username,
        password: password
    }

    const url = BACKEND_URL + "/login";
    try {
        const res = await fetch(
            url,
            {
                method: 'POST',
                headers: {'Accept': 'application/json', 'Content-Type': 'application/json'},
                body: JSON.stringify(loginDate)
            }
        );
        return res.status === 200;
    } catch (e) {
        console.log(e);
        return false;
    }
}