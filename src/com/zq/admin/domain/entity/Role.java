package com.zq.admin.domain.entity;
import com.zq.common.entity.BaseEntity;
 
/**
 * @ClassName: Role
 * @Description: 角色
 * @author zhuzq
 * @date 2020年04月30日 14:04:25
 */ 
public class Role extends BaseEntity{

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
	private Integer validFlag;
	/**
	 * 排序
	 */
	private Integer sortId;
 
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
	
	public Integer getValidFlag() {
	
		return validFlag;
	}

	
	public void setValidFlag(Integer validFlag) {
	
		this.validFlag = validFlag;
	}

	public Integer getSortId(){
		return this.sortId;
	}
	
	public void setSortId(Integer sortId){
		this.sortId = sortId;
	}
}