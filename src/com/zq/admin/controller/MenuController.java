package com.zq.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zq.admin.domain.dto.MenuDto;
import com.zq.admin.domain.vo.MenuVo;
import com.zq.admin.service.MenuService;
import com.zq.common.entity.JsonResult;
import com.zq.common.entity.AdminResultByPage;

/**
 * @ClassName: MenuController
 * @Description: 菜单
 * @author zhuzq
 * @date 2020年05月04日 13:39:50
 */
@Controller
public class MenuController {

	@Autowired
	private MenuService menuService;

	/**
	 * @Title: save
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:50
	 * @param menuVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/menu/save", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult save(MenuVo menuVo) {
		JsonResult result = new JsonResult();

		// 参数验证
		String errMsg = menuService.checkParam(menuVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		errMsg = menuService.checkUnique(menuVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}
		// 保存
		boolean save = menuService.saveMenu(menuVo);
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
	 * @date 2020年05月04日 13:39:50
	 * @param menuId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/menu/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult delete(Integer menuId) {
		JsonResult result = new JsonResult();

		// 验证参数
		if (null == menuId) {
			result.failure("菜单ID不能为空");
			return result;
		}
		// 删除
		boolean delete = menuService.deleteMenu(menuId);
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
	 * @date 2020年05月04日 13:39:50
	 * @param menuIdArr
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/menu/batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult deleteByIdArr(@RequestParam("menuIdArr[]") Integer[] menuIdArr) {
		JsonResult result = new JsonResult();
		// 验证参数
		if (null == menuIdArr || menuIdArr.length < 1) {
			result.failure("请选项要删除的数据");
			return result;
		}
		// 删除
		Integer delete = menuService.deleteByBatch(menuIdArr);
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
	 * @date 2020年05月04日 13:39:50
	 * @param menuVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/menu/update", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult update(MenuVo menuVo) {
		JsonResult result = new JsonResult();

		// 验证参数
		Integer menuId = menuVo.getMenuId();
		if (null == menuId) {
			result.failure("菜单ID不能为空");
			return result;
		}
		String errMsg = menuService.checkParam(menuVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		errMsg = menuService.checkUnique(menuVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}
		// 修改
		boolean update = menuService.updateMenu(menuVo);
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
	 * @date 2020年05月04日 13:39:50
	 * @param menuVo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/menu/list", method = { RequestMethod.POST,RequestMethod.GET })
	public AdminResultByPage list(MenuVo menuVo, HttpServletRequest request) {

		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer limit = Integer.valueOf(request.getParameter("limit"));

		AdminResultByPage jsonResult = new AdminResultByPage(page, limit);

		jsonResult = menuService.findByPage(menuVo, jsonResult);

		return jsonResult;
	}

	/**
	 * @Title: toList
	 * @Description: 列表UI
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:50
	 * @return
	 */
	@RequestMapping(value = "/admin/menu/list/ui", method = { RequestMethod.GET })
	public String toList() {
		return "/admin/menu/menu_list";
	}

	/**
	 * @Title: edit
	 * @Description: 编辑
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:50
	 * @param menuId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/menu/edit", method = { RequestMethod.GET, RequestMethod.POST })
	public String edit(Integer menuId, HttpServletRequest request) {
		// 编辑,为空新增
		if (null != menuId) {
			MenuDto menuDTO = menuService.getMenu(menuId);
			request.setAttribute("menuDTO", menuDTO);
		}
		return "/admin/menu/menu_edit";
	}

}