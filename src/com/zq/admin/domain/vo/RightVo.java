package com.zq.admin.domain.vo;
import com.zq.common.entity.BaseEntity;
 
/**
 * @ClassName: RightVo
 * @Description: 权限
 * @author zhuzq
 * @date 2020年04月29日 11:56:47
 */ 
public class RightVo extends BaseEntity{

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
	 * 排序
	 */
	private Integer sortId;
	
	/**
	 * 开始日期
	 */
	private String startDate;
	/**
	 * 结束日期
	 */
	private String endDate;
 
	public Integer getRightId(){
		return this.rightId;
	}
	
	public void setRightId(Integer rightId){
		this.rightId = rightId;
	}
	public String getRule(){
		return this.rule;
	}
	
	public void setRule(String rule){
		this.rule = rule;
	}
	public String getRightName(){
		return this.rightName;
	}
	
	public void setRightName(String rightName){
		this.rightName = rightName;
	}
	public Integer getRightCategoryId(){
		return this.rightCategoryId;
	}
	
	public void setRightCategoryId(Integer rightCategoryId){
		this.rightCategoryId = rightCategoryId;
	}
	public Integer getSortId(){
		return this.sortId;
	}
	
	public void setSortId(Integer sortId){
		this.sortId = sortId;
	}

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