package com.github.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.github.pojo.Column;
import com.github.pojo.Table;

public class JDBCMsg {

	/**
	 * 取一个数据库中所有表的信�?
	 * 
	 * @throws SQLException
	 */
	public static List<Table> getTables(List<String> names) throws SQLException {
		Connection conn = JDBCUtil.getConnection();

		System.out.println("######  DatabaseMetaData关于数据库的整体综合信息====");
		java.sql.DatabaseMetaData dbmd = conn.getMetaData();
		/*
		 * System.out.println("数据库产品名: " + dbmd.getDatabaseProductName());
		 * System.out.println("数据库是否支持事�? " + dbmd.supportsTransactions());
		 * System.out.println("数据库产品的版本�?"+dbmd.getDatabaseProductVersion());
		 * System
		 * .out.println("数据库的默认事务隔离级别:"+dbmd.getDefaultTransactionIsolation());
		 * System.out.println("支持批量更新:"+dbmd.supportsBatchUpdates());
		 * System.out.println("DBMS �?URL:"+dbmd.getURL());
		 * System.out.println("数据库的已知的用户名�?"+dbmd.getUserName());
		 * System.out.println("数据库是否处于只读模�?"+dbmd.isReadOnly());
		 * System.out.println("数据库是否支持为列提供别�?"+dbmd.supportsColumnAliasing());
		 * System
		 * .out.println("是否支持指定 LIKE 转义子句:"+dbmd.supportsLikeEscapeClause());
		 * System
		 * .out.println("是否为外连接提供受限制的支持:"+dbmd.supportsLimitedOuterJoins());
		 * System
		 * .out.println("是否允许�?��打开多个事务:"+dbmd.supportsMultipleTransactions());
		 * System
		 * .out.println("是否支持 EXISTS 表达式中的子查询:"+dbmd.supportsSubqueriesInExists
		 * ());
		 * System.out.println("是否支持 IN 表达式中的子查询:"+dbmd.supportsSubqueriesInIns
		 * ());
		 * System.out.println("是否支持给定事务隔离级别:"+dbmd.supportsTransactionIsolationLevel
		 * (1)); System.out.println("此数据库是否支持事务:"+dbmd.supportsTransactions());
		 * System.out.println("此数据库是否支持 SQL UNION:"+dbmd.supportsUnion());
		 * System
		 * .out.println("此数据库是否支持 SQL UNION ALL:"+dbmd.supportsUnionAll());
		 * System.out.println("此数据库是否为每个表使用�?��文件:"+dbmd.usesLocalFilePerTable());
		 * System.out.println("此数据库是否将表存储在本地文件中:"+dbmd.usesLocalFiles());
		 * System.out.println("底层数据库的主版本号:"+dbmd.getDatabaseMajorVersion());
		 * System.out.println("底层数据库的次版本号:"+dbmd.getDatabaseMinorVersion());
		 * 
		 * System.out.println("JDBC 驱动程序的主版本�?"+dbmd.getJDBCMajorVersion());
		 * System.out.println("JDBC 驱动程序的次版本�?"+dbmd.getJDBCMinorVersion());
		 * System.out.println("JDBC 驱动程序的名�?"+dbmd.getDriverName());
		 * System.out.println
		 * ("JDBC 驱动程序�?String 形式的版本号:"+dbmd.getDriverVersion());
		 * 
		 * System.out.println("可以在不带引号的标识符名称中使用的所有�?额外”字�?"+dbmd.
		 * getExtraNameCharacters());
		 * System.out.println("用于引用 SQL 标识符的字符�?"+dbmd
		 * .getIdentifierQuoteString());
		 * System.out.println("允许用于类别名称的最大字符数:"+dbmd.getMaxCatalogNameLength());
		 * System.out.println("允许用于列名称的�?��字符�?"+dbmd.getMaxColumnNameLength());
		 * System
		 * .out.println("允许�?GROUP BY 子句中使用的�?��列数:"+dbmd.getMaxColumnsInGroupBy
		 * ());
		 * System.out.println("允许�?SELECT 列表中使用的�?��列数:"+dbmd.getMaxColumnsInSelect
		 * ()); System.out.println("允许在表中使用的�?��列数:"+dbmd.getMaxColumnsInTable());
		 * System.out.println("数据库的并发连接的可能最大数:"+dbmd.getMaxConnections());
		 * System.out.println("允许用于游标名称的最大字符数:"+dbmd.getMaxCursorNameLength());
		 * System.out.println("在同�?��间内可处于开放状态的�?��活动语句�?"+dbmd.getMaxStatements());
		 */

		// 获取�?���?new String[]{"TABLE"}
		// String[] type = {"TABLE","VIEW"} null
		System.out.println("###### 获取表的信息");
		ResultSet tSet = dbmd.getTables(null, "%", "tb_%", new String[] {
				"TABLE", "VIEW" });

		List<Table> tables = new ArrayList<Table>();
		int skip = 0;
		while (tSet.next()) {
			// System.out.println(tSet.getRow()+"_表类�?"+tSet.getString("TABLE_CAT")+"_表模�?"+tSet.getString("TABLE_SCHEM")
			// +"_表名�?"+tSet.getString("TABLE_NAME")+"_表类�?"+tSet.getString("TABLE_TYPE")
			// //+"\n_表的解释性注�?"+tSet.getString("REMARKS")+"_类型的类�?"+tSet.getString("TYPE_CAT")
			// //+"\n_类型模式:"+tSet.getString("TYPE_SCHEM")+"_类型名称:"+tSet.getString("TYPE_NAME")
			// //+"\n_有类型表的指�?identifier'列的名称:"+tSet.getString("SELF_REFERENCING_COL_NAME")
			// //+"\n_指定�?SELF_REFERENCING_COL_NAME 中创建�?的方�?"+tSet.getString("REF_GENERATION")
			// );
			// 2_表类�?MANOR_表模�?PUBLIC_表名�?SYS_RESOURCE_表类�?TABLE
			String tableName = tSet.getString(3);
			System.out.println(tableName);
			if (null != names && !names.contains(tableName)) {
				continue;
			}

			String sql = "select * from " + tableName;

			try {
				ResultSet rsSet = conn.createStatement().executeQuery(sql);
				ResultSetMetaData rsData = rsSet.getMetaData();

				Table table = new Table();
				table.setTableName(tableName);

				List<Column> columns = new ArrayList<Column>();

				for (int i = 1; i <= rsData.getColumnCount(); i++) {

					Column column = new Column();

					String commentSql = "select *  from USER_COL_COMMENTS where "
							+ "table_name= '"
							+ tableName
							+ "'"
							+ " and column_name= '"
							+ rsData.getColumnName(i)
							+ "' ";
					// ResultSet comSet = conn.createStatement().executeQuery(
					// commentSql);
					// while (comSet.next()) {
					// if (comSet.getString(3) == null) {
					// System.out.println("�? + tableName + "没有注释");
					// }
					// String[] split = comSet.getString(3).split("�?);
					// column.setComment(split.length > 0 ? split[0] : comSet
					// .getString(3));
					// }

					column.setName(rsData.getColumnName(i));
					column.setType(rsData.getColumnClassName(i));
					column.setLength(rsData.getPrecision(i));
					column.setPrecion(rsData.getPrecision(i));
					column.setScale(rsData.getScale(i));
					column.setNullable(rsData.isNullable(i));
					columns.add(column);
				}
				// String zjSql =
				// "select   col.column_name from user_constraints con,user_cons_columns col where con.constraint_name=col.constraint_name and con.constraint_type='P' and col.table_name='"
				// + tableName + "'";
				// ResultSet comSet1 =
				// conn.createStatement().executeQuery(zjSql);
				// List<String> list = new ArrayList();
				// while (comSet1.next()) {
				// list.add(comSet1.getString(1) + "");
				// for (Column column : columns) {
				// if (column.getName().equals(comSet1.getString(1))) {
				// column.setPk(true);
				// }
				// }
				// }
				//
				// table.setPk(list);

				// String zjSql =
				// "select a.table_name,b.comments   from   user_tables a,ALL_TAB_COMMENTS b where a.table_name=b.table_name and a.table_name='"
				// + tableName + "'";
				// ResultSet comSet1 =
				// conn.createStatement().executeQuery(zjSql);
				// while (comSet1.next()) {
				// table.setComments(comSet1.getString(2) + "");
				// }
				table.setColumns(columns);
				tables.add(table);

			} catch (Exception e) {

			}

			skip++;
			if (skip == 100) {
				break;
			}

		}
		tSet.close();

		/*
		 * System.out.println("###### 获取当前数据库所支持的SQL数据类型"); ResultSet tableType
		 * = dbmd.getTypeInfo(); while(tableType.next()){
		 * System.out.println("数据类型�?"+tableType.getString(1)
		 * +",短整型的�?"+tableType.getString(2) +",整型的数:"+tableType.getString(3)
		 * +",�?��精度:"+tableType.getString(14) +",�?��精度:"+tableType.getString(15));
		 * //数据类型�?TIMESTAMP,短整型的�?93,整型的数:23,�?��精度:0,�?��精度:10
		 * //数据类型�?VARCHAR,短整型的�?12,整型的数:2147483647,�?��精度:0,�?��精度:0 }
		 * 
		 * System.out.println("###### 表的主键列信�?); ResultSet primaryKey =
		 * dbmd.getPrimaryKeys("MANOR","PUBLIC","SYS_ROLE_RES");
		 * while(primaryKey.next()){
		 * System.out.println("表名:"+primaryKey.getString
		 * ("TABLE_NAME")+",列名:"+primaryKey.getString("COLUMN_NAME")
		 * +" 主键�?"+primaryKey.getString("PK_NAME"));
		 * //表名:SYS_ROLE_RES,列名:SYS_RES_ID 主键�?CONSTRAINT_9
		 * //表名:SYS_ROLE_RES,列名:SYS_ROLE_ID 主键�?CONSTRAINT_9 }
		 * 
		 * System.out.println("###### 表的外键列信�?); ResultSet foreinKey =
		 * dbmd.getImportedKeys("MANOR","PUBLIC","SYS_ROLE_RES");
		 * while(foreinKey.next()){
		 * System.out.println("主键�?"+foreinKey.getString
		 * ("PK_NAME")+",外键�?"+foreinKey.getString("FKCOLUMN_NAME")
		 * +",主键表名:"+foreinKey
		 * .getString("PKTABLE_NAME")+",外键表名:"+foreinKey.getString
		 * ("FKTABLE_NAME")
		 * +",外键列名:"+foreinKey.getString("PKCOLUMN_NAME")+",外键序号:"
		 * +foreinKey.getString("KEY_SEQ"));
		 * //主键�?PRIMARY_KEY_95,外键�?SYS_RES_ID,
		 * 主键表名:SYS_RESOURCE,外键表名:SYS_ROLE_RES,外键列名:ID,外键序号:1
		 * //主键�?PRIMARY_KEY_A
		 * ,外键�?SYS_ROLE_ID,主键表名:SYS_ROLE,外键表名:SYS_ROLE_RES,外键列名:ID,外键序号:1 }
		 * 
		 * System.out.println("###### 获取数据库中允许存在的表类型"); ResultSet tableTypes =
		 * dbmd.getTableTypes(); while(tableTypes.next()){
		 * System.out.println("类型�?"+tableTypes.getString(1)); /** H2 类型�?SYSTEM
		 * TABLE 类型�?TABLE 类型�?TABLE LINK 类型�?VIEW
		 * 
		 * }
		 */

		// 此外还可以获取索引等的信�?
		conn.close();

		return tables;
	}

