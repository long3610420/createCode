package com.github.pojo;

import java.util.List;

import com.github.common.FileUtil;
import com.github.common.StringUtil;

public class XmlMaker {

	public void createXmlFile(Table table) throws Exception {

		String className = StringUtil.makeClassName(table.getTableName()
				.substring(3, table.getTableName().length()));

		StringBuffer content = new StringBuffer();
//		content.append(getTitle());
//		content.append(getDeclare(className,
//				table.getTableName()
//						.substring(3, table.getTableName().length())));
		content.append(getProperties(table.getColumns(), table.getPk()));
//		content.append(getEnd());
		FileUtil.writeFile("xmlfile\\" + className + ".xml",
				content.toString());
	}

	private String getTitle() {
		StringBuffer title = new StringBuffer();
		title.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		title.append("<!DOCTYPE hibernate-mapping PUBLIC \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\"\n");
		title.append("\"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\">\n");
		title.append("<!-- \n");
		title.append("	Mapping file autogenerated by Audit Cood Generator Tools \n");
		title.append("-->\n");
		return title.toString();

	}

	private String getDeclare(String className, String tableName) {
		StringBuffer declare = new StringBuffer();
		declare.append("<hibernate-mapping>\n");
		declare.append("	<class name=\"com.pojo." + className + "\"\n");
		declare.append("		table=\"tbl" + tableName
				+ "\"  dynamic-update=\"true\" dynamic-insert=\"true\"> \n");
		return declare.toString();

	}

	private String getProperties(List<Column> columns, List<String> pk) {

		StringBuffer properties = new StringBuffer();
//		if (null != pk && pk.size() > 1) {
//			properties.append("        <composite-id>\n");
//			for (String str : pk) {
//				properties
//						.append("       <key-property name=\""
//								+ StringUtil.makeParmeterName(str)
//								+ "\" type=\"java.lang.String\">\n"
//								+ "            <column name=\"" + str
//								+ "\" length=\"32\" />\n"
//								+ "        </key-property>\n");
//			}
//			properties.append("        </composite-id>\n");
//		} else {
//			properties
//					.append("       <id name=\"id\" type=\"java.lang.Integer\">\n"
//							+ "            <column name=\"id\" />\n"
//
//							+ "            <generator class=\"native\" /> \n"
//							+ "        </id>\n");
//
//		}
		for (Column column : columns) {
			if (!column.isPk()) {
				properties.append("         #{item."
						+ StringUtil.firstLowerCase(column.getName()) + "},\n");
				
			}

		}
		for (Column column : columns) {
			if (!column.isPk()) {
				properties.append("         "
						+ column.getName() + ",\n");
				
			}
			
		}

		return properties.toString();

	}

	private String getEnd() {
		StringBuffer end = new StringBuffer();
		end.append("\n\n	    </class>\n");
		end.append("</hibernate-mapping>\n");
		return end.toString();
	}

	private String getLength(Column column) {
		String length = "";

		if (column.getType().equals("java.lang.String")) {

			length = "length=\"" + column.getLength() + "\" ";
		}
		if (column.getType().equals("java.sql.Timestamp")) {

			length = "length=\"7" + "\" ";
		}

		if (column.getType().equals("java.lang.Double")) {

			length = "";
		}
		if (column.getType().equals("java.math.BigDecimal")) {

			length = "precision=\"" + column.getPrecion() + "\" scale=\""
					+ column.getScale() + "\" ";
		}

		return length;
	}

}
