/*
Joshua Taylor
jostaylor@chapman.edu

Deck

This class creates the functionality for Deck objects.
The Constructor creates a new full deck of 52 cards
*/

import java.util.LinkedList;
import java.util.Random;

public class Deck{
  // Each element in the array will be a string of length 3: a number and char (for suit)
  private LinkedList<Card> cards;
  // All the cards
  private static String[][] allCards;

  /**
  * Empty Constructor that creates an ArrayList of cards
  */
  public Deck(){
    this.cards = new LinkedList<Card>();
    // Iterates through every suit
    for (int j = 0; j < 4; j++){
      // declare variable
      String suit = "";
      // Iterates through every numeric value
      for (int i = 2; i < 15; i++){
        // declare variable
        String cardValue = "";
        // If statements that determine when to add face cards and also determines suit
        if (j == 0){
          suit = "hearts";
        }
        else if (j == 1){
          suit = "clubs";
        }
        else if (j == 2){
          suit = "diamonds";
        }
        else if (j == 3){
          suit = "spades";
        }

        if (i == 11){
          cardValue = "J";
        }
        else if (i == 12){
          cardValue = "Q";
        }
        else if (i == 13){
          cardValue = "K";
        }
        else if (i == 14){
          cardValue = "A";
        }
        else{
          cardValue = Integer.toString(i);
        }

        cards.add(new Card(cardValue, suit, i));
      }
    }
  }

  /**
  * Chooses a card at random and removes and returns the card
  * @return Card
  */
  public Card deal(){
    int chooseCard = (int) (Math.random() * cards.size());
    return cards.remove(chooseCard);
  }

  
}
