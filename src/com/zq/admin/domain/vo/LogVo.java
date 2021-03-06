package com.zq.admin.domain.vo;
import com.zq.admin.domain.entity.Log;
 
/**
 * @ClassName: LogVo
 * @Description: 菜单
 * @author zhuzq
 * @date 2020年05月14日 22:04:08
 */ 
public class LogVo extends Log{

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