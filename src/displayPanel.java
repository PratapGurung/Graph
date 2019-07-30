import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
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
	public void paint(Graphics g) {
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
			vertexlist.add(v);
			vertexlist.get(exist(v)).vertexNum = exist(v);
			this.repaint();
		}

	}
	public void addedge(vertex v1, vertex v2){
		// TODO Auto-generated method stub
		if(exist(v1) !=- 1&&exist(v2) != -1 
				&& !v1.equals(v2)){
			edge e = new edge(v1,v2);
			edgelist.add(e);
			v1.addConEdges(e);
			v2.addConEdges(e);
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
			this.repaint();
		}	
	}

	public void removeEdge(int x, int y) {
		Rectangle2D r = new Rectangle2D.Float(x-5, y-5, 10, 10);
		for(int i=0;i<edgelist.size();i++)
		{
			if(edgelist.get(i).getBounds().intersects(r)) {
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
			System.out.print("false");
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

	public void showComponents(){
		for(edge e: edgelist) {
			if(e.retVisted())
				e.setVisited(false);
		}
		for(vertex v: vertexlist) {
			v.edgeColor = Color.blue;
		}
		coloredges(vertexlist,edgelist);
		this.repaint();
	}
	public void coloredges(ArrayList<vertex> vlist, ArrayList<edge>elist) {
		for(vertex v: vlist) {
			
			Color col =  new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
			

			for(edge e: elist) {
				if(!e.retVisted() &&
						(e.retVertex1().equals(v)|| e.retVertex2().equals(v)) ) {

					e.setVisited(true);
					if(e.retVertex1().equals(v) ) {
						if(v.edgeColor != Color.blue ) {
							e.setColor(v.edgeColor);
							e.retVertex2().edgeColor = v.edgeColor;
						}
						else if(v.edgeColor == Color.blue
								&& e.retVertex2().edgeColor !=  Color.blue){
							e.setColor(e.retVertex2().edgeColor);
							v.edgeColor = e.retVertex2().edgeColor;

						}
						
						else {
							col = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
							e.setColor(col);
							v.edgeColor = col;
							e.retVertex2().edgeColor = col;
						}
					}
					if(e.retVertex2().equals(v) ) {
						if(v.edgeColor != Color.blue) {
							e.setColor(v.edgeColor);
							e.retVertex1().edgeColor = v.edgeColor;
						}

						else if(v.edgeColor == Color.blue
								&& e.retVertex1().edgeColor !=  Color.blue){
							e.setColor(e.retVertex1().edgeColor);
							v.edgeColor = e.retVertex1().edgeColor;

						}
						
						else {
							col = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
							e.setColor(col);
							v.edgeColor = col;
							e.retVertex1().edgeColor = col;
						}
					}
				}
			}
		}
	}
	public void clear() {
		//Graph g= new Graph(vertexlist, edgelist);
		//g.connectedComponents();
		// TODO Auto-generated method stub
		vertexlist.removeAll(vertexlist);
		edgelist.removeAll(edgelist);
		this.repaint();
	}
	
	public void cutVertices() {
		@SuppressWarnings("unchecked")
		ArrayList<vertex> vlist = (ArrayList<vertex>) vertexlist.clone();
		@SuppressWarnings("unchecked")
		ArrayList<edge> elist= (ArrayList<edge>)edgelist.clone();
		ArrayList<Integer> id = new ArrayList<Integer>();
		for(vertex v: vlist) {
			v.setVisited(false);
		}
		for(edge e: elist) {
			e.setVisited(false);
		}
		for(int i=0;i< vlist.size();i++) {
			vertex v = vlist.get(i);
			if(!v.visited()) {
				v.setVisited(true);
				for(int j = 0; j< elist.size();j++) {
					if(elist.get(j).retVertex1().equals(v)||elist.get(j).retVertex2().equals(v))
						elist.remove(j);
						
				}
				vlist.remove(i);
				coloredges(vlist, elist);
				if(checkGraph() > 0) {
					id.add(i);
				}
				vlist.add(v);
			}
		}
		
		for(int i = 0; i< id.size();i++) {
			vertexlist.get(id.get(i)).setColor(Color.green);
		}
		this.repaint();
	}

	private int checkGraph() {
		// TODO Auto-generated method stub
		ArrayList<Color> clist = new ArrayList<Color>();
		Color c = edgelist.get(0).lineColor();
		clist.add(c);
		for(edge e: edgelist) {
			if(!clist.contains(e.lineColor())) {
				clist.add(e.lineColor());
			}
		}
		
		return clist.size();
	}

}
