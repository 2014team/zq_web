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

import com.zq.admin.domain.dto.RightCategoryDto;
import com.zq.admin.domain.dto.RoleDto;
import com.zq.admin.domain.vo.RoleVo;
import com.zq.admin.service.RightCategoryService;
import com.zq.admin.service.RightService;
import com.zq.admin.service.RoleService;
import com.zq.common.entity.AdminResultByPage;
import com.zq.common.entity.JsonResult;

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
	public JsonResult save(RoleVo roleVo) {
		JsonResult result = new JsonResult();

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
		} else {
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
		} else {
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
		} else {
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
	public JsonResult update(RoleVo roleVo) {
		JsonResult result = new JsonResult();

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
		} else {
			result.failure();
		}

		return result;
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

}
