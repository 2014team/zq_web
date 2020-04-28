package com.zq.admin.domain.dto;
import com.zq.common.entity.BaseEntity;
 
/**
 * @ClassName: RightCategoryDto
 * @Description: 权限分类
 * @author zhuzq
 * @date 2020年04月28日 15:54:44
 */ 
public class RightCategoryDto extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**
	 * 类别ID
	 */
	private Integer categoryId;
	/**
	 * 类别名
	 */
	private String categoryName;
	/**
	 * 排序
	 */
	private Integer sortId;
 
	public Integer getCategoryId(){
		return this.categoryId;
	}
	
	public void setCategoryId(Integer categoryId){
		this.categoryId = categoryId;
	}
	public String getCategoryName(){
		return this.categoryName;
	}
	
	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}
	public Integer getSortId(){
		return this.sortId;
	}
	
	public void setSortId(Integer sortId){
		this.sortId = sortId;
	}
}