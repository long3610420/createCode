package com.github.pojo;

import java.util.List;

import com.github.common.FileUtil;
import com.github.common.StringUtil;

public class WsddMaker {

	public void createXmlFile(List<Table> tables) throws Exception {

		StringBuffer content = new StringBuffer();
		StringBuffer content1 = new StringBuffer();
		// content.append(getTitle());
		for (Table table : tables) {
			String temp = StringUtil.makeParmeterName(table.getTableName()
					.substring(4));
			String className = StringUtil.makeClassName(table.getTableName()
					.substring(4));
			content.append(getDeclareStruts(temp, className));
			content1.append(getDeclare(temp, className));
		}
		// content.append("      <transport name=\"http\">\n"
		// + "        <requestFlow>\n"
		// + "           <handler type=\"URLMapper\"/>\n"
		// + "        </requestFlow>\n" + "      </transport>\n"
		// + " </deployment>");
		FileUtil.writeFile("xmlfile\\server-config.wsdd", content.toString());
		FileUtil.writeFile("xmlfile\\applicationContext.xml",
				content1.toString());
		// FileUtil.writeFile("xmlfile\\applicationContext.xml",content1.toString());
		// FileUtil.fileCopy("jspTemp\\applicationContext.xml",
		// "xmlfile\\applicationContext.xml");
		// FileUtil.replaceTxtByStr("xmlfile\\applicationContext.xml", "@data@",
		// content1.toString());
	}

	private String getTitle() {
		StringBuffer title = new StringBuffer();
		title.append(" <deployment xmlns=\"http://xml.apache.org/axis/wsdd/\"");
		title.append("             xmlns:java=\"http://xml.apache.org/axis/wsdd/providers/java\">");
		title.append("      <handler name=\"URLMapper\" type=\"java:org.apache.axis.handlers.http.URLMapper\"/>");
		return title.toString();

	}

	private String getDeclare(String tableName, String className) {
		StringBuffer declare = new StringBuffer();
		declare.append("	<bean id=\"" + tableName + "\" class=\"com.pojo."
				+ className + "\" scope=\"prototype\"/>\n");
		declare.append("	<bean id=\""
				+ tableName
				+ "Dao\" class=\"com.dao."
				+ className
				+ "Dao\" autowire=\"byName\">\n"
				+ "	  <property name=\"hibernateTemplate\" ref=\"hibernateTemplate\"></property>\n"
				+ "	</bean>\n");
		// declare.append("	<bean id=\"" + tableName
		// + "ManagerImpl\" class=\"com.service.impl." + className
		// + "ManagerImpl\" autowire=\"byName\">\n"
		// + "	  <property name=\"" + tableName + "Dao\" >\n"
		// + "	  <ref bean=\"" + tableName + "Dao\"/>\n"
		// + "	  </property>\n" + "	</bean>\n");

		return declare.toString();

	}

	private String getDeclareStruts(String tableName, String className) {

		StringBuffer declare = new StringBuffer();
		declare.append("      <service name=\""
				+ className
				+ "\" provider=\"java:RPC\">  \n"
				+ "        		  <parameter name=\"className\" value=\"com.service.impl."
				+ className
				+ "ManagerImpl\" />   \n"
				+ "        				  <!-- * 代表所有的方法都暴露 -->        \n"
				+ "         <parameter name=\"allowedMethods\" value=\"*\" />        \n"
				+ "         <parameter name=\"scope\" value=\"request\" />    \n"
				+ "				<messageReceiver\n"
				+ "				class=\"org.apache.axis2.receivers.RawXMLINOutMessageReceiver\" />\n"
				+ "      </service>");
		declare.append("\n");
		return declare.toString();

	}

}
