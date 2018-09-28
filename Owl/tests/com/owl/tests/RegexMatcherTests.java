package com.owl.tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import com.owl.main.*;

public class RegexMatcherTests{
	
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
		_regexMatcher1 = new RegexMatcher("a");
		_regexMatcher2 = new RegexMatcher("a.b");
		_regexMatcher3 = new RegexMatcher("a+b");
		_regexMatcher4 = new RegexMatcher("*a");
		_regexMatcher5 = new RegexMatcher("(a)");
		_regexMatcher6 = new RegexMatcher("*(a)");
		_regexMatcher7 = new RegexMatcher("**(a)");
		_regexMatcher8 = new RegexMatcher("**a");
		_regexMatcher9 = new RegexMatcher("(a).(b)");
		_regexMatcher10 = new RegexMatcher("(a.(b))");
		_regexMatcher11 = new RegexMatcher("d");
		_regexMatcher12 = new RegexMatcher("#(");
		_regexMatcher13 = new RegexMatcher("a+b+c+d");
		_regexMatcher14 = new RegexMatcher("(*(*a)).b");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	/////////////////////// REGEX : "a" ///////////////////////////
	
	@Test
	public void regex1test1() {
		String inputString = "a";
		assertEquals(true, _regexMatcher1.matchesString(inputString));
	}
	
	@Test
	public void regex1test2() {
		String inputString = "b";
		assertEquals(false, _regexMatcher1.matchesString(inputString));
	}
	
	@Test
	public void regex1test3() {
		String inputString = "";
		assertEquals(false, _regexMatcher1.matchesString(inputString));
	}
	
	@Test
	public void regex1test4() {
		String inputString = "abcd";
		assertEquals(false, _regexMatcher1.matchesString(inputString));
	}
	
	@Test
	public void regex1test5() {
		String inputString = " ";
		assertEquals(false, _regexMatcher1.matchesString(inputString));
	}
	
	/////////////////////// END REGEX : "a" ///////////////////////////
	
	/////////////////////// REGEX : "a.b" ///////////////////////////
	
	@Test
	public void regex2test1(){
		String inputString = "ab";
		assertEquals(true, _regexMatcher2.matchesString(inputString));
	}
	
	@Test
	public void regex2test2(){
		String inputString = "abc";
		assertEquals(false, _regexMatcher2.matchesString(inputString));
	}
	
	@Test
	public void regex2test3(){
		String inputString = "cab";
		assertEquals(false, _regexMatcher2.matchesString(inputString));
	}
	
	@Test
	public void regex2test4(){
		String inputString = "abab";
		assertEquals(false, _regexMatcher2.matchesString(inputString));
	}
	
	@Test
	public void regex2test5(){
		String inputString = "";
		assertEquals(false, _regexMatcher2.matchesString(inputString));
	}
	
	@Test
	public void regex2test6(){
		String inputString = " ";
		assertEquals(false, _regexMatcher2.matchesString(inputString));
	}
	
	@Test
	public void regex2test7(){
		String inputString = "a.b";
		assertEquals(false, _regexMatcher2.matchesString(inputString));
	}
	
	/////////////////////// END REGEX : "a.b" ///////////////////////////
	
	/////////////////////// REGEX : "a+b" ///////////////////////////
	
	@Test
	public void regex3test1(){
		String inputString = "a";
		assertEquals(true, _regexMatcher3.matchesString(inputString));
	}
	
	@Test
	public void regex3test2(){
		String inputString = "b";
		assertEquals(true, _regexMatcher3.matchesString(inputString));
	}
	
	@Test
	public void regex3test3(){
		String inputString = "c";
		assertEquals(false, _regexMatcher3.matchesString(inputString));
	}
	
	@Test
	public void regex3test4(){
		String inputString = "ab";
		assertEquals(false, _regexMatcher3.matchesString(inputString));
	}
	
