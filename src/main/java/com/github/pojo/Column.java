package com.github.pojo;

public class Column {
	
	private String name;
	private String type;
	private int length;
	private int precion;
	private int scale;
	private int nullable;
	private String comment;

    private boolean isPk;

    public boolean isPk() {
        return isPk;
    }

    public void setPk(boolean isPk) {
        this.isPk = isPk;
    }
    public int getNullable() {
		return nullable;
	}
	public void setNullable(int nullable) {
		this.nullable = nullable;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getPrecion() {
		return precion;
	}
	public void setPrecion(int precion) {
		this.precion = precion;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	

}
