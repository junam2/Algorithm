package permuANDcomb;

import java.util.LinkedList;

public class Main {
	static int[] arr = {1,2,3,4,5};
	public static void main(String[] args) {
		LinkedList<Integer> perArr = new LinkedList<Integer>(); 
		rePermutation(4,10,perArr);
		
//		int[] comb = {0,0,0,0,1};
//		
//		do {
//			for(int i=0; i<comb.length; i++) {
//				if(comb[i] == 0) {
//					System.out.print(arr[i] + " ");
//				}
//			}
//			
//			System.out.println();
//		} while(next_permutation(comb));
	}
	
	private static void rePermutation(int n, int r, LinkedList<Integer> rePerArr) {
        if(rePerArr.size() == r){
            for(int i : rePerArr){
                System.out.print(i+" ");
            }
            System.out.println();
            return;
        }
         
        for(int i=0; i<n; i++){  
            rePerArr.add(i);
            rePermutation(n, r, rePerArr);
            rePerArr.removeLast();
 
        }
         
         
    }
	
	public static boolean next_permutation(int[] arr) {
		int i = arr.length - 1;
		
		while(i>0 && arr[i-1] >= arr[i]) {
			i--;
		}
		
		if(i<=0) {
			return false;
		}
		
		int j = arr.length - 1;
		
		while(arr[i-1] >= arr[j]) {
			j--;
		}
		
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		
		j = arr.length-1;
		
		while(i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}
		
		
		
		return true;
	}
}
