package ca.ubc.ece.cpen221.mp3.graph;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

import java.util.*;

public class AdjacencyListGraph implements Graph {
	// TODO: Implement this class

	// maps vertex to downstream and upstream vertices
	HashMap<Vertex, ArrayList<List<Vertex>>> internal_map;

	public AdjacencyListGraph() {
		internal_map = new HashMap<Vertex, ArrayList<List<Vertex>>>();
	}

	/**
	 * Adds a vertex to the graph.
	 *
	 * Precondition: v is not already a vertex in the graph
	 */
	@Override
	public void addVertex(Vertex v) {
		ArrayList<List<Vertex>> init = new ArrayList<List<Vertex>>();
		init.add(new ArrayList<>());
		init.add(new ArrayList<>());
		internal_map.put(v, init);

	}

	/**
	 * Adds an edge from v1 to v2.
	 *
	 * Precondition: v1 and v2 are vertices in the graph
	 */

	public void addEdge(Vertex v1, Vertex v2) {
		// downstream
		internal_map.get(v1).get(0).add(v2);
		// upstream
		internal_map.get(v2).get(1).add(v1);
			
	}

	/**
	 * Check if there is an edge from v1 to v2.
	 *
	 * Precondition: v1 and v2 are vertices in the graph Postcondition: return
	 * true iff an edge from v1 connects to v2
	 */
	@Overide
	public boolean edgeExists(Vertex v1, Vertex v2) {
		return internal_map.get(v1).get(0).contains(v2);
		
	}

	/**
	 * Get an array containing all downstream vertices adjacent to v.
	 *
	 * Precondition: v is a vertex in the graph
	 * 
	 * Postcondition: returns a list containing each vertex w such that there is
	 * an edge from v to w. The size of the list must be as small as possible
	 * (No trailing null elements). This method should return a list of size 0
	 * iff v has no downstream neighbors.
	 */

	public List<Vertex> getDownstreamNeighbors(Vertex v) {
		return new ArrayList<Vertex>(internal_map.get(v).get(0));
	}

	/**
	 * Get an array containing all upstream vertices adjacent to v.
	 *
	 * Precondition: v is a vertex in the graph
	 * 
	 * Postcondition: returns a list containing each vertex u such that there is
	 * an edge from u to v. The size of the list must be as small as possible
	 * (No trailing null elements). This method should return a list of size 0
	 * iff v has no upstream neighbors.
	 */

	public List<Vertex> getUpstreamNeighbors(Vertex v) {
		return new ArrayList<Vertex>(internal_map.get(v).get(1));
	}

	/**
	 * Get all vertices in the graph.
	 *
	 * Postcondition: returns a list containing all vertices in the graph. This
	 * method should return a list of size 0 iff the graph has no vertices.
	 */

	public List<Vertex> getVertices() {
		return new ArrayList<Vertex>(internal_map.keySet());
	}

}