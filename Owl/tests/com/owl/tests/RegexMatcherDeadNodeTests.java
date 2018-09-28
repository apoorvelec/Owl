package com.owl.tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.owl.main.RegexMatcher;

public class RegexMatcherDeadNodeTests {
	
	private static RegexMatcher _regexMatcher1;
	private static RegexMatcher _regexMatcher2;
	private static RegexMatcher _regexMatcher3;
	private static RegexMatcher _regexMatcher4;
	private static RegexMatcher _regexMatcher5;
	private static RegexMatcher _regexMatcher6;
	private static RegexMatcher _regexMatcher7;
	private static RegexMatcher _regexMatcher8;
	private static RegexMatcher _regexMatcher9;
	private static RegexMatcher _regexMatcher10;
	private static RegexMatcher _regexMatcher11;
	private static RegexMatcher _regexMatcher12;
	private static RegexMatcher _regexMatcher13;
	private static RegexMatcher _regexMatcher14;
	private static RegexMatcher _regexMatcher15;
	private static RegexMatcher _regexMatcher16;
	private static RegexMatcher _regexMatcher17;
	private static RegexMatcher _regexMatcher18;
	private static RegexMatcher _regexMatcher19;
	private static RegexMatcher _regexMatcher20;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		_regexMatcher1 = new RegexMatcher("a.b.c.d");
		_regexMatcher2 = new RegexMatcher("a");
		_regexMatcher3 = new RegexMatcher("a+b");
		_regexMatcher4 = new RegexMatcher("(*(*a)).b");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	//////////////////////// REGEX: a.b.c.d ///////////////////////////////
	
	@Test
	public void regex1test1() {
		Boolean stringMatchHitDeadNode = _regexMatcher1.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, null);
	}
	
	@Test
	public void regex1test2() {
		_regexMatcher1.matchesString("a");
		Boolean stringMatchHitDeadNode = _regexMatcher1.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, false);
	}
	
	@Test
	public void regex1test3() {
		_regexMatcher1.matchesString("abc");
		Boolean stringMatchHitDeadNode = _regexMatcher1.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, false);
	}
	
	@Test
	public void regex1test4() {
		_regexMatcher1.matchesString("abcd");
		Boolean stringMatchHitDeadNode = _regexMatcher1.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, false);
	}
	
	@Test
	public void regex1test5() {
		_regexMatcher1.matchesString("");
		Boolean stringMatchHitDeadNode = _regexMatcher1.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, false);
	}
	
	@Test
	public void regex1test6() {
		_regexMatcher1.matchesString("z");
		Boolean stringMatchHitDeadNode = _regexMatcher1.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, true);
	}
	
	@Test
	public void regex1test7() {
		_regexMatcher1.matchesString("abcz");
		Boolean stringMatchHitDeadNode = _regexMatcher1.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, true);
	}
	
	@Test
	public void regex1test8() {
		_regexMatcher1.matchesString("abcz");
		_regexMatcher1.matchesString("abc");
		Boolean stringMatchHitDeadNode = _regexMatcher1.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, false);
	}
	
	@Test
	public void regex1test9() {
		_regexMatcher1.matchesString("abcz");
		Boolean stringMatchHitDeadNode = _regexMatcher1.stringMatchHitDeadNode();
		_regexMatcher1.matchesString("abc");
		stringMatchHitDeadNode = _regexMatcher1.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, false);
	}

	//////////////////////// END REGEX: a.b.c.d ///////////////////////////////
	
	//////////////////////// REGEX: a ///////////////////////////////
	
	@Test
	public void regex2test1(){
		_regexMatcher2.matchesString("");
		Boolean stringMatchHitDeadNode = _regexMatcher2.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, false);
	}
	
	@Test
	public void regex2test2(){
		_regexMatcher2.matchesString("b");
		Boolean stringMatchHitDeadNode = _regexMatcher2.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, true);
	}
	
	@Test
	public void regex2test3(){
		_regexMatcher2.matchesString("b");
		Boolean stringMatchHitDeadNode = _regexMatcher2.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, true);
		
		_regexMatcher2.matchesString("b");
		stringMatchHitDeadNode = _regexMatcher2.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, true);
		
		_regexMatcher2.matchesString("c");
		stringMatchHitDeadNode = _regexMatcher2.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, true);
		
		_regexMatcher2.matchesString("a");
		stringMatchHitDeadNode = _regexMatcher2.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, false);
		
		_regexMatcher2.matchesString("");
		stringMatchHitDeadNode = _regexMatcher2.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, false);
	}
	
	//////////////////////// END REGEX: a ///////////////////////////////
	
	//////////////////////// REGEX: a+b ///////////////////////////////
	
	@Test
	public void regex3test1(){
		_regexMatcher3.matchesString("");
		Boolean stringMatchHitDeadNode = _regexMatcher3.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, false);
	}
	
	@Test
	public void regex3test2(){
		_regexMatcher3.matchesString("a");
		Boolean stringMatchHitDeadNode = _regexMatcher3.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, false);
	}
	
	@Test
	public void regex3test3(){
		_regexMatcher3.matchesString("b");
		Boolean stringMatchHitDeadNode = _regexMatcher3.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, false);
	}
	
	@Test
	public void regex3test4(){
		_regexMatcher3.matchesString("aa");
		Boolean stringMatchHitDeadNode = _regexMatcher3.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, true);
	}
	
	@Test
	public void regex3test5(){
		_regexMatcher3.matchesString("z");
		Boolean stringMatchHitDeadNode = _regexMatcher3.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, true);
	}
	
	@Test
	public void regex3test6(){
		_regexMatcher3.matchesString("az");
		Boolean stringMatchHitDeadNode = _regexMatcher3.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, true);
	}
	
	//////////////////////// END REGEX: a+b ///////////////////////////////
	
	//////////////////////// REGEX: (*(*a)).b ///////////////////////////////
	
	@Test
	public void regex4test1(){
		_regexMatcher4 = new RegexMatcher("(*(*a)).b");
		_regexMatcher4.matchesString("aaa");
		Boolean stringMatchHitDeadNode = _regexMatcher4.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, false);
	}
	
	@Test
	public void regex4test2(){
		_regexMatcher4 = new RegexMatcher("(*(*a)).b");
		_regexMatcher4.matchesString("b");
		Boolean stringMatchHitDeadNode = _regexMatcher4.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, false);
	}
	
	@Test
	public void regex4test3(){
		_regexMatcher4 = new RegexMatcher("(*(*a)).b");
		_regexMatcher4.matchesString("zb");
		Boolean stringMatchHitDeadNode = _regexMatcher4.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, true);
	}
	
	@Test
	public void regex4test4(){
		_regexMatcher4 = new RegexMatcher("(*(*a)).b");
		_regexMatcher4.matchesString("z");
		Boolean stringMatchHitDeadNode = _regexMatcher4.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, true);
	}
	
	@Test
	public void regex4test5(){
		_regexMatcher4 = new RegexMatcher("(*(*a)).b");
		_regexMatcher4.matchesString("");
		Boolean stringMatchHitDeadNode = _regexMatcher4.stringMatchHitDeadNode();
		assertEquals(stringMatchHitDeadNode, false);
	}
	
	//////////////////////// END REGEX: (*(*a)).b ///////////////////////////////
}
