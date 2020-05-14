package swea_q17140;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

public class Main {
	static int r,c,k;
	static int[][] mapmap;
	static int time = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String[] str = br.readLine().split(" ");
		r = Integer.parseInt(str[0]) - 1;
		c = Integer.parseInt(str[1]) - 1;
		k = Integer.parseInt(str[2]);
		
		mapmap = new int[3][3];
		
		for(int i=0; i<3; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<3; j++) {
				mapmap[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		while(true) {
			int n = mapmap.length;
			int m = mapmap[0].length;
			
			if(n>r && m>c) {
				if(mapmap[r][c] == k) {
					break;
				}
			}
			
			if(time > 100) {
				time = -1;
				break;
			}
			
			start();
			time++;
		}
		
		System.out.println(time);
	}
	
	public static void start() {
		// n: 행의 길이, m: 열의 길이
		int n = mapmap.length;
		int m = mapmap[0].length;
		
		cal(n,m);
	}
	
	public static void cal(int a, int b) {
		int n = a;
		int m = b;
		ArrayList<Map<Integer, Integer>> arr = new ArrayList<>();
		int maxLength = Integer.MIN_VALUE;
		boolean nflag = true;
		
		if(n<m) {
			int temp = n;
			n = m;
			m = temp;
			nflag = false;
		}
		
		for(int i=0; i<n; i++) {
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			if(nflag) {				
				for(int j=0; j<m; j++) {
					if(mapmap[i][j] != 0) {				
						if(map.containsKey(mapmap[i][j])) {
							map.put(mapmap[i][j], map.get(mapmap[i][j]) + 1);
						} else {
							map.put(mapmap[i][j], 1);
						}
					}
				}			
			} else {
				for(int j=0; j<m; j++) {
					if(mapmap[j][i] != 0) {				
						if(map.containsKey(mapmap[j][i])) {
							map.put(mapmap[j][i], map.get(mapmap[j][i]) + 1);
						} else {
							map.put(mapmap[j][i], 1);
						}
					}
				}	
			}

			List<Map.Entry<Integer, Integer>> list = new LinkedList<>(map.entrySet());
			
			Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
				
				@Override
				public int compare(Entry<Integer, Integer> arg0, Entry<Integer, Integer> arg1) {
					int comparision = arg0.getValue() - arg1.getValue();
					return comparision == 0? arg0.getKey().compareTo(arg1.getKey()):comparision;
				}
			});
			
			Map<Integer, Integer> sortedMap = new LinkedHashMap<>();
			
			for(Iterator<Map.Entry<Integer, Integer>> iter = list.iterator(); iter.hasNext();) {
				Map.Entry<Integer, Integer> entry = iter.next();
				sortedMap.put(entry.getKey(), entry.getValue());
			}
			
			arr.add(sortedMap);
			
			if(maxLength <= sortedMap.size()*2) {
				maxLength = sortedMap.size()*2;
			}
		}
		
		
		int[][] tmp_map;
		
		if(nflag) {
			tmp_map = new int[n][maxLength];
		} else {
			tmp_map = new int[maxLength][n];
		}
		
		for(int i=0; i<arr.size(); i++) {
			Iterator<Integer> it = arr.get(i).keySet().iterator();
			ArrayList<Integer> arr2 = new ArrayList<Integer>();
			
			while(it.hasNext()) {
				int k = it.next();
				arr2.add(k);
				arr2.add(arr.get(i).get(k));
			}
			
			if(nflag) {
				for(int j=0; j<arr2.size(); j++) {
					tmp_map[i][j] = arr2.get(j);
				}
				
				if(arr2.size() != maxLength) {
					for(int j=arr2.size(); j<maxLength; j++) {
						tmp_map[i][j] = 0;
					}
				}		
			} else {
				for(int j=0; j<arr2.size(); j++) {
					tmp_map[j][i] = arr2.get(j);
				}
				
				if(arr2.size() != maxLength) {
					for(int j=arr2.size(); j<maxLength; j++) {
						tmp_map[j][i] = 0;
					}
				}	
			}
			
		}
		
		mapmap = tmp_map;
	}
}
