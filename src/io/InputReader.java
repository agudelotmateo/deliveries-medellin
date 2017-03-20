package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Class to read input. Simulates a Scanner using BufferedReader and
 * StringTokenizer. It's much faster and provides pretty much the same
 * functionalities. Based on <a href=
 * "https://github.com/albusmath/acm-icpc-template/blob/master/code/java.java">
 * this</a>
 * 
 * @author Mateo Agudelo Toro
 */
public class InputReader {
	BufferedReader reader;
	StringTokenizer tokenizer;

	/**
	 * Creates a new input reader which reads from the given input stream.
	 * 
	 * @param stream
	 *            Stream to read input from.
	 */
	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream));
		tokenizer = null;
	}

	/**
	 * Checks for available tokens to be read.
	 * 
	 * @return Whether there's a token available to be read.
	 */
	public boolean hasNext() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (Exception e) {
				return false;
			}
		}
		return tokenizer.hasMoreTokens();
	}

	/**
	 * Gives the next token. In case that there aren't available tokens, a
	 * RuntimeException will be thrown.
	 * 
	 * @return Next available token.
	 */
	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}

	/**
	 * Reads the line starting right after the last token, starting with this
	 * token but reads the line as it is, not by tokens.
	 * 
	 * @return Remaining of current line, null if nothing is there.
	 */
	public String remainingLine() {
		if (tokenizer == null || !tokenizer.hasMoreTokens())
			return null;
		int size = tokenizer.countTokens();
		StringBuilder sb = new StringBuilder();
		while (size-- > 1)
			sb.append(tokenizer.nextToken()).append(" ");
		return sb.append(tokenizer.nextToken()).toString();
	}

	/**
	 * Reads the entire next line. If not all tokens in the current one have
	 * been read, it doesn't care and still jumps to the next one.
	 * 
	 * @return Next line (entirely, not by tokens). null there are not more
	 *         lines to read.
	 */
	public String nextLine() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Reads next token casted to double. Will throw an exception in case that
	 * the token can't be properly parsed to double.
	 * 
	 * @return Next double number.
	 */
	public double nextDouble() {
		return Double.parseDouble(next());
	}

	/**
	 * Reads next token casted to long. Will throw an exception in case that the
	 * token can't be properly parsed to long.
	 * 
	 * @return Next long number.
	 */
	public long nextLong() {
		return Long.parseLong(next());
	}
}
