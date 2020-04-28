
var admin_url_map = {
	'admin/LOGIN/SUBMIT': {url: '/admin/login/submit', isUsed: true, desc: '首页'},
	'admin/INDEX': {url: '/admin/index', isUsed: true, desc: '跳转登录'},
	'admin/USER/LIST': {url: '/admin/user/list', isUsed: true, desc: '管理员列表'},
	'admin/USER/VALIDFLAG': {url: '/admin/user/validFlag', isUsed: true, desc: '更新状态'},
	'admin/USER/DELETE': {url: '/admin/user/delete', isUsed: true, desc: '删除用户'},
	'admin/USER/UPDATE': {url: '/admin/user/update', isUsed: true, desc: '修改用户'},
	'admin/USER/EDIT': {url: '/admin/user/edit', isUsed: true, desc: '编辑用户'},
	'admin/USER/SAVE': {url: '/admin/user/save', isUsed: true, desc: '保存用户'},
	'admin/USER/BATCH/DELETE': {url: '/admin/user/batch/delete', isUsed: true, desc: '用户批量删除'},
	
	'admin/RIGHTCATEGORY/SAVE': {url: '/admin/rightCategory/save', isUsed: true, desc: '权限分类保存'},
	'admin/RIGHTCATEGORY/DELETE': {url: '/admin/rightCategory/delete', isUsed: true, desc: '权限分类删除'},
	'admin/RIGHTCATEGORY/UPDATE': {url: '/admin/rightCategory/update', isUsed: true, desc: '权限修改'},
	'admin/RIGHTCATEGORY/EDIT': {url: '/admin/rightCategory/edit', isUsed: true, desc: '权限编辑'},
	'admin/RIGHTCATEGORY/LIST': {url: '/admin/rightCategory/list', isUsed: true, desc: '权限列表'},
	'admin/RIGHTCATEGORY/BATCH/DELETE': {url: '/admin/rightCategory/batch/delete', isUsed: true, desc: '权限批量删除'}
	
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
