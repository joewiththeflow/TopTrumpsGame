package model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * The Logger class is responsible for appending log messages to a file.
 *
 * Upon instantiation, any existing log file located at logFilePath will be
 * deleted.
 *
 */
public class Logger {

   private Boolean writeGameLogsToFile;
   private String logFilePath;

   /**
    * Instantiates a new Logger.
    *
    * @param logFilePath         the log filepath
    * @param writeGameLogsToFile flag to indicate whether logger should be
    *                            activated
    */
   Logger(String logFilePath, Boolean writeGameLogsToFile) {
      this.logFilePath = logFilePath;
      this.writeGameLogsToFile = writeGameLogsToFile;

      // If the log file exists, delete it.
      try {
         Files.delete(Paths.get(logFilePath));
      } catch (IOException e) {
         System.out.println("Log file created: " + e.getMessage());
      }

   }

   /**
    * Log a message to the logfile.
    *
    * @param logMessage the log message
    */
   void log(String logMessage) {
      if (writeGameLogsToFile) {
         // Create a print writer in auto-flush mode to allow appending.
         try (PrintWriter out =
                      new PrintWriter(new FileWriter(logFilePath, true))) {
            // Write the log message.
            out.println(logMessage);

         } catch (IOException e) {
            System.err.println("Logger error: " + e.getMessage());
         }
      }
   }

}
