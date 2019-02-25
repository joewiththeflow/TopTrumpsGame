package commandline;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	public static void main(String[] args) {

		boolean writeGameLogsToFile = false;
        // Command line selection.
		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true;

        // Flag to check whether the user wants to quit the application
		boolean userWantsToQuit = false;

		// Loop until the user wants to exit the game.
		while (!userWantsToQuit) {
         
		// Commence the controller logic. Returns true when user wants to
        // quit.
        userWantsToQuit = new Controller(writeGameLogsToFile).start();
		}


	}

}
