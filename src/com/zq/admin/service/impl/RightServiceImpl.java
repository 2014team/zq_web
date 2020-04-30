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
import com.zq.admin.dao.RightDao;
import com.zq.admin.domain.dto.RightCategoryDto;
import com.zq.admin.domain.dto.RightDto;
import com.zq.admin.domain.entity.Right;
import com.zq.admin.domain.entity.RightCategory;
import com.zq.admin.domain.vo.RightVo;
import com.zq.admin.service.RightService;
import com.zq.common.entity.AdminResultByPage;
import com.zq.common.service.impl.BaseServiceImpl;

/**
 * @ClassName: RightServiceImpl
 * @Description: 权限
 * @author zhuzq
 * @date 2020年04月29日 11:56:47
 */
@Service
public class RightServiceImpl extends BaseServiceImpl<Right,Integer>  implements RightService {
	
	@Autowired
	private RightDao rightDao;
	
	@Autowired
	private RightCategoryDao rightCategoryDao;


	/**
	 * @Title: saveRight
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年04月29日 11:56:47
	 * @param rightVo
	 * @return
	 */
	@Override
	public boolean saveRight(RightVo rightVo) {
		// RightVo转Right
		Right right = convertRight(rightVo);
		Integer result = rightDao.save(right);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteRight
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2020年04月29日 11:56:47
	 * @param rightId
	 * @return
	 */
	@Override
	public boolean deleteRight(Integer rightId) {
		Integer result = rightDao.delete(rightId);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2020年04月29日 11:56:47
	 * @param rightIdArr
	 * @return
	 */
	@Override
	public int deleteByBatch(Integer[] rightIdArr) {
		List<Integer> rightIdList = Arrays.asList(rightIdArr);
		return rightDao.deleteByBatch(rightIdList);
	}

	/**
	 * @Title: updateRight
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2020年04月29日 11:56:47
	 * @param rightVo
	 * @return
	 */
	@Override
	public boolean updateRight(RightVo rightVo) {
		// RightVo转Right
		Right right = convertRight(rightVo);
		Integer result = rightDao.update(right);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: getRight
	 * @Description: 根据rightId获取用户
	 * @author zhuzq
	 * @date 2020年04月29日 11:56:47
	 * @param rightId
	 * @return
	 */
	@Override
	public RightDto getRight(Integer rightId) {
		RightDto rightDTO = null;
		Right right = rightDao.get(rightId);
		if (null != right) {
			rightDTO = convertRightDto(right);
		}
		return rightDTO;
	}

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * * @date 2020年04月29日 11:56:47
	 * @param rightVo
	 * @param jsonResult
	 * @return
	 */
	@Override
	public AdminResultByPage findByPage(RightVo rightVo, AdminResultByPage jsonResult) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rightVo", rightVo);
		paramMap.put("page", jsonResult);

		int count = rightDao.findByPageCount(paramMap);

		if (count > 0) {
			List<RightDto> dataList = null;
			List<Right> rightList = findByPage(paramMap);
			if (null != rightList && rightList.size() > 0) {
				dataList = new ArrayList<RightDto>();
				for (Right right : rightList) {
					// Right转RightDTO
					RightDto rightDTO = convertRightDto(right);
					dataList.add(rightDTO);
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
	 * @date 2020年04月29日 11:56:47
	 * @param rightVo
	 * @return
	 */
	@Override
	public String checkParam(RightVo rightVo) {
	    String rule = rightVo.getRule();
		if (StringUtils.isBlank(rule)) {
			return "规则不能为空";
		}
	    String rightName = rightVo.getRightName();
		if (StringUtils.isBlank(rightName)) {
			return "权限名称不能为空";
		}
		Integer rightCategoryId = rightVo.getRightCategoryId();
		if (null == rightCategoryId) {
			return "权限类别ID不能为空";
		}
		Integer sortId = rightVo.getSortId();
		if (null == sortId) {
			return "排序不能为空";
		}
		return null;
	}


	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2020年04月29日 11:56:47
	 * @param rightVo
	 * @return
	 */
	@Override
	public String checkUnique(RightVo RightVo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rule", RightVo.getRule());
		List<Right> rightList = rightDao.select(paramMap);
		if (null == rightList || rightList.size() < 1) {
			return null;
		}

		Integer rightId = RightVo.getRightId();
		if (null != rightId) {
			for (Right entity : rightList) {
				if (!entity.getRightId().equals(rightId) && entity.getRule().equals(RightVo.getRule())) {
					return "规则已经存在";
				}
			}
		} else {
			return "规则已经存在";
		}
		return null;

	}

	/**
	 * @Title: convertRight
	 * @Description: RightVo转Right
	 * @author zhuzq
	 * @date 2020年04月29日 11:56:47
	 * @param rightVo
	 * @return
	 */
	private Right convertRight(RightVo rightVo) {
		Right right = new Right();
		right.setRightId(rightVo.getRightId());
		right.setRule(rightVo.getRule());
		right.setRightName(rightVo.getRightName());
		right.setRightCategoryId(rightVo.getRightCategoryId());
		right.setSortId(rightVo.getSortId());
		right.setCreateDate(rightVo.getCreateDate());
		right.setUpdateDate(rightVo.getUpdateDate());
		return right;
	}

	/**
	 * @Title: convertRightDto
	 * @Description: Right转RightDto
	 * @author zhuzq
	 * @date 2020年04月29日 11:56:47
	 * @param right
	 * @return
	 */
	private RightDto convertRightDto(Right right) {
		RightDto dto = new RightDto();
		Integer rightCategoryId  = right.getRightCategoryId();
		if(null != rightCategoryId && rightCategoryId > 0){
			RightCategory rightCategory = rightCategoryDao.get(rightCategoryId);
			if(null != rightCategory){
				String rightCategoryName = rightCategory.getCategoryName();
				dto.setRightCategoryName(rightCategoryName);
			}
		}
		
		dto.setRightId(right.getRightId());
		dto.setRule(right.getRule());
		dto.setRightName(right.getRightName());
		dto.setRightCategoryId(rightCategoryId);
		dto.setSortId(right.getSortId());
		dto.setCreateDate(right.getCreateDate());
		dto.setUpdateDate(right.getUpdateDate());
		
		return dto;
	}

	/**
	* @Title: getRightList
	* @Description: 权限列表
	* @author zhuzq
	* @date  2020年4月30日 下午4:06:11
	* @param rightCategoryDtoList
	* @return
	*/
	@Override
	public List<RightCategoryDto> getRightList(List<RightCategoryDto> rightCategoryDtoList) {
		
		Map<String,Object> paramMap = null;
		if(null != rightCategoryDtoList && rightCategoryDtoList.size() > 0){
			paramMap = new HashMap<String,Object>();
			List<RightDto> rightDtoList = null;
			for (RightCategoryDto rightCategoryDto : rightCategoryDtoList) {
				Integer categoryId = rightCategoryDto.getCategoryId();
				paramMap.put("rightCategoryId", categoryId);
				List<Right> rightList = rightDao.select(paramMap);
				if(null != rightList && rightList.size() > 0){
					rightDtoList = new ArrayList<RightDto>();
					for (Right right : rightList) {
						RightDto rightDto = convertRightDto(right);
						rightDtoList.add(rightDto);
					}
					
					rightCategoryDto.setRightDtoList(rightDtoList);
					
				}
			}
		}
		return rightCategoryDtoList;
	}
	
}
