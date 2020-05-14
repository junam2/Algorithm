package swea_17825;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
	static int[] dice = new int[10];
	static int max_score = Integer.MIN_VALUE;
	static horse[] horse = new horse[4];
	static ArrayList<int[]> course = new ArrayList<int[]>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		LinkedList<Integer> perArr = new LinkedList<Integer>();
		LinkedList<Integer> test = new LinkedList<Integer>();
		
		test.add(1);
		test.add(1); test.add(1); test.add(2); test.add(3); test.add(3); 
		test.add(3); test.add(3); test.add(3); test.add(1); 
		
		String[] str = br.readLine().split(" ");
		
		int[] course1 = new int[22];
		
		for(int i=0; i<course1.length; i++) {
			course1[i] = 2*i;
		}
		course1[21] = -1;
		
		int[] course2 = {10,13,16,19,25,30,35,40, -1};
		int[] course3 = {20,22,24,25,30,35,40, -1};
		int[] course4 = {30,28,27,26,25,30,35,40, -1};
		
		course.add(course1);
		course.add(course2);
		course.add(course3);
		course.add(course4);
		
		
		for(int i=0; i<dice.length; i++) {
			dice[i] = Integer.parseInt(str[i]);
		}
		
		rePermutation(4,10,perArr);
		//gameStart(test, dice);

		
		System.out.println(max_score);
		
	}
	
	static void gameStart(LinkedList<Integer> arr, int[] dice) {
		int score = 0;
		boolean flag = true;
		
		for(int i=0; i<horse.length; i++) {
			horse[i] = new horse();
		}
		
		loop: for(int i=0; i<arr.size(); i++) {
			horse tmp_horse = horse[arr.get(i)];
			
			if(tmp_horse.gameFlag) {
				tmp_horse.index += dice[i];
				
				if(course.get(tmp_horse.course).length > tmp_horse.index) {
					tmp_horse.location = course.get(tmp_horse.course)[tmp_horse.index];					
					
					if(tmp_horse.location == 10 && tmp_horse.course == 0) {
						tmp_horse.course = 1;
						tmp_horse.index = 0;
					} else if(tmp_horse.location == 20  && tmp_horse.course == 0) {
						tmp_horse.course = 2;
						tmp_horse.index = 0;
					} else if(tmp_horse.location == 30  && tmp_horse.course == 0) {
						tmp_horse.course = 3;
						tmp_horse.index = 0;
					}
					
					for(int j=0; j<horse.length; j++) {
						if(arr.get(i) != j && tmp_horse.location == horse[j].location && tmp_horse.course == horse[j].course) {
							flag = false;
							break loop;
						}
						
						if(arr.get(i) != j && tmp_horse.location == horse[j].location && tmp_horse.course != horse[j].course) {
							if(tmp_horse.location == 25 || tmp_horse.location == 40 || tmp_horse.location == 30 || tmp_horse.location == 35 ) {
								if(tmp_horse.location == 30) {
									if((tmp_horse.course == 3 && tmp_horse.index ==0) || (horse[j].course == 3 && horse[j].index ==0)) {
										
									} else {
										flag = false;
										break loop;
									}
								} else {
									flag = false;
									break loop;
								}
							}
						}	
					}
					
					tmp_horse.score += tmp_horse.location;
					
				} else {
					tmp_horse.gameFlag = false;
					tmp_horse.location = -1;
					tmp_horse.course = -1;
				}
				
			}
		}
		
		if(flag) {
			for(int i=0; i<horse.length; i++) {
				score += horse[i].score;
			}
			
			if(max_score < score) {
				max_score = score;
			}
		}
		
	}
	
	static void rePermutation(int n, int r, LinkedList<Integer> rePerArr) {
        if(rePerArr.size() == r){
            
        	gameStart(rePerArr, dice);
        	
            return;
        }
         
        for(int i=0; i<n; i++){  
            rePerArr.add(i);
            rePermutation(n, r, rePerArr);
            rePerArr.removeLast();
 
        }
         
         
    }
}

class horse {
	int location = 0;
	int score = 0;
	int course = 0;
	int index = 0;
	boolean gameFlag = true;
}