	@Test
	public void regex3test5(){
		String inputString = "";
		assertEquals(false, _regexMatcher3.matchesString(inputString));
	}
	
	@Test
	public void regex3test6(){
		String inputString = " ";
		assertEquals(false, _regexMatcher3.matchesString(inputString));
	}
	
	@Test
	public void regex3test7(){
		String inputString = "a+b";
		assertEquals(false, _regexMatcher3.matchesString(inputString));
	}
	/////////////////////// END REGEX : "a+b" ///////////////////////////
	
	/////////////////////// REGEX : "*a" ///////////////////////////
	
	@Test
	public void regex4test1(){
		String inputString = "";
		assertEquals(true, _regexMatcher4.matchesString(inputString));
	}
	
	@Test
	public void regex4test2(){
		String inputString = "a";
		assertEquals(true, _regexMatcher4.matchesString(inputString));
	}
	
	@Test
	public void regex4test3(){
		String inputString = "aa";
		assertEquals(true, _regexMatcher4.matchesString(inputString));
	}
	
	@Test
	public void regex4test5(){
		String inputString = "aaa";
		assertEquals(true, _regexMatcher4.matchesString(inputString));
	}
	
	@Test
	public void regex4test6(){
		String inputString = "aaaaaaaaaaaaaaaaa";
		assertEquals(true, _regexMatcher4.matchesString(inputString));
	}
	
	@Test
	public void regex4test7(){
		String inputString = "b";
		assertEquals(false, _regexMatcher4.matchesString(inputString));
	}
	
	@Test
	public void regex4test8(){
		String inputString = "aaaaaaab";
		assertEquals(false, _regexMatcher4.matchesString(inputString));
	}
	
	@Test
	public void regex4test9(){
		String inputString = "aaaaaaabaaaaaaa";
		assertEquals(false, _regexMatcher4.matchesString(inputString));
	}
	
	@Test
	public void regex4test10(){
		String inputString = " aaaaaaa";
		assertEquals(false, _regexMatcher4.matchesString(inputString));
	}
	
	/////////////////////// END REGEX : "*a" ///////////////////////////
	
	/////////////////////// REGEX : "(a)" ///////////////////////////
	
	@Test
	public void regex5test1(){
		String inputString = "a";
		assertEquals(true, _regexMatcher5.matchesString(inputString));
	}
	
	@Test
	public void regex5test2(){
		String inputString = "";
		assertEquals(false, _regexMatcher5.matchesString(inputString));
	}
	
	@Test
	public void regex5test3(){
		String inputString = " ";
		assertEquals(false, _regexMatcher5.matchesString(inputString));
	}
	
	@Test
	public void regex5test4(){
		String inputString = "(a)";
		assertEquals(false, _regexMatcher5.matchesString(inputString));
	}
	
	@Test
	public void regex5test5(){
		String inputString = "b";
		assertEquals(false, _regexMatcher5.matchesString(inputString));
	}
	
	@Test
	public void regex5test6(){
		String inputString = "c";
		assertEquals(false, _regexMatcher5.matchesString(inputString));
	}
	
	@Test
	public void regex5test7(){
		String inputString = " a";
		assertEquals(false, _regexMatcher5.matchesString(inputString));
	}
	
	@Test
	public void regex5test8(){
		String inputString = " a ";
		assertEquals(false, _regexMatcher5.matchesString(inputString));
	}
	
	@Test
	public void regex5test9(){
		String inputString = "a ";
		assertEquals(false, _regexMatcher5.matchesString(inputString));
	}
	
	/////////////////////// END REGEX : "(a)" ///////////////////////////
	
	/////////////////////// REGEX : "*(a)" ///////////////////////////
	
	@Test
	public void regex6test1(){
		String inputString = "";
		assertEquals(true, _regexMatcher6.matchesString(inputString));
	}
	
	@Test
	public void regex6test2(){
		String inputString = "a";
		assertEquals(true, _regexMatcher6.matchesString(inputString));
	}
	
