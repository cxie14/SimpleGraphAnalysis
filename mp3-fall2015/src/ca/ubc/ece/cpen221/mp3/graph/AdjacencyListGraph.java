package ca.ubc.ece.cpen221.mp3.graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;
import javafx.util.Pair;

public class AdjacencyListGraph implements Graph {
    
    //maps vertex to downstream and upstream verteces
    HashMap<Vertex, ArrayList<List<Vertex>>> internal_map;
    
    public AdjacencyListGraph() {
        internal_map = new HashMap<Vertex, ArrayList<List<Vertex>>>();
    }
    
    @Override
    public void addVertex(Vertex v) {
        ArrayList<List<Vertex>> init = new ArrayList<List<Vertex>>(2);
        init.add(new ArrayList<>());
        init.add(new ArrayList<>());
        internal_map.put(v, init);
    }

    @Override
    public void addEdge(Vertex v1, Vertex v2) {
        //downstream
        internal_map.get(v1).get(0).add(v2);
        //upstream
        internal_map.get(v2).get(1).add(v1);
    }

    @Override
    public boolean edgeExists(Vertex v1, Vertex v2) {
        // TODO Auto-generated method stub
        return internal_map.get(v1).get(0).contains(v2);
    }

    @Override
    public List<Vertex> getDownstreamNeighbors(Vertex v) {
        // TODO Auto-generated method stub
        return new ArrayList<Vertex>(internal_map.get(v).get(0));
    }

    @Override
    public List<Vertex> getUpstreamNeighbors(Vertex v) {
        // TODO Auto-generated method stub
        return new ArrayList<Vertex>(internal_map.get(v).get(1));
    }

    @Override
    public List<Vertex> getVertices() {
        // TODO Auto-generated method stub
        return new ArrayList<Vertex>(internal_map.keySet());
    }
}
