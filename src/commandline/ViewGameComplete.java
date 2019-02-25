package commandline;

import model.GameInfo;

/**
* A view to represent the end of a game state.
* It is responsible for returning the game winner object from
* the GameInfo class and print out the result, depending 
* on whether or not there was an actual winner and who that is.
**/
class ViewGameComplete {
   void show(GameInfo gameInfo) {

      // Extract required values from gameInfo.
      String winner = gameInfo.getRoundWinnerName();

      // Print out the result of the end of game state.
      ViewUtils.indent();
      if (winner == null) {
         System.out.println("Game Over: Stalemate!");
      } else {
         System.out.println("Game Over: " + winner + " wins the game!");
      }
   }
}