package com.owl.trees.draw;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.owl.regexparser.RecursiveDescentParser;
import com.owl.trees.IParseTreeNode;
import com.owl.trees.ITreeNode;

public class LowWidthLayoutPrinter implements ITreePrinter{

	private int NODEID;
	private ArrayList<Node> _nodes;
	private ArrayList<Edge> _edges;
	
	private HashMap<Integer, Integer> _nextAvailableXSlot; // key is depth, value is X position
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		RecursiveDescentParser parser = new RecursiveDescentParser("(*(a.b))+[d-g]");
//		ITreeNode root = parser.parse();
//		ITreePrinter printer = new LowWidthLayoutPrinter();
//		printer.displayTree(root);
		
		IParseTreeNode node = new IParseTreeNode() {
			
			@Override
			public String getValue() {
				// TODO Auto-generated method stub
				return "root";
			}
			
			@Override
			public List<IParseTreeNode> getAllChildNodes() {
				// TODO Auto-generated method stub
				
				ArrayList<IParseTreeNode> list = new ArrayList<IParseTreeNode>();
				
				list.add(new IParseTreeNode() {
					
					@Override
					public String getValue() {
						// TODO Auto-generated method stub
						return "child1";
					}
					
					@Override
					public List<IParseTreeNode> getAllChildNodes() {
						// TODO Auto-generated method stub
						return null;
					}
				});
				
				list.add(new IParseTreeNode() {
					
					@Override
					public String getValue() {
						// TODO Auto-generated method stub
						return "child2";
					}
					
					@Override
					public List<IParseTreeNode> getAllChildNodes() {
						// TODO Auto-generated method stub
						return null;
					}
				});

				list.add(new IParseTreeNode() {
					
					@Override
					public String getValue() {
						// TODO Auto-generated method stub
						return "child3";
					}
					
					@Override
					public List<IParseTreeNode> getAllChildNodes() {
						// TODO Auto-generated method stub
						ArrayList<IParseTreeNode> list = new ArrayList<IParseTreeNode>();
						
						list.add(new IParseTreeNode() {
							
							@Override
							public String getValue() {
								// TODO Auto-generated method stub
								return "root";
							}
							
							@Override
							public List<IParseTreeNode> getAllChildNodes() {
								// TODO Auto-generated method stub
								
								ArrayList<IParseTreeNode> list = new ArrayList<IParseTreeNode>();
								
								list.add(new IParseTreeNode() {
									
									@Override
									public String getValue() {
										// TODO Auto-generated method stub
										return "child1";
									}
									
									@Override
									public List<IParseTreeNode> getAllChildNodes() {
										// TODO Auto-generated method stub
										return null;
									}
								});
								
								list.add(new IParseTreeNode() {
									
									@Override
									public String getValue() {
										// TODO Auto-generated method stub
										return "child2";
									}
									
									@Override
									public List<IParseTreeNode> getAllChildNodes() {
										// TODO Auto-generated method stub
										return null;
									}
								});

								list.add(new IParseTreeNode() {
									
									@Override
									public String getValue() {
										// TODO Auto-generated method stub
										return "child3";
									}
									
									@Override
									public List<IParseTreeNode> getAllChildNodes() {
										// TODO Auto-generated method stub
										return null;
									}
								});
								
								return list;
							}
						});
						return list;
					}
				});
				
				return list;
			}
			
		};
		ITreePrinter printer1 = new LowWidthLayoutPrinter();
		printer1.displayTree(node);
	}
	
	public LowWidthLayoutPrinter(){
		this.NODEID = 0;
		this._nodes = new ArrayList<Node>();
		this._edges = new ArrayList<Edge>();
		this._nextAvailableXSlot = new HashMap<Integer, Integer>();
	}
	
	private class Node{
		public int id;
		public String label;
		public int xCoordinate;
		int yCoordinate;
		
		public Node(String label, int xCoordinate, int yCoordinate){
			this.label = label;
			this.xCoordinate = xCoordinate;
			this.yCoordinate = yCoordinate;
			id = NODEID++; // automatically assign the id
		}
	}
	
	private class Edge{
		public int fromNodeId;
		public int toNodeId;
		
		public Edge(int fromNodeId, int toNodeId){
			this.fromNodeId = fromNodeId;
			this.toNodeId = toNodeId;
		}
	}

	@Override
	public void displayTree(ITreeNode root) {
		// TODO Auto-generated method stub
		int yCoordinate = 50;
		preOrderTraversal(root, yCoordinate);
		
		TreeViewer frame = new TreeViewer("Test Window");
		//frame.setSize(2000,2000);
    	
    	frame.setVisible(true);
		for(Node node : _nodes){
			frame.addNode(node.label, node.xCoordinate, node.yCoordinate);
		}
		
		for(Edge edge : _edges){
			frame.addEdge(edge.fromNodeId, edge.toNodeId);
		}
		//frame.pack();
		
		BufferedImage bi = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_BGR);
		Graphics2D graphics = bi.createGraphics();
		frame.print(graphics);
		try {
			ImageIO.write(bi, "jpg", new File("img.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		graphics.dispose();
		
		frame.show();
	}

	private Node preOrderTraversal(ITreeNode root, int yCoordinate) {
		// TODO Auto-generated method stub
		
		if(root == null){ // if there isn't any tree, return null
			return null;
		}
		Node rootNode = null;
		if(this._nextAvailableXSlot.keySet().contains(yCoordinate)){
			int xCoordinate = this._nextAvailableXSlot.get(yCoordinate);
			rootNode = new Node(root.GetValue(), xCoordinate, yCoordinate);
			this._nextAvailableXSlot.put(yCoordinate, xCoordinate + 50);
		}else{
			int xCoordinate = 50;
			rootNode = new Node(root.GetValue(), xCoordinate, yCoordinate);
			this._nextAvailableXSlot.put(yCoordinate, xCoordinate + 50);
		}
		this.addNode(rootNode);
		
		if(root.GetSingleChildNode()!=null){ // if the node is of type ParseTreeSTARNode
			//Start the preorder traversal from the singleChildNode
			//and get back its representation in the LowWidthLayoutPrinter.Node class
			Node singleChildNode = 
					preOrderTraversal(root.GetSingleChildNode(), yCoordinate+50);
			
			//add edge
			if(singleChildNode!=null){
				Edge edge = new Edge(rootNode.id, singleChildNode.id);
				this.addEdge(edge);
			}
			return rootNode;
		}else{
			Node leftNode = preOrderTraversal(root.GetLeftNode(), yCoordinate+50);
			
			//add edge
			if(leftNode!=null){
				Edge edge = new Edge(rootNode.id, leftNode.id);
				this.addEdge(edge);
			}
			
			Node rightNode = preOrderTraversal(root.GetRightNode(), yCoordinate+50);
			
			//add edge
			if(rightNode!=null){
				Edge edge = new Edge(rootNode.id, rightNode.id);
				this.addEdge(edge);
			}
			
			return rootNode;
		}
	}
	
	@Override
	public void displayTree(IParseTreeNode root) {
		// TODO Auto-generated method stub
		int yCoordinate = 50;
		preOrderTraversal(root, yCoordinate);
		
		TreeViewer frame = new TreeViewer("Test Window");
		//frame.setSize(2000,2000);
    	
    	frame.setVisible(true);
		for(Node node : _nodes){
			frame.addNode(node.label, node.xCoordinate, node.yCoordinate);
		}
		
		for(Edge edge : _edges){
			frame.addEdge(edge.fromNodeId, edge.toNodeId);
		}
		//frame.pack();
		
		BufferedImage bi = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_BGR);
		Graphics2D graphics = bi.createGraphics();
		frame.print(graphics);
		try {
			ImageIO.write(bi, "jpg", new File("img.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		graphics.dispose();
		
		frame.show();
	}
	
	private Node preOrderTraversal(IParseTreeNode root, int yCoordinate) {
		// TODO Auto-generated method stub
		
		if(root == null){ // if there isn't any tree, return null
			return null;
		}
		Node rootNode = null;
		if(this._nextAvailableXSlot.keySet().contains(yCoordinate)){
			int xCoordinate = this._nextAvailableXSlot.get(yCoordinate);
			rootNode = new Node(root.getValue(), xCoordinate, yCoordinate);
			this._nextAvailableXSlot.put(yCoordinate, xCoordinate + 50);
		}else{
			int xCoordinate = 50;
			rootNode = new Node(root.getValue(), xCoordinate, yCoordinate);
			this._nextAvailableXSlot.put(yCoordinate, xCoordinate + 50);
		}
		this.addNode(rootNode);
		
		List<? extends IParseTreeNode> allChildNodes = root.getAllChildNodes();
		if(allChildNodes == null){
			return rootNode;
		}
		for(IParseTreeNode node: allChildNodes){
			Node childNode = preOrderTraversal(node, yCoordinate+50);
			
			//add edge
			if(childNode!=null){
				Edge edge = new Edge(rootNode.id, childNode.id);
				this.addEdge(edge);
			}
		}
		
		return rootNode;
	}
	
	private void addNode(Node node){
		if(_nodes!=null){
			_nodes.add(node);
		}else{
			_nodes = new ArrayList<Node>();
			_nodes.add(node);
		}
	}
	
	private void addEdge(Edge edge){
		if(_edges!=null){
			_edges.add(edge);
		}else{
			_edges = new ArrayList<Edge>();
			_edges.add(edge);
		}
	}

}