	@Test
	public void regex6test3(){
		String inputString = "aa";
		assertEquals(true, _regexMatcher6.matchesString(inputString));
	}
	
	@Test
	public void regex6test4(){
		String inputString = "aaaaaaaaaaa";
		assertEquals(true, _regexMatcher6.matchesString(inputString));
	}
	
	@Test
	public void regex6test5(){
		String inputString = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		assertEquals(true, _regexMatcher6.matchesString(inputString));
	}
	
	@Test
	public void regex6test6(){
		String inputString = " a";
		assertEquals(false, _regexMatcher6.matchesString(inputString));
	}
	
	@Test
	public void regex6test7(){
		String inputString = " a ";
		assertEquals(false, _regexMatcher6.matchesString(inputString));
	}
	
	@Test
	public void regex6test8(){
		String inputString = "a ";
		assertEquals(false, _regexMatcher6.matchesString(inputString));
	}
	
	@Test
	public void regex6test9(){
		String inputString = " a aaa a aa aaaa aaa a";
		assertEquals(false, _regexMatcher6.matchesString(inputString));
	}
	
	@Test
	public void regex6test10(){
		String inputString = "b";
		assertEquals(false, _regexMatcher6.matchesString(inputString));
	}
	
	@Test
	public void regex6test11(){
		String inputString = "A";
		assertEquals(false, _regexMatcher6.matchesString(inputString));
	}
	
	/////////////////////// END REGEX : "*(a)" ///////////////////////////
	
	/////////////////////// REGEX : "**(a)" ///////////////////////////
	
	@Test
	public void regex7test1(){
		String inputString = "";
		assertEquals(true, _regexMatcher7.matchesString(inputString));
	}
	
	@Test
	public void regex7test2(){
		String inputString = "a";
		assertEquals(true, _regexMatcher7.matchesString(inputString));
	}
	
	@Test
	public void regex7test3(){
		String inputString = "aaaa";
		assertEquals(true, _regexMatcher7.matchesString(inputString));
	}
	
	@Test
	public void regex7test4(){
		String inputString = " a";
		assertEquals(false, _regexMatcher7.matchesString(inputString));
	}
	
	@Test
	public void regex7test5(){
		String inputString = " a ";
		assertEquals(false, _regexMatcher7.matchesString(inputString));
	}
	
	@Test
	public void regex7test6(){
		String inputString = "a ";
		assertEquals(false, _regexMatcher7.matchesString(inputString));
	}
	
	@Test
	public void regex7test7(){
		String inputString = " aa aaa aaa aaaaaa a";
		assertEquals(false, _regexMatcher7.matchesString(inputString));
	}
	
	@Test
	public void regex7test8(){
		String inputString = "b";
		assertEquals(false, _regexMatcher7.matchesString(inputString));
	}
	
	@Test
	public void regex7test9(){
		String inputString = "A";
		assertEquals(false, _regexMatcher7.matchesString(inputString));
	}
	
	/////////////////////// END REGEX : "**(a)" ///////////////////////////
	
	/////////////////////// REGEX : "**a" ///////////////////////////
	
	@Test
	public void regex8test1(){
		String inputString = "";
		assertEquals(true, _regexMatcher8.matchesString(inputString));
	}
	
	@Test
	public void regex8test2(){
		String inputString = "a";
		assertEquals(true, _regexMatcher8.matchesString(inputString));
	}
	
	@Test
	public void regex8test3(){
		String inputString = "aaaaa";
		assertEquals(true, _regexMatcher8.matchesString(inputString));
	}
	
	@Test
	public void regex8test4(){
		String inputString = " a";
		assertEquals(false, _regexMatcher8.matchesString(inputString));
	}
	
	@Test
	public void regex8test5(){
		String inputString = " a ";
		assertEquals(false, _regexMatcher8.matchesString(inputString));
	}
	
