package com.github.common;

import java.util.HashMap;
import java.util.Map;

import com.github.pojo.Column;

public class StringUtil {

	static Map<String, String> maps = new HashMap<String, String>();
	static Map<String, String> fullMaps = new HashMap<String, String>();

	static {
		maps.put("java.lang.String", "String");
		maps.put("java.lang.Double", "Float");
		maps.put("java.math.BigDecimal", "Double");
		maps.put("java.sql.Timestamp", "Date");
		maps.put("oracle.sql.CLOB", "Clob");
		maps.put("java.lang.Integer", "int");
		maps.put("java.sql.Date", "Date");
		maps.put("java.lang.Float", "Float");

		fullMaps.put("java.sql.Date", "Date");
		fullMaps.put("java.lang.Integer", "java.lang.Integer");
		fullMaps.put("java.lang.String", "java.lang.String");
		fullMaps.put("java.lang.Double", "java.lang.Float");
		fullMaps.put("java.math.BigDecimal", "java.lang.Double");
		fullMaps.put("java.sql.Timestamp", "java.util.Date");
		fullMaps.put("oracle.sql.CLOB", "java.sql.Clob");
		fullMaps.put("java.lang.Float", "java.lang.Float");
	}

	/**
	 * ����������������
	 * 
	 * @param columnName
	 * @return
	 */
	public static String makeParmeterName(String columnName) {

		String[] parts = columnName.split("_");
		String parmeter = "";
		int i = 0;
		for (String p : parts) {

			if (i == 0) {
				parmeter = p.toLowerCase();
			}

			if (i > 0) {

				if (parmeter.length() == 1) {

					parmeter = parmeter + p.toLowerCase();
				} else {

					String firstLetter = p.substring(0, 1);
					String lastLetter = p.substring(1, p.length());

					parmeter = parmeter + firstLetter.toUpperCase()
							+ lastLetter.toLowerCase();
				}
			}

			i++;
		}

		return parmeter;
	}

	/**
	 * ��ݱ����������
	 * 
	 * @param tableName
	 * @return
	 */
	public static String makeClassName(String tableName) {

		String[] parts = tableName.split("_");

		String className = "";
		if (null != parts) {
			for (String p : parts) {
				String firstLetter = p.length() > 0 ? p.substring(0, 1) : "";
				String lastLetter = p.length() > 0 ? p.substring(1, p.length())
						: "";
				className = className + firstLetter.toUpperCase()
						+ lastLetter.toLowerCase();

			}
		}

		return className;
	}

	/**
	 * ����������ĸ��д
	 * 
	 * @param parmeter
	 * @return
	 */
	public static String firstUpCase(String parmeter) {

		return parmeter.substring(0, 1).toUpperCase()
				+ parmeter.substring(1, parmeter.length());
	}

	/**
	 * ����������ĸСд
	 * 
	 * @param parmeter
	 * @return
	 */
	public static String firstLowerCase(String parmeter) {

		return parmeter.substring(0, 1).toLowerCase()
				+ parmeter.substring(1, parmeter.length());
	}

	/**
	 * ����ݿ�����ӳ��Ϊjava����
	 * 
	 * @param column
	 * @return
	 */
	public static String changeType(Column column) {

		String type = column.getType();

		String copyType = type;
		type = maps.get(type);
		if (type == null) {
			System.out.println(copyType);
		}
		if (type.equals("Double")) {

			if (column.getScale() == 0) {
				if (column.getLength() < 5) {
					type = "int";
				} else {

					type = "Long";
				}
			}

		}
		if (type.equals("Integer")) {

			if (column.getScale() == 0) {
				if (column.getLength() < 5) {
					type = "int";
				} else {

					type = "Long";
				}
			}

		}

		return type;
	}

	/**
	 * ����ݿ�����ӳ��Ϊjava����
	 * 
	 * @param column
	 * @return
	 */
	public static String changeFullType(Column column) {

		String type = column.getType();

		type = fullMaps.get(type);
		if (type.equals("java.lang.Double")) {

			if (column.getScale() == 0) {
				if (column.getLength() < 5) {
					type = "java.lang.Integer";
				} else {

					type = "java.lang.Long";
				}
			}

		}

		return type;
	}

	/**
	 * ����ݿ�����ӳ��Ϊjava����
	 * 
	 * @param column
	 * @return
	 */
	public static String changeTypeCheckData(Column column) {

		String type = column.getType();
		String copyType = type;
		type = maps.get(type);
		if (type == null) {
			System.out.println(copyType);
		}
		if (type.equals("String")) {

			type = "1," + column.getLength() + ",0";
		}
		if (type.equals("Double")) {

			if (column.getScale() == 0) {
				type = "2," + column.getPrecion() + ",0";
			} else {
				type = "3," + column.getPrecion() + "," + column.getScale();
			}
		}
		if (type.equals("Date")) {

			type = "4,7,0";
		}
		if (type.equals("Float")) {

			type = "3," + column.getPrecion() + ",5";
		}

		return type;
	}

}
