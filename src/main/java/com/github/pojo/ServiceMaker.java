package com.github.pojo;

import java.sql.SQLException;
import java.util.List;

import com.github.common.FileUtil;
import com.github.common.StringUtil;

public class ServiceMaker {

	public void createJavaFile(String adress, Table table) throws Exception {

		String className = StringUtil.makeClassName(table.getTableName()
				.substring(4, table.getTableName().length()));

		StringBuffer content = new StringBuffer();

		content.append(getImport(className));
		content.append(getTitle(table.getTableName().substring(4,
				table.getTableName().length())));
		content.append(getConstrator(
				table.getTableName()
						.substring(4, table.getTableName().length()), table
						.getPk()));
		StringBuffer content1 = new StringBuffer();

		content1.append("package com.service;\n"

		+ "public interface " + className + "Manager extends CommonManager {\n"

		+ "}");

		content.append("\n\n }");

		FileUtil.writeFile(adress + "\\" + className + "Manager.java",
				content1.toString());
		FileUtil.writeFile(
				adress + "\\impl\\" + className + "ManagerImpl.java",
				content.toString());
	}

	private String getImport(String className) {

		String imports = "package com.service.impl;\n"

				+ "import java.util.List;\n"
				+ "import com.util.JsonUtil;\n"
				+ "import java.util.HashMap;\n"
				+ "import java.util.Map;\n"

				+ "import org.springframework.context.ApplicationContext;\n"
				+ "import org.springframework.context.support.FileSystemXmlApplicationContext;\n"

				+ "import com.service.CommonSupport;\n" + "import com.dao."
				+ className + "Dao;\n" + "import com.pojo." + className + ";\n"
				// + "import com.pojo.Tree;\n" + "import com.pojo.TreeNode;\n"
				+ "import com.service." + className + "Manager;\n";
		return imports;
	}

	private String getTitle(String tableName) {

		String className = StringUtil.makeClassName(tableName);

		StringBuffer title = new StringBuffer();
		title.append("public class ");
		title.append(className);
		title.append("ManagerImpl extends CommonSupport\n" + "		implements "
				+ className + "Manager {\n\n\n");

		return title.toString();

	}

