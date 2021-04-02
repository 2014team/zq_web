package com.zq.common.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.zq.common.util.LogUtil;
import com.zq.common.util.PropertiesUtil;

@Component
public class ConfigListener implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (null == event.getApplicationContext().getParent()) {
			LogUtil.logInfo("系统开始初始化配置文件");
			//PropertiesUtil.init("");
		}
	}

}