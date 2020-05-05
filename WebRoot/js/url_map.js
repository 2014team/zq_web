
var admin_url_map = {
	'admin/LOGIN/SUBMIT': {url: '/admin/login/submit', isUsed: true, desc: '首页'},
	'admin/INDEX': {url: '/admin/index', isUsed: true, desc: '跳转登录'},
	
	//用户
	'admin/USER/LIST': {url: '/admin/user/list', isUsed: true, desc: '管理员列表'},
	'admin/USER/VALIDFLAG': {url: '/admin/user/validFlag', isUsed: true, desc: '更新状态'},
	'admin/USER/DELETE': {url: '/admin/user/delete', isUsed: true, desc: '删除用户'},
	'admin/USER/UPDATE': {url: '/admin/user/update', isUsed: true, desc: '修改用户'},
	'admin/USER/EDIT': {url: '/admin/user/edit', isUsed: true, desc: '编辑用户'},
	'admin/USER/SAVE': {url: '/admin/user/save', isUsed: true, desc: '保存用户'},
	'admin/USER/BATCH/DELETE': {url: '/admin/user/batch/delete', isUsed: true, desc: '用户批量删除'},
	'admin/USER/GET': {url: '/admin/user/get', isUsed: true, desc: '查找单个用户'},
	
	//权限分类
	'admin/RIGHTCATEGORY/SAVE': {url: '/admin/rightCategory/save', isUsed: true, desc: '权限分类保存'},
	'admin/RIGHTCATEGORY/DELETE': {url: '/admin/rightCategory/delete', isUsed: true, desc: '权限分类删除'},
	'admin/RIGHTCATEGORY/UPDATE': {url: '/admin/rightCategory/update', isUsed: true, desc: '权限分类修改'},
	'admin/RIGHTCATEGORY/EDIT': {url: '/admin/rightCategory/edit', isUsed: true, desc: '权限分类编辑'},
	'admin/RIGHTCATEGORY/LIST': {url: '/admin/rightCategory/list', isUsed: true, desc: '权限分类列表'},
	'admin/RIGHTCATEGORY/BATCH/DELETE': {url: '/admin/rightCategory/batch/delete', isUsed: true, desc: '权限分类批量删除'},
	
	//权限
	'admin/RIGHT/GET': {url: '/admin/right/get', isUsed: true, desc: '权限查找'},
	'admin/RIGHT/SAVE': {url: '/admin/right/save', isUsed: true, desc: '权限保存'},
	'admin/RIGHT/DELETE': {url: '/admin/right/delete', isUsed: true, desc: '权限删除'},
	'admin/RIGHT/UPDATE': {url: '/admin/right/update', isUsed: true, desc: '权限修改'},
	'admin/RIGHT/EDIT': {url: '/admin/right/edit', isUsed: true, desc: '权限编辑'},
	'admin/RIGHT/LIST': {url: '/admin/right/list', isUsed: true, desc: '权限列表'},
	'admin/RIGHT/BATCH/DELETE': {url: '/admin/right/batch/delete', isUsed: true, desc: '权限批量删除'},
	
	//角色
	'admin/ROLE/GET': {url: '/admin/role/get', isUsed: true, desc: '角色查找'},
	'admin/ROLE/SAVE': {url: '/admin/role/save', isUsed: true, desc: '角色保存'},
	'admin/ROLE/DELETE': {url: '/admin/role/delete', isUsed: true, desc: '角色删除'},
	'admin/ROLE/UPDATE': {url: '/admin/role/update', isUsed: true, desc: '角色修改'},
	'admin/ROLE/EDIT': {url: '/admin/role/edit', isUsed: true, desc: '角色编辑'},
	'admin/ROLE/LIST': {url: '/admin/role/list', isUsed: true, desc: '角色列表'},
	'admin/ROLE/BATCH/DELETE': {url: '/admin/role/batch/delete', isUsed: true, desc: '角色批量删除'},
	'admin/ROLE/VALIDFLAG': {url: '/admin/role/validFlag', isUsed: true, desc: '角色状态修改'},
	
	//菜单
	'admin/MENU/GET': {url: '/admin/menu/get', isUsed: true, desc: '菜单查找'},
	'admin/MENU/SAVE': {url: '/admin/menu/save', isUsed: true, desc: '菜单保存'},
	'admin/MENU/DELETE': {url: '/admin/menu/delete', isUsed: true, desc: '菜单删除'},
	'admin/MENU/UPDATE': {url: '/admin/menu/update', isUsed: true, desc: '菜单修改'},
	'admin/MENU/EDIT': {url: '/admin/menu/edit', isUsed: true, desc: '菜单编辑'},
	'admin/MENU/LIST': {url: '/admin/menu/list', isUsed: true, desc: '菜单列表'},
	'admin/MENU/BATCH/DELETE': {url: '/admin/menu/batch/delete', isUsed: true, desc: '菜单批量删除'},
	'admin/MENU/VALIDFLAG': {url: '/admin/menu/validFlag', isUsed: true, desc: '菜单状态修改'}

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
