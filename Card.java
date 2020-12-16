/*
Joshua Taylor
jostaylor@chapman.edu

Card

This class creates the functionality for Card objects.
Each card has a value and a suit as a well as a numerical value so face cards can be numerically compared to 2-10 cards
*/

public class Card{
  // Value is a string of 2-10, J, Q, K, A
  private String value;
  // Suit is a word: hearts, clubs, spades, or diamonds
  private String suit;
  // Numeric value of value var used to compare cards
  private int numericValue;

  /**
  * Overloaded Constructor
  */
  public Card(String value, String suit, int numericValue){
    this.value = value;
    this.suit = suit;
    this.numericValue = numericValue;
  }

  /**
  * Empty Constructor that creates fake values in order to show that the program is broken
  */
  public Card(){
    this.value = "1";
    this.suit = "NoSuit";
    this.numericValue = 1;
  }

	/**
	* Returns value of value
	* @return String
	*/
	public String getValue() {
		return value;
	}

	/**
	* Sets new value of value
	* @param String
	*/
	public void setValue(String value) {
		this.value = value;
	}

  /**
	* Returns value of numericValue
	* @return int
	*/
	public int getNumericValue() {
		return numericValue;
	}

	/**
	* Sets new value of numericValue
	* @param int
	*/
	public void setValue(int numericValue) {
		this.numericValue = numericValue;
	}

	/**
	* Returns value of suit
	* @return String
	*/
	public String getSuit() {
		return suit;
	}

	/**
	* Sets new value of suit
	* @param String
	*/
	public void setSuit(String suit) {
		this.suit = suit;
	}


}
