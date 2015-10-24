package ca.ubc.ece.cpen221.mp3.twitterAnalysis;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.NoPathFoundException;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;
import sun.security.provider.certpath.AdjacencyList;

public class TwitterAnalysis {
    
    private static final String DATASET_PATH = "datasets/twitter.txt";
    private static final String COMMON_INFLUENCERS_QUERY = "commonInfluencers";
    private static final String NUMBER_RETWEETS_QUERY = "numRetweets";
    
    public static void main(String[] args) throws IOException{
        Graph graph = buildGraph(Paths.get(DATASET_PATH));
        handleQueries(graph, args[0], args[1]);
    }
    
    public static void handleQueries(Graph graph, String queryPath, String resultPath) throws IOException{
        PrintWriter writer = new PrintWriter(resultPath, "UTF-8");
        
        Files.lines(Paths.get(queryPath)).forEachOrdered(new Consumer<String>(){
            
            @Override
            public void accept(String t) {
                t.replace("//s+", " ");
                String[] tList = t.split(" ");
                if(tList.length >= 3){
                    if(tList[0].equals(COMMON_INFLUENCERS_QUERY)){
                        String uID1 = tList[1];
                        String uID2 = tList[2];
                        writer.println("query: " + COMMON_INFLUENCERS_QUERY + " " + uID1 + " " + uID2);
                        writer.println("<result>");
                        for(Vertex each_influencer : CommonInfluencers(graph, uID1, uID2))
                            writer.println(each_influencer.toString());
                        writer.println("</result>");
                    }
                    else if(tList[0].equals(NUMBER_RETWEETS_QUERY)){
                        String uID1 = tList[1];
                        String uID2 = tList[2];
                        writer.println("query: " + NUMBER_RETWEETS_QUERY + " " + uID1 + " " + uID2);
                        writer.println("<result>");
                        try {
                            writer.println(numRetweets(graph, uID1, uID2));
                        } catch (NoPathFoundException e) {
                            writer.println("ERROR: No connection exists between the users.");
                        }
                        writer.println("</result>");
                    }
                }
            }
          
        });
        
        writer.close();
    }
    
    public static Graph buildGraph(Path path) throws IOException{
        
        Graph graph = new AdjacencyListGraph();
        
        HashSet<Vertex> addedSet = new HashSet<Vertex>();
        
        Files.lines(path).forEachOrdered(new Consumer<String>(){

            @Override
            public void accept(String t) {
                t = t.replaceAll("//s", "");
                String[] tList = t.split("->");
                Vertex user1 = new Vertex(tList[0]);
                Vertex user2 = new Vertex(tList[1]);
                
                if(!addedSet.contains(user1)){
                    graph.addVertex(user1);
                    addedSet.add(user1);
                }
                if(!addedSet.contains(user2)){
                    graph.addVertex(user2);
                    addedSet.add(user2);
                }
                graph.addEdge(user1, user2);
            }
          
        });
        
        return graph;
    }
    
    public static int numRetweets(Graph graph, String uID1, String uID2) throws NoPathFoundException{
        return Algorithms.shortestDistance(graph, new Vertex(uID1), new Vertex(uID2));
    }
    
    public static List<Vertex> CommonInfluencers(Graph graph, String uID1, String uID2){
        return  Algorithms.commonDownstreamVertices(graph, new Vertex(uID1), new Vertex(uID2));
    }
}
