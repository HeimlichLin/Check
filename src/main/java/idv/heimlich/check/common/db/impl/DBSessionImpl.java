package idv.heimlich.check.common.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;

import idv.heimlich.check.common.dao.GeneralDAO;
import idv.heimlich.check.common.dao.IConverter;
import idv.heimlich.check.common.dao.RowMap;
import idv.heimlich.check.common.dao.RowMapList;
import idv.heimlich.check.common.dao.TableMapper;
import idv.heimlich.check.common.db.IDBSession;
import idv.heimlich.check.common.db.sql.SqlCode;
import idv.heimlich.check.common.db.sql.SqlObject;
import idv.heimlich.check.common.db.sql.SqlWhere;
import idv.heimlich.check.common.db.utils.ConnectionUtil;
import idv.heimlich.check.common.exception.TxBusinessException;
import idv.heimlich.check.common.log.LogFactory;

public class DBSessionImpl implements IDBSession {

	private static Logger LOGGER = LogFactory.getInstance();
	private Connection connection;

	private Connection initial() {
		try {
			if (this.connection == null || this.connection.isClosed()) {
				this.connection = ConnectionUtil.getConnection();
			}
			return this.connection;
		} catch (SQLException e) {
			LOGGER.debug("DBSessionImpl initial fail", e);
			throw new TxBusinessException("DBSessionImpl initial fail", e);
		}
	}

	@Override
	public Connection getConnection() {
		return this.initial();
	}

	@Override
	public void beginTransaction() {
		try {
			this.initial().setAutoCommit(false);
		} catch (final SQLException e) {
			LOGGER.debug("DBSessionImpl beginTransaction fail", e);
			throw new TxBusinessException(
					"DBSessionImpl beginTransaction fail", e);
		}
	}

	@Override
	public void commit() {
		try {
			this.initial().commit();
		} catch (final SQLException e) {
			LOGGER.debug("DBSessionImpl commit fail", e);
			throw new TxBusinessException("DBSessionImpl commit fail", e);
		}
	}

	@Override
	public void close() {
		try {
			this.initial().close();
		} catch (final SQLException e) {
			LOGGER.debug("DBSessionImpl close fail", e);
			throw new TxBusinessException("DBSessionImpl close fail", e);
		}
	}

	@Override
	public void rollback() {
		try {
			this.initial().rollback();
			this.initial().close();
		} catch (final SQLException e) {
			LOGGER.debug("DBSessionImpl rollback fail", e);
			throw new TxBusinessException("DBSessionImpl rollback fail", e);
		}
	}

	@Override
	public RowMapList query(String sql) {
		PreparedStatement preparedStatement = null; 
		ResultSet resultSet = null;
		try {
			final Connection connection = this.initial();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			RowMapList result = this.result2RowMapList(resultSet);
			return result;
		} catch (final Exception e) {
			LOGGER.debug("DBSessionImpl query fail", e);
			throw new TxBusinessException("DBSessionImpl query fail : " + sql,
					e);
		} finally {
			ConnectionUtil.close(preparedStatement, resultSet);
		}
	}

	@Override
	public RowMapList query(String sql, SqlWhere sqlWhere) {
		PreparedStatement preparedStatement = null; 
		ResultSet resultSet = null;
		try {
			final Connection connection = this.initial();
			preparedStatement = connection
					.prepareStatement(sql);
			for (String key : sqlWhere.toMap().keySet()) {
				if (sqlWhere.toMap().get(key) instanceof String) {
					preparedStatement.setString(Integer.parseInt(key), sqlWhere
							.toMap().get(key));
				} else {
					preparedStatement.setObject(Integer.parseInt(key), sqlWhere
							.toMap().get(key));
				}
			}
			resultSet = preparedStatement.executeQuery();
			RowMapList result = this.result2RowMapList(resultSet);
			return result;
		} catch (final Exception e) {
			LOGGER.debug("DBSessionImpl query fail", e);
			throw new TxBusinessException("DBSessionImpl query fail : " + sql,
					e);
		} finally {
			ConnectionUtil.close(preparedStatement, resultSet);
		}
	}

	@Override
	public <Po> List<Po> select(IConverter<Po> converter, String sql) {
		final RowMapList rowMapList = this.query(sql);
		final List<Po> pos = new ArrayList<Po>();
		final Iterator<RowMap> rowMapIterator = rowMapList.iterator();
		while (rowMapIterator.hasNext()) {
			pos.add(converter.convert(rowMapIterator.next()));
		}
		return pos;
	}

