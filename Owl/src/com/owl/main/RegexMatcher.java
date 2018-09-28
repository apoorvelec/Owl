package com.owl.main;

import com.owl.automata.*;
import com.owl.regexparser.*;
import com.owl.trees.*;

public class RegexMatcher {

	private String _regexString;
	private IRegexParser _parser;
	private ITreeNode _parseTreeRoot;
	private IFiniteAutomata _underlyingAutomata;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RecursiveDescentParser parser = new RecursiveDescentParser("#(.#(");
		ITreeNode root = parser.parse();
		IFiniteAutomata nfa = root.GetEquivalentNFA();
		Boolean ans = ((NFA)nfa).stringSearchHitDeadNode();
		System.out.println(ans);
		ans = nfa.matchesString("((");
		System.out.println(ans);
		ans = nfa.matchesString("a");
		System.out.println(((NFA)nfa).stringSearchHitDeadNode());
		ans = nfa.matchesString("(");
		System.out.println(((NFA)nfa).stringSearchHitDeadNode());
	}
	
	//default parser is the recursive descent parser
	public RegexMatcher(String inputRegex){
		this._regexString = inputRegex;
		this._parser = new RecursiveDescentParser(this._regexString);
		this._parseTreeRoot = this._parser.parse();
		this._underlyingAutomata = this._parseTreeRoot.GetEquivalentNFA();
	}

	public boolean matchesString(String inputString){
		//trivial case
		if(inputString == null){
			return false;
		}
		
		if(this._underlyingAutomata != null){
			boolean result = this._underlyingAutomata.matchesString(inputString);
			return result;
		}
		
		//inputString is not null and _underlyingAutomata is null
		return false;
	}
	
	public Boolean stringMatchHitDeadNode(){
		if(this._underlyingAutomata!=null){
			return ((NFA)this._underlyingAutomata).stringSearchHitDeadNode();
		}
		
		return null;
	}
	
}
