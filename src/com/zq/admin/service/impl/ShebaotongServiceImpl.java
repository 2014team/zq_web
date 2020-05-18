package com.zq.admin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.zq.admin.constant.ShebaotongConstant;
import com.zq.admin.service.ShebaotongService;

@Service
public class ShebaotongServiceImpl extends AJsoupServiceImpl implements ShebaotongService {

	
	@Override
	public Map<String, String> getLoginParam(String url) throws Exception {
		Map<String, String> resultMap = new HashMap<String, String>();
		Document doc = sendGet(url, null);
		Element form = doc.getElementById("form-login");
		Elements input= form.select("input");
		Elements inputHide= input.select("[type=hidden]");
		for (Element element : inputHide) {
			String name = element.attr("name");
			String value = element.attr("value");
			resultMap.put(name, value);
		}
		
		resultMap.put("userName", ShebaotongConstant.USERNAME);
		resultMap.put("password", ShebaotongConstant.PASSWORD);
		return resultMap;
	}

	@Override
	public Map<String, String> getinsOrg(Map<String, String> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
