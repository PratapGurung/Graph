import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

class edge {
 	private int startingX;
 	private int startingY;
 	private Graphics2D g;
 	private int endX;
 	private int endY;
 	
 	private vertex vertex1;
 	private vertex vertex2;
 	private boolean visited = false;

 	private Color color = Color.blue;
 	edge(vertex v1, vertex v2){
 		vertex1 = v1;
 		vertex2 = v2;
 		startingX = v1.getX();
 		startingY = v1.gety();
 		endX = v2.getX();
 		endY = v2.gety();
 	}
 	
 	public int getStX() {return startingX;}
 	public int getEX() {return endX;}
 	
 	public Color lineColor() {
 		return color; 
 	}
 	public void setVertex1(vertex v) { vertex1 = v;}
 	public void setVertex2(vertex v) { vertex2 = v;}
 	public void setColor(Color c) {this.color = c;}
 	public void setVisited(Boolean b) {visited = b;}
 	public vertex retVertex1() {return vertex1;}
 	public vertex retVertex2() {return vertex2;}
 	public Line2D getBounds() {
 		return new Line2D.Float(startingX, startingY, endX, endY);
 	}
 	public void draw(Graphics g) {
 		 this.g = (Graphics2D) g;
 		 this.g.setStroke(new BasicStroke(7));
 		this.g.setColor(color);
 		this.g.drawLine(vertex1.getX(), vertex1.gety(), vertex2.getX(), vertex2.gety());
 	}

	public boolean retVisted() {
		// TODO Auto-generated method stub
		return visited;
	}
 	
 	
 }

