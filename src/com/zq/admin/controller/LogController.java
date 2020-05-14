package com.zq.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zq.admin.domain.dto.LogDto;
import com.zq.admin.domain.vo.LogVo;
import com.zq.admin.service.LogService;
import com.zq.common.entity.JsonResult;
import com.zq.common.entity.AdminResultByPage;

/**
 * @ClassName: LogController
 * @Description: 菜单
 * @author zhuzq
 * @date 2020年05月14日 22:04:07
 */
@Controller
public class LogController {

	@Autowired
	private LogService logService;

	/**
	 * @Title: save
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:07
	 * @param logVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/log/save", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult save(LogVo logVo) {
		JsonResult result = new JsonResult();

		// 参数验证
		String errMsg = logService.checkParam(logVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		errMsg = logService.checkUnique(logVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}
		// 保存
		boolean save = logService.saveLog(logVo);
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
	 * @date 2020年05月14日 22:04:07
	 * @param logId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/log/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult delete(Integer logId) {
		JsonResult result = new JsonResult();

		// 验证参数
		if (null == logId) {
			result.failure("日志ID不能为空");
			return result;
		}
		// 删除
		boolean delete = logService.deleteLog(logId);
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
	 * @date 2020年05月14日 22:04:07
	 * @param logIdArr
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/log/batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult deleteByIdArr(@RequestParam("logIdArr[]") Integer[] logIdArr) {
		JsonResult result = new JsonResult();
		// 验证参数
		if (null == logIdArr || logIdArr.length < 1) {
			result.failure("请选项要删除的数据");
			return result;
		}
		// 删除
		Integer delete = logService.deleteByBatch(logIdArr);
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
	 * @date 2020年05月14日 22:04:07
	 * @param logVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/log/update", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult update(LogVo logVo) {
		JsonResult result = new JsonResult();

		// 验证参数
		Integer logId = logVo.getLogId();
		if (null == logId) {
			result.failure("日志ID不能为空");
			return result;
		}
		String errMsg = logService.checkParam(logVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		errMsg = logService.checkUnique(logVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}
		// 修改
		boolean update = logService.updateLog(logVo);
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
	 * @date 2020年05月14日 22:04:07
	 * @param logVo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/log/list", method = { RequestMethod.POST })
	public AdminResultByPage list(LogVo logVo, HttpServletRequest request) {

		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer limit = Integer.valueOf(request.getParameter("limit"));

		AdminResultByPage jsonResult = new AdminResultByPage(page, limit);

		jsonResult = logService.findByPage(logVo, jsonResult);

		return jsonResult;
	}

	/**
	 * @Title: toList
	 * @Description: 列表UI
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:07
	 * @return
	 */
	@RequestMapping(value = "/admin/log/list/ui", method = { RequestMethod.GET })
	public String toList() {
		return "/admin/log/log_list";
	}

	/**
	 * @Title: edit
	 * @Description: 编辑
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:07
	 * @param logId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/log/edit", method = { RequestMethod.GET, RequestMethod.POST })
	public String edit(Integer logId, HttpServletRequest request) {
		// 编辑,为空新增
		if (null != logId) {
			LogDto logDto = logService.getLog(logId);
			request.setAttribute("logDto", logDto);
		}
		return "/admin/log/log_edit";
	}

}
