import static org.junit.Assert.*;

import org.junit.Test;


public class ArrayExamplesTest {

	@Test
	public void testEq() {
		int [] arr1 = { 1 , 2 , 3 };
		int [] arr2 = { 1 , 2 , 3 };
		assertTrue( ArrayExamples.eq(arr1,arr2) );
	}
	@Test
	public void testNEq() {
		int [] arr1 = { 1 , 2 , 3 };
		int [] arr2 = { 1 , 2 , 4 };
		assertFalse( ArrayExamples.eq(arr1,arr2) );
	}
	
	@Test
	public void testNEqOrder() {
		int [] arr1 = { 1 , 2 , 3 };
		int [] arr2 = { 1 , 3 , 2 };
		assertFalse( ArrayExamples.eq(arr1,arr2) );
	}

	@Test
	public void testEmpty() {
		int [] arr1 = { };
		int [] arr2 = { };
		assertTrue( ArrayExamples.eq(arr1,arr2) );
	}
	@Test
	public void testNull() {
		int [] arr1 = null;
		int [] arr2 = null;
		assertTrue( ArrayExamples.eq(arr1,arr2) );
	}
	@Test
	public void testLonger1() {
		int [] arr1 = { 1 , 2 };
		int [] arr2 = { 1 };
		assertFalse( ArrayExamples.eq(arr1,arr2) );
	}
	@Test
	public void testLonger2() {
		int [] arr1 = { 1 };
		int [] arr2 = { 1 , 2 };
		assertFalse( ArrayExamples.eq(arr1,arr2) );
	}
	
	@Test
	public void testRect1() {
		int [][] a = {};
	    assertTrue( ArrayExamples.rect(a));
	}
	@Test
	public void testRect2() {
		int [][] a = { {} };
	    assertTrue( ArrayExamples.rect(a));
	}
	@Test
	public void testRect22() {
		int [][] a = { { 2 , 2 }, { 2 , 2 }};
	    assertTrue( ArrayExamples.rect(a));
	}
	
	@Test
	public void testRect23ok() {
		int [][] a = { { 2 , 2 , 3 }, { 2 , 2 , 4 }};
	    assertTrue( ArrayExamples.rect(a));
	}
	
	@Test
	public void testRect23() {
		int [][] a = { { 2 , 2 }, { 2 , 2 , 3}};
	    assertFalse( ArrayExamples.rect(a));
	}
	
	@Test
	public void testRectNull() {
		int [][] a = null;
	    assertFalse( ArrayExamples.rect(a));
	}
	
	@Test
	public void testRectNull2() {
		int [][] a =  { null, { 2 ,3 } };
	    assertFalse( ArrayExamples.rect(a));
	}
	
	@Test
	public void testReverse() {
		int[] arr = new int[]{ 1 , 2 , 3 };
		int[] arr2 = ArrayExamples.reverse(arr);
		assertTrue(arr != arr2);
		assertNotNull(arr2);
		assertTrue(arr2.length == 3);
		assertTrue(arr2[2] == 1);
		assertTrue(arr2[1] == 2);
		assertTrue(arr2[0] == 3);
	}
	
	@Test
	public void testNotPalindrome() {
		int[] arr = new int[] { 1, 2, 3} ;
		assertFalse(ArrayExamples.palindrome(arr));
	}
	
	@Test
	public void testPalindromeEven() {
		int[] arr = new int[] { 1, 2, 2, 1} ;
		assertTrue(ArrayExamples.palindrome(arr));
	}
	
	@Test
	public void testPalindromeEmpty() {
		int[] arr = new int[] {} ;
		assertTrue(ArrayExamples.palindrome(arr));
	}
	
	@Test
	public void testPalindromeOdd() {
		int[] arr = new int[] { 1, 2, 3, 2, 1} ;
		assertTrue(ArrayExamples.palindrome(arr));
	}
	
	@Test
	public void testConcat1() {
		int[] arr1 = new int[] { 1, 2, 3 } ;
		int[] arr2 = new int[] { 4 ,5, 6 } ;
		int[] ans  = new int[] { 1, 2, 3, 4, 5, 6} ;
		assertTrue(ArrayExamples.eq(ans, ArrayExamples.concat(arr1,arr2)));
	}
	
	@Test
	public void testConcat2() {
		int[] arr1 = new int[] { 1, 2, 3 } ;
		int[] arr2 = new int[] {} ;
		int[] ans  = new int[] { 1, 2, 3} ;
		assertTrue(ArrayExamples.eq(ans, ArrayExamples.concat(arr1,arr2)));
	}
	
	@Test
	public void testConcat3() {
		int[] arr1 = new int[] {} ;
		int[] arr2 = new int[] {1,2,3} ;
		int[] ans  = new int[] { 1, 2, 3} ;
		assertTrue(ArrayExamples.eq(ans, ArrayExamples.concat(arr1,arr2)));
	}

	@Test
	public void testInterleave() {
		int[] arr1 = { 1, 3, 5 };
		int[] arr2 = { 2 };
		int[] ans  = { 1 , 2, 3, 5 };
		assertTrue (ArrayExamples.eq(ans, ArrayExamples.interleave(arr1, arr2)));
	}
	
	@Test
	public void testMax() {
		int[] arr1 = { 1, 3, 4, 4};
		assertEquals(ArrayExamples.max(arr1), 4);	
	}
	
	@Test
	public void testMaxIndex() {
		int[] arr1 = { 1, 3, 4, 4};
		assertEquals(2, ArrayExamples.maxIndex(arr1));	
	}
	
	
	
	@Test 
	public void testAvg1() {
		int[][] arr = { { 1 } };
		assertEquals(ArrayExamples.avg(arr),1);
	}
	
	@Test 
	public void testAvg2() {
		int[][] arr = { { 1, 2 }, { 3 }};
		assertEquals(ArrayExamples.avg(arr),2);
	}

	@Test
	public void testSub() {
		int[] arr = { 0, 1, 2, 3, 4 };
		int[] ans = ArrayExamples.sub(arr, 2, 3);
		assertTrue(ArrayExamples.eq( new int[]{ 2 }, ans));
	}
	
	@Test
	public void testSubHead() {
		int[] arr = { 0, 1, 2, 3, 4 };
		int[] ans = ArrayExamples.sub(arr, 0, 2);
		assertTrue(ArrayExamples.eq( new int[]{ 0,1 }, ans));
	}
	@Test
	public void testSubTail() {
		int[] arr = { 0, 1, 2, 3, 4 };
		int[] ans = ArrayExamples.sub(arr, 2,5);
		assertTrue(ArrayExamples.eq( new int[]{ 2,3,4 }, ans));
	}
}