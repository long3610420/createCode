package com.github.pojo;

import java.util.List;

public class Table {

	private String tableName;
	private String comments;

	private List<Column> columns;

	private List<String> pk;

	public List<String> getPk() {
		return pk;
	}

	public void setPk(List<String> pk) {
		this.pk = pk;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
