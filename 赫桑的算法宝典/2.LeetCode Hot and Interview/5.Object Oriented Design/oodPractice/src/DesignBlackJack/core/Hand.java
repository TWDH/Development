package DesignBlackJack.core;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    // Use Case
    // insert card into hand
    public void insertCard(Card card) {
        cards.add(card);
    }

    // Get a list of possible results from hand
    private List<Integer> getPossibleValues() {
        List<Integer> results = new ArrayList<>();

        int aceCount = 0;
        int resultWithoutAce = 0;
        for (Card card : cards) {
            if (card.getCardValue() == 1) {
                aceCount++;
            }
            else if (card.getCardValue() == 11 || card.getCardValue() == 12 || card.getCardValue() == 13) {
                resultWithoutAce += 10;
            }
            else
                resultWithoutAce += card.getCardValue();
        }

        for (int i = 0; i <= aceCount; i++) {
            int ones = i;
            int elevens = aceCount - i;

            results.add(resultWithoutAce + ones + elevens * 11);
        }

        return results;
    }

    // -1 means went over 21, otherwise means the best value of this hand
    public int getBestValue() {
        List<Integer> results = getPossibleValues();

        int maxUnder = -1;
        for (int result : results) {
            if (result <= 21 && result > maxUnder) {
                maxUnder = result;
            }
        }
        return maxUnder;
    }

    public String printHand() {
        String res = "Cards: ";
        for (int i = 0; i < cards.size(); i++){
            res += (cards.get(i).getCardValue());
            if(i != cards.size() - 1){
                res+=" , ";
            }
            else res+=';';
        }

        res += " Current best value is: " + getBestValue();
        return res;
    }
}
