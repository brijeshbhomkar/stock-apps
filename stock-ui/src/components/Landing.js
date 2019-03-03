import React from "react"
import StockTable from "../components/Tables"
import SearchPanel from "../components/SearchPanel"

const PERIOD = "day";
const STOCK_ID = "1270529";
const PUBLIC_TOEKN = "YVCHBWLMD498ImdsqhO0sHd3Lw0fF5dZ";
const USER_ID= "RB1822";
const API_KEY= "kitefront";
const ACCESS_TOKEN = "X5ZQOWggyhh4qXmS3eKmdU4N9sIvEMjJ";
const FROM_DATE = "2015-05-06"
const TO_DATE = "2019-02-14";

const NIFTY_50 = "https://www.nseindia.com/live_market/dynaContent/live_analysis/pre_open/nifty.json";
const NIFTY_FO = "https://www.nseindia.com/live_market/dynaContent/live_analysis/pre_open/fo.json";
const NIFTY_BANK = "https://www.nseindia.com/live_market/dynaContent/live_analysis/pre_open/niftybank.json";	
const NIFTY_ALL = "https://www.nseindia.com/live_market/dynaContent/live_analysis/pre_open/all.json";

// getStockInfo = async (e) => {
// 	e.preventDefault()
// 	const api_call = await fetch(`https://kitecharts-aws.zerodha.com/api/chart/${STOCK_ID}/${PERIOD}
// 		?public_token=${PUBLIC_TOEKN}&user_id=${USER_ID}&api_key=${API_KEY}&access_token=${ACCESS_TOKEN}
// 		&from=${FROM_DATE}&to=${TO_DATE}`);
// 	const data = await api_call.json();
// 	console.log(data);
// }

class Form extends React.Component {


	getNifty50 = async(e) => {
		e.preventDefault()
		const api_nifty_50 = await fetch(NIFTY_50)
		const data = await api_nifty_50.json();
		console.log(data);
	}

	render() {
		return (
				<form onSubmit={this.getStockInfo}>
					<SearchPanel />
					<StockTable />
				</form>
			);
	}
};

export default Form;