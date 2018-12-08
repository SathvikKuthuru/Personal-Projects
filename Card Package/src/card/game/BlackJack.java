package card.game;

import java.util.Scanner;

import card.util.*;


public class BlackJack {
	static Scanner scan = new Scanner(System.in);
	
	Deck dealer;
	Deck player;
	int total = 0;
	boolean hit;
	
	public BlackJack(int n) {
		this.dealer = new Deck(true);
		this.player = new Deck(false);
		hit = true;
		this.deal(n);
		this.player.printDeck();
	}
	
	public void printOptions() {
		System.out.println("1. Stay/Hit");
		System.out.println("2. Show Hand");
		System.out.println("3. Show Value");
	}
	public void deal(int n) {
		dealer.shuffle();
		dealer.removeCards(player.deck, n);
		this.updateTotal();
	}
	
	public void updateTotal() {
		int n = 0;
		for(Card c : player.deck) n += c.getNumber();
		this.total = n;
 	}
	
	public void choose(String s) {
		if(s.equals("1") || s.equals("1.")) {
			System.out.println("Would You like to stay or hit?");
			String l = scan.next().toLowerCase();
			while(!l.equals("stay") && !l.equals("hit")) {
				System.out.println("That's Not An Option! Please Try Again!");
				l = scan.next();
			}
			if(l.equals("hit")) {
				this.dealer.removeCards(this.player.deck, 1);
				System.out.println("You Drew a: " + this.player.deck.get(player.deck.size()-1));
				this.updateTotal();
			}
			else {
				this.hit = false;
			}
		}
		else if(s.equals("2") || s.equals("2.")) this.player.printDeck();
		else if(s.equals("3") || s.equals("3.")) System.out.println("Your Total is: " + total);
	}
	
	public boolean finished() {
		return (!hit) || (total >= 21);
	}
	
	public String end() {
		if(total == 21) return "Congrats You Got A BlackJack!";
		else if(total > 21) return "You went bust!";
		return "Not Bad! You got a total of: " + total;
	}
	
	public void play() {
		while(!this.finished()) {
			this.printOptions();
			String y = scan.next();
			if(!y.equals("1") && !y.equals("1.") && !y.equals("2") && !y.equals("2.") && !y.equals("3") && !y.equals("3.")) {
				System.out.println("That's Not A Valid Option! Please Try Again");
			}
			else this.choose(y);
		}
		System.out.println(this.end());
	}

}
