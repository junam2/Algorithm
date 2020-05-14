package q2657;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int test = Integer.parseInt(br.readLine());
		
		for(int t=0; t<test; t++) {
			String[] str = br.readLine().split(" ");
			int n = Integer.parseInt(str[0]);
			String tmp = str[1];
			
			for(int i=0; i<tmp.length(); i++) {
				char tt = tmp.charAt(i);
				
				for(int j=0; j<n; j++) {
					System.out.print(tt);
				}
			}
			System.out.println();
		}
	}

}
