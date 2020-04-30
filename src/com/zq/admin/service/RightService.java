package com.zq.admin.service;

import com.zq.common.service.BaseService;
import com.zq.admin.domain.entity.Right;
import com.zq.admin.domain.vo.RightVo;
import com.zq.admin.domain.dto.RightDto;
import com.zq.common.entity.AdminResultByPage;

/**
 * @ClassName: RightDao
 * @Description: 权限
 * @author zhuzq
 * @date 2020年04月29日 11:56:47
 */
public interface RightService extends BaseService<Right,Integer>{

	/**
	 * @Title: saveRight
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年04月29日 11:56:47
	 * @param rightVo
	 * @return
	 */
	public boolean saveRight(RightVo rightVo);

	/**
	 * @Title: deleteRight
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2020年04月29日 11:56:47
	 * @param rightId
	 * @return
	 */
	public boolean deleteRight(Integer rightId);

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2020年04月29日 11:56:47
	 * @param rightIdArr
	 * @return
	 */
	public int deleteByBatch(Integer[] rightIdArr);

	/**
	 * @Title: updateRight
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2020年04月29日 11:56:47
	 * @param rightVo
	 * @return
	 */
	public boolean updateRight(RightVo rightVo);

	/**
	 * @Title: getRight
	 * @Description: 根据rightId获取对象
	 * @author zhuzq
	 * @date 2020年04月29日 11:56:47
	 * @param rightId
	 * @return
	 */
	public RightDto getRight(Integer rightId);

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2020年04月29日 11:56:47
	 * @param rightVo
	 * @param jsonResult
	 * @return
	 */
	public AdminResultByPage findByPage(RightVo rightVo, AdminResultByPage jsonResult);

	/**
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author zhuzq
	 * @date 2020年04月29日 11:56:47
	 * @param rightVo
	 * @return
	 */
	public String checkParam(RightVo rightVo);

	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2020年04月29日 11:56:47
	 * @param rightVo
	 * @return
	 */
	public String checkUnique(RightVo rightVo);

}
