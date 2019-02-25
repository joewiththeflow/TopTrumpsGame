package commandline;

import java.util.*;

import static commandline.CharCodes.*;

/**
 * The ViewUtils class provides static utility methods for displaying
 * information to the user via System.out, and for prompting for user input
 * via System.in.
 */
final class ViewUtils {

   // Private constructor to prevent accidental instantiation of this
   // utility class.
   private ViewUtils() {
   }


   // =======================================================================
   //
   // PACKAGE UTILITY METHODS
   //
   // =======================================================================


   /**
    * Print a list of items as a numbered list, and prompt the user for a
    * selection.
    *
    * @param menuItems the menu items.
    * @return int representing the selection.
    */
   static int prompt(Collection menuItems) {
      // Print the menu.
      int i = 1;
      for (Object item : menuItems) {
         indent();
         System.out.println(i++ + ": " + item.toString());
      }
      // Prompt for a selection from the user.
      indent();
      System.out.print("Selection: ");


      Scanner in = new Scanner(System.in);
      int selection = 0;
      for (;;) {
         try {
            // Grab the user input.
            selection = Integer.parseInt(in.nextLine());

            // Define limits of allowable selections.
            final int LOWER_BOUND = 1;
            final int UPPER_BOUND = menuItems.size();

            // Check selection is within a valid range.
            if (selection >= LOWER_BOUND && selection <= UPPER_BOUND) {
               break;
            } else {
               System.out.print(String.format("Entry must be between %d and %d: ",
                       LOWER_BOUND, UPPER_BOUND));
            }
         } catch (NumberFormatException e) {
            System.out.print("Entry must be an integer: ");
         }
      }
      return selection;
   }


   /**
    *
    * @param middleWidth
    * @param valueWidth
    * @param title
    * @param cardProperties
    * @param numCardsInHand
    */
   static void printStackedCardStyle(int middleWidth, int valueWidth,
                                     String title,
                                     Map<String, Integer> cardProperties,
                                     int numCardsInHand) {
      Boolean stacked = false;
      if (numCardsInHand > 1) {
         stacked = true;
      }

      printTopBoundary(middleWidth);
      System.out.println();
      printTitle(middleWidth, title, stacked);
      if (stacked) { printTopBoundaryStackedCards(numCardsInHand); }
      System.out.println();
      printMiddleBoundary(middleWidth);
      if (stacked) { printEdgesStackedCards(numCardsInHand); }
      System.out.println();
      for (Map.Entry<String, Integer> entry : cardProperties.entrySet()) {
         printCardProperty(middleWidth, valueWidth, entry.getKey(),
                 entry.getValue().toString());
         if (stacked) { printEdgesStackedCards(numCardsInHand); }
         System.out.println();
      }
      printBottomBoundary(middleWidth, stacked);
      if (stacked) { printEdgesStackedCards(numCardsInHand); }
      System.out.println();
      if (stacked) { printBottomStackedCards(middleWidth, numCardsInHand); }
      System.out.println();
   }


   /**
    *
    * @param middleWidth
    * @param valueWidth
    * @param titles
    * @param allCardProperties
    */
   static void printHorizontalCardStyle(int middleWidth, int valueWidth,
                                        List<String> titles,
                                        List<Map<String, Integer>>
                                                allCardProperties,
                                        String activeCategory, int horizGap) {
      int numCards = titles.size();

      for (int i = 0; i < numCards; i++) {
         printTopBoundary(middleWidth);
         indent(horizGap);
      }
      System.out.println();

      for (int i = 0; i < numCards; i++) {
         printTitle(middleWidth, titles.get(i), false);
         indent(horizGap);
      }
      System.out.println();

      for (int i = 0; i < numCards; i++) {
         printMiddleBoundary(middleWidth);
         indent(horizGap);
      }
      System.out.println();

      // Put all the keys into an ArrayList. Keys will be common amongst all
      // cards.
      List<String> keys = new ArrayList<>();
      for (Map.Entry<String, Integer> entry : allCardProperties.get(0)
              .entrySet()) {
         keys.add(entry.getKey());
      }


      // For each key, print the corresponding key:value, for each card.
      for (int i = 0; i < keys.size(); i++) {
         for (Map<String, Integer> cardProperties : allCardProperties) {
            String categoryMarkerLeft = "";
            String categoryMarkerRight = "";
            if (keys.get(i).equals(activeCategory)) {
               categoryMarkerLeft = ACTIVE_CATEGORY_LEFT.getCode();
               categoryMarkerRight = ACTIVE_CATEGORY_RIGHT.getCode();
            }
            String winnerMarker = "";
            printCardProperty(middleWidth, valueWidth,
                    categoryMarkerLeft + "" + keys.get(i) + "" +
                            categoryMarkerRight,
                    cardProperties.get(keys.get(i)).toString() + winnerMarker);
            indent(horizGap);
         }
         System.out.println();
      }

      for (int i = 0; i < numCards; i++) {
         printBottomBoundary(middleWidth, false);
         indent(horizGap);
      }
      System.out.println();
   }


