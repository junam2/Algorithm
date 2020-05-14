package q1926;

import java.util.*;

public class Q2 {

	public static void main(String[] args) {
		Solution a = new Solution();
		int[] param = {3,2,4,4,2,5,2,5,5};
		
		int[] answer = a.solution(param);
		
		for(int i=0; i<answer.length; i++) {
			System.out.print(answer[i] + " ");
		}
		
		System.out.println(answer.length);
	}

}

class Solution {
	public int[] solution(int[] param) {
		int[] answer = {};
		
		Set<Integer> set = new HashSet<Integer>();
		
		for(int i=0; i<param.length; i++) {
			set.add(param[i]);
		}
		
		answer = new int[set.size()];
		
		List<Integer> arr = new ArrayList<>(set);
		Collections.sort(arr);
		
		ArrayList<Integer> answer2 = new ArrayList<>();
		
		for(int i=0; i<arr.size(); i++) {
			for(int j=0; j<param.length; j++) {
				if(arr.get(i) == param[j]) {
					answer[i]++;
				}
			}
		}
		
		
		for(int i=0; i<answer.length; i++) {
			if(answer[i] != 1) {
				answer2.add(answer[i]);
			}
		}
		
		int[] real_answer = new int[answer2.size()];
		
		for(int i=0; i<real_answer.length; i++) {
			real_answer[i] = answer2.get(i);
		}
		
		if(real_answer.length == 0) {
			int[] no_answer = {-1};
			return no_answer;
		}
		
		return real_answer;
	}
}