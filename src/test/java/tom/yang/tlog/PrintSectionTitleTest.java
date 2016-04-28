package tom.yang.tlog;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class PrintSectionTitleTest {
	private static final String answer = "-------------------------------\r\n         S E C T I O N\r\n-------------------------------\r\n";

	private static final Object bigest_answer = "-------------------------------\r\n1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7\r\n-------------------------------\r\n";

	private static final Object more_large_answer="-----------------------------------------------------\r\n1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9\r\n-----------------------------------------------------\r\n";

	private static final Object smalest_answer = "-------------------------------\r\n               1\r\n-------------------------------\r\n";

	@Rule
	public ExpectedException thrown = ExpectedException.none();;;

	@Test
	public void testBigestLength() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Tlog.printSection("1234567891234567");
		Assert.assertEquals(bigest_answer, out.toString());
	}

	@Test
	public void testEmpty() {
		thrown.expect(IllegalArgumentException.class);
		Tlog.printSection("");
	}

	@Test
	public void testLargeString() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Tlog.printSection("123456789123456789123456789");
		Assert.assertEquals(more_large_answer, out.toString());
	}

	@Test
	public void testNormal(){
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Tlog.printSection("sectIon");
		Assert.assertEquals(answer,out.toString());
	}

	@Test
	public void testNull() {
		thrown.expect(NullPointerException.class);
		Tlog.printSection(null);
	}

	@Test
	public void testSmallestLength() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Tlog.printSection("1");
		Assert.assertEquals(smalest_answer, out.toString());
	}

}
