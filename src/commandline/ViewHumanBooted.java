package commandline;

import model.GameInfo;

/**
* This class is responsible for providing
* the view in the case of an eliminated 
* human player. The player will be notified
* that he is out of the game and he will be 
* aware that the remaining AI players will
* continue until the game is over.
**/
class ViewHumanBooted {

   // Notify user that he has been eliminated
   void show(GameInfo gameInfo) {
      ViewUtils.indent();
      System.out.print("You have lost! ");

      // If there are more players in the game, then they can continue playing and the human 
      // player will be aware of that.
      if (gameInfo.getPlayerNames().size() > 1) {
         System.out.print("Now be a good sport and let the AI finish...");
      }

      System.out.println();
   }
}
