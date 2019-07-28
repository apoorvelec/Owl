package com.owl.trees;

import java.util.List;

/**
 * Represents a node which can have multiple children
 * @author Apoorv
 */
public interface IParseTreeNode {
	
	public List<? extends IParseTreeNode> getAllChildNodes();
	public String getValue();
	
}
