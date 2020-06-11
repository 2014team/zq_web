
package com.zq.admin.constant;

/**
 * @ClassName: MenuTypeEnum
 * @Description: 菜单按钮类型
 * @author zhuzq
 * @date 2020年4月16日 下午3:46:06
 */
public enum MenuTypeEnum {
	MENU(0, "菜单"), // 菜单
	BUTtON(1, "按钮"),// 按钮
	;

	private Integer value;

	private String name;

	private MenuTypeEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getNameByValue(Integer value) {
		MenuTypeEnum[] menuTypeEnumArr = MenuTypeEnum.values();
		for (MenuTypeEnum menuTypeEnum : menuTypeEnumArr) {
			Integer valueEnum = menuTypeEnum.value;
			if (valueEnum.equals(value)) {
				return menuTypeEnum.name;
			}
		}
		return null;
	}
}
