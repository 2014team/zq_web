package com.zq.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zq.admin.constant.ShebaotongConstant;
import com.zq.admin.service.ShebaotongService;
import com.zq.common.util.HttpUtil;

@Controller
public class ShebaotongController {
	
	@Autowired
	private ShebaotongService shebaotongService;
	
	@RequestMapping("/login")
	public String login(){
		try {
			Map<String,String> paramMap = shebaotongService.getLoginParam(ShebaotongConstant.ULR_LOGIN);
			
			Map<String,String> cookiesParamMap = shebaotongService.getCookies(ShebaotongConstant.ULR_LOGIN_SUBMIT, paramMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
