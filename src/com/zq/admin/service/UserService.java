
package com.zq.admin.service;

import com.zq.admin.domain.dto.UserDto;
import com.zq.admin.domain.entity.User;
import com.zq.admin.domain.vo.UserVo;
import com.zq.common.entity.JsonResultByPage;
import com.zq.common.service.BaseService;

/**
 * @ClassName: UserService
 * @Description: 用户
 * @author zhuzq
 * @date 2020年4月23日 下午1:41:50
 */
public interface UserService extends BaseService<User, Integer> {

	/**
	 * @Title: saveUser
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:42:41
	 * @param userVo
	 * @return
	 */
	public boolean saveUser(UserVo userVo);

	/**
	 * @Title: deleteUser
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:43:10
	 * @param userId
	 * @return
	 */
	public boolean deleteUser(Integer userId);

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:43:28
	 * @param userIdArr
	 * @return
	 */
	public int deleteByBatch(Integer[] userIdArr);

	/**
	 * @Title: updateUser
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:43:37
	 * @param userVo
	 * @return
	 */
	public boolean updateUser(UserVo userVo);

	/**
	 * @Title: getUser
	 * @Description: 根据userId获取用户
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:43:45
	 * @param userId
	 * @return
	 */
	public UserDto getUser(Integer userId);

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:43:51
	 * @param userVo
	 * @param jsonResult
	 * @return
	 */
	public JsonResultByPage findByPage(UserVo userVo, JsonResultByPage jsonResult);

	/**
	 * @Title: login
	 * @Description: 登录
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:44:00
	 * @param userVo
	 * @return
	 */
	public UserDto login(UserVo userVo);

	/**
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:44:07
	 * @param userVo
	 * @return
	 */
	public String checkParam(UserVo userVo);

	/**
	 * @Title: checkLoginParam
	 * @Description: 登录参数验证
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:44:21
	 * @param userVo
	 * @return
	 */
	public String checkLoginParam(UserVo userVo);

	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:44:47
	 * @param userVo
	 * @return
	 */
	public String checkUnique(UserVo userVo);

	/**
	 * @Title: updateValidFlag
	 * @Description: 更新状态
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:45:11
	 * @param userVo
	 * @return
	 */
	public boolean updateValidFlag(UserVo userVo);

}
