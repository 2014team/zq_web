package com.zq.admin.service;

import com.zq.common.service.BaseService;
import com.zq.admin.domain.entity.Role;
import com.zq.admin.domain.vo.RoleVo;

import java.util.List;
import java.util.Map;

import com.zq.admin.domain.dto.RightCategoryDto;
import com.zq.admin.domain.dto.RightDto;
import com.zq.admin.domain.dto.RoleDto;
import com.zq.common.entity.AdminResultByPage;

/**
 * @ClassName: RoleDao
 * @Description: 角色
 * @author zhuzq
 * @date 2020年04月30日 14:04:26
 */
public interface RoleService extends BaseService<Role,Integer>{

	/**
	 * @Title: saveRole
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleVo
	 * @return
	 */
	public boolean saveRole(RoleVo roleVo);

	/**
	 * @Title: deleteRole
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleId
	 * @return
	 */
	public boolean deleteRole(Integer roleId);

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleIdArr
	 * @return
	 */
	public int deleteByBatch(Integer[] roleIdArr);

	/**
	 * @Title: updateRole
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleVo
	 * @return
	 */
	public boolean updateRole(RoleVo roleVo);

	/**
	 * @Title: getRole
	 * @Description: 根据roleId获取对象
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleId
	 * @return
	 */
	public RoleDto getRole(Integer roleId);

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleVo
	 * @param jsonResult
	 * @return
	 */
	public AdminResultByPage findByPage(RoleVo roleVo, AdminResultByPage jsonResult);

	/**
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleVo
	 * @return
	 */
	public String checkParam(RoleVo roleVo);

	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleVo
	 * @return
	 */
	public String checkUnique(RoleVo roleVo);
	

}
