package com.zq.admin.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zq.admin.domain.dto.RightDto;
import com.zq.admin.domain.entity.RightCategory;
import com.zq.admin.domain.vo.RightVo;
import com.zq.admin.service.RightCategoryService;
import com.zq.admin.service.RightService;
import com.zq.common.entity.JsonResult;
import com.zq.common.entity.AdminResultByPage;

/**
 * @ClassName: RightController
 * @Description: 权限
 * @author zhuzq
 * @date 2020年04月29日 11:56:47
 */
@Controller
public class RightController {

	@Autowired
	private RightService rightService;
	
	@Autowired
	private RightCategoryService rightCategoryService;

	/**
	 * @Title: save
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年04月29日 11:56:47
	 * @param rightVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/right/save", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult save(RightVo rightVo) {
		JsonResult result = new JsonResult();

		// 参数验证
		String errMsg = rightService.checkParam(rightVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		errMsg = rightService.checkUnique(rightVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}
		// 保存
		boolean save = rightService.saveRight(rightVo);
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
	 * @date 2020年04月29日 11:56:47
	 * @param rightId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/right/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult delete(Integer rightId) {
		JsonResult result = new JsonResult();

		// 验证参数
		if (null == rightId) {
			result.failure("权限ID不能为空");
			return result;
		}
		// 删除
		boolean delete = rightService.deleteRight(rightId);
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
	 * @date 2020年04月29日 11:56:47
	 * @param rightIdArr
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/right/batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult deleteByIdArr(@RequestParam("rightIdArr[]") Integer[] rightIdArr) {
		JsonResult result = new JsonResult();
		// 验证参数
		if (null == rightIdArr || rightIdArr.length < 1) {
			result.failure("请选项要删除的数据");
			return result;
		}
		// 删除
		Integer delete = rightService.deleteByBatch(rightIdArr);
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
	 * @date 2020年04月29日 11:56:47
	 * @param rightVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/right/update", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult update(RightVo rightVo) {
		JsonResult result = new JsonResult();

		// 验证参数
		Integer rightId = rightVo.getRightId();
		if (null == rightId) {
			result.failure("权限ID不能为空");
			return result;
		}
		String errMsg = rightService.checkParam(rightVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		errMsg = rightService.checkUnique(rightVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}
		// 修改
		boolean update = rightService.updateRight(rightVo);
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
	 * @date 2020年04月29日 11:56:47
	 * @param rightVo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/right/list", method = { RequestMethod.POST })
	public AdminResultByPage list(RightVo rightVo, HttpServletRequest request) {

		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer limit = Integer.valueOf(request.getParameter("limit"));

		AdminResultByPage jsonResult = new AdminResultByPage(page, limit);

		jsonResult = rightService.findByPage(rightVo, jsonResult);

		return jsonResult;
	}

	/**
	 * @Title: toList
	 * @Description: 列表UI
	 * @author zhuzq
	 * @date 2020年04月29日 11:56:47
	 * @return
	 */
	@RequestMapping(value = "/admin/right/list/ui", method = { RequestMethod.GET })
	public String toList(HttpServletRequest request) {
		//权限分类列表
		List<RightCategory> rightCategoryList = rightCategoryService.select(new HashMap<String, Object>());
		request.setAttribute("rightCategoryList", rightCategoryList);
		
		return "/admin/right/right_list";
	}

	/**
	 * @Title: edit
	 * @Description: 编辑
	 * @author zhuzq
	 * @date 2020年04月29日 11:56:47
	 * @param rightId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/right/edit", method = { RequestMethod.GET, RequestMethod.POST })
	public String edit(Integer rightId, HttpServletRequest request) {
		// 编辑,为空新增
		if (null != rightId) {
			RightDto rightDTO = rightService.getRight(rightId);
			request.setAttribute("rightDTO", rightDTO);
		}
		
		//权限分类列表
		List<RightCategory> rightCategoryList = rightCategoryService.select(new HashMap<String, Object>());
		request.setAttribute("rightCategoryList", rightCategoryList);
		
		return "/admin/right/right_edit";
	}
	
	/**
	* @Title: get
	* @Description: 查找
	* @author zhuzq
	* @date  2020年4月29日 下午6:20:21
	* @param rightId
	* @return
	*/
	@ResponseBody
	@RequestMapping(value = "/admin/right/get", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult get(Integer rightId) {
		
		JsonResult result = new JsonResult();
		// 验证参数
		if (null == rightId || rightId< 1) {
			result.failure("权限ID不能为空");
			return result;
		}
		RightDto rightDTO = rightService.getRight(rightId);
		result.success("rightDTO", rightDTO);
		return result;
	}
	
}
