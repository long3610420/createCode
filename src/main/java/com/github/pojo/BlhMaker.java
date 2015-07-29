package com.github.pojo;

import java.sql.SQLException;
import java.util.List;

import com.github.common.FileUtil;
import com.github.common.StringUtil;

public class BlhMaker {

	public void createJavaFile(Table table) throws Exception {

		String className = StringUtil.makeClassName(table.getTableName());

		StringBuffer content = new StringBuffer();

		content.append(getImport1(className));
		content.append(getTitle1(table.getTableName()));
		content.append(getConstrator1(table.getTableName(), table.getPk()));

		content.append("\n\n }");

		FileUtil.writeFile("actionfile\\" + className + "Manager.java",
				content.toString());
	}

	private String getImport1(String className) {

		String imports = "package com.service;\n"

		+ "import java.rmi.Remote;\n";
		return imports;
	}

	private String getTitle1(String tableName) {

		String className = StringUtil.makeClassName(tableName);

		StringBuffer title = new StringBuffer();
		title.append("public interface ");
		title.append(className);
		title.append("Manager extends Remote {\n\n\n");

		return title.toString();

	}

	private String getConstrator1(String tableName, List<String> pk)
			throws SQLException {

		// String pkName = StringUtil.makeClassName(pk.get(0));
		// String pkpName = StringUtil.makeParmeterName(pk.get(0));
		String className = StringUtil.makeClassName(tableName);
		String parName = StringUtil.makeParmeterName(tableName);
		StringBuffer constrator = new StringBuffer();
		constrator.append("\n\n\n");
		constrator
				.append("	public String save(String data);\n"

						+ "	public String find(int id);\n"

						+ "	public String findAll(String data, String tj, int pageSize, int pageNow);\n"

						+ "	public String update(String data);\n"

						+ "	public String delete(String data);\n");
		return constrator.toString();
	}

	private String getImport(String className) {

		String imports = "package com.cnpc.oms.service.mb.blh;\n\n";

		imports = imports + "import java.util.HashMap;\n";
		imports = imports + "import java.util.ArrayList;\n";
		imports = imports + "import java.util.List;\n";
		imports = imports + "import java.util.Set;\n";
		imports = imports + "import java.util.Map;\n";

		imports = imports + "import java.beans.IntrospectionException;\n";
		imports = imports
				+ "import java.lang.reflect.InvocationTargetException;\n";
		imports = imports + "import com.cnpc.oms.dao.DAOFactory;\n";
		imports = imports + "import com.cnpc.oms.dao.PageModel;\n";
		imports = imports + "import com.cnpc.oms.message.ISvrMsg;\n";
		imports = imports + "import com.cnpc.oms.message.SrvMsgUtil;\n";
		imports = imports
				+ "import com.cnpc.oms.service.drill.common.DrillCommmManager;\n";
		imports = imports + "import com.cnpc.oms.audit.pojo." + className
				+ ";\n";
		imports = imports + "import com.cnpc.oms.service.mb.dao.I" + className
				+ "DAO;\n";
		imports = imports + "import com.cnpc.oms.service.mb.dao.impl."
				+ className + "DAO;\n";
		imports = imports
				+ "import com.cnpc.oms.serviceManager.BLHException;\n";
		imports = imports + "import com.cnpc.oms.serviceManager.BaseBLH;\n";
		imports = imports + "import com.cnpc.oms.service.drill.util.Util;\n";
		return imports;
	}

	private String getTitle(String tableName) {

		String className = StringUtil.makeClassName(tableName);

		StringBuffer title = new StringBuffer();
		title.append("public class ");
		title.append(className);
		title.append("BLH implements BaseBLH {\n\n\n");

		return title.toString();

	}

