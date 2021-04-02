
package com.zq.common.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.zq.common.dao.BaseDao;
import com.zq.common.entity.BaseEntity;
import com.zq.common.service.BaseService;

public abstract class BaseServiceImpl<T extends BaseEntity, PK extends Serializable> implements BaseService<T, PK> {

	// @SuppressWarnings("unchecked")
	// private Class<T> getTClass() {
	// return ((Class<T>) ((ParameterizedType)
	// getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	//
	// }
	/**
	 * spring4.0以后支持泛型注入 因为Autowired是依赖类型
	 * 4.0以前认为是同一类型，子类继承GenericService后，会认为有多个相同GenericDao实例，以至于报错
	 */
	@Autowired
	public BaseDao<T, PK> dao;

	/**
	 * @Title: save
	 * @Description: 保存
	 * @param t
	 * @return
	 */
	public int save(T t) {

		if (t == null) {
			return 0;
		}

		return this.dao.save(t);
	}

	/**
	 * @Title: saveOrUpdate
	 * @Description: 保存或更新数据
	 * @param t
	 * @return
	 */
	public int saveOrUpdate(T t) {

		if (t == null) {
			return 0;
		}

		if (t.id == null || t.id == 0) {
			return this.save(t);
		} else {
			return this.update(t);
		}
	}

	/**
	 * @Title: update
	 * @Description: 根据Map修改
	 * @param paramMap
	 * @return
	 */
	@Transactional
	public int update(Map<String, Object> paramMap) {

		if (paramMap == null || paramMap.size() == 0) {
			return 0;
		}
		return this.dao.update(paramMap);
	}

	/**
	 * @Title: update
	 * @Description: 修改
	 * @param t
	 * @return
	 */
	@Transactional
	public int update(T t) {

		if (t == null) {
			return 0;
		}
		return this.dao.update(t);
	}

	/**
	 * @Title: delete
	 * @Description: 根据主键删除
	 * @param id
	 * @return
	 */
	@Transactional
	public int delete(PK id) {

		if (id == null) {
			return 0;
		}
		return this.dao.delete(id);
	}

	/**
	 * @Title: delete
	 * @Description: 根据Map删除
	 * @param paramMap
	 * @return
	 */
	@Transactional
	public int delete(Map<String, Object> paramMap) {

		if (paramMap == null || paramMap.size() == 0) {
			return 0;
		}
		return this.dao.delete(paramMap);
	}

	/**
	 * @Title: get
	 * @Description: 根据主键获取
	 * @param id
	 * @return
	 */
	public T get(PK id) {

		if (id == null) {
			return null;
		}
		T t = this.dao.get(id);
		return t;
	}

	/**
	 * @Title: get
	 * @Description: 根据Map获取
	 * @param paramMap
	 * @return
	 */
	public T get(Map<String, Object> paramMap) {

		if (paramMap == null || paramMap.size() == 0) {
			return null;
		}
		T t = this.dao.get(paramMap);
		return t;
	}

	/**
	 * @Title: list
	 * @Description: 获取列表
	 * @return
	 */
	public List<T> list() {

		return this.dao.select();
	}

	/**
	 * @Title: select
	 * @Description: 根据Map获取列表
	 * @param paramMap
	 * @return
	 */
	public List<T> select(Map<String, Object> paramMap) {

		return this.dao.select(paramMap);
	}

	/**
	 * @Title: select
	 * @Description: 根据实体类获取列表
	 * @param t
	 * @return
	 */
	public List<T> select(T t) {

		return this.dao.select(t);
	}

	/**
	 * @Title: count
	 * @Description: 查询整表总记录数
	 * @return
	 */
	public int count() {

		return this.dao.count();
	}

	/**
	 * @Title: count
	 * @Description: 根据Map获取记录总数
	 * @param paramMap
	 * @return
	 */
	public int count(Map<String, Object> paramMap) {

		if (paramMap == null || paramMap.size() == 0) {
			return 0;
		}
		return this.dao.count(paramMap);
	}

	/**
	 * @Title: findByPage
	 * @Description: 分类查找
	 * @param paramMap
	 * @return
	 */
	public List<T> findByPage(Map<String, Object> paramMap) {

		return this.dao.findByPage(paramMap);
	}

	/**
	 * @Title: findByPageCount
	 * @Description: 分页查找总数
	 * @param paramMap
	 * @return
	 */
	public int findByPageCount(Map<String, Object> paramMap) {

		if (paramMap == null || paramMap.size() == 0) {
			return 0;
		}
		return this.dao.findByPageCount(paramMap);
	}

	/**
	 * @Title: getOneByMap
	 * @Description: 根据Map获取单个对象
	 * @param paramMap
	 * @return
	 */
	public T getOneByMap(Map<String, Object> paramMap) {
		return this.dao.getOneByMap(paramMap);
	}

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @param list
	 * @return
	 */
	public int deleteByBatch(List<Integer> list) {
		if (list == null || list.size() < 1) {
			return 0;
		}
		return this.dao.deleteByBatch(list);
	}
}
