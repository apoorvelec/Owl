package com.owl.trees;

import com.owl.automata.IFiniteAutomata;
import com.owl.automata.NFA;

public class ParseTreeSingleValueNode extends ParseTreeNode{
	
	private String _character;
	
	public ParseTreeSingleValueNode(String character){
		this._character = character;
		this.Operator = ParseTreeNode.OPERATORTYPE.SINGLEVALUE;
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
		return this._character;
	}

	@Override
	public ITreeNode GetSingleChildNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFiniteAutomata GetEquivalentNFA() {
		// TODO Auto-generated method stub
		if(this._character.length() == 1){
			Character c = new Character(this._character.charAt(0));
			NFA singleCharacterNFA = NFA.constructSingleCharacterNFA(c);
			return singleCharacterNFA;
		}else{
			return null; // this means trouble
		}
	}
	
}