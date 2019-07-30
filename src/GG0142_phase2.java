
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class GG0142_phase2  extends JPanel {
	// dimension for frame
	public static final int WIDTH = 800, HEIGHT = 600;


	private JPanel MenuPanel;
	private displayPanel display;
	private JPanel containPanel;

	GG0142_phase2(){

		createPanel();
		creatButtons();	
		this.setBackground(Color.white);
	}


	public void createPanel() {
		containPanel = new JPanel();


		MenuPanel = new JPanel();
		MenuPanel.setPreferredSize(new Dimension(180, HEIGHT));
		MenuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		MenuPanel.setBorder(BorderFactory.createRaisedBevelBorder());


		display = new displayPanel();
		display.setPreferredSize(new Dimension(600, HEIGHT));
		display.setBackground(Color.cyan);
		display.addMouseListener(new myMouseAdapter(display,this));


		containPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		containPanel.add(MenuPanel);
		containPanel.add(display);

	}
	public void creatButtons() {
		//CREATE RADIOBUTTONS
		addVertex =  new JRadioButton("Add Vertex");
		addVertex.setSelected(true);

		addEdge = new JRadioButton("Add Edge");
		removeVertex = new JRadioButton("Remove Vertex");
		removeEdge = new JRadioButton("Remove Edge");
		moveVertex = new JRadioButton("Move Vertex");

		//CONTAIN RADIO BUTTONS IN BUTTON GROUP
		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(addVertex);
		bgroup.add(addEdge);
		bgroup.add(removeVertex);
		bgroup.add(removeEdge);
		bgroup.add(moveVertex);

		//ADD RADIO BUTTONS TO PANEL
		MenuPanel.add(addVertex);
		MenuPanel.add(addEdge);
		MenuPanel.add(removeVertex);
		MenuPanel.add(removeEdge);
		MenuPanel.add(moveVertex);

		//CREAT BUTTONS 
		addAllEdges = new JButton("Add all Edges");
		addAllEdges.setName("aae");
		addAllEdges.setPreferredSize(new Dimension(160, 40));
		connectComponent= new JButton("Connect Component");
		connectComponent.setPreferredSize(new Dimension(160, 40));
		showCutVert = new JButton("Show Cut Vertices");
		showCutVert.setPreferredSize(new Dimension(160, 40));
		help = new JButton("Help");
		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, helpString, "Help"
						, JOptionPane.INFORMATION_MESSAGE);
			}
		});
		help.setPreferredSize(new Dimension(160, 40));

		MenuPanel.add(addAllEdges);
		MenuPanel.add(connectComponent);
		MenuPanel.add(showCutVert);
		MenuPanel.add(help);

	}



	protected JRadioButton addVertex;
	protected JRadioButton addEdge;
	protected JRadioButton removeVertex;
	protected JRadioButton removeEdge;
	protected JRadioButton moveVertex;

	private JButton addAllEdges;
	private JButton	connectComponent;
	private JButton	showCutVert;
	private JButton	help;

	private String helpString = "1.  Add Verte: Adds on the right half of the gui "
			+ "by clicking the mouse on selected position.\n\n" +
			"2. Remove Vertex allows to remove a vertex"
			+ " by clicking on it.\n\n"+
			"3. Move Vertex allow  to select a vertex by clicking on it "
			+ "and then click at a new location to move the vertex.\n\n"+
			"4.Add Edge to add edges.\n\n"+
			"5.Remove Edge to remove selected edges.\n\n"+
			"6.Add All Edges is a shortcut that add in all "
			+ "possible edges between pairs of vertices.\n\n"+
			"7.Connected Components makes the gui show the different "
			+ "components of the graph in different colors.";

	public static void main(String[] args) {
		GG0142_phase2 ggui = new GG0142_phase2();
		JFrame frame;
		frame = new JFrame();
		frame.setTitle("GraphGui!!_Phase2");
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.getContentPane().add(ggui.containPanel);
		frame.setVisible(true);

	}

	///mouse listener and adapter
	class myMouseAdapter extends MouseAdapter {
		displayPanel panel = null;
		private GG0142_phase2 ggui;
		private int clickcount = 0;
		private int oldvloc  = 0;
		myMouseAdapter(displayPanel display, GG0142_phase2 ggui){
			panel = display;
			this.ggui = ggui;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.print("x:"+ e.getX() + "y:"+e.getY() );
			if(ggui.addVertex.isSelected())
				panel.addvertex(new vertex(e.getX(),e.getY()));
			if(ggui.removeVertex.isSelected())
				panel.removevertex(new vertex(e.getX(),e.getY()));

			if(ggui.moveVertex.isSelected()) {
				vertex v = new vertex(e.getX(),e.getY());
				if(clickcount == 0 && panel.exist(v) != -1 ) {
					oldvloc = panel.exist(v);
					clickcount++;
				}
				else if(clickcount ==1) {
					panel.addvertex(v);
					panel.removevertex(panel.vertexlist.remove(oldvloc));
					//panel.repaint();
					clickcount = 0;
				}	
			}	
		}
	}
	//end of mouse adapter class


	//vertex class

	class vertex {
		private int xLoc;
		private int yLoc;
		private final static int width = 20;
		private final static int height = 20;
		vertex(int x, int y){
			xLoc = x;
			yLoc = y;
		}
		public int getX() {return xLoc;};
		public int gety() {return yLoc;};
		public void draw(Graphics g) {
			g.fillOval(xLoc- width/2, yLoc-height/2, width, height);
			g.setColor(Color.black);
		}

		public Rectangle getBounds(){
			return new Rectangle( xLoc,yLoc, width, height);
		}

	}
	//end of vertex class

	//
	public class displayPanel extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		protected ArrayList<vertex> vertexlist = new ArrayList<vertex>();
		displayPanel(){
			super();
		}
		public void paint(Graphics g) {
			for(vertex x: vertexlist) {
				x.draw(g);
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

		public void addvertex(vertex e) {
			// TODO Auto-generated method stub

			if(exist(e) == -1 && !outofbound(e.getX(), e.gety())) {
				vertexlist.add(e);
				this.repaint();
			}

		}

		public void removevertex(vertex v) {
			if(exist(v)!= -1) {
				vertexlist.remove(exist(v));	
			}
			this.repaint();		
		}
		public boolean outofbound(int x, int y) {
			if(x-10 < (int)this.getAlignmentX() || y-10 < (int)this.getAlignmentY()) {
				return true;
			}
			return false;
		}

	}
	//end of displaypanel

}


