package com.github.pojo;

import java.util.List;

import com.github.common.FileUtil;
import com.github.common.StringUtil;

public class JspMaker {
	
    private String checkdata = "checkdata";

    private String trtd = "trtd";
	
	
	
	
	public void createJavaFile(Table table) throws Exception{
		
        String parmeterName = StringUtil.makeParmeterName(table.getTableName());
        String className = StringUtil.makeClassName(table.getTableName());
        String pk = StringUtil.makeParmeterName(table.getPk().get(0));

         FileUtil.fileCopy("jspTemp\\add.jsp", "jspfile\\" + parmeterName
         + "Add.jsp");
         FileUtil.fileCopy("jspTemp\\edit.jsp", "jspfile\\" + parmeterName
         + "Edit.jsp");
         FileUtil.fileCopy("jspTemp\\list.jsp", "jspfile\\" + parmeterName
         + "List.jsp");
         FileUtil.fileCopy("jspTemp\\view.jsp", "jspfile\\" + parmeterName
         + "View.jsp");
        
         // FileUtil.replaceTxtByStr("jspfile\\" + className + "Add.jsp",
         // title,
         // "123");
         FileUtil.replaceTxtByStr("jspfile\\" + parmeterName + "Add.jsp",
         this.checkdata, getCheckdata(table.getColumns()));
         FileUtil.replaceTxtByStr("jspfile\\" + parmeterName + "Edit.jsp",
         this.checkdata, getCheckdata(table.getColumns()));
         FileUtil.replaceTxtByStr("jspfile\\" + parmeterName + "Add.jsp",
                "add_action", "      action=\"<%=contextPath%>/mb/"
                        + parmeterName + "insert.do\" method=\"post\" >");
        FileUtil.replaceTxtByStr("jspfile\\" + parmeterName + "Add.jsp",
         this.trtd, this.getTrtd(table.getColumns()));
        FileUtil.replaceTxtByStr("jspfile\\" + parmeterName + "Edit.jsp",
                "edit_action", " action=\"<%=contextPath%>/mb/" + parmeterName
                        + "update.do\" method=\"post\" >");
         FileUtil.replaceTxtByStr("jspfile\\" + parmeterName + "Edit.jsp",
         this.trtd, this.getTrtdEdit(table.getColumns()));
         FileUtil.replaceTxtByStr("jspfile\\" + parmeterName + "View.jsp",
         this.trtd, this.getTrtdView(table.getColumns()));
         FileUtil.replaceTxtByStr("jspfile\\" + parmeterName + "List.jsp",
         "ec_column", this.getTrtdList(table.getColumns()));
        FileUtil.replaceTxtByStr("jspfile\\" + parmeterName + "List.jsp",
                "add_action", "             form.action=\"<%=contextPath%>/mb/"
                        + parmeterName + "add.do?\";");
        FileUtil.replaceTxtByStr("jspfile\\" + parmeterName + "List.jsp",
                "modify_action",
                "             form.action=\"<%=contextPath%>/mb/"
                        + parmeterName + "edit.do?\";");
        FileUtil.replaceTxtByStr("jspfile\\" + parmeterName + "List.jsp",
                "delete_action",
                "             form.action=\"<%=contextPath%>/mb/"
                        + parmeterName + "delete.do?\";");
        FileUtil.replaceTxtByStr("jspfile\\" + parmeterName + "List.jsp",
                "view_action",
                "             form.action=\"<%=contextPath%>/mb/"
                        + parmeterName + "view.do?\";");
        FileUtil.replaceTxtByStr("jspfile\\" + parmeterName + "List.jsp",
                "to_Back",
                "            window.location.href = \"<%=contextPath%>/mb/"
                        + parmeterName + "query.do\";");
        FileUtil.replaceTxtByStr("jspfile\\" + parmeterName + "List.jsp",
                "list_action", " action=\"<%=contextPath%>/mb/" + parmeterName
                        + "query.do\" method=\"post\" class=\"formstyle\">");
        FileUtil.replaceTxtByStr("jspfile\\" + parmeterName + "List.jsp",
                "draw_action", "            form.action=\"<%=contextPath%>/mb/"
                        + parmeterName + "draw.do?\";");
        FileUtil.replaceTxtByStr("jspfile\\" + parmeterName + "List.jsp",
                "verify_action",
                "                  form.action=\"<%=contextPath%>/mb/"
                        + parmeterName + "verify.do?\";");
        FileUtil.replaceTxtByStr("jspfile\\" + parmeterName + "List.jsp",
                "verifyAll_action",
                "                   form.action=\"<%=contextPath%>/mb/"
                        + parmeterName + "verifyAll.do?\";");
        FileUtil.replaceTxtByStr("jspfile\\" + parmeterName + "List.jsp",
                "ec_action",
                "           action=\"${pageContext.request.contextPath}/mb/"
                        + parmeterName + "query.do\"");
        FileUtil.replaceTxtByStr("jspfile\\" + parmeterName + "List.jsp",
                "12345",
                "               var proj = document.getElementById(\"" + pk
                        + "\");");
        FileUtil.replaceTxtByStr("jspfile\\" + parmeterName + "List.jsp",
                "hidden_cementFluidAddId",
                "               <input type=\"hidden\" name=\"" + pk
                        + "\" id=\"" + pk + "\" value=\"\"/>");
        FileUtil.replaceTxtByStr(
                "jspfile\\" + parmeterName + "List.jsp",
                "checkbox_value",
                "                  <input type=\"checkbox\" name=\"radioitem\" value=\"${DrillDate."
                        + pk + "}\">");

        FileUtil.fileCopy("jspTemp\\addAction.java", "actionfile\\" + className
                + "AddAction.java");
        FileUtil.fileCopy("jspTemp\\editAction.java", "actionfile\\"
                + className
                + "EditAction.java");
        FileUtil.fileCopy("jspTemp\\listQueryAction.java", "actionfile\\"
                + className + "ListQueryAction.java");
        FileUtil.fileCopy("jspTemp\\queryAction.java", "actionfile\\"
                + className
                + "QueryAction.java");

        FileUtil.replaceTxtByStr("actionfile\\" + className
                + "AddAction.java", "add_action",
                "public class " + className
                        + "AddAction extends OMSAction{");
        FileUtil.replaceTxtByStr(
                "actionfile\\" + className + "EditAction.java", "edit_action",
                "public class " + className + "EditAction extends OMSAction{");
        FileUtil.replaceTxtByStr("actionfile\\" + className
                + "ListQueryAction.java", "list_action", "public class "
                + className + "ListQueryAction extends OMSAction{");
        FileUtil.replaceTxtByStr("actionfile\\" + className
                + "QueryAction.java", "query_action", "public class " + className
                + "QueryAction extends OMSAction{");
	}

