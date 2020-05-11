
package com.zq.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class RightTag extends TagSupport{
	private static final long serialVersionUID = 1L;
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
	public int doStartTag() throws JspException {
		System.out.println(this.menuName+" "+this.menuUrl);
		// TODO Auto-generated method stub
		return SKIP_BODY;
	}
	


	

}
