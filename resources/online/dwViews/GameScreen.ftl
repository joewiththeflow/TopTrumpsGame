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
	<div class="row justify-content-center">
		  <div class="col-sm card" id="Player 1">
			<div class="cardheader">
			  <div class="playername">
				<h2>Player 1</h2>
			  </div>
			  <div class="shipname">
				<h2 id="p1_card_name"></h2>
			  </div>
			</div>
			<div class="shipinfo">
			  <div class="shipimg">
				<img id="p1_shipImg" src="" />
			  </div>
				<button class="btn btn-sm cardbtn" id="p1_cat1_name" disabled></button><br />
				<button class="btn btn-sm cardbtn" id="p1_cat2_name" disabled></button><br />
				<button class="btn btn-sm cardbtn" id="p1_cat3_name" disabled></button><br />
				<button class="btn btn-sm cardbtn" id="p1_cat4_name" disabled></button><br />
				<button class="btn btn-sm cardbtn" id="p1_cat5_name" disabled></button><br />
				<p id="p1_cards_left">Cards Remaining: </p>
			  </div>
		  </div>
		  
		  <div class="col-sm card" id="AI 1" style="visibility:hidden">
			<div class="cardheader">
			  <div class="playername">
				<h2 >AI 1</h2>
			  </div>
			  <div class="shipname">
				<h2 id="ai1_card_name"></h2>
			  </div>
			</div>
			<div class="shipinfo">
			  <div class="shipimg">
				<img id="ai1_shipImg" src="" />
			  </div>
				<p id="ai1_cat1_name"></p>
				<p id="ai1_cat2_name"></p>
				<p id="ai1_cat3_name"></p>
				<p id="ai1_cat4_name"></p>
				<p id="ai1_cat5_name"></p>
				<p id="ai1_cards_left"></p>
			</div>
		  </div>
		  <div class="col-sm card" id="AI 2" style="visibility:hidden">
			<div class="cardheader">
			  <div class="playername">
				<h2>AI 2</h2>
			  </div>
			  <div class="shipname">
				<h2 id="ai2_card_name"></h2>
			  </div>
			</div>
			<div class="shipinfo">
			  <div class="shipimg">
				<img id="ai2_shipImg" src="" />
			  </div>
			  <p id="ai2_cat1_name"></p>
				<p id="ai2_cat2_name"></p>
				<p id="ai2_cat3_name"></p>
				<p id="ai2_cat4_name"></p>
				<p id="ai2_cat5_name"></p>
				<p id="ai2_cards_left"></p>
			</div>
		  </div>
		  <div class="col-sm card" id="AI 3" style="visibility:hidden">
			<div class="cardheader">
			  <div class="playername">
				<h2>AI 3</h2>
			  </div>
			  <div class="shipname">
				<h2 id="ai3_card_name"></h2>
			  </div>
			</div>
			<div class="shipinfo">
			  <div class="shipimg">
				<img id="ai3_shipImg" src="" />
			  </div>
			  <p id="ai3_cat1_name"></p>
				<p id="ai3_cat2_name"></p>
				<p id="ai3_cat3_name"></p>
				<p id="ai3_cat4_name"></p>
				<p id="ai3_cat5_name"></p>
				<p id="ai3_cards_left"></p>
			</div>
		  </div>
		  <div class="col-sm card" id="AI 4" style="visibility:hidden">
			<div class="cardheader">
			  <div class="playername">
				<h2>AI 4</h2>
			  </div>
			  <div class="shipname">
				<h2 id="ai4_card_name"></h2>
			  </div>
			</div>
			<div class="shipinfo">
			  <div class="shipimg">
				<img id="ai4_shipImg" src="" />
			  </div>
			  <p id="ai4_cat1_name"></p>
				<p id="ai4_cat2_name"></p>
				<p id="ai4_cat3_name"></p>
				<p id="ai4_cat4_name"></p>
				<p id="ai4_cat5_name"></p>
				<p id="ai4_cards_left"></p>
			</div>
		  </div>
	</div>
	<div class="row justify-content-center">
		<button class="btn btn-info" id="categoryButton" onclick="categorySelection()">Category Selection</button>
	</div>
	<div class="row justify-content-center">
		<p class="center-msg" id="message"></p>
	</div>


	<div class="row">
			<ul>
				<li><p id="currentRound"></p></li>
				<li><p id="currentActivePlayer"></p></li>
				<li><p id="numOfCommunalCards"></p></li>
			</ul>
	</div>
	
	<!----------------Testing------------------------->
		<nav style="visibility:hidden;">
			<ul><li><p id="gameIndex"></p></li>
				<li><p id="playerNames"></p></li>
				<li><p id="p1_wins"></p></li>
				<li><p id="ai1_wins"></p></li>
				<li><p id="ai2_wins"></p></li>
				<li><p id="ai3_wins"></p></li>
				<li><p id="ai4_wins"></p></li>
				<li><p id="ai5_wins"></p></li>
				<li><p id="num_draws"></p></li>
				<li><p id="db_message"></p></li>
			</ul>
		</nav>
	<!--------------------------------------------------->
	
	
		<script type="text/javascript">
		
			//-------------Variables to help store values to be used between methods----------------------------------------------
			var gameIndex = 0;                 // value which holds the game reference for this tab
			var categories = "";			   // variable used to store category names
			var numPlayersLeft = 0;            // value used to keep track of how many players are left in the game
			var playerNames = "";			   // variable which holds player names
			var activePlayer = "";             // variable used to keep track of the current Active Player
			var activeCategory = "";		   // variable used to keep track of the current ActiveCategory
			var roundWinner = "";			   //variable to hold roundWinner
			var isHuman = true;				   // variable to store whether human is in game or not
			var wins = [0, 0, 0, 0, 0, 0];     // an array which keeps tracks of player wins, used by computeResult() API method
			var draws = 0;                     // value which keeps track of the number of draws
			
			//----------------------------------------------------------------------------------------------------------------------
			
			// Method that is called on page load
			function initalize() {
				
				startNewGame();
			}
			
			
			
			//---------------HELPER METHODS--------------------------------
			
			
			// -------------------------------------------------------------------------------------------------------------------
			// Call the changeImage() helper method with the id name of the tag to be updated and the title of the player's top card
			// Assumes the images are located at: http://dcs.gla.ac.uk/~richardm/TopTrumps/" + imgName +".jpg
			// Called by the getHumanTopCardTitle() API method.
			// Called by the getTopCardTitles() API method.
			// -------------------------------------------------------------------------------------------------------------------
			
			function changeImage(id, imgName){
				document.getElementById(id).src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + imgName +".jpg"
			}
			
			
			// -------------------------------------------------------------------------------------------------------------------
			// Call the updateCategories() helper method with the id name of the tag to be updated, with category name and value.
			// Called by the getHumanTopCardCategories() API method.
			// Called by the getTopCards() API method.
			// -------------------------------------------------------------------------------------------------------------------
			
			function updateCategories(id, key, value){
						document.getElementById(id).innerHTML = key + ": " + value;
			}
			
			
			
			// --------------------------------------------------------------------------------------------------------------------------------------
			// Call the updateButton() helper method with the id name of the button to be updated, the function to be assigned to the onlcick event,
			// the parameter to be passed to the function and the text to be displayed on the button.
			// Changes the function which is called when the button is clicked and the text which is displayed on the button.
			// Called by the newRound() API method.
			// Called by the getTopCards() API method.
			// Called by the computeResult() API method
			// -------------------------------------------------------------------------------------------------------------------------------------
			
			function updateButton(id, funcName, parameter, text) {
					document.getElementById(id).innerHTML = text;
					document.getElementById(id).onclick = function(){funcName(parameter)};
			}
			
			
			function updateButtonAuto(gameIndex) {
				getNumCommunalCards(gameIndex);	
			}
			
			// -----------------------------------------------------------------------------------------------
			// Call the categorySelection() method to initiate choosing of categories.
			// If the activePlayer is an AI player, call the selectCategory() API method to obtain AI's choice from Game.
			// If the activePlayer is Player 1, ask Player 1 to choose a category. Also adds EventListener's to <p>
			// tags on Player1's card by using the userCategoriesActivate() helper method.
			// Called by pressing button which has onlick="categorySelection().
			// -----------------------------------------------------------------------------------------------
				
			function categorySelection() {
				if (activePlayer != "Player 1") {
					selectCategory(gameIndex);
				}
				else {
					document.getElementById("message").innerHTML = "Player 1, please select a category on your card";
					userChoice();
				}
			}
			
			// assign an onclick method to each category on the user's card when it is Player 1's turn to select category
			function userChoice() {
			
				document.getElementById("p1_cat1_name").disabled = false;
				document.getElementById("p1_cat2_name").disabled = false;
				document.getElementById("p1_cat3_name").disabled = false;
				document.getElementById("p1_cat4_name").disabled = false;
				document.getElementById("p1_cat5_name").disabled = false;
						
				document.getElementById("p1_cat1_name").onclick = function(){userChoiceMade(categories[0])};
				document.getElementById("p1_cat2_name").onclick = function(){userChoiceMade(categories[1])};
				document.getElementById("p1_cat3_name").onclick = function(){userChoiceMade(categories[2])};
				document.getElementById("p1_cat4_name").onclick = function(){userChoiceMade(categories[3])};
				document.getElementById("p1_cat5_name").onclick = function(){userChoiceMade(categories[4])};
			
			}
			
			
			//-------------------------------------------------------------------------------------------------------------
			//Remove the onclick event from the categories on Player 1's card after a selection has been made
			//Also set the choice to be the activeCategory and pass along with gameIndex to selectCategoryHuman() API method.
			//Called by categorySelection() helper method.
			//--------------------------------------------------------------------------------------------------------------
			function userChoiceMade(category) {
				
				document.getElementById("p1_cat1_name").disabled = true;
				document.getElementById("p1_cat2_name").disabled = true;
				document.getElementById("p1_cat3_name").disabled = true;
				document.getElementById("p1_cat4_name").disabled = true;
				document.getElementById("p1_cat5_name").disabled = true;
				
				activeCategory = category;
				var gameIndexCat = "" + gameIndex + "xxxxx" + category;
				selectCategoryHuman(gameIndexCat);
			}
			
			// -------------------------------------------------------------------------------------------------------------------
			// Call the updateButtonComplete() helper method with the link you wish the button to link to when game complete.
			// Called by the playersLeft() API method when there is an overall winner.
			// -------------------------------------------------------------------------------------------------------------------
			
			function updateButtonComplete(link) {
				window.location=link;
			}
			
			
			//--------------------------------------------------------------------------------------------------------------------
			// Call makeVisible() helper method to make AI cards visible when it is the correct time in the game.
			// Called by 
			//----------------------------------------------------------------------------------------------------------------------
			
			function makeVisible() {
				
				for (i = 0; i < numPlayersLeft; i++) {
					for (j = 1; j < 5; j++) {
						if (playerNames[i] == ("AI " + j)) {
							document.getElementById("AI " + j).style.visibility = "visible";
						}
					}
				}
			}
			
			//--------------------------------------------------------------------------------------------------------------------
			// Call makeInvisible() helper method to make all AI cards invisible when it is the correct time in the game.
			// Called by newRound() API method.
			//----------------------------------------------------------------------------------------------------------------------
			
			function makeInvisible() {								
				for (i = 1; i < 5; i++) {
					document.getElementById("AI " + i).style.visibility = "hidden";
				}
			}
			
			
			// -----------------------------------------------------------------------------------------------
			// Highlight the selected category
			// -----------------------------------------------------------------------------------------------
			function highlightCategory(activeCategory) {
			
					for (j = 0; j < categories.length; j++) {
						if (activeCategory == categories[j]) {
							for (i = 1; i < numPlayersLeft; i++) {
								console.log("BOLDING " + "ai" + i +"_cat" + (j + 1) + "_name")
								document.getElementById("ai" + i +"_cat" + (j + 1) + "_name").style.fontWeight = 'bold';
							}
							document.getElementById("p1_cat" + (j+1) + "_name").style.fontWeight = 'bold';
						} 
					}
			}
			
			// -----------------------------------------------------------------------------------------------
			// UnHighlight the selected category
			// -----------------------------------------------------------------------------------------------
			
			function unHighlightCategory(activeCategory) {
				for (j = 0; j < categories.length; j++) {
					if (activeCategory == categories[j]) {
						for (i = 1; i < numPlayersLeft; i++) {
							document.getElementById("ai" + i +"_cat" + (j + 1) + "_name").style.fontWeight = 'normal';
						}
						document.getElementById("p1_cat" + (j+1) + "_name").style.fontWeight = 'normal';
					}
				}
			}
			
			// -----------------------------------------------------------------------------------------------
			// Highlight the selected category for the roundWinner
			// -----------------------------------------------------------------------------------------------
			function highlightWinningCategory(activeCategory) {
				if (roundWinner != "Player 1") {
						for (i = 1; i < 5; i++) {
							if (roundWinner == ("AI " + i)) {
								for (j = 0; j < categories.length; j++) {
									if(activeCategory == categories[j]) {
										document.getElementById("ai" + i + "_cat" + (j + 1) + "_name").style.border = "thick solid #7ccd7c";
									}
								}
							}
					    }
				}
				else {
					for (j = 0; j < categories.length; j++) {
						if(activeCategory == categories[j]) {
							document.getElementById("p1_cat" + (j + 1) + "_name").style.border = "thick solid #7ccd7c";
						}
					}
				}
			}
			
			// -----------------------------------------------------------------------------------------------
			// UnHighlight the selected category for the roundWinner
			// -----------------------------------------------------------------------------------------------
			function unHighlightWinningCategory(activeCategory) {
				if (roundWinner != "Player 1") {
						for (i = 1; i < 5; i++) {
							if (roundWinner == ("AI " + i)) {
								for (j = 0; j < categories.length; j++) {
									if(activeCategory == categories[j]) {
										document.getElementById("ai" + i + "_cat" + (j + 1) + "_name").style.border = "hidden";
									}
								}
							}
					    }
				}
				else {
					for (j = 0; j < categories.length; j++) {
						if(activeCategory == categories[j]) {
							document.getElementById("p1_cat" + (j + 1) + "_name").style.border = "hidden";
						}
					}
				}
			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
		
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
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
				
			
			
		//-------------------------------------------First Part of Game-----------------------------	
			
			
			// -----------------------------------------------------------------------------------------------
			// Call the startNewGame() API method to get the game index reference for this particular game tab.
			// Called by the initalise() method referenced within the <body> tag.
			// -----------------------------------------------------------------------------------------------
			
			function startNewGame() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/startNewGame"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					gameIndex = JSON.parse(responseText);
					document.getElementById("gameIndex").innerHTML = "Game Number: " + gameIndex;
					getNumCommunalCards(gameIndex);
					//getNumOfCardsLeft(gameIndex);
					getCategories(gameIndex);	
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// -----------------------------------------------------------------------------------------------
			// Call the getNumCommunalCards() API method with the gameIndex number for this particular tab.
			// Receives the number of cards currently in the communal pile and updates this number on screen.
			// Called by the startNewGame() API method.
			// Called by computeResult() API method.
			// -----------------------------------------------------------------------------------------------
			
			// This calls the getNumCommunalCards REST method from TopTrumpsRESTAPI
			function getNumCommunalCards(gameIndex) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getNumCommunalCards?gameIndex="+gameIndex); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					var numOfCommunalCards = JSON.parse(responseText);
					document.getElementById("numOfCommunalCards").innerHTML = "There are " + numOfCommunalCards 
					+ " cards in the communal pile.";
					getNumOfCardsLeft(gameIndex);
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// -----------------------------------------------------------------------------------------------
			// Call the getNumOfCardsLeft() API method with the gameIndex number for this particular tab.
			// Receives the number of cards left for each player and updates these numbers on screen.
			// Called by the startNewGame() API method.
			// Called by computeResult() API method.
			// -----------------------------------------------------------------------------------------------
			
			// This calls the getNumOfCardsLeft REST method from TopTrumpsRESTAPI
			function getNumOfCardsLeft(gameIndex) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getNumOfCardsLeft?gameIndex="+gameIndex); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					var numOfCardsLeft = JSON.parse(responseText);
					var playerNames = Object.keys(numOfCardsLeft);
					
					var player1Found = false;
					for (i = 0; i < playerNames.length; i++) {
						if (playerNames[i] == "Player 1") {
							document.getElementById("p1_cards_left").innerHTML = "Cards Remaining: " + numOfCardsLeft[playerNames[i]];
							player1Found = true;
							
						}
					}
					if (player1Found == false) {
						document.getElementById("p1_cards_left").innerHTML = "0";
					}
					
					for (i = 1; i < 5; i++) {
						var aiPlayerFound = false;
						for (j = 0; j < playerNames.length; j++) {
							if (playerNames[j] == "AI " + i) {
								document.getElementById("ai" + i + "_cards_left").innerHTML = "Cards Remaining: " + numOfCardsLeft[playerNames[j]];
								aiPlayerFound = true;
								
							}
						}
						if (aiPlayerFound == false) {
							document.getElementById("ai" + i + "_cards_left").innerHTML = "0";
						}
					}
					
					if (isHuman == false) {
						newRound(gameIndex);
					}
					
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// ------------------------------------------------------------------------------------------------------
			// Call the getCategories() API method with the gameIndex number for this particular tab.
			// Receives the category names for this deck of cards and updates global variables with this information.
			// Called by the startNewGame() API method.
			// ------------------------------------------------------------------------------------------------------
			
			// This calls the getCategories REST method from TopTrumpsRESTAPI
			function getCategories(gameIndex) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCategories?gameIndex="+gameIndex); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					categories = JSON.parse(responseText);
					categoryNames = Object.keys(categories); 
					categories = [categoryNames[0], categoryNames[1], categoryNames[2], categoryNames[3], categoryNames[4]];
					newRound(gameIndex);
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			
			// -----------------------------------------------------------------------------------------------
			// Call the newRound() API method with the gameIndex number for this particular tab.
			// Begins a new round within the Game and updates the round number on screen.
			// Uses updateButton helper method to update button for category selection.
			// Called by the getCategories() API method.
			// Called by user pressing on screen button "Next Round" when round has completed.
			// -----------------------------------------------------------------------------------------------
			
			// This calls the newRound REST method from TopTrumpsRESTAPI
			function newRound(gameIndex) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/newRound?gameIndex="+gameIndex); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					round = JSON.parse(responseText);
					document.getElementById("currentRound").innerHTML = "Current Round: " + round;
					getPlayerNames(gameIndex);
					updateButton("categoryButton", categorySelection, gameIndex, "Category Selection");
					document.getElementById("gameIndex").innerHTML = "Game Number: " + gameIndex;
					document.getElementById("message").innerHTML = "Players have drawn their cards";
					unHighlightCategory(activeCategory);
					unHighlightWinningCategory(activeCategory);
					
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			
			// -----------------------------------------------------------------------------------------------
			// Call the getPlayerNames() API method with the gameIndex number for this particular tab.
			// Receives the player Names of the players still in the game.
			// Called by the newRound() API method.
			// -----------------------------------------------------------------------------------------------
			
			// This calls the getPlayerNames REST method from TopTrumpsRESTAPI
			function getPlayerNames(gameIndex) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getPlayerNames?gameIndex="+gameIndex); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					playerNames = JSON.parse(responseText);
					document.getElementById("playerNames").innerHTML = "Players still in game: " + playerNames;
					
					if (round == 1) {
						numPlayersLeft = playerNames.length;
						document.getElementById("p1_wins").innerHTML = "Player 1 wins: 0";
						for (i = 1; i < 5; i++) {
							document.getElementById("ai" + i + "_wins").innerHTML = "AI " + i + " wins: 0";
						}
						document.getElementById("num_draws").innerHTML = "Number of draws: 0";
					}
					
					if (document.getElementById("p1_cards_left").innerHTML != "0") {
						getHumanTopCardTitle(gameIndex);
					}
					else {
						document.getElementById("Player 1").style.visibility = "hidden";
						getActivePlayer(gameIndex);
					}
					makeInvisible();
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			
			// -----------------------------------------------------------------------------------------------------------
			// Call the getHumanTopCardTitle() API method with the gameIndex number for this particular tab.
			// Receives the title of Player 1's top card and calls the changeImage helper method to update picture on card.
			// Called by the getPlayerNames() API method.
			// -----------------------------------------------------------------------------------------------------------
			
			function getHumanTopCardTitle(gameIndex) {
			
				//First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getHumanTopCardTitle?gameIndex="+gameIndex); // Request type and URL+parameters
				
				//Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				//CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					var humanTopCardTitle = JSON.parse(responseText);
					document.getElementById("p1_card_name").innerHTML = humanTopCardTitle;
					changeImage("p1_shipImg", humanTopCardTitle);
					getHumanTopCardCategories(gameIndex);
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			
			// -----------------------------------------------------------------------------------------------
			// Call the getHumanTopCardCategories() API method with the gameIndex number for this particular tab.
			// Receives the category detials for Player 1's top card and updates the card on screen.
			// Called by the getHumanTopCardTitle() API method.
			// -----------------------------------------------------------------------------------------------
			
			function getHumanTopCardCategories(gameIndex) {
			
				//First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getHumanTopCardCategories?gameIndex="+gameIndex); // Request type and URL+parameters
				
				//Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				//CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					var humanTopCardCategories = JSON.parse(responseText);
					
					var categoryNames = Object.keys(humanTopCardCategories);
					for (i = 0; i < categoryNames.length; i++) {
						updateCategories("p1_cat" + (i + 1) + "_name", categoryNames[i], 
						humanTopCardCategories[categoryNames[i]]);
					}  
					getActivePlayer(gameIndex);              
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			
			// -----------------------------------------------------------------------------------------------
			// Call the getActivePlayer() API method with the gameIndex number for this particular tab.
			// Receives the activePlayer Name and updates this value to the screen.
			// Called by the getHumanTopCardCategories() API method.
			// -----------------------------------------------------------------------------------------------
			
			// This calls the getActivePlayer REST method from TopTrumpsRESTAPI
			function getActivePlayer(gameIndex) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getActivePlayer?gameIndex="+gameIndex); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					activePlayer = JSON.parse(responseText);
					document.getElementById("currentActivePlayer").innerHTML = "Current Player: " + activePlayer;
					if (isHuman == false) {
						selectCategory(gameIndex);
					}
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			 
			
		//---------------------------------------Second Part of Game - Category Selection--------------    
			
			
			// -----------------------------------------------------------------------------------------------
			// Call the selectCategory() API method with the gameIndex number for this particular tab.
			// Receives the category picked by the AI Player for the current round and updates the screen.
			// Called when user presses button which says Category Selection if activePlayer is AI Player.
			// -----------------------------------------------------------------------------------------------
			
			function selectCategory(gameIndex) {
			
				//First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/selectCategory?gameIndex="+gameIndex); // Request type and URL+parameters
				
				//Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				//CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					activeCategory = JSON.parse(responseText);
					document.getElementById("message").innerHTML = activePlayer + " selected " + activeCategory;
					highlightCategory(activeCategory);
					getTopCardTitles(gameIndex);
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			
			// -----------------------------------------------------------------------------------------------
			// Call the getTopCardTitles() API method with the gameIndex number for this particular tab.
			// Receives the titles for each AI Player's top card and updates their cards on screen.
			// Uses the changeImage() helper method to update pictures of cards.
			// Called by the selectCategory() API method.
			// -----------------------------------------------------------------------------------------------
			
			
			function getTopCardTitles(gameIndex) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getTopCardTitles?gameIndex="+gameIndex); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					topCardTitles = JSON.parse(responseText);
					if (document.getElementById("p1_cards_left").innerHTML != "0") { 
						var topCardTitleIndex = 1;
					}
					else {
						var topCardTitleIndex = 0;
					}
					for(i = 1; i < 5; i++) {
						if (document.getElementById("ai" + i + "_cards_left").innerHTML != "0") {
							document.getElementById("ai" + i + "_card_name").innerHTML = topCardTitles[topCardTitleIndex];
							changeImage("ai" + i + "_shipImg", topCardTitles[topCardTitleIndex]);
							topCardTitleIndex += 1;
						}
					}	
					getTopCards(gameIndex);    
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// -----------------------------------------------------------------------------------------------
			// Call the getTopCards() API method with the gameIndex number for this particular tab.
			// Receives the details of each AI Player's cards and updates these details on screen.
			// Uses the updateButton method to prepare button to "Show Winner".
			// Called by the getTopCardTitles() API method.
			// -----------------------------------------------------------------------------------------------
			
			function getTopCards(gameIndex) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getTopCards?gameIndex="+gameIndex); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					topCards = JSON.parse(responseText);
					topCardsCategories = Object.keys(topCards[0]);
					
					if (document.getElementById("p1_cards_left").innerHTML != "0") { 
						var topCardsIndex = 1;
					}
					else {
						var topCardsIndex = 0;
					}
					for (i = 1; i < 5; i++) {
						if (document.getElementById("ai" + i + "_cards_left").innerHTML != "0") {
							for (j = 0; j < topCardsCategories.length; j++) {
								updateCategories("ai" + i +"_cat" + (j + 1) + "_name", topCardsCategories[j], 
								topCards[topCardsIndex][topCardsCategories[j]]);
								
							}
							topCardsIndex += 1;
						}
					}
					
					makeVisible();
					if (isHuman == true) {
						updateButton("categoryButton", computeResult, gameIndex, "Show Winner");
					}
					else {
					    computeResult(gameIndex);
					}
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			
			// -----------------------------------------------------------------------------------------------
			// Call the selectCategoryHuman() API method with the gameIndex number for this particular tab.
			// Also passes Category chosen as part of the string, both values separated by String "xxxxx".
			// Receives confirmation that game has processed human's ctageory choice from Game.
			// Called when user clicks on a category within their card, assuming they are the active player.
			// -----------------------------------------------------------------------------------------------
			
			function selectCategoryHuman(gameIndexCat) {
			
				//First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/selectCategoryHuman?gameIndexCat="+gameIndexCat); // Request type and URL+parameters
				
				//Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				//CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					activeCategory = JSON.parse(responseText);
					document.getElementById("message").innerHTML = activePlayer + " selected " + activeCategory;
					highlightCategory(activeCategory);

					getTopCardTitles(gameIndex);
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			


			// -----------------------------------------------------------------------------------------------
			// Call the computeResult() API method with the gameIndex number for this particular tab.
			// Receives the Round Winner Name or that the round was a draw and updates screen accordingly.
			// uses updateBButton helper method to prepare button for "Next Round".
			// Called when user presses button displaying "Show Winner".
			// -----------------------------------------------------------------------------------------------
			
			function computeResult(gameIndex) {
			
				//First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/computeResult?gameIndex="+gameIndex); // Request type and URL+parameters
				
				//Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				//CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					roundWinner = JSON.parse(responseText);
					
					if (roundWinner == null){
						draws += 1;
						document.getElementById("message").innerHTML = activePlayer + " chose " + activeCategory + " and it was a draw.";
						document.getElementById("num_draws").innerHTML = "Number of draws: " + draws;
					}
					else {
						if (roundWinner == "Player 1") {
							wins[0] += 1;
							document.getElementById("p1_wins").innerHTML = "Player 1 wins: " + wins[0];
						}
						else {
							for (i = 1; i < 5; i++) {
								if (roundWinner == ("AI " + i)) {
									wins[i] += 1;
									document.getElementById("ai" + i + "_wins").innerHTML = "AI " + i + " wins: " + wins[i];
								}
							}
						}
						highlightWinningCategory(activeCategory);
						document.getElementById("message").innerHTML = roundWinner + " won!";
						activePlayer = roundWinner;
					}
					
					getPlayersLeft(gameIndex);
					
				};
				
				
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// -----------------------------------------------------------------------------------------------
			// Call the getPlayersLeft() API method with the gameIndex number for this particular tab.
			// Receives the number of players left and checks to see if there is only one left.
			// Called from computeResult() API method.
			// -----------------------------------------------------------------------------------------------
			
			function getPlayersLeft(gameIndex) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getPlayersLeft?gameIndex="+gameIndex); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					var playersLeft = JSON.parse(responseText);
					
					//if isHuman == true or false
					// check if players less than 2
					//if so, check if remaining player is player 1 or computer and print winner - this should be the only choice regardless first
					if (playersLeft.length < 2) {
						if (playersLeft == "Player 1"){
							document.getElementById("p1_cards_left").innerHTML = "WINNER";
							updateButton("categoryButton", updateButtonComplete, 'http://localhost:7777/toptrumps/', "Return to Selection");
							
							updateDB(gameIndex);
						}
						else {
							for (i = 1; i < 5; i++) {
								if (playersLeft == "AI " + i) {
								
									winnerFound = true;
									document.getElementById("ai" + i + "_cards_left").innerHTML = "WINNER";
									updateButton("categoryButton", updateButtonComplete, 'http://localhost:7777/toptrumps/', "Return to Selection");
									updateDB(gameIndex);
								}
							}
						}
					}
					//if a winner isn't found then proceed if isHuman has previously been set to false
					//this will loop through the game without the human
					else if (isHuman == false && playersLeft.length > 1) {
			
							getNumCommunalCards(gameIndex);
						
					}

					//else check if human is still in game and set isHuman to false if no longer in game
					//create button which will begin game loop Auto Complete
					else if (isHuman == true) {
						var player1Found = false;
						for (i = 0; i < playersLeft.length; i++) {
							if(playersLeft[i] == "Player 1") {
								player1Found = true;
							}
						}
						if (player1Found == false) {
							isHuman = false;
							updateButton("categoryButton", updateButtonAuto, gameIndex, "Auto Complete");
							document.getElementById("message").innerHTML = "Player 1, you have been eliminated. Please press Auto Complete button to complete game";
						}	
					}	
					
				    //continue game as normal if human still in game and there is at least 1 AI player
					if (isHuman == true && playersLeft.length > 1) {
						getNumCommunalCards(gameIndex);
						getNumOfCardsLeft(gameIndex);
						//update categoryButton to "Show Winner", computeResult()
						updateButton("categoryButton", newRound, gameIndex, "Next Round");
					}
					
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// -----------------------------------------------------------------------------------------------
			// Call the updateDB() API method with the gameIndex number for this particular tab.
			// Tells the game to commit the results to the database.
			// Called by playersLeft() API method when there is only one player left.
			// -----------------------------------------------------------------------------------------------
			
			function updateDB(gameIndex) {
			
				//First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/updateDB?gameIndex="+gameIndex); // Request type and URL+parameters
				
				//Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				//CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					var message = JSON.parse(responseText);
					
					document.getElementById("db_message").innerHTML = message;
					
				};
				
				
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			

		</script>
		
		</body>
</html>