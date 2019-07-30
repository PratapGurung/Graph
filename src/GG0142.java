
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class GG0142  extends JPanel {
	// dimension for frame
	public static final int WIDTH = 1000, HEIGHT = 800;


	private JPanel MenuPanel;
	private displayPanel display;
	private JPanel containPanel;

	GG0142(){

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
		display.setPreferredSize(new Dimension(800, HEIGHT));
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
		addAllEdges.setPreferredSize(new Dimension(160, 40));
		addAllEdges.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				display.addAllEdges();
			}
		});
		connectComponent= new JButton("Connect Component");
		connectComponent.setPreferredSize(new Dimension(160, 40));
		connectComponent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				display.showComponents();
			}
		});

		showCutVert = new JButton("Show Cut Vertices");
		showCutVert.setPreferredSize(new Dimension(160, 40));
		showCutVert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				display.cutVertices();
			}
		});
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
		Clear = new JButton("Clear");
		Clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				display.clear();
			}
		});
		Clear.setPreferredSize(new Dimension(160, 40));

		MenuPanel.add(addAllEdges);
		MenuPanel.add(connectComponent);
		MenuPanel.add(showCutVert);
		MenuPanel.add(help);
		MenuPanel.add(Clear);


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
	private JButton	Clear;

	private String helpString = "1.  Add Verte: Adds on the right half of the gui "
			+ "by clicking the mouse on selected position.\n\n" +
			"2. Remove Vertex allows to remove a vertex"
			+ " by clicking on it.\n\n"+
			"3. Move Vertex allow  to select a vertex by clicking on it "
			+ "and then click at a new location to move the vertex.\n\n"+
			"4.Add Edge to add edges.\n\n"+
			"5.Remove Edge to remove selected edges BUT TO SELECT EDGES YOU TO SELECT TWO CORRESPONDING VERTICES.\n\n"+
			"6.Add All Edges is a shortcut that add in all "
			+ "possible edges between pairs of vertices.\n\n"+
			"7.Connected Components makes the gui show the different "
			+ "components of the graph in different colors.";

	public static void main(String[] args) {
		GG0142 ggui = new GG0142();
		JFrame frame;
		frame = new JFrame();
		frame.setTitle("GraphGui!!:Phase 3");
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.getContentPane().add(ggui.containPanel);
		frame.setVisible(true);

	}
}
