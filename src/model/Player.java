package model;

import java.util.*;


/**
 * Defines an object representing a single player
 * The class is responsible for storing the player's data
 * and current band
 */

class Player {

   /**
    * The constant representing the max number of cards in an initial deck
    */
   private final int MAX_CARDS = 40;


   /**
    * The instance variables for the Player class
    * They consist of the players name, the number of winning rounds,
    * and the player's individual hand of cards as an array populated with objects of
    * type Card
    */
   private String name;
   private Boolean isHuman; //added this in, Joe
   private int roundsWon = 0;


   ArrayList<Card> hand = new ArrayList<>();


   /**
    *  constructor for the Class
    */
   Player(String name, boolean isHuman) {
      this.name = name;
      this.isHuman = isHuman;
   }


   /**
    * Getter method for player name
    */
   String getName() {
      return name;
   }

   ArrayList<Card> getHand() {
      return hand;
   }

   ArrayList<Card> getList() {
      return hand;
   }

   Boolean getIsHuman() {
      return isHuman;
   }

   /**
    * This method will return the object of type
    * Card, that lives at the top of the
    * players hand of cards
    * @return
    */
   Card getTopMostCard() {

      try {
         if (hand.size() != 0) {
            return hand.get(0);
         }
      } catch (IndexOutOfBoundsException e) {
         System.out.println("No cards in the player's hand");
         return null;
      }
      return null;

   }


   /**
    * This method will be called after each round for
    * the players that are currently in a game
    * It will submit their round cards to the pile
    * of cards. Then the winner can collect the cards
    * from there. Apply to every player, after every round
    * @param aDeck
    */
   void submitActiveCard(ArrayList<Card> aDeck) {
      try {
         if (hand.size() != 0) {
            aDeck.add(hand.get(0));
            hand.remove(hand.get(0));
         }

      } catch (NullPointerException n) {
         System.out.println("No card to submit in player's hand");
      }
   }

   //A method to add a card to the player's deck 
   //useful for dealing at the start
   void receiveCard(Card card) {
      hand.add(card);
   }


   /**
    * It will be called on a winner player object ,
    * so that the winner gets the all the cards, i.e.
    * the round cards if there was not a draw before,
    * or draw and pile cards if there was a draw previously
    * and the pile had already some cards in it
    */
   void takeAllCards(ArrayList<Card> aDeck) {

      try {
         if (!aDeck.isEmpty()) {
            for (int i = 0; i < aDeck.size(); i++) {
               hand.add(aDeck.get(i));
            }
         }
         //Pile is cleared from cards
         aDeck.clear();

         //increment the number of rounds won, since taking all cards
         //means that it was a winning round for the player
         roundsWon++;

      } catch (IndexOutOfBoundsException i) {
         System.out.println("No cards in the draw pile");
      }

   }


   /**
    *
    * @return a string containing the player name and hand.
    *
    */
   @Override
   public String toString() {
      StringJoiner joiner = new StringJoiner(", ", "(", ")");
      joiner.add("Player=" + name);
      for (Card card : hand) {
         joiner.add(card.toString());
      }
      return joiner.toString();
   }


}
