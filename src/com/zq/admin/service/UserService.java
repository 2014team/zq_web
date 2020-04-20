
package com.zq.admin.service;

import com.zq.admin.entity.User;
import com.zq.common.entity.JsonResultByPage;
import com.zq.common.service.BaseService;

/**
 * @ClassName: UserService
 * @Description: 用户
 * @author zhuzq
 * @date 2020年4月16日 上午10:44:48
 */
public interface UserService extends BaseService<User, Integer> {

	/**
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author zhuzq
	 * @date 2020年4月16日 上午10:46:05
	 * @param user
	 * @return
	 */
	public String checkParam(User user);

	/**
	 * @Title: saveUser
	 * @Description: 用户保存
	 * @author zhuzq
	 * @date 2020年4月16日 上午10:46:13
	 * @param user
	 * @return
	 */
	public boolean saveUser(User user);

	/**
	 * @Title: updateUser
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2020年4月16日 上午10:51:03
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user);

	/**
	 * @Title: deleteUser
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2020年4月16日 上午10:53:12
	 * @param userId
	 * @return
	 */
	public boolean deleteUser(Integer userId);

	/**
	 * @Title: checkLoginParam
	 * @Description: 登录参数验证
	 * @author zhuzq
	 * @date 2020年4月16日 下午1:49:09
	 * @param user
	 * @return
	 */
	public String checkLoginParam(User user);

	/**
	 * @Title: login
	 * @Description: 登录
	 * @author zhuzq
	 * @date 2020年4月16日 下午2:07:30
	 * @param user
	 * @return
	 */
	public User login(User user);

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2020年4月16日 下午4:16:52
	 * @param entity
	 * @param jsonResult
	 * @return
	 */
	public JsonResultByPage findByPage(User entity, JsonResultByPage jsonResult);
	
	/**
	* @Title: checkUnique
	* @Description: 唯一性确认
	* @author zhuzq
	* @date  2020年4月17日 下午7:50:25
	* @param user
	* @return
	*/
	public String checkUnique(User user);

}