	@Test
	public void regex8test6(){
		String inputString = "a ";
		assertEquals(false, _regexMatcher8.matchesString(inputString));
	}
	
	@Test
	public void regex8test7(){
		String inputString = " aa aaa aaaa aaaaaaa aaa";
		assertEquals(false, _regexMatcher8.matchesString(inputString));
	}
	
	@Test
	public void regex8test8(){
		String inputString = "b";
		assertEquals(false, _regexMatcher8.matchesString(inputString));
	}
	
	@Test
	public void regex8test9(){
		String inputString = "A";
		assertEquals(false, _regexMatcher8.matchesString(inputString));
	}
	
	/////////////////////// END REGEX : "**a" ///////////////////////////
	
	/////////////////////// REGEX : "(a).(b)" ///////////////////////////
	
	@Test
	public void regex9test1(){
		String inputString = "ab";
		assertEquals(true, _regexMatcher9.matchesString(inputString));
	}
	
	@Test
	public void regex9test2(){
		String inputString = " ab";
		assertEquals(false, _regexMatcher9.matchesString(inputString));
	}
	
	@Test
	public void regex9test3(){
		String inputString = " ab ";
		assertEquals(false, _regexMatcher9.matchesString(inputString));
	}
	
	@Test
	public void regex9test4(){
		String inputString = "ab ";
		assertEquals(false, _regexMatcher9.matchesString(inputString));
	}
	
	@Test
	public void regex9test5(){
		String inputString = "";
		assertEquals(false, _regexMatcher9.matchesString(inputString));
	}
	
	@Test
	public void regex9test6(){
		String inputString = "Ab";
		assertEquals(false, _regexMatcher9.matchesString(inputString));
	}
	
	@Test
	public void regex9test7(){
		String inputString = "AB";
		assertEquals(false, _regexMatcher9.matchesString(inputString));
	}
	
	@Test
	public void regex9test8(){
		String inputString = "aB";
		assertEquals(false, _regexMatcher9.matchesString(inputString));
	}
	
	@Test
	public void regex9test9(){
		String inputString = "a";
		assertEquals(false, _regexMatcher9.matchesString(inputString));
	}
	
	@Test
	public void regex9test10(){
		String inputString = "b";
		assertEquals(false, _regexMatcher9.matchesString(inputString));
	}
	
	@Test
	public void regex9test11(){
		String inputString = "c";
		assertEquals(false, _regexMatcher9.matchesString(inputString));
	}
	
	/////////////////////// END REGEX : "(a).(b)" ///////////////////////////
	
	/////////////////////// REGEX : "(a.(b))" ///////////////////////////
	
	@Test
	public void regex10test1(){
		String inputString = "ab";
		assertEquals(true, _regexMatcher10.matchesString(inputString));
	}
	
	@Test
	public void regex10test2(){
		String inputString = " ab";
		assertEquals(false, _regexMatcher10.matchesString(inputString));
	}
	
	@Test
	public void regex10test3(){
		String inputString = " ab ";
		assertEquals(false, _regexMatcher10.matchesString(inputString));
	}
	
	@Test
	public void regex10test4(){
		String inputString = "ab ";
		assertEquals(false, _regexMatcher10.matchesString(inputString));
	}
	
	@Test
	public void regex10test5(){
		String inputString = "a";
		assertEquals(false, _regexMatcher10.matchesString(inputString));
	}
	
	@Test
	public void regex10test6(){
		String inputString = "b";
		assertEquals(false, _regexMatcher10.matchesString(inputString));
	}
	
	@Test
	public void regex10test7(){
		String inputString = "";
		assertEquals(false, _regexMatcher10.matchesString(inputString));
	}
	
	@Test
	public void regex10test8(){
		String inputString = "c";
		assertEquals(false, _regexMatcher10.matchesString(inputString));
	}
	
	@Test
	public void regex10test9(){
		String inputString = "Ab";
		assertEquals(false, _regexMatcher10.matchesString(inputString));
	}
	
