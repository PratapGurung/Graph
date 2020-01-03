
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
// import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;			

class displayPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ArrayList<vertex> vertexlist = new ArrayList<vertex>();
	protected ArrayList<edge> edgelist = new ArrayList<edge>();

	displayPanel(){
		super();
	}
	public void paintComponent(Graphics g) {
		//Graphics g = this.getGraphics();
		super.paintComponent(g);
		for(vertex x: vertexlist) {
			x.draw(g);
		}
		for(edge e: edgelist) {
			e.draw(g);

		}
	}

	public int exist(vertex e) {
		for(int i=0;i<vertexlist.size();i++)
		{
			if(vertexlist.get(i).getBounds().intersects(e.getBounds())) {
				return i;
			}

		}
		return -1;
	}
	public int exist(edge e) {
		for(int i=0;i<edgelist.size();i++)
		{
			edge m = edgelist.get(i);
			if(m.getStX() == e.getStX() && m.getEX() == e.getEX()) {
				return i;
			}
		}
		return -1;
	}

	public void addvertex(vertex v) {
		// TODO Auto-generated method stu
		if(exist(v) == -1 && !outofbound(v.getX(), v.gety())) {
			v.vertexNum = vertexlist.size();
			vertexlist.add(v);
			this.repaint();
		}

	}

	public void addedge(vertex v1, vertex v2){
		// TODO Auto-generated method stub
		if(exist(v1) !=- 1&&exist(v2) != -1 
				&& !v1.equals(v2)){
			edge e = new edge(v1,v2);
			edgelist.add(e);

			//add connecting edges
			v1.addConEdges(e);
			v2.addConEdges(e);

			//add adjacency
			v1.addAdjV(v2);
			v2.addAdjV(v1);

			//repaint
			this.repaint();
		}

	}

	public void removevertex(vertex v) {

		if(exist(v)!= -1) {
			vertexlist.get(exist(v)).removeallConEdges(this);
			vertexlist.remove(exist(v));	
		}
		this.repaint();		
	}

	public void removeEdge(edge e) {
		removeEdge(e.retVertex1(), e.retVertex2());
	}
	public void removeEdge(vertex v1, vertex v2) {
		if(exist(v1) !=- 1&&exist(v2) != -1 
				&& !v1.equals(v2)){
			edge e1 = new edge(v1,v2);
			edge e2 = new edge(v1,v2);
			int i = exist(e1);
			int j = exist(e2);
			if(i != -1) {
				edgelist.remove(i);
				v1.removeEdge(e1);
				v2.removeEdge(e1);
			}

			else if(j !=-1) {
				edgelist.remove(j);
				v1.removeEdge(e2);
				v2.removeEdge(e2);
			}

			//remove adjacency
			v1.removeAdjV(v2);
			v2.removeAdjV(v1);
			this.repaint();
		}	
	}

	public void removeEdge(int x, int y) {
		Rectangle2D r = new Rectangle2D.Float(x-5, y-5, 10, 10);
		for(int i=0;i<edgelist.size();i++)
		{
			if(edgelist.get(i).getBounds().intersects(r)) {
				vertex v1 = edgelist.get(i).retVertex1();
				vertex v2 = edgelist.get(i).retVertex2();
				v1.removeEdge(edgelist.get(i));
				v2.removeEdge(edgelist.get(i));
				//remove adjacency
				v1.removeAdjV(v2);
				v2.removeAdjV(v1);
				edgelist.remove(i);
				break;
			}
		}
		this.repaint();
	}

	public boolean outofbound(int x, int y) {
		if(x-10 < (int)this.getAlignmentX() || y-10 < (int)this.getAlignmentY()) {
			return true;
		}
		return false;
	}

	public void edgelistupdate(ArrayList<edge> conlist) {
		for(edge e: conlist) {
			if(exist(e) != -1) {
				edgelist.remove(exist(e));
			}
		}
		this.repaint();
	}

	public void addAllEdges() {
		// TODO Auto-generated method stub
		if(edgelist.size() -(vertexlist.size() * (vertexlist.size() - 1))/2!= 0) {
			for(int i=0;i< vertexlist.size();i++) {
				vertex v = vertexlist.get(i);
				for(int j = 0; j < i; j++) {
					vertex x = vertexlist.get(j);
					edge e = new edge(v,x);
					if(exist(e) == -1) {
						this.addedge(v, x);
					}
				}
			}
		}
		for(edge e: edgelist) {
			if(e.lineColor() != Color.blue)
				e.setColor(Color.blue);
		}
		this.repaint();
	}

	public void recreatEdges(vertex oldV, vertex newV) {
		// TODO Auto-generated method stub
		for(edge e: edgelist)
		{
			if(e.retVertex1().equals(oldV))
			{
				e.setVertex1(newV);

			}
			else if(e.retVertex2().equals(oldV)){
				e.setVertex2(newV);
			}

		}
		this.repaint();
		vertexlist.remove(exist(oldV));
	}

	public void showComponents() {
		for (edge e: edgelist) {
			e.setVisited(false);//set visited
			
			/*
			 * //v.setColor(Color.black); System.out.println(v.getX() + " " + v.gety());
			 * for(vertex x: v.adjV()) { System.out.println("\t" + x.getX() + " " +
			 * x.gety()); }
			 */
		}


		for (edge e: edgelist) { 
			Color col = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());

			if(!e.visited()) {
				DFSVISIT(e, col); 
				e.setVisited(true);
			} 
		}

		this.repaint();

	}

	private void DFSVISIT(edge e, Color c){
		if(e.visited()) {
			return;
		}
		else {
			e.setVisited(true);
			vertex v1 = e.retVertex1();
			vertex v2 = e.retVertex2();
			ArrayList<edge> adjlist = new ArrayList<edge>();
			
			for(edge x: v1.returnEdges()) {
				if(!adjlist.contains(x) && !x.visited()) {
					adjlist.add(x);
				}
				
			}
			for(edge x: v2.returnEdges()) {
				if(!adjlist.contains(x) && !x.visited()) {
					adjlist.add(x);
				}
			}
			//v.edgeColor = c;
			/*
			 * for (vertex x: adjlist) { if(!x.visited()) { x.setVisited(true);
			 * //x.edgeColor = c; x.setColor(c); DFSVISIT(x,c); }
			 * 
			 * }
			 */
			for(edge x: adjlist) {
				if(!x.visited()) {
					DFSVISIT(x, c);	
				}
				
			}
		}
		
		e.setColor(c);
		
	}

	
	
	public void clear() {
		// TODO Auto-generated method stub
		vertexlist.removeAll(vertexlist);
		edgelist.removeAll(edgelist); 
		this.repaint();
		//System.out.println("Clear button pressed");
	}

	// A recursive function that find articulation points using DFS 
    // u --> The vertex to be visited next 
    // visited[] --> keeps tract of visited vertices 
    // disc[] --> Stores discovery times of visited vertices 
    // parent[] --> Stores parent vertices in DFS tree 
    // ap[] --> Store articulation points 
    void APUtil(vertex  u , ArrayList<vertex> ap) 
    { 
  
      
  
        // Mark the current node as visited 
        u.setVisited(true);
  
        // Initialize discovery time and low value 
        u.disc = u.low = ++time; 
  
        // Go through all vertices aadjacent to this 
       for(vertex v: u.adjV())
        { 
    	   	// v is current adjacent of u 
  
            // If v is not visited yet, then make it a child of u 
            // in DFS tree and recur for it 
            if (!v.visited()) 
            { 
                
                v.parent = u;
                APUtil(v, ap); //
  
                // Check if the subtree rooted with v has a connection to 
                // one of the ancestors of u 
                u.low  = Math.min(u.low, v.low); 
  
                // u is an articulation point in following cases 
               
				/*
				 * // (1) u is root of DFS tree and has two or more children. if (u.parent ==
				 * null && u.adjV().size() > 1) { if(!ap.contains(u)) { ap.add(u); } }
				 */
  
                // (2) If u is not root and low value of one of its child 
                // is more than discovery value of u. 
                if (u.parent != null && v.low >= u.disc) 
                {
                	if(!ap.contains(u)) {
                		ap.add(u);
                	}
                }
                
            } 
  
            // Update low value of u for parent function calls. 
            else if (v != u.parent) 
                u.low  = Math.min(u.low, v.disc); 
        } 
    } 
  
    // The function to do DFS traversal. It uses recursive function APUtil() 
    public void AP() 
    { 
        // Mark all the vertices as not visited 
       
    	 ArrayList<vertex> ap = new ArrayList<vertex>(); 
  
        // Initialize parent and visited, and ap(articulation point) 
        // arrays 
        for (vertex v: vertexlist) 
        { 
           v.parent = null; 
           v.setVisited(false);
        } 
  
        // Call the recursive helper function to find articulation 
        // points in DFS tree rooted with vertex 'i' 
        for (vertex v: vertexlist) {
        	 if (!v.visited()) 
                 APUtil(v, ap); 
        }
           
  
        // Now ap[] contains articulation points, print them 
        for (vertex v: ap) {
        	System.out.println(v.vertexNum);
        	v.setColor(Color.green);
        }
        this.repaint();
            
    } 
    
    
    static int time = 0; 
   // To store articulation points 
   
    

}
