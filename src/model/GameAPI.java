package model;

import java.util.Observer;



/**
 * The GameAPI class is a Facade, responsible for providing public methods
 * relating to key game actions, for use by external packages.
 */
public class GameAPI {

   private Game game;

   /**
    *
    * @param numAIPlayers the number of AI opponents.
    * @param deckInputFile the location of the deck file.
    * @param writeLogsToFile logging flag, true = log to file.
    */
   public GameAPI(int numAIPlayers, String deckInputFile,
                  Boolean writeLogsToFile) {
      game = new Game(numAIPlayers, deckInputFile, writeLogsToFile);
   }



   /**
    * Initialises a new game by loading the deck, spawning players, dealing the
    * cards and selecting the first active player.
    */
   public void newGame() {
      game.newGame();
   }



   /**
    * Initialises a new round by incrementing the round number.
    */
   public void newRound() {
      game.newRound();
   }



   /**
    * Set the active category, either by asking the player or by
    * automatically selecting a category, depending on whether the active player
    * is human.
    */
   public void selectCategory() {
      game.selectCategory();
   }



   /**
    * @param category the card category.
    */
   public void setCategory(String category) {
      game.setCategory(category);
   }



   /**
    * Establish the result of the round by comparing the top card of all
    * players.
    */
   public void computeResult() {
      game.computeResult();
   }



   /**
    * Conclude the round by reallocating cards and eliminating players with no
    * remaining cards.
    */
   public void concludeRound() {
      game.concludeRound();
   }



   /**
    * @return a GameInfo object, representing containing key game data.
    */
   public GameInfo getGameInfo() {
      return new GameInfo(game);
   }



   /**
    * Add a new observer to the Game.
    *
    * @param object to be the observer.
    */
   public void addObserver(Observer object) {
      game.addObserver(object);
   }
}
