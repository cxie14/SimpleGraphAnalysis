package ca.ubc.ece.cpen221.mp3.graph;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.NoPathFoundException;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;
import sun.dc.path.PathException;

public class Algorithms {

	/**
	 * *********************** Algorithms ****************************
	 * 
	 * Please see the README for the machine problem for a more detailed
	 * specification of the behavior of each method that one should implement.
	 */

	/**
	 * This is provided as an example to indicate that this method and other
	 * methods should be implemented here.
	 * 
	 * You should write the specs for this and all other methods.
	 * 
	 * @param graph
	 * @param a
	 * @param b
	 * @return
	 * @throws NoPathFoundException 
	 */
	public static int shortestDistance(Graph graph, Vertex a, Vertex b) throws NoPathFoundException {
		// TODO: Implement this method and others
	    Queue<Vertex> nextVertexQueue = new LinkedList<Vertex>();
	    HashSet<Vertex> scheduledSet = new HashSet<Vertex>();
	    
        nextVertexQueue.addAll(graph.getDownstreamNeighbors(a));
        scheduledSet.addAll(graph.getDownstreamNeighbors(a));
	    
	    int depth = 1;
	    while(!nextVertexQueue.isEmpty()){
	        Queue<Vertex> currentVertexQueue = new LinkedList<Vertex>(nextVertexQueue);
	        nextVertexQueue = new LinkedList<>();
	        while(!currentVertexQueue.isEmpty()){
	            Vertex currentRoot = currentVertexQueue.poll();
	            if(currentVertexQueue.poll().equals(b))
	                return depth;
	            else{
	                for(Vertex each_child : graph.getDownstreamNeighbors(currentRoot)){
	                    if(!scheduledSet.contains(each_child)){
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
	
	public static Set<List<Vertex>> breadthFirstSearch(Graph graph){
	    //TODO: Representation safety required
	    Set<List<Vertex>> result = new HashSet<List<Vertex>>();
	    for(Vertex a : graph.getVertices()){
	        
    	    Queue<Vertex> nextVertexQueue = new LinkedList<Vertex>();
            HashSet<Vertex> scheduledSet = new HashSet<Vertex>();
            
            //initialize the first root queue
            nextVertexQueue.addAll(graph.getDownstreamNeighbors(a));
            scheduledSet.addAll(graph.getDownstreamNeighbors(a));
            
            //one traversal of the graph starting at a vertex a
            List<Vertex> traversal = new LinkedList<Vertex>();
            
            //loop until run out of new vertexes to visit
            while(!nextVertexQueue.isEmpty()){
                Queue<Vertex> currentVertexQueue = new LinkedList<Vertex>(nextVertexQueue);
                nextVertexQueue = new LinkedList<>();
                
                //loop through all the vertices on this level
                while(!currentVertexQueue.isEmpty()){
                    
                    //add the current vertex to the traversal
                    Vertex currentRoot = currentVertexQueue.poll();
                    traversal.add(currentRoot);
                    
                    //add children if they aren't already scheduled
                    for(Vertex each_child : graph.getDownstreamNeighbors(currentRoot)){
                        if(!scheduledSet.contains(each_child)){
                            nextVertexQueue.add(each_child);
                            scheduledSet.add(each_child);
                        }
                    }
                }
            }
            result.add(traversal);
	    }
	    return result;
	}
	
	public static Set<List<Vertex>> depthFirstSearch(Graph graph){
	    Set<List<Vertex>> result = new HashSet<List<Vertex>>();
	    for(Vertex primaryRoot : graph.getVertices()){
	        Stack<Vertex> vertexStack = new Stack<Vertex>();
	        HashSet<Vertex> scheduledSet = new HashSet<Vertex>();
	        List<Vertex> traversal = new LinkedList<Vertex>();
	        
	        vertexStack.add(primaryRoot);
	        scheduledSet.add(primaryRoot);
	        
	        while(!vertexStack.isEmpty()){
	            boolean noChildLeft = true;
    	        for(Vertex each_childRoot : graph.getDownstreamNeighbors(vertexStack.peek())){
    	            if(!scheduledSet.contains(each_childRoot)){
    	                scheduledSet.add(each_childRoot);
    	                vertexStack.add(each_childRoot);
    	                traversal.add(each_childRoot);
    	                noChildLeft = false;
    	                break;
    	            }
    	        }
    	        if(noChildLeft){
    	            vertexStack.pop();
    	        }
	        }
	        result.add(traversal);
	    }
	    return result;
	}
	
	/**
     * Finds the common upstream vertices, such that for each upstream vertex v,
     * a and b are both one edge up from v. Returns an empty list if none exist.
     * @param graph Non-empty graph representation
     * @param a Vertex contained within graph
     * @param b Vertex contained within graph
     * @return A list of vertices that contains the common down stream vertices of a and b
     */
	public static List<Vertex> commonUpstreamVertices(Graph graph, Vertex a, Vertex b){
	    List<Vertex> aList = graph.getDownstreamNeighbors(a);
        List<Vertex> bList = graph.getDownstreamNeighbors(b);
        List<Vertex> commonDown = new LinkedList<Vertex>();
        for(Vertex aVertex : aList){
            for(Vertex bVertex : bList){
                if(aVertex.equals(bVertex)){
                    commonDown.add(aVertex);
                }
            }
        }
        return commonDown;
	}
	
	/**
	 * Finds the common downstream vertices, such that for each downstream vertex v,
	 * a and b are both one edge down from v. Returns an empty list if none exist.
	 * @param graph Non-empty graph representation
	 * @param a Vertex contained within graph
	 * @param b Vertex contained within graph
	 * @return A list of vertices that contains the common down stream vertices of a and b
	 */
	public static List<Vertex> commonDownstreamVertices(Graph graph, Vertex a, Vertex b){
	    List<Vertex> aList = graph.getUpstreamNeighbors(a);
	    List<Vertex> bList = graph.getUpstreamNeighbors(b);
	    List<Vertex> commonDown = new LinkedList<Vertex>();
	    for(Vertex aVertex : aList){
	        for(Vertex bVertex : bList){
	            if(aVertex.equals(bVertex)){
	                commonDown.add(aVertex);
	            }
	        }
	    }
	    return commonDown;
    }
}
