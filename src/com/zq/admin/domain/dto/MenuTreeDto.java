
package com.zq.admin.domain.dto;

import java.io.Serializable;
import java.util.List;


public class MenuTreeDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;//节点唯一索引值，用于对指定节点进行各类操作

	private boolean checked;// 节点是否初始为选中状态（如果开启复选框的话），默认 false

	private String field;// 节点字段名

	private String title;// 节点标题

	private boolean spread;// 节点是否初始展开，默认 false

	private List<MenuTreeDto> children;// childrenr

	
	public Integer getId() {
	
		return id;
	}

	
	public void setId(Integer id) {
	
		this.id = id;
	}

	
	public boolean isChecked() {
	
		return checked;
	}

	
	public void setChecked(boolean checked) {
	
		this.checked = checked;
	}

	
	public String getField() {
	
		return field;
	}

	
	public void setField(String field) {
	
		this.field = field;
	}

	
	public String getTitle() {
	
		return title;
	}

	
	public void setTitle(String title) {
	
		this.title = title;
	}

	
	public boolean isSpread() {
	
		return spread;
	}

	
	public void setSpread(boolean spread) {
	
		this.spread = spread;
	}

	
	public List<MenuTreeDto> getChildren() {
	
		return children;
	}

	
	public void setChildren(List<MenuTreeDto> children) {
	
		this.children = children;
	}

	
}