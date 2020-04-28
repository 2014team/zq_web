package com.zq.admin.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zq.admin.dao.RightCategoryDao;
import com.zq.admin.domain.entity.RightCategory;
import com.zq.admin.service.RightCategoryService;
import com.zq.common.service.impl.BaseServiceImpl;
import com.zq.admin.domain.vo.RightCategoryVo;
import com.zq.admin.domain.dto.RightCategoryDto;
import com.zq.common.entity.AdminResultByPage;

/**
 * @ClassName: RightCategoryServiceImpl
 * @Description: 权限分类
 * @author zhuzq
 * @date 2020年04月28日 15:54:44
 */
@Service
public class RightCategoryServiceImpl extends BaseServiceImpl<RightCategory,Integer>  implements RightCategoryService {
	
	@Autowired
	private RightCategoryDao rightCategoryDao;


	/**
	 * @Title: saveRightCategory
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategoryVo
	 * @return
	 */
	@Override
	public boolean saveRightCategory(RightCategoryVo rightCategoryVo) {
		// RightCategoryVo转RightCategory
		RightCategory rightCategory = convertRightCategory(rightCategoryVo);
		Integer result = rightCategoryDao.save(rightCategory);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteRightCategory
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategoryId
	 * @return
	 */
	@Override
	public boolean deleteRightCategory(Integer categoryId) {
		Integer result = rightCategoryDao.delete(categoryId);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategoryIdArr
	 * @return
	 */
	@Override
	public int deleteByBatch(Integer[] categoryIdArr) {
		List<Integer> categoryIdList = Arrays.asList(categoryIdArr);
		return rightCategoryDao.deleteByBatch(categoryIdList);
	}

	/**
	 * @Title: updateRightCategory
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategoryVo
	 * @return
	 */
	@Override
	public boolean updateRightCategory(RightCategoryVo rightCategoryVo) {
		// RightCategoryVo转RightCategory
		RightCategory rightCategory = convertRightCategory(rightCategoryVo);
		Integer result = rightCategoryDao.update(rightCategory);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: getRightCategory
	 * @Description: 根据rightCategoryId获取用户
	 * @author zhuzq
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategoryId
	 * @return
	 */
	@Override
	public RightCategoryDto getRightCategory(Integer rightCategoryId) {
		RightCategoryDto rightCategoryDTO = null;
		RightCategory rightCategory = rightCategoryDao.get(rightCategoryId);
		if (null != rightCategory) {
			rightCategoryDTO = convertRightCategoryDto(rightCategory);
		}
		return rightCategoryDTO;
	}

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * * @date 2020年04月28日 15:54:44
	 * @param rightCategoryVo
	 * @param jsonResult
	 * @return
	 */
	@Override
	public AdminResultByPage findByPage(RightCategoryVo rightCategoryVo, AdminResultByPage jsonResult) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rightCategoryVo", rightCategoryVo);
		paramMap.put("page", jsonResult);

		int count = rightCategoryDao.findByPageCount(paramMap);

		if (count > 0) {
			List<RightCategoryDto> dataList = null;
			List<RightCategory> rightCategoryList = findByPage(paramMap);
			if (null != rightCategoryList && rightCategoryList.size() > 0) {
				dataList = new ArrayList<RightCategoryDto>();
				for (RightCategory rightCategory : rightCategoryList) {
					// RightCategory转RightCategoryDTO
					RightCategoryDto rightCategoryDTO = convertRightCategoryDto(rightCategory);
					dataList.add(rightCategoryDTO);
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
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategoryVo
	 * @return
	 */
	@Override
	public String checkParam(RightCategoryVo rightCategoryVo) {
	    String categoryName = rightCategoryVo.getCategoryName();
		if (StringUtils.isBlank(categoryName)) {
			return "类别名不能为空";
		}
		Integer sortId = rightCategoryVo.getSortId();
		if (null == sortId) {
			return "排序不能为空";
		}
		return null;
	}


	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategoryVo
	 * @return
	 */
	@Override
	public String checkUnique(RightCategoryVo RightCategoryVo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("categoryName", RightCategoryVo.getCategoryName());
		List<RightCategory> rightCategoryList = rightCategoryDao.select(paramMap);
		if (null == rightCategoryList || rightCategoryList.size() < 1) {
			return null;
		}

		Integer categoryId = RightCategoryVo.getCategoryId();
		if (null != categoryId) {
			for (RightCategory entity : rightCategoryList) {
				if (!entity.getCategoryId().equals(categoryId) && entity.getCategoryName().equals(RightCategoryVo.getCategoryName())) {
					return "类别名已经存在";
				}
			}
		} else {
			return "类别名已经存在";
		}
		return null;

	}

	/**
	 * @Title: convertRightCategory
	 * @Description: RightCategoryVo转RightCategory
	 * @author zhuzq
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategoryVo
	 * @return
	 */
	private RightCategory convertRightCategory(RightCategoryVo rightCategoryVo) {
		RightCategory rightCategory = new RightCategory();
		rightCategory.setCategoryId(rightCategoryVo.getCategoryId());
		rightCategory.setCategoryName(rightCategoryVo.getCategoryName());
		rightCategory.setSortId(rightCategoryVo.getSortId());
		rightCategory.setCreateDate(rightCategoryVo.getCreateDate());
		rightCategory.setUpdateDate(rightCategoryVo.getUpdateDate());
		return rightCategory;
	}

	/**
	 * @Title: convertRightCategoryDto
	 * @Description: RightCategory转RightCategoryDto
	 * @author zhuzq
	 * @date 2020年04月28日 15:54:44
	 * @param rightCategory
	 * @return
	 */
	private RightCategoryDto convertRightCategoryDto(RightCategory rightCategory) {
		RightCategoryDto dto = new RightCategoryDto();
		dto.setCategoryId(rightCategory.getCategoryId());
		dto.setCategoryName(rightCategory.getCategoryName());
		dto.setSortId(rightCategory.getSortId());
		dto.setCreateDate(rightCategory.getCreateDate());
		dto.setUpdateDate(rightCategory.getUpdateDate());
		return dto;
	}
	
}
