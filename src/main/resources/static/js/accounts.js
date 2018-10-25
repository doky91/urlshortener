/**
 * 
 */

function AccountForm(){

	var state= {
		"accountId": ""
	};

	function handleChange(event) {
		const name = event.target.name;
		const value = event.target.value;
		state[name] = value;
	}

	function handleSubmit(event) {
		axios.post('http://localhost:8080/account', {
			accountId: state["accountId"] 
		})
		.then(function (response) {
			ReactDOM.render(
				<div> {JSON.stringify(response.data)} </div>,
				document.getElementById('accounts_output')
			);
		})
		.catch(function (error) {
			console.log(error);
		});

		event.preventDefault();
	}

	return (
		<form onSubmit={handleSubmit}>
			<label>Account id:</label>
			<input type="text" name="accountId" onChange={handleChange} />
			<input type="submit" value="Submit" />
		</form>
	);
}

//Main
ReactDOM.render(
	<AccountForm />,
	document.getElementById('accounts')
);
