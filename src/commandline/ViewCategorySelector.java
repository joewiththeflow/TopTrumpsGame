package commandline;

import model.GameInfo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



/**
 * The ViewCategorySelector class is responsible for displaying a list of
 * categories.
 * Menu elements are numbered, and the user is prompted to enter the number
 * corresponding to their menu selection.
 */
class ViewCategorySelector {

   /**
    * Show the category menu.
    *
    * @return a String representing the selected category.
    */
   String show(GameInfo gameInfo) {
      System.out.println("Select a category:");

      // Extract the category names (key) and store in a List.
      List<String> descriptionList = new ArrayList<>(gameInfo
              .getCardCategories().keySet());

      // Display prompt and return the user selection.
      int response = ViewUtils.prompt(descriptionList);

      // Return the category corresponding to the selected index.
      // - 1 to offset zero-based index numbering.
      return descriptionList.get(response - 1);
   }

}
