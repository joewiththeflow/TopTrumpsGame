package commandline;

import model.GameAPI;
import model.GameInfo;
import model.GameState;
import persistence.PostgresPersistence;

import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;





/**
 * The Controller class is responsible for triggering appropriate Views,
 * handling any returned user input, and reacting to Game events (gameAPI states).
 */
class Controller implements Observer {

   private Boolean writeGameLogsToFile;
   private GameAPI gameAPI;
   private List<String> initialPlayerNames;
   private PostgresPersistence dbConnection;

 

  /**
    * Instantiates a new Controller.
    *
    * @param writeGameLogsToFile flag to indicate if gameAPI logs should be
    *                            written to a debug file.
    */
   Controller(Boolean writeGameLogsToFile) {
      this.writeGameLogsToFile = writeGameLogsToFile;
      dbConnection = new PostgresPersistence();
   }

 

  /**
    * Handle main menu selection.
    *
    * @return a quit flag.
    */
   Boolean start() {
      // There are always 4 AI players in command line mode.
      final int NUM_AI_PLAYERS = 4;

      // Future flexibility, define input file here.
      final String DECK_INPUT_FILE = "StarCitizenDeck.txt";

      // First, we present the main menu to the user and await their response.
      final int selection = new ViewMainMenu().show();

     
	  // Now handle the response appropriately.
      // Start a new game.
      if (selection == 1) {
         gameAPI = new GameAPI(NUM_AI_PLAYERS, DECK_INPUT_FILE,
                 writeGameLogsToFile);
         // Observe the gameAPI.
         gameAPI.addObserver(this);
         gameAPI.newGame();



      // View statistics.
      } else if (selection == 2) {
         try {
            dbConnection.establishDBConnection();
            new ViewStats().show(dbConnection.getGameCount(),
                    dbConnection.getAIWinCount(),
                    dbConnection.getHumanWinCount(),
                    dbConnection.getAverageDraws(),
                    dbConnection.getMaxRoundCount());
            dbConnection.closeDBConnection();
         } catch (SQLException | ClassNotFoundException e) {
            new ViewDBError().show(e.getMessage());
         }



      // Quit.
      } else {
         return true;
      }

      return false;

   }




   /*
   /  Observe the game and react as appropriate to key events.
   */
   public void update(Observable observable, Object o) {
      // Take a snapshot of game info and establish the game state.
      GameInfo gameInfo = gameAPI.getGameInfo();
      GameState gameState = gameInfo.getGameState();



      /*
      /  The following conditional block reacts to game events by serving
      /  views, commencing further game logic etc, as appropriate for the
      /  received event.
      */
      if (gameState.equals(GameState.PLAYERS_SPAWNED)) {
         // Record the player names at onset of a new game.
         initialPlayerNames = gameInfo.getPlayerNames();

      } else if (gameState.equals(GameState.NEW_GAME_INITIALISED)) {
         // Once the game has been initialised, we start the first round.
         gameAPI.newRound();

      } else if (gameState.equals(GameState.NEW_ROUND_INITIALISED)) {
         // If the user is still in the game, we show the user his top card.
         if (gameInfo.getNumHumanCards() != null) {
            new ViewNewRound().show(gameInfo, initialPlayerNames);
         }
         // Pause if the active player is not human, to allow the user to review
         // his card.
         if (!gameInfo.getActivePlayerHuman() &&
                 gameInfo.getNumHumanCards() != null) {
            new ViewPause().show();
         }
         // Regardless of whether the human is in the game, commence category
         // selection logic.
         gameAPI.selectCategory();


      } else if (gameState.equals(GameState.CATEGORY_REQUIRED)) {
         // The human is the active player, so he should select a category.
         // Show the appropriate view, and return the result to the game.
         final String selection = new ViewCategorySelector().show(gameInfo);
         gameAPI.setCategory(selection);

      } else if (gameState.equals(GameState.CATEGORY_SELECTED)) {
         // When the category is defined, compute the winner.
            gameAPI.computeResult();

      } else if (gameState.equals(GameState.ROUND_RESULT_COMPUTED)) {
         // Show the results of the round.
         new ViewRoundSummary().show(gameInfo);
         // If the user is still in the game, enforce a pause to allow the
         // user to review the results.
         if (gameInfo.getNumHumanCards() != null) {
            new ViewPause().show();
         }
         gameAPI.concludeRound();

      } else if (gameState.equals(GameState.HUMAN_OUT)) {
         new ViewHumanBooted().show(gameInfo);
         new ViewPause().show();

      } else if (gameState.equals(GameState.ROUND_COMPLETE))
         // Continue to play rounds so long as there is more than one player
         // in the game.
         if (gameInfo.getPlayerNames().size() > 1) {
            gameAPI.newRound();
         } else {
            new ViewGameComplete().show(gameInfo);
            try {
               dbConnection.establishDBConnection();
               dbConnection.update(gameInfo);
               dbConnection.commit();
               dbConnection.closeDBConnection();
            } catch (SQLException | ClassNotFoundException e) {
               new ViewDBError().show(e.getMessage());
            }
         }
      }
}