	private String getConstrator(String tableName, List<String> pk)
			throws SQLException {

		// String pkName = StringUtil.makeClassName(pk.get(0));
		// String pkpName = StringUtil.makeParmeterName(pk.get(0));
		String className = StringUtil.makeClassName(tableName);
		String pName = StringUtil.makeParmeterName(tableName);
		StringBuffer constrator = new StringBuffer();
		constrator.append("\n\n\n");
		constrator
				.append("	private "
						+ className
						+ "Dao "
						+ pName
						+ "Dao;\n"
						+ "	private "
						+ className
						+ " "
						+ pName
						+ ";\n"

						+ "	protected void onInit() {\n"
						+ "			this."
						+ pName
						+ "Dao = ("
						+ className
						+ "Dao) getWebApplicationContext()\n"
						+ "					.getBean(\""
						+ pName
						+ "Dao\");\n"
						+ "							}\n"
						+ "						/**\n"
						+ "						 * ���� data��json����\n"
						+ "						 */\n"
						+ "						public String insert(String data) {\n"
						+ "							int re = 0;\n"
						+ "						try {\n"
						// + "							data = data.replace(\"\\\\\", \"\");\n"
						+ "							"
						+ pName
						+ " = ("
						+ className
						+ ") JsonUtil.toJava(data, "
						+ className
						+ ".class);\n"
						+ "							"
						+ pName
						+ "Dao.save("
						+ pName
						+ ");\n"
						+ "							re = "
						+ pName
						+ ".getId();\n"
						+ "			} catch (Exception e) {\n"
						// TODO: handle exception
						+ "				return JsonUtil.toJson(e.fillInStackTrace());\n"
						+ "			}\n"
						+ "							return \"\" + re;\n"
						+ "						}\n"

						+ "						/**\n"
						+ "						 * id ���� ͨ�������ѯ��¼\n"
						+ "						 */\n"
						+ "						public String getInfo(int id) {\n"
						+ "							String re = \"\";\n"
						+ "						try {\n"
						+ "							"
						+ pName
						+ " = ("
						+ className
						+ ") "
						+ pName
						+ "Dao.findById(id);\n"
						+ "							re = JsonUtil.toJson("
						+ pName
						+ ");\n"
						+ "			} catch (Exception e) {\n"
						// TODO: handle exception
						+ "				return JsonUtil.toJson(e.fillInStackTrace());\n"
						+ "			}\n"
						+ "							return re;\n"
						+ "						}\n"

						+ "						/**\n"
						+ "						 * ��ѯ���м�¼��tj��ѯ������pageSizeÿҳ��ʾ��������¼��pageNow��ǰ�ǵڼ�ҳ\n"
						+ "						 */\n"
						+ "						public String getList(String data, String tj, int pageSize, int pageNow) {\n"
						+ "							String re = \"\";\n"
						+ "						try {\n"
						+ "								String tj1 = \"\";\n"
						+ "		Map map = (Map) JsonUtil.toJava(tj, Map.class);\n"
						+ "		if (null != map) {\n"
						+ "			for (Object o : map.keySet()) {\n"

						+ "				tj1 += \" and \" + o + \"=\" + map.get(o);\n"
						+ "			}\n"
						+ "		}\n"
						+ "							List list = (List) "
						+ pName
						+ "Dao.findAll(tj1, pageSize, pageNow);\n"
						+ "							if (null != list) {\n"
						+ "								re = JsonUtil.toJson(list);\n"
						+ "							}\n"
						+ "			} catch (Exception e) {\n"
						// TODO: handle exception
						+ "				return JsonUtil.toJson(e.fillInStackTrace());\n"
						+ "			}\n"
						+ "							return re;\n"
						+ "						}\n"

						+ "						/**\n"
						+ "						 * ��ѯ���м�¼��tj��ѯ����\n"
						+ "						 */\n"
						+ "						public String getAll(String tj) {\n"
						+ "							String re = \"\";\n"
						+ "						try {\n"
						+ "								String tj1 = \"\";\n"
						+ "		Map map = (Map) JsonUtil.toJava(tj, Map.class);\n"
						+ "		if (null != map) {\n"
						+ "			for (Object o : map.keySet()) {\n"

						+ "				tj1 += \" and \" + o + \"=\" + map.get(o);\n"
						+ "			}\n"
						+ "		}\n"
						+ "							List list = (List) "
						+ pName
						+ "Dao.findAll(tj1);\n"
						+ "							if (null != list) {\n"
						+ "								re = JsonUtil.toJson(list);\n"
						+ "							}\n"
						+ "			} catch (Exception e) {\n"
						// TODO: handle exception
						+ "				return JsonUtil.toJson(e.fillInStackTrace());\n"
						+ "			}\n"
						+ "							return re;\n"
						+ "						}\n"

						+ "						/**\n"
						+ "						 * ���� data��json����\n"
						+ "						 */\n"
						+ "						public String update(String data) {\n"
						+ "							int re = 0;\n"
						+ "						try {\n"
						+ "							"
						+ pName
						+ " = ("
						+ className
						+ ") JsonUtil.toJava(data, "
						+ className
						+ ".class);\n"
						+ "							"
						+ pName
						+ "Dao.update("
						+ pName
						+ ");\n"
						+ "							re = "
						+ pName
						+ ".getId();\n"
						+ "			} catch (Exception e) {\n"
						// TODO: handle exception
						+ "				return JsonUtil.toJson(e.fillInStackTrace());\n"
						+ "			}\n"
						+ "							return \"\" + re;\n"
						+ "						}\n"

						+ "						/**\n"
						+ "						 * ɾ��data��json����\n"
						+ "						 */\n"
						+ "						public String delete(String data) {\n"
						+ "							String re = \"succes\";\n"
						+ "						try {\n"
						+ "							"
						+ "							data = \"{\\\"id\\\":\\\"\" + data + \"\\\"}\";\n"
						+ "							" + pName + " = (" + className
						+ ") JsonUtil.toJava(data, " + className + ".class);\n"
						+ "					re = " + pName + "Dao.delete(" + pName + ");\n"
						+ "			} catch (Exception e) {\n"
						// TODO: handle exception
						+ "				return JsonUtil.toJson(e.fillInStackTrace());\n"
						+ "			}\n" + "							return re;\n" + "						}\n" + "");
		return constrator.toString();

	}
}
