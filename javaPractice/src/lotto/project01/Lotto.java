package lotto.project01;

import java.util.Arrays;

public class Lotto {
	private int[] nums;
	
	public Lotto() {
		nums = new int[6];
		randomNum();
	}
	
	private void randomNum() { //6�� ���� �����Ҵ�(1~46), �ߺ�X, ����
		
		for(int i=0; i<nums.length ; i++) {
			nums[i] = (int)(Math.random()*46 + 1); //�����Ҵ�
			for(int j=0 ; j<i ; j++) {  //�ߺ� �˻�
				if(nums[i] == nums[j]) {
					i--;
					break;
				}
			}
		}
		Arrays.sort(nums); //����
	}
	
	public int[] getNums() {
		return nums;
	}
}
