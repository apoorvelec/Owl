package com.owl.automata;

public class NFA implements IFiniteAutomata{

	NFANode _startNode;
	NFANode _endNode;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		NFA a = NFA.constructSingleCharacterNFA('a');
		NFA b = NFA.constructSingleCharacterNFA('b');
		NFA ab = NFA.constructANDNFA(a, b);
		NFA abrep = NFA.constructRepetitionNFA(ab);
		
		System.out.println(abrep.matchesString("abab"));
	}
	
	public NFA(NFANode startNode, NFANode endNode){
		this._startNode = startNode;
		this._endNode = endNode;
	}
	
	@Override
	public Boolean matchesString(String s){
		return this._startNode.matches(s);
	}
	
	public static NFA constructSingleCharacterNFA(Character c){
		NFANode start = new NFANode();
		NFANode end = new NFANode();
		
		start.addCharacterEdge(c, end);
		end._isFinalNode = true;
		
		return new NFA(start, end);
	}
	
	public static NFA constructRepetitionNFA(NFA nfa){
		Character epsilon = Character.MIN_VALUE;
		nfa._startNode.addCharacterEdge(epsilon, nfa._endNode);
		nfa._endNode.addCharacterEdge(epsilon, nfa._startNode);
		return nfa;
	}
	
	public static NFA constructORNFA(NFA nfa1, NFA nfa2){
		NFANode start = new NFANode();
		NFANode end = new NFANode();
		
		Character epsilon = Character.MIN_VALUE;
		start.addCharacterEdge(epsilon, nfa1._startNode);
		start.addCharacterEdge(epsilon, nfa2._startNode);
		
		nfa1._endNode.addCharacterEdge(epsilon, end);
		nfa2._endNode.addCharacterEdge(epsilon, end);
		
		nfa1._endNode._isFinalNode = false;
		nfa2._endNode._isFinalNode = false;
		
		end._isFinalNode = true;
		
		return new NFA(start, end);
	}
	
	public static NFA constructANDNFA(NFA nfa1, NFA nfa2){
		NFANode start = new NFANode();
		NFANode end = new NFANode();
		
		Character epsilon = Character.MIN_VALUE;
		
		start.addCharacterEdge(epsilon, nfa1._startNode);
		nfa1._endNode._isFinalNode = false;
		nfa1._endNode.addCharacterEdge(epsilon, nfa2._startNode);
		nfa2._endNode._isFinalNode = false;
		nfa2._endNode.addCharacterEdge(epsilon, end);
		
		end._isFinalNode = true;
		
		return new NFA(start, end);
	}
	
	
	
}
