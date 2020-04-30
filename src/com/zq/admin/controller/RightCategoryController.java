package com.zq.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zq.admin.domain.dto.RightCategoryDto;
import com.zq.admin.domain.vo.RightCategoryVo;
import com.zq.admin.service.RightCategoryService;
import com.zq.common.entity.AdminResultByPage;
import com.zq.common.entity.JsonResult;

/**
 * @ClassName: RightCategoryController
 * @Description: 权限分类
 * @author zhuzq
 * @date 2020年04月28日 15:54:44
 */
@Controller
public class RightCategoryController {

	@Autowired
	private RightCategoryService rightCategoryService;

	/**
	 * @Title: save
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategoryVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/rightCategory/save", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult save(RightCategoryVo rightCategoryVo) {
		JsonResult result = new JsonResult();

		// 参数验证
		String errMsg = rightCategoryService.checkParam(rightCategoryVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		errMsg = rightCategoryService.checkUnique(rightCategoryVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}
		// 保存
		boolean save = rightCategoryService.saveRightCategory(rightCategoryVo);
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
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategoryId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/rightCategory/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult delete(Integer categoryId) {
		JsonResult result = new JsonResult();

		// 验证参数
		if (null == categoryId) {
			result.failure("类别ID不能为空");
			return result;
		}
		// 删除
		boolean delete = rightCategoryService.deleteRightCategory(categoryId);
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
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategoryIdArr
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/rightCategory/batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult deleteByIdArr(@RequestParam("categoryIdArr[]") Integer[] categoryIdArr) {
		JsonResult result = new JsonResult();
		// 验证参数
		if (null == categoryIdArr || categoryIdArr.length < 1) {
			result.failure("请选项要删除的数据");
			return result;
		}
		// 删除
		Integer delete = rightCategoryService.deleteByBatch(categoryIdArr);
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
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategoryVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/rightCategory/update", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult update(RightCategoryVo rightCategoryVo) {
		JsonResult result = new JsonResult();

		// 验证参数
		Integer categoryId = rightCategoryVo.getCategoryId();
		if (null == categoryId) {
			result.failure("类别ID不能为空");
			return result;
		}
		String errMsg = rightCategoryService.checkParam(rightCategoryVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		errMsg = rightCategoryService.checkUnique(rightCategoryVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}
		// 修改
		boolean update = rightCategoryService.updateRightCategory(rightCategoryVo);
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
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategoryVo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/rightCategory/list", method = { RequestMethod.POST })
	public AdminResultByPage list(RightCategoryVo rightCategoryVo, HttpServletRequest request) {

		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer limit = Integer.valueOf(request.getParameter("limit"));

		AdminResultByPage jsonResult = new AdminResultByPage(page, limit);

		jsonResult = rightCategoryService.findByPage(rightCategoryVo, jsonResult);

		return jsonResult;
	}

	/**
	 * @Title: toList
	 * @Description: 列表UI
	 * @author zhuzq
	 * @date 2020年04月28日 15:54:44
	 * @return
	 */
	@RequestMapping(value = "/admin/rightCategory/list/ui", method = { RequestMethod.GET })
	public String toList() {
		return "/admin/rightCategory/rightCategory_list";
	}

	/**
	 * @Title: edit
	 * @Description: 编辑
	 * @author zhuzq
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategoryId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/rightCategory/edit", method = { RequestMethod.GET, RequestMethod.POST })
	public String edit(Integer categoryId, HttpServletRequest request) {
		// 编辑,为空新增
		if (null != categoryId) {
			RightCategoryDto rightCategoryDTO = rightCategoryService.getRightCategory(categoryId);
			request.setAttribute("rightCategoryDTO", rightCategoryDTO);
		}
		return "/admin/rightCategory/rightCategory_edit";
	}
	

}
