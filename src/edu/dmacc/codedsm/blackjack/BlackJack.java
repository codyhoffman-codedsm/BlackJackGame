package edu.dmacc.codedsm.blackjack;

import java.util.*;

public class BlackJack {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // initialize scanner
        
        int sumOfCards = 0; // intialize sum of the cards
        List<Card> myHand = new ArrayList<>(); // Create my hand (list of cards)
        Map<String, List<Integer>> deck = createDeck(); // create a card deck

        sumOfCards = initialDeal(deck, myHand, sumOfCards);
        printCurrentHand(myHand); // iterate through hand and print it out
        playGame(input, deck, sumOfCards, myHand); // enter while loop that executes until the game is over 
    }

    public static List<Integer> createCards() {
        List<Integer> cards = new ArrayList<>();

        for (int i = 1; i < 14; i++) {
            cards.add(i);
        }

        return cards;
    }

    public static Map<String, List<Integer>> createDeck() {
        Map<String, List<Integer>> deck = new HashMap<>();

        deck.put("Clubs", createCards());
        deck.put("Diamonds", createCards());
        deck.put("Spades", createCards());
        deck.put("Hearts", createCards());

        return deck;
    }

    public static Card dealCardAndRemoveFromDeck(Map<String, List<Integer>> deck) {
        Card card = DeckRandomizer.chooseRandomCards(deck, 1).get(0);
        deck.get(card.suit).remove(card.value);
        return card;
    }

    public static int dealCardAndUpdateSum(Map<String, List<Integer>> deck, int sumOfCards, List<Card> myHand) {
        Card cardDelt = dealCardAndRemoveFromDeck(deck);
        myHand.add(cardDelt);
        sumOfCards += cardDelt.value;
        
        return sumOfCards;
    }

    public static int hit(Map<String, List<Integer>> deck, int sumOfCards, List<Card> myHand) {
        sumOfCards = dealCardAndUpdateSum(deck, sumOfCards, myHand);
        printCurrentHand(myHand);
        
        return sumOfCards;
    }

    public static int initialDeal(Map<String, List<Integer>> deck, List<Card> myHand, int sumOfCards) {
        sumOfCards = dealCardAndUpdateSum(deck, sumOfCards, myHand);
        sumOfCards = dealCardAndUpdateSum(deck, sumOfCards, myHand);
        
        return sumOfCards;
    }

    public static void playGame(Scanner input, Map<String, List<Integer>> deck, int sumOfCards, List<Card> myHand) {
        while(true) {
            System.out.print("\nType '1' to hit or '2' to stand:  ");
            String hitOrStand = input.next();

            if (hitOrStand.equals("1")) {
                sumOfCards = hit(deck, sumOfCards, myHand);
            } else if (hitOrStand.equals("2")) {
                stand(sumOfCards, myHand);
            } else {
                System.out.print("Invalid input\n");
                printCurrentHand(myHand);
            }
        }
    }

    public static void printCurrentHand(List<Card> myHand) {
        System.out.println();

        for(int i = 0; i < myHand.size(); i++) {
            Card card = myHand.get(i);
            System.out.print(card.suit + " - " + card.value);

            if (i+1 < myHand.size()) {
                System.out.print(", ");
            }
        }
    }

    public static void stand(int sumOfCards, List<Card> myHand) {
        printCurrentHand(myHand);
        System.out.println("\nPlayer's hand was " + sumOfCards);
        System.exit(0);
    }

}