    /**
     * ���checkdata��������
     * 
     * �Գ���������ͽ���Ч��rigorCheckValue()����Զ���λ
     * ����args1������������磺document.form1.name.value ����args2����Ƿ�Ϊ��:0���� 1���ǿ�
     * ����args3����������:1���"������< > ��string" �� 2���"number"�� 3���"float"��
     * 4���"date"��?5���"ֻ�������ֺ���ĸ�����,6���"datetime"����ʱ��,7���"datemonth"����
     * ����args4����ַ��ȣ�0����?���� ����args5�������С���λ�����float������Ч����0����?����
     * ����args6�������ʵ�����壬�Ա���׼ȷ֪ͨ�û���
     * 
     * @param columns
     * @return
     */
	
    private String getCheckdata(List<Column> columns) {

        StringBuffer methods = new StringBuffer();

        for (Column column : columns) {
            methods.append("    if(!rigorCheckValue(document.checkForm.");
            methods.append(StringUtil.makeParmeterName(column.getName())
                    + ",0," + StringUtil.changeTypeCheckData(column) + ",\""
                    + column.getComment() + "\")){\n");
            methods.append("      return false; \n    } \n");
        }

        return methods.toString();
    }

    private String getTrtd(List<Column> columns) {

        StringBuffer methods = new StringBuffer();

        int i = 0;
        for (Column column : columns) {
            String mpn = StringUtil.makeParmeterName(column.getName());
            if (i % 2 == 0) {
                methods.append("  <tr>\n");
            }
            methods.append("    <td class=\"ali4\"> " + column.getComment()
                    + "��</td>\n"
                    + "    <td class=\"ali5\" ><input type=\"text\" name=\"");
            methods.append(mpn);
            methods.append("\"  class=\"input_width\"/> ");
            if (column.getType().equals("java.sql.Timestamp")) {
                methods.append("\n    <img src=\"<%=contextPath%>/images/calendar.gif\" id=\"tributton1"
                        + i
                        + "\"  style=\"cursor:hand;\" onmouseover=\"timeSelector("
                        + mpn
                        + ",tributton1"
                        + i + ");\"/>");
            }
            methods.append("</td>\n");
            if (i % 2 != 0) {
                methods.append("  </tr>\n");
            }
            if (i % 2 == 0 && i == columns.size() - 1) {
                methods.append("    <td class=\"ali4\"> &nbsp;</td>\n"
                        + "    <td class=\"ali5\" > &nbsp;</td>\n");
                methods.append("  </tr>\n");
            }
            i++;
        }

        return methods.toString();
    }

