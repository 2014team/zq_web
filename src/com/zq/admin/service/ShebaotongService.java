package com.zq.admin.service;

import java.util.Map;

public interface ShebaotongService extends AJsoupService {
	
	public Map<String, String> getLoginParam(String url) throws Exception;
	
	public Map<String, String> getinsOrg(Map<String, String> paramMap) throws Exception;

}
