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
		<div class="row justify-content-sm-center">
			<div class="desctext">
				<p>Top Trumps is a simple card game in which decks of cards are based on a theme. Within a deck each card represents an entity within that topic. Each card has a value for each characteristic of the deck. The objective of the game is to 'trump' your opponent by selecting a category and having a "better" value for your card than the opponent does in their current card.</p>
				<br />
			</div>
		</div>
		<div class="row justify-content-center">
			<a href="http://localhost:7777/toptrumps/game"><button type="button" class="btn btn-success" id="menubtn" >New Game</button></a>
			<a href="http://localhost:7777/toptrumps/stats"><button type="button" class="btn btn-success" id="menubtn" >View Stats</button></a>
		</div>
	</div>

</body>

<script type="text/javascript">

	// Method that is called on page load
	function initalize() {
		
	}

	// This is a reusable method for creating a CORS request. Do not edit this.
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

</html>
