package com.owl.trees.draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class TreeViewer extends JFrame{
	
	private int _nodeWidth;
	private int _nodeHeight;
	
	private int _contentWidth;
	private int _contentHeight;
	
	private ArrayList<Node> _nodes;
    private ArrayList<Edge> _edges;
    
    private JScrollPane _scrollContainer;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public TreeViewer() { //Constructor
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this._nodes = new ArrayList<Node>();
		this._edges = new ArrayList<Edge>();
		this._nodeWidth = 30;
		this._nodeHeight = 30;
    }
	
	public TreeViewer(String name) { //Construct with label
		this._scrollContainer= new ScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setContentPane(this._scrollContainer);
		this.setTitle(name);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this._nodes = new ArrayList<Node>();
		this._edges = new ArrayList<Edge>();
		this._nodeWidth = 30;
		this._nodeHeight = 30;
		this._contentWidth = 0;
		this._contentHeight = 0;
	}
	
	private class ScrollPane extends JScrollPane{
		
		public ScrollPane(int vsbPolicy, int hsbPolicy){
			super(vsbPolicy, hsbPolicy);
		}
		
		@Override
		public void paint(Graphics g){
			this.setBackground(Color.BLUE);
			for (Edge e : _edges) {
			    g.drawLine(_nodes.get(e.fromNode).xCoordinate, _nodes.get(e.fromNode).yCoordinate,
				     _nodes.get(e.toNode).xCoordinate, _nodes.get(e.toNode).yCoordinate);
			}
			
			for(Node n : _nodes){
				n.paint(g);
			}
		}
		
	}
	
	private class Node extends JComponent{
		
		private static final long serialVersionUID = 1L;
		
		public int xCoordinate;
		public int yCoordinate;
		public String label;
		
		public Node(String label, int xCoordinate, int yCoordinate) {
		    this.xCoordinate = xCoordinate;
		    this.yCoordinate = yCoordinate;
		    this.label = label;
		}
		
		@Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        FontMetrics f = g.getFontMetrics();
	    	int nodeHeight = Math.max(_nodeHeight, f.getHeight());
	    	g.setColor(Color.black);
	    	
	    	int nodeWidth = 
	    			Math.max(_nodeWidth, f.stringWidth(this.label)+_nodeWidth/2);
		    g.setColor(Color.WHITE);
		    
		    g.fillOval(this.xCoordinate-nodeWidth/2, this.yCoordinate-nodeHeight/2, 
			       nodeWidth, nodeHeight);
		    
		    g.setColor(Color.black);
		    g.drawOval(this.xCoordinate-nodeWidth/2, this.yCoordinate-nodeHeight/2, 
			       nodeWidth, nodeHeight);
		    
		    g.drawString(this.label, this.xCoordinate-f.stringWidth(this.label)/2,
		    		this.yCoordinate+f.getHeight()/2);
	    }

	    @Override
	    public void paint(Graphics g) {
	        super.paint(g);
	        paintComponent(g);
	        paintBorder(g);
	        paintChildren(g);
	    }
	    
	    @Override
	    public Dimension getPreferredSize() {
	        return new Dimension(_nodeWidth, _nodeHeight);
	    }
	}
	
	private class Edge {
		public int fromNode;
		public int toNode;
		
		public Edge(int fromNode, int toNode) {
		    this.fromNode = fromNode;
		    this.toNode = toNode;
		}
	}
	
	public void addNode(String label, int xCoordinate, int yCoordinate) { 
		//add a node at pixel (xCoordinate,yCoordinate)
		this._nodes.add(new Node(label, xCoordinate, yCoordinate));
		if(this._contentWidth < xCoordinate){
			this._contentWidth = xCoordinate;
		}
		
		if(this._contentHeight < yCoordinate){
			this._contentHeight = yCoordinate;
		}
		this.repaint();
	}
	
	public void addEdge(int fromNode, int toNode) {
		//add an edge between node with id fromNode to node with id toNode
		this._edges.add(new Edge(fromNode, toNode));
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g){
		/*for (Edge e : _edges) {
		    g.drawLine(_nodes.get(e.fromNode).xCoordinate, _nodes.get(e.fromNode).yCoordinate,
			     _nodes.get(e.toNode).xCoordinate, _nodes.get(e.toNode).yCoordinate);
		}
		
		for(Node n : _nodes){
			n.paint(g);
		}*/
		this.setSize(this._contentWidth + 100, this._contentHeight + 100);
		this.getContentPane().setBackground(Color.YELLOW);
		this._scrollContainer.paint(g);
	}
	
	@Override
	public int getWidth(){
		return this._contentWidth + 100;
	}
	
	@Override
	public int getHeight(){
		return this._contentHeight + 100;
	}
	
}
