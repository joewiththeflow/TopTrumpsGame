package online.dwResources;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;
import persistence.PostgresPersistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


import model.GameAPI;

import model.GameInfo;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	
	
		//maintain a list of GameAPI objects to allow for multiple tabs
		private List<GameAPI> games;
		private int numOfCurrentGames;
		private String deckInputFile;
		private int numAIPlayers;
		private PostgresPersistence dbConnection;
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------
		numOfCurrentGames = 0;
		deckInputFile = conf.getDeckFile();
		numAIPlayers = conf.getNumAIPlayers();
		games = new ArrayList<GameAPI>();
		dbConnection = new PostgresPersistence();
	}

	
	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------
	
	
	
	// We wish to create a Game object (accessed via a GameAPI object), 
	// but return the index of the GameAPI object 
	// so that the tab can keep track of which Game object it 'owns', 
	// We'll return this as a JSON string.
	@GET
	@Path("/startNewGame")
	public String startNewGame() throws IOException{
		//create a GameAPI object to get access to Game methods
		GameAPI game = new GameAPI(numAIPlayers, deckInputFile, false);
			
		//add the new GameAPI object to the list of GameAPIs
		games.add(game);

		//start a new game
		game.newGame();
			
		//create a String to hold the current game number
		String gameInd = "" + numOfCurrentGames;
			
		// increment number of current Games in preparation for another Game in another tab
		numOfCurrentGames++;
			
		//send the Index to the web page
		//there is a method startNewGame() in GameScreen.ftl which will grab this info
		String stringAsJSONString = oWriter.writeValueAsString(gameInd);
			
		return stringAsJSONString;
		}
	
	@GET
	@Path("/getCategories")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String getCategories(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
		
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to get the list of categories
		Map<String, Integer> categories = gameInfo.getCardCategories();
		
		//send the map of categories to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(categories);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/newRound")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String newRound(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
	
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//start a new round
		game.newRound();
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to return the new round number
		int round = gameInfo.getRound();
		
		//send the map of categories to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(round);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/getNumOfCardsLeft")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	
	public String getNumOfCardsLeft(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
		
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to get a list of cards left
		Map<String, Integer> cardsLeft = gameInfo.getNumOfCardsLeft();
		
		//send the map of categories to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(cardsLeft);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/getNumCommunalCards")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String getNumCommunalCards(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
		
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to get the number of communal cards
		int numOfCommunalCards = gameInfo.getNumOfCommunalCards();
		
		//send the number of communal cards to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(numOfCommunalCards);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/getActivePlayer")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String getActivePlayer(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
	
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to return the active player
		String activePlayer  = gameInfo.getActivePlayerName();
		
		//send the active player to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(activePlayer);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/getPlayerNames")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String getPlayerNames(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
	
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to return the player names
		List<String> players = gameInfo.getPlayerNames();
		
		//send the list of player names to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(players);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/getHumanTopCardTitle")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String getHumanTopCardTitle(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
	
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to return the human top card title
		String humanTopCardTitle = gameInfo.getHumanTopCardTitle();
		
		//send the top card title to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(humanTopCardTitle);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/getHumanTopCardCategories")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String getHumanTopCardCategories(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
		
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to get the list of categories
		Map<String, Integer> categories = gameInfo.getCardCategories();
		
		//send the map of categories to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(categories);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/getTopCardTitles")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String getTopCardTitles(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
	
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to return the top card titles
		List<String> topCardTitles = gameInfo.getTopCardTitles();
		
		//send the list of top card titles to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(topCardTitles);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/getTopCards")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String getTopCards(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
	
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to return the top card categories and values
		List<Map<String, Integer>> topCards = gameInfo.getTopCards();
		
		//send the list of maps of categories/values to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(topCards);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/selectCategory")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String selectCategory(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
	
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//select category in game
		game.selectCategory();
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to return the new round number
		String activeCategory = gameInfo.getActiveCategory();
		
		//send the active category to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(activeCategory);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/selectCategoryHuman")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String selectCategoryHuman(@QueryParam("gameIndexCat") String gameIndexCat) throws IOException {
		
		//split the string into a String array, splitting on "xxxxx"
		String[] request = gameIndexCat.split("xxxxx");
		String num = request[0];
		int gameNumber = Integer.parseInt(num);
	
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//select category in game via GameAPI
		game.selectCategory();
		
		//Human actually needs to set category
		String selectedCategory = request[1];
		game.setCategory(selectedCategory);
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to return the active category
		String activeCategoryCheck = gameInfo.getActiveCategory();
		
		//send the active category to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(activeCategoryCheck);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/computeResult")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String computeResult(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
	
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//compare Cards
		game.computeResult();
		
		//transfer cards
		game.concludeRound();
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to return the round winner
		String roundWinner = gameInfo.getRoundWinnerName();
		
		//send the round winner to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(roundWinner);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/getPlayersLeft")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String getPlayerLeft(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
	
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to return the players left
		List<String> players = gameInfo.getPlayerNames();
		
		//send the list of players left to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(players);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/updateDB")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String updateDB(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
	
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		String message = "DB updated";
		try {
			dbConnection.establishDBConnection();
            dbConnection.update(gameInfo);
            dbConnection.commit();
            dbConnection.closeDBConnection();
        } catch (SQLException | ClassNotFoundException e) {
        	message = "Database Error";
        }
		
		//send success or error message as JSON string
		String stringAsJSONString = oWriter.writeValueAsString(message);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/getDB")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String getDB() throws IOException {
		
		Map<String, Integer> dbDetails = new LinkedHashMap<String, Integer>();
		
		try {
            dbConnection.establishDBConnection();
            		dbDetails.put("Games", dbConnection.getGameCount());
            		dbDetails.put("AI Wins", dbConnection.getAIWinCount());
            		dbDetails.put("Human Wins", dbConnection.getHumanWinCount());
            		dbDetails.put("Average Draws per game", (int) dbConnection.getAverageDraws());
            		dbDetails.put("Longest Game", dbConnection.getMaxRoundCount());
            dbConnection.closeDBConnection();
         } catch (SQLException | ClassNotFoundException e) {
        	 System.out.println("Database Error");
         }
		
		//send the database details to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(dbDetails);
		return stringAsJSONString;
	}
}
