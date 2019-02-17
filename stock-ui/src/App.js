import React from "react";

import Titles from "./components/Titles"
import Form from "./components/Forms"
import NavBar from "./components/NavBar"
import Footer from "./components/Footer"

class App extends React.Component {


    render() {
        return (
            <div>
                <NavBar/>
                <Form/>
                <Footer/>
            </div>
        );
    }
}

export default App;
