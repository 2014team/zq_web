package com.zq.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zq.admin.entity.User;
import com.zq.admin.service.UserService;
import com.zq.admin.util.SessionUtil;
import com.zq.common.entity.JsonResult;
import com.zq.common.entity.JsonResultByPage;

/**
 * @ClassName: UserController
 * @Description: 用户
 * @author zhuzq
 * @date 2020年4月16日 上午10:44:56
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	

	/**
	* @Title: toList
	* @Description: 列表UI
	* @author zhuzq
	* @date  2020年4月16日 下午4:15:42
	* @return
	*/
	@RequestMapping(value = "/admin/user/list/ui")
	public String toList() {
		return "/admin/user/user_list";
	}
	
	
	/**
	* @Title: list
	* @Description: 分页查找
	* @author zhuzq
	* @date  2020年4月16日 下午4:16:05
	* @param entity
	* @param request
	* @return
	*/
	@ResponseBody
	@RequestMapping(value = "/admin/user/list", method = { RequestMethod.POST})
	public JsonResultByPage list(User entity, HttpServletRequest request) {
		
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer limit = Integer.valueOf(request.getParameter("limit"));
		
		JsonResultByPage jsonResult = new JsonResultByPage(page, limit);
		
		jsonResult = userService.findByPage(entity, jsonResult);
		
		return jsonResult;
	}
	
	/**
	* @Title: validFlag
	* @Description: 更新状态
	* @author zhuzq
	* @date  2020年4月17日 上午11:55:10
	* @param entity
	* @return
	*/
	@ResponseBody
	@RequestMapping(value = "/admin/user/validFlag", method = { RequestMethod.POST})
	public JsonResult validFlag(User entity) {
		JsonResult jsonResult = new JsonResult();
		Integer userId = entity.getUserId();
		if(null == userId){
			jsonResult.failure("用户ID参数错误");
			return jsonResult;
		}
		
		Integer validFlag = entity.getValidFlag();
		if(null == validFlag){
			jsonResult.failure("状态参数错误");
			return jsonResult;
		}
		
		User user = userService.get(userId);
		if(null == user){
			jsonResult.failure("更新数据失败");
			return jsonResult;
		}
		
		user.setValidFlag(validFlag);
		
		Integer result = userService.update(user);
		if(null != result && result > 0){
			jsonResult.success(user);
		}else{
			jsonResult.failure();
		}
		return jsonResult;
	}

	/**
	* @Title: login
	* @Description: 登录界面
	* @author zhuzq
	* @date  2020年4月16日 下午3:56:33
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
	* @date  2020年4月16日 下午3:56:47
	* @param user
	* @param request
	* @return
	*/
	@ResponseBody
	@RequestMapping(value = "/admin/login/submit", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult loginSubmit(User user,HttpServletRequest request) {
		JsonResult result = new JsonResult();
		// 验证参数
		String errMsg = userService.checkLoginParam(user);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}
		
		// 登录
		User entity = userService.login(user);
		if (null != entity) {
			result.success();
			
		 // 保存信息到session
		 SessionUtil.saveSessionUser(request, entity);
			
		} else {
			result.failure("用户名或者密码错误!");
		}

		return result;
	}
	

	/**
	 * @Title: save
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年4月16日 上午10:55:30
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/user/save", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult save(User user) {
		JsonResult result = new JsonResult();

		// 验证参数
		String errMsg = userService.checkParam(user);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}
		
		//唯一性验证
		errMsg = userService.checkUnique(user);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}
		
		// 保存
		boolean save = userService.saveUser(user);
		if (save) {
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
	 * @date 2020年4月16日 上午10:55:38
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/user/update", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult update(User user) {
		JsonResult result = new JsonResult();

		// 验证参数
		Integer userId = user.getUserId();
		if (null == userId) {
			result.failure("用户ID不能为空");
			return result;
		}
		String errMsg = userService.checkParam(user);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}
		
		//唯一性验证
		errMsg = userService.checkUnique(user);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}
				
		// 修改
		boolean update = userService.updateUser(user);
		if (update) {
			result.success();
		} else {
			result.failure();
		}

		return result;
	}
	
	
	/**
	* @Title: edit
	* @Description:  编辑
	* @author zhuzq
	* @date  2020年4月17日 下午3:11:29
	* @param userId
	* @param request
	* @return
	*/
	@RequestMapping(value = "/admin/user/edit", method = { RequestMethod.GET, RequestMethod.POST })
	public String edit(Integer userId,HttpServletRequest request) {
		//编辑,为空新增
		if(null != userId){
			User entity = userService.get(userId);
			request.setAttribute("entity", entity);
		}
		
		return "/admin/user/user_edit";
	}

	/**
	 * @Title: delete
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2020年4月16日 上午10:55:13
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

}
