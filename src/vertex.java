import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

class vertex {
	private int xLoc;
	private int yLoc;
	private Color color = Color.red;
	private final static int width = 20;
	private final static int height = 20;
	private boolean visited = false;
	private ArrayList<edge> connectingEdge = new ArrayList<edge>();
	private ArrayList<vertex> adjacentVertex = new ArrayList<vertex>();
	
	//public 
	public int vertexNum = 0;
	public Color edgeColor = Color.blue;
	
	//fields for articulation points
	public int disc = 0;
	public int low = 0;
	public vertex parent = null; 

	vertex(int x, int y){
		xLoc = x;
		yLoc = y;
	}
	public boolean visited() {return visited;};
	public void setVisited(Boolean b) { visited = b;};

	public int getX() {return xLoc;};
	public int gety() {return yLoc;}
	public ArrayList<edge> returnEdges(){return connectingEdge;}
	public ArrayList<vertex> adjV(){return adjacentVertex;}

	public boolean hasEdges() {
		if(connectingEdge.size() != 0)
			return true;
		else
			return false;
	}
	public void addAdjV(vertex v) {
		this.adjacentVertex.add(v);
	}
	public void removeAdjV(vertex v) {
		this.adjacentVertex.remove(v);
	}
	public void addConEdges(edge e) {
		connectingEdge.add(e);
	}
	public void removeEdge(edge e) {
		connectingEdge.remove(e);
	}
	public void removeallConEdges(displayPanel panel) {
		panel.edgelistupdate(connectingEdge);
	}
	public void setColor(Color c) {
		color = c;
	}
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(xLoc- width/2, yLoc-height/2, width, height);
	}

	public Rectangle getBounds(){
		return new Rectangle( xLoc,yLoc, width, height);
	}
	
	
}
 