   // =======================================================================
   //
   // PRIVATE HELPER METHODS
   //
   // =======================================================================


   /**
    *
    */
   static void indent() {
      System.out.print(" ");
   }

   static void indent(int indent) {
      StringBuilder stringBuilder = new StringBuilder();
      for (int i = 0; i < indent; i ++) {
         stringBuilder.append(" ");
      }
      System.out.print(stringBuilder.toString());
   }


   /**
    * Print the topmost boundary line of a card representation.
    *
    * @param middleWidth the middle width of the card representation.
    */
   private static void printTopBoundary(int middleWidth) {
      // Print top boundary.
      System.out.print(TOP_LEFT.getCode());
      for (int i = 0; i < middleWidth; i++) {
         System.out.print(HORIZONTAL.getCode());
      }
      System.out.print(TOP_RIGHT.getCode());
   }


   /**
    *
    * @param numCards
    */
   private static void printTopBoundaryStackedCards(int numCards) {
      // Intermediate cards.
      final int numIntermediateCards = numCards - 2;
      for (int i = 0; i < numIntermediateCards; i++) {
         System.out.print(TEE_DOWN.getCode());
      }
      // Backmost card.
      if (numCards > 1) {
         System.out.print(TOP_RIGHT.getCode());
      }
   }


   /**
    * Print the title line of a card representation.
    *
    * @param middleWidth the middle width of the card representation.
    * @param title       the card title
    */
   private static void printTitle(int middleWidth, String title, Boolean stacked) {
      // Topmost card.
      System.out.print(VERTICAL.getCode());
      System.out.print(String.format("%-" + middleWidth + "s", title));

      // Boundary between adjacent cards.
      if (stacked) {
         System.out.print(TEE_RIGHT.getCode());

      } else {
         System.out.print(VERTICAL.getCode());
      }
   }


   /**
    *
    * @param numCards
    */
   private static void printEdgesStackedCards(int numCards) {
      // Remaining cards.
      for (int i = 0; i < numCards - 1; i++) {
         System.out.print(VERTICAL.getCode());
      }
   }


   /**
    * Print the middle boundary line of a card representation.
    *
    * @param middleWith the middle width of the card representation.
    */
   private static void printMiddleBoundary(int middleWith) {
      // Topmost card.
      System.out.print(TEE_RIGHT.getCode());
      for (int i = 0; i < middleWith; i++) {
         System.out.print(HORIZONTAL.getCode());
      }
      System.out.print(TEE_LEFT.getCode());
   }


   /**
    * Print a line containing a card property.
    *
    * @param middleWidth the middle width of the card representation.
    * @param valueWidth  the max width of the value field.
    * @param property    the card property name.
    * @param value       the card property value.
    */
   private static void printCardProperty(int middleWidth, int valueWidth,
                                         String property, String value) {

      System.out.print(VERTICAL.getCode());

      // Property name is formatted to be left justified, with an explicit
      // limit on allowable field length (defined by middleWidth -
      // valueWidth). String lengths which exceed this width will be
      // truncated.
      String propertyFormatString = "%-" + (middleWidth - valueWidth) +
              "." + (middleWidth - valueWidth) + "s";
      System.out.print(String.format(propertyFormatString, property));

      // Property value is formatted to be right justified, with an explicit
      // limit on allowable field length (defined by valueWidth). String
      // lengths which exceed this width will be truncated.
      String valueFormatString = "%" + valueWidth + "." + valueWidth + "s";
      System.out.print(String.format(valueFormatString, value));
      System.out.print(VERTICAL.getCode());
   }


   /**
    *
    * @param middleWidth
    * @param numCards
    */
   private static void printBottomStackedCards(int middleWidth, int numCards) {
      if (numCards > 1) {
         System.out.print(" ");
         System.out.print(BOTTOM_LEFT.getCode());
         for (int i = 0; i < middleWidth; i++) {
            System.out.print(HORIZONTAL.getCode());
         }
         // Intermediate cards.
         final int numIntermediateCards = numCards - 2;
         for (int i = 0; i < numIntermediateCards; i++) {
            System.out.print(TEE_UP.getCode());
         }
         System.out.print(BOTTOM_RIGHT.getCode());
      }
   }


   /**
    * Print the bottom boundary line of a card representation.
    *
    * @param middleWidth the middle width
    */
   private static void printBottomBoundary(int middleWidth, Boolean stacked) {
      // Topmost card.
      System.out.print(BOTTOM_LEFT.getCode());

      if (stacked) {
         System.out.print(TEE_DOWN.getCode());
      } else {
         System.out.print(HORIZONTAL.getCode());
      }

      for (int i = 0; i < middleWidth - 1; i++) {
         System.out.print(HORIZONTAL.getCode());
      }
      System.out.print(BOTTOM_RIGHT.getCode());
   }

}
