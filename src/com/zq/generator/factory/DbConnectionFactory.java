package com.zq.generator.factory;

import com.zq.generator.db.DataBaseUtil;
import com.zq.generator.db.DbConnection;
import com.zq.generator.db.impl.MySqlConnectionImpl;
import com.zq.generator.db.impl.SqlserverConnectionImpl;

public class DbConnectionFactory {
	
	public static DbConnection getDbConnection(){
		String dbType = DataBaseUtil.getDbType();
		if(null != dbType){
			if(dbType.equals("mysql")){
				return new MySqlConnectionImpl();
				
			}else if(dbType.equals("sqlserver")){
				return new SqlserverConnectionImpl();
				
			}
		}
		return null;
	}
}
