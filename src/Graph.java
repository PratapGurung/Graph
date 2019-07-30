// Java program to print connected components in  
// an undirected graph  
import java.util.ArrayList;
import java.util.LinkedList; 
class Graph { 
    // A user define class to represent a graph. 
    // A graph is an array of adjacency lists. 
    // Size of array will be V (number of vertices 
    // in graph) 
    int V; 
    LinkedList<Integer>[] adjListArray; 
    ArrayList<vertex> vlist;
     ArrayList<edge> elist;
    // constructor 
    Graph(ArrayList<vertex> vlist, ArrayList<edge> elist) { 
       
        V = vlist.size();
        this.vlist = vlist;
        this.elist = elist;
        adjListArray = new LinkedList[V]; 
  
        // Create a new list for each vertex 
        // such that adjacent nodes can be stored 
  
        for(int i = 0; i < V ; i++){ 
            adjListArray[i] = new LinkedList<Integer>(); 
        } 
        for(edge e : elist) {
        		addEdge(e.retVertex1().vertexNum,e.retVertex2().vertexNum);
        }
    } 
      
    // Adds an edge to an undirected graph 
    void addEdge( int src, int dest) { 
        // Add an edge from src to dest. 
    		
        adjListArray[src].add(dest); 
  
        // Since graph is undirected, add an edge from dest 
        // to src also 
        adjListArray[dest].add(src); 
    } 
      
    void DFSUtil(int v, boolean[] visited) { 
        // Mark the current node as visited and print it 
        visited[v] = true; 
        System.out.print(v+" "); 
        // Recur for all the vertices 
        // adjacent to this vertex 
        for (int x : adjListArray[v]) { 
            if(!visited[x]) DFSUtil(x,visited); 
        } 
  
    } 
    void connectedComponents() { 
        // Mark all the vertices as not visited 
        boolean[] visited = new boolean[V]; 
        for(int v = 0; v < V; ++v) { 
            if(!visited[v]) { 
                // print all reachable vertices 
                // from v 
                DFSUtil(v,visited); 
                System.out.println(); 
            } 
        } 
    } 
    public void prints() {
    	 LinkedList<Integer> n = adjListArray.getFirst();
    	 
    }

}   