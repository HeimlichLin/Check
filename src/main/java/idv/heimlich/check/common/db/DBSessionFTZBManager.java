package idv.heimlich.check.common.db;

public class DBSessionFTZBManager extends AbstractDBSessionManager {
	
	public static final String PROP_DEFAULT_CONN_ID = "PFTZBPool"; // "apConn";

	@Override
	protected String getConnId() {
		return PROP_DEFAULT_CONN_ID;
	}

}
