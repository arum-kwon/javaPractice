package lotto;

import java.util.Arrays;

public class LottoIssue {
	//�ζ� �ڵ� Ƽ�� �ߺ� 1���� �ִ� 5���� ����
	private Lotto[] tickets;
	public LottoIssue(int money) {
		if(check(money)) {
			int coin = money/1000;
			tickets = new Lotto[coin];
			for(int i=0 ; i<coin ; i++) {
				tickets[i] = new Lotto();
			}
		}
		
	}
	
	private boolean check(int money) {
		if(money >5000) {
			System.out.println("1번에 5게임 가능");
			return false;
		}
		if(money%1000 != 0) {
			System.out.println("한 게임당 1000원");
			return false;
		}
		return true;
	}
		
	public Lotto[] getLottos() {
		return tickets;
	}
	
	public void printTickets() {
		System.out.println("-------------------------");
		for(int i=0 ; i<tickets.length ; i++) {
			int[] nums = tickets[i].getNums();
			for(int j=0; j<nums.length ; j++) {
				System.out.print(String.format("%3d",nums[j]) + " ");
			}
			System.out.println();  
		}
		System.out.println("-------------------------");

	}
	
}
