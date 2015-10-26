import static org.junit.Assert.*;

import java.util.*;
import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class TestAdjacencyListGraph {

	Vertex v1 = new Vertex("user1");
	Vertex v2 = new Vertex("user2");
	Vertex v3 = new Vertex("1234242");
	Vertex v4 = new Vertex("randomOne");
	Vertex v5 = new Vertex("67fht");
	Vertex v6 = new Vertex("user6");
	Vertex v7 = new Vertex("user7");
	Vertex v8 = new Vertex("me");

	@Test
	public void addVertexTest() {
		Graph testGraph = new AdjacencyListGraph();

		testGraph.addVertex(v1);
		testGraph.addVertex(v2);
		testGraph.addVertex(v3);

		List<Vertex> listOfVertices = new ArrayList<Vertex>();
		listOfVertices.add(v1);
		listOfVertices.add(v2);
		listOfVertices.add(v3);

		assertEquals(listOfVertices, testGraph.getVertices());

	}

	@Test
	public void addEdgeTest() {
		Graph testGraph = new AdjacencyListGraph();

		testGraph.addVertex(v2);
		testGraph.addVertex(v3);
		testGraph.addVertex(v7);

		testGraph.addEdge(v2, v3);

		assertEquals(true, testGraph.edgeExists(v2, v3));
		assertEquals(false, testGraph.edgeExists(v2, v7));

	}

	@Test
	public void getDownstreamNeighborsTest() {
		Graph testGraph = new AdjacencyListGraph();

		testGraph.addVertex(v1);
		testGraph.addVertex(v2);
		testGraph.addVertex(v7);
		testGraph.addVertex(v4);
		testGraph.addVertex(v6);
		testGraph.addVertex(v8);

		testGraph.addEdge(v1, v2);
		testGraph.addEdge(v2, v7);
		testGraph.addEdge(v2, v4);
		testGraph.addEdge(v2, v6);
		testGraph.addEdge(v6, v8);

		List<Vertex> downStreamNeighborsList1 = new ArrayList<Vertex>();

		downStreamNeighborsList1.add(v7);
		downStreamNeighborsList1.add(v4);
		downStreamNeighborsList1.add(v6);

		assertEquals(downStreamNeighborsList1, testGraph.getDownstreamNeighbors(v2));

		// test if there are no downstream neighbors
		List<Vertex> downStreamNeighborsList2 = new ArrayList<Vertex>(0);

		assertEquals(downStreamNeighborsList2, testGraph.getDownstreamNeighbors(v4));
	}

	@Test
	public void getUpstreamNeighborsTest() {
		Graph testGraph = new AdjacencyListGraph();

		testGraph.addVertex(v1);
		testGraph.addVertex(v2);
		testGraph.addVertex(v7);
		testGraph.addVertex(v4);
		testGraph.addVertex(v6);
		testGraph.addVertex(v8);

		testGraph.addEdge(v1, v2);
		testGraph.addEdge(v2, v7);
		testGraph.addEdge(v2, v4);
		testGraph.addEdge(v7, v8);
		testGraph.addEdge(v6, v8);

		List<Vertex> upstreamNeighborsList1 = new ArrayList<Vertex>();

		upstreamNeighborsList1.add(v7);
		upstreamNeighborsList1.add(v6);
		
	
		assertEquals(upstreamNeighborsList1, testGraph.getUpstreamNeighbors(v8));
		

		List<Vertex> upstreamNeighborsList2 = new ArrayList<Vertex>(0);

		assertEquals(upstreamNeighborsList2, testGraph.getUpstreamNeighbors(v1));

	}
}
