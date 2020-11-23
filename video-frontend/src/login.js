import React from "react";
import './login.css';

class Login extends React.Component {


    handleSubmit(event) {
        event.preventDefault();
        console.log(event)
    }

    render() {
        return (
            <section className="login_form_section">
                    <form onSubmit={this.handleSubmit}>
                        <h1 className="login_logo">VideoApp</h1>
                        <div className="line-break"/>
                        <input className="login_form_input--text" placeholder="Podaj swój nick..." type="text"/>
                        <div className="line-break"/>
                        <input className="login_form_input--button" type="submit" value="Wyślij"/>
                    </form>

            </section>
        )
    }
}

export default Login