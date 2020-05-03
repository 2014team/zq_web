
package com.zq.common.util;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

	/**
	* @Title: trimString
	* @Description: 数值转字符串
	* @author zhuzq
	* @date  2020年5月2日 下午9:03:04
	* @param arr
	* @return
	*/
	public static String trim(Integer[] arr) {

		if (null == arr || arr.length < 1) {
			return "";
		}
		return StringUtils.strip(Arrays.asList(arr).toString(), "[]").replaceAll("\\s*", "");

	}

}
