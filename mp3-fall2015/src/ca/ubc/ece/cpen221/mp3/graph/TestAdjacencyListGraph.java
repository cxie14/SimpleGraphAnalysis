package ca.ubc.ece.cpen221.mp3.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class TestAdjacencyListGraph {
    
    List<Vertex> vertices = new ArrayList<Vertex>(Arrays.asList(
            new Vertex("vertex0"),
            new Vertex("vertex1"),
            new Vertex("vertex2"),
            new Vertex("vertex3"),
            new Vertex("vertex4"),
            new Vertex("vertex5"),
            new Vertex("vertex6"),
            new Vertex("vertex7"),
            new Vertex("vertex8"),
            new Vertex("vertex9")));
    
    @Test
    public void testAddVertex(){
        Graph testGraph1 = new AdjacencyListGraph(); 
        HashSet result1 = new HashSet<Vertex>();
        testGraph1.addVertex(vertices.get(0));
        result1.add(vertices.get(0));
        testGraph1.addVertex(vertices.get(9));
        result1.add(vertices.get(9));
        assertEquals(result1, new HashSet<Vertex>(testGraph1.getVertices()));
    }
    
    @Test
    public void testEdgeExists(){
        Graph testGraph1 = new AdjacencyListGraph(); 
        testGraph1.addVertex(vertices.get(0));
        testGraph1.addVertex(vertices.get(9));
        testGraph1.addEdge(vertices.get(0), vertices.get(9));
        assertEquals(true, testGraph1.edgeExists(vertices.get(0), vertices.get(9)));
        assertEquals(false, testGraph1.edgeExists(vertices.get(9), vertices.get(0)));
    }
}
