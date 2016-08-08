package wang.ronnie.utils;

import wang.ronnie.global.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getCurrent() {
		return getCurrent("yyyy-MM-dd HH:mm:ss");
	}
	
	public static String getCurrent(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(System.currentTimeMillis()));
	}
	
	public static String format(long mills, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(mills));
	}
	
	public static long parse(String str, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(str).getTime();
		} catch (ParseException e) {
			Log.ex(e);
		}
		
		return 0;
	}
	
}
