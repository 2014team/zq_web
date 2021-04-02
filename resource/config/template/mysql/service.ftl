package ${servicePackageName};

import ${serviceCommonPackageName}.BaseService;
import ${entityPackageName}.${table.className?cap_first};
import ${voPackageName}.${table.className?cap_first}Vo;
import ${dtoPackageName}.${table.className?cap_first}Dto;
import ${entityCommonPackage}.AdminResultByPage;

/**
 * @ClassName: ${table.className?cap_first}Dao
 * @Description: ${description}
 * @author ${author}
 * @date ${dateTime}
 */
public interface ${table.className?cap_first}Service extends BaseService<${table.className?cap_first},Integer>{

	/**
	 * @Title: save${table.className?cap_first}
	 * @Description: 保存
	 * @author ${author}
	 * @date ${dateTime}
	 * @param ${table.className?uncap_first}Vo
	 * @return
	 */
	public boolean save${table.className?cap_first}(${table.className?cap_first}Vo ${table.className?uncap_first}Vo);

	/**
	 * @Title: delete${table.className?cap_first}
	 * @Description: 删除
	 * @author ${author}
	 * @date ${dateTime}
	 * @param ${table.className?uncap_first}Id
	 * @return
	 */
	public boolean delete${table.className?cap_first}(Integer ${table.key_fields[0].java_field_Name});

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author ${author}
	 * @date ${dateTime}
	 * @param ${table.className?uncap_first}IdArr
	 * @return
	 */
	public int deleteByBatch(Integer[] ${table.className?uncap_first}IdArr);

	/**
	 * @Title: update${table.className?cap_first}
	 * @Description: 修改
	 * @author ${author}
	 * @date ${dateTime}
	 * @param ${table.className?uncap_first}Vo
	 * @return
	 */
	public boolean update${table.className?cap_first}(${table.className?cap_first}Vo ${table.className?uncap_first}Vo);

	/**
	 * @Title: get${table.className?cap_first}
	 * @Description: 根据${table.key_fields[0].java_field_Name}获取对象
	 * @author ${author}
	 * @date ${dateTime}
	 * @param ${table.className?uncap_first}Id
	 * @return
	 */
	public ${table.className?cap_first}Dto get${table.className?cap_first}(Integer ${table.className?uncap_first}Id);

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author ${author}
	 * @date ${dateTime}
	 * @param ${table.className?uncap_first}Vo
	 * @param jsonResult
	 * @return
	 */
	public AdminResultByPage findByPage(${table.className?cap_first}Vo ${table.className?uncap_first}Vo, AdminResultByPage jsonResult);

	/**
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author ${author}
	 * @date ${dateTime}
	 * @param ${table.className?uncap_first}Vo
	 * @return
	 */
	public String checkParam(${table.className?cap_first}Vo ${table.className?uncap_first}Vo);

	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author ${author}
	 * @date ${dateTime}
	 * @param ${table.className?uncap_first}Vo
	 * @return
	 */
	public String checkUnique(${table.className?cap_first}Vo ${table.className?uncap_first}Vo);

}
