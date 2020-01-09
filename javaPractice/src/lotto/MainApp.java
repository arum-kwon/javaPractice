package lotto;

public class MainApp {

	public static void main(String[] args) {
		LottoIssue lot = new LottoIssue(5000);
		lot.printTickets();
		
		LottoDraw draw = new LottoDraw();
		draw.printDrawNums();
		
		LottoCheck check = new LottoCheck();
		check.checkNum(draw, lot);
	}

}
