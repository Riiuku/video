import React from 'react'
import ReactDOM from 'react-dom'
import './index.css'
import { BrowserRouter as Router, Route} from "react-router-dom";
import {Login} from "./login";
import {Main} from "./main";
function Start() {
    return (
        <section>
            <Router>
                <Route exact path="/" component={Login} />
                <Route exact path="/main" component={Main} />
            </Router>
        </section>
    );
}



ReactDOM.render(<Start/>, document.getElementById('root'));