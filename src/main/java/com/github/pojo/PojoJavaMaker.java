package com.github.pojo;

import java.util.List;

import com.github.common.FileUtil;
import com.github.common.StringUtil;

public class PojoJavaMaker {

	public void createJavaFile(String adress, Table table) throws Exception {

		String className = StringUtil.makeClassName(table.getTableName()
				.substring(3, table.getTableName().length()));

		StringBuffer content = new StringBuffer();

		content.append(getImport());
		content.append(getTitle(table.getTableName().substring(4,
				table.getTableName().length())));
		content.append(getParmeters(table.getColumns()));
		content.append(getConstrator(table.getTableName().substring(4,
				table.getTableName().length())));
		content.append(getMethods(table.getColumns()));
		// content.append(toStr(table.getColumns()));

		content.append("\n\n }");
		FileUtil.writeFile(adress + "\\" + className + ".java",
				content.toString());
	}

	private String getImport() {

		String imports = "package com.pojo;\n\n";
		imports = imports + "import java.util.Date;" + "\n";
		imports = imports + "import java.sql.Clob;" + "\n";
		imports = imports + "import java.io.Serializable;" + "\n";
		imports = imports
				+ "import org.apache.commons.lang.builder.ToStringBuilder;"
				+ "\n";

		return imports;
	}

	private String getTitle(String tableName) {

		String className = StringUtil.makeClassName(tableName);

		StringBuffer title = new StringBuffer();

		title.append("public class ");
		title.append(className);
		title.append("  implements java.io.Serializable {\n\n\n");

		return title.toString();

	}

	/**
	 * ��ɲ����
	 * 
	 * @param columns
	 * @return
	 */
	private String getParmeters(List<Column> columns) {

		StringBuffer parmeters = new StringBuffer();

		for (Column column : columns) {

			parmeters.append("     private ");
			parmeters.append(StringUtil.changeType(column));
			parmeters.append(" ");
			parmeters.append(StringUtil.firstLowerCase(column.getName()));
			parmeters.append("; //");
			String comment = "";
			if (column.getComment() != null) {
				comment = column.getComment();
			}
			parmeters.append(comment + "\n");

		}
		return parmeters.toString();
	}

	/**
	 * ��ɹ��캯���
	 * 
	 * @param tableName
	 * @return
	 */
	private String getConstrator(String tableName) {

		String className = StringUtil.makeClassName(tableName);
		StringBuffer constrator = new StringBuffer();
		constrator.append("\n\n\n");
		constrator.append("    public ");
		constrator.append(className);
		constrator.append("() {\n");
		constrator.append("    }");
		constrator.append("\n\n\n");

		return constrator.toString();

	}

	/**
	 * ���get set��������
	 * 
	 * @param columns
	 * @return
	 */
	private String getMethods(List<Column> columns) {

		StringBuffer methods = new StringBuffer();

		for (Column column : columns) {

			methods.append(getMethod(
					StringUtil.firstLowerCase(column.getName()),
					StringUtil.changeType(column)));
			methods.append("\n");
			methods.append(setMethod(
					StringUtil.firstLowerCase(column.getName()),
					StringUtil.changeType(column)));
			methods.append("\n");
		}

		return methods.toString();
	}

	private String getMethod(String parmeter, String type) {

		String method = "	public " + type + "  get"
				+ StringUtil.firstUpCase(parmeter) + "() { \n";

		method = method + "		return " + parmeter + ";\n	}";
		return method;

	}

	private String setMethod(String parmeter, String type) {

		String method = "	public void  set" + StringUtil.firstUpCase(parmeter)
				+ "(" + type + " " + parmeter + ") { \n";

		method = method + "		this." + parmeter + " = " + parmeter + ";\n	}";
		return method;

	}

	private String toStr(List<Column> columns) {
		String method = "	public String toString() {\n"
				+ "		return new ToStringBuilder(this)";
		for (Column column : columns) {
			method += ".append(\"" + column.getName() + "\","
					+ StringUtil.firstLowerCase(column.getName()) + ")";

		}
		return method + ".toString();\n" + "	}";

	}

}
