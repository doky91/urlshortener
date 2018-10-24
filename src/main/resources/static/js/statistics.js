/**
 * 
 */

function generateTable(data) {

	function Table() {
		const rows = Object.keys(data).map((k) => {
			return ( 
				<tr key={k}>
					<td key={k}>{k}</td>
					<td key={data[k]}>{data[k]}</td> 
				</tr>
			);
		});

		return (
			<table>
				<thead><tr><th>Link</th><th>Visits</th></tr></thead>
				<tbody>{rows}</tbody>
			</table>
		);
	}

	ReactDOM.render(
		<Table />,
		document.getElementById('statistics_output')
	);
}

function getTableData(username) {
	axios.get('http://localhost:8080/statistics/' + username)
	.then(function(response) {
		generateTable(response.data);
	}).catch(function(error) {
		console.log(error);
	});
}

function StatisticsForm(){

	var state= {};

	function handleChange(event) {
		const name = event.target.name;
		const value = event.target.value;
		state[name] = value;
	}

	function handleSubmit(event) {
		getTableData(state["accountId"]);
		event.preventDefault();
	}

	return (
		<form onSubmit={handleSubmit}>
			<label>Username:</label>
			<input type="text" name="username" onChange={handleChange} /> <br />
			<label>Password:</label> 
			<input type="password" name="password" onChange={handleChange} /> <br />
			<label>Account id:</label>
			<input type="text" name="accountId" onChange={handleChange} />
			<input type="submit" value="Submit" />
		</form>
	);
}

//Main
ReactDOM.render(
	<StatisticsForm />,
	document.getElementById('statistics')
);
