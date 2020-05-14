package q1019;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		int finish = sc.nextInt();
		int start = 1;
		int point = 1;
		int[] arr = new int[10];
		
		/*
		    이렇게 하면 시간초과가 당연히 나버린다.
		  for(int i=1; i<=n; i++) {
			int t = i;
			while(t!=0) {
				int a = t%10;
				arr[a]++;
				t /= 10;
			}
		  }
		 */
		
		while(start <= finish) {
			while(finish%10 != 9 && start<=finish) {
				cal(finish, arr, point);
				finish--;
			}
			
			if(start > finish) {
				break;
			}
			
			while(start%10 != 0 && start <= finish) {
				cal(start, arr, point);
				start++;
			}
			
			start /= 10;
			finish /= 10;
			
			for(int i=0; i<arr.length; i++) {
				arr[i] += (finish - start + 1) * point;
			}
			
			point *= 10;
		}
		
		for(int i=0; i<10; i++) {
			System.out.print(arr[i] + " ");
		}
	}
	
	public static void cal(int n, int[] arr, int point) {
		while(n!=0) {
			arr[n%10] += point;
			n = n/10;
		}
	}
}
