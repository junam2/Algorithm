package q1668;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> arr = new ArrayList<Integer>();
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i=0; i<n; i++) {
			arr.add(Integer.parseInt(br.readLine()));
		}
		
		int count1 = 1;
		int max1 = Integer.MIN_VALUE;
		int count2 = 1;
		int max2 = Integer.MIN_VALUE;
		
		for(int i=0; i<n-1; i++) {
			if(max1 < arr.get(i)) {
				max1 = arr.get(i);
			}
			
			if(max1 >= arr.get(i+1)) {
				continue;
			}
			count1++;
		}
		
		for(int i=n-1; i>0; i--) {
			if(max2 < arr.get(i)) {
				max2 = arr.get(i);
			}
			
			if(max2 >= arr.get(i-1)) {
				continue;
			}
			count2++;
		}
		
		System.out.println(count1 + " " + count2);
	}

}
