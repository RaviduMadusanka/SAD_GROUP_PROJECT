package SMMV.OL.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

    public static String Formact(String stringDate) {

        String str = stringDate;
        int spacePos = str.indexOf(" ");
        if (spacePos > 0) {
            String youString = str.substring(0, spacePos - 0);

            String myTime = youString;
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            Date date = null;
            try {
                date = sdf.parse(myTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            SimpleDateFormat dt1 = new SimpleDateFormat("hh:mm:ss");
            
            return dt1.format(date);
        } else {
            return null;
        }

    }

}
