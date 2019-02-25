<html>

<head>
	<title>Top Trumps</title>

	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

	<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>

	<!-- Custom styles for this page -->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,800" rel="stylesheet"> 
	<link rel="stylesheet" href="../resources/main.css"/>
</head>

<div class="header">
	<h1>Top Trumps</h1>
</div>

<body onload="initalize()"> <!-- Call the initalize method when the page loads -->

	<div class="container">
		<div class="justify-content-center">
			<table class="table table-striped stats-table">
			  <tbody>
			    <tr>
			      <th scope="row">Games Played</th>
			      <td id="games">0</td>
			    </tr>
			    <tr>
			      <th scope="row">Human Wins</th>
			      <td id="human_wins">0</td>
			    </tr>
			    <tr>
			      <th scope="row">AI Wins</th>
			      <td id="ai_wins">0</td>
			    </tr>
			    <tr>
			      <th scope="row">Average Draws</th>
			      <td id="average_draws">0</td>
			    </tr>
			    <tr>
			      <th scope="row">Longest Game</th>
			      <td id="longest_game">0</td>
			    </tr>
			  </tbody>
			</table>
		</div>

		<br />

		<div class="row justify-content-center">
			<a href="http://localhost:7777/toptrumps/game"><button onclick="newGame()" type="button" class="btn btn-success" id="menubtn">New Game</button></a>
			<a href="http://localhost:7777/toptrumps/"><button type="button" class="btn btn-success" id="menubtn" >Home</button></a>
		</div>
	</div>

</body>


	<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {

				getDB();
				
			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------

			function newGame() {
				window.location='http://localhost:7777/toptrumps/game';
			}

			function createCORSRequest(method, url) {
				var xhr = new XMLHttpRequest();
				if ("withCredentials" in xhr) {

					// Check if the XMLHttpRequest object has a "withCredentials" property.
					// "withCredentials" only exists on XMLHTTPRequest2 objects.
					xhr.open(method, url, true);

				} else if (typeof XDomainRequest != "undefined") {

					// Otherwise, check if XDomainRequest.
					// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
					xhr = new XDomainRequest();
					xhr.open(method, url);

				} else {

					// Otherwise, CORS is not supported by the browser.
					xhr = null;

				}
				return xhr;
			}

		</script>
		
		<script type="text/javascript">

			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function getDB() {

				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getDB"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					var details = JSON.parse(responseText);
					var detailNames = Object.keys(details);
					document.getElementById("games").innerHTML = details["Games"];
					document.getElementById("human_wins").innerHTML = details["Human Wins"];
					document.getElementById("ai_wins").innerHTML = details["AI Wins"];
					document.getElementById("average_draws").innerHTML = details["Average Draws per game"];
					document.getElementById("longest_game").innerHTML = details["Longest Game"] + " rounds";
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			

		</script>
		
	</body>
	</html>