package com.zq.admin.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zq.admin.dao.LogDao;
import com.zq.admin.domain.entity.Log;
import com.zq.admin.service.LogService;
import com.zq.common.service.impl.BaseServiceImpl;
import com.zq.admin.domain.vo.LogVo;
import com.zq.admin.domain.dto.LogDto;
import com.zq.common.entity.AdminResultByPage;

/**
 * @ClassName: LogServiceImpl
 * @Description: 菜单
 * @author zhuzq
 * @date 2020年05月14日 22:04:08
 */
@Service
public class LogServiceImpl extends BaseServiceImpl<Log,Integer>  implements LogService {
	
	@Autowired
	private LogDao logDao;


	/**
	 * @Title: saveLog
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:08
	 * @param logVo
	 * @return
	 */
	@Override
	public boolean saveLog(LogVo logVo) {
		// LogVo转Log
		Log log = convertLog(logVo);
		Integer result = logDao.save(log);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteLog
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:08
	 * @param logId
	 * @return
	 */
	@Override
	public boolean deleteLog(Integer logId) {
		Integer result = logDao.delete(logId);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:08
	 * @param logIdArr
	 * @return
	 */
	@Override
	public int deleteByBatch(Integer[] logIdArr) {
		List<Integer> logIdList = Arrays.asList(logIdArr);
		return logDao.deleteByBatch(logIdList);
	}

	/**
	 * @Title: updateLog
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:08
	 * @param logVo
	 * @return
	 */
	@Override
	public boolean updateLog(LogVo logVo) {
		// LogVo转Log
		Log log = convertLog(logVo);
		Integer result = logDao.update(log);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: getLog
	 * @Description: 根据logId获取用户
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:08
	 * @param logId
	 * @return
	 */
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
	 * @author zhuzq
	 * * @date 2020年05月14日 22:04:08
	 * @param logVo
	 * @param jsonResult
	 * @return
	 */
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
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:08
	 * @param logVo
	 * @return
	 */
	@Override
	public String checkParam(LogVo logVo) {
		Integer logType = logVo.getLogType();
		if (null == logType) {
			return "0:正常日志1:错误日志不能为空";
		}
	    String operator = logVo.getOperator();
		if (StringUtils.isBlank(operator)) {
			return "操作人不能为空";
		}
	    String requestIp = logVo.getRequestIp();
		if (StringUtils.isBlank(requestIp)) {
			return "请求IP不能为空";
		}
	    String requestMethod = logVo.getRequestMethod();
		if (StringUtils.isBlank(requestMethod)) {
			return "请求方法不能为空";
		}
	    String requestParams = logVo.getRequestParams();
		if (StringUtils.isBlank(requestParams)) {
			return "请求参数不能为空";
		}
	    String methodDescrible = logVo.getMethodDescrible();
		if (StringUtils.isBlank(methodDescrible)) {
			return "方法描述不能为空";
		}
	    String exceptionCode = logVo.getExceptionCode();
		if (StringUtils.isBlank(exceptionCode)) {
			return "错误码不能为空";
		}
	    String exceptionDetail = logVo.getExceptionDetail();
		if (StringUtils.isBlank(exceptionDetail)) {
			return "错误详情不能为空";
		}
		return null;
	}


	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:08
	 * @param logVo
	 * @return
	 */
	@Override
	public String checkUnique(LogVo LogVo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("logType", LogVo.getLogType());
		List<Log> logList = logDao.select(paramMap);
		if (null == logList || logList.size() < 1) {
			return null;
		}

		Integer logId = LogVo.getLogId();
		if (null != logId) {
			for (Log entity : logList) {
				if (!entity.getLogId().equals(logId) && entity.getLogType().equals(LogVo.getLogType())) {
					return "0:正常日志1:错误日志已经存在";
				}
			}
		} else {
			return "0:正常日志1:错误日志已经存在";
		}
		return null;

	}

	/**
	 * @Title: convertLog
	 * @Description: LogVo转Log
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:08
	 * @param logVo
	 * @return
	 */
	private Log convertLog(LogVo logVo) {
		Log log = new Log();
		log.setLogId(logVo.getLogId());
		log.setLogType(logVo.getLogType());
		log.setOperator(logVo.getOperator());
		log.setRequestIp(logVo.getRequestIp());
		log.setRequestMethod(logVo.getRequestMethod());
		log.setRequestParams(logVo.getRequestParams());
		log.setMethodDescrible(logVo.getMethodDescrible());
		log.setExceptionCode(logVo.getExceptionCode());
		log.setExceptionDetail(logVo.getExceptionDetail());
		log.setCreateDate(logVo.getCreateDate());
		log.setUpdateDate(logVo.getUpdateDate());
		return log;
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
