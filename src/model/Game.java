package model;

import java.util.*;
import java.io.FileReader;
import java.io.IOException;



/**
 * The Game class is responsible for defining game logic and flow.
 */
public class Game extends Observable {
   private Logger logger;
   private GameState gameState;
   private ArrayList<Card> deck = new ArrayList<>();
   private ArrayList<Player> players = new ArrayList<>();
   private Player activePlayer;
   private Player roundWinner;
   private Player gameWinner;
   private String deckInputFile;
   private String activeCategory;
   private int numAIPlayers;
   private int round = 0;
   private int humanWonRounds = 0;
   private int numDraws = 0;
   private String divider = "----------------------------------------";


   // =========================================================================
   // CONSTRUCTORS
   // =========================================================================

   Game(int numAIPlayers, String deckInputFile,
        Boolean writeGameLogsToFile) {
      this.deckInputFile = deckInputFile;
      this.numAIPlayers = numAIPlayers;

      String logFilePath = "toptrumps.log";
      logger = new Logger(logFilePath, writeGameLogsToFile);
   }


   // =========================================================================
   // GETTERS & SETTERS
   // =========================================================================

   GameState getGameState() {
      return gameState;
   }
   
   ArrayList<Card> getDeck() {
	   return deck;
   }

   ArrayList<Player> getPlayers() {
      return players;
   }

   Player getActivePlayer() {
      return activePlayer;
   }

   Player getRoundWinner() {
      return roundWinner;
   }

   Player getGameWinner() {
      return gameWinner;
   }

   String getActiveCategory() {
      return activeCategory;
   }

   int getRound() {

      return round;
   }

   int getHumanWonRounds() {
      return humanWonRounds;
   }

   int getNumDraws() {
      return numDraws;
   }

   private void setGameState(GameState gameState) {
      this.gameState = gameState;
      setChanged();
      notifyObservers();
   }

   void setCategory(String category) {
      activeCategory = category;
   }



   // =========================================================================
   // GAME LOGIC METHODS
   // =========================================================================

   /*
   /  Initialises a new game by loading the deck, spawning players, dealing the
   /  cards and selecting the first active player.
   */
   void newGame() {
      logger.log("New game starting.");
      logger.log(divider);


      // Create the deck.
      createDeck();
      logger.log("Deck loaded: " + deck.toString());
	  logger.log(divider);


      // Shuffle the deck.
      shuffleDeck();
      logger.log("Deck shuffled: " + deck.toString());
      logger.log(divider);

      // Create the players.
      createPlayers();
      setGameState(GameState.PLAYERS_SPAWNED);

      // Deal cards to players' decks.
      deal();
      for (Player player : players) {
         logger.log("Hand: " + player);
      }
      

      // Randomly select the active player for the first round.
      selectRandomPlayer();

      setGameState(GameState.NEW_GAME_INITIALISED);
   }





   /*
   /  Initialises a new round by incrementing the round number.
   */
   void newRound() {
      // Increase the round number on every round.
      round++;

      // Write to the log file
	   logger.log(divider);
      logger.log("\nRound " + round + " starting.");
      logger.log("Active player: " + activePlayer.getName());

      // Log player name and topmost card for all players.
      for (Player player : players) {
         logger.log(player.getName() + "'s card: " + player.getTopMostCard()
                 .toString());
      }

      setGameState(GameState.NEW_ROUND_INITIALISED);
   }





   /*
   /  Set the active category, either by asking the player or by
   /  automatically selecting a category, depending on whether the active player
   /  is human.
   */
   void selectCategory() {
      // If user is the active player, ask for the user's category.
      if (activePlayer.getIsHuman()) {
         setGameState(GameState.CATEGORY_REQUIRED);
      }
      // Or get AI active player to select best category.
      else {
         Card activeCard = activePlayer.getTopMostCard();
         activeCategory = activeCard.getBestCategory();
      }

      logger.log("Selected category: " + activeCategory + "=" +
              activePlayer.getTopMostCard().getCardProperties()
                      .get(activeCategory));
      logger.log(divider);

      setGameState(GameState.CATEGORY_SELECTED);
   }





   /*
   /  Establish the result of the round by comparing the top card of all
   /  players.
   */
   void computeResult() {
      // Get the winner (null if draw).
      roundWinner = compareTopCards();


      if (roundWinner == null) {
         logger.log("Round winner: Draw");
         logger.log(divider);
         // Increment statistic.
         numDraws++;

      } else {
         logger.log("Round winner: " + roundWinner.getName());
	      logger.log(divider);
         this.gameWinner = roundWinner;
         if (roundWinner.getIsHuman()) {
            // Increment statistic.
            humanWonRounds++;
         }
      }

      setGameState(GameState.ROUND_RESULT_COMPUTED);
   }




   /*
   /  Conclude the round by reallocating cards and eliminating players with no
   /  remaining cards.
   */
   void concludeRound() {

      ArrayList<Card> currentTopCards = getCurrentTopCards();
      transferCards(roundWinner, currentTopCards);
      eliminatePlayers();

      logger.log("Communal Deck: " + deck);
      logger.log(divider);
      for (Player player : players) {
         logger.log("Hand: " + player);
      }

      logger.log("Round complete.");

      setGameState(GameState.ROUND_COMPLETE);
   }


   // =========================================================================
   // HELPER METHODS
   // =========================================================================

   private void createPlayers() {
      // create human player first
      Player human = new Player("Player 1", true);
      players.add(human);
     
      // create remaining AI Players
      for (int i = 0; i < numAIPlayers; i++) {
         Player AI = new Player("AI " + (i + 1), false);
         players.add(AI);
      }
   }

   

   // read in file to create the deck and shuffle
   private void createDeck() {
      FileReader reader = null;
      try {
         try {
            reader = new FileReader(deckInputFile);
            Scanner in = new Scanner(reader);
            
            // read the top line column names of the file
            // e.g. description, size, speed etc.
            String categories = in.nextLine();

            // loop through the file line by line, creating a card and adding to the deck
            while (in.hasNextLine()) {
               String values = in.nextLine();
               Card newCard = new Card(categories, values);
               deck.add(newCard);
            }
         } finally {
            if (reader != null) {
               reader.close();
            }
         }
      } catch (IOException e) {
         System.out.print("error");
      }
   }
   private void shuffleDeck() {
      Collections.shuffle(deck);
   }



   // only deal while there are cards in the deck
   // means cards can be dealt in a round robin fashion without 
   // worrying about how many players and cards there are
   private void deal() {
      int index = 0;
      while (!deck.isEmpty()) {
         Card card = deck.get(0);
         players.get(index).receiveCard(card);
         deck.remove(0);
         if (index < players.size() - 1) {
            index++;
         } else {
            index = 0;
         }
      }
   }


   // A method to determine who the active player id
   // when the game is initiated the first time.
   private void selectRandomPlayer() {
      Random rand = new Random();
      // Restrict random number range to available indexes in the players list.
      // - 1 to offset zero-based index numbering.
      int random = rand.nextInt(players.size());
      activePlayer = players.get(random);
   }



   // A method to look at the selected category
   // and compare the values for this category among
   // the players and their top most cards
   private Player compareTopCards() {
      // COMPARE CARDS
      int bestValue = activePlayer.getTopMostCard().getCardPropertyValue(activeCategory);
      // assume activePlayer will win the round and then test if this is true
      // if no other card beats or equals the ActivePlayer's card
      // the activePlayer will be returned as the winner
      Player currentWinner = activePlayer;
      for (Player player : players) {
         // if not the active player
         if (!(player == activePlayer)) {
            // check specific player value for the active category
            int value = player.getTopMostCard().getCardPropertyValue(activeCategory);
            if (bestValue < value) {
               bestValue = value;
               currentWinner = player;
            }
            // if value matches top value then there is a tie
            else if (bestValue == value) {
               currentWinner = null;
            }
         }
      }
      return currentWinner;
   }




   // This method will collect the current cards
   // from all players and place them into a new array
   // for the current round.
   private ArrayList<Card> getCurrentTopCards() {
      // logic to get round cards from all players
      ArrayList<Card> currentTopCards = new ArrayList<>();
      for (Player player : players) {
         player.submitActiveCard(currentTopCards);
      }
      return currentTopCards;
   }





   // Method to transfer the cards to the winner player if any
   // or to the communal deck if there is a draw
   private void transferCards(Player roundWinner, ArrayList<Card> currentTopCards) {
      // if there is a winner, the winner becomes the active player and takes round
      // cards
      if (roundWinner != null) {
         // set winner of round to be activePlayer for next round
         activePlayer = roundWinner;
         // take the pile of round cards
         roundWinner.takeAllCards(currentTopCards);
         // take communal cards if communal pile is not empty
         if (!deck.isEmpty()) {
            roundWinner.takeAllCards(deck);
         }
      }
      // if there is a draw, add round cards to the communal pile
      else {
         while (!currentTopCards.isEmpty()) {
            Card card = currentTopCards.get(0);
            deck.add(card);
            currentTopCards.remove(0);
         }
      }
   }




   // check if any players have no cards and eliminate them
   private void eliminatePlayers() {
      
      Iterator<Player> iter = players.iterator();

      while (iter.hasNext()) {
         Player player = iter.next();

         if (player.getList().isEmpty()) {
            iter.remove();
            if (player.getIsHuman()) {
               setGameState(GameState.HUMAN_OUT);
            }
            // need to remember that due to successive draws, the active player could run
            // out of cards
            // select a new random player if player gets eliminated
            if (!players.contains(activePlayer)) {
               selectRandomPlayer();
            }
         }
      }
   }
}
