package com.zq.generator.db;

import java.sql.Connection;

import com.zq.generator.MyTable;

public abstract class DbConnection {
	

	/**
	 * 获取表
	 * @return
	 */
	public abstract MyTable getTable(String tableName);
	
	public Connection getConnection(){
		return DataBaseUtil.getConnection();
	}
	
}
