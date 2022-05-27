package DesignBlackJack.core;

import java.util.ArrayList;
import java.util.List;

public class BlackJack {
    // current players
    private List<Player> players;
    // dealer
    private Dealer dealer;
    // card library
    private List<Card> cards;

    // constructor
    public BlackJack() {
        players = new ArrayList<>();
        cards = new ArrayList<>();
    }

    // use cases
    public void addPlayer(Player player) {
        players.add(player);
    }

    public void initCards(List<Card> cards) {
        this.cards = cards;
    }

    // deal initial cards (2 times)
    public void dealInitialCards() {
        for (int i = 0; i < 2; i++) {
            // to player
            for (Player player : players) {
                player.insertCard(dealNextCard());
            }

            // to dealer
            dealer.insertCard(dealNextCard());
        }
    }

    // deal next card
    public Card dealNextCard() {
        Card card = cards.remove(0);
        return card;
    }

    // compare result between dealer and player
    public void compareResult() {
        for (Player player : players) {
            if (dealer.largerThan(player)) {
                dealer.updateBets(player.getBets());
                player.lose();
            }
            else{
                dealer.updateBets(-player.getBets());
                player.win();
            }
        }
    }

    // Getter & Setter
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
