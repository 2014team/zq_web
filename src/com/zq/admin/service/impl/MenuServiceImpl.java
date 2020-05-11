
package com.zq.admin.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zq.admin.dao.MenuDao;
import com.zq.admin.dao.RoleDao;
import com.zq.admin.domain.dto.MenuDto;
import com.zq.admin.domain.dto.MenuTreeDto;
import com.zq.admin.domain.entity.Menu;
import com.zq.admin.domain.entity.Role;
import com.zq.admin.domain.vo.MenuVo;
import com.zq.admin.service.MenuService;
import com.zq.common.entity.AdminResultByPage;
import com.zq.common.service.impl.BaseServiceImpl;

/**
 * @ClassName: MenuServiceImpl
 * @Description: 菜单
 * @author zhuzq
 * @date 2020年05月04日 13:39:51
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu, Integer> implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Autowired
	private RoleDao roleDao;

	/**
	 * @Title: saveMenu
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuVo
	 * @return
	 */
	@Override
	public boolean saveMenu(MenuVo menuVo) {

		// MenuVo转Menu
		Menu menu = convertMenu(menuVo);
		Integer result = menuDao.save(menu);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteMenu
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuId
	 * @return
	 */
	@Override
	public boolean deleteMenu(Integer menuId) {

		Integer result = menuDao.delete(menuId);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuIdArr
	 * @return
	 */
	@Override
	public int deleteByBatch(Integer[] menuIdArr) {

		List<Integer> menuIdList = Arrays.asList(menuIdArr);
		return menuDao.deleteByBatch(menuIdList);
	}

	/**
	 * @Title: updateMenu
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuVo
	 * @return
	 */
	@Override
	public boolean updateMenu(MenuVo menuVo) {

		// MenuVo转Menu
		Menu menu = convertMenu(menuVo);
		Integer result = menuDao.update(menu);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: getMenu
	 * @Description: 根据menuId获取用户
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuId
	 * @return
	 */
	@Override
	public MenuDto getMenu(Integer menuId) {

		MenuDto menuDTO = null;
		Menu menu = menuDao.get(menuId);
		if (null != menu) {
			menuDTO = menuDtoDeal(menu);
		}
		return menuDTO;
	}

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq * @date 2020年05月04日 13:39:51
	 * @param menuVo
	 * @param jsonResult
	 * @return
	 */
	@Override
	public AdminResultByPage findByPage(MenuVo menuVo, AdminResultByPage jsonResult) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		String menuName = menuVo.getMenuName();
		if (StringUtils.isBlank(menuName)) {
			// 默认选择父类
			menuVo.setParentId("0");
		}

		paramMap.put("menuVo", menuVo);
		paramMap.put("page", jsonResult);

		int count = menuDao.findByPageCount(paramMap);

		if (count > 0) {
			List<MenuDto> dataList = null;
			List<Menu> menuList = findByPage(paramMap);
			if (null != menuList && menuList.size() > 0) {
				dataList = new ArrayList<MenuDto>();
				for (Menu menu : menuList) {
					// Menu转MenuDTO
					MenuDto menuDTO = menuDtoDeal(menu);
					dataList.add(menuDTO);
				}
			}
			jsonResult.setData(dataList);
			jsonResult.setCount(count);
		}
		return jsonResult;
	}

	/**
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuVo
	 * @return
	 */
	@Override
	public String checkParam(MenuVo menuVo) {

		String menuName = menuVo.getMenuName();
		if (StringUtils.isBlank(menuName)) {
			return "菜单名称不能为空";
		}
		String menuUrl = menuVo.getMenuUrl();
		if (StringUtils.isBlank(menuUrl)) {
			menuVo.setMenuUrl("");
		}
		Integer validFlag = menuVo.getValidFlag();
		if (null == validFlag) {
			return "有效标识 0:启用1：停用不能为空";
		}
		Integer sortId = menuVo.getSortId();
		if (null == sortId) {
			return "排序不能为空";
		}
		Integer menuType = menuVo.getMenuType();
		if (null == menuType) {
			return "0:菜单1：按钮不能为空";
		}
		String parentId = menuVo.getParentId();
		if (StringUtils.isBlank(parentId)) {
			menuVo.setParentId("0");
		}
		return null;
	}

	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuVo
	 * @return
	 */
	@Override
	public String checkUnique(MenuVo MenuVo) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuName", MenuVo.getMenuName());
		List<Menu> menuList = menuDao.select(paramMap);
		if (null == menuList || menuList.size() < 1) {
			return null;
		}

		Integer menuId = MenuVo.getMenuId();
		if (null != menuId) {
			for (Menu entity : menuList) {
				if (!entity.getMenuId().equals(menuId) && entity.getMenuName().equals(MenuVo.getMenuName())) {
					return "菜单名称已经存在";
				}
			}
		}
		else {
			return "菜单名称已经存在";
		}
		return null;

	}

	/**
	 * @Title: convertMenu
	 * @Description: MenuVo转Menu
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuVo
	 * @return
	 */
	private Menu convertMenu(MenuVo menuVo) {

		Menu menu = new Menu();

		menu.setMenuId(menuVo.getMenuId());
		menu.setMenuName(menuVo.getMenuName());
		menu.setMenuUrl(menuVo.getMenuUrl());
		menu.setValidFlag(menuVo.getValidFlag());
		menu.setSortId(menuVo.getSortId());
		menu.setMenuType(menuVo.getMenuType());
		menu.setCreateDate(menuVo.getCreateDate());
		menu.setUpdateDate(menuVo.getUpdateDate());
		menu.setParentId(menuVo.getParentId());
		return menu;
	}

	/**
	 * @Title: convertMenuDto
	 * @Description: Menu转MenuDto
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menu
	 * @return
	 */
	private MenuDto convertMenuDto(Menu menu) {

		MenuDto dto = new MenuDto();
		dto.setMenuId(menu.getMenuId());
		dto.setMenuName(menu.getMenuName());
		dto.setMenuUrl(menu.getMenuUrl());
		dto.setValidFlag(menu.getValidFlag());
		dto.setSortId(menu.getSortId());
		dto.setMenuType(menu.getMenuType());
		dto.setCreateDate(menu.getCreateDate());
		dto.setUpdateDate(menu.getUpdateDate());
		dto.setParentId(menu.getParentId());
		return dto;
	}

	private MenuTreeDto convertTreeMenuDto(Menu menu) {

		MenuTreeDto tree = new MenuTreeDto();
		tree.setId(menu.getMenuId());
		tree.setField("menuId");
		tree.setTitle(menu.getMenuName());
		tree.setChecked(false);
		tree.setSpread(false);
		return tree;
	}

	private MenuDto menuDtoDeal(Menu menu) {

		MenuDto result = convertMenuDto(menu);
		Integer menuId = menu.getMenuId();
		if (null != menuId) {
			// 二级菜单
			List<MenuDto> childList = null;
			List<Menu> menuList = menuChildList(menuId);
			if (null != menuList && menuList.size() > 0) {
				childList = new ArrayList<MenuDto>();
				for (Menu m : menuList) {
					MenuDto childDto = convertMenuDto(m);

					// 三级菜单
					List<MenuDto> thirdList = null;
					List<Menu> thirdMenuList = menuChildList(childDto.getMenuId());
					if (null != thirdMenuList && thirdMenuList.size() > 0) {
						thirdList = new ArrayList<MenuDto>();
						for (Menu menu2 : thirdMenuList) {
							MenuDto thirdDto = convertMenuDto(menu2);
							thirdList.add(0,thirdDto);
						}
						childDto.setChildList(thirdList);
					}

					childList.add(0,childDto);
				}
				result.setChildList(childList);
			}

		}

		return result;
	}

	private List<Menu> menuChildList(Integer menuId) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentId", menuId);
		List<Menu> menuList = menuDao.select(paramMap);
		return menuList;
	}

	/**
	 * @Title: selectList
	 * @Description: 查询列表
	 * @author zhuzq
	 * @date 2020年5月6日 上午11:08:39
	 * @return
	 */
	@Override
	public List<MenuDto> selectList() {

		Map<String, Object> paramMap = null;
		List<MenuDto> menuDtoList = null;
		List<Menu> menuList = menuDao.select(paramMap);
		if (null != menuList && menuList.size() > 0) {
			menuDtoList = new ArrayList<MenuDto>();
			for (Menu menu : menuList) {
				MenuDto menuDto = convertMenuDto(menu);
				menuDtoList.add(menuDto);
			}
		}
		return menuDtoList;
	}

	@Override
	public List<MenuTreeDto> getMenuTree() {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentId", 0);
		List<Menu> menuList = menuDao.select(paramMap);
		List<MenuTreeDto> menuTreeDtoList = new ArrayList<MenuTreeDto>();;
		if (null != menuList && menuList.size() > 0) {

			for (Menu menu : menuList) {
				MenuTreeDto tree = menuDeal(menu);
				menuTreeDtoList.add(tree);
			}
		}
		return menuTreeDtoList;
	}

	private MenuTreeDto menuDeal(Menu menu) {

		MenuTreeDto result = convertTreeMenuDto(menu);
		Integer menuId = menu.getMenuId();
		if (null != menuId) {
			// 二级菜单
			List<MenuTreeDto> childList = null;
			List<Menu> menuList = menuChildList(menuId);
			if (null != menuList && menuList.size() > 0) {
				childList = new ArrayList<MenuTreeDto>();
				for (Menu m : menuList) {
					MenuTreeDto childDto = convertTreeMenuDto(m);

					// 三级菜单
					List<MenuTreeDto> thirdList = null;
					List<Menu> thirdMenuList = menuChildList(childDto.getId());
					if (null != thirdMenuList && thirdMenuList.size() > 0) {
						thirdList = new ArrayList<MenuTreeDto>();
						for (Menu menu2 : thirdMenuList) {
							MenuTreeDto thirdDto = convertTreeMenuDto(menu2);
							thirdList.add(thirdDto);
						}
						childDto.setChildren(thirdList);
					}

					childList.add(childDto);
				}
				result.setChildren(childList);
			}

		}

		return result;
	}

	@Override
	public String getMenuIds(Integer roleId) {

		Role role = roleDao.get(roleId);
		List<String> list = null;
		String[] menuIdArr = null;
		if (null != role) {
			String menuId = role.getMenuId();
			if (StringUtils.isNotBlank(menuId)) {
				menuIdArr = menuId.split(",");
				if (null != menuIdArr && menuIdArr.length > 0) {
					list = new ArrayList<String>();
					for (String str : menuIdArr) {
						list.add(str);
					}
				}

				if (null != list && list.size() > 0) {
					// 获取菜单
					List<Menu> menuList = menuDao.selectByBatch(list);
					for (String str : menuIdArr) {
						if (null != menuList && menuList.size() > 0) {
							for (Menu menu : menuList) {
								String parentId = menu.getParentId();
								if (parentId.equals(str)) {
									list.remove(str);
								}
							}

						}

					}

				}

			}

		}
		return StringUtils.strip(Arrays.asList(list).toString(), "[]").replaceAll("\\s*", "");

	}

}
