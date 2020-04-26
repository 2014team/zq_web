package com.zq.admin.domain.vo;
import com.zq.common.entity.BaseEntity;
 
/**
 * @ClassName: RightCategoryVo
 * @Description: 权限分类
 * @author zhuzq
 * @date 2020年04月26日 14:21:28
 */ 
public class RightCategoryVo extends BaseEntity{

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