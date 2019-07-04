package com.sungeon.bos.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

	private BufferedReader br;

	private BufferedWriter bw;

	private boolean hasNext = true;

	private char separator; // 分隔符

	private char quotechar; // 引号符

	private int skipLines; //

	private boolean linesSkiped; // 转移线

	private int type;

	/** The default separator to use if none is supplied to the constructor. */
	public static final char DEFAULT_SEPARATOR = ',';

	/**
	 * The default quote character to use if none is supplied to the
	 * constructor.
	 */
	public static final char DEFAULT_QUOTE_CHARACTER = '"';

	/**
	 * The default line to start reading.
	 */
	public static final int DEFAULT_SKIP_LINES = 0;

	public static final String DEFAULT_CHRSET = "UTF-8";

	public static final int TYPE_READ = 0;

	public static final int TYPE_WRITE = 1;

	public CSVUtil(File file, int type) throws UnsupportedEncodingException, FileNotFoundException {
		this(file, type, DEFAULT_CHRSET);
	}

	/**
	 * Constructs CSVReader using a comma for the separator.
	 * 
	 * @param file
	 *            the file to an underlying CSV source.
	 * @param type
	 *            TYPE_READ or TYPE_WRITE
	 * @param chrset
	 *            the chrset for CSV file, default "UTF-8"
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public CSVUtil(File file, int type, String chrset) throws UnsupportedEncodingException, FileNotFoundException {
		this(file, type, DEFAULT_SEPARATOR, chrset);
	}

	/**
	 * Constructs CSVReader with supplied separator.
	 * 
	 * @param file
	 *            the file to an underlying CSV source.
	 * @param type
	 *            TYPE_READ or TYPE_WRITE
	 * @param separator
	 *            the delimiter to use for separating entries.
	 * @param chrset
	 *            the chrset for CSV file, default "UTF-8"
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public CSVUtil(File file, int type, char separator, String chrset)
			throws UnsupportedEncodingException, FileNotFoundException {
		this(file, type, separator, DEFAULT_QUOTE_CHARACTER, chrset);
	}

	/**
	 * Constructs CSVReader with supplied separator and quote char.
	 * 
	 * @param file
	 *            the file to an underlying CSV source.
	 * @param type
	 *            TYPE_READ or TYPE_WRITE
	 * @param separator
	 *            the delimiter to use for separating entries
	 * @param quotechar
	 *            the character to use for quoted elements
	 * @param chrset
	 *            the chrset for CSV file, default "UTF-8"
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public CSVUtil(File file, int type, char separator, char quotechar, String chrset)
			throws UnsupportedEncodingException, FileNotFoundException {
		this(file, type, separator, quotechar, DEFAULT_SKIP_LINES, chrset);
	}

	/**
	 * Constructs CSVReader with supplied separator and quote char.
	 * 
	 * @param file
	 *            the file to an underlying CSV source.
	 * @param type
	 *            TYPE_READ or TYPE_WRITE
	 * @param separator
	 *            the delimiter to use for separating entries
	 * @param quotechar
	 *            the character to use for quoted elements
	 * @param line
	 *            the line number to skip for start reading
	 * @param chrset
	 *            the chrset for CSV file, default "UTF-8"
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public CSVUtil(File file, int type, char separator, char quotechar, int line, String chrset)
			throws UnsupportedEncodingException, FileNotFoundException {
		if (null == chrset)
			chrset = DEFAULT_CHRSET;

		if (type == TYPE_READ)
			this.br = new BufferedReader(new InputStreamReader(new FileInputStream(file), chrset));
		else if (type == TYPE_WRITE)
			this.bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), chrset));
		this.type = type;
		this.separator = separator;
		this.quotechar = quotechar;
		this.skipLines = line;
	}

	/**
	 * Reads the entire file into a List with each element being a String[] of
	 * tokens.
	 * 
	 * @return a List of String[], with each String[] representing a line of the
	 *         file.
	 * 
	 * @throws IOException
	 *             if bad things happen during the read
	 */
	public List<String[]> readAllLine() throws IOException {
		List<String[]> allElements = new ArrayList<String[]>();
		while (hasNext) {
			String[] nextLineAsTokens = readNextLine();
			if (nextLineAsTokens != null)
				allElements.add(nextLineAsTokens);
		}
		return allElements;
	}

	/**
	 * Reads the next line from the buffer and converts to a string array.
	 * 
	 * @return a string array with each comma-separated element as a separate
	 *         entry.
	 * 
	 * @throws IOException
	 *             if bad things happen during the read
	 */
	public String[] readNextLine() throws IOException {
		String nextLine = getNextLine();
		return hasNext ? parseLine(nextLine) : null;
	}

	public boolean writeLines(List<String[]> datas) throws IOException {
		for (String[] data : datas) {
			if (writeOneLine(data))
				continue;
			else
				return false;
		}
		return true;
	}

	public boolean writeOneLine(String[] data) throws IOException {
		if (data.length != 0) {
			for (int i = 0; i < data.length; i++) {
				if (i == 0)
					bw.append(data[i]);
				else
					bw.append(separator).append(data[i]);
			}
			bw.append("\r");
		}
		return true;
	}

	/**
	 * Reads the next line from the file.
	 * 
	 * @return the next line from the file without trailing newline
	 * @throws IOException
	 *             if bad things happen during the read
	 */
	private String getNextLine() throws IOException {
		if (!this.linesSkiped) {
			for (int i = 0; i < skipLines; i++) {
				br.readLine();
			}
			this.linesSkiped = true;
		}
		String nextLine = br.readLine();
		if (nextLine == null) {
			hasNext = false;
		}
		return hasNext ? nextLine : null;
	}

	/**
	 * Parses an incoming String and returns an array of elements.
	 * 
	 * @param nextLine
	 *            the string to parse
	 * @return the comma-tokenized list of elements, or null if nextLine is null
	 * @throws IOException
	 *             if bad things happen during the read
	 */
	private String[] parseLine(String nextLine) throws IOException {

		if (nextLine == null) {
			return null;
		}

		List<String> tokensOnThisLine = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		boolean inQuotes = false;
		do {
			if (inQuotes) {
				// continuing a quoted section, reappend newline
				sb.append("\n");
				nextLine = getNextLine();
				if (nextLine == null)
					break;
			}
			for (int i = 0; i < nextLine.length(); i++) {

				char c = nextLine.charAt(i);
				if (c == quotechar) {
					// this gets complex... the quote may end a quoted block, or
					// escape another quote.
					// do a 1-char lookahead:
					if (inQuotes // we are in quotes, therefore there can be
							// escaped quotes in here.
							&& nextLine.length() > (i + 1) // there is indeed
							// another character
							// to check.
							&& nextLine.charAt(i + 1) == quotechar) { // ..and
						// that
						// char.
						// is a
						// quote
						// also.
						// we have two quote chars in a row == one quote char,
						// so consume them both and
						// put one on the token. we do *not* exit the quoted
						// text.
						sb.append(nextLine.charAt(i + 1));
						i++;
					} else {
						inQuotes = !inQuotes;
						// the tricky case of an embedded quote in the middle:
						// a,bc"d"ef,g
						if (i > 2 // not on the begining of the line
								&& nextLine.charAt(i - 1) != this.separator // not
								// at
								// the
								// begining
								// of
								// an
								// escape
								// sequence
								&& nextLine.length() > (i + 1) && nextLine.charAt(i + 1) != this.separator // not
						// at
						// the
						// end
						// of
						// an
						// escape
						// sequence
						) {
							sb.append(c);
						}
					}
				} else if (c == separator && !inQuotes) {
					tokensOnThisLine.add(sb.toString());
					sb = new StringBuffer(); // start work on next token
				} else {
					sb.append(c);
				}
			}
		} while (inQuotes);
		tokensOnThisLine.add(sb.toString());
		return (String[]) tokensOnThisLine.toArray(new String[0]);

	}

	/**
	 * Closes the underlying reader.
	 * 
	 * @throws IOException
	 *             if the close fails
	 */
	public void close() throws IOException {
		if (type == TYPE_READ)
			br.close();
		else if (type == TYPE_WRITE)
			bw.close();
	}

}
