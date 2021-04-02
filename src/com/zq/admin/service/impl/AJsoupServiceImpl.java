package com.zq.admin.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.zq.admin.service.AJsoupService;

@Service
public class AJsoupServiceImpl implements AJsoupService {

	@Override
	public Document sendGet(String url, Map<String, String> paramMap) throws Exception {
		Document doc = null;
		Connection con = Jsoup.connect(url);
		if (null != paramMap) {
			con.data(paramMap);
		}
		doc = con.userAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; MALC)")
				.ignoreContentType(true).timeout(100000).get();
		return doc;
	}

	@Override
	public Document sendPost(String url, Map<String, String> paramMap) throws Exception {
		Document doc = Jsoup.connect(url).data(paramMap)
				.userAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; MALC)")
				.ignoreContentType(true)
				//  设置超时等待
				.timeout(100000).post();
		return doc;
	}

	@Override
	public Map<String, String> getCookies(String url, Map<String, String> paramMap) throws Exception {
		if (StringUtils.isBlank(url)) {
			return null;
		}

		Map<String, String> resultMap = new HashMap<String, String>();

		Connection con = Jsoup.connect(url);
		if (null != paramMap && paramMap.size() > 0) {
			con.data(paramMap);
		}
		con.userAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; MALC)").ignoreContentType(true)
				//  设置请求方式
				.method(Method.POST)
				//  设置超时等待
				.timeout(100000);
		Response response = con.execute();
		if (null != response) {
			Map<String, String> cookies = response.cookies();
			for (Map.Entry<String, String> entry : cookies.entrySet()) {
				resultMap.put(entry.getKey(), entry.getValue());
			}
		}
		return resultMap;
	}

}
