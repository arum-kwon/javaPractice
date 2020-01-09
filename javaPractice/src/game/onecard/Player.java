package game.onecard;

public class Player {
	Card[] card;
	int cardIndex;
	public Player() {
		card = new Card[Card.nums * Card.pats];
		cardIndex = 0;
	}
}
