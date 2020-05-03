package com.zq.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zq.admin.domain.entity.Right;
import com.zq.common.dao.BaseDao;

/**
 * @ClassName: RightDao
 * @Description: 权限
 * @author zhuzq
 * @date 2020年04月29日 11:56:47
 */
@Repository
public interface RightDao extends BaseDao<Right,Integer>{
	
	
	List<Right> getByBatch(List<String> list);

}
