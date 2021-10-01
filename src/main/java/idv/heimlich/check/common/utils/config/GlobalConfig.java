package idv.heimlich.check.common.utils.config;

import java.util.Locale;
import java.util.TimeZone;

public class GlobalConfig {
	
	/***
	 * 日期PATTERN:yyyyMMddHHmm
	 */
	public static final String DATE_FORMAT_12="yyyyMMddHHmm";
	/***
	 * 日期PATTERN:yyyyMMddHHmmss
	 */
	public static final String DATE_FORMAT_14="yyyyMMddHHmmss";
	/***
	 * 日期PATTERN:yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATE_FORMAT_14_ALL="yyyy-MM-dd HH:mm:ss";
	/***
	 * 日期PATTERN:yyyyMMdd HH:mm:ss
	 */
	public static final String DATE_FORMAT_14_NO_DASH="yyyyMMdd HH:mm:ss";
	/***
	 * 資料庫日期轉換格式:YYYYMMDD HH24:MI:SS
	 */
	public static final String DATE_FORMAT_DB = "YYYYMMDD HH24:MI:SS";
	/***
	 * 時區
	 */
	public static final TimeZone DATE_TIMEZONE= TimeZone.getTimeZone("TST");
	
	public static final Locale DEFAULT_LOCALE = Locale.TAIWAN;

}
