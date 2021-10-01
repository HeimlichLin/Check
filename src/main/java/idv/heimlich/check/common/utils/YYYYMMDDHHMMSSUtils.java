package idv.heimlich.check.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import idv.heimlich.check.common.exception.ApBusinessException;

public class YYYYMMDDHHMMSSUtils {

	protected static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddhhmmss");

	public static String getText(Date date) {
		return SDF.format(date);
	}

	public static String getText() {
		return getText(new Date());
	}

	public static Date parse(String yyyyMMddmmss) {
		try {
			return SDF.parse(yyyyMMddmmss);
		} catch (final ParseException e) {
			throw new ApBusinessException("日期格式錯誤:" + yyyyMMddmmss);
		}
	}

}
