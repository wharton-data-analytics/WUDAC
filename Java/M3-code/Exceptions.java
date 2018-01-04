import java.io.IOException;

class ExceptionDemo {
	public void methodA() throws IOException {
		System.out.println("Entering A");
		methodB();
		System.out.println("Leaving A");
	}
	
	public void methodB() throws IOException {
		System.out.println("Entering B");
		try {
		  methodC(true);
		  System.out.println("In B after C");
		  return;
		} 
		catch (RuntimeException e) {
			
		} finally {
			System.out.println("In B's finally");
		}
		System.out.println("Leaving B");
	}
	
	public void methodC(boolean raiseException) throws IOException {
		System.out.println("Entering C");
		if (raiseException) throw new RuntimeException("HERE!");
		System.out.println("Leaving C");
	}
	
}



public class Exceptions {

	public static void main(String[] args) {
		try {
		  (new ExceptionDemo()).methodA();
		} catch (IOException e) {
			
			
		} 
	}

}
