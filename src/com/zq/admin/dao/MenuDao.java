package com.zq.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zq.admin.domain.entity.Menu;
import com.zq.common.dao.BaseDao;

/**
 * @ClassName: MenuDao
 * @Description: 菜单
 * @author zhuzq
 * @date 2020年05月04日 13:39:50
 */
@Repository
public interface MenuDao extends BaseDao<Menu,Integer>{

	/**
	* @Title: selectByBatch
	* @Description: 批量查找
	* @author zhuzq
	* @date  2020年5月10日 下午10:56:26
	* @param list
	* @return
	*/
	public List<Menu> selectByBatch(List<String> list);
}
