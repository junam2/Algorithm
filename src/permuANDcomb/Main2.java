package permuANDcomb;

import java.util.LinkedList;

public class Main2 {

	public static void main(String[] args) {
		//�ߺ� ���� (���� �������, �ڱ� �ڽ� ����)
		LinkedList<Integer> perArr = new LinkedList<Integer>();
		System.out.println("�ߺ� ����");
		rePermutation(3,3, perArr);
		System.out.println();
		
		
		//�ߺ� ���� (���� �������, �ڱ� �ڽ� ����)
		int[] comb = new int[3];
		System.out.println("�ߺ� ����");
		reCombination(comb, comb.length, 3, 0, 0);
	}
	
	static void rePermutation(int n, int r, LinkedList<Integer> reperArr) {
		if(reperArr.size() == r) {
			for(int i=0; i<reperArr.size(); i++) {
				System.out.print(reperArr.get(i) + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i=0; i<n; i++) {
			reperArr.add(i);
			rePermutation(n,r,reperArr);
			reperArr.removeLast();
		}
	}
	
	static void reCombination(int[] comb, int n, int r, int index, int target) {
		if(r == 0) {
			for(int i=0; i<index; i++) {
				System.out.print(comb[i] + " ");
			}
			System.out.println();
			return;
		}
		
		if(target == n) return;
		
		comb[index] = target;
		reCombination(comb, n, r-1, index+1, target);
		reCombination(comb, n, r, index, target+1);
	}
}
