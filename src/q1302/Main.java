package q1302;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> arr = new ArrayList<String>();
		Set<String> set = new HashSet<String>();
		ArrayList<Integer> count = new ArrayList<Integer>();
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i=0; i<n; i++) {
			String str = br.readLine();
			arr.add(str);
			set.add(str);
		}
		
		Iterator<String> it = set.iterator();
		
		for(int i=0; i<set.size(); i++) {
			int tmp_count = 0;
			String tmp_str = it.next();
			for(int j=0; j<arr.size(); j++) {
				if(tmp_str.equals(arr.get(j))) {
					tmp_count++;
				}
			}
			count.add(tmp_count);
		}
		
		int max = Collections.max(count);
		ArrayList<String> result = new ArrayList<String>();
		result.addAll(set);
		Iterator<String> it2 = set.iterator();
		
		ArrayList<String> real = new ArrayList<String>();
		
		for(int i=0; i<count.size(); i++) {
			if(count.get(i) == max) {
				real.add(it2.next());
			} else {
				it2.next();
			}
		}
		
		Collections.sort(real);
		
		System.out.println(real.get(0));
		
	}

}
