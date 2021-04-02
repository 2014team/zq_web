package com.zq.admin.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zq.admin.annotation.AdminServiceLog;
import com.zq.admin.dao.LogDao;
import com.zq.admin.domain.dto.LogDto;
import com.zq.admin.domain.entity.Log;
import com.zq.admin.domain.vo.LogVo;
import com.zq.admin.service.LogService;
import com.zq.common.entity.AdminResultByPage;
import com.zq.common.service.impl.BaseServiceImpl;

/**
 * @ClassName: LogServiceImpl
 * @Description: 菜单
 * @author zhuzq
 * @date 2020年05月14日 22:04:08
 */
@Service
public class LogServiceImpl extends BaseServiceImpl<Log, Integer> implements LogService {

	@Autowired
	private LogDao logDao;

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:08
	 * @param logIdArr
	 * @return
	 */
	@AdminServiceLog(description="菜单批量删除")
	@Override
	public int deleteByBatch(Integer[] logIdArr) {
		List<Integer> logIdList = Arrays.asList(logIdArr);
		return logDao.deleteByBatch(logIdList);
	}

	/**
	 * @Title: getLog
	 * @Description: 根据logId获取用户
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:08
	 * @param logId
	 * @return
	 */
	@AdminServiceLog(description="菜单根据logId获取用户")
	@Override
	public LogDto getLog(Integer logId) {
		LogDto logDTO = null;
		Log log = logDao.get(logId);
		if (null != log) {
			logDTO = convertLogDto(log);
		}
		return logDTO;
	}

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq * @date 2020年05月14日 22:04:08
	 * @param logVo
	 * @param jsonResult
	 * @return
	 */
	@AdminServiceLog(description="菜单分页查找")
	@Override
	public AdminResultByPage findByPage(LogVo logVo, AdminResultByPage jsonResult) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("logVo", logVo);
		paramMap.put("page", jsonResult);

		int count = logDao.findByPageCount(paramMap);

		if (count > 0) {
			List<LogDto> dataList = null;
			List<Log> logList = findByPage(paramMap);
			if (null != logList && logList.size() > 0) {
				dataList = new ArrayList<LogDto>();
				for (Log log : logList) {
					// Log转LogDTO
					LogDto logDTO = convertLogDto(log);
					dataList.add(logDTO);
				}
			}
			jsonResult.setData(dataList);
			jsonResult.setCount(count);
		}
		return jsonResult;
	}

	/**
	 * @Title: convertLogDto
	 * @Description: Log转LogDto
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:08
	 * @param log
	 * @return
	 */
	private LogDto convertLogDto(Log log) {
		LogDto dto = new LogDto();
		dto.setLogId(log.getLogId());
		dto.setLogType(log.getLogType());
		dto.setOperator(log.getOperator());
		dto.setRequestIp(log.getRequestIp());
		dto.setRequestMethod(log.getRequestMethod());
		dto.setRequestParams(log.getRequestParams());
		dto.setMethodDescrible(log.getMethodDescrible());
		dto.setExceptionCode(log.getExceptionCode());
		dto.setExceptionDetail(log.getExceptionDetail());
		dto.setCreateDate(log.getCreateDate());
		dto.setUpdateDate(log.getUpdateDate());
		return dto;
	}

}
