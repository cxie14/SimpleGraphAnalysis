package ca.ubc.ece.cpen221.mp3.graph;

import java.util.*;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.NoPathFoundException;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class Algorithms {

	/**
	 * *********************** Algorithms ****************************
	 * 
	 * Please see the README for the machine problem for a more detailed
	 * specification of the behavior of each method that one should implement.
	 */


	/**
	 * Finds the shortest possible path, measured in number of edges traversed,
	 * between two vertices, a and b, in a graph, and returns this distance. The distance
	 * between a vertex and itself is zero.
	 * Throws NoPathFoundException if there does not exist a path between a and b.
	 * @param graph the graph which contains vertices a and b
	 * @param a the starting vertex contained in the graph
	 * @param b the vertex to be traveled to (end vertex), contained in the 
	 * 	same graph as a
	 * @return the number of edges traversed to get from a to b along the
	 * shortest path
	 * @throws NoPathFoundException if there does not exist a path from a to b
	 */
	public static int shortestDistance(Graph graph, Vertex a, Vertex b) throws NoPathFoundException {
		
		Queue<Vertex> nextVertexQueue = new LinkedList<Vertex>();
		HashSet<Vertex> scheduledSet = new HashSet<Vertex>();

		nextVertexQueue.addAll(graph.getDownstreamNeighbors(a));
		scheduledSet.addAll(graph.getDownstreamNeighbors(a));

		int depth = 1;
		while (!nextVertexQueue.isEmpty()) {
			Queue<Vertex> currentVertexQueue = new LinkedList<Vertex>(nextVertexQueue);
			nextVertexQueue = new LinkedList<>();
			while (!currentVertexQueue.isEmpty()) {
				Vertex currentRoot = currentVertexQueue.poll();
				if (currentRoot.equals(b))
					return depth;
				else {
					for (Vertex each_child : graph.getDownstreamNeighbors(currentRoot)) {
						if (!scheduledSet.contains(each_child)) {
							nextVertexQueue.add(each_child);
							scheduledSet.add(each_child);
						}
					}
				}
			}
			depth++;
		}
		throw new NoPathFoundException();
	}

	/**
	 * Finds the common upstream vertices such that for each upstream vertex v,
	 * a and b are both one edge up from a. Returns an empty list if none exist.
	 * 
	 * @param graph
	 *            non-empty graph representation
	 * @param a
	 *            Vertex contained within the graph
	 * @param b
	 *            Vertex contained within the graph
	 * @return A list of vertices that are the common upstream vertices of a and
	 *         b
	 */
	public static List<Vertex> commonUpstreamVertices(Graph graph, Vertex a, Vertex b) {
		List<Vertex> aList = graph.getUpstreamNeighbors(a);
		List<Vertex> bList = graph.getUpstreamNeighbors(b);
		List<Vertex> commonUpStreamList = new LinkedList<Vertex>();

		for (Vertex aVertex : aList) {
			for (Vertex bVertex : bList) {
				if (aVertex.equals(bVertex)) {
					commonUpStreamList.add(aVertex);
				}
			}
		}
		return commonUpStreamList;
	}

	/**
	 * Finds the common downstream vertices such that for each downstream vertex
	 * v, a and b are both one edge down from a. Returns an empty list if none
	 * exist.
	 * 
	 * @param graph
	 *            non-empty graph representation
	 * @param a
	 *            Vertex contained within the graph
	 * @param b
	 *            Vertex contained within the graph
	 * @return A list of vertices that are the common downstream vertices of a
	 *         and b
	 */
	public static List<Vertex> commonDownstreamVertices(Graph graph, Vertex a, Vertex b) {
		List<Vertex> aList = graph.getDownstreamNeighbors(a);
		List<Vertex> bList = graph.getDownstreamNeighbors(b);
		List<Vertex> commonDownStreamList = new LinkedList<Vertex>();

		for (Vertex aVertex : aList) {
			for (Vertex bVertex : bList) {
				if (aVertex.equals(bVertex)) {
					commonDownStreamList.add(aVertex);
				}
			}
		}
		return commonDownStreamList;
	}

	/**
	 * returns a set of lists that represent all possible
	 * breadth-first traversals of the graph. Each traversal 
	 * is always the shortest possible way to traverse the graph 
	 * from a starting index. The number of lists in the set
	 * represents the possible number of starting vertices.
	 * @param graph the graph to be traversed
	 * @return Set<List<Vertex>> the set of lists of all possible
	 * ways to traverse the graph
	 */
	public static Set<List<Vertex>> breadthFirstSearch(Graph graph) {
		// TODO: Representation safety required
		Set<List<Vertex>> result = new HashSet<List<Vertex>>();
		for (Vertex a : graph.getVertices()) {

			Queue<Vertex> nextVertexQueue = new LinkedList<Vertex>();
			HashSet<Vertex> scheduledSet = new HashSet<Vertex>();

			// initialize the first root queue
			nextVertexQueue.addAll(graph.getDownstreamNeighbors(a));
			scheduledSet.addAll(graph.getDownstreamNeighbors(a));

			// one traversal of the graph starting at a vertex a
			List<Vertex> traversal = new LinkedList<Vertex>();
			traversal.add(a);
			// loop until run out of new vertexes to visit
				while (!nextVertexQueue.isEmpty()) {

					// add the current vertex to the traversal
					Vertex currentRoot = nextVertexQueue.poll();
					traversal.add(currentRoot);

					// add children if they aren't already scheduled
					for (Vertex each_child : graph.getDownstreamNeighbors(currentRoot)) {
						if (!scheduledSet.contains(each_child)) {
							nextVertexQueue.add(each_child);
							scheduledSet.add(each_child);
						}
					}
				}
			
			result.add(traversal);
		}
		return result;
	}

	/**
	 * traverses the graph using a depth first search algorithm. Returns a set
	 * of lists that represent all possible ways to traverse the graph. Each
	 * list contains the traversal of the vertices in the graph in the order
	 * that they were visited starting from a start index. The number of lists
	 * in the set represents all possible starting vertices in the graph.
	 * 
	 * @param graph
	 *            the graph to be traversed
	 * @return Set<List<Vertex>> a set of the lists of all possible ways to
	 *         traverse the graph.
	 */
	public static Set<List<Vertex>> depthFirstSearch(Graph graph) {
		Set<List<Vertex>> result = new HashSet<List<Vertex>>();
		for (Vertex primaryRoot : graph.getVertices()) {
			Stack<Vertex> vertexStack = new Stack<Vertex>();
			HashSet<Vertex> scheduledSet = new HashSet<Vertex>();
			List<Vertex> traversal = new LinkedList<Vertex>();

			vertexStack.add(primaryRoot);
			scheduledSet.add(primaryRoot);
			traversal.add(primaryRoot);

			while (!vertexStack.isEmpty()) {
				boolean noChildLeft = true;
				for (Vertex each_childRoot : graph.getDownstreamNeighbors(vertexStack.peek())) {
					if (!scheduledSet.contains(each_childRoot)) {
						scheduledSet.add(each_childRoot);
						vertexStack.add(each_childRoot);
						traversal.add(each_childRoot);
						noChildLeft = false;
						break;
					}
				}
				if (noChildLeft) {
					vertexStack.pop();
				}
			}
			result.add(traversal);
		}
		return result;
	}

}
