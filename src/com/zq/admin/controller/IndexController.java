package com.zq.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName: indexController
 * @Description: 后台中心
 * @author zhuzq
 * @date 2020年4月16日 下午2:29:45
 */
@Controller
public class IndexController {

	@RequestMapping(value = "/admin/index", method = { RequestMethod.GET, RequestMethod.POST })
	public String index() {
		return "/admin/index";
	}

}
