package lotto;

public class LottoCheck {
	public void checkNum(LottoDraw drawNums, LottoIssue tickets) {
		Lotto drowLotto = drawNums.getDrawNum();
		final int[] drowNums = drowLotto.getNums();
		final int bonus = drawNums.getBonus();
		Lotto[] lottos = tickets.getLottos();
		
		for(int i=0; i<lottos.length ; i++) { //한 줄씩
			int count =0;
			int bonusCount=0;
			int[] lotto = lottos[i].getNums();
			
			for(int j=0; j<lotto.length ; j++) { //한 숫자씩
				System.out.print(String.format("%4d",lotto[j])+" ");
				if(drowNums[0]==lotto[j]) {
					count++;
					System.out.print("o");
				}else if(drowNums[1]==lotto[j]) {
					count++;
					System.out.print("o");
				}else if(drowNums[2]==lotto[j]) {
					count++;
					System.out.print("o");
				}else if(drowNums[3]==lotto[j]) {
					count++;
					System.out.print("o");
				}else if(drowNums[4]==lotto[j]) {
					count++;
					System.out.print("o");
				}else if(drowNums[5]==lotto[j]) {
					count++;
					System.out.print("o");
				}else if(bonus==lotto[j]) {
					bonusCount++;
					System.out.print("b");
				}else {
					System.out.print(" ");
				}
			}
			System.out.print(" 일치:" + count + ", 보너스일치:" + bonusCount);
			switch(count) {
			case 6:
				System.out.println(" 1등!");
				break;
			case 5:
				if(bonusCount == 1) {
					System.out.println(" 2등!");
				}else {
					System.out.println(" 3등!");
				}
			case 4:
				System.out.println(" 4등!");
				break;
			case 3:
				System.out.println(" 5등!");
				break;
			default:
				System.out.println(" 꽝");
				break;
			}
		}//6개 1등 | 5개+보너스 2등 | 5개 3등 | 4개 4등  | 3개 5등
	}
}
