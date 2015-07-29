package com.github.pojo;

import java.sql.SQLException;
import java.util.List;

import com.github.common.FileUtil;
import com.github.common.StringUtil;

public class DaoMaker {

	public void createJavaFile(String adress, Table table) throws Exception {

		String className = StringUtil.makeClassName(table.getTableName()
				.substring(3, table.getTableName().length()));

		// StringBuffer content = new StringBuffer();
		StringBuffer content1 = new StringBuffer();
		//
		// content.append(getImport(className));
		// content.append(getTitle(table.getTableName()));
		// content.append(getConstrator(table.getTableName()));
		//
		// content.append("\n\n }");

		content1.append(getImportImpl(className));
		content1.append(getConstratorImpl(
				table.getTableName()
						.substring(4, table.getTableName().length()), table
						.getPk(), table.getColumns()));
		content1.append("\n\n }");

		// FileUtil.writeFile("daofile\\I" + className + "DAO.java",
		// content.toString());
		FileUtil.writeFile(adress + "\\" + className + "Dao.java",
				content1.toString());
	}

	private String getImport(String className) {

		String imports = "package com.cnpc.oms.service.mb.dao;\n\n";
		imports = imports + "import java.util.List;" + "\n";
		imports = imports + "import java.util.Map;" + "\n";
		imports = imports + "import com.cnpc.oms.dao.IDAO;" + "\n";
		imports = imports + "import com.cnpc.oms.dao.PageModel;" + "\n";
		imports = imports + "import com.cnpc.oms.audit.pojo." + className
				+ ";\n\n\n";

		return imports;
	}

	private String getImportImpl(String className) {

		String imports = "package com.dao;\n\n";

		imports = imports + "import java.sql.Connection;\n";
		imports = imports + "import java.sql.SQLException;\n";
		imports = imports + "import java.sql.Statement;\n";
		imports = imports + "import java.text.ParseException;\n";
		imports = imports + "import java.text.SimpleDateFormat;\n";
		imports = imports + "import java.util.Date;\n";
		imports = imports + "import java.util.List;\n";
		imports = imports + "import java.util.Map;\n";
		imports = imports
				+ "import org.springframework.dao.DataAccessException;\n";

		imports = imports + "import org.apache.commons.lang.StringUtils;\n";
		imports = imports + "import org.hibernate.Criteria;\n";
		imports = imports + "import org.hibernate.criterion.Restrictions;\n";
		imports = imports
				+ "import org.springframework.dao.EmptyResultDataAccessException;\n";

		imports = imports + "import com.pojo." + className + ";\n";
		// imports = imports + "import com.cnpc.oms.service.mb.dao.I" +
		// className
		// + "DAO;\n";
		imports = imports + "import org.hibernate.HibernateException;\n";
		imports = imports + "import org.hibernate.Query;\n";
		imports = imports + "import org.hibernate.Session;\n";
		imports = imports + "import org.slf4j.Logger;\n";
		imports = imports + "import org.slf4j.LoggerFactory;\n";
		imports = imports
				+ "import org.springframework.orm.hibernate3.HibernateCallback;\n";
		imports = imports
				+ "import org.springframework.orm.hibernate3.support.HibernateDaoSupport;\n";
		return imports;
	}

	private String getTitle(String tableName) {

		String className = StringUtil.makeClassName(tableName);

		StringBuffer title = new StringBuffer();

		title.append("public interface I");
		title.append(className);
		title.append("DAO  extends IDAO {\n\n");

		return title.toString();

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
		constrator.append("    public PageModel get");
		constrator.append(className);
		constrator.append("(Map map,PageModel ps);\n");
		constrator.append("    public ");
		constrator.append(className);
		constrator.append(" find (String id);\n");
		constrator.append("    public Map findExist(String id);\n");
		constrator.append("    public List getView" + className
				+ "(Map map);\n");
		constrator.append("    public void verifyAll();\n");
		constrator.append("    public void verify(String str);");
		constrator.append("\n\n\n");

		return constrator.toString();

	}

