package com.github;

import java.util.List;

import com.github.pojo.AppMaker;
import com.github.pojo.BlhMaker;
import com.github.pojo.DaoMaker;
import com.github.pojo.HbmMaker;
import com.github.pojo.PojoJavaMaker;
import com.github.pojo.ServiceMaker;
import com.github.pojo.Table;
import com.github.util.JDBCMsg;

public class TableTxt {

	public static void main(String[] args) throws Exception {

		// String[] names = FileUtil.readTxtToStr("src\\table.txt");
		// List<String> nameList = new ArrayList<String>();
		// for (String n : names) {
		// nameList.add(n);
		// System.out.println("111:" + n);
		// }

		// nameList = Arrays.asList(names);

		List<Table> tables = JDBCMsg.getTables(null);
		for (Table t : tables) {
			System.out.println(t.getTableName());
		}
		PojoJavaMaker pojoJava = new PojoJavaMaker();
		// JspMaker jsp = new JspMaker();
		DaoMaker dao = new DaoMaker();

		HbmMaker hbmMaker = new HbmMaker();
		AppMaker app = new AppMaker();
		BlhMaker blh = new BlhMaker();
		ServiceMaker ser = new ServiceMaker();
		/*
		 * 
		 * for (Table table : tables) { pojoJava.createJavaFile(table);
		 * hbmMaker.createHbmFile(table); dao.createJavaFile(table);
		 * blh.createJavaFile(table); ser.createJavaFile(table);
		 * 
		 * // jsp.createJavaFile(table); } app.createXmlFile(tables);
		 */
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
