# 棋牌类

- Clarify : 玩家，规则，胜负，积分
- Core object: Hand, Board, Deck/Table, Suit, …
- Use cases: Initialization / Play / Checkout

![image-20220518225144382](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220518225144382.png)

![image-20220525200640314](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220525200640314.png)

- Player.joinGame()：加入一个牌桌
- BlackJack.addPlayer()：牌桌加入玩家 `List<Player> players`
- Player.placeBets()：下注
- BlackJack.dealInitialCard()：给玩家和庄家发牌
- Player/Dealer.insertCard() ---> Hand.insertCard()：添加刚刚发过的牌
- BlackJack.dealNextCard()：从牌堆 `List<Card> cards` 中拿出一个，加入到玩家的手牌
- Player.dealNextCard()：获取下一张牌
  1. 调用 Deck.dealNextCard()
  2. Deck 从牌堆中拿牌
- Player.StopDealing()：停止拿牌
  - Boolean stopDealing 属性：是否停止拿牌
- BlackJack.compareResults()：对比玩家和庄家手牌
- Hand.getValue()：获得手牌大小（如何计算大小算法）



- **Clarify**
  - 玩家
    - Is there a fixed dealer or players take turn to become dealer?
  - 规则
    - What if we run out of cards?
    - Can dealer run out of bets? (输光)
  - 胜负
  - 积分
- **Core Object**
  - ![image-20220525202133880](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220525202133880.png)
- **Use Case**
  - Initialize
    - Join Table
    - Place bet
    - Get initial cards
  - Play
    - Deal
    - Stop dealing
    - Increase bet
  - Win/Lose/Tie
    - Compare score
    - Take/Lose bets

```java
// K.Z
// Black Jack
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

// Player
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

// Hand
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

// Dealer
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

// Card
package DesignBlackJack.core;

public class Card {
    private int cardValue;

    public Card(int cardValue) {
        this.cardValue = cardValue;
    }

    public int getCardValue() {
        return cardValue;
    }

    public void setCardValue(int cardValue) {
        this.cardValue = cardValue;
    }
}

```



```java
// 九章算法
public class BlackJack {
	private List<NormalPlayer> players;
	private Dealer dealer;

	private List<Card> cards;

	public BlackJack() {
		players = new ArrayList<>();
		dealer = new Dealer();
	}

	public void initCards(List<Card> cards) {
		this.cards = cards;
	}

	public void addPlayer(NormalPlayer p) {
		players.add(p);
	}


	public void dealInitialCards() {
		for (NormalPlayer player : players) {
			player.insertCard(dealNextCard());
		}

		dealer.insertCard(dealNextCard());

		for (NormalPlayer player : players) {
			player.insertCard(dealNextCard());
		}

		dealer.insertCard(dealNextCard());
	}

	public Card dealNextCard() {
		Card card = cards.remove(0);
		return card;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void compareResult() {
		for (NormalPlayer p : players) {
			if (dealer.largerThan(p)) {
				dealer.updateBets(p.getCurrentBets());
				p.lose();
			} else {
				dealer.updateBets(-p.getCurrentBets());
				p.win();
			}
		}
	}

	public String print() {
		String s = "";
		for (NormalPlayer player : players) {
			s += "playerid: " + (player.getId() + 1) + " ;" + player.printPlayer();
		}
		return s;
	}
}


class NormalPlayer {
	private BlackJack game;
	private int id;
	private Hand hand;
	private int totalBets;
	private int bets;
	private boolean stopDealing;

	public NormalPlayer(int id, int bets) {
		this.id = id;
		hand = new Hand();
		totalBets = 1000;
		try{
		    placeBets(bets);
		}catch(Exception e){
		    e.printStackTrace();
		}
		stopDealing = false;
	}

	public int getId() {
		return this.id;
	}

	public void insertCard(Card card) {
		hand.insertCard(card);
	}

	public int getBestValue() {
		return hand.getBestValue();
	}

	public void stopDealing() {
		stopDealing = true;
	}

	public void joinGame(BlackJack game) {
		this.game = game;
		game.addPlayer(this);
	}

	public void dealNextCard() {
		insertCard(game.dealNextCard());
	}

	public void placeBets(int amount) throws Exception {
		if (totalBets < amount) {
			throw new Exception("No enough money.");
		}
		bets = amount;
		totalBets -= bets;
	}

	public int getCurrentBets() {
		return bets;
	}

	public String printPlayer() {
		return hand.printHand() + ", current bets: " + bets + ", total bets: " + totalBets + "\n";
	}

	public void win() {
		totalBets += (bets * 2);
		bets = 0;
	}

	public void lose() {
		bets = 0;
	}
}


class Hand {
	private List<Card> cards;

	public Hand() {
		cards = new ArrayList<>();
	}

	// Get a list of possible results from hand
	private List<Integer> getPossibleValues() {
		List<Integer> results = new ArrayList<>();

		int aceCount = 0;
		int resultWithoutAce = 0;
		for (Card card : cards) {
			if (card.getValue() == 1) {
				aceCount++;
			} 
            else if (card.getValue() == 11 || card.getValue() == 12 || card.getValue() == 13) {
				resultWithoutAce += 10;
			} 
            else
				resultWithoutAce += card.getValue();
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

	public void insertCard(Card card) {
		cards.add(card);
	}

	public String printHand() {
		String res = "Cards: ";
		for (int i = 0; i < cards.size(); i++){
			res += (cards.get(i).getValue());
			if(i != cards.size() - 1){
				res+=" , ";
			}
			else res+=';';
		}

		res += " Current best value is: " + getBestValue();
		return res;
	}
}


class Card {
	private int value;

	public Card(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}


class Dealer {
	private BlackJack game;
	private Hand hand;
	private int bets;

	public Dealer() {
		hand = new Hand();
		bets = 10000;
	}

	public void insertCard(Card card) {
		hand.insertCard(card);
	}

	public boolean largerThan(NormalPlayer p) {
		return hand.getBestValue() >= p.getBestValue();
	}

	public void updateBets(int amount) {
		bets += amount;
	}

	public void setGame(BlackJack game) {
		this.game = game;
	}

	public void dealNextCard() {
		insertCard(game.dealNextCard());
	}

	public String printDealer() {
		return "Dealer " + hand.printHand() + ", total bets: " + bets + "\n";
	}
}
```

