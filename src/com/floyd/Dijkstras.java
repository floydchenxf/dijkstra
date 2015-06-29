package com.floyd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dijkstras {

	public static void main(String[] args) {
		Graph g = new Graph();
		g.addVertex('H', Arrays.asList(new Vertex('E', 1), new Vertex('F', 3)));
		g.addVertex('E', Arrays.asList(new Vertex('H', 1)));
		g.addVertex('G', Arrays.asList(new Vertex('C', 4), new Vertex('F', 9)));
		g.addVertex('F', Arrays.asList(new Vertex('B', 2), new Vertex('C', 1), new Vertex('D', 2), new Vertex('G', 9), new Vertex('H', 3)));
		g.addVertex('D', Arrays.asList(new Vertex('F', 2), new Vertex('B', 2), new Vertex('A', 4)));
		g.addVertex('C', Arrays.asList(new Vertex('A', 9), new Vertex('F', 1), new Vertex('G', 4)));
		g.addVertex('B', Arrays.asList(new Vertex('A', 5), new Vertex('F', 2), new Vertex('D', 2)));
		g.addVertex('A', Arrays.asList(new Vertex('B', 5), new Vertex('C', 9), new Vertex('D', 4)));
		List<Character> paths = g.getShortestPath('A', 'F');
		List<Character> dd = new ArrayList<Character>();
		for (int i = paths.size() - 1; i >= 0; i--) {
			dd.add(paths.get(i));
		}
		System.out.println(dd);
	}
}