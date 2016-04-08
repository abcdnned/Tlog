package tom.yang.tlog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

public class TlogTest {

    private final static String testpath = "D:/test/ttt.log";

    @AfterClass
    public static void clean() {
        File f = new File(testpath);
        if (f.exists() && !f.isDirectory()) {
            f.delete();
        }
    }

    @Test
    public void testFastlogSpecificPath() throws FileNotFoundException, IOException {
        final String path = testpath;
        final String tl1 = "testline1";
        final String tl2 = "testline2";
        Tlog.config.logpath = path;
        Tlog.fastLog(tl1);
        Tlog.fastLog(tl2);
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            Assert.assertTrue(reader.readLine().contains(tl1));
            Assert.assertTrue(reader.readLine().contains(tl2));
        }
    }

    @Test
    public void testFastlogNoContentFail() {
        Assert.assertEquals(Tlogcode.NO_CONTENT, Tlog.fastLog(""));
        Assert.assertEquals(Tlogcode.NO_CONTENT, Tlog.fastLog(null));
    }
}
