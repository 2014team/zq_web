package com.zq.admin.service;

import com.zq.common.service.BaseService;
import com.zq.admin.domain.entity.Menu;
import com.zq.admin.domain.vo.MenuVo;
import com.zq.admin.domain.dto.MenuDto;
import com.zq.common.entity.AdminResultByPage;

/**
 * @ClassName: MenuDao
 * @Description: 菜单
 * @author zhuzq
 * @date 2020年05月04日 13:39:51
 */
public interface MenuService extends BaseService<Menu,Integer>{

	/**
	 * @Title: saveMenu
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuVo
	 * @return
	 */
	public boolean saveMenu(MenuVo menuVo);

	/**
	 * @Title: deleteMenu
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuId
	 * @return
	 */
	public boolean deleteMenu(Integer menuId);

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuIdArr
	 * @return
	 */
	public int deleteByBatch(Integer[] menuIdArr);

	/**
	 * @Title: updateMenu
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuVo
	 * @return
	 */
	public boolean updateMenu(MenuVo menuVo);

	/**
	 * @Title: getMenu
	 * @Description: 根据menuId获取对象
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuId
	 * @return
	 */
	public MenuDto getMenu(Integer menuId);

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuVo
	 * @param jsonResult
	 * @return
	 */
	public AdminResultByPage findByPage(MenuVo menuVo, AdminResultByPage jsonResult);

	/**
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuVo
	 * @return
	 */
	public String checkParam(MenuVo menuVo);

	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuVo
	 * @return
	 */
	public String checkUnique(MenuVo menuVo);

}
