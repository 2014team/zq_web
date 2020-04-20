
package com.zq.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zq.admin.dao.UserDao;
import com.zq.admin.entity.User;
import com.zq.admin.service.UserService;
import com.zq.common.entity.JsonResultByPage;
import com.zq.common.service.impl.BaseServiceImpl;

/**
 * @ClassName: UserServiceImpl
 * @Description:用户
 * @author zhuzq
 * @date 2020年4月16日 上午10:45:36
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author zhuzq
	 * @date 2020年4月16日 上午10:46:05
	 * @param user
	 * @return
	 */
	@Override
	public String checkParam(User user) {
		String userName = user.getUserName();
		if (StringUtils.isBlank(userName)) {
			return "用户名不能为空";
		}

		if (userName.length() > 25) {
			return "用户名不能超过25个字符";
		}
		String password = user.getPassword();
		if (StringUtils.isBlank(password)) {
			return "密码不能为空";
		}

		if (userName.length() > 25) {
			return "密码不能超过25个字符";
		}
		Integer roleId = user.getRoleId();
		if (null == roleId) {
			return "角色不能为空";
		}

		Integer sortId = user.getSortId();
		if (null == sortId) {
			return "序号不能为空";
		}
		Integer validFlag = user.getValidFlag();
		if (null == validFlag) {
			return "用户状态不能为空";
		}

		return null;
	}

	/**
	 * @Title: saveUser
	 * @Description: 用户保存
	 * @author zhuzq
	 * @date 2020年4月16日 上午10:46:13
	 * @param user
	 * @return
	 */
	@Override
	public boolean saveUser(User user) {
		Integer result = userDao.save(user);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: updateUser
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2020年4月16日 上午10:51:03
	 * @param user
	 * @return
	 */
	@Override
	public boolean updateUser(User user) {
		Integer result = userDao.update(user);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteUser
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2020年4月16日 上午10:53:12
	 * @param userId
	 * @return
	 */
	@Override
	public boolean deleteUser(Integer userId) {
		Integer result = userDao.delete(userId);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: checkLoginParam
	 * @Description: 登录参数验证
	 * @author zhuzq
	 * @date 2020年4月16日 下午1:49:09
	 * @param user
	 * @return
	 */
	@Override
	public String checkLoginParam(User user) {
		String userName = user.getUserName();
		if (StringUtils.isBlank(userName)) {
			return "用户名不能为空";
		}

		String password = user.getPassword();
		if (StringUtils.isBlank(password)) {
			return "密码不能为空";
		}
		return null;
	}

	/**
	 * @Title: login
	 * @Description: 登录
	 * @author zhuzq
	 * @date 2020年4月16日 下午2:07:30
	 * @param user
	 * @return
	 */
	@Override
	public User login(User user) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", user.getUserName());
		paramMap.put("password", user.getPassword());
		User entity = userDao.getOneByMap(paramMap);
		return entity;
	}

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2020年4月16日 下午4:16:52
	 * @param entity
	 * @param jsonResult
	 * @return
	 */
	@Override
	public JsonResultByPage findByPage(User entity, JsonResultByPage jsonResult) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("entity", entity);
		paramMap.put("page", jsonResult);

		int count = userDao.findByPageCount(paramMap);

		if (count > 0) {
			List<User> dataList = findByPage(paramMap);
			jsonResult.setData(dataList);
			jsonResult.setCount(count);
		}
		return jsonResult;
	}

	

	/**
	* @Title: checkUnique
	* @Description: 唯一性确认
	* @author zhuzq
	* @date  2020年4月17日 下午7:50:25
	* @param user
	* @return
	*/
	@Override
	public String checkUnique(User user) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", user.getUserName());
		List<User> userList = userDao.select(paramMap);
		if(null == userList || userList.size() < 1){
			return null;
		}
		
		Integer userId = user.getUserId();
		if(null != userId ){
				for (User u : userList) {
					if(!u.getUserId().equals(userId) && u.getUserName().equals(user.getUserName())){
						return "用户名已经存在";
					}
				}
		}else{
			return "用户名已经存在";
		}
		return null;
		
	}
}
