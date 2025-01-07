import './LoginPage.css'
import {useState} from "react";
import {authorizeUser} from "../api/api.js";
import {Link} from "react-router-dom";

function LoginPage(props) {

    const [login, setLogin] = useState("");
    const [password, setPassword] = useState("");

    const [error, setError] = useState(false);

    function changeLogin(event) {
        setLogin(event.target.value);
    }

    function changePassword(event) {
        setPassword(event.target.value);
    }

    function authorize() {
        authorizeUser(login, password).then(res => {
            if (res) {
                props.setUser(login);
            } else {
                setError(true);
            }
        });

    }

    if (props.user === "" || props.user == null) {
        return (
            <div className="login-container">
                <div className="login-form">
                    <h2>Авторизация</h2>
                    <label htmlFor="login">Логин:</label>
                    <input onChange={changeLogin} type="text" id="login" name="login" required/>

                    <label htmlFor="password">Пароль:</label>
                    <input onChange={changePassword} type="password" id="password" name="password" required/>

                    <button onClick={authorize}>Войти</button>
                    {error && <p className="erorrText">Неверный логин или пароль</p>}
                </div>
            </div>
        )
    } else {
        return (
            <Link to="/routes"/>
        )
    }
}

export default LoginPage
