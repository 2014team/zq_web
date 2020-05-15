package com.zq.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zq.admin.annotation.AdminControllerAfterLog;
import com.zq.admin.domain.dto.UserDto;
import com.zq.admin.domain.vo.UserVo;
import com.zq.admin.service.RightService;
import com.zq.admin.service.UserService;
import com.zq.admin.util.SessionUtil;
import com.zq.common.entity.JsonResult;

/**
 * @ClassName: indexController
 * @Description: 后台中心
 * @author zhuzq
 * @date 2020年4月16日 下午2:29:45
 */
@Controller
public class IndexController {

	@Autowired
	private UserService userService;
	@Autowired
	private RightService rightService;

	
	@RequestMapping(value = "/admin/index", method = { RequestMethod.GET, RequestMethod.POST })
	public String index(HttpServletRequest request) {
		UserDto userDto = SessionUtil.getSessionUser(request);
		request.setAttribute("userDto", userDto);
		return "/admin/index";
	}

	/**
	 * @Title: login
	 * @Description: 登录界面
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:40:09
	 * @return
	 */
	@RequestMapping(value = "/admin/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login() {
		return "/admin/login";
	}

	/**
	 * @Title: loginSubmit
	 * @Description: 登录
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:40:16
	 * @param userVo
	 * @param request
	 * @return
	 */
	@AdminControllerAfterLog(description="登录")
	@ResponseBody
	@RequestMapping(value = "/admin/login/submit", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult loginSubmit(UserVo userVo, HttpServletRequest request) {
		JsonResult result = new JsonResult();
		// 验证参数
		String errMsg = userService.checkLoginParam(userVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 登录
		UserDto userDto = userService.login(userVo);
		if (null != userDto) {
			// 保存信息到session
			SessionUtil.saveSessionUser(request, userDto);

			// 新增用户权限到缓存
			rightService.addCache(userDto);

			result.success();

		} else {
			result.failure("用户名或者密码错误!");
		}

		return result;
	}

	/**
	 * @Title: logout
	 * @Description: 退出
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:40:58
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpServletRequest request) {

		UserDto userDto = SessionUtil.getSessionUser(request);
		if (null != userDto) {

			// 移除用户权限到缓存
			rightService.removeCache(userDto);

			// 删除用户session信息
			SessionUtil.deleteSessionUser(request);

		}

		return "redirect:/admin/login";
	}

}