	@Test
	public void regex10test10(){
		String inputString = "AB";
		assertEquals(false, _regexMatcher10.matchesString(inputString));
	}
	
	@Test
	public void regex10test11(){
		String inputString = "aB";
		assertEquals(false, _regexMatcher10.matchesString(inputString));
	}
	
	@Test
	public void regex10test12(){
		String inputString = " ";
		assertEquals(false, _regexMatcher10.matchesString(inputString));
	}
	
	/////////////////////// END REGEX : "(a.(b))" ///////////////////////////
	
	/////////////////////// REGEX : "d" ///////////////////////////
	
	@Test
	public void regex11test1(){
		String inputString = "d";
		assertEquals(true, _regexMatcher11.matchesString(inputString));
	}
	
	@Test
	public void regex11test2(){
		String inputString = "z";
		assertEquals(false, _regexMatcher11.matchesString(inputString));
	}
	
	@Test
	public void regex11test3(){
		String inputString = "D";
		assertEquals(false, _regexMatcher11.matchesString(inputString));
	}
	
	@Test
	public void regex11test4(){
		String inputString = " ";
		assertEquals(false, _regexMatcher11.matchesString(inputString));
	}
	
	@Test
	public void regex11test5(){
		String inputString = " d";
		assertEquals(false, _regexMatcher11.matchesString(inputString));
	}
	
	/////////////////////// END REGEX : "d" ///////////////////////////
	
	/////////////////////// REGEX : "#(" ///////////////////////////
	
	@Test
	public void regex12test1(){
		String inputString = "(";
		assertEquals(true, _regexMatcher12.matchesString(inputString));
	}
	
	@Test
	public void regex12test2(){
		String inputString = " (";
		assertEquals(false, _regexMatcher12.matchesString(inputString));
	}
	
	@Test
	public void regex12test3(){
		String inputString = "a";
		assertEquals(false, _regexMatcher12.matchesString(inputString));
	}
	
	@Test
	public void regex12test4(){
		String inputString = " ( ";
		assertEquals(false, _regexMatcher12.matchesString(inputString));
	}
	
	@Test
	public void regex12test5(){
		String inputString = "( ";
		assertEquals(false, _regexMatcher12.matchesString(inputString));
	}
	
	/////////////////////// END REGEX : "#(" ///////////////////////////
	
	/////////////////////// REGEX : "a+b+c+d" ///////////////////////////
	
	@Test
	public void regex13test1(){
		String inputString = "a";
		assertEquals(true, _regexMatcher13.matchesString(inputString));
	}
	
	@Test
	public void regex13test2(){
		String inputString = "b";
		assertEquals(true, _regexMatcher13.matchesString(inputString));
	}
	
	@Test
	public void regex13test3(){
		String inputString = "c";
		assertEquals(true, _regexMatcher13.matchesString(inputString));
	}
	
	@Test
	public void regex13test4(){
		String inputString = "d";
		assertEquals(true, _regexMatcher13.matchesString(inputString));
	}
	
	/////////////////////// END REGEX : "a+b+c+d" ///////////////////////////
	
	/////////////////////// REGEX : "(*(*a)).b" ///////////////////////////
	
	@Test
	public void regex14test1(){
		String inputString = "ab";
		assertEquals(true, _regexMatcher14.matchesString(inputString));
	}
	
	@Test
	public void regex14test2(){
		String inputString = "aab";
		assertEquals(true, _regexMatcher14.matchesString(inputString));
	}
	
	@Test
	public void regex14test3(){
		String inputString = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		assertEquals(false, _regexMatcher14.matchesString(inputString));
	}
	
	@Test
	public void regex14test4(){
		String inputString = "";
		assertEquals(false, _regexMatcher14.matchesString(inputString));
	}
	
	/////////////////////// END REGEX : "(*(*a)).b" ///////////////////////////
}
