package idv.heimlich.check.common.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import idv.heimlich.check.common.utils.config.GlobalConfig;

public class CommonUtils {

	/***
	 * 轉換日期時間
	 * @param dateStr 傳入日期格式為 "yyyy-MM-dd HH:mm:ss"
	 * @return String 轉換成格式為 "yyyyMMdd HH:mm:ss"
	 * @throws Exception
	 */
	public static String convertDate(String dateStr) throws Exception{			
		SimpleDateFormat srcSdf = new SimpleDateFormat(GlobalConfig.DATE_FORMAT_14_ALL,GlobalConfig.DEFAULT_LOCALE);
		srcSdf.setTimeZone(GlobalConfig.DATE_TIMEZONE);
		Date rlsDate = srcSdf.parse(dateStr);		
		SimpleDateFormat tarSdf = new SimpleDateFormat(GlobalConfig.DATE_FORMAT_14_NO_DASH,GlobalConfig.DEFAULT_LOCALE);	
		tarSdf.setTimeZone(GlobalConfig.DATE_TIMEZONE);
		return tarSdf.format(rlsDate);				
	}
	
	public static Timestamp formObj(Object obj) {
		return (obj == null || "".equals(obj))? null:Timestamp.valueOf(obj.toString());
	}
	
}
