package lotto;

import java.util.Arrays;

public class Lotto {
	private int[] nums;
	
	public Lotto() {
		nums = new int[6];
		randomNum();
	}
	
	private void randomNum() { //6개 숫자 랜덤할당(1~46), 중복X, 정렬
		
		for(int i=0; i<nums.length ; i++) {
			nums[i] = (int)(Math.random()*46 + 1); //랜덤할당
			for(int j=0 ; j<i ; j++) {  //중복 검사
				if(nums[i] == nums[j]) {
					i--;
					break;
				}
			}
		}
		Arrays.sort(nums); //정렬
	}
	
	public int[] getNums() {
		return nums;
	}
}
