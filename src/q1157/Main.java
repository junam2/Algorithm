package q1157;

import java.util.*;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.next().toUpperCase();
		HashMap<Character, Integer> map = new HashMap<>();
		
		for(int i=0; i<str.length(); i++) {
			char tmp = str.charAt(i);
			
			if(map.containsKey(tmp)) {
				map.put(tmp, map.get(tmp) + 1);
			} else {
				map.put(tmp, 1);
			}
		}
		
		List<Map.Entry<Character, Integer>> list = new LinkedList<>();
		list.addAll(map.entrySet());
		
		Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {

			@Override
			public int compare(Entry<Character, Integer> arg0, Entry<Character, Integer> arg1) {
				// TODO Auto-generated method stub
				return arg1.getValue() - arg0.getValue();
			}
		});
		
		
		if(list.size() > 1 && list.get(0).getValue() == list.get(1).getValue()) {
			System.out.println("?");
		} else {
			System.out.println(list.get(0).getKey());
		}
	}

}
