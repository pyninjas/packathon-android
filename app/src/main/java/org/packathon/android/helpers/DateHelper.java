package org.packathon.android.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

    private static final String TAG = "DateHelper";

    public static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ";
    public static SimpleDateFormat FORMAT = new SimpleDateFormat(DATE_FORMAT);

    public static Date getDate(String dateString) {
        Date date = null;
        try {
            date = FORMAT.parse(dateString.replaceAll("Z$", "+000000"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
