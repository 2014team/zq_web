package com.zq.admin.dao;

import com.zq.common.dao.BaseDao;
import org.springframework.stereotype.Repository;
import com.zq.admin.domain.entity.Role;

/**
 * @ClassName: RoleDao
 * @Description: 角色
 * @author zhuzq
 * @date 2020年04月30日 14:04:25
 */
@Repository
public interface RoleDao extends BaseDao<Role,Integer>{

}
