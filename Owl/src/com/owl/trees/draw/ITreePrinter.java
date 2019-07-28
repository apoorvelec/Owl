package com.owl.trees.draw;

import javax.swing.JFrame;

import com.owl.trees.IParseTreeNode;
import com.owl.trees.ITreeNode;

public interface ITreePrinter {

	public void displayTree(ITreeNode root);
	public void displayTree(IParseTreeNode root);
}
