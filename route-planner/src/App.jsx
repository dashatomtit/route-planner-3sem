import './App.css'
import {BrowserRouter, Route, Routes} from "react-router-dom";
import RouteCatalog from "./route-catalog/RouteCatalog.jsx";
import MapPage from "./map-page/MapPage.jsx";
import {useState} from "react";
import LoginPage from "./login/LoginPage.jsx";

function App() {

    function useLocalStorage(key) {
        const [state, setState] = useState(localStorage.getItem(key));
        function setStorage(item) {
            localStorage.setItem(key, item);
            setState(item);
        }
        return [state, setStorage];
    }

    const [user, setUser] = useLocalStorage("user");

    if (user === "" || user == null) {
        return (
            <BrowserRouter>
                <Routes>
                    <Route path="*" element={<LoginPage user={user} setUser={setUser}/>}/>
                </Routes>
            </BrowserRouter>
        );
    } else {
        console.log(user)
        return (
            <BrowserRouter>
                <Routes>
                    <Route path="/routes" element={<RouteCatalog/>}/>
                    <Route path="/routes/:id" element={<MapPage editable={false} needsLoading={true}/>}/>
                    <Route path="/create" element={<MapPage editable={true} needsLoading={false}/>}/>
                </Routes>
            </BrowserRouter>
        );
    }
}

export default App
