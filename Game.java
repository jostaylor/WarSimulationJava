/*
Joshua Taylor
jostaylor@chapman.edu

Game

This class creates the functionality for Game objects
These objects simulate a game of war using card, deck, and player objects
*/

import java.util.ArrayList;

public class Game{
  private Player player1;
  private Player player2;

  private int numBattles;
  private int numWars;
  private int numDoubleWars;

  /**
  * Empty Constructor that initializes the private variables
  */
  public Game(){
    this.numBattles = 0;
    this.numWars = 0;
    this.numDoubleWars = 0;
  }

  /**
  * A  method that adds 1 to the amount of battles
  */
  public void addWar(){
    this.numWars += 1;
  }

  /**
  * A method that adds 1 to the amount of double wars
  */
  public void addDoubleWar(){
    this.numDoubleWars += 1;
  }

  /**
  * An accessor method that return the value of numBattles
  * @return int
  */
  public int getNumBattles(){
    return this.numBattles;
  }

  /**
  * An accessor method that return the value of numWars
  * @return int
  */
  public int getNumWars(){
    return this.numWars;
  }

  /**
  * An accessor method that return the value of numDoubleWars
  * @return int
  */
  public int getNumDoubleWars(){
    return this.numDoubleWars;
  }


  /**
  * A method that plays one full game of war
  */
  public void play(){
    // Creates players and Deck
    player1 = new Player(1);
    player2 = new Player(2);
    Deck fullDeck = new Deck();

    // Deals the deck into 2 even stacks of 26 cards for each player
    for (int i = 0; i < 52; i++){
      // Iterates every other card and adds card from the deck to each player
      if (i % 2 == 0){
        player1.collect(fullDeck.deal());
      }
      else{
        player2.collect(fullDeck.deal());
      }
    }
    // Debug statement
    //System.out.println(player1.getPlayerCards());

    // Begin playing
    while (player1.hasWon() == false && player2.hasWon() == false){
      // Each player flips a card onto the table
      Card card1 = player1.flip();
      Card card2 = player2.flip();

      // Chooses a random number between 0 and 1 in order to randomize the order the cards are collected in
      int randomizeCollection = (int)(Math.random() * 2);

      // If the first card is greater
      if (card1.getNumericValue() > card2.getNumericValue()){
        // Implements the randomization
        if (randomizeCollection == 0){
          player1.collect(card1);
          player1.collect(card2);
        }
        else{
          player1.collect(card2);
          player1.collect(card1);
        }
      }
      // If the second player's card is greater
      else if (card1.getNumericValue() < card2.getNumericValue()){
        // Implements the randomization
        if (randomizeCollection == 0){
          player2.collect(card1);
          player2.collect(card2);
        }
        else{
          player2.collect(card2);
          player2.collect(card1);
        }
      }
      // If the cards have the same value --> war is played
      else{
        // Adds initial cards to a new ArrayList
        ArrayList<Card> cardsForWar = new ArrayList<Card>(52);
        cardsForWar.add(card1);
        cardsForWar.add(card2);
        // Runs war and returns true/false into a boolean
        boolean wasDoubleWar = Player.war(player1, player2, cardsForWar, false);
        // Boolean checks to see if a single or double war occured
        if (wasDoubleWar == true){
          this.numDoubleWars += 1;
        }
        else{
          this.numWars += 1;
        }
      }

      // Update statistic
      this.numBattles += 1;
      // Debuggin stuff
      //System.out.println("Num Battles: " + this.numBattles);
      //System.out.println("player 1 left: " + player1.getNumPlayerCardsLeft());
      //System.out.println("player 2 left: " + player2.getNumPlayerCardsLeft());
      //System.out.println(card1.getValue());
      //System.out.println(card2.getValue());

    }

    // Lets user know the game is over
    System.out.println("Battles, wars, and double wars: " + this.numBattles + ", " + this.numWars + ", and " + this.numDoubleWars);


  }

}
