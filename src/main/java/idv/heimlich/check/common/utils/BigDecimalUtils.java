package idv.heimlich.check.common.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {
	
	public static BigDecimal formObj(Object obj) {
		return obj == null ? new BigDecimal(0) : new BigDecimal(String.valueOf(obj));
	}

}
