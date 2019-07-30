import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class myMouseAdapter extends MouseAdapter {
	displayPanel panel = null;
	private GG0142 ggui;
	private boolean firstclick = true;
	private boolean secondclick = false;
	private int oldvloc  = 0;
	private vertex vertex1;
	private vertex vertex2;
	
	myMouseAdapter(displayPanel display, GG0142 ggui){
		panel = display;
		this.ggui = ggui;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.print("x:"+ e.getX() + "y:"+e.getY() );
		if(ggui.addVertex.isSelected()) {
			panel.addvertex(new vertex(e.getX(),e.getY()));
			clickreset();
		}
			
		if(ggui.removeVertex.isSelected()) {
			panel.removevertex(new vertex(e.getX(),e.getY()));
			clickreset();
		}
		
		
		if(ggui.moveVertex.isSelected()) {
			vertex v = new vertex(e.getX(),e.getY());
			if(firstclick && panel.exist(v) != -1 ) {
				oldvloc = panel.exist(v);
				panel.vertexlist.get(oldvloc).setColor(Color.green);
				panel.repaint();
				firstclick =false;
				secondclick = true;
			}
			else if(secondclick && panel.exist(v) == -1) {
				
				panel.addvertex(v);
				panel.recreatEdges(panel.vertexlist.get(oldvloc), v);
				//panel.removevertex(panel.vertexlist.get(oldvloc));
				secondclick = false;
				firstclick = true;
			}	
		}
		
		if(ggui.addEdge.isSelected()) {
			
			vertex v = new vertex(e.getX(),e.getY());
	
			if(firstclick && panel.exist(v) != -1) {
				vertex1 = panel.vertexlist.get(panel.exist(v));
				vertex1.setColor(Color.green);
				panel.repaint();
				firstclick = false;
				secondclick = true;
			}
			else if(secondclick && panel.exist(v) != -1) {
				vertex2 = panel.vertexlist.get(panel.exist(v));
				panel.addedge(vertex1, vertex2);
				vertex1.setColor(Color.red);
				panel.repaint();
				secondclick = false;
				firstclick = true;
			}
		}
		
		if(ggui.removeEdge.isSelected()) {
			panel.removeEdge(e.getX(),e.getY());
		}
	}
	public void clickreset() {
		firstclick = true;
		secondclick = false;
	}
}

