
package com.zq.admin.annotation;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zq.admin.constant.LogTypeEnum;
import com.zq.admin.domain.dto.UserDto;
import com.zq.admin.domain.entity.Log;
import com.zq.admin.service.LogService;
import com.zq.admin.util.SessionUtil;
import com.zq.common.util.GsonUtil;
import com.zq.common.util.LogUtil;
import com.zq.common.util.ToolsUtil;

/**
 * @ClassName: AdminLogAspect
 * @Description: 切点类
 * @author zhuzq
 * @date 2020年5月14日 下午8:27:55
 */
@Aspect
@Component
public class AdminLogAspect implements Ordered {

	@Autowired
	private LogService logService;

	// Service层切点
	@Pointcut("@annotation(com.zq.admin.annotation.AdminServiceLog)")
	public void serviceAspect() {

	}

	// Controller层切点
	@Pointcut("@annotation(com.zq.admin.annotation.AdminControllerLog)")
	public void controllerAspect() {

	}

	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint 切点
	 */
	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {

		HttpServletRequest request = (( ServletRequestAttributes ) RequestContextHolder.getRequestAttributes()).getRequest();
		UserDto userDto = SessionUtil.getSessionUser(request);

		try {
			// 请求的IP
			String ip = ToolsUtil.getIpAddress(request);
			String operator = userDto.getUserName();
			String method = (joinPoint.getTarget().getClass().getName()	+ "." + joinPoint.getSignature().getName()+ "()");
			String methodDescrible = getControllerMethodDescription(joinPoint);

			LogUtil.logInfo("=====前置通知开始=====");
			LogUtil.logInfo("请求方法:" + method);
			LogUtil.logInfo("方法描述:" + methodDescrible);
			LogUtil.logInfo("请求人:" + operator);
			LogUtil.logInfo("请求IP:" + ip);

			Log log = new Log();
			log.setOperator(operator);
			log.setLogType(LogTypeEnum.SUCCESS.ordinal());
			log.setRequestIp(ip);
			log.setRequestMethod(method);
			log.setMethodDescrible(methodDescrible);
			log.setExceptionCode("");
			log.setExceptionDetail("");
			log.setRequestParams("");
			logService.save(log);
		}
		catch (Exception e) {
			LogUtil.logError(e.getMessage());
		}
	}

	/**
	 * 异常通知 用于拦截service层记录异常日志
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {

		HttpServletRequest request = (( ServletRequestAttributes ) RequestContextHolder.getRequestAttributes()).getRequest();
		UserDto userDto = SessionUtil.getSessionUser(request);

		try {
			String ip = ToolsUtil.getIpAddress(request);
			String operator = userDto.getUserName();
			String method = (joinPoint.getTarget().getClass().getName()	+ "." + joinPoint.getSignature().getName()
								+ "()");
			String methodDescrible = getServiceMthodDescription(joinPoint);
			String exceptionCode = e.getClass().getName();
			String exceptionDetail = StringUtils.isBlank(e.getMessage())?"":e.getMessage();
			// 获取用户请求方法的参数并序列化为JSON格式字符串
			String params = "";
			if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
				for (int i = 0; i < joinPoint.getArgs().length; i++) {
					params += GsonUtil.toJsonAll(joinPoint.getArgs()[i]) + ";";
				}
			}
			LogUtil.logInfo("=====异常通知 开始=====");
			LogUtil.logInfo("请求方法:" + method);
			LogUtil.logInfo("方法描述:" + methodDescrible);
			LogUtil.logInfo("请求人:" + operator);
			LogUtil.logInfo("请求IP:" + ip);

			Log log = new Log();
			log.setOperator(operator);
			log.setLogType(LogTypeEnum.FAIL.ordinal());
			log.setRequestIp(ip);
			log.setRequestMethod(method);
			log.setMethodDescrible(methodDescrible);
			log.setExceptionCode(exceptionCode);
			log.setExceptionDetail(exceptionDetail);
			log.setRequestParams(params);
			logService.save(log);

		}
		catch (Exception e2) {
			e2.printStackTrace();
			LogUtil.logError(joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName());
		}

	}

	/**
	 * 获取注解中对方法的描述信息 用于service层注解
	 * @param joinPoint 切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getServiceMthodDescription(JoinPoint joinPoint) throws Exception {

		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(AdminServiceLog.class).description();
					break;
				}
			}
		}
		return description;
	}

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint 切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {

		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(AdminControllerLog.class).description();
					break;
				}
			}
		}
		return description;
	}

	@Override
	public int getOrder() {
		return 1;
	}
}