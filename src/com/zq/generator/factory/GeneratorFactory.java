package com.zq.generator.factory;

import com.zq.generator.db.DataBaseUtil;
import com.zq.generator.go.GeneratorCode;
import com.zq.generator.go.impl.MysqlGeneratorImpl;
import com.zq.generator.go.impl.SqlserverGeneratorImpl;

public class GeneratorFactory {
	
	public static GeneratorCode getGenerator(){
		
		String dbType = DataBaseUtil.getDbType();
		if(null != dbType){
			if(dbType.equals("mysql")){
				return new MysqlGeneratorImpl();
				
			}else if(dbType.equals("sqlserver")){
				return new SqlserverGeneratorImpl();
				
			}
		}
		return null;
	}

}
