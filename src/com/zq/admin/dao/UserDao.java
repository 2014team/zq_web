
package com.zq.admin.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zq.admin.entity.User;
import com.zq.common.dao.BaseDao;

/**
* @ClassName: UserDao
* @Description: 用户
* @author zhuzq
* @date 2020年4月16日 上午10:45:23
*/
@Repository
public interface UserDao extends BaseDao<User, Integer> {
	
	/**
	* @Title: getOneByMap
	* @Description: 通过Map单个查找 
	* @author zhuzq
	* @date  2020年4月16日 下午2:03:45
	* @param paramMap
	* @return
	*/
	public User getOneByMap(Map<String,Object> paramMap);

}