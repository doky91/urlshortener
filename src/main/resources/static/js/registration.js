/**
 * 
 */

function AccountForm(){
	
	var state= {
		"username": "",
		"password": "",
		"url": "",
		"redirectType": ""
	};

	function handleChange(event) {
		const name = event.target.name;
		const value = event.target.value;
		state[name] = value;
	}

	function handleSubmit(event) {
		
		var basicAuth = 'Basic ' + btoa(unescape(encodeURIComponent(state["username"] + ':' + state["password"])));
		
		axios.post('http://localhost:8080/register', {
			url: state["url"],
			redirectType: state["redirectType"] 
		}, {
			  headers: { 'Authorization': basicAuth }
		})
		.then(function (response) {
			ReactDOM.render(
				<div> {JSON.stringify(response.data)} </div>,
				document.getElementById('registration_output')
			);
		})
		.catch(function (error) {
			console.log(error);
		});

		event.preventDefault();
	}

	return (
		<form onSubmit={handleSubmit}>
			<label> Username: </label>
			<input type="text" name="username" onChange={handleChange} />  <br />
			<label> Password: </label> 
			<input type="password" name="password" onChange={handleChange} /> <br />
			<label>Registration link:</label>
			<input type="text" name="url" onChange={handleChange} /> <br />
			<label>Redirect type:</label>
			<input type="text" name="redirectType" onChange={handleChange} />  <br />
			<input type="submit" value="Submit" />
		</form>
	);
}

//Main
ReactDOM.render(
	<AccountForm />,
	document.getElementById('registration')
);