	private String getConstratorImpl(String tableName, List<String> pk,
			List<Column> columns) throws SQLException {

		String className = StringUtil.makeClassName(tableName);
		String pName = StringUtil.makeParmeterName(tableName);
		StringBuffer constrator = new StringBuffer();
		constrator.append("\n\n\n");
		constrator
				.append("public class "
						+ className
						+ "Dao extends HibernateDaoSupport {\n"
						+ "	private static final Logger log = LoggerFactory\n"
						+ "			.getLogger("
						+ className
						+ "Dao.class); // ����ķ���\n"

						+ "	public String save("
						+ className
						+ " "
						+ pName
						+ ") {\n"
						+ "		log.debug(\"saving com.pojo."
						+ className
						+ " instance\");\n"
						+ "		int id = 0;\n"
						+ "		try {\n"
						+ "			id = (Integer) super.getHibernateTemplate().save("
						+ pName
						+ ");\n"
						+ "			log.debug(\"save successful\");\n"
						+ "		} catch (RuntimeException re) {\n"
						+ "			log.error(\"save failed\", re);\n"
						+ "			return \"failed\";\n"
						+ "		}\n"
						+ "		return \"\"+id;\n"
						+ "	}\n"

						+ "	// ɾ��ķ���\n"
						+ "	public String delete("
						+ className
						+ " "
						+ pName
						+ ") {\n"
						+ "		log.debug(\"deleting com.pojo."
						+ className
						+ " instance\");\n"
						+ "		try {\n"
						+ "			getHibernateTemplate().delete("
						+ pName
						+ ");\n"
						+ "			log.debug(\"delete successful\");\n"
						+ "		} catch (RuntimeException re) {\n"
						+ "			log.error(\"delete failed\", re);\n"
						+ "			return \"failed\";\n"
						+ "		}\n"
						+ "		return \"succes\";\n"
						+ "	}\n"

						+ "	// ���ʵ�������Id�õ�ʵ��ķ���\n"
						+ "	public "
						+ className
						+ " findById(java.lang.Integer id) {\n"
						+ "		log.debug(\"getting com.pojo."
						+ className
						+ " instance with id: \" + id);\n"
						+ "		try {\n"
						+ "			"
						+ className
						+ " instance = ("
						+ className
						+ ") getHibernateTemplate()\n"
						+ "					.get(\"com.pojo."
						+ className
						+ "\", id);\n"
						+ "			return instance;\n"
						+ "		} catch (RuntimeException re) {\n"
						+ "			log.error(\"get failed\", re);\n"
						+ "			throw re;\n"
						+ "		}\n"
						+ "	}\n"

						+ "	// ���ȫ��ķ�����������ҳ\n"
						+ "	public List findAll(String tj) {\n"
						+ "		log.debug(\"finding all com.pojo."
						+ className
						+ " instances\");\n"
						+ "		try {\n"
						+ "			String queryString = \"from com.pojo."
						+ className
						+ "\";\n"
						+ "						if (null != tj && !tj.equals(\"\")) {\n"
						+ "							queryString += \" where 1=1 \" + tj;\n"
						+ "						}\n"
						+ "			return getHibernateTemplate().find(queryString);\n"
						+ "		} catch (RuntimeException re) {\n"
						+ "			log.error(\"find all failed\", re);\n"
						+ "			throw re;\n"
						+ "		}\n"
						+ "	}\n"

						+ "	// ���ȫ��ķ���������ҳ\n"

						+ "	public List findAll(String tj, int pageSize, int pageNow) {\n"
						+ "		log.debug(\"finding all com.pojo."
						+ className
						+ " instances\");\n"
						+ "		Session session = null;\n"
						+ "		try {\n"
						+ "			String queryString = \"from com.pojo."
						+ className
						+ "\";\n"
						+ "			if (null != tj && !tj.equals(\"\")) {\n"
						+ "				queryString += \" where 1=1 \" + tj;\n"
						+ "			}\n"
						+ "			session = this.getSessionFactory().openSession();\n"
						+ "			Query query = session.createQuery(queryString);\n"
						+ "			int firstResultIndex = pageSize * (pageNow - 1);\n"
						+ "			query.setFirstResult(firstResultIndex);\n"
						+ "			query.setMaxResults(pageSize);\n"
						+ "			List list = query.list();\n"
						+ "			return list;\n"
						+ "		} catch (RuntimeException re) {\n"
						+ "			log.error(\"find all failed\", re);\n"
						+ "			throw re;\n"
						+ "		} finally {// �ǵ�sessionҪ�رգ���Ȼ���������������ݻ���\n"
						+ "			session.close();\n"
						+ "		}\n"
						+ "	}\n"

						+ "	// �޸ĵķ��������������κζ��У�����ǵ�һ������һ��ʵ�壬�ڶ��������� //�޸ĵķ���1\n"
						+ "	public "
						+ className
						+ " merge("
						+ className
						+ " u) {\n"
						+ "		log.debug(\"merging com.pojo."
						+ className
						+ " instance\");\n"
						+ "		try {\n"
						+ "			"
						+ className
						+ " result = ("
						+ className
						+ ") getHibernateTemplate()\n"
						+ "					.merge(u);\n"
						+ "			log.debug(\"merge successful\");\n"
						+ "			return result;\n"
						+ "		} catch (RuntimeException re) {\n"
						+ "			log.error(\"merge failed\", re);\n"
						+ "			throw re;\n"
						+ "		}\n"
						+ "	}\n"

						+ "						// �޸ĵķ���2\n"
						+ "						public void update("
						+ className
						+ " u) {\n"
						+ "							log.debug(\"merging com.pojo."
						+ className
						+ " instance\");\n"
						+ "							Session session = null;\n"
						+ "							try {\n"
						+ "								session = this.getSessionFactory().openSession();\n"
						+ "								"
						+ className
						+ " obj = ("
						+ className
						+ ") session.get("
						+ className
						+ ".class, u.getId());\n"
						+ getMethods(columns)
						+ "								session.update(obj);\n"
						+ "								session.flush();\n"
						+ "							} catch (Exception e) { // TODO Auto-generated catch block\n"
						+ "								e.printStackTrace();\n"
						+ "							} finally {\n"
						+ "								// �ǵ�sessionҪ�رգ���Ȼ���������������ݻ���\n"
						+ "								session.close();\n"
						+ "							}\n"
						+ "						}\n"

						+ "	// �õ��ܹ���������ݵķ���\n"
						+ "	public int getCount() {\n"
						// + "		System.out.println(\"pageCount\");\n"
						+ "		Session session = null;\n"
						+ "		int count = 0;\n"
						+ "		try {\n"
						+ "			String queryString = \"select count(*) from com.pojo."
						+ className
						+ "\";\n"
						+ "			session = this.getSessionFactory().openSession();\n"
						+ "			Query query = session.createQuery(queryString);\n"
						+ "			count = Integer.valueOf(query.uniqueResult().toString());\n"
						+ "		} catch (Exception e) { // TODO Auto-generated catch block\n"
						+ "			e.printStackTrace();\n"
						+ "		} finally {\n"
						+ "			// �ǵ�sessionҪ�رգ���Ȼ���������������ݻ���\n"
						+ "			session.close();\n"
						+ "		}\n"
						+ "		return count;\n"
						+ "	}\n"

						+ "	// ÿpageSize����ݣ��м�ҳ���\n"
						+ "	public int getPageCount(int count, int pageSize) {\n"
						+ "		int pageCount = count / pageSize;\n"
						+ "		if (count % pageSize > 0) {\n"
						+ "			pageCount++;\n" + "		}\n"
						+ "		return pageCount;\n" + "	}\n");
		return constrator.toString();

	}

	private String getMethods(List<Column> columns) {

		StringBuffer methods = new StringBuffer();
		for (Column column : columns) {
			String temp = StringUtil.firstUpCase(column.getName());
			if (StringUtil.changeType(column).equals("int")) {
				methods.append("								if (0 != u.get" + temp + "())");
			} else {
				methods.append("								if (null != u.get" + temp + "())");
			}
			methods.append("\n");
			methods.append("									obj.set" + temp + "(u.get" + temp + "());");
			methods.append("\n");
		}

		return methods.toString();
	}
}
