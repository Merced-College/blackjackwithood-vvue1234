// Nue Lopez, Veejay Vue, Jacob Butler, ...
// 1/28/2025
// Card Game With OOD

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CardGame {
	//Initializes array...
	private static ArrayList<Card> deckOfCards = new ArrayList<Card>();
	private static ArrayList<Card> playerCards = new ArrayList<Card>();
	//                                                                                                                                          private static ArrayList<Card> playerCards2 = new ArrayList<Card>();

	public static void main(String[] args) {
		Scanner input = null; //Input hasn't been used yet.

		//Added how many cards the player wants.
		Scanner scnr = new Scanner(System.in);
		System.out.println("How many cards do you want? ");
		int numOfCards = scnr.nextInt();
		Players player1 = new Players();
		
		System.out.println("How many players want to play? ");
		int numPlayers = scnr.nextInt();
		player1.setPlayerCount(numPlayers);

		//This checks if there is an error, it will accept it even though there is an error. 
		try {//Reads in from another text file the cards. It is so that way it doesn't take all the space in the main.
			input = new Scanner(new File("cards.txt"));
		} catch (FileNotFoundException e) {
			// error
			System.out.println("error");
			e.printStackTrace();
		}

		while(input.hasNext()) {
			String[] fields  = input.nextLine().split(",");
			//	public Card(String cardSuit, String cardName, int cardValue, String cardPicture) {
			Card newCard = new Card(fields[0], fields[1].trim(),
					Integer.parseInt(fields[2].trim()), fields[3]);
			deckOfCards.add(newCard);	
		}

		shuffle(); //Shuffles the cards when called for.
		//for(Card c: deckOfCards)
			//System.out.println(c);

		//deal the player x amount of cards
		for(int i = 0; i < numOfCards; i++) {
			//playerCards.get(i).getSuit(); //Gets suit
			playerCards.add(deckOfCards.remove(i));
		}
		
		//Prints out the player's cards.
		System.out.println("players cards");
		for(Card c: playerCards)
			System.out.println(c);
		
		//Line says if there are any pairs in the decks dealt.
		System.out.println("pairs is " + checkFor2Kind());
	}//end main

	public static void shuffle() {
		//shuffling the cards by deleting and reinserting
		for (int i = 0; i < deckOfCards.size(); i++) {
			int index = (int) (Math.random()*deckOfCards.size());
			Card c = deckOfCards.remove(index);
			//System.out.println("c is " + c + ", index is " + index);
			deckOfCards.add(c);
		}
	}

	//check for 2 of a kind in the players hand
	public static boolean checkFor2Kind() {
		int count = 0;
		for(int i = 0; i < playerCards.size() - 1; i++) {
			Card current = playerCards.get(i);
			Card next = playerCards.get(i+1);

			//Going to add a three of a kind comparison...?
			/*
			Card third = playerCards.get(i+2);
			*/
			
			for(int j = i+1; j < playerCards.size(); j++) {
				next = playerCards.get(j);
				//System.out.println(" comparing " + current); //comment out afterwards
				//System.out.println(" to " + next); //comment out afterwards
				if(current.equals(next))
					count++;
			}//end of inner for
			if(count == 1)
				return true;
		}//end outer for
		return false;
	}
	
}//end class