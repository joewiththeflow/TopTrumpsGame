package commandline;

import model.GameInfo;

import java.util.List;
import java.util.Map;
import static commandline.CharCodes.*;



/**
 * The ViewRoundSummary class is responsible for displaying information relevant
 * to the end of a new round:
 * The selected category.
 * The result.
 */
class ViewRoundSummary {


   /**
    * Show the summary.
    *
    * @param gameInfo the game info object.
    *
    */
   void show(GameInfo gameInfo) {

      // Extract required values from gameInfo.
      List<String> allTopCardTitles = gameInfo.getTopCardTitles();
      List<Map<String, Integer>> allTopCards = gameInfo.getTopCards();
      String activeCategory = gameInfo.getActiveCategory();
      List<String> playerNames = gameInfo.getPlayerNames();
      String roundWinner = gameInfo.getRoundWinnerName();
      String activePlayer = gameInfo.getActivePlayerName();

      // Character width of a card.
      final int CARD_WIDTH = 25;

      // Available characters between vertical boundaries.
      final int MIDDLE_WIDTH = CARD_WIDTH - 2;

      // Max field width of value column
      final int VALUE_WIDTH = 3;

      final int INDENT_WIDTH = 1;

      final int H_GAP = 2;

      ViewUtils.printHorizontalCardStyle(MIDDLE_WIDTH, VALUE_WIDTH, allTopCardTitles,
              allTopCards, activeCategory, H_GAP);

      final int fieldLength = CARD_WIDTH - INDENT_WIDTH + H_GAP;
      String formatString = "%-" + fieldLength + "s";
      for (String playerName : playerNames) {
         ViewUtils.indent(INDENT_WIDTH);
         String winnerMarker = "";
         if (playerName.equals(roundWinner)) {
            winnerMarker = ROUND_WINNER.getCode();
         }
         System.out.print(String.format(formatString, playerName + " "
                 + winnerMarker));
      }
      System.out.println();
      System.out.println();



      //Determine what to put out to view,
      // depending on the outcome. Notify user
      // with appropriate message
      ViewUtils.indent(INDENT_WIDTH);
      if (roundWinner == null) {
         System.out.println(activePlayer + " picks category " +
                 activeCategory + " and the result is a draw.");
      } else if (activePlayer.equals(roundWinner)) {
         System.out.println(activePlayer + " picks category " +
                 activeCategory + " and wins!");
      }  else {
         System.out.println(activePlayer + " picks category " +
         activeCategory + " and loses to " + roundWinner + ".");
      }
      System.out.println();
   }

}
