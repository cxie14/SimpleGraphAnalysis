package ca.ubc.ece.cpen221.mp3.graph;
import java.util.*;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyMatrixGraph implements Graph {
// TODO: Implement this class
	

	private HashMap<Vertex, Integer> indexer;
	LinkedList<LinkedList<Boolean>> internal_matrix;
	
	//creates an empty VxV matrix of type boolean  
	
	public AdjacencyMatrixGraph(){
		  internal_matrix=new LinkedList<LinkedList<Boolean>>();
		  indexer= new HashMap<Vertex, Integer>();
	}
	
	public void addVertex(Vertex v){
		indexer.put(v,  internal_matrix.size());
		LinkedList newRow= new LinkedList<Boolean>();
		
		for(LinkedList<Boolean> row : internal_matrix){
			
			row.add(false);
		}
		
		for(int col=0; col<internal_matrix.size()+1; col++){
				newRow.add(false);
			}
		internal_matrix.add(newRow);
		
		}
	
	public void addEdge(Vertex v1, Vertex v2){
		
		int index1= indexer.get(v1);
		int index2= indexer.get(v2);
		internal_matrix.get(index2).add(index1, true);
				
	}
	
	public boolean edgeExists(Vertex v1, Vertex v2){
		
		int index1= indexer.get(v1);
		int index2= indexer.get(v2);
		
		return internal_matrix.get(index2).get(index1);
	}
	
	public List<Vertex> getVertices(){
		 List<Vertex> vertices= new LinkedList<Vertex>();
		 for(int vertexIndex=0;  vertexIndex< internal_matrix.size(); vertexIndex++){
			 for(Vertex newVertex : indexer.keySet()){
				 if(indexer.get(newVertex)== vertexIndex){
					 vertices.add(newVertex);
				 }
			 }
		 }
		return vertices; 
		 
	}
	
	public List<Vertex> getDownstreamNeighbors(Vertex v){
		
		int index= indexer.get(v);
		LinkedList<Vertex> downStreamList= new LinkedList<Vertex>();
		
		for(int rowIndex=0; rowIndex < internal_matrix.size(); rowIndex++){
			if(internal_matrix.get(rowIndex).get(index)){
				for(Vertex newVertex : indexer.keySet()){
					if(indexer.get(newVertex)==rowIndex){
						downStreamList.add(newVertex);
					}
				}
			}
		}
		
		return downStreamList;
	}
	
		public List<Vertex> getUpstreamNeighbors(Vertex v){
			
			int index= indexer.get(v);
			LinkedList<Vertex> upStreamList= new LinkedList<Vertex>();
			
			for(int colIndex=0; colIndex < internal_matrix.size(); colIndex++){
				if(internal_matrix.get(colIndex).get(index)){
					for(Vertex newVertex : indexer.keySet()){
						if(indexer.get(newVertex)== colIndex){
							upStreamList.add(newVertex);
						}
					}
				}
			}
			return upStreamList;
		}
		
}
	
	
