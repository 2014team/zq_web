package com.zq.admin.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zq.admin.dao.RoleDao;
import com.zq.admin.domain.dto.RoleDto;
import com.zq.admin.domain.entity.Role;
import com.zq.admin.domain.vo.RoleVo;
import com.zq.admin.service.RoleService;
import com.zq.common.entity.AdminResultByPage;
import com.zq.common.service.impl.BaseServiceImpl;
import com.zq.common.util.LogUtil;
import com.zq.common.util.StringUtil;

/**
 * @ClassName: RoleServiceImpl
 * @Description: 角色
 * @author zhuzq
 * @date 2020年04月30日 14:04:26
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer> implements RoleService {

	@Autowired
	private RoleDao roleDao;
	

	/**
	 * @Title: saveRole
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleVo
	 * @return
	 */
	@Override
	public boolean saveRole(RoleVo roleVo) {
		// RoleVo转Role
		Role role = convertRole(roleVo);
		Integer result = roleDao.save(role);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteRole
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleId
	 * @return
	 */
	@Override
	public boolean deleteRole(Integer roleId) {
		Integer result = roleDao.delete(roleId);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleIdArr
	 * @return
	 */
	@Override
	public int deleteByBatch(Integer[] roleIdArr) {
		List<Integer> roleIdList = Arrays.asList(roleIdArr);
		return roleDao.deleteByBatch(roleIdList);
	}

	/**
	 * @Title: updateRole
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleVo
	 * @return
	 */
	@Override
	public boolean updateRole(RoleVo roleVo) {
		// RoleVo转Role
		Role role = convertRole(roleVo);
		Integer result = roleDao.update(role);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: getRole
	 * @Description: 根据roleId获取用户
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleId
	 * @return
	 */
	@Override
	public RoleDto getRole(Integer roleId) {
		RoleDto roleDTO = null;
		Role role = roleDao.get(roleId);
		if (null != role) {
			roleDTO = convertRoleDto(role);
		}
		return roleDTO;
	}

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq * @date 2020年04月30日 14:04:26
	 * @param roleVo
	 * @param jsonResult
	 * @return
	 */
	@Override
	public AdminResultByPage findByPage(RoleVo roleVo, AdminResultByPage jsonResult) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleVo", roleVo);
		paramMap.put("page", jsonResult);

		int count = roleDao.findByPageCount(paramMap);

		if (count > 0) {
			List<RoleDto> dataList = null;
			List<Role> roleList = findByPage(paramMap);
			if (null != roleList && roleList.size() > 0) {
				dataList = new ArrayList<RoleDto>();
				for (Role role : roleList) {
					// Role转RoleDTO
					RoleDto roleDTO = convertRoleDto(role);
					dataList.add(roleDTO);
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
	 * @date 2020年04月30日 14:04:26
	 * @param roleVo
	 * @return
	 */
	@Override
	public String checkParam(RoleVo roleVo) {
		String roleName = roleVo.getRoleName();
		if (StringUtils.isBlank(roleName)) {
			return "角色名称不能为空";
		}
		String description = roleVo.getDescription();
		if (StringUtils.isBlank(description)) {
			return "描述不能为空";
		}
		String categoryId = roleVo.getCategoryId();
		if (StringUtils.isBlank(categoryId)) {
			//return "权限类别ID不能为空";
			roleVo.setCategoryId("");
		}
		String rightId = roleVo.getRightId();
		if (StringUtils.isBlank(rightId)) {
			//return "权限ID不能为空";
			roleVo.setRightId("");
		}
		Integer sortId = roleVo.getSortId();
		if (null == sortId) {
			return "排序不能为空";
		}
		return null;
	}

	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleVo
	 * @return
	 */
	@Override
	public String checkUnique(RoleVo RoleVo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleName", RoleVo.getRoleName());
		List<Role> roleList = roleDao.select(paramMap);
		if (null == roleList || roleList.size() < 1) {
			return null;
		}

		Integer roleId = RoleVo.getRoleId();
		if (null != roleId) {
			for (Role entity : roleList) {
				if (!entity.getRoleId().equals(roleId) && entity.getRoleName().equals(RoleVo.getRoleName())) {
					return "角色名称已经存在";
				}
			}
		} else {
			return "角色名称已经存在";
		}
		return null;

	}

	/**
	 * @Title: convertRole
	 * @Description: RoleVo转Role
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleVo
	 * @return
	 */
	private Role convertRole(RoleVo roleVo) {
		
		Role role = new Role();
		role.setRoleId(roleVo.getRoleId());
		role.setRoleName(roleVo.getRoleName());
		role.setDescription(roleVo.getDescription());
		role.setCategoryId(roleVo.getCategoryId());
		role.setRightId(roleVo.getRightId());
		role.setValidFlag(roleVo.getValidFlag());
		role.setSortId(roleVo.getSortId());
		role.setCreateDate(roleVo.getCreateDate());
		role.setUpdateDate(roleVo.getUpdateDate());
		return role;
	}

	/**
	 * @Title: convertRoleDto
	 * @Description: Role转RoleDto
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param role
	 * @return
	 */
	private RoleDto convertRoleDto(Role role) {
		RoleDto dto = new RoleDto();
		dto.setRoleId(role.getRoleId());
		dto.setRoleName(role.getRoleName());
		dto.setDescription(role.getDescription());
		dto.setCategoryId(role.getCategoryId());
		dto.setRightId(role.getRightId());
		dto.setValidFlag(role.getValidFlag());
		dto.setSortId(role.getSortId());
		dto.setCreateDate(role.getCreateDate());
		dto.setUpdateDate(role.getUpdateDate());
		return dto;
	}

	/**
	* @Title: updateValidFlag
	* @Description: 更新状态
	* @author zhuzq
	* @date  2020年5月3日 上午11:35:21
	* @param roleVo
	* @return
	*/
	@Override
	public boolean updateValidFlag(RoleVo roleVo) {
		Integer roleId = roleVo.getRoleId();
		Integer validFlag = roleVo.getValidFlag();
		Role role = roleDao.get(roleId);
		if (null == role) {
			LogUtil.logError("没有查倒数据，更新数据失败");
			return false;
		}
		role.setValidFlag(validFlag);
		Integer result = roleDao.update(role);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	* @Title: selectList
	* @Description: 获取列表
	* @author zhuzq
	* @date  2020年5月3日 下午2:32:59
	* @return
	*/
	@Override
	public List<RoleDto> selectList() {
		Map<String, Object> paramMap = null;
		List<RoleDto> roleDtoList = null;
		List<Role> roleList = roleDao.select(paramMap);
		if(null != roleList && roleList.size() > 0){
			roleDtoList = new ArrayList<RoleDto>();
			for (Role role : roleList) {
				RoleDto roleDto = convertRoleDto(role);
				roleDtoList.add(roleDto);
			}
		}
		return roleDtoList;
	}

	@Override
	public boolean saveRight(Integer roleId,Integer[] menuIdArr) {
		Role role = roleDao.get(roleId);
		if(null != role){
			role.setMenuId(StringUtil.trim(menuIdArr));
			Integer result = roleDao.update(role);
			if(null != result && result > 0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	
	
}
