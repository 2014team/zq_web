package com.zq.admin.service;

import java.util.Map;

import org.jsoup.nodes.Document;

public interface AJsoupService {
	
	/**
	* @Title: sendGet
	* @Description: 发送GET请求
	* @author zhuzq
	* @date  2020年5月18日 下午1:51:29
	* @param url
	* @param paramMap
	* @return
	* @throws Exception
	*/
	abstract Document sendGet(String url,Map<String,String> paramMap) throws Exception;
	
	/**
	* @Title: sendPost
	* @Description: 发送POST请求
	* @author zhuzq
	* @date  2020年5月18日 下午1:51:47
	* @param url
	* @param paramMap
	* @return
	* @throws Exception
	*/
	abstract Document sendPost(String url,Map<String,String> paramMap) throws Exception;
	
	/**
	* @Title: getCookies
	* @Description: 获取cookis值
	* @author zhuzq
	* @date  2020年5月18日 下午1:52:04
	* @param url
	* @param paramMap
	* @return
	* @throws Exception
	*/
	abstract Map<String,String> getCookies(String url,Map<String,String> paramMap) throws Exception;

}
