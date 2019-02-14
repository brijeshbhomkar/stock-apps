import React from "react"

class Form extends React.Component {

	render() {
		return (
				<form onSubmit={this.props.getStockInfo}>
					<button>Find stock</button>
				</form>
			);
	}
};

export default Form;