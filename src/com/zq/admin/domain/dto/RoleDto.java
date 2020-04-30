package com.zq.admin.domain.dto;
import java.util.List;
import java.util.Map;

import com.zq.common.entity.BaseEntity;
 
/**
 * @ClassName: RoleDto
 * @Description: 角色
 * @author zhuzq
 * @date 2020年04月30日 14:04:25
 */ 
public class RoleDto extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**
	 * 角色ID
	 */
	private Integer roleId;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 权限类别ID
	 */
	private String categoryId;
	/**
	 * 权限ID
	 */
	private String rightId;
	/**
	 * 有效标识 0:启用1：停用
	 */
	private byte validFlag;
	/**
	 * 排序
	 */
	private Integer sortId;
	
	
	/**
	* 权限
	*/
	private Map<List<RightCategoryDto>,List<RightDto>> rightMap;
 
	public Integer getRoleId(){
		return this.roleId;
	}
	
	public void setRoleId(Integer roleId){
		this.roleId = roleId;
	}
	public String getRoleName(){
		return this.roleName;
	}
	
	public void setRoleName(String roleName){
		this.roleName = roleName;
	}
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	public String getCategoryId(){
		return this.categoryId;
	}
	
	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}
	public String getRightId(){
		return this.rightId;
	}
	
	public void setRightId(String rightId){
		this.rightId = rightId;
	}
	public byte getValidFlag(){
		return this.validFlag;
	}
	
	public void setValidFlag(byte validFlag){
		this.validFlag = validFlag;
	}
	public Integer getSortId(){
		return this.sortId;
	}
	
	public void setSortId(Integer sortId){
		this.sortId = sortId;
	}
}