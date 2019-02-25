package commandline;

import static commandline.CharCodes.HORIZONTAL;



/**
* This class is responsible for displaying
* the statistics view when the player has selected
* the corresponding option. It will provide information
* about the number of games played, the counts for the human 
* and AI players wins, the average draws and the number of the rounds 
* for the game being viewed.
**/
class ViewStats {
   void show(int gameCount, int AIWinCount, int humanWinCount,
             double avgDrawCount, int maxRoundCount) {

      final int COL_WIDTH = 30;

      System.out.println();
      String title = "STATISTICS";
      System.out.println(title);
      for (int i = 0; i < title.length(); i++) {
         System.out.print(HORIZONTAL.getCode());
      }
      System.out.println();

      String formatString = "%-" + COL_WIDTH + "s";
      //String formatString = "%-30s";
      ViewUtils.indent();



      // Print out the information about the number of games, the
      // wins for AI and human players, the average drawsd and  the maximum 
      // number of rounds.
      System.out.println(String.format(formatString + "%d",
              "Games played overall:", gameCount));

      ViewUtils.indent();
      System.out.println(String.format(formatString + "%d",
              "AI Wins:", AIWinCount));

      ViewUtils.indent();
      System.out.println(String.format(formatString + "%d",
              "Human Wins:", humanWinCount));

      ViewUtils.indent();
      System.out.println(String.format(formatString + "%.2f",
              "Average draws:", avgDrawCount));

      ViewUtils.indent();
      System.out.println(String.format(formatString + "%d",
              "Max rounds in one game:", maxRoundCount));
   }

}
