package idv.heimlich.check.common.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import idv.heimlich.check.common.exception.ApBusinessException;
import idv.heimlich.check.common.utils.ObjectsUtils;

public enum TableMapper {
	
//	WAREHSE(WarehseDo.class, WarehseDAO.class),
	;

	final Class<?> doClas;
	final Class<?> daoClas;
	final static Map<Class<?>, Class<?>> MAP;
	
	private <PO, T extends GeneralDAO<PO>> TableMapper(final Class<PO> doClas, final Class<T> daoClas) {
		this.doClas = doClas;
		this.daoClas = daoClas;
	}
	
	static {
		final Map<Class<?>, Class<?>> map = new HashMap<Class<?>, Class<?>>();
		for (final TableMapper mapper : TableMapper.values()) {
			map.put(mapper.doClas, mapper.daoClas);
		}
		MAP = Collections.unmodifiableMap(map);
	}

	private static <PO> Class<?> lookupDAOByDo(final PO object) {
		if (!TableMapper.MAP.containsKey(object.getClass())) {
			throw new ApBusinessException("此無物件無定義" + object);
		}
		return TableMapper.MAP.get(object.getClass());
	}
	
	@SuppressWarnings("unchecked")
	public static <PO> GeneralDAO<PO> lookupDAO(final PO object) {
		final Class<?> daoClass = lookupDAOByDo(object);
		return (GeneralDAO<PO>) ObjectsUtils.newInstance(daoClass);
	}
	
//	public static <PO> Class<?> lookupDO(final String name) {
//		
//		TableMapper.valueOf(name);
////		if (TableMapper.valueOf(name))
//	}
	
//	public static <PO> Class<?> lookupDAO(PO object) {
////		if (!TableMapper.MAP.containsKey(object.getClass())) {
////			throw new ApBusinessException("此無物件無定義" + object);
////		}
//		lookupDAOByDo(object.getClass());
//		try {
//			UserSourceDAO aaaDao = (UserSourceDAO) TableMapper.MAP.get(object.getClass()).newInstance();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return aaaDao;
//	}
	
}
