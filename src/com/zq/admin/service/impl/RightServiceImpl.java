
package com.zq.admin.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zq.admin.dao.MenuDao;
import com.zq.admin.dao.RoleDao;
import com.zq.admin.domain.dto.RightDto;
import com.zq.admin.domain.dto.UserDto;
import com.zq.admin.domain.entity.Menu;
import com.zq.admin.domain.entity.Role;
import com.zq.admin.service.RightService;

@Service
public class RightServiceImpl implements RightService {

	private static Map<Integer, RightDto> cacheMap = new HashMap<Integer, RightDto>();

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private MenuDao menuDao;

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
	@Override
	public boolean checkRight(Integer userId, String menuName, String menuUrl) {

		if (StringUtils.isBlank(menuName) && StringUtils.isBlank(menuUrl)) {
			return false;
		}

		// 获取用户权限
		Map<Integer, RightDto> map = cacheMap;
		if (null != map && map.size() > 0 && map.containsKey(userId)) {
			RightDto rightDto = map.get(userId);
			if (null != rightDto) {
				List<Menu> menuList = rightDto.getMenuList();
				if (null != menuList && menuList.size() > 0) {
					for (Menu menu : menuList) {
						String name = menu.getMenuName();
						String url = menu.getMenuUrl();
						if (StringUtils.isNotBlank(menuName) && StringUtils.isNotBlank(menuUrl)) {
							// 有权限
							if (name.equals(menuName) && url.equals(menuUrl)) {
								return true;
							}
						}
						else if (StringUtils.isNotBlank(name)) {
							if (name.equals(menuName)) {
								return true;
							}
						}

					}
				}
			}

		}
		return false;
	}

	/**
	* @Title: addCache
	* @Description: 新增到缓存
	* @author zhuzq
	* @date  2020年5月13日 下午11:59:25
	* @param userDto
	*/
	@Override
	public void addCache(UserDto userDto) {

		if (null == userDto) {
			return;
		}

		Integer userId = userDto.getUserId();
		Integer roleId = userDto.getRoleId();
		Role role = roleDao.get(roleId);
		if (null == role) {
			return;
		}
		String menuId = role.getMenuId();
		if (StringUtils.isBlank(menuId)) {
			return;
		}

		// 根据menuId获取权限菜单
		List<String> menuIdlist = Arrays.asList(menuId.split(","));
		List<Menu> menuList = menuDao.selectByBatch(menuIdlist);

		Date now = new Date();
		RightDto right = new RightDto(now, menuList);

		if (cacheMap.size() > 5000) {
			cacheMap.clear();
		}
		cacheMap.put(userId, right);

	}

	/**
	* @Title: removeCache
	* @Description: 移除缓存
	* @author zhuzq
	* @date  2020年5月13日 下午11:59:37
	* @param userDto
	*/
	@Override
	public void removeCache(UserDto userDto) {

		if (null == userDto) {
			return;
		}

		Integer userId = userDto.getUserId();

		if (null != cacheMap && cacheMap.size() > 0 && cacheMap.containsKey(userId)) {
			cacheMap.remove(userId);
		}

	}

}
