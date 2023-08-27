package dev.lucin.domain;

import java.util.ArrayList;
import java.util.List;

public record Card(Suit suit, String face, int rank) {
    public static Card getNumericCard(Suit suit, int cardNumber)
            throws IllegalStateException {
        if (cardNumber < 2 || cardNumber > 10) {
            throw new IllegalStateException();
        }

        return new Card(suit, String.valueOf(cardNumber), cardNumber - 2);
    }

    public static Card getFaceCard(Suit suit, char abbreviation)
            throws IllegalStateException {
        int charIndex = "JQKA".indexOf(abbreviation);
        if (charIndex == -1) {
            throw new IllegalStateException();
        }

        return new Card(suit, "" + abbreviation, charIndex + 9);
    }

    public static List<Card> getStandardDeck() {
        List<Card> deck = new ArrayList<>(52);
        for (Suit suit : Suit.values()) {
            for (int i = 2; i <= 10; i++) {
                deck.add(getNumericCard(suit, i));
            }
            for (char c : new char[]{'J', 'Q', 'K', 'A'}) {
                deck.add(getFaceCard(suit, c));
            }
        }

        return deck;
    }

    public static void printDeck(List<Card> deck) {
        printDeck(deck, "Current Deck", 4);
    }

    public static void printDeck(List<Card> deck, String description, int rows) {
        System.out.println("_".repeat(100));
        if (description != null) {
            System.out.println(description);
        }

        int cardsInRow = deck.size() / rows;
        for (int i = 0; i < rows; i++) {
            int startIndex = i * cardsInRow;
            int endIndex = startIndex + cardsInRow;
            deck.subList(startIndex, endIndex).forEach(c -> System.out.print(c + " "));
            System.out.println();
        }
    }

    @Override
    public String toString() {
        int index = face.equals("10") ? 2 : 1;
        String faceString = face.substring(0, index);
        return "%s%c(%d)".formatted(faceString, suit.getSuit(), rank);
    }


    public enum Suit {
        CLUB, DIAMOND, HEART, SPADE;

        char getSuit() {
            return switch (this) {
                case CLUB -> 9827;
                case DIAMOND -> 9830;
                case HEART -> 9829;
                case SPADE -> 9824;
            };
        }
    }
}
