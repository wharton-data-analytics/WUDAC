public class ResArray {
  
    private int[] data;
    private int extent = 0;
    /* INVARIANT: extent = 1+index of last nonzero
     * element, or 0 if all elements are 0.
     */
  
    /** Constructor, takes no arguments. */
    public ResArray() {
	data = new int[3];
    }   
  
    /** Access the array at position i. 
     * If i is negative, through IllegalArgumentException.
     * If position i has not yet 
     * been initialized, return 0.
     */
    public int get(int idx) {
	if (idx < 0) {
	    throw new IllegalArgumentException();
	} else {
	    if (idx >= data.length) {
		return 0;
	    } else {
		return data[idx];
	    }
	}
    }
  
    private void grow(int idx) {
	if (idx >= data.length) {
	    int[] newdata = new int[Math.max(idx+1, data.length*2)];
	    for (int i=0; i < data.length; i++) {
		newdata[i] = data[i];
	    }
	    data = newdata;
	}
    }
  
    /** Modify the array at position i to contain the value v. */
    public void set(int idx, int val) {
	if (idx < 0) {
	    throw new IllegalArgumentException();
	}
	grow(idx);
	data[idx] = val;
	if (val != 0 && idx+1 > extent) {
	    extent = idx+1;
	}
	if (val == 0 && idx+1 == extent) {
	    while (extent > 0 && data[extent-1] == 0) {
		extent--;
	    }
	}
    }
  
    /** Return the extent of the array. i.e. one past the index
     * of the last nonzero value in the array. */
    public int getExtent() {
	return extent;
    }

    // This implementation violates abstraction!
    public int[] values() {
	return data;
    }
  
}
