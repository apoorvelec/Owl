package com.owl.trees;

import com.owl.automata.IFiniteAutomata;
import com.owl.automata.NFA;

public class ParseTreeORNode extends ParseTreeNode{
	private ParseTreeNode _leftNode;
	private ParseTreeNode _rightNode;
	
	public ParseTreeORNode(ParseTreeNode leftNode, ParseTreeNode rightNode){
		this._leftNode = leftNode;
		this._rightNode = rightNode;
		this.Operator = ParseTreeNode.OPERATORTYPE.OR;
	}

	@Override
	public ITreeNode GetLeftNode() {
		// TODO Auto-generated method stub
		return this._leftNode;
	}

	@Override
	public ITreeNode GetRightNode() {
		// TODO Auto-generated method stub
		return this._rightNode;
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
	public String GetValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITreeNode GetSingleChildNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFiniteAutomata GetEquivalentNFA() {
		// TODO Auto-generated method stub
		NFA leftNodeNFA = null;
		if(this._leftNode != null){
			leftNodeNFA = (NFA) this._leftNode.GetEquivalentNFA();
		}
		
		NFA rightNodeNFA = null;
		if(this._rightNode != null){
			rightNodeNFA = (NFA) this._rightNode.GetEquivalentNFA();
		}
		
		NFA leftNFAOrRightNFA = NFA.constructORNFA(leftNodeNFA, rightNodeNFA);
		
		return leftNFAOrRightNFA;
	}
}