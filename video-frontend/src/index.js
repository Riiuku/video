import React from 'react'
import ReactDOM from 'react-dom'
import Login from './login'
import './index.css'
function Start() {
    return (
        <section>
            <Login />
        </section>
    );
}

ReactDOM.render(<Start/>, document.getElementById('root'));