package com.zq.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zq.admin.domain.entity.RightCategory;
import com.zq.common.dao.BaseDao;

/**
 * @ClassName: RightCategoryDao
 * @Description: 权限分类
 * @author zhuzq
 * @date 2020年04月28日 15:54:44
 */
@Repository
public interface RightCategoryDao extends BaseDao<RightCategory,Integer>{
	
	/**
	* @Title: getByBatch
	* @Description: 批量查找
	* @author zhuzq
	* @date  2020年5月2日 下午5:23:34
	* @param list
	* @return
	*/
	List<RightCategory> getByBatch(List<String> list);

}
