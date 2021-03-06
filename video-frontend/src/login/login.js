import React, {useEffect, useState} from "react";
import './login.css';
import {useHistory} from 'react-router-dom';
import {getUser, setUser} from "../api/user-api";

export function Login() {

    const history = useHistory();
    const [name, setName] = useState("")
    function handleSubmit(props) {
        setUser({userName: name})
            .then(r =>
               r.json().then(data => {
                   localStorage.setItem("user-id", data.publicId)
                   history.push("/main")
               })
            )

        props.preventDefault();
        console.log(name)
    }

    useEffect(() => {

        if (localStorage.getItem("user-id") != null) {
            getUser()
                .then(r => {
                    if (r.status < 299) {
                        history.push("/main")
                    } else {
                        localStorage.removeItem("user-id")
                    }
                })
        }
    }, [])

    return (
        <section className="login_form_section">
            <form onSubmit={handleSubmit}>
                <h1 className="login_logo">VideoApp</h1>
                <div className="line-break"/>
                <input className="login_form_input--text" value={name} onChange={(e) => setName(e.target.value)} placeholder="Podaj swój nick..." type="text"/>
                <div className="line-break"/>
                <input className="login_form_input--button" type="submit" value="Wyślij"/>
            </form>

        </section>
    )
}

