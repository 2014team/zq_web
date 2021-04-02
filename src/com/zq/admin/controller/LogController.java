package com.zq.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zq.admin.annotation.AdminControllerLog;
import com.zq.admin.domain.dto.LogDto;
import com.zq.admin.domain.vo.LogVo;
import com.zq.admin.service.LogService;
import com.zq.common.entity.AdminResultByPage;
import com.zq.common.entity.JsonResult;

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
	 * @Title: batchDelete
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:07
	 * @param logIdArr
	 * @return
	 */
	@AdminControllerLog(description="日志批量删除")
	@ResponseBody
	@RequestMapping(value = "/admin/center/log/batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
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
	 * @Title: list
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:07
	 * @param logVo
	 * @param request
	 * @return
	 */
	@AdminControllerLog(description="日志分页查找")
	@ResponseBody
	@RequestMapping(value = "/admin/center/log/list", method = { RequestMethod.POST })
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
	@RequestMapping(value = "/admin/center/log/list/ui", method = { RequestMethod.GET })
	public String toList() {
		return "/admin/center/log/log_list";
	}

	
	/**
	* @Title: get
	* @Description: 获取详情
	* @author zhuzq
	* @date  2020年5月15日 上午10:02:30
	* @param logId
	* @param request
	* @return
	*/
	@AdminControllerLog(description="日志获取详情")
	@RequestMapping(value = "/admin/center/log/detail", method = { RequestMethod.GET, RequestMethod.POST })
	public String detail(Integer logId, HttpServletRequest request) {
		LogDto logDto = null;
		if (null != logId) {
			 logDto = logService.getLog(logId);
		}
		request.setAttribute("logDto", logDto);
		return "/admin/center/log/log_edit";
	}

}
