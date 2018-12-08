package card.util;

public class Card {
	//Instance variables
	private int number;
	private char face;
	private char suit;
	
	//Card constructor for non-face cards
	public Card(int num, char suit) {
		this.number = num;
		this.suit = suit;
		this.face = ' ';
	}
	
	/* 
	Card constructor for face cards.
	This is called method overloading, where we can make methods with the same name, but with different parameters.
	*/
	public Card(char face, char suit) {
		this.face = face;
		this.suit = suit;
		switch(this.face) {
			case 'A':
				this.number = 1;
				break;
			case 'J':
				this.number = 11;
				break;
			case 'Q':
				this.number = 12;
				break;
			case 'K':
				this.number = 13;
				break;
		}
	}
	
	/* 
	Getter Methods
	This is called encapsulation in which we use getters to get the values of instance variables 
	because other classes cannot directly access the priave variables 
	*/
	public int getNumber() { return this.number; }
	public char getFace() { return this.face; }
	public char getSuit() { return this.suit; }
	
	//Overriding default toString() Method so it has a cutsom way it is returned when printed out in the console.
	public String toString() {
		boolean isFace = false;
		if(this.getFace() != ' ') isFace = true;
		if(isFace) {
			return this.getFace() + " " + this.getSuit();
		}
		return this.getNumber() + " " + this.getSuit();
	}
	
	public String reverse() {
		boolean isFace = false;
		if(this.getFace() != ' ') isFace = true;
		if(isFace) {
			return this.getSuit() + " "+ this.getFace();
		}
		return this.getSuit() + " "+ this.getNumber();
	}
}
