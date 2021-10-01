package idv.heimlich.check.common.utils;

import idv.heimlich.check.common.exception.ApBusinessException;

public class ObjectsUtils {
	
	public static Object newInstance(String className) {
		try {
			Class<?> e = Class.forName(className);
			return e.newInstance();
		} catch (Exception arg1) {
			throw new ApBusinessException("建立失敗", arg1);
		}
	}

	public static <T> T newInstance(Class<T> pClass) {
		try {
			return pClass.newInstance();
		} catch (Exception arg1) {
			throw new ApBusinessException("建立失敗", arg1);
		}
	}

}
