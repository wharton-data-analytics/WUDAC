package histogram;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Histogram { 

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: java Histrogram <file.txt>");
      return;
    }
    
    try {
      Reader r = new FileReader(args[0]);
      
      Iterator<String> ws = new WordScanner(r);
      
      Map<String, Integer> histogram = 
    		  new TreeMap<String,Integer>();
      // count words
      while (ws.hasNext()) {
       String word = ws.next();
       if (histogram.containsKey(word)) {
    	   	 int count = histogram.get(word);
    	   	 histogram.put(word, count + 1);
       } else {
    	     histogram.put(word, 1);
       }
      }
      // print results
      
      for (Map.Entry<String,Integer> entry : histogram.entrySet()) {
    	  	System.out.println(entry.getKey() + ": " + entry.getValue());
      }
      
    } catch (FileNotFoundException e) {
      System.out.println("File Not Found: " + args[0]);
    }

  }

}
