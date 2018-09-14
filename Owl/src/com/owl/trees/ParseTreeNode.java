package com.owl.trees;

public abstract class ParseTreeNode implements ITreeNode{
	
	public enum OPERATORTYPE{
		OR, AND, STAR, SINGLEVALUE
	}
	
	public OPERATORTYPE Operator;

}
