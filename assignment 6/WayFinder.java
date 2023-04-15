/* 
 * CSC 225 - Assignment 6
 * Name: Hal Nguyen
 * Student number: V00972322
 */

import java.io.*;
import java.util.*;


public class WayFinder {
	static int shortestPath(char[][] map) {
		// Locating origin
		Node origin = new Node(-1, -1, 0);

		boolean found = false;
		for (int y = 0; y < map.length; ++y) {
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
		final Direction[] DIRECTIONS = {
			new Direction(-1, 0), // left
			new Direction(1, 0), // right
			new Direction(0, -1), // up
			new Direction(0, 1), // down
		};

		PathMap path = new PathMap(map.length);
		Queue<Node> queue = new LinkedList<>();
		queue.offer(origin);
		path.walk(origin);

		while (!queue.isEmpty()) {
			Node currentNode = queue.poll();

			for (Direction direction : DIRECTIONS) {
				Node node = currentNode.move(direction);

				if (node.outOfBound(map.length)) continue;

				boolean pathOK = map[node.y][node.x] != '#' && !path.at(node);
				if (!pathOK) continue;

				queue.offer(node);
				path.walk(node);

				if (map[node.y][node.x] == 'B') return node.distance;
			}
		}

		return Integer.MAX_VALUE;
	}

	static private class Node {
		int x;
		int y;
		int distance;

		private Node(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}

		private Node move(Direction dir) {
			return new Node(
				this.x + dir.x, 
				this.y + dir.y, 
				this.distance + dir.distance
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

	static private class Direction extends Node {
		private Direction(int x, int y) {
			super(x, y, 1);
		}
	}

	static private class PathMap {
		boolean[][] path;
		private PathMap(int size) {
			this.path = new boolean[size][size];
		}

		private void walk(Node node) {
			this.path[node.y][node.x] = true;
		}

		private boolean at(Node node) {
			return this.path[node.y][node.x];
		}
	}

	public static void main(String[] args) {
		Scanner s;
		if (args.length > 0) {
			try {
				s = new Scanner(new File(args[0]));
			} catch (java.io.FileNotFoundException e) {
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		} else {
			s = new Scanner(System.in);
			System.out.printf("Reading input values from stdin.\n");
		}

		int n = s.nextInt();
		char[][] map = new char[n][n];
		for (int i = 0; i < n; i++) {
			String temp = s.next();
			for (int j = 0; j < n; j++) {
				map[i][j] = temp.charAt(j);
			}
		}	

		int distance = shortestPath(map);

		if (distance < Integer.MAX_VALUE) System.out.println(distance);
		else System.out.println("IMPOSSIBLE");
	}
}
