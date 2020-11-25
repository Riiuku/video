import React from 'react'
import ReactDOM from 'react-dom'
import './index.css'
import { BrowserRouter as Router, Route} from "react-router-dom";
import {Login} from "./login";
import {Main} from "./main";
import {Footer} from "./footer";
function Start() {
    return (
        <section className="index__section">
            <article className="index__router">
                <Router >
                    <Route exact path="/" component={Login} />
                    <Route exact path="/main" component={Main} />
                </Router>
            </article>
            <article className="index_footer">
                <Footer />
            </article>

        </section>
    );
}



ReactDOM.render(<Start/>, document.getElementById('root'));