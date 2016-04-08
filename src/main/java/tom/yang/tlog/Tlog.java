package tom.yang.tlog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tlog {
    public static final Tlogconf config = new Tlogconf();

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
}
