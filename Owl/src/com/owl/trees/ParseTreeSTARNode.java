package com.owl.trees;

import com.owl.automata.IFiniteAutomata;
import com.owl.automata.*;

public class ParseTreeSTARNode extends ParseTreeNode{
	
	private ParseTreeNode _node;
	
	public ParseTreeSTARNode(ParseTreeNode node){
		this._node = node;
		this.Operator = ParseTreeNode.OPERATORTYPE.STAR;
	}

	@Override
	public ITreeNode GetLeftNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITreeNode GetRightNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String GetValue() {
		// TODO Auto-generated method stub
		return "*";
	}

	@Override
	public void PrintInorderTraversal() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PrintPreorderTraversal() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PrintPostOrderTraversal() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ITreeNode GetSingleChildNode() {
		// TODO Auto-generated method stub
		return this._node;
	}

	@Override
	public IFiniteAutomata GetEquivalentNFA() {
		// TODO Auto-generated method stub
		if(this._node != null){
			NFA nodeNFA = (NFA) this._node.GetEquivalentNFA();
			NFA nodeNFARepetition = NFA.constructRepetitionNFA(nodeNFA);
			return nodeNFARepetition;
		}else{
			return null;
		}
	}
	
}