/* 
 * CSC 225 - Assignment 6
 * Name: 
 * Student number:
 */

import java.io.*;
import java.util.*;


public class WayFinder {
	static private class Point {
		int x;
		int y;
		int dist;

		Point(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		private Point move(Point loc) {
			return new Point(
				this.x + loc.x, 
				this.y + loc.y, 
				this.dist + loc.dist
			);
		}

		private void update(int x, int y) {
			this.x = x;
			this.y = y;
		}

		private boolean outOfBound(int size) {
			return this.x < 0 
				|| this.y < 0 
				|| this.x >= size 
				|| this.y >= size;
		}
	}

	static int shortestPath(char[][] map){
		// Locating origin
		Point origin = new Point(-1, -1, 0);

		for (int y = 0; y < map.length; ++y) {
			boolean found = false;
			for (int x = 0; x < map[y].length; ++x) {
			if (map[y][x] == 'A') {
					origin.update(x, y);
					found = true;
					break;
				}
			}
			if (found) break;
		}

		if (origin.x == -1 || origin.y == -1) return Integer.MAX_VALUE;

		// BFS
		Point[] DIRECTIONS = {
			new Point(-1, 0, 1), // left
			new Point(1, 0, 1), // right
			new Point(0, -1, 1), // up
			new Point(0, 1, 1), // down
		};

		int size = map.length;
		boolean[][] seen = new boolean[size][size];

		LinkedList<Integer> paths = new LinkedList<>();

		Queue<Point> queue = new LinkedList<>();
		queue.offer(origin);
		seen[origin.y][origin.x] = true;


		while (!queue.isEmpty()) {
			Point currentNode = queue.poll();

			for (Point direction : DIRECTIONS) {
				Point nextNode = currentNode.move(direction);

				if (nextNode.outOfBound(size)) continue;

				boolean pathBlocked = map[nextNode.y][nextNode.x] == '#';
				if (seen[nextNode.y][nextNode.x] || pathBlocked) continue;

				queue.offer(nextNode);
				seen[nextNode.y][nextNode.x] = true;

				if (map[nextNode.y][nextNode.x] == 'B') {
					return nextNode.dist;
				}
			}

		}
		return Integer.MAX_VALUE;
	}

	public static void main(String[] args) {
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
