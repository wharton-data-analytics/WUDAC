package histogram;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class WordScanner implements Iterator<String> {
	private Reader r;	
	private int c = -1;

	public WordScanner(Reader r) {
		this.r = r;

		skipNonLetters();
	}

	private void skipNonLetters() {
		try {
			c = r.read();
			while (!Character.isLetter(c) && c != -1) {
				c = r.read();
			}
		} catch (IOException e) {
			c = -1;
		}
	}

	@Override
	public boolean hasNext() {
		return ( c != -1 );
	}

	@Override
	public String next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		String answer = "";
		try {
			while (Character.isLetter(c)) {
				answer = answer + (char)c;
				c = r.read();
			}
			skipNonLetters();
		} catch (IOException e) {
			throw new NoSuchElementException();
		}
		return answer;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
