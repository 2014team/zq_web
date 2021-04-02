package com.zq.admin.domain.vo;
import com.zq.admin.domain.entity.Role;
 
/**
 * @ClassName: RoleVo
 * @Description: 角色
 * @author zhuzq
 * @date 2020年04月30日 14:04:26
 */ 
public class RoleVo extends Role{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 开始日期
	 */
	private String startDate;
	/**
	 * 结束日期
	 */
	private String endDate;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
}