import React, {Component} from "react";
import UserAuthentication from "./Authentication";
import AppBar from "material-ui/AppBar/AppBar";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import Tab from '@material-ui/core/Tab';
import Tabs from '@material-ui/core/Tabs';

class UserForm extends Component {
    state = {
        username:'',
        password:'',
        email:'',
        value:0
    }

    handleChange = input => e => {
        this.setState({[input]:e.target.value})
    }

    render() {

        const {username, password, email} = this.state;
        const values = {username, password, email};
        const { value } = this.state;

        return(
        <MuiThemeProvider>
            <AppBar position="static">
            <Tabs value={value} onChange={this.handleChange}>
            <Tab label="Login" />
            <Tab label="Registration" />
            </Tabs>
            </AppBar>
            <UserAuthentication
            handleChange={this.handleChange}
            values={values}
           />
           </MuiThemeProvider>
        )
    }
}


export default UserForm;