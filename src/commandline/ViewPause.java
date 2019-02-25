package commandline;

import java.util.Scanner;

/**
 * The ViewPause class is responsible for interrupting application flow until
 * the user presses ENTER.
 */
class ViewPause {

   /**
    * Show the pause screen and wait for user input.
    */
   void show() {
      ViewUtils.indent();
      System.out.print("Press ENTER to proceed...");
      new Scanner(System.in).nextLine();
   }

}
