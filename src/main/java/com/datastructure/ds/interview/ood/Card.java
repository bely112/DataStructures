package com.datastructure.ds.interview.ood;

import java.util.ArrayList;
import java.util.Random;

abstract class Card {
    private boolean available = true;

    protected int faceValue;
    protected Suit suit;
    public Card(int f, Suit s) {
        faceValue = f;
        suit = s;
    }

    public abstract int value();
    public Suit suit() { return suit; }

    public boolean isAvailable() { return available; }
    public void markAsAvailable() { available = true; }
    public void markAsUnavailable() { available = false; }
}

enum Suit {
    Club(0), Diamond(1), Heart(2), Spade(3);
    private int value;

    private Suit(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }

    public static Suit getSuitFromValue(int value) {
        switch(value) {
            case 0:
                return Suit.Club;
            case 1:
                return Suit.Diamond;
            case 2:
                return Suit.Heart;
            case 3:
                return Suit.Spade;
            default:
                return null;
        }
    }
}

class Deck<T extends Card> {
    private ArrayList<T> cards;
    private int dealtIndex = 0;

    public void setDeckOfCards(ArrayList<T> deckOfCards) {
        cards = deckOfCards;
    }

    public int remainingCards() {
        return cards.size() - dealtIndex;
    }

    public void shuffle() {
        for (int i = 0; i < cards.size(); i++) {
            Random random = new Random();
            int s = cards.size();
            int j = random.nextInt(s - i);

            T card1 = cards.get(i);
            T card2 = cards.get(j);
            cards.set(i, card2);
            cards.set(j, card1);
        }
    }

    public T[] dealHand(int number) {
        if (remainingCards() < number) {
            return null;
        }

        T[] hand = (T[]) new Card[number];
        int count = 0;
        while (count < number) {
            T card = dealCard();
            if (card != null) {
                hand[count] = card;
                count++;
            }
        }

        return hand;
    }

    public T dealCard() {
        if (remainingCards() == 0) {
            return null;
        }

        T card = cards.get(dealtIndex);
        card.markAsUnavailable();
        dealtIndex++;

        return card;
    }

}

class Hand<T extends Card> {
    protected ArrayList<T> cards = new ArrayList<T>();

    public int score() {
        int score = 0;
        for (T card : cards) {
            score += card.value();
        }
        return score;
    }

    public void addCard(T card) {
        cards.add(card);
    }
}

class BlackJackHand extends Hand<BlackJackCard> {
    public int score() {
        ArrayList<Integer> scores = possibleScores();
        int minOver = Integer.MAX_VALUE;
        int maxUnder = Integer.MIN_VALUE;
        for (int score : scores) {
            if (score > 21 && score < minOver) {
                minOver = score;
            } else if (score <= 21 && score > maxUnder) {
                maxUnder = score;
            }
        }
        return maxUnder == Integer.MIN_VALUE ? minOver : maxUnder;
    }

    private ArrayList<Integer> possibleScores() {
        ArrayList<Integer> scores = new ArrayList<Integer>();
        if (cards.size() == 0) {
            return scores;
        }
        for (BlackJackCard card : cards) {
            addCardToScoreList(card, scores);
        }
        return scores;
    }

    private void addCardToScoreList(BlackJackCard card, ArrayList<Integer> scores) {
        if (scores.size() == 0) {
            scores.add(0);
        }
        int length = scores.size();
        for (int i = 0; i < length; i++) {
            int score = scores.get(i);
            scores.set(i, score + card.getMin());
            if (card.getMin() != card.getMax()) {
                scores.add(score + card.getMax());
            }
        }
    }

    public boolean is21() {
        return score() == 21;
    }

    public boolean busted() {
        return score() > 21;
    }

    public boolean isBlackJack() {
        if (cards.size() != 2) {
            return false;
        }
        BlackJackCard card1 = cards.get(0);
        BlackJackCard card2 = cards.get(1);
        return (card1.isAce() && card2.isFaceCard())
                || (card2.isAce() && card1.isFaceCard());
    }
}

class BlackJackCard extends Card {
    public BlackJackCard(int f, Suit s) {
        super(f, s);
    }

    public int value() {
        if (isAce()) {
            return 1;
        } else if (isFaceCard()) {
            return 10;
        } else {
            return faceValue;
        }
    }

    public int getMin() {
        if (isAce()) return 1;
        else return value();
    }

    public int getMax() {
        if (isAce()) return 11;
        else return value();
    }

    public boolean isFaceCard() {
        return faceValue >= 11 && faceValue <= 13;
    }

    public boolean isAce() {
        return faceValue == 1;
    }
}
