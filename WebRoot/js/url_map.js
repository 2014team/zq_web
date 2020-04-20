
var admin_url_map = {
	'admin/LOGIN/SUBMIT': {url: '/admin/login/submit', isUsed: true, desc: '首页'},
	'admin/INDEX': {url: '/admin/index', isUsed: true, desc: '跳转登录'},
	'admin/USER/LIST': {url: '/admin/user/list', isUsed: true, desc: '管理员列表'},
	'admin/USER/VALIDFLAG': {url: '/admin/user/validFlag', isUsed: true, desc: '更新状态'},
	'admin/USER/DELETE': {url: '/admin/user/delete', isUsed: true, desc: '删除用户'},
	'admin/USER/UPDATE': {url: '/admin/user/update', isUsed: true, desc: '修改用户'},
	'admin/USER/EDIT': {url: '/admin/user/edit', isUsed: true, desc: '编辑用户'}
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
