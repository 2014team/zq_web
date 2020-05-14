package com.zq.admin.domain.vo;
import com.zq.common.entity.BaseEntity;
 
/**
 * @ClassName: LogVo
 * @Description: 菜单
 * @author zhuzq
 * @date 2020年05月14日 22:04:08
 */ 
public class LogVo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**
	 * 日志ID
	 */
	private Integer logId;
	/**
	 * 0:正常日志1:错误日志
	 */
	private Integer logType;
	/**
	 * 操作人
	 */
	private String operator;
	/**
	 * 请求IP
	 */
	private String requestIp;
	/**
	 * 请求方法
	 */
	private String requestMethod;
	/**
	 * 请求参数
	 */
	private String requestParams;
	/**
	 * 方法描述
	 */
	private String methodDescrible;
	/**
	 * 错误码
	 */
	private String exceptionCode;
	/**
	 * 错误详情
	 */
	private String exceptionDetail;
 
	public Integer getLogId(){
		return this.logId;
	}
	
	public void setLogId(Integer logId){
		this.logId = logId;
	}
	public Integer getLogType(){
		return this.logType;
	}
	
	public void setLogType(Integer logType){
		this.logType = logType;
	}
	public String getOperator(){
		return this.operator;
	}
	
	public void setOperator(String operator){
		this.operator = operator;
	}
	public String getRequestIp(){
		return this.requestIp;
	}
	
	public void setRequestIp(String requestIp){
		this.requestIp = requestIp;
	}
	public String getRequestMethod(){
		return this.requestMethod;
	}
	
	public void setRequestMethod(String requestMethod){
		this.requestMethod = requestMethod;
	}
	public String getRequestParams(){
		return this.requestParams;
	}
	
	public void setRequestParams(String requestParams){
		this.requestParams = requestParams;
	}
	public String getMethodDescrible(){
		return this.methodDescrible;
	}
	
	public void setMethodDescrible(String methodDescrible){
		this.methodDescrible = methodDescrible;
	}
	public String getExceptionCode(){
		return this.exceptionCode;
	}
	
	public void setExceptionCode(String exceptionCode){
		this.exceptionCode = exceptionCode;
	}
	public String getExceptionDetail(){
		return this.exceptionDetail;
	}
	
	public void setExceptionDetail(String exceptionDetail){
		this.exceptionDetail = exceptionDetail;
	}
}