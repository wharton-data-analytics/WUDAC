// Finger exercises for working with arrays

// All of these methods are static methods that calculate with 
// array data structures
  

public class ArrayExamples {

	// Determine whether two integer arrays contain the 
	// same elements
	
	// two null arrays are equal
	// and two nonnull arrays are equal when all of their elements are equal
	public static boolean eq(int [] arr1, int[] arr2) {
		if (arr1 == null && arr2 == null) {
			return true;
		}  
		if (arr1 == null || arr2 == null) {
			return false;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length ; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
	
		return true;
	}	
	
	public static void example() {
		int[][] products = new int[5][];            
		for(int col = 0; col < 5; col++) {
			products[col] = new int[col+1];           
			for(int row = 0; row <= col; row++) {
				products[col][row] = col * row;
			}          
		}

	}

	
	// copy the array between positions m to n
	public static int[] sub(int[] a, int m, int n) {
		return null;
	}
	
	// determine whether an array is a palindrome
	public static boolean palindrome(int [] arr) {
		return false;
	}
	
	// concatenate two arrays together, producing a new 
	// array that contains all of the elements of the first
	// array followed by all of the elements of the second
	// array.
	public static int[] concat (int[] arr1, int[] arr2) {
		return null;
	}	
	
	// interleave two arrays together
	public static int[] interleave (int[] arr1, int[] arr2) {
		return null;
	}	
	
	// compute the maximum element of an array
	// that contains at least one element
	public static int max(int[] arr) {
		return -1;
	}	
	
	
	// return an array that is the reverse of the given array
	public static int[] reverse(int [] arr) {
		return null;
	}
	
	// compute the *index* of the maximum element of an array
	// that contains at least one element
	public static int maxIndex(int[] arr) {
		return -1;
	}	
	
	// ensure a 2D array is rectangular
	public static boolean rect (int [][] a) {
		return true;
	}
	
	// find the average value of a 2D array
	public static int avg (int [][] a) {
		return 0;
	}
}