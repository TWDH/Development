package DesignBlackJack.core;

public class Dealer {
    private BlackJack game;
    private Hand hand;
    private int bets;

    public Dealer() {
        hand = new Hand();
        bets = 10000;
    }

    // use case
    public void dealNextCard() {
        insertCard(game.dealNextCard());
    }
    public void insertCard(Card card) {
        hand.insertCard(card);
    }

    public boolean largerThan(Player player) {
        return hand.getBestValue() >= player.getHand().getBestValue();
    }

    public void updateBets(int amount) {
        bets += amount;
    }

    public BlackJack getGame() {
        return game;
    }

    public void setGame(BlackJack game) {
        this.game = game;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public int getBets() {
        return bets;
    }

    public void setBets(int bets) {
        this.bets = bets;
    }
}