    private String getTrtdEdit(List<Column> columns) {
        StringBuffer methods = new StringBuffer();

        int i = 0;
        for (Column column : columns) {
            String mpn = StringUtil.makeParmeterName(column.getName());
            if (i % 2 == 0) {
                methods.append("  <tr>\n");
            }
            methods.append("    <td class=\"ali4\"> " + column.getComment()
                    + "��</td>\n"
                    + "    <td class=\"ali5\" ><input type=\"text\" name=\"");
            methods.append(mpn);
            methods.append("\"  class=\"input_width\" value=\"<oms:object property=\"AuditVO."
                    + mpn
                    + "\"/>\"/> ");
            if (column.getType().equals("java.sql.Timestamp")) {
                methods.append("\n    <img src=\"<%=contextPath%>/images/calendar.gif\" id=\"tributton1"
                        + i
                        + "\"  style=\"cursor:hand;\" onmouseover=\"timeSelector("
                        + mpn
                        + ",tributton1" + i + ");\"/>");
            }
            methods.append("</td>\n");
            if (i % 2 != 0) {
                methods.append("  </tr>\n");
            }
            if (i % 2 == 0 && i == columns.size() - 1) {
                methods.append("    <td class=\"ali4\"> &nbsp;</td>\n"
                        + "    <td class=\"ali5\" > &nbsp;</td>\n");
                methods.append("  </tr>\n");
            }
            i++;
        }

        return methods.toString();
    }

    private String getTrtdView(List<Column> columns) {

        StringBuffer methods = new StringBuffer();

        int i = 0;
        for (Column column : columns) {
            if (i % 2 == 0) {
                methods.append("  <tr>\n");
            }
            methods.append("    <td class=\"ali4\"> "
                    + column.getComment()
                    + "��</td>\n"
                    + "    <td class=\"ali5\" >&nbsp;<oms:object property=\"AuditVO."
                    + StringUtil.makeParmeterName(column.getName())
                    + "\"/> </td>\n");
            if (i % 2 != 0) {
                methods.append("  </tr>\n");
            }
            if (i % 2 == 0 && i == columns.size() - 1) {
                methods.append("    <td class=\"ali4\"> &nbsp;</td>\n"
                        + "    <td class=\"ali5\" > &nbsp;</td>\n");
                methods.append("  </tr>\n");
            }
            i++;
        }

        return methods.toString();
    }

    private String getTrtdList(List<Column> columns) {

        StringBuffer methods = new StringBuffer();
        for (Column column : columns) {

            methods.append("                <ec:column property=\""
                    + StringUtil.makeParmeterName(column.getName())
                    + "\" title=\"" + column.getComment() + "\"/>\n");

        }

        return methods.toString();
    }




}
