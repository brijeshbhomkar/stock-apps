import React from "react";

import Titles from "./components/Titles"
import Form from "./components/Forms"

const PERIOD = "day";
const STOCK_ID = "1270529";
const PUBLIC_TOEKN = "YVCHBWLMD498ImdsqhO0sHd3Lw0fF5dZ";
const USER_ID= "RB1822";
const API_KEY= "kitefront";
const ACCESS_TOKEN = "X5ZQOWggyhh4qXmS3eKmdU4N9sIvEMjJ";
const FROM_DATE = "2015-05-06"
const TO_DATE = "2019-02-14";

class App extends React.Component {

	getStockInfo = async (e) => {
		e.preventDefault()
		const api_call = await fetch(`https://kitecharts-aws.zerodha.com/api/chart/${STOCK_ID}/${PERIOD}
			?public_token=${PUBLIC_TOEKN}&user_id=${USER_ID}&api_key=${API_KEY}&access_token=${ACCESS_TOKEN}
			&from=${FROM_DATE}&to=${TO_DATE}`);
		const data = await api_call.json();
		console.log(data);
	}

    render() {
        return (
            <div>
                <Titles/>
                <Form getStockInfo={this.getStockInfo}/>
            </div>
        );
    }
}

export default App;
