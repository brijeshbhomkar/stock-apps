import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import Typography from '@material-ui/core/Typography';

function TabContainer(props) {
  return (
    <Typography component="div" style={{ padding: 8 * 3 }}>
      {props.children}
    </Typography>
  );
}

TabContainer.propTypes = {
  children: PropTypes.node.isRequired,
};

const styles = theme => ({
  root: {
    flexGrow: 1,
    backgroundColor: theme.palette.background.paper,
  },
});

class Footer extends React.Component {
  state = {
    value: 0,
  };

  handleChange = (event, value) => {
    this.setState({ value });
  };

  render() {
    const { classes } = this.props;
    const { value } = this.state;

    return (
      <div className={classes.root}>

        {value === 0 && <TabContainer>Settings related to the user </TabContainer>}
        {value === 1 && <TabContainer>This will shows stocks in uptrend </TabContainer>}
        {value === 2 && <TabContainer>This will show stocks in downtrend </TabContainer>}

        <AppBar position="static">
          <Tabs value={value} onChange={this.handleChange}>
            <Tab label="Settings" />
            <Tab label="Uptrends" />
            <Tab label="DownTrends" />
          </Tabs>
        </AppBar>
      </div>
    );
  }
}

Footer.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Footer);