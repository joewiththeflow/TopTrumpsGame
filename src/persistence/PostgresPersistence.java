package persistence;

import java.sql.*;

public class PostgresPersistence {

   /**
    * SQL Queries
    */
   String QUERY_getNumOfGames = "SELECT COUNT (*) FROM GameData";
   String QUERY_AIWins = "SELECT COUNT (*) FROM GameData WHERE winnerIsHuman = '0'";
   String QUERY_humanWins = "SELECT COUNT (*) FROM GameData WHERE winnerIsHuman = '1'";
   String QUERY_avgDraws = "SELECT AVG(numOfDraws) FROM GameData";
   String QUERY_maxRounds = "SELECT MAX(numOfRounds) FROM GameData";
   // Declare Connection
   private Connection connection = null;
   // Declare connection properties
   private String dbURL, username, password, dbname, logSuppressor;
   // Declare attributes to be written to the database
   private int numOfGames = 0;
   private int numOfRounds = 0;
   private int numPlayerWins = 0;
   private Boolean gameWinnerHuman;
   private String gameWinnerName;
   private double numDraws = 0.0;

   /**
    * Constructs a postgreSQL connection to the persistence database with no preset stats
    */
   public PostgresPersistence() {
      /** PRODUCTION VALUES */

      dbURL = "jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/";
      username = "m_17_2320989b";
      password = "2320989b";
      dbname = "m_17_2320989b";
      logSuppressor = "?loggerLevel=OFF";

      // TODO: Remove, for testing only.
      /** LOCAL VALUES - TM */
      //username = "postgres";
      //dbname = "dbname";
      //password = "pw";
      //dbURL = "jdbc:postgresql://localhost:5432/";

      // TODO: Remove, for testing only.
      // LOCAL VALUES - CB
      // dbURL = "jdbc:postgresql://192.168.1.4:5432/";
      // Get the total previous games
      // this.numOfGames = this.getGameCount();
   }

   /**
    * Updates all properties based on a GameInfo object
    * @param gameInfo The GameInfo object
    */
   public void update(model.GameInfo gameInfo) {
      this.setGameDraws(gameInfo.getNumDraws());
      this.setGameWinnerIsHuman(gameInfo.getGameWinnerHuman());
      this.setGameWinnerName(gameInfo.getGameWinnerName());
      this.setNumGameRounds(gameInfo.getRound());
      this.setPlayerRounds(gameInfo.getHumanRoundsWon());
   }

   /**
    * Set the number of draws for the current game
    *
    * @param num The number of draws
    */
   public void setGameDraws(int num) {
      this.numDraws = (double)num;
   }

   /**
    * Set the winner name
    *
    * @param name The name of the winning player
    */
   public void setGameWinnerName(String name) {
      this.gameWinnerName = name;
   }

   /**
    * Set the winner human status
    *
    * @param isHuman An int (1 = human, 0 = AI)
    */
   public void setGameWinnerIsHuman(Boolean isHuman) {
      this.gameWinnerHuman = isHuman;
   }

   /**
    * Set the number of rounds for the current game
    *
    * @param num The number of rounds
    */
   public void setNumGameRounds(int num) {
      this.numOfRounds = num;
   }

   /**
    * Set the number of rounds the player won
    *
    * @param num The number of rounds won
    */
   public void setPlayerRounds(int num) {
      this.numPlayerWins = num;
   }

   /**
    * Establish the connection to the database.
    */
   public void establishDBConnection() throws SQLException,
           ClassNotFoundException {
      // Define JDBC driver.
      Class.forName("org.postgresql.Driver");
      connection = DriverManager.getConnection(dbURL + dbname + logSuppressor,
              username, password);
   }

   /**
    * Close the connection to the database.
    */
   public void closeDBConnection() throws SQLException {
      connection.close();
   }

   /**
    * Commit a full game record to the database
    *
    * @return True if successful
    */
   public void commit() throws SQLException {

      String QUERY_commit = "INSERT INTO gamedata (" +
              "winnerishuman, " +
              "winnername, " +
              "numofdraws, " +
              "numofrounds, " +
              "numplayerwonrounds" +
              ") " +
              "VALUES (" +
              gameWinnerHuman +
              ", " +
              "'" +
              gameWinnerName +
              "'" +
              ", " +
              numDraws +
              ", " +
              numOfRounds +
              ", " +
              numPlayerWins +
              ")";

      Statement statement = connection.createStatement();
      statement.executeUpdate(QUERY_commit);
   }

   /**
    * Get the total number of games previously played
    *
    * @return The number of games played
    */
   public int getGameCount() throws SQLException {
      Statement statement = null;
      int gameNo = 0;
      statement = connection.createStatement();
      ResultSet resSet = statement.executeQuery(QUERY_getNumOfGames);
      while (resSet.next()) {
         gameNo = resSet.getInt(1);
      }
      return gameNo;
   }

   /**
    * Get the number of previous games won by the AI
    *
    * @return The number of games
    */
   public int getAIWinCount() throws SQLException {
      Statement statement = null;
      int AIWins = 0;
      statement = connection.createStatement();
      ResultSet resSet = statement.executeQuery(QUERY_AIWins);

      while (resSet.next()) {
         AIWins = resSet.getInt(1);
      }

      return AIWins;
   }

   /**
    * Get the number of previous games won by humans
    *
    * @return The number of games
    */
   public int getHumanWinCount() throws SQLException {
      Statement statement = null;
      int humanWins = 0;

      statement = connection.createStatement();
      ResultSet resSet = statement.executeQuery(QUERY_humanWins);
      while (resSet.next()) {
         humanWins = resSet.getInt(1);
      }

      return humanWins;
   }

   /**
    * Get the average number of draws in previous games
    *
    * @return The number of draws
    */
   public double getAverageDraws() throws SQLException {
      Statement statement = null;
      double drawCount = 0;
      statement = connection.createStatement();
      ResultSet resSet = statement.executeQuery(QUERY_avgDraws);
      while (resSet.next()) {
         drawCount = resSet.getDouble(1);
      }

      return drawCount;
   }

   /**
    * Get the highest number of rounds played in a single game
    *
    * @return The number of rounds
    */
   public int getMaxRoundCount() throws SQLException {
      Statement statement = null;
      int maxRounds = 0;
      statement = connection.createStatement();
      ResultSet resSet = statement.executeQuery(QUERY_maxRounds);
      while (resSet.next()) {
         maxRounds = resSet.getInt(1);
      }

      return maxRounds;
   }

}
