package level2_다리는지나는트럭;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		int[] truck_weights = {7,4,5,6};
		Solution a = new Solution();
		System.out.println(a.solution(2, 10, truck_weights));

	}

}

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int sum = 0;
        int i = 1;
        Queue<Integer> q = new LinkedList<Integer>(); 
        ArrayList<Integer> arr = new ArrayList<Integer>();
        q.add(truck_weights[0]);
        sum += truck_weights[0];
        answer++;
        int[] car_d = new int[truck_weights.length];
        car_d[0]++;
        while(true) {
            
            if(i<truck_weights.length && sum + truck_weights[i] <= weight) {
                q.add(truck_weights[i]);
                sum += truck_weights[i];
          
                i++;
            }
            
            answer++;
            for(int j=0; j<i; j++) {
            	if(car_d[j] != -1) {
            		car_d[j]++;
            	}
            }
            
            for(int j=0; j<i; j++) {
            	if(car_d[j] == bridge_length) {
            		int complete = q.poll();
            		arr.add(complete);
            		sum -= complete;
            		car_d[j] = -1;
            	}
            }
            
            if(arr.size() == truck_weights.length) {
            	answer++;
                break;
            }
            
        }
        
        return answer;
    }
}