	/**
	 * PreparedStatement 信息 ResultSetMetaData 信息
	 * 
	 * @throws SQLException
	 */
	public static void getDBParameterMetaData() throws SQLException {
		Connection conn = JDBCUtil.getConnection(); // id,name
		PreparedStatement pre = conn
				.prepareStatement("SELECT * FROM SYS_APPTYPE where id = ?");
		pre.setInt(1, 3);
		java.sql.ParameterMetaData pmd = pre.getParameterMetaData();
		System.out.println("参数的个�?" + pmd.getParameterCount());
		System.out.println("获取指定参数�?SQL 类型:" + pmd.getParameterType(1));
		System.out.println("culomn的参数类�?" + pmd.getParameterTypeName(1));
		System.out.println("Java 类的完全限定名称:" + pmd.getParameterClassName(1));
		System.out.println("获取指定参数的模�?" + pmd.getParameterMode(1));
		System.out.println("获取指定参数的指定列大小:" + pmd.getPrecision(1));
		System.out.println("获取指定参数的小数点右边的位�?" + pmd.getScale(1));
		System.out.println("是否允许在指定参数中使用 null �?" + pmd.isNullable(1));
		System.out.println("指定参数的�?是否可以是带符号的数�?" + pmd.isSigned(1));

		// 获取结果集元数据
		ResultSet rs = pre.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString(1) + "___" + rs.getString(2));
		}
		rs.close();
	}

	/**
	 * 获取�?��Driver信息
	 */
	public static void getAllDriverMsg() {
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			Driver d = drivers.nextElement();
			System.out.println(d.getClass().getName() + "_"
					+ d.getMajorVersion());
		}

	}
}