///import static org.junit.Assert.*;

import static org.junit.Assert.*;

import org.junit.Test;

/** Test cases for resizeable arrays */

public class ResArrayTest {

	//initialization and get
	@Test public void testGet0 () {
		ResArray a = new ResArray();
		assertEquals(0, a.get(17));
	}
	
	@Test public void testAtExtent() {
		ResArray a = new ResArray();
		int e = a.getExtent();
		assertEquals(0, a.get(e));
	}

	// set then get
	@Test
	public void testSetGet1 () {
		ResArray a = new ResArray();
		a.set(17, 120);
		int result = a.get(17);
		
		assertEquals(120, result);
	}

	// set then get
	@Test
	public void testSetGet2 () {
		ResArray a = new ResArray();
		a.set(17, 120);
		a.set(17, 110);
		assertEquals(110, a.get(17));
	}

	// set then get
	@Test
	public void testSetGet3 () {
		ResArray a = new ResArray();
		a.set(17, 120);
		assertEquals(0, a.get(14));
	}

	// test that an expected exception is raised
	@Test(expected = IllegalArgumentException.class) 
	public void testNegativeArg () {
		ResArray a = new ResArray();
		a.set(-17, 23);
	}
	
	
	// determine the extent of the array
	@Test
	public void testExtent0 () {
		ResArray a = new ResArray();
		assertEquals(0, a.getExtent());
	}

	// determine the extent of the array
	@Test
	public void testExtent1 () {
		ResArray a = new ResArray();
		a.set(17, 120);
		assertEquals(18, a.getExtent());
	}

	
	// determine the extent of the array
	@Test
	public void testExtent2 () {
		ResArray a = new ResArray();
		a.set(17, 120);
		a.set(17, 0);
		assertEquals(0, a.getExtent());
	}
	
	// determine the extent of the array
	@Test
	public void testExtent3 () {
		ResArray a = new ResArray();
		a.set(17, 120);
		a.set(15, 110);
		a.set(17, 0);
		assertEquals(16, a.getExtent());
	}
	
/*
	// Getting all of the values after set
	@Test
	public void testValues1() {
		ResArray a = new ResArray();
		a.set(1,1);
		a.set(2,4);
		int [] b = a.values();
		assertEquals(b.length,3);
		assertEquals(0,b[0]);
		assertEquals(1,b[1]);
		assertEquals(4,b[2]);
	}
	
  // Getting all of the values after set
  @Test
  public void testValues2() {
    ResArray a = new ResArray();
    a.set(1, 1);
    a.set(2, 4);
    a.set(2, 0);
    int[] b = a.values();
    assertEquals(b.length, 2);
    assertEquals(0,b[0]);
    assertEquals(1,b[1]);
  }
  */

}