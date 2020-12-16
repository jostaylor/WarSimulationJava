/*
Joshua Taylor
jostaylor@chapman.edu

Player

This class creates the functionality for Player objects
It also handles a significant amount of the 'war' functionality
*/

import java.util.LinkedList;
import java.util.ArrayList;

public class Player{
  private int playerNum;
  private LinkedList<Card> playerCards;

  /**
  * Constructor that takes the players ID as input and initializes the instance variables
  */
  public Player(int playerNum){
    this.playerCards = new LinkedList<Card>();
    this.playerNum = playerNum;
  }

  /**
  * A void method that takes a card and adds it to the player's deck
  * @param Card
  */
  public void collect(Card card){
    playerCards.add(card);
  }

  /**
  * A function that removes a card from the players deck and then returns that card
  * @return Card
  */
  public Card flip(){
    return playerCards.remove();
  }

  /**
  * A function that checks to see if the player has won and collected every card in the deck
  * @return boolean
  */
  public boolean hasWon(){
    return (playerCards.size() == 52) ? true : false;
  }

	/**
	* Returns value of playerNum
	* @return
	*/
	public int getPlayerNum() {
		return playerNum;
	}

	/**
	* Sets new value of playerNum
	* @param
	*/
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}

	/**
	* Returns the list of playerCards
	* @return
	*/
	public String getPlayerCards() {
    String str = "";
		for (int i = 0; i < this.playerCards.size(); i++){
      str += " " + this.playerCards.get(i).getValue();
      str += " of " + this.playerCards.get(i).getSuit() + " (" + this.playerCards.get(i).getNumericValue() + "),";
    }
    return str;
	}

  /**
  * Returns the amount of cards the player has
  */
  public int getNumPlayerCardsLeft(){
    return this.playerCards.size();
  }

  /**
  * A void method that handles the logic for war.
  * The war logic adapts if the players runs out of cards in the process
  * @param Player, player, ArrayList, and booelan
  * @return a boolean which shows true if a double war occured and false if only 1 occured
  */
  public static boolean war(Player p1, Player p2, ArrayList<Card> warCards, boolean isDoubleWar){
    // Declare and initialize variable
    Card finalCard1 = new Card();
    Card finalCard2 = new Card();

    // Creates boolean vars to keep track of face-up/final card
    boolean finalCard1Exists = false;
    boolean finalCard2Exists = false;

    // Checks to see if the player is already out of cards
    if (p1.playerCards.size() == 0){
      // Makes final card the second to last card from the ArrayList
      finalCard1 = warCards.get(warCards.size() - 2);
      warCards.remove(warCards.size() - 2); // THIS REMOVES THE DUPLICATE
      finalCard1Exists = true;
    }
    if (p2.playerCards.size() == 0){
      // Makes final card the last card from the ArrayList
      finalCard2 = warCards.get(warCards.size() - 1);
      warCards.remove(warCards.size() - 1); // THIS REMOVES THE DUPLICATE
      finalCard2Exists = true;
    }

    // Checks to see if the player has already laid their face up card
    if (finalCard1Exists == false){
      // Flips cards onto table face down until last card which always becomes the face-up/final card
      for (int i = 0; i < 3; i++){
        if (p1.playerCards.size() > 1){
          warCards.add(p1.flip());
        }
        // If there is only one card left
        else{
          finalCard1 = p1.flip();
          finalCard1Exists = true;
          break;
        }
      }
    }

    // Checks to see if the player has already laid their face up card
    if (finalCard2Exists == false){
      // Same thing as above except this time for the second player
      for (int i = 0; i < 3; i++){
        if (p2.playerCards.size() > 1){
          warCards.add(p2.flip());
        }
        // If there is only one card left
        else{
          finalCard2 = p2.flip();
          finalCard2Exists = true;
          break;
        }
      }
    }

    // Flips two face up cards, 1 from each player ONLY if a final card doesn't exist yet
    if (finalCard1Exists == false){
      finalCard1 = p1.flip();
      finalCard1Exists = true;
    }
    if (finalCard2Exists == false){
      finalCard2 = p2.flip();
      finalCard2Exists = true;
    }

    // If player 1 wins war
    if (finalCard1.getNumericValue() > finalCard2.getNumericValue()){
      // Collects the face up cards
      p1.collect(finalCard1);
      p1.collect(finalCard2);
      // Collects every face down card from the ArrayList that contains them
      for (int i = 0; i < warCards.size(); i++){
        p1.collect(warCards.get(i));
      }
      // Returns what kind of war occured
      return isDoubleWar;
    }
    // If player 2 wins war
    else if (finalCard1.getNumericValue() < finalCard2.getNumericValue()){
      // Collects the face up cards
      p2.collect(finalCard1);
      p2.collect(finalCard2);
      // Collects every face down card from the ArrayList that contains them
      for (int i = 0; i < warCards.size(); i++){
        p2.collect(warCards.get(i));
      }
      // Return what kind of war occured
      return isDoubleWar;
    }
    // If the players tie AGAIN
    else{
      // Adds face up cards to the ArrayList
      warCards.add(finalCard1);
      warCards.add(finalCard2);
      // Updates boolean so we know a double war occured
      isDoubleWar = true;
      // Runs war AGAIN
      Player.war(p1, p2, warCards, isDoubleWar);
      return true;
    }

  }

}
