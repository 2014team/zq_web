
package com.zq.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zq.common.entity.BaseEntity;

public abstract interface BaseService<T extends BaseEntity, PK extends Serializable> {

	/**
	 * @Title: save
	 * @Description: 保存
	 * @param t
	 * @return
	 */
	public int save(T t);

	/**
	 * @Title: saveOrUpdate
	 * @Description: 保存或更新数据
	 * @param t
	 * @return
	 */
	public int saveOrUpdate(T t);

	/**
	 * @Title: update
	 * @Description: 根据Map修改
	 * @param paramMap
	 * @return
	 */
	public int update(Map<String, Object> paramMap);

	/**
	 * @Title: update
	 * @Description: 修改
	 * @param t
	 * @return
	 */
	public int update(T t);

	/**
	 * @Title: delete
	 * @Description: 根据主键删除
	 * @param id
	 * @return
	 */
	public int delete(PK id);

	/**
	 * @Title: delete
	 * @Description: 根据Map删除
	 * @param paramMap
	 * @return
	 */
	public int delete(Map<String, Object> paramMap);

	/**
	 * @Title: get
	 * @Description: 根据主键获取
	 * @param id
	 * @return
	 */
	public T get(PK id);

	/**
	 * @Title: get
	 * @Description: 根据Map获取
	 * @param paramMap
	 * @return
	 */
	public T get(Map<String, Object> paramMap);

	/**
	 * @Title: list
	 * @Description: 获取列表
	 * @return
	 */
	public List<T> list();

	/**
	 * @Title: select
	 * @Description: 根据Map获取列表
	 * @param paramMap
	 * @return
	 */
	public List<T> select(Map<String, Object> paramMap);

	/**
	 * @Title: select
	 * @Description: 根据实体类获取列表
	 * @param t
	 * @return
	 */
	public List<T> select(T t);

	/**
	 * @Title: count
	 * @Description: 查询整表总记录数
	 * @return
	 */
	public int count();

	/**
	 * @Title: count
	 * @Description: 根据Map获取记录总数
	 * @param paramMap
	 * @return
	 */
	public int count(Map<String, Object> paramMap);

	/**
	 * @Title: findByPage
	 * @Description: 分类查找
	 * @param paramMap
	 * @return
	 */
	public List<T> findByPage(Map<String, Object> paramMap);

	/**
	 * @Title: findByPageCount
	 * @Description: 分页查找总数
	 * @param paramMap
	 * @return
	 */
	public int findByPageCount(Map<String, Object> paramMap);

	/**
	 * @Title: getOneByMap
	 * @Description: 根据Map获取单个对象
	 * @param paramMap
	 * @return
	 */
	public abstract T getOneByMap(Map<String, Object> paramMap);

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @param list
	 * @return
	 */
	public abstract int deleteByBatch(List<Integer> list);
}