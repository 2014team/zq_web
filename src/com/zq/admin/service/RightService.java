
package com.zq.admin.service;

import com.zq.admin.domain.dto.UserDto;

public interface RightService {

	/**
	* @Title: checkRight
	* @Description: 权限验证
	* @author zhuzq
	* @date  2020年5月13日 下午11:59:14
	* @param userId
	* @param menuName
	* @param menuUrl
	* @return true:有权限 ：false：没有权限
	*/
	public boolean checkRight(Integer userId,String menuName, String menuUrl);

	/**
	* @Title: addCache
	* @Description: 新增到缓存
	* @author zhuzq
	* @date  2020年5月13日 下午11:59:25
	* @param userDto
	*/
	public void addCache(UserDto userDto);
	
	/**
	* @Title: removeCache
	* @Description: 移除缓存
	* @author zhuzq
	* @date  2020年5月13日 下午11:59:37
	* @param userDto
	*/
	public void removeCache(UserDto userDto);

}
