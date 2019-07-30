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
	public int vertexNum = 0;
	public Color edgeColor = Color.blue;
	private boolean visited = false;

	private ArrayList<edge> connectingEdge = new ArrayList<edge>();
	

	vertex(int x, int y){
		xLoc = x;
		yLoc = y;
	}
	public boolean visited() {return visited;};
	public void setVisited(Boolean b) { visited = b;};

	public int getX() {return xLoc;};
	public int gety() {return yLoc;}
	public ArrayList<edge> returnEdges(){return connectingEdge;}

	public boolean hasEdges() {
		if(connectingEdge.size() != 0)
			return true;
		else
			return false;
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
	public ArrayList<edge> retconedges() {
		// TODO Auto-generated method stub
		return connectingEdge;
	}
}
 
