package q2309;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int[] arr = new int[9];
		ArrayList<Integer> arr2 = new ArrayList<Integer>();
		
		for(int i=0; i<arr.length; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int total = 0;
		
		loop: for(int i=0; i<arr.length-1; i++) {
			for(int j=i+1; j<arr.length; j++) {
				for(int k=0; k<arr.length; k++) {
					if(k == i ||  k== j) {
						continue;
					} else {
						total += arr[k];
					}
				}
				
				if(total == 100) {
					for(int k=0; k<arr.length; k++) {
						if(k == i ||  k== j) {
							continue;
						} else {
							arr2.add(arr[k]);
						}
					}
					break loop;
				} else {
					total = 0;
				}
			}
		}
		
		Collections.sort(arr2);
		
		for(int i=0; i<arr2.size(); i++) {
			System.out.println(arr2.get(i));
		}
	}

}
