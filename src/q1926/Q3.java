package q1926;

import java.util.*;

public class Q3 {

	public static void main(String[] args) {
		int[] arr1 = {1,2,2,2,2,2,5};
		int[] arr2 = {4,4,4,4,5,6,7};
		Solution2 a = new Solution2(); 
		
		System.out.println(a.solution(arr1, arr2));
		
	}

}

class Solution2 {
	public int solution(int[] arr1, int[] arr2) {
		HashMap<Integer,Integer> a1 = pair(arr1);
		HashMap<Integer, Integer> a2 = pair(arr2);
		
		return fight(a1,a2);
	}
	
	public static HashMap pair(int[] arr) {
		int pair = 0;
		
		HashMap<Integer,Integer> map = new HashMap();
		Set set = new HashSet();
		
		for(int i=0; i<arr.length; i++) {
			set.add(arr[i]);
		}
		
		ArrayList<Integer> set_arr = new ArrayList<Integer>(set);
		int[] arr_value = new int[set_arr.size()];
				
		for(int i=0; i<set_arr.size(); i++) {
			for(int j=0; j<arr.length; j++) {
				if(set_arr.get(i) == arr[j]) {
					arr_value[i]++;
				}
			}
			
			if(arr_value[i] >= 5) {
				arr_value[i] = 4;
			}
		}
		
		for(int i=0; i<set_arr.size(); i++) {
			map.put(set_arr.get(i), arr_value[i]);
		}
		
		Iterator it = map.values().iterator();
		
		ArrayList<Integer> value = new ArrayList<Integer>();
		
		while(it.hasNext()) {
			value.add((Integer) it.next());
		}
		
		int max = Collections.max(value);
		
		
		ArrayList<Integer> max_value_index = new ArrayList<Integer>();
		for(int i=0; i<value.size(); i++) {
			if(value.get(i) == max) {
				max_value_index.add(i);
			}
		}

		ArrayList<Integer> keyset = new ArrayList<Integer>(map.keySet());
		ArrayList<Integer> max_keyset = new ArrayList<Integer>();

		for(int i=0; i<max_value_index.size(); i++) {
			max_keyset.add(keyset.get(max_value_index.get(i)));
		}

		int max_key = Collections.max(max_keyset);
		
		HashMap<Integer, Integer> last = new HashMap();
		last.put(max_key, max);

		
		return last;
	}
	
	public int fight(HashMap map1, HashMap map2) {
		int answer = 0;
		
		ArrayList<Integer> map1_key = new ArrayList<Integer>(map1.keySet());
		ArrayList<Integer> map2_key = new ArrayList<Integer>(map2.keySet());
		
		int map1_value = (int) map1.get(map1_key.get(0));
		int map2_value = (int) map2.get(map2_key.get(0));

		if(map1_value == 1 && map2_value == 1) {
			return 0;
		} else if(map1_value > map2_value) {
			return 1;
		} else if(map1_value < map2_value) {
			return 2;
		} else {
			if(map1_key.get(0) > map2_key.get(0)) {
				return 1;
			} else if(map1_key.get(0) < map2_key.get(0)) {
				return 2;
			} else {
				return 0;
			}
		}
		
	}
	
}
