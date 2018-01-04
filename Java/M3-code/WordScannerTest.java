package histogram;
import static org.junit.Assert.*;

import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;


public class WordScannerTest {

  @Test
  public void testNull() {
    try {
      WordScanner ws = new WordScanner(null);
    } catch (NullPointerException e) {
      return;
    }
    fail();
  }
  
  @Test
  public void testHasNext() {

      Reader r = new StringReader("one");
      WordScanner ws = new WordScanner(r);
      assertTrue(ws.hasNext());
  
  }
  
  @Test
  public void testHasNextBlank() {

      Reader r = new StringReader("  ");
      WordScanner ws = new WordScanner(r);
      assertFalse(ws.hasNext());
  
  }
  
  @Test
  public void testHasNextTrailing() {

      Reader r = new StringReader("one  ");
      WordScanner ws = new WordScanner(r);
      assertTrue(ws.hasNext());
      assertEquals("one",ws.next());
      assertFalse(ws.hasNext());
  
  }
  
  @Test
  public void testHasNextPunc() {

      Reader r = new StringReader(".*#^$#$");
      WordScanner ws = new WordScanner(r);
      assertFalse(ws.hasNext());
  
  }
  
  @Test
  public void testHasNextApostrophe() {

      Reader r = new StringReader("don't");
      WordScanner ws = new WordScanner(r);
      assertTrue(ws.hasNext());
      assertEquals("don", ws.next());
      assertTrue(ws.hasNext());
      assertEquals("t",ws.next());
      assertFalse(ws.hasNext());
  
  }
  
 
}
