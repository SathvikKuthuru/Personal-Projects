package card.util;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	//Deck or Hand of cards. Has-a relationship with Card class
	public ArrayList<Card> deck = new ArrayList<Card>();
	public int total;
	
	//Fill the deck with 52 cards if needed
	public void fillDeck() {
		for(int i = 1; i <= 52; i++) {
			char suit;
			if(i <= 13) suit = '♠';
			else if(i <= 26) suit ='♦';
			else if(i <= 39) suit ='♣';
			else suit = '♥';
			
			switch(i % 13) {
			case 1:
				deck.add(new Card('A', suit));
				break;
			case 11:
				deck.add(new Card('J', suit));
				break;
			case 12:
				deck.add(new Card('Q', suit));
				break;
			case 0:
				deck.add(new Card('K', suit));
				break;
			default:
				deck.add(new Card(i%13 , suit));
				break;
			}
		}
	}
	
	//empty constructor, could be added upon if instantce variables are needed
	public Deck(boolean fill) {
		if(fill) this.fillDeck();
	}
	
	//print out the deck like an actual card
	public void printDeck() {
		for(Card c : this.deck) {
			if(c.getNumber() == 10) {
				System.out.print(" ____  ");
			}
			else {
				System.out.print(" ___  ");
			}
		}
		System.out.println();
		for(Card c : this.deck) {
			System.out.print("|" + c + "| ");
		}
		System.out.println();
		for(Card c : this.deck) {
			if(c.getNumber() == 10) {
				System.out.print("|    | ");
			}
			else System.out.print("|   | ");
		}
		System.out.println();
		for(Card c : this.deck) {
			System.out.print("|" + c.reverse() + "| ");
		}
		System.out.println();
		for(Card c : this.deck) {
			if(c.getNumber() == 10) {
				System.out.print(" ‾‾‾‾  ");
			}
			else {
				System.out.print(" ‾‾‾  ");
			}
		}
		System.out.println();
		
	}
	//shuffle the deck, could also be made static	
	public void shuffle() {
		Collections.shuffle(this.deck);
	}
	
	//take Cards from one Deck and put it in another deck
	public void removeCards(ArrayList<Card> d, int n) {
		for(int i = 0; i < Math.min(this.deck.size(), n); i++) {
			d.add(this.deck.remove(0));
		}
	}
	
	//Simply remove cards from deck
	public void removeCards(int n) {
		for(int i = 0; i < Math.min(this.deck.size(), n); i++) {
			this.deck.remove(0);
		}
	}

}
