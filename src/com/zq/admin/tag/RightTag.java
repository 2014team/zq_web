
package com.zq.admin.tag;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.zq.admin.domain.dto.UserDto;
import com.zq.admin.service.RightService;
import com.zq.admin.util.SessionUtil;
import com.zq.common.util.LogUtil;

public class RightTag extends RequestContextAwareTag {

	private static final long serialVersionUID = 1L;

	private RightService rightService = null;

	/**
	 * 菜单名称
	 */
	private String menuName;

	/**
	 * 菜单URL
	 */
	private String menuUrl;

	public String getMenuName() {

		return menuName;
	}

	public void setMenuName(String menuName) {

		this.menuName = menuName;
	}

	public String getMenuUrl() {

		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {

		this.menuUrl = menuUrl;
	}

	@Override
	protected int doStartTagInternal() throws Exception {
		LogUtil.logInfo("menuName = "+this.menuName + " menuUrl" + this.menuUrl);
		
		HttpServletRequest request = ( HttpServletRequest ) this.pageContext.getRequest();
		UserDto userDto = SessionUtil.getSessionUser(request);

		// 判断是否登录
		if (null == userDto) {
			return SKIP_BODY;
		}
		Integer userId = userDto.getUserId();

		// 权限验证
		boolean result = getRightService().checkRight(userId, menuName, menuUrl);
		if (!result) {
			return SKIP_BODY;
		}

		return EVAL_PAGE;
	}

	private RightService getRightService() {
		if (null == rightService) {
			rightService = (RightService) 
							this.getRequestContext()
							 	.getWebApplicationContext()
									.getBean("rightServiceImpl");

		}
		return rightService;

	}

}
