package com.owl.trees.draw;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.owl.regexparser.RecursiveDescentParser;
import com.owl.trees.ITreeNode;

public class WideLayoutPrinter implements ITreePrinter{

	private int XCOORDINATE;
	private int NODEID;
	private ArrayList<Node> _nodes;
	private ArrayList<Edge> _edges;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RecursiveDescentParser parser = new RecursiveDescentParser("(*(a.b))+[d-g]");
		ITreeNode root = parser.parse();
		ITreePrinter printer = new WideLayoutPrinter();
		printer.displayTree(root);
	}
	
	public WideLayoutPrinter(){
		this.XCOORDINATE = 0;
		this.NODEID = 0;
		this._nodes = new ArrayList<Node>();
		this._edges = new ArrayList<Edge>();
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
		XCOORDINATE = 50;
		int yCoordinate = 50;
		inOrderTraversal(root, yCoordinate);
		
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

	private Node inOrderTraversal(ITreeNode root, int yCoordinate) {
		// TODO Auto-generated method stub
		
		if(root == null){ // if there isn't any tree, return null
			return null;
		}
		
		if(root.GetSingleChildNode()!=null){ // if the node is of type ParseTreeSTARNode
			//Start the inorder traversal from the singleCHildNode
			//and get back its representation in the WideLayoutPrinter.Node class
			Node singleChildNode = 
					inOrderTraversal(root.GetSingleChildNode(), yCoordinate+50);
			
			Node rootNode = new Node(root.GetValue(), XCOORDINATE, yCoordinate);
			this.addNode(rootNode);
			
			// Since the current node is processed, update the XCOORDINATE
			XCOORDINATE += 50;
			
			//add edge
			if(singleChildNode!=null){
				Edge edge = new Edge(rootNode.id, singleChildNode.id);
				this.addEdge(edge);
			}
			return rootNode;
		}else{
			Node leftNode = inOrderTraversal(root.GetLeftNode(), yCoordinate+50);
			
			Node rootNode = new Node(root.GetValue(), XCOORDINATE, yCoordinate);
			this.addNode(rootNode);
			
			XCOORDINATE += 50;
			
			//add edge
			if(leftNode!=null){
				Edge edge = new Edge(rootNode.id, leftNode.id);
				this.addEdge(edge);
			}
			
			Node rightNode = inOrderTraversal(root.GetRightNode(), yCoordinate+50);
			
			//add edge
			if(rightNode!=null){
				Edge edge = new Edge(rootNode.id, rightNode.id);
				this.addEdge(edge);
			}
			
			return rootNode;
		}
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