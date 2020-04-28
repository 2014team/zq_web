package com.zq.admin.service;

import com.zq.common.service.BaseService;
import com.zq.admin.domain.entity.RightCategory;
import com.zq.admin.domain.vo.RightCategoryVo;
import com.zq.admin.domain.dto.RightCategoryDto;
import com.zq.common.entity.AdminResultByPage;

/**
 * @ClassName: RightCategoryDao
 * @Description: 权限分类
 * @author zhuzq
 * @date 2020年04月28日 15:54:44
 */
public interface RightCategoryService extends BaseService<RightCategory,Integer>{

	/**
	 * @Title: saveRightCategory
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategoryVo
	 * @return
	 */
	public boolean saveRightCategory(RightCategoryVo rightCategoryVo);

	/**
	 * @Title: deleteRightCategory
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategoryId
	 * @return
	 */
	public boolean deleteRightCategory(Integer categoryId);

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategoryIdArr
	 * @return
	 */
	public int deleteByBatch(Integer[] rightCategoryIdArr);

	/**
	 * @Title: updateRightCategory
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategoryVo
	 * @return
	 */
	public boolean updateRightCategory(RightCategoryVo rightCategoryVo);

	/**
	 * @Title: getRightCategory
	 * @Description: 根据categoryId获取对象
	 * @author zhuzq
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategoryId
	 * @return
	 */
	public RightCategoryDto getRightCategory(Integer rightCategoryId);

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategoryVo
	 * @param jsonResult
	 * @return
	 */
	public AdminResultByPage findByPage(RightCategoryVo rightCategoryVo, AdminResultByPage jsonResult);

	/**
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author zhuzq
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategoryVo
	 * @return
	 */
	public String checkParam(RightCategoryVo rightCategoryVo);

	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategoryVo
	 * @return
	 */
	public String checkUnique(RightCategoryVo rightCategoryVo);

}
