package com.owl.automata;

import java.util.ArrayList;
import java.util.HashMap;

public class NFANode {
	
	public static int TOTALNUMBEROFNODES = 0;
	public HashMap<Character, ArrayList<NFANode>> _onCharInput;
	public boolean _isFinalNode;
	private int _nodeID;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public NFANode(){
		this._onCharInput = new HashMap<Character, ArrayList<NFANode>>();
		this._isFinalNode = false;
		this._nodeID = this.TOTALNUMBEROFNODES++;
	}
	
	public void addCharacterEdge(Character c, NFANode node){
		if(this._onCharInput.containsKey(c)){
			ArrayList<NFANode> list = this._onCharInput.get(c);
			if(!list.contains(node)){
				list.add(node);
			}
			this._onCharInput.put(c, list);
		}else{
			ArrayList<NFANode> list = new ArrayList<NFANode>();
			list.add(node);
			this._onCharInput.put(c, list);
		}
	}
	
	public boolean matches(String s){
		return matches(s, new ArrayList<NFANode>());
	}
	
	public boolean matches(String s, ArrayList<NFANode> visited){
		if(visited.contains(this)){
			return false;
		}
		
		visited.add(this);
		
		if(s.length() == 0){
			if(this._isFinalNode){
				return true;
			}
			try{
				for(NFANode node: this._onCharInput.get(Character.MIN_VALUE)){
					if(node.matches("", visited)){
						return true;
					}
				}
			}catch(Exception e){
				//e.printStackTrace();
				//System.out.println("Handled Exception");
			}
			
			return false;
		}else{
			Character c = s.charAt(0);
			try{
				for(NFANode node: this._onCharInput.get(c)){
					if(node.matches(s.substring(1))){
						return true;
					}
				}
			}catch(Exception e){
				//e.printStackTrace();
				//System.out.println("Handled Exception");
			}
			
			
			try{
				for(NFANode node: this._onCharInput.get(Character.MIN_VALUE)){
					if(node.matches(s, visited)){
						return true;
					}
				}
			}catch(Exception e){
				//e.printStackTrace();
				//System.out.println("Handled Exception");
			}
			
			return false;
		}
	}
	
	@Override
	public boolean equals(Object node){
		if(!(node instanceof NFANode)){
			return false;
		}
		if(((NFANode)node)._nodeID == this._nodeID){
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return this._nodeID;
	}

}
