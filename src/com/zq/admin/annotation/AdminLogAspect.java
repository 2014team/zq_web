
package com.zq.admin.annotation;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
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
 * 
 * @Before: 前置通知, 在方法执行之前执行
 * @After: 后置通知, 在方法执行之后执行 。
 * @AfterRunning: 返回通知, 在方法返回结果之后执行
 * @AfterThrowing: 异常通知, 在方法抛出异常之后
 * @Around: 环绕通知, 围绕着方法执行
 * 
 * 
 */
@Aspect
@Component
public class AdminLogAspect implements Ordered {

	@Autowired
	private LogService logService;

	// Service层切点
	@Pointcut("@annotation(com.zq.admin.annotation.AdminServiceLog)")
	public void afterThrowingAspect() {
	}

	// Controller层切点
	@Pointcut("@annotation(com.zq.admin.annotation.AdminControllerLog)")
	public void beforeAspect() {
	}

	// Controller层切点
	@Pointcut("@annotation(com.zq.admin.annotation.AdminControllerAfterLog)")
	public void afterAspect() {

	}

	/**
	 * @Title: Before
	 * @Description: 前置通知 用于拦截Controller层记录用户的操作
	 * @author zhuzq
	 * @date 2020年5月15日 下午5:08:45
	 * @param joinPoint
	 */
	@Before("beforeAspect()")
	public void Before(JoinPoint joinPoint) {

		String methodDescrible = "";
		try {
			methodDescrible = getBeforDescription(joinPoint);
			// 保存日志
			saveLog(joinPoint, methodDescrible);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @Title: after
	 * @Description:通知 用于拦截Controller层(例如登录)
	 * @author zhuzq
	 * @date 2020年5月15日 下午5:09:04
	 * @param joinPoint
	 */
	@After("afterAspect()")
	public void after(JoinPoint joinPoint) {
		String methodDescrible = "";
		try {
			methodDescrible = getAfterDescription(joinPoint);
			// 保存日志
			saveLog(joinPoint, methodDescrible);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: afterThrowing
	 * @Description: 异常通知 用于拦截service层记录异常日志
	 * @author zhuzq
	 * @date 2020年5月15日 下午5:16:13
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "afterThrowingAspect()", throwing = "e")
	public void afterThrowing(JoinPoint joinPoint, Throwable e) {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		UserDto userDto = SessionUtil.getSessionUser(request);

		try {
			String ip = ToolsUtil.getIpAddress(request);
			String operator = userDto.getUserName();
			String method = (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()
					+ "()");
			String methodDescrible = getAfterThrowingDescription(joinPoint);

			StringBuffer scSb = new StringBuffer();
			if (StringUtils.isNotBlank(e.getClass().getName())) {
				scSb.append(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : "");
				scSb.append("\r\n");
				scSb.append(e.getClass().getName());
			}

			String exceptionCode = scSb.toString();

			StringBuffer sb = new StringBuffer();
			StackTraceElement[] stackTraceElement = e.getStackTrace();
			if (null != stackTraceElement && stackTraceElement.length > 0) {
				for (StackTraceElement et : stackTraceElement) {
					sb.append(et);
					sb.append("\r\n");
				}
			}
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
			log.setExceptionDetail(sb.toString());
			log.setRequestParams(params);
			logService.save(log);

		} catch (Exception e2) {
			e2.printStackTrace();
			LogUtil.logError(joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName());
		}

	}

	/**
	 * @Title: getBeforDescription
	 * @Description: 获取异常通知上的注解内容
	 * @author zhuzq
	 * @date 2020年5月15日 下午5:16:22
	 * @param joinPoint
	 * @return
	 * @throws Exception
	 */
	public static String getAfterThrowingDescription(JoinPoint joinPoint) throws Exception {
		MethodSignature methodName = (MethodSignature) joinPoint.getSignature();
		Method method = methodName.getMethod();
		return method.getAnnotation(AdminServiceLog.class).description();
	}

	/**
	 * @Title: saveLog
	 * @Description: 保存日志
	 * @author zhuzq
	 * @date 2020年5月15日 下午5:15:47
	 * @param joinPoint
	 * @param methodDescrible
	 */
	private void saveLog(JoinPoint joinPoint, String methodDescrible) {
		try {

			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			UserDto userDto = SessionUtil.getSessionUser(request);

			// 请求的IP
			String ip = ToolsUtil.getIpAddress(request);
			String operator = userDto.getUserName();
			String method = (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()
					+ "()");

			LogUtil.logInfo("=========");
			LogUtil.logInfo("请求方法:" + method);
			LogUtil.logInfo("方法描述:" + methodDescrible);
			LogUtil.logInfo("请求人:" + operator);
			LogUtil.logInfo("请求IP:" + ip);
			LogUtil.logInfo("=========");

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
		} catch (Exception e) {
			LogUtil.logError(e.getMessage());
		}

	}

	/**
	 * @Title: getBeforDescription
	 * @Description: 获取前置通知上的注解内容
	 * @author zhuzq
	 * @date 2020年5月15日 下午5:16:22
	 * @param joinPoint
	 * @return
	 * @throws Exception
	 */
	public static String getBeforDescription(JoinPoint joinPoint) throws Exception {
		MethodSignature methodName = (MethodSignature) joinPoint.getSignature();
		Method method = methodName.getMethod();
		return method.getAnnotation(AdminControllerLog.class).description();
	}

	/**
	 * @Title: getAfterDescription
	 * @Description: 获取后置通知上的注解内容
	 * @author zhuzq
	 * @date 2020年5月15日 下午5:19:37
	 * @param joinPoint
	 * @return
	 * @throws Exception
	 */
	public static String getAfterDescription(JoinPoint joinPoint) throws Exception {
		MethodSignature methodName = (MethodSignature) joinPoint.getSignature();
		Method method = methodName.getMethod();
		return method.getAnnotation(AdminControllerAfterLog.class).description();
	}

	@Override
	public int getOrder() {
		return 1;
	}
}