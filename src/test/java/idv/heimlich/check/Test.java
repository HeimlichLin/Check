package idv.heimlich.check;

import org.slf4j.Logger;

import idv.heimlich.check.common.db.IDBSession;
import idv.heimlich.check.common.db.IDBSessionFactory;
import idv.heimlich.check.common.db.impl.DBSessionFactoryImpl;
import idv.heimlich.check.common.log.LogFactory;

public class Test {
	
	private static Logger LOGGER = LogFactory.getInstance();
	
	public static void main(String[] args) {
		LOGGER.debug("Test Start");
		IDBSessionFactory sessionFactory = new DBSessionFactoryImpl();
		IDBSession session = sessionFactory.getXdaoSession("");
		
	}

}
