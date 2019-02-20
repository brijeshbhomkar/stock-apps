import React from "react";

import Form from "./components/Forms"
import NavBar from "./components/NavBar"


class App extends React.Component {

    render() {
        return (
            <div>
                <NavBar/>
                <Form/>   
            </div>
        );
    }
}

export default App;
