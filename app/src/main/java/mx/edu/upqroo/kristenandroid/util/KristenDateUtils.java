package mx.edu.upqroo.kristenandroid.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class KristenDateUtils {
    private static SimpleDateFormat mFormatter = new SimpleDateFormat("EEEE, d MMMM, yyyy",
            Locale.getDefault());

    public static String formatDate(Date date) {
        return mFormatter.format(date);
    }
}
