package ${servicePackageName}.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${table.package_name_base}.dao.${table.className?cap_first}Dao;
import ${entityPackageName}.${table.className?cap_first};
import ${servicePackageName}.${table.className?cap_first}Service;
import ${serviceImplCommonPackageName}.BaseServiceImpl;
import ${voPackageName}.${table.className?cap_first}Vo;
import ${dtoPackageName}.${table.className?cap_first}Dto;
import ${entityCommonPackage}.AdminResultByPage;

/**
 * @ClassName: ${table.className?cap_first}ServiceImpl
 * @Description: ${description}
 * @author ${author}
 * @date ${dateTime}
 */
@Service
public class ${table.className?cap_first}ServiceImpl extends BaseServiceImpl<${table.className?cap_first},Integer>  implements ${table.className?cap_first}Service {
	
	@Autowired
	private ${table.className?cap_first}Dao ${table.className?uncap_first}Dao;


	/**
	 * @Title: save${table.className?cap_first}
	 * @Description: 保存
	 * @author ${author}
	 * @date ${dateTime}
	 * @param ${table.className?uncap_first}Vo
	 * @return
	 */
	@Override
	public boolean save${table.className?cap_first}(${table.className?cap_first}Vo ${table.className?uncap_first}Vo) {
		// ${table.className?cap_first}Vo转${table.className?cap_first}
		${table.className?cap_first} ${table.className?uncap_first} = convert${table.className?cap_first}(${table.className?uncap_first}Vo);
		Integer result = ${table.className?uncap_first}Dao.save(${table.className?uncap_first});
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: delete${table.className?cap_first}
	 * @Description: 删除
	 * @author ${author}
	 * @date ${dateTime}
	 * @param ${table.className?uncap_first}Id
	 * @return
	 */
	@Override
	public boolean delete${table.className?cap_first}(Integer ${table.key_fields[0].java_field_Name}) {
		Integer result = ${table.className?uncap_first}Dao.delete(${table.key_fields[0].java_field_Name});
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author ${author}
	 * @date ${dateTime}
	 * @param ${table.className?uncap_first}IdArr
	 * @return
	 */
	@Override
	public int deleteByBatch(Integer[] ${table.key_fields[0].java_field_Name}Arr) {
		List<Integer> ${table.key_fields[0].java_field_Name}List = Arrays.asList(${table.key_fields[0].java_field_Name}Arr);
		return ${table.className?uncap_first}Dao.deleteByBatch(${table.key_fields[0].java_field_Name}List);
	}

	/**
	 * @Title: update${table.className?cap_first}
	 * @Description: 修改
	 * @author ${author}
	 * @date ${dateTime}
	 * @param ${table.className?uncap_first}Vo
	 * @return
	 */
	@Override
	public boolean update${table.className?cap_first}(${table.className?cap_first}Vo ${table.className?uncap_first}Vo) {
		// ${table.className?cap_first}Vo转${table.className?cap_first}
		${table.className?cap_first} ${table.className?uncap_first} = convert${table.className?cap_first}(${table.className?uncap_first}Vo);
		Integer result = ${table.className?uncap_first}Dao.update(${table.className?uncap_first});
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: get${table.className?cap_first}
	 * @Description: 根据${table.className?uncap_first}Id获取${description}
	 * @author ${author}
	 * @date ${dateTime}
	 * @param ${table.className?uncap_first}Id
	 * @return
	 */
	@Override
	public ${table.className?cap_first}Dto get${table.className?cap_first}(Integer ${table.className?uncap_first}Id) {
		${table.className?cap_first}Dto ${table.className?uncap_first}DTO = null;
		${table.className?cap_first} ${table.className?uncap_first} = ${table.className?uncap_first}Dao.get(${table.className?uncap_first}Id);
		if (null != ${table.className?uncap_first}) {
			${table.className?uncap_first}DTO = convert${table.className?cap_first}Dto(${table.className?uncap_first});
		}
		return ${table.className?uncap_first}DTO;
	}

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author ${author}
	 * * @date ${dateTime}
	 * @param ${table.className?uncap_first}Vo
	 * @param jsonResult
	 * @return
	 */
	@Override
	public AdminResultByPage findByPage(${table.className?cap_first}Vo ${table.className?uncap_first}Vo, AdminResultByPage jsonResult) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("${table.className?uncap_first}Vo", ${table.className?uncap_first}Vo);
		paramMap.put("page", jsonResult);

		int count = ${table.className?uncap_first}Dao.findByPageCount(paramMap);

		if (count > 0) {
			List<${table.className?cap_first}Dto> dataList = null;
			List<${table.className?cap_first}> ${table.className?uncap_first}List = findByPage(paramMap);
			if (null != ${table.className?uncap_first}List && ${table.className?uncap_first}List.size() > 0) {
				dataList = new ArrayList<${table.className?cap_first}Dto>();
				for (${table.className?cap_first} ${table.className?uncap_first} : ${table.className?uncap_first}List) {
					// ${table.className?cap_first}转${table.className?cap_first}DTO
					${table.className?cap_first}Dto ${table.className?uncap_first}DTO = convert${table.className?cap_first}Dto(${table.className?uncap_first});
					dataList.add(${table.className?uncap_first}DTO);
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
	 * @author ${author}
	 * @date ${dateTime}
	 * @param ${table.className?uncap_first}Vo
	 * @return
	 */
	@Override
	public String checkParam(${table.className?cap_first}Vo ${table.className?uncap_first}Vo) {
		<#list table.common_fields as field>
		<#if (field.java_type == 'String' && field.java_type == 'String')>
	    String ${field.java_field_Name} = ${table.className?uncap_first}Vo.get${field.java_field_Name?cap_first}();
		if (StringUtils.isBlank(${field.java_field_Name})) {
			return "${field.field_comment}不能为空";
		}
		<#elseif (field.java_type == 'Integer' && field.java_type == 'Integer')>
		Integer ${field.java_field_Name} = ${table.className?uncap_first}Vo.get${field.java_field_Name?cap_first}();
		if (null == ${field.java_field_Name}) {
			return "${field.field_comment}不能为空";
		}
		<#else>
		</#if>
		</#list>
		return null;
	}


	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author ${author}
	 * @date ${dateTime}
	 * @param ${table.className?uncap_first}Vo
	 * @return
	 */
	@Override
	public String checkUnique(${table.className?cap_first}Vo ${table.className?cap_first}Vo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("${table.common_fields[0].java_field_Name}", ${table.className?cap_first}Vo.get${table.common_fields[0].java_field_Name?cap_first}());
		List<${table.className?cap_first}> ${table.className?uncap_first}List = ${table.className?uncap_first}Dao.select(paramMap);
		if (null == ${table.className?uncap_first}List || ${table.className?uncap_first}List.size() < 1) {
			return null;
		}

		Integer ${table.key_fields[0].java_field_Name?uncap_first} = ${table.className?cap_first}Vo.get${table.key_fields[0].java_field_Name?cap_first}();
		if (null != ${table.key_fields[0].java_field_Name?uncap_first}) {
			for (${table.className?cap_first} entity : ${table.className?uncap_first}List) {
				if (!entity.get${table.key_fields[0].java_field_Name?cap_first}().equals(${table.key_fields[0].java_field_Name?uncap_first}) && entity.get${table.common_fields[0].java_field_Name?cap_first}().equals(${table.className?cap_first}Vo.get${table.common_fields[0].java_field_Name?cap_first}())) {
					return "${table.common_fields[0].field_comment}已经存在";
				}
			}
		} else {
			return "${table.common_fields[0].field_comment}已经存在";
		}
		return null;

	}

	/**
	 * @Title: convert${table.className?cap_first}
	 * @Description: ${table.className?cap_first}Vo转${table.className?cap_first}
	 * @author ${author}
	 * @date ${dateTime}
	 * @param ${table.className?uncap_first}Vo
	 * @return
	 */
	private ${table.className?cap_first} convert${table.className?cap_first}(${table.className?cap_first}Vo ${table.className?uncap_first}Vo) {
		${table.className?cap_first} ${table.className?uncap_first} = new ${table.className?cap_first}();
		<#list (table.key_fields + table.common_fields) as field>
		${table.className?uncap_first}.set${field.java_field_Name?cap_first}(${table.className?uncap_first}Vo.get${field.java_field_Name?cap_first}());
		</#list>
		return ${table.className?uncap_first};
	}

	/**
	 * @Title: convert${table.className?cap_first}Dto
	 * @Description: ${table.className?cap_first}转${table.className?cap_first}Dto
	 * @author ${author}
	 * @date ${dateTime}
	 * @param ${table.className?uncap_first}
	 * @return
	 */
	private ${table.className?cap_first}Dto convert${table.className?cap_first}Dto(${table.className?cap_first} ${table.className?uncap_first}) {
		${table.className?cap_first}Dto dto = new ${table.className?cap_first}Dto();
		<#list (table.key_fields + table.common_fields) as field>
		dto.set${field.java_field_Name?cap_first}(${table.className?uncap_first}.get${field.java_field_Name?cap_first}());
		</#list>
		return dto;
	}
	
}
