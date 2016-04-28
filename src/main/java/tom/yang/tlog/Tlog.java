package tom.yang.tlog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tlog {

	private static final char BLANK_FILL = ' ';

	public static final Tlogconf config = new Tlogconf();

	private static final int MAX_SECTION_LENGTH = 100;

	private static final int MIN_SLICER_LENGTH=31;

	private static final String SLICER ;

	private static final char SLICER_FILL = '-';

	static {
		char[] slicer = new char[MIN_SLICER_LENGTH];
		for (int i = 0; i < MIN_SLICER_LENGTH; ++i) {
			slicer[i] = SLICER_FILL;
		}
		SLICER = new String(slicer);
	}


	public static int fastLog(final String msg) {
		if (msg == null || !(msg.length() > 0)) {
			return Tlogcode.NO_CONTENT;
		}
		final File f = new File(config.logpath);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (final IOException e) {
				return Tlogcode.CREATE_FILE_FAILED;
			}
		}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(f, true))) {
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
			writer.write(sdf.format(new Date()) + msg);
			writer.newLine();
			writer.flush();
			writer.close();
		} catch (final IOException e) {
			return Tlogcode.WROTE_FAIL;
		}
		return Tlogcode.OK;
	}

	public static void printSection(String string) {
		// caculate number of blank-fill.
		int l=string.length();

		int sl = SLICER.length();
		// do validate arguement.
		if (l == 0) {
			throw new IllegalArgumentException("string is empty.");
		} else if (l > MAX_SECTION_LENGTH) {
			StringBuilder sb=new StringBuilder();
			sb.append("string length ").append(l).append(" is greater than ").append(MAX_SECTION_LENGTH);
			throw new IllegalArgumentException(sb.toString());
		}

		if (l <= sl / 2 + 1) {
			int black = (sl - (l * 2 - 1)) / 2;

			System.out.println(SLICER);

			char[] cs = new char[black + l * 2 - 1];
			// fill a black string
			for (int i = 0; i < black; ++i) {
				cs[i] = BLANK_FILL;
			}
			// add title
			for (int i = 0; i < l; ++i) {
				cs[2 * i + black] = string.charAt(i);
				if (i != l - 1) {
					cs[2 * i + black + 1] = BLANK_FILL;
				}
			}

			System.out.println(new String(cs).toUpperCase());

			System.out.println(SLICER);

		} else {
			int size=2*l-1;
			char[] cs=new char[size];
			for(int i=0;i<size;++i){
				cs[i] = SLICER_FILL;
			}
			char[] sec = new char[size];
			for (int i = 0; i < size; ++i) {
				if (i % 2 == 0) {
					sec[i] = string.charAt(i / 2);
				} else {
					sec[i] = BLANK_FILL;
				}
			}
			System.out.println(cs);
			System.out.println(sec);
			System.out.println(cs);
		}

	}

}
