package com.owl.trees;

import com.owl.automata.*;

public interface ITreeNode {
	
	public ITreeNode GetLeftNode();
	public ITreeNode GetRightNode();
	public String GetValue();
	public ITreeNode GetSingleChildNode(); // in case of the STAR node
	
	
	public void PrintInorderTraversal();
	public void PrintPreorderTraversal();
	public void PrintPostOrderTraversal();
	
	public IFiniteAutomata GetEquivalentNFA();
}