	@Override
	public <Po> List<Po> select(IConverter<Po> converter, String sql,
			SqlWhere sqlWhere) {
		final RowMapList rowMapList = this.query(sql, sqlWhere);
		final List<Po> pos = new ArrayList<Po>();
		final Iterator<RowMap> rowMapIterator = rowMapList.iterator();
		while (rowMapIterator.hasNext()) {
			pos.add(converter.convert(rowMapIterator.next()));
		}
		return pos;
	}

	@Override
	public int count(final String sql) {
		PreparedStatement preparedStatement = null; 
		ResultSet resultSet = null;
		try {
			final Connection connection = this.initial();
			preparedStatement = connection
					.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			resultSet = preparedStatement.executeQuery();
			return (resultSet.last() == true) ? resultSet.getRow() : 0;
		} catch (SQLException e) {
			LOGGER.debug("DBSessionImpl count fail", e);
			throw new TxBusinessException("DBSessionImpl count fail : " + sql,
					e);
		} finally {
			ConnectionUtil.close(preparedStatement, resultSet);
		}
	}

	@Override
	public int executeUpdate(final String sql) {
		PreparedStatement preparedStatement = null;
		try {
			final Connection connection = this.initial();
			preparedStatement = connection
					.prepareStatement(sql);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.debug("DBSessionImpl executeUpdate fail", e);
			throw new TxBusinessException("DBSessionImpl executeUpdate fail : "
					+ sql, e);
		} finally {
			ConnectionUtil.close(preparedStatement);
		}
	}

	@Override
	public int executeInsert(final String sql, Map<String, Object> map) {
		PreparedStatement preparedStatement = null;
		try {
			final Connection connection = this.initial();
			preparedStatement = connection
					.prepareStatement(sql);
			Object[] valuse = map.values().toArray();
			for (int i = 0; i < valuse.length; i++) {
				if (valuse[i] instanceof String) {
					preparedStatement.setString(i + 1, valuse[i].toString());
				} else {
					preparedStatement.setObject(i + 1, valuse[i]);
				}
			}
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.debug("DBSessionImpl executeUpdate fail", e);
			throw new TxBusinessException("DBSessionImpl executeUpdate fail : "
					+ sql, e);
		} finally {
			ConnectionUtil.close(preparedStatement);
		}
	}

	@Override
	public <Po> int insertPos(List<Po> datas) {
		int total = 0;
		Object po;
		for (Iterator<Po> arg3 = datas.iterator(); arg3.hasNext(); total += this
				.insertPo(po)) {
			po = (Object) arg3.next();
		}
		return total;
	}

	@Override
	public <Po> int insertPo(Po object) {
		GeneralDAO<Po> dao = TableMapper.lookupDAO(object);
		return this.insert(dao.getTableName(), dao.getRowMap(object));
	}

	@Override
	public int insert(String tableName, RowMap rowMap) {
		if (rowMap.getMap().isEmpty()) {
			return 0;
		}
		SqlObject sqlObject = new SqlObject(tableName);
		for (Entry<String, Object> entry : rowMap.getMap().entrySet()) {
			sqlObject.setSqlValue(entry.getKey(), entry.getValue());
		}
		String sql = SqlCode.creatInsertSql(sqlObject);
		return this.executeInsert(sql, rowMap.getMap());
	}

	private RowMapList result2RowMapList(final ResultSet resultSet) {
		int count;
		try {
			final ResultSetMetaData rsmd = resultSet.getMetaData();
			count = rsmd.getColumnCount();
			final RowMapList rowMapList = new RowMapList();
			while (resultSet.next()) {
				final RowMap rowMap = new RowMap();
				for (int i = 1; i <= count; i++) {
					rowMap.setValue(rsmd.getColumnName(i), resultSet
							.getObject(i) == null ? "" : resultSet.getObject(i));
				}
				rowMapList.add(rowMap);
			}
			return rowMapList;
		} catch (final Exception e) {
			LOGGER.debug("DBSessionImpl result2RowMapList fail", e);
			throw new TxBusinessException(
					"DBSessionImpl result2RowMapList fail", e);
		}
	}

}
