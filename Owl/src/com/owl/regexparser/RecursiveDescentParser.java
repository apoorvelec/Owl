package com.owl.regexparser;

import com.owl.automata.IFiniteAutomata;
import com.owl.trees.*;

public class RecursiveDescentParser implements IRegexParser{

	private String _input;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RecursiveDescentParser parser = new RecursiveDescentParser("a.b");
		ITreeNode root = parser.parse();
		IFiniteAutomata nfa = root.GetEquivalentNFA();
		Boolean ans = nfa.matchesString("ab");
		System.out.println(ans);
		ans = nfa.matchesString("abab");
		System.out.println(ans);
	}
	
	public RecursiveDescentParser(String input){
		this._input = input;
	}
	
	private String eatNextCharacterInString(){
		String nextCharacter = this._input.substring(0, 1);
		this._input = this._input.substring(1);
		return nextCharacter;
	}
	
	private String peekNextCharacterInString(){
		String nextCharacter = this._input.substring(0, 1);
		return nextCharacter;
	}
	
	private boolean inputHasMoreUnseenCharacters(){
		boolean result = this._input.length()>0;
		return result;
	}

	@Override
	public ITreeNode parse() {
		// TODO Auto-generated method stub
		return REGEX();
	}
	
	public ParseTreeNode REGEX(){
		ParseTreeNode term = TERM();
		
		if(inputHasMoreUnseenCharacters() && peekNextCharacterInString().equals("+")){
			eatNextCharacterInString();//eats '+'
			ParseTreeNode regex = REGEX();
			return new ParseTreeORNode(term, regex);
		}
		
		return term;
	}
	
	public ParseTreeNode TERM(){
		ParseTreeNode factor = FACTOR();
		
		if(inputHasMoreUnseenCharacters() && peekNextCharacterInString().equals(".")){
			eatNextCharacterInString();//eats '.'
			ParseTreeNode term = TERM();
			return new ParseTreeANDNode(factor, term);
		}
		
		return factor;
	}
	
	public ParseTreeNode FACTOR(){
		String nextCharacterInput = eatNextCharacterInString();
		ParseTreeNode node = null;
		if(nextCharacterInput.equals("(")){
			//eatNextCharacterInString(); //eats '('
			node = REGEX();
			eatNextCharacterInString(); //eats ')'
		}else if( (nextCharacterInput.equals("a")||
				nextCharacterInput.equals("b")||
				nextCharacterInput.equals("c")||
				nextCharacterInput.equals(""))){
			//String nextCharacter = eatNextCharacterInString(); //eats next alphabet
			return new ParseTreeSingleValueNode(nextCharacterInput);
		}else if(nextCharacterInput.equals("*")){
			//eatNextCharacterInString(); //eats '*'
			node = FACTOR();
			return new ParseTreeSTARNode(node);
		}
		return node;
	}

}
