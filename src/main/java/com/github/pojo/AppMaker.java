package com.github.pojo;

import java.util.List;

import com.github.common.FileUtil;
import com.github.common.StringUtil;

public class AppMaker {
	
	
	
    public void createXmlFile(List<Table> tables) throws Exception {
		
        StringBuffer content = new StringBuffer();
        StringBuffer content1 = new StringBuffer();
        content.append(getTitle());
        content1.append(getTitleStruts());

        for (Table table : tables) {
            String className = StringUtil.makeClassName(table.getTableName());
            content.append(getDeclare(className));
            content1.append(getDeclareStruts(table.getTableName(), className));
        }
        content.append("</beans>");
        content1.append("     </action-mappings>\n</struts-config>");
        FileUtil.writeFile("xmlfile\\mb-applicationContext-dao.xml",
                content.toString());
        FileUtil.writeFile("xmlfile\\struts-mb.xml", content1.toString());
	}
	
	private String getTitle(){
		StringBuffer title = new StringBuffer();
		title.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        title.append("<beans xmlns=\"http://www.springframework.org/schema/beans\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
        title.append("xmlns:aop=\"http://www.springframework.org/schema/aop\" xmlns:tx=\"http://www.springframework.org/schema/tx\"\n");
        title.append("xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.0.xsd \n http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-2.0.xsd \n http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-2.0.xsd\" default-autowire=\"byName\" default-lazy-init=\"true\">\n");
		return title.toString();
		
	}
	
	
    private String getDeclare(String className) {
		StringBuffer declare = new StringBuffer();
        declare.append("    <bean id=\"" + className
                + "DAO\" parent=\"hibernateTransactionProxy\">\n");
		declare.append("	    <property name=\"target\">\n");
        declare.append("                <bean class=\"com.cnpc.oms.service.mb.dao.impl."
                + className
                + "DAO\"> \n                </bean>\n           </property>\n    </bean>\n");
		

		return declare.toString();
		
	}

    private String getTitleStruts() {
        StringBuffer title = new StringBuffer();
        title.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<struts-config>\n");
        title.append(" <action-mappings>\n");
        return title.toString();

    }

    private String getDeclareStruts(String tableName, String className) {
        String par = StringUtil.makeParmeterName(tableName);
        StringBuffer declare = new StringBuffer();
        declare.append("         <action path=\"/mb/" + par + "list\"\n"
                + "                 type=\"com.cnpc.oms.webapp.mb.action."
                + className + "ListQueryAction\">\n"
                + "             <forward name=\"success\" path=\"/mb/" + par
                + "List.jsp\"/>\n"
                + "             <serviceCall serviceName=\"Mb_" + className
                + "_Mgr\" operationName=\"query_" + par + "\"/>\n"
                + "        </action>\n");
        declare.append("         <action path=\"/mb/" + par + "query\"\n"
                + "                 type=\"com.cnpc.oms.webapp.mb.action."
                + className + "QueryAction\">\n"
                + "             <forward name=\"success\" path=\"/mb/" + par
                + "List.jsp\"/>\n"
                + "             <serviceCall serviceName=\"Mb_" + className
                + "_Mgr\" operationName=\"query_" + par + "\"/>\n"
                + "        </action>\n");
        declare.append("         <action path=\"/mb/" + par + "add\"\n"
                + "                 type=\"com.cnpc.oms.webapp.mb.action."
                + className + "AddAction\">\n"
                + "             <forward name=\"success\" path=\"/mb/" + par
                + "Add.jsp\"/>\n             <serviceCall name=\"codeservice\" serviceName=\"Drill_DrillBaseCodeList_Mgr\" operationName=\"query_basecodelist\"/>\n"
                + "        </action>\n");
        declare.append("         <action path=\"/mb/"
                + par
                + "insert\"\n"
                + "                 type=\"com.cnpc.oms.webapp.drill.common.DrillDispatchSaveOMSAction\">\n"
                + "             <forward name=\"success\" path=\"/mb/" + par
                + "query.do\"/>\n"
                + "             <serviceCall serviceName=\"Mb_" + className
                + "_Mgr\" operationName=\"insert_" + par + "\"/>\n"
                + "        </action>\n");
        declare.append("         <action path=\"/mb/"
                + par
                + "edit\"\n"
                + "                 type=\"com.cnpc.oms.webapp.mb.action."
                + className + "EditAction\">\n"
                + "             <forward name=\"success\" path=\"/mb/" + par
                + "Edit.jsp\"/>\n"
                + "             <serviceCall name=\"audit\" serviceName=\"Mb_"
                + className
                + "_Mgr\" operationName=\"find_"
                + par
                + "\"/>\n             <serviceCall name=\"codeservice\" serviceName=\"Drill_DrillBaseCodeList_Mgr\" operationName=\"query_basecodelist\"/>\n"
                + "        </action>\n");
        declare.append("         <action path=\"/mb/"
                + par
                + "update\"\n"
                + "                 type=\"com.cnpc.oms.webapp.drill.common.DrillDispatchUpOrDelOMSAction\">\n"
                + "             <forward name=\"success\" path=\"/mb/" + par
                + "query.do\"/>\n"
                + "             <serviceCall serviceName=\"Mb_" + className
                + "_Mgr\" operationName=\"update_" + par + "\"/>\n"
                + "        </action>\n");
        declare.append("         <action path=\"/mb/"
                + par
                + "delete\"\n"
                + "                 type=\"com.cnpc.oms.webapp.drill.common.DrillDispatchUpOrDelOMSAction\">\n"
                + "             <forward name=\"success\" path=\"/mb/" + par
                + "query.do\"/>\n"
                + "             <serviceCall serviceName=\"Mb_" + className
                + "_Mgr\" operationName=\"delete_" + par + "\"/>\n"
                + "        </action>\n");
        declare.append("         <action path=\"/mb/"
                + par
                + "find\"\n"
                + "                 type=\"com.cnpc.oms.webapp.drill.common.DrillDispatchOMSAction\">\n"
                + "             <forward name=\"success\" path=\"/mb/" + par
                + "View.jsp\"/>\n"
                + "             <serviceCall serviceName=\"Mb_" + className
                + "_Mgr\" operationName=\"find_" + par + "\"/>\n"
                + "        </action>\n");
        declare.append("         <action path=\"/mb/"
                + par
                + "draw\"\n"
                + "                 type=\"com.cnpc.oms.webapp.drill.common.DrillDispatchOMSAction\">\n"
                + "             <forward name=\"success\" path=\"/mb/" + par
                + "query.do\"/>\n"
                + "             <serviceCall serviceName=\"Mb_" + className
                + "_Mgr\" operationName=\"draw_" + par + "\"/>\n"
                + "        </action>\n");
        declare.append("         <action path=\"/mb/"
                + par
                + "verify\"\n"
                + "                 type=\"com.cnpc.oms.webapp.drill.common.DrillDispatchOMSAction\">\n"
                + "             <forward name=\"success\" path=\"/mb/" + par
                + "query.do\"/>\n"
                + "             <serviceCall serviceName=\"Mb_" + className
                + "_Mgr\" operationName=\"verify_" + par + "\"/>\n"
                + "        </action>\n");
        declare.append("         <action path=\"/mb/"
                + par
                + "verifyAll\"\n"
                + "                 type=\"com.cnpc.oms.webapp.drill.common.DrillDispatchOMSAction\">\n"
                + "             <forward name=\"success\" path=\"/mb/" + par
                + "query.do\"/>\n"
                + "             <serviceCall serviceName=\"Mb_" + className
                + "_Mgr\" operationName=\"verifyAll_" + par + "\"/>\n"
                + "        </action>\n");
        declare.append("        <!--=================================================-->\n");
        return declare.toString();

    }
	
	

}
