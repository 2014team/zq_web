
var admin_url_map = {
	'admin/LOGIN/SUBMIT': {url: '/admin/login/submit', isUsed: true, desc: '首页'},
	'admin/INDEX': {url: '/admin/index', isUsed: true, desc: '跳转登录'},
	
	//用户
	'admin/CENTER/USER/LIST': {url: '/admin/center/user/list', isUsed: true, desc: '管理员列表'},
	'admin/CENTER/USER/VALIDFLAG': {url: '/admin/center/user/validFlag', isUsed: true, desc: '更新状态'},
	'admin/CENTER/USER/DELETE': {url: '/admin/center/user/delete', isUsed: true, desc: '删除用户'},
	'admin/CENTER/USER/UPDATE': {url: '/admin/center/user/update', isUsed: true, desc: '修改用户'},
	'admin/CENTER/USER/EDIT': {url: '/admin/center/user/edit', isUsed: true, desc: '编辑用户'},
	'admin/CENTER/USER/SAVE': {url: '/admin/center/user/save', isUsed: true, desc: '保存用户'},
	'admin/CENTER/USER/BATCH/DELETE': {url: '/admin/center/user/batch/delete', isUsed: true, desc: '用户批量删除'},
	'admin/CENTER/USER/GET': {url: '/admin/center/user/get', isUsed: true, desc: '查找单个用户'},
	
	//角色
	'admin/CENTER/ROLE/GET': {url: '/admin/center/role/get', isUsed: true, desc: '角色查找'},
	'admin/CENTER/ROLE/SAVE': {url: '/admin/center/role/save', isUsed: true, desc: '角色保存'},
	'admin/CENTER/ROLE/DELETE': {url: '/admin/center/role/delete', isUsed: true, desc: '角色删除'},
	'admin/CENTER/ROLE/UPDATE': {url: '/admin/center/role/update', isUsed: true, desc: '角色修改'},
	'admin/CENTER/ROLE/EDIT': {url: '/admin/center/role/edit', isUsed: true, desc: '角色编辑'},
	'admin/CENTER/ROLE/LIST': {url: '/admin/center/role/list', isUsed: true, desc: '角色列表'},
	'admin/CENTER/ROLE/BATCH/DELETE': {url: '/admin/center/role/batch/delete', isUsed: true, desc: '角色批量删除'},
	'admin/CENTER/ROLE/VALIDFLAG': {url: '/admin/center/role/validFlag', isUsed: true, desc: '角色状态修改'},
	'admin/CENTER/ROLE/RIGHT': {url: '/admin/center/role/right', isUsed: true, desc: '角色分配权限'},
	'admin/CENTER/ROLE/RIGHT/SAVE': {url: '/admin/center/role/right/save', isUsed: true, desc: '角色分配权限保存'},
	
	//菜单
	'admin/CENTER/MENU/GET': {url: '/admin/center/menu/get', isUsed: true, desc: '菜单查找'},
	'admin/CENTER/MENU/SAVE': {url: '/admin/center/menu/save', isUsed: true, desc: '菜单保存'},
	'admin/CENTER/MENU/DELETE': {url: '/admin/center/menu/delete', isUsed: true, desc: '菜单删除'},
	'admin/CENTER/MENU/UPDATE': {url: '/admin/center/menu/update', isUsed: true, desc: '菜单修改'},
	'admin/CENTER/MENU/EDIT': {url: '/admin/center/menu/edit', isUsed: true, desc: '菜单编辑'},
	'admin/CENTER/MENU/LIST': {url: '/admin/center/menu/list', isUsed: true, desc: '菜单列表'},
	'admin/CENTER/MENU/BATCH/DELETE': {url: '/admin/center/menu/batch/delete', isUsed: true, desc: '菜单批量删除'},
	'admin/CENTER/MENU/VALIDFLAG': {url: '/admin/center/menu/validFlag', isUsed: true, desc: '菜单状态修改'},
	
	//日志
	'admin/CENTER/LOG/DETAIL': {url: '/admin/center/log/detail', isUsed: true, desc: '日志详情'},
	'admin/CENTER/LOG/LIST': {url: '/admin/center/log/list', isUsed: true, desc: '日志列表'},
	'admin/CENTER/LOG/BATCH/DELETE': {url: '/admin/center/log/batch/delete', isUsed: true, desc: '日志批量删除'},

}

var web_url_map = {

}



function getAminUrl(key) {
	if(admin_url_map[key] && admin_url_map[key].isUsed == true){
		return admin_url_map[key].url;
	}else{
		return '';
	}
}

function getWebUrl(key) {
	if(web_url_map[key] && web_url_map[key].isUsed == true){
		return web_url_map[key].url;
	}else{
		return '';
	}
}
