package com.floyd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Graph {
	private final Map<Character, List<Vertex>> vertices;

	public Graph() {
		this.vertices = new LinkedHashMap<Character, List<Vertex>>();
	}

	public void addVertex(Character character, List<Vertex> vertex) {
		this.vertices.put(character, vertex);
	}

	public List<Character> getShortestPath(Character start, Character finish) {
		Map<Character, Integer> distances = new LinkedHashMap<Character, Integer>();
		PriorityQueue<Vertex> nodes = new PriorityQueue<Vertex>();
		Map<Character, Vertex> previous = new LinkedHashMap<Character, Vertex>();
		List<Character> path = new LinkedList<Character>();

		for (Character vertex : vertices.keySet()) {
			if (vertex == start) {
				distances.put(vertex, 0);
				nodes.add(new Vertex(vertex, 0));
			} else {
				distances.put(vertex, Integer.MAX_VALUE);
				nodes.add(new Vertex(vertex, Integer.MAX_VALUE));
			}
			previous.put(vertex, null);
		}

		while (!nodes.isEmpty()) {
			Vertex smallest = nodes.poll();
			if (smallest.getId() == finish) {
				path = new LinkedList<Character>();
				while (previous.get(smallest.getId()) != null) {
					path.add(smallest.getId());
					smallest = previous.get(smallest.getId());
				}

				path.add(start);
				return path;
			}

			if (distances.get(smallest.getId()) == Integer.MAX_VALUE) {
				break;
			}

			for (Vertex neighbor : vertices.get(smallest.getId())) {
				Integer alt = distances.get(smallest.getId()) + neighbor.getDistance();
				if (alt < distances.get(neighbor.getId())) {
					distances.put(neighbor.getId(), alt);
					previous.put(neighbor.getId(), smallest);

					forloop: for (Vertex n : nodes) {
						if (n.getId() == neighbor.getId()) {
							n.setDistance(alt);
							break forloop;
						}
					}

					// 重新排序
					Vertex[] objs = nodes.toArray(new Vertex[nodes.size()]);
					nodes.clear();
					nodes.addAll(Arrays.asList(objs));
					objs = null;
				}
			}
		}

		return new ArrayList<Character>(distances.keySet());
	}
}
