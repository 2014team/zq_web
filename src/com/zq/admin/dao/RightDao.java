package com.zq.admin.dao;

import com.zq.common.dao.BaseDao;
import org.springframework.stereotype.Repository;
import com.zq.admin.domain.entity.Right;

/**
 * @ClassName: RightDao
 * @Description: 权限
 * @author zhuzq
 * @date 2020年04月29日 11:56:47
 */
@Repository
public interface RightDao extends BaseDao<Right,Integer>{

}
