/* 
 * CSC 225 - Assignment 6
 * Name: 
 * Student number:
 */

import java.io.*;
import java.util.*;


public class WayFinder {
	
	
    static int shortestPath(char[][] map){
		for (char[] i : map) {
			System.out.println(i);
		}
		for (int i = 0; i < map.length; ++i) {
			for (int j = 0; j < map[i].length; ++j) {
				if (map[i][j] == 'A') {
					System.out.print(i);
					System.out.print(j);
				}
			}
		}
		
		/*
		// Variable for distance of shortest path between A and B.
		int distance = Integer.MAX_VALUE;
		System.out.println(distance);
		
         Your solution goes here.
        */
		 
		 
		return 2;
        
    }
	
    public static void main(String[] args) {
		System.out.println("HEllo");
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Reading input values from stdin.\n");
		}
		
		int n = s.nextInt();
		char[][] map = new char[n][n];
		for (int i = 0; i < n; i++){
			String temp = s.next();
			for (int j = 0; j < n; j++){
				map[i][j] = temp.charAt(j);
			}
		}	
		
		int distance = shortestPath(map);
		
		if (distance < Integer.MAX_VALUE)
			System.out.println(distance);
		else
			System.out.println("IMPOSSIBLE");
    }
}
