package com.owl.automata;

import java.util.ArrayList;
import java.util.HashMap;

public class NFANode {
	
	public static int TOTALNUMBEROFNODES = 0;
	public HashMap<Character, ArrayList<NFANode>> _onCharInput;
	public boolean _isFinalNode;
	private int _nodeID;
	private Boolean _stringMatchResultedInDeadNode;

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
		this._stringMatchResultedInDeadNode = false;
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
			
			//Add dead end code here
			//if there are no possible c transitions AND if there are
			//no more epsilon transitions as well, then we have reached a dead state.
			//if there are some epsilon transitions, check if each epsilon-transition
			//node is a dead node for Character c input. if it is then this is truly
			//a dead node.
			if(s!=null && s.length()>0){ 
				ArrayList<NFANode> nextNodesOnCharInput = this._onCharInput.get(c);
				ArrayList<NFANode> nextNodesOnEpsilonInput = this._onCharInput.get(Character.MIN_VALUE);
				
				if((nextNodesOnCharInput == null || 
						(nextNodesOnCharInput!=null && 
						nextNodesOnCharInput.size() == 0))
						&&
						(nextNodesOnEpsilonInput == null || 
						(nextNodesOnEpsilonInput!=null && 
						nextNodesOnEpsilonInput.size() == 0))){
					
					//This means that we can go nowhere from the current node.
					//So this is a dead node
					this._stringMatchResultedInDeadNode = true;/*else{
						this._stringMatchResultedInDeadNode = true;
						for(NFANode node: this._onCharInput.get(Character.MIN_VALUE)){
							if(node!=null){
								this._stringMatchResultedInDeadNode = this._stringMatchResultedInDeadNode 
										&& node.stringMatchResultedInDeadNode();
							}
							
						}
					}*/
					
				}else{
					this._stringMatchResultedInDeadNode = true;
					try{
						for(NFANode node: this._onCharInput.get(Character.MIN_VALUE)){
							this._stringMatchResultedInDeadNode = this._stringMatchResultedInDeadNode
									&& node.stringMatchResultedInDeadNode();
						}
					}catch(Exception e){
						//e.printStackTrace();
						//System.out.println("Handled Exception");
					}
					
					try{
						for(NFANode node: this._onCharInput.get(c)){
							this._stringMatchResultedInDeadNode = this._stringMatchResultedInDeadNode
									&& node.stringMatchResultedInDeadNode();
						}
					}catch(Exception e){
						//e.printStackTrace();
						//System.out.println("Handled Exception");
					}
				}
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
	
	public Boolean stringMatchResultedInDeadNode(){
		return this._stringMatchResultedInDeadNode;
	}

}
