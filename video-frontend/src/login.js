import React from "react";

class Login extends React.Component {


    handleSubmit(event) {
        event.preventDefault();
        console.log(event)
    }

    render() {
        return (
            <section>
                <form onSubmit={this.handleSubmit}>
                    <label>
                        Nick:
                        <input type="text"/>
                    </label>
                    <input type="submit" value="Wyślij"/>
                </form>
            </section>
        )
    }
}

export default Login