package idv.heimlich.check.common.db;

public interface IDBSessionFactory {

	IDBSession getXdaoSession(String conn);

}
