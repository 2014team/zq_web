
package com.zq.admin.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zq.admin.constant.SessionConstant;
import com.zq.admin.entity.User;

public class SessionUtil {

	/**
	 * @Title: getSessionUser
	 * @Description: 获取用户session信息
	 * @author zhuzq
	 * @date 2020年4月16日 下午2:22:56
	 * @param request
	 * @return
	 */
	public static User getSessionUser(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Object object = session.getAttribute(SessionConstant.USER_KEY_SESSION);
		if (null != object) {
			return (User) object;
		}
		return null;
	}

	/**
	 * @Title: deleteSessionUser
	 * @Description: 删除用户session信息
	 * @author zhuzq
	 * @date 2020年4月16日 下午2:23:05
	 * @param request
	 */
	public static void deleteSessionUser(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Object object = session.getAttribute(SessionConstant.USER_KEY_SESSION);
		if (null != object) {
			session.removeAttribute(SessionConstant.USER_KEY_SESSION);
		}
	}

	/**
	 * @Title: saveSessionUser
	 * @Description: 保存用户session信息
	 * @author zhuzq
	 * @date 2020年4月16日 下午2:23:14
	 * @param request
	 * @param user
	 */
	public static void saveSessionUser(HttpServletRequest request, User user) {

		HttpSession session = request.getSession();
		session.setAttribute(SessionConstant.USER_KEY_SESSION, user);
	}
}
