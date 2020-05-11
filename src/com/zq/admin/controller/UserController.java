package com.zq.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zq.admin.domain.dto.RoleDto;
import com.zq.admin.domain.dto.UserDto;
import com.zq.admin.domain.vo.UserVo;
import com.zq.admin.service.RoleService;
import com.zq.admin.service.UserService;
import com.zq.admin.util.SessionUtil;
import com.zq.common.entity.AdminResultByPage;
import com.zq.common.entity.JsonResult;

/**
 * @ClassName: UserController
 * @Description: 用户
 * @author zhuzq
 * @date 2020年4月23日 下午1:38:44
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	/**
	 * @Title: save
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:38:54
	 * @param userVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/user/save", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult save(UserVo userVo) {
		JsonResult result = new JsonResult();

		// 参数验证
		String errMsg = userService.checkParam(userVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		errMsg = userService.checkUnique(userVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}
		// 保存
		boolean save = userService.saveUser(userVo);
		if (save) {
			result.success();
		} else {
			result.failure();
		}

		return result;
	}

	/**
	 * @Title: delete
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:39:03
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/user/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult delete(Integer userId) {
		JsonResult result = new JsonResult();

		// 验证参数
		if (null == userId) {
			result.failure("用户ID不能为空");
			return result;
		}
		// 删除
		boolean delete = userService.deleteUser(userId);
		if (delete) {
			result.success();
		} else {
			result.failure();
		}

		return result;
	}

	/**
	 * @Title: batchDelete
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:39:19
	 * @param userIdArr
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/user/batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult deleteByIdArr(@RequestParam("userIdArr[]") Integer[] userIdArr) {
		JsonResult result = new JsonResult();
		// 验证参数
		if (null == userIdArr || userIdArr.length < 1) {
			result.failure("请选项要删除的数据");
			return result;
		}
		// 删除
		Integer delete = userService.deleteByBatch(userIdArr);
		if (null != delete && delete > 0) {
			result.success();
		} else {
			result.failure();
		}

		return result;
	}

	/**
	 * @Title: update
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:39:28
	 * @param userVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/user/update", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult update(UserVo userVo) {
		JsonResult result = new JsonResult();

		// 验证参数
		Integer userId = userVo.getUserId();
		if (null == userId) {
			result.failure("用户ID不能为空");
			return result;
		}
		String errMsg = userService.checkParam(userVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		errMsg = userService.checkUnique(userVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}
		// 修改
		boolean update = userService.updateUser(userVo);
		if (update) {
			result.success();
		} else {
			result.failure();
		}

		return result;
	}

	/**
	 * @Title: validFlag
	 * @Description: 更新状态
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:39:36
	 * @param userVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/user/validFlag", method = { RequestMethod.POST })
	public JsonResult validFlag(UserVo userVo) {
		JsonResult jsonResult = new JsonResult();
		Integer userId = userVo.getUserId();
		if (null == userId) {
			jsonResult.failure("用户ID参数错误");
			return jsonResult;
		}

		Integer validFlag = userVo.getValidFlag();
		if (null == validFlag) {
			jsonResult.failure("状态参数错误");
			return jsonResult;
		}

		// 更新状态
		boolean result = userService.updateValidFlag(userVo);
		if (result) {
			jsonResult.success();
		} else {
			jsonResult.failure();
		}
		return jsonResult;
	}

	/**
	 * @Title: list
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:40:00
	 * @param userVo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/user/list", method = { RequestMethod.POST })
	public AdminResultByPage list(UserVo userVo, HttpServletRequest request) {

		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer limit = Integer.valueOf(request.getParameter("limit"));

		AdminResultByPage jsonResult = new AdminResultByPage(page, limit);

		jsonResult = userService.findByPage(userVo, jsonResult);

		return jsonResult;
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
		UserDto userDTO = userService.login(userVo);
		if (null != userDTO) {
			// 保存信息到session
			SessionUtil.saveSessionUser(request, userDTO);
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

		// 删除用户session信息
		SessionUtil.deleteSessionUser(request);
		return "redirect:/admin/login";
	}

	/**
	 * @Title: toList
	 * @Description: 列表UI
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:41:16
	 * @return
	 */
	@RequestMapping(value = "/admin/user/list/ui", method = { RequestMethod.GET })
	public String toList(HttpServletRequest request) {
		List<RoleDto>  roleDtoList = roleService.selectList();
		request.setAttribute("roleDtoList", roleDtoList);
		return "/admin/user/user_list";
	}

	/**
	 * @Title: edit
	 * @Description: 编辑
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:41:25
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/user/edit", method = { RequestMethod.GET, RequestMethod.POST })
	public String edit(Integer userId, HttpServletRequest request) {
		// 编辑,为空新增
		if (null != userId) {
			UserDto userDTO = userService.getUser(userId);
			request.setAttribute("userDTO", userDTO);
		}
		
		List<RoleDto> roleatoList = roleService.selectList();
		request.setAttribute("roleatoList", roleatoList);
		
		
		return "/admin/user/user_edit";
	}
	
	/**
	* @Title: get
	* @Description: 查找
	* @author zhuzq
	* @date  2020年5月3日 下午3:18:05
	* @param userId
	* @return
	*/
	@ResponseBody
	@RequestMapping(value = "/admin/user/get", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult get(Integer userId) {
		
		JsonResult result = new JsonResult();
		// 验证参数
		if (null == userId || userId< 1) {
			result.failure("用户ID不能为空");
			return result;
		}
		UserDto userDto = userService.getUser(userId);
		result.success("userDto", userDto);
		return result;
	}

}
