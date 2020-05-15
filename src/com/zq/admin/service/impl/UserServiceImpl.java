
package com.zq.admin.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zq.admin.annotation.AdminServiceLog;
import com.zq.admin.constant.ValidFlagEnum;
import com.zq.admin.dao.RoleDao;
import com.zq.admin.dao.UserDao;
import com.zq.admin.domain.dto.UserDto;
import com.zq.admin.domain.entity.Role;
import com.zq.admin.domain.entity.User;
import com.zq.admin.domain.vo.UserVo;
import com.zq.admin.service.UserService;
import com.zq.common.entity.AdminResultByPage;
import com.zq.common.service.impl.BaseServiceImpl;
import com.zq.common.util.LogUtil;

/**
 * @ClassName: UserServiceImpl
 * @Description: 用户
 * @author zhuzq
 * @date 2020年4月23日 下午1:42:15
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;

	/**
	 * @Title: saveUser
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:42:41
	 * @param userVo
	 * @return
	 */
	@AdminServiceLog(description="用户保存")
	@Override
	public boolean saveUser(UserVo userVo) {
		// UserVo转User
		User user = convertUser(userVo);
		Integer result = userDao.save(user);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteUser
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:43:10
	 * @param userId
	 * @return
	 */
	@AdminServiceLog(description="用户删除")
	@Override
	public boolean deleteUser(Integer userId) {
		Integer result = userDao.delete(userId);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:43:28
	 * @param userIdArr
	 * @return
	 */
	@AdminServiceLog(description="用户批量删除")
	@Override
	public int deleteByBatch(Integer[] userIdArr) {
		List<Integer> userIdList = Arrays.asList(userIdArr);
		return userDao.deleteByBatch(userIdList);
	}

	/**
	 * @Title: updateUser
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:43:37
	 * @param userVo
	 * @return
	 */
	@AdminServiceLog(description="用户修改")
	@Override
	public boolean updateUser(UserVo userVo) {
		// UserVo转User
		User user = convertUser(userVo);
		Integer result = userDao.update(user);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: getUser
	 * @Description: 根据userId获取用户
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:43:45
	 * @param userId
	 * @return
	 */
	@AdminServiceLog(description="用户根据userId获取用户")
	@Override
	public UserDto getUser(Integer userId) {
		UserDto dto = null;
		User user = userDao.get(userId);
		if (null != user) {
			dto = convertUser(user);
		}
		return dto;
	}

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:43:51
	 * @param userVo
	 * @param jsonResult
	 * @return
	 */
	@AdminServiceLog(description="用户分页查找")
	@Override
	public AdminResultByPage findByPage(UserVo userVo, AdminResultByPage jsonResult) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userVo", userVo);
		paramMap.put("page", jsonResult);

		int count = userDao.findByPageCount(paramMap);

		if (count > 0) {
			List<UserDto> dataList = null;
			List<User> userList = findByPage(paramMap);
			if (null != userList && userList.size() > 0) {
				dataList = new ArrayList<UserDto>();
				for (User user : userList) {
					// User转UserDTO
					UserDto dto = convertUser(user);
					dataList.add(dto);
				}
			}
			jsonResult.setData(dataList);
			jsonResult.setCount(count);
		}
		return jsonResult;
	}

	/**
	 * @Title: login
	 * @Description: 登录
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:44:00
	 * @param userVo
	 * @return
	 */
	@AdminServiceLog(description="用户登录")
	@Override
	public UserDto login(UserVo userVo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", userVo.getUserName());
		paramMap.put("password", userVo.getPassword());
		paramMap.put("validFlag", ValidFlagEnum.ON.ordinal());
		User user = userDao.getOneByMap(paramMap);
		UserDto dto = null;
		if (null != user) {
			dto = convertUser(user);
		}
		return dto;
	}

	/**
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:44:07
	 * @param userVo
	 * @return
	 */
	@AdminServiceLog(description="用户参数验证")
	@Override
	public String checkParam(UserVo userVo) {
		String userName = userVo.getUserName();
		if (StringUtils.isBlank(userName)) {
			return "用户名不能为空";
		}

		if (userName.length() > 25) {
			return "用户名不能超过25个字符";
		}
		String password = userVo.getPassword();
		if (StringUtils.isBlank(password)) {
			return "密码不能为空";
		}

		if (userName.length() > 25) {
			return "密码不能超过25个字符";
		}
		Integer roleId = userVo.getRoleId();
		if (null == roleId) {
			return "角色不能为空";
		}

		Integer sortId = userVo.getSortId();
		if (null == sortId) {
			return "序号不能为空";
		}
		Integer validFlag = userVo.getValidFlag();
		if (null == validFlag) {
			return "用户状态不能为空";
		}

		return null;
	}

	/**
	 * @Title: checkLoginParam
	 * @Description: 登录参数验证
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:44:21
	 * @param userVo
	 * @return
	 */
	@AdminServiceLog(description="用户登录参数验证")
	@Override
	public String checkLoginParam(UserVo userVo) {
		String userName = userVo.getUserName();
		if (StringUtils.isBlank(userName)) {
			return "用户名不能为空";
		}

		String password = userVo.getPassword();
		if (StringUtils.isBlank(password)) {
			return "密码不能为空";
		}
		return null;
	}

	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:44:47
	 * @param userVo
	 * @return
	 */
	@AdminServiceLog(description="用户唯一性验证")
	@Override
	public String checkUnique(UserVo UserVo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", UserVo.getUserName());
		List<User> userList = userDao.select(paramMap);
		if (null == userList || userList.size() < 1) {
			return null;
		}

		Integer userId = UserVo.getUserId();
		if (null != userId) {
			for (User entity : userList) {
				if (!entity.getUserId().equals(userId) && entity.getUserName().equals(UserVo.getUserName())) {
					return "用户名已经存在";
				}
			}
		} else {
			return "用户名已经存在";
		}
		return null;

	}

	/**
	 * @Title: updateValidFlag
	 * @Description: 更新状态
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:45:11
	 * @param userVo
	 * @return
	 */
	@AdminServiceLog(description="用户更新状态")
	@Override
	public boolean updateValidFlag(UserVo userVo) {
		Integer userId = userVo.getUserId();
		Integer validFlag = userVo.getValidFlag();
		User user = userDao.get(userId);
		if (null == user) {
			LogUtil.logError("没有查倒数据，更新数据失败");
			return false;
		}
		user.setValidFlag(validFlag);
		Integer result = userDao.update(user);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: convertUser
	 * @Description: UserVo转User
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:48:16
	 * @param userVo
	 * @return
	 */
	private User convertUser(UserVo userVo) {
		User user = new User();
		user.setUserId(userVo.getUserId());
		user.setUserName(userVo.getUserName());
		user.setPassword(userVo.getPassword());
		user.setRoleId(userVo.getRoleId());
		user.setSortId(userVo.getSortId());
		user.setValidFlag(userVo.getValidFlag());
		return user;
	}

	/**
	 * @Title: convertUser
	 * @Description: User转UserDto
	 * @author zhuzq
	 * @date 2020年4月22日 下午3:35:22
	 * @param user
	 * @return
	 */
	private UserDto convertUser(User user) {
		
		UserDto dto = new UserDto();
		Integer roleId = user.getRoleId();
		Role role = roleDao.get(roleId);
		if(null != role){
			String roleName = role.getRoleName();
			dto.setRoleName(roleName);
		}
		
		
		dto.setUserId(user.getUserId());
		dto.setUserName(user.getUserName());
		dto.setPassword(user.getPassword());
		dto.setRoleId(roleId);
		dto.setSortId(user.getSortId());
		dto.setValidFlag(user.getValidFlag());
		dto.setCreateDate(user.getCreateDate());
		dto.setUpdateDate(user.getUpdateDate());
		return dto;
	}

}
