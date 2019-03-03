import React, {Component} from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import TextField from 'material-ui/TextField';
import RaisedButton from 'material-ui/RaisedButton';

class Authentication extends Component {

    validate = e => {
        e.preventDefault();
        //validate and authenticate
    }

    render() {

        const { values, handleChange } = this.props;

        return(
            <MuiThemeProvider>
                <React.Fragment>
                    <TextField 
                        required
                        hintText="Enter username"
                        floatingLabelText="User Name"
                        onChange={handleChange('userName')}
                        defaultValue={values.userName}
                    />
                    <br/>
                    <TextField 
                        required
                        type="password"
                        hintText="Enter password"
                        floatingLabelText="Password"
                        onChange={handleChange('password')}
                        defaultValue={values.password}
                    />
                    <br/>
                     <TextField 
                        required
                        type="email"
                        hintText="Enter Email"
                        floatingLabelText="Email"
                        onChange={handleChange('email')}
                        defaultValue={values.email}
                    />
                    <br/>
                    <RaisedButton
                    primary="true"
                    label="Login"
                    style={styles.button}
                    onclick={this.validate}
                    />
                </React.Fragment>
            </MuiThemeProvider>
        )
    }
}

const styles = {
    button : {
        margin:15
    }
}
export default Authentication;