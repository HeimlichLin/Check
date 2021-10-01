package idv.heimlich.check.common.db.sql;

import idv.heimlich.check.common.db.code.DBCommand;


public class SqlCode {
	
	public static String creatSelectSql(SqlObject sqlObject) {
		return String.format(DBCommand.SELECT.getSql(), sqlObject.getSqlColumn(), sqlObject.getSqlTableName(), sqlObject.getSqlWhere(), sqlObject.getSqlOrderBy());
	}
	
	public static String creatInsertSql(SqlObject sqlObject) {
		return String.format(DBCommand.INSERT.getSql(), sqlObject.getSqlTableName(), sqlObject.getSqlValue());
	}
	
	public static String creatUpdateSql(SqlObject sqlObject) {
		return String.format(DBCommand.UPDATE.getSql(), sqlObject.getSqlTableName(), sqlObject.getSqlSet(), sqlObject.getSqlWhere());
	}
	
	public static String creatDeleteSql(SqlObject sqlObject) {
		return String.format(DBCommand.DELETE.getSql(), sqlObject.getSqlTableName(), sqlObject.getSqlWhere());
	}

}
