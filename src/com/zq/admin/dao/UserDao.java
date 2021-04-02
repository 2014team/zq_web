
package com.zq.admin.dao;

import org.springframework.stereotype.Repository;

import com.zq.admin.domain.entity.User;
import com.zq.common.dao.BaseDao;

/**
 * @ClassName: UserDao
 * @Description: 用户
 * @author zhuzq
 * @date 2020年4月23日 下午1:42:24
 */
@Repository
public interface UserDao extends BaseDao<User, Integer> {


}