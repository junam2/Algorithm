package q1475;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] n1 = new int[10];
		int temp = n;
		

		
		while(temp!=0) {
			int k = temp%10;
			if(k==6 || k==9) {
				n1[9]++;
			} else {
				n1[k]++;
			}
			temp /= 10;
		}
		
		int max_to_eight = 0;
		
		for(int i=0; i<n1.length-1; i++) {
			if(max_to_eight <= n1[i]) {
				max_to_eight = n1[i];
			}
		}
		
		int six_or_nine = 0;
		
		if(n1[9]%2==0) {
			six_or_nine = n1[9]/2;
		} else {
			six_or_nine = n1[9]/2 + 1;
		}
		
		int real_max = (int) Math.max(max_to_eight, six_or_nine);
		if(n==0) real_max = 1;
		System.out.println(real_max);
		
	}

}
