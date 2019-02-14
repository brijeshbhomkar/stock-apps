import React from "react";

import Titles from "./components/Titles"
import Form from "./components/Forms"

class App extends React.Component {


    render() {
        return (
            <div>
                <Titles/>
                <Form/>
            </div>
        );
    }
}

export default App;
