
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

import com.zq.admin.domain.dto.MenuTreeDto;
import com.zq.admin.domain.dto.RightCategoryDto;
import com.zq.admin.domain.dto.RoleDto;
import com.zq.admin.domain.vo.RoleVo;
import com.zq.admin.service.MenuService;
import com.zq.admin.service.RightCategoryService;
import com.zq.admin.service.RightService;
import com.zq.admin.service.RoleService;
import com.zq.common.entity.AdminResultByPage;
import com.zq.common.entity.JsonResult;
import com.zq.common.util.GsonUtil;
import com.zq.common.util.StringUtil;

/**
 * @ClassName: RoleController
 * @Description: 角色
 * @author zhuzq
 * @date 2020年04月30日 14:04:25
 */
@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private RightCategoryService rightCategoryService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private RightService rightService;

	/**
	 * @Title: save
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:25
	 * @param roleVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/role/save", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult save(	RoleVo roleVo,
							@RequestParam(required = false, value = "categoryIdArr[]") Integer[] categoryIdArr,
							@RequestParam(required = false, value = "rightIdArr[]") Integer[] rightIdArr) {

		JsonResult result = new JsonResult();

		roleVo.setCategoryId(StringUtil.trim(categoryIdArr));
		roleVo.setRightId(StringUtil.trim(rightIdArr));

		// 参数验证
		String errMsg = roleService.checkParam(roleVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		errMsg = roleService.checkUnique(roleVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}
		// 保存
		boolean save = roleService.saveRole(roleVo);
		if (save) {
			result.success();
		}
		else {
			result.failure();
		}

		return result;
	}

	/**
	 * @Title: delete
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:25
	 * @param roleId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/role/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult delete(Integer roleId) {

		JsonResult result = new JsonResult();

		// 验证参数
		if (null == roleId) {
			result.failure("角色ID不能为空");
			return result;
		}
		// 删除
		boolean delete = roleService.deleteRole(roleId);
		if (delete) {
			result.success();
		}
		else {
			result.failure();
		}

		return result;
	}

	/**
	 * @Title: batchDelete
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:25
	 * @param roleIdArr
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/role/batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult deleteByIdArr(@RequestParam("roleIdArr[]") Integer[] roleIdArr) {

		JsonResult result = new JsonResult();
		// 验证参数
		if (null == roleIdArr || roleIdArr.length < 1) {
			result.failure("请选项要删除的数据");
			return result;
		}
		// 删除
		Integer delete = roleService.deleteByBatch(roleIdArr);
		if (null != delete && delete > 0) {
			result.success();
		}
		else {
			result.failure();
		}

		return result;
	}

	/**
	 * @Title: update
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:25
	 * @param roleVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/role/update", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult update(	RoleVo roleVo,
								@RequestParam(required = false, value = "categoryIdArr[]") Integer[] categoryIdArr,
								@RequestParam(required = false, value = "rightIdArr[]") Integer[] rightIdArr) {

		JsonResult result = new JsonResult();

		roleVo.setCategoryId(StringUtil.trim(categoryIdArr));
		roleVo.setRightId(StringUtil.trim(rightIdArr));

		// 验证参数
		Integer roleId = roleVo.getRoleId();
		if (null == roleId) {
			result.failure("角色ID不能为空");
			return result;
		}
		String errMsg = roleService.checkParam(roleVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		errMsg = roleService.checkUnique(roleVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}
		// 修改
		boolean update = roleService.updateRole(roleVo);
		if (update) {
			result.success();
		}
		else {
			result.failure();
		}

		return result;
	}

	/**
	 * @Title: validFlag
	 * @Description: 更新状态
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:39:36
	 * @param roleVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/role/validFlag", method = { RequestMethod.POST, RequestMethod.GET })
	public JsonResult validFlag(RoleVo roleVo) {

		JsonResult jsonResult = new JsonResult();
		Integer roleId = roleVo.getRoleId();
		if (null == roleId) {
			jsonResult.failure("角色ID参数错误");
			return jsonResult;
		}

		Integer validFlag = roleVo.getValidFlag();
		if (null == validFlag) {
			jsonResult.failure("状态参数错误");
			return jsonResult;
		}

		// 更新状态
		boolean result = roleService.updateValidFlag(roleVo);
		if (result) {
			jsonResult.success();
		}
		else {
			jsonResult.failure();
		}
		return jsonResult;
	}

	/**
	 * @Title: list
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:25
	 * @param roleVo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/role/list", method = { RequestMethod.POST })
	public AdminResultByPage list(RoleVo roleVo, HttpServletRequest request) {

		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer limit = Integer.valueOf(request.getParameter("limit"));

		AdminResultByPage jsonResult = new AdminResultByPage(page, limit);

		jsonResult = roleService.findByPage(roleVo, jsonResult);

		return jsonResult;
	}

	/**
	 * @Title: toList
	 * @Description: 列表UI
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:25
	 * @return
	 */
	@RequestMapping(value = "/admin/role/list/ui", method = { RequestMethod.GET })
	public String toList() {

		return "/admin/role/role_list";
	}

	/**
	 * @Title: edit
	 * @Description: 编辑
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:25
	 * @param roleId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/role/edit", method = { RequestMethod.GET, RequestMethod.POST })
	public String edit(Integer roleId, HttpServletRequest request) {

		// 编辑,为空新增
		if (null != roleId) {
			RoleDto roleDTO = roleService.getRole(roleId);
			request.setAttribute("roleDTO", roleDTO);
		}

		List<RightCategoryDto> rightCategoryDtoList = rightCategoryService.getRightCategoryList();

		// 权限列表
		List<RightCategoryDto> rightList = rightService.getRightList(rightCategoryDtoList);
		request.setAttribute("rightList", rightList);

		return "/admin/role/role_edit";
	}

	/**
	 * @Title: get
	 * @Description: 获取单个权限
	 * @author zhuzq
	 * @date 2020年5月8日 下午11:54:56
	 * @param roleId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/role/get", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult get(Integer roleId) {

		JsonResult result = new JsonResult();
		// 验证参数
		if (null == roleId || roleId < 1) {
			result.failure("角色ID不能为空");
			return result;
		}
		RoleDto roleDto = roleService.getRole(roleId);
		result.success("roleDTO", roleDto);
		return result;
	}

	/**
	 * @Title: right
	 * @Description: 获取权限列表
	 * @author zhuzq
	 * @date 2020年5月8日 下午11:56:27
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/admin/role/right", method = { RequestMethod.GET, RequestMethod.POST })
	public String right(Integer roleId, HttpServletRequest request) {

		// 获取全部菜单
		List<MenuTreeDto> menuTreeDtoList = menuService.getMenuTree();
		// 获取角色菜单
		String echoMenuId = menuService.getMenuIds(roleId);

		request.setAttribute("menuTreeDtoList", GsonUtil.toJsonAll(menuTreeDtoList));
		request.setAttribute("echoMenuId", echoMenuId);
		request.setAttribute("roleId", roleId);
		return "/admin/role/role_right";
	}

	/**
	 * @Title: saveRight
	 * @Description: 权限设置保存
	 * @author zhuzq
	 * @date 2020年5月10日 下午5:44:18
	 * @param roleVo
	 * @param menuIdArr
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/role/right/save", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult saveRight(RoleVo roleVo,
								@RequestParam(required = false, value = "menuIdArr[]") Integer[] menuIdArr) {

		JsonResult result = new JsonResult();

		Integer roleId = roleVo.getRoleId();
		if (null == roleId || roleId < 1) {
			result.failure("角色ID不能为空");
			return result;
		}

		boolean saveResult = roleService.saveRight(roleId, menuIdArr);
		if (saveResult) {
			result.success();
		}
		else {
			result.failure();
		}
		return result;
	}

}
