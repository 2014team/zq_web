package ${daoPackageName};

import ${daoCommonPackageName}.BaseDao;
import org.springframework.stereotype.Repository;
import ${entityPackageName}.${table.className?cap_first};

/**
 * @ClassName: ${table.className?cap_first}Dao
 * @Description: ${description}
 * @author ${author}
 * @date ${dateTime}
 */
@Repository
public interface ${table.className?cap_first}Dao extends BaseDao<${table.className?cap_first},Integer>{

}
