package com.zq.admin.constant;

import com.zq.common.util.PropertiesUtil;

public class ShebaotongConstant {
	
	public static String ULR_LOGIN ="https://www.shebaotong.com/shebaotong/login";
	public static String ULR_LOGIN_SUBMIT ="https://www.shebaotong.com/shebaotong/logon";
	public static String ULR_FIND_INSAREADTL ="https://www.shebaotong.com/shebaotong/member/insAreaSup/findInsAreaDtl?areaCode=AREACODE&insuranceOrgCode=INSURANCEORGCODE";
	public static String ULR_FIND_INSAREAORG ="https://www.shebaotong.com/shebaotong/member/insAreaSup/findInsAreaOrg?areaCode=AREACODE";
	
	public static String USERNAME = PropertiesUtil.getValue("shebaotong_user_name");
	public static String PASSWORD = PropertiesUtil.getValue("shebaotong_user_password");

	
	
}