	private String getConstrator(String tableName, List<String> pk)
			throws SQLException {

		String pkName = StringUtil.makeClassName(pk.get(0));
		String pkpName = StringUtil.makeParmeterName(pk.get(0));
		String className = StringUtil.makeClassName(tableName);
		String parName = StringUtil.makeParmeterName(tableName);
		StringBuffer constrator = new StringBuffer();
		constrator.append("\n\n\n");
		constrator
				.append("public ISvrMsg execute(ISvrMsg requestDTO) throws Exception {\n"

						+ "        // ��DTO�н�������Ĳ�����ƣ���ݲ�����Ƶ�����Ӧ�ķ�������\n"
						+ "        String operationName = requestDTO.getOperationName();\n"
						+ "        String serviceName = requestDTO.getServiceName();\n"
						+ "        // ��ѯ����\n"
						+ "        if (operationName != null\n"
						+ "                && operationName.trim()\n"
						+ "                        .equalsIgnoreCase(\"query_"
						+ parName
						+ "\"))\n"
						+ "            return query"
						+ className
						+ "(requestDTO);\n"
						+ "        // �����������\n"
						+ "        else if (operationName != null\n"
						+ "                && operationName.trim().equalsIgnoreCase(\"insert_"
						+ parName
						+ "\"))\n"
						+ "            return insert"
						+ className
						+ "(requestDTO);\n"
						+ "        // ������\n"
						+ "        else if (operationName != null\n"
						+ "                && operationName.trim().equalsIgnoreCase(\"find_"
						+ parName
						+ "\"))\n"
						+ "            return view"
						+ className
						+ "(requestDTO);\n"
						+ "        // ���²���\n"
						+ "        else if (operationName != null\n"
						+ "                && operationName.trim().equalsIgnoreCase(\n"
						+ "                        \"update_"
						+ parName
						+ "\"))\n"
						+ "            return update"
						+ className
						+ "(requestDTO);\n"
						+ "        // �h�����\n"
						+ "        else if (operationName != null\n"
						+ "                && operationName.trim().equalsIgnoreCase(\"delete_"
						+ parName
						+ "\"))\n"
						+ "            return delete"
						+ className
						+ "(requestDTO);\n"
						+ "        // ��ȡ����\n"
						+ "        else if (operationName != null\n"
						+ "                && operationName.trim().equalsIgnoreCase(\"draw_"
						+ parName
						+ "\"))\n"
						+ "            return draw"
						+ className
						+ "(requestDTO);\n"
						+ "        // ��˲���\n"
						+ "        else if (operationName != null\n"
						+ "                && operationName.trim().equalsIgnoreCase(\"verify_"
						+ parName
						+ "\"))\n"
						+ "            return verify"
						+ className
						+ "(requestDTO);\n"
						+ "        else if (operationName != null\n"
						+ "                && operationName.trim().equalsIgnoreCase(\n"
						+ "                        \"verifyAll_"
						+ parName
						+ "\"))\n"
						+ "            return verifyAll"
						+ className
						+ "(requestDTO);\n"
						+ "        else\n"
						+ "            throw new BLHException(\"service \" + serviceName\n"
						+ "            +\" have no corresponding operation:\" + operationName);\n"

						+ "    }");
		constrator.append("\n\n\n");
		constrator
				.append("    private ISvrMsg query"
						+ className
						+ "(ISvrMsg reqDTO) throws Exception {\n"
						+ "        // TODO Auto-generated method stub\n"
						+ "        String currentPage = reqDTO.getValue(\"SelectForm_p\");\n"

						+ "        int cur = 0;\n"
						+ "        int pageSize = reqDTO.getValue(\"SelectForm_crd\") == null ? 10 : Integer.parseInt(reqDTO.getValue(\"SelectForm_crd\"));\n"
						+ "        if (currentPage == null) {\n"
						+ "            cur = 0;\n"
						+ "        } else {\n"
						+ "            cur = Integer.parseInt(currentPage);\n"
						+ "        }\n"

						+ "        PageModel page = new PageModel();\n"
						+ "        page.setCurrPage(cur);\n"
						+ "        page.setPageSize(pageSize);\n"
						+ "        String str = reqDTO.getValue(\"wellId\");\n"
						+ "        Map<String, String> map = new HashMap<String, String>();\n"
						+ "        map.put(\"recordId\", reqDTO.getValue(\"wellId\"));\n"
						+ "        map.put(\"wellName1\", reqDTO.getValue(\"wellName\"));\n"
						+ "        map.put(\"consumeDate\", reqDTO.getValue(\"consumeDate\"));\n"
						+ "        map.put(\"consumeDate2\", reqDTO.getValue(\"consumeDate2\"));\n"

						+ "        I"
						+ className
						+ "DAO "
						+ parName
						+ "dao = (I"
						+ className
						+ "DAO) DAOFactory\n"
						+ "                .getDAO(\""
						+ className
						+ "DAO\");\n"
						+ "        page = "
						+ parName
						+ "dao.get"
						+ className
						+ "(map, page);\n"

						+ "        List list = page.getData();\n"
						+ "        ISvrMsg msg = SrvMsgUtil.createResponseMsg(reqDTO);\n"
						+ "        msg.setOperationName(reqDTO.getOperationName());\n"
						+ "        // ����ϢΪ�б���ʾ��Ϣ\n"

						+ "        msg.setValue(\"drilldatelist\", list);\n"
						+ "        msg.setValue(\"consumeDate\", reqDTO.getValue(\"consumeDate\"));\n"
						+ "        msg.setValue(\"consumeDate2\", reqDTO.getValue(\"consumeDate2\"));\n"

						+ "        msg.setValue(\"totalRecords\", page.getTotalRow());\n"
						+ "        return msg;\n" + "    }");
		constrator.append("\n\n\n");
		constrator
				.append("    private ISvrMsg insert"
						+ className
						+ "(ISvrMsg reqDTO) throws Exception {\n"
						+ "        "
						+ className
						+ " "
						+ parName
						+ " = ("
						+ className
						+ ") reqDTO.toPojo("
						+ className
						+ ".class);\n"
						+ "        I"
						+ className
						+ "DAO "
						+ parName
						+ "dao = (I"
						+ className
						+ "DAO) DAOFactory.getDAO(\""
						+ className
						+ "DAO\");\n        "
						+ parName
						+ ".set"
						+ pkName
						+ "(Util.randomCode(32));\n"
						+ "        "
						+ parName
						+ "dao.save("
						+ parName
						+ ");\n"
						+ "        ISvrMsg msg = SrvMsgUtil.createResponseMsg(reqDTO);\n"
						+ "        return msg;\n" + "    }");

		constrator.append("\n\n\n");
		constrator
				.append("    private ISvrMsg view"
						+ className
						+ "(ISvrMsg reqDTO) throws Exception {\n"

						+ "        String "
						+ pkpName
						+ " = reqDTO.getValue(\""
						+ pkpName
						+ "\");// ��Ԫ��ݿ���鵽��Ԫ������\n"
						+ "        I"
						+ className
						+ "DAO "
						+ parName
						+ "dao = (I"
						+ className
						+ "DAO) DAOFactory.getDAO(\""
						+ className
						+ "DAO\");\n"

						+ "        ISvrMsg msg = SrvMsgUtil.createResponseMsg(reqDTO);\n"

						+ "        " + className + " " + parName + " = "
						+ parName + "dao.find(" + pkpName + ");\n"

						+ "        msg.setValue(\"AuditVO\", " + parName
						+ ");\n"

						+ "        return msg;\n" + "    }");
		constrator.append("\n\n\n");
		constrator
				.append("    private ISvrMsg update"
						+ className
						+ "(ISvrMsg reqDTO) throws Exception {\n"
						+ "        "
						+ className
						+ " "
						+ parName
						+ " = ("
						+ className
						+ ") reqDTO.toPojo("
						+ className
						+ ".class);\n"
						+ "        I"
						+ className
						+ "DAO "
						+ parName
						+ "dao = (I"
						+ className
						+ "DAO) DAOFactory.getDAO(\""
						+ className
						+ "DAO\");\n"
						+ "        "
						+ parName
						+ "dao.update("
						+ parName
						+ ");\n"

						+ "        ISvrMsg msg = SrvMsgUtil.createResponseMsg(reqDTO);\n"

						+ "        return msg;\n" + "    }");
		constrator.append("\n\n\n");
		constrator
				.append("    private ISvrMsg delete"
						+ className
						+ "(ISvrMsg reqDTO) throws Exception {\n"
						+ "        String "
						+ pkpName
						+ " = reqDTO.getValue(\""
						+ pkpName
						+ "\");// ��Ԫ��ݿ���鵽��Ԫ������\n"
						+ "        I"
						+ className
						+ "DAO "
						+ parName
						+ "dao = (I"
						+ className
						+ "DAO) DAOFactory.getDAO(\""
						+ className
						+ "DAO\");\n"

						+ "        ISvrMsg msg = SrvMsgUtil.createResponseMsg(reqDTO);\n"

						+ "        " + parName + "dao.delete(" + pkpName
						+ ");\n"

						+ "        return msg;\n" + "    }");
		constrator.append("\n\n\n");
		constrator
				.append("    private ISvrMsg draw"
						+ className
						+ "(ISvrMsg reqDTO) throws Exception {\n"
						+ "        String consumeDate = reqDTO.getValue(\"consumeDate\");\n"
						+ "        String consumeDate2 = reqDTO.getValue(\"consumeDate2\");\n"
						+ "        I"
						+ className
						+ "DAO "
						+ parName
						+ "dao = (I"
						+ className
						+ "DAO) DAOFactory.getDAO(\""
						+ className
						+ "DAO\");\n"

						+ "        Map<String, String> map = new HashMap<String, String>();\n"
						+ "        List list = "
						+ parName
						+ "dao.getView"
						+ className
						+ "(map);\n"
						+ "        "
						+ className
						+ " mb = new "
						+ className
						+ "();\n"
						+ "        list = to"
						+ className
						+ "(list, mb);\n"
						+ "        for (int i = 0; i < list.size(); i++) {\n"
						+ "            mb = ("
						+ className
						+ ") list.get(i);\n"
						+ "            if (mb.get"
						+ pkName
						+ "() != null) {\n"
						+ "                Map mbmap = "
						+ parName
						+ "dao.findExist(mb.get"
						+ pkName
						+ "());\n"
						+ "                if (mbmap == null) {\n"

						+ "                    "
						+ parName
						+ "dao.save(mb);\n"
						+ "                }\n"
						+ "                else {\n"
						+ "                    "
						+ parName
						+ "dao.update(mb);\n"
						+ "                }\n"
						+ "            }\n"
						+ "            else {\n"
						+ "                "
						+ parName
						+ "dao.save(mb);\n"
						+ "            }\n"
						+ "        }\n"
						+ "        ISvrMsg msg = SrvMsgUtil.createResponseMsg(reqDTO);\n"

						+ "        return msg;\n" + "    }");
		constrator.append("\n\n\n");
		constrator
				.append("    private List to"
						+ className
						+ "(List<Map> list, "
						+ className
						+ " mb)\n"
						+ "            throws IntrospectionException, IllegalAccessException,\n"
						+ "            InstantiationException, InvocationTargetException {\n"
						+ "        List listnew = new ArrayList();\n"
						+ "        for (Map<String, Object> map : list) {\n"
						+ "            Map<String, Object> newmap = new HashMap<String, Object>();\n"
						+ "            Set<String> keys = map.keySet();\n"
						+ "            for (String s : keys) {\n"
						+ "                String[] parts = s.split(\"_\");\n"
						+ "                if (parts.length > 0) {\n"
						+ "                    String key1 = \"\";\n"
						+ "                    for (String p : parts) {\n"
						+ "                        key1 += p;\n"
						+ "                    }\n"
						+ "                    String value = \"\" + map.get(s);\n"
						+ "                    newmap.put(key1, value);\n"
						+ "                }\n" + "                else {\n"
						+ "                    newmap.put(s, map.get(s));\n"
						+ "                }\n" + "            }\n"
						+ "            mb = (" + className
						+ ") Util.convertMap(" + className + ".class,\n"
						+ "                    newmap);\n"
						+ "            listnew.add(mb);\n" + "        }\n"
						+ "        return listnew;\n" + "    }");
		constrator.append("\n\n\n");
		constrator
				.append("    private ISvrMsg verify"
						+ className
						+ "(ISvrMsg reqDTO) throws Exception {\n"
						+ "        String "
						+ pkpName
						+ " = reqDTO.getValue(\""
						+ pkpName
						+ "\");\n"
						+ "        I"
						+ className
						+ "DAO "
						+ parName
						+ "dao = (I"
						+ className
						+ "DAO) DAOFactory.getDAO(\""
						+ className
						+ "DAO\");\n"
						+ "        ISvrMsg msg = SrvMsgUtil.createResponseMsg(reqDTO);\n"
						+ "        String[] strs = "
						+ pkpName
						+ ".split(\";\");\n"
						+ "        for (String str : strs) {\n"
						+ "            "
						+ parName
						+ "dao.verify(str);\n"
						+ "        }\n"
						+ "        return msg;\n"
						+ "    }\n"

						+ "    private ISvrMsg verifyAll"
						+ className
						+ "(ISvrMsg reqDTO)\n"
						+ "            throws Exception {\n"
						+ "        I"
						+ className
						+ "DAO "
						+ parName
						+ "dao = (I"
						+ className
						+ "DAO) DAOFactory.getDAO(\""
						+ className
						+ "DAO\");\n"
						+ "        ISvrMsg msg = SrvMsgUtil.createResponseMsg(reqDTO);\n"
						+ "        " + parName + "dao.verifyAll();\n"
						+ "        return msg;\n" + "    }");
		constrator.append("\n\n\n");
		return constrator.toString();

	}

}
