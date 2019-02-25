package commandline;

/**
 * A view class to throw an error message
 * in case that we cannot establish connection
 * update and closing of the Database.
 */
class ViewDBError {

   // Method to  return the error as a string message
   void show(String message) {
      ViewUtils.indent();
      System.out.println("Database error: " + message);
   }

}
