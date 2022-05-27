package DesignBlackJack.core;

public class Player {

    private int id;

    private BlackJack game;

    private Hand hand;

    private int totalBets;

    private int bets;

    private boolean stopDealing;

    public Player(int id, int bets) {
        this.id = id;
        this.hand = new Hand();
        this.totalBets = 1000;
        this.stopDealing = false;

        // bet
        try {
            placeBets(bets);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Use Case
    // join game
    public void joinGame(BlackJack game) {
        this.game = game;
        game.addPlayer(this);
    }

    // place bets
    public void placeBets(int amount) throws Exception {
        if (totalBets < amount) {
            throw new Exception("No enough money");
        }

        bets = amount;
        totalBets -= bets;
    }

    // deal next card
    public void dealNextCard() {
        // get card from game
        insertCard(game.dealNextCard());
    }

    // insert card int hand
    public void insertCard(Card card) {
        hand.insertCard(card);
    }

    // get best value
    public int getBestValue() {
        return hand.getBestValue();
    }

    // stop dealing
    public void stopDealing() {
        stopDealing = true;
    }

    // win/lose
    public void win() {
        totalBets += (bets * 2);
        bets = 0;
    }

    public void lose() {
        bets = 0;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getTotalBets() {
        return totalBets;
    }

    public void setTotalBets(int totalBets) {
        this.totalBets = totalBets;
    }

    public int getBets() {
        return bets;
    }

    public void setBets(int bets) {
        this.bets = bets;
    }

    public boolean isStopDealing() {
        return stopDealing;
    }

    public void setStopDealing(boolean stopDealing) {
        this.stopDealing = stopDealing;
    }
}
