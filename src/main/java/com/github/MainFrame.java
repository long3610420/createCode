package com.github;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.github.common.FileUtil;
import com.github.pojo.DaoMaker;
import com.github.pojo.HbmMaker;
import com.github.pojo.PojoJavaMaker;
import com.github.pojo.ServiceMaker;
import com.github.pojo.Table;
import com.github.pojo.WsddMaker;
import com.github.pojo.XmlMaker;
import com.github.util.JDBCMsg;
import com.github.util.JDBCUtil;

public class MainFrame {

	public static void main(String[] args) throws Exception {
		Properties ps = new Properties();// Properties类表示了一个持久的属性集
		try {
			ps.load(JDBCUtil.class.getResourceAsStream("adress.properties"));// 从输入流中读取属性列表（键和元素对）。
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] names = FileUtil.readTxtToStr("src\\table.txt");
		List<String> nameList = new ArrayList<String>();
		// for (String n : names) {
		// nameList.add(n);
		// System.out.println("111:" + n);
		// }

		nameList = Arrays.asList(names);

		List<Table> tables = JDBCMsg.getTables(nameList);
		// for (Table t : tables) {
		// System.out.println(t.getTableName());
		// }
		PojoJavaMaker pojoJava = new PojoJavaMaker();
		// JspMaker jsp = new JspMaker();
		DaoMaker dao = new DaoMaker();

		HbmMaker hbmMaker = new HbmMaker();
		WsddMaker app = new WsddMaker();
		// BlhMaker blh = new BlhMaker();
		ServiceMaker ser = new ServiceMaker();
		XmlMaker xml = new XmlMaker();

		for (Table table : tables) {
//			pojoJava.createJavaFile(ps.getProperty("pojo"), table);
//			hbmMaker.createHbmFile(ps.getProperty("pojo"), table);
//			dao.createJavaFile(ps.getProperty("dao"), table);
			xml.createXmlFile(table);
			// ser.createJavaFile(ps.getProperty("service"), table);

			// blh.createJavaFile(table);
			// jsp.createJavaFile(table);
		}
		app.createXmlFile(tables);

		// for (Table table : tables) {
		// System.out.println("			<class type=\"crud\">");
		// System.out.println("				<className>"
		// + StringUtil.makeClassName(table.getTableName())
		// + "</className>");
		// System.out.println("				<objectName>"
		// + StringUtil.makeParmeterName(table.getTableName())
		// + "</objectName>");
		// System.out.println("				<url>cjfsy</url>");
		// System.out.println("				<prefix></prefix>");
		// System.out.println("				<suffix></suffix>");
		// System.out.println("				<description>" + table.getComments()
		// + "</description>");
		// System.out.println("				<tableName>" + table.getTableName()
		// + "</tableName>");
		// System.out.println("				<view>jsp</view>");
		// System.out.println("			</class>");
		// }

	}
}
