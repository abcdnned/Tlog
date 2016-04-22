package tom.yang.tlog;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class PrintSectionTitleTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private static final String answer = "-------------------------------\r\n         S E C T I O N\r\n-------------------------------\r\n";

    private static final Object bigest_answer = "-------------------------------\r\n1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7\r\n-------------------------------\r\n";

    private static final Object smalest_answer = "-------------------------------\r\n               1\r\n-------------------------------\r\n";;;

    @Test
    public void testNormal(){
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Tlog.printSection("sectIon");
        Assert.assertEquals(answer,out.toString());
    }

    @Test
    public void testSmallestLength() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Tlog.printSection("1");
        Assert.assertEquals(smalest_answer, out.toString());
    }

    @Test
    public void testNull() {
        thrown.expect(NullPointerException.class);
        Tlog.printSection(null);
    }

    @Test
    public void testEmpty() {
        thrown.expect(IllegalArgumentException.class);
        Tlog.printSection("");
    }

    @Test
    public void testBigestLength() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Tlog.printSection("1234567891234567");
        Assert.assertEquals(bigest_answer, out.toString());
    }

    @Test
    public void testLargeString() {
        try {
            Tlog.printSection("fdsfsfadsafdsafdsafdsafsdafa");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("string length is greater than 16.", e.getMessage());
        }
    }
}
