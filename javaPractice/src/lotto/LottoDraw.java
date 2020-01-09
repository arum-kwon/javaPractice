package lotto;

public class LottoDraw {
	private Lotto drawNum;
	private int bonus;

	public LottoDraw() {
		drawNum = new Lotto();
		
		boolean b= false;
		do {
			bonus = (int)(Math.random()*46+1);
			b= false;
			int[] nums = drawNum.getNums();
			for(int i=0; i<nums.length ;i++) {
				if(bonus == nums[i]) {
					b=true;
				}
			}
		}while(b);
		//for(int i=0; i<1 ; i++){
		// ....i-- .... }
	}
	
	public void printDrawNums() {
		System.out.print("NUM : ");
		for(int n : drawNum.getNums()) {
			System.out.print(n + " ");
		}
		System.out.println("| BONUS : " + bonus);
	}

	public Lotto getDrawNum() {
		return drawNum;
	}

	public int getBonus() {
		return bonus;
	}
	
}
