package ca.ubc.ece.cpen221.mp3.graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyMatrixGraph implements Graph {

    private HashMap<Vertex, Integer> indexer;
    private ArrayList<ArrayList<Boolean>> internal_matrix;
    
    public AdjacencyMatrixGraph() {
        internal_matrix = new ArrayList<ArrayList<Boolean>>();
        indexer = new HashMap<Vertex, Integer>();
    }
    
    @Override
    public void addVertex(Vertex v) {
        indexer.put(v, internal_matrix.size());
        ArrayList<Boolean> newRow = new ArrayList<Boolean>();
        
        for(ArrayList<Boolean> each_row : internal_matrix){
            each_row.add(false);
        }
        
        for(int col = 0; col < internal_matrix.size()+1; col++){
            newRow.add(false);
        }
        
        internal_matrix.add(newRow);
        
    }

    @Override
    public void addEdge(Vertex v1, Vertex v2) {
        int index1 = indexer.get(v1);
        int index2 = indexer.get(v2);
        internal_matrix.get(index2).add(index1, true);
    }

    @Override
    public boolean edgeExists(Vertex v1, Vertex v2) {
        int index1 = indexer.get(v1);
        int index2 = indexer.get(v2);
        return internal_matrix.get(index2).get(index1);
    }

    @Override
    public List<Vertex> getDownstreamNeighbors(Vertex v) {
        int index = indexer.get(v);
        List<Vertex> downStreamList = new LinkedList<Vertex>(); 
        for(int rowIndex = 0; rowIndex < internal_matrix.size(); rowIndex++){
            if(internal_matrix.get(rowIndex).get(index)){
                for(Vertex newVertex : indexer.keySet()){
                    if(indexer.get(newVertex) == rowIndex){
                        downStreamList.add(newVertex);
                    }
                }
            }
        }
        return downStreamList;
    }

    @Override
    public List<Vertex> getUpstreamNeighbors(Vertex v) {
        int index = indexer.get(v);
        List<Vertex> upStreamList = new LinkedList<Vertex>();
        for(int colIndex = 0; colIndex < internal_matrix.size(); colIndex++){
            if(internal_matrix.get(index).get(colIndex)){
                for(Vertex newVertex : indexer.keySet()){
                    if(indexer.get(newVertex) == colIndex){
                        upStreamList.add(newVertex);
                    }
                }
            }
        }
        return upStreamList;
    }

    @Override
    public List<Vertex> getVertices() {
        List<Vertex> vertices = new LinkedList<Vertex>();
        for(int vertexIndex = 0; vertexIndex < internal_matrix.size(); vertexIndex++){
            for(Vertex newVertex : indexer.keySet()){
                if(indexer.get(newVertex) == vertexIndex){
                    vertices.add(newVertex);
                }
            }
        }
        return vertices;
    }
}
