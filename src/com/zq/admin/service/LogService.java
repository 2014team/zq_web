package com.zq.admin.service;

import com.zq.common.service.BaseService;
import com.zq.admin.domain.entity.Log;
import com.zq.admin.domain.vo.LogVo;
import com.zq.admin.domain.dto.LogDto;
import com.zq.common.entity.AdminResultByPage;

/**
 * @ClassName: LogDao
 * @Description: 菜单
 * @author zhuzq
 * @date 2020年05月14日 22:04:07
 */
public interface LogService extends BaseService<Log,Integer>{

	/**
	 * @Title: saveLog
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:07
	 * @param logVo
	 * @return
	 */
	public boolean saveLog(LogVo logVo);

	/**
	 * @Title: deleteLog
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:07
	 * @param logId
	 * @return
	 */
	public boolean deleteLog(Integer logId);

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:07
	 * @param logIdArr
	 * @return
	 */
	public int deleteByBatch(Integer[] logIdArr);

	/**
	 * @Title: updateLog
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:07
	 * @param logVo
	 * @return
	 */
	public boolean updateLog(LogVo logVo);

	/**
	 * @Title: getLog
	 * @Description: 根据logId获取对象
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:07
	 * @param logId
	 * @return
	 */
	public LogDto getLog(Integer logId);

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:07
	 * @param logVo
	 * @param jsonResult
	 * @return
	 */
	public AdminResultByPage findByPage(LogVo logVo, AdminResultByPage jsonResult);

	/**
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:07
	 * @param logVo
	 * @return
	 */
	public String checkParam(LogVo logVo);

	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:07
	 * @param logVo
	 * @return
	 */
	public String checkUnique(LogVo logVo);

}
