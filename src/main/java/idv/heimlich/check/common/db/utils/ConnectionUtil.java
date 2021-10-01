package idv.heimlich.check.common.db.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;

import idv.heimlich.check.common.evn.EVNConfigProducer;
import idv.heimlich.check.common.log.LogFactory;

public class ConnectionUtil{

	private static Logger LOGGER = LogFactory.getInstance();

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(EVNConfigProducer.getConfig().getDriverClass());
			conn = DriverManager
					.getConnection(EVNConfigProducer.getConfig()
							.getConnectionIP(), EVNConfigProducer.getConfig()
							.getUserName(), EVNConfigProducer.getConfig()
							.getPassword());
		} catch (final SQLException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (final ClassNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return conn;
	}
	
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) 
			throws SQLException {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
