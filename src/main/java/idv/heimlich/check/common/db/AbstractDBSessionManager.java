package idv.heimlich.check.common.db;

import idv.heimlich.check.common.db.impl.DBSessionImpl;

public abstract class AbstractDBSessionManager {

	private static IDBSession dbSession;

	public static IDBSession getDBSession() {
		if (dbSession == null) {
			dbSession = new DBSessionImpl();
		}
		return dbSession;
	}

	protected abstract String getConnId();

}
