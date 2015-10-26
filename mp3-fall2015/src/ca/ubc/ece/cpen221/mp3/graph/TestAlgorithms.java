package ca.ubc.ece.cpen221.mp3.graph;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.NoPathFoundException;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class TestAlgorithms {

	Vertex v1 = new Vertex("user1");
	Vertex v2 = new Vertex("user2");
	Vertex v3 = new Vertex("1234242");
	Vertex v4 = new Vertex("randomOne");
	Vertex v5 = new Vertex("67fht");
	Vertex v6 = new Vertex("user6");
	Vertex v7 = new Vertex("user7");
	Vertex v8 = new Vertex("me");
	Vertex v9 = new Vertex("notInGraph");

	@Test
	public void shortestDistanceTest() {
		Graph testGraph1 = new AdjacencyListGraph();

		testGraph1.addVertex(v1);
		testGraph1.addVertex(v2);
		testGraph1.addVertex(v3);
		testGraph1.addVertex(v4);
		testGraph1.addVertex(v5);
		testGraph1.addVertex(v6);
		testGraph1.addVertex(v7);
		testGraph1.addVertex(v8);
		testGraph1.addVertex(v9);

		testGraph1.addEdge(v1, v2);
		testGraph1.addEdge(v1, v3);
		testGraph1.addEdge(v2, v7);
		testGraph1.addEdge(v2, v4);
		testGraph1.addEdge(v2, v6);
		testGraph1.addEdge(v7, v8);
		testGraph1.addEdge(v6, v8);
		testGraph1.addEdge(v3, v5);
		testGraph1.addEdge(v3, v6);
		testGraph1.addEdge(v5, v8);

		try {
			assertEquals(1, Algorithms.shortestDistance(testGraph1, v1, v2));
		} catch (NoPathFoundException e) {

		}
		try {
			assertEquals(0, Algorithms.shortestDistance(testGraph1, v1, v1));
		} catch (NoPathFoundException e) {
			
		}

		try {
			Algorithms.shortestDistance(testGraph1, v1, v2);
		} catch (NoPathFoundException e) {

		}

	}

	@Test
	public void commonUpstreamVerticesTest() {
		Graph testGraph1 = new AdjacencyListGraph();

		testGraph1.addVertex(v1);
		testGraph1.addVertex(v2);
		testGraph1.addVertex(v3);
		testGraph1.addVertex(v4);
		testGraph1.addVertex(v5);
		testGraph1.addVertex(v6);
		testGraph1.addVertex(v7);
		testGraph1.addVertex(v8);
		testGraph1.addVertex(v9);

		testGraph1.addEdge(v1, v2);
		testGraph1.addEdge(v1, v3);
		testGraph1.addEdge(v2, v7);
		testGraph1.addEdge(v2, v4);
		testGraph1.addEdge(v2, v6);
		testGraph1.addEdge(v7, v8);
		testGraph1.addEdge(v6, v8);
		testGraph1.addEdge(v3, v5);
		testGraph1.addEdge(v3, v6);
		testGraph1.addEdge(v5, v8);
		
		List<Vertex> commonUpstreamList1= new ArrayList<Vertex>();
		
		commonUpstreamList1.add(v8);
		assertEquals(commonUpstreamList1, Algorithms.commonUpstreamVertices(testGraph1, v7, v6));
		
		List<Vertex> commonUpstreamList2= new ArrayList<Vertex>(0);
		assertEquals(commonUpstreamList2, Algorithms.commonUpstreamVertices(testGraph1,v2, v1));
	}

	@Test
	public void commonDownstreamVerticesTest() {
		Graph testGraph1 = new AdjacencyListGraph();

		testGraph1.addVertex(v1);
		testGraph1.addVertex(v2);
		testGraph1.addVertex(v3);
		testGraph1.addVertex(v4);
		testGraph1.addVertex(v5);
		testGraph1.addVertex(v6);
		testGraph1.addVertex(v7);
		testGraph1.addVertex(v8);
		testGraph1.addVertex(v9);

		testGraph1.addEdge(v1, v2);
		testGraph1.addEdge(v1, v3);
		testGraph1.addEdge(v2, v7);
		testGraph1.addEdge(v2, v4);
		testGraph1.addEdge(v2, v6);
		testGraph1.addEdge(v7, v8);
		testGraph1.addEdge(v6, v8);
		testGraph1.addEdge(v3, v5);
		testGraph1.addEdge(v3, v6);
		testGraph1.addEdge(v5, v8);
		
		List<Vertex> commonDownstreamList1= new ArrayList<Vertex>();
		commonDownstreamList1.add(v2);
		
		assertEquals(commonDownstreamList1, Algorithms.commonDownstreamVertices(testGraph1, v7, v4));
		
		List<Vertex> commonDownstreamList2= new ArrayList<Vertex>();
		
		assertEquals(commonDownstreamList2, Algorithms.commonDownstreamVertices(testGraph1, v2, v6));
	}

	@Test
	public void breadthFirstSearchTest() {
		Graph testGraph1 = new AdjacencyListGraph();

		testGraph1.addVertex(v1);
		testGraph1.addVertex(v2);
		testGraph1.addVertex(v3);
		testGraph1.addVertex(v4);
		testGraph1.addVertex(v5);
		testGraph1.addVertex(v6);
		testGraph1.addVertex(v7);
		testGraph1.addVertex(v8);
		
		testGraph1.addEdge(v1, v2);
		testGraph1.addEdge(v1, v3);
		testGraph1.addEdge(v2, v7);
		testGraph1.addEdge(v2, v4);
		testGraph1.addEdge(v2, v6);
		testGraph1.addEdge(v7, v8);
		testGraph1.addEdge(v6, v8);
		testGraph1.addEdge(v3, v5);
		testGraph1.addEdge(v3, v6);
		testGraph1.addEdge(v5, v8);
		
		Set<List<Vertex>> testTraverses= new HashSet<List<Vertex>>();
		
		List<Vertex> root1= new ArrayList<Vertex>();
		root1.add(v1);
		root1.add(v2);
		root1.add(v3);
		root1.add(v7);
		root1.add(v4);
		root1.add(v6);
		root1.add(v5);
		root1.add(v8);
		testTraverses.add(root1);
		assertEquals(true, Algorithms.breadthFirstSearch(testGraph1).contains(root1));
		
		List<Vertex> root2= new ArrayList<Vertex>();
		root2.add(v2);
		root2.add(v7);
		root2.add(v4);
		root2.add(v6);
		root2.add(v8);
		testTraverses.add(root2);
		assertEquals(true, Algorithms.breadthFirstSearch(testGraph1).contains(root2));
		
		List<Vertex> root3= new ArrayList<Vertex>();
		root3.add(v3);
		root3.add(v6);
		root3.add(v5);
		root3.add(v8);
		testTraverses.add(root3);
		//assertEquals(true, Algorithms.breadthFirstSearch(testGraph1).contains(root3));
		
		List<Vertex> root4= new ArrayList<Vertex>();
		root4.add(v4);
		testTraverses.add(root4);
		assertEquals(true, Algorithms.breadthFirstSearch(testGraph1).contains(root4));
		
		List<Vertex> root5= new ArrayList<Vertex>();
		root5.add(v5);
		root5.add(v8);
		testTraverses.add(root5);
		assertEquals(true, Algorithms.breadthFirstSearch(testGraph1).contains(root5));
		
		List<Vertex> root6= new ArrayList<Vertex>();
		root6.add(v6);
		root6.add(v8);
		testTraverses.add(root6);
		assertEquals(true, Algorithms.breadthFirstSearch(testGraph1).contains(root6));
		
		List<Vertex> root7= new ArrayList<Vertex>();
		root7.add(v7);
		root7.add(v8);
		testTraverses.add(root7);
		assertEquals(true, Algorithms.breadthFirstSearch(testGraph1).contains(root7));
		
		List<Vertex> root8= new ArrayList<Vertex>();
		root8.add(v8);
		testTraverses.add(root8);
		assertEquals(true, Algorithms.breadthFirstSearch(testGraph1).contains(root8));
		

		assertEquals(testTraverses, Algorithms.breadthFirstSearch(testGraph1));
		
	}

	@Test
	public void depthFirstSearchTest() {

	}

}
