package com.owl.regexparser;

import java.util.Arrays;
import java.util.HashSet;

import com.owl.automata.IFiniteAutomata;
import com.owl.trees.*;

public class RecursiveDescentParser implements IRegexParser{
	
	private static final HashSet<Character> _keyWords = 
			new HashSet<Character>(Arrays.asList('+', '.', '*', '(', ')', '#', '[', ']', '-'));

	private String _input;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RecursiveDescentParser parser = new RecursiveDescentParser("[!-##]");
		ITreeNode root = parser.parse();
		IFiniteAutomata nfa = root.GetEquivalentNFA();
		Boolean ans = nfa.matchesString("#");
		System.out.println(ans);
		ans = nfa.matchesString("a");
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
		}else if(nextCharacterInput.equals("*")){
			//eatNextCharacterInString(); //eats '*'
			node = FACTOR();
			return new ParseTreeSTARNode(node);
		}else if(nextCharacterInput.equals("[")){
			
			// fromChar variable value may be different 
			// than the value held by fromCharNode in the 
			// case when regex pattern contains character escaping
			String fromChar = eatNextCharacterInString();
			ParseTreeNode fromCharNode = ALPHABET(fromChar);
			
			String dash = eatNextCharacterInString();
			
			String toChar = eatNextCharacterInString();
			ParseTreeNode toCharNode = ALPHABET(toChar);
			
			int fromCharAscii = fromCharNode.GetValue().charAt(0);
			int toCharAscii = toCharNode.GetValue().charAt(0);
			if(fromCharAscii<=toCharAscii){
				String regexPattern = "";
				if(!this._keyWords.contains(fromCharNode.GetValue().charAt(0))){
					regexPattern = Character.toString((char)fromCharAscii);
				}else{
					regexPattern = "#"+
							Character.toString((char)fromCharAscii);
				}
				
				fromCharAscii++;
				while(fromCharAscii<=toCharAscii){
					if(!this._keyWords.contains((char)fromCharAscii)){
						regexPattern += "+" + Character.toString((char)fromCharAscii);
					}else{
						regexPattern += "+#" + Character.toString((char)fromCharAscii);
					}
					
					fromCharAscii++;
				}
				
				RecursiveDescentParser parser = new RecursiveDescentParser(regexPattern);
				node = (ParseTreeNode) parser.parse();
			}
			eatNextCharacterInString(); // eats ']'
		}else if(!_keyWords.contains((Character)nextCharacterInput.charAt(0))
				|| nextCharacterInput.charAt(0)=='#'){
			node = ALPHABET(nextCharacterInput);
		}
		return node;
	}
	
	public ParseTreeNode ALPHABET(String nextCharacterInput){
		ParseTreeNode node = null;
		if(nextCharacterInput.equals("#")){
			nextCharacterInput = eatNextCharacterInString(); //eats next char
		}
		return new ParseTreeSingleValueNode(nextCharacterInput);
	}

}
