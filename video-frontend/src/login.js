import React from "react";
import './login.css';
import {useHistory} from 'react-router-dom';

export function Login() {

    const history = useHistory();

    function handleSubmit(props) {

        history.push("/main")
        props.preventDefault();
        console.log(props)
    }

    return (
        <section className="login_form_section">
            <form onSubmit={handleSubmit}>
                <h1 className="login_logo">VideoApp</h1>
                <div className="line-break"/>
                <input className="login_form_input--text" placeholder="Podaj swój nick..." type="text"/>
                <div className="line-break"/>
                <input className="login_form_input--button" type="submit" value="Wyślij"/>
            </form>

        </section>
    )
}

