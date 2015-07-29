package com.github.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {
	private static Properties ps = new Properties();// Properties���ʾ��һ���־õ����Լ�
	static {
		try {
			ps.load(JDBCUtil.class
					.getResourceAsStream("conf.properties"));// ���������ж�ȡ�����б?���Ԫ�ضԣ���
			Class.forName(ps.getProperty("driver"));// getResourceAsStream���Ҿ��и���Ƶ���Դ��
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {// Connection���ض���ݿ�����ӣ��Ự����
		try {
			return DriverManager.getConnection(ps.getProperty("url"),// DriverManager����һ��
																		// JDBC
																		// �����Ļ����

					ps.getProperty("username"), ps.getProperty("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void close(Connection conn, Statement stm, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stm != null) {
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
