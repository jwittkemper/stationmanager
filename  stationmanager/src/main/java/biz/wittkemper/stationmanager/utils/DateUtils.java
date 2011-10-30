package biz.wittkemper.stationmanager.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	static SimpleDateFormat format = new SimpleDateFormat( "dd-MMM-yyyy" );
	
	public static Date getDate(String date){
		try {
			return format.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

}
