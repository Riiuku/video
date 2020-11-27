import React, {useEffect, useState} from 'react'
import ReactDOM from 'react-dom'
import './index.css'
import {BrowserRouter as Router, Route, useHistory, withRouter} from "react-router-dom";
import {Login} from "./login/login";
import {Main} from "./main/main";
import {Footer} from "./footer/footer";
import {getUser} from "./api/user-api";

function Start() {

    return (
        <section className="index__section">
            <article className="index__router">
                <Router>
                    <Route exact path="/" component={Login}/>
                    <Route exact path="/main"  component={Main}/>
                </Router>
            </article>
            <article className="index_footer">
                <Footer/>
            </article>

        </section>
    );
}


ReactDOM.render(<Start/>, document.getElementById('root'));