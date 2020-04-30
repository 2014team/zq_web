package com.zq.admin.domain.dto;

import com.zq.common.entity.BaseEntity;

/**
 * @ClassName: RightDto
 * @Description: 权限
 * @author zhuzq
 * @date 2020年04月29日 11:56:47
 */
public class RightDto extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 权限ID
	 */
	private Integer rightId;
	/**
	 * 规则
	 */
	private String rule;
	/**
	 * 权限名称
	 */
	private String rightName;
	/**
	 * 权限类别ID
	 */
	private Integer rightCategoryId;
	/**
	 * 分类名称
	 */
	private String rightCategoryName;
	/**
	 * 排序
	 */
	private Integer sortId;

	public Integer getRightId() {
		return rightId;
	}

	public void setRightId(Integer rightId) {
		this.rightId = rightId;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public Integer getRightCategoryId() {
		return rightCategoryId;
	}

	public void setRightCategoryId(Integer rightCategoryId) {
		this.rightCategoryId = rightCategoryId;
	}

	public String getRightCategoryName() {
		return rightCategoryName;
	}

	public void setRightCategoryName(String rightCategoryName) {
		this.rightCategoryName = rightCategoryName;
	}

	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

}