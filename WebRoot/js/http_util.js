/***
 * reqGet : GET请求
 * url : 请求url
 * success : 成功之后请求方法
 * fail :    失败之后请求方法
 */
function reqGet(url,success,fail){
	if(!url){
		return ;
	}
	$.ajax({
		type : "GET", //请求方式 
		url : url, //	请求URL
		dataType : "json", // 返回参数类型
		async : false, //是否异步请求   true：异步  , 反之：不异步
		cache : false, //是否缓存此页面   true：缓存  , 反之：不缓存
		success : function(data) {
			success(data);
		},
		error : function(e) {
			fail(e);
		}
	});
}


/***
 * reqGetHasParameter : GET带参数请求
 * url : 请求url
 * parameter ：请求参数
 * success : 成功之后请求方法
 * fail :    失败之后请求方法
 */
function reqGetHasParameter(url,parameter,success,fail){
	if(!url){
		return ;
	}
	$.ajax({
		type : "GET", //请求方式 
		url : url, //	请求URL
		data : parameter,
		dataType : "json", // 返回参数类型
		async : false, //是否异步请求   true：异步  , 反之：不异步
		cache : false, //是否缓存此页面   true：缓存  , 反之：不缓存
		success : function(data) {
			success(data);
		},
		error : function(e) {
			fail(e);
		}
	});
}


/***
 * reqPost : POST请求
 * url : 请求url
 * success : 成功之后请求方法
 * fail :    失败之后请求方法
 */
function reqPost(url,success,fail){
	if(!url){
		return ;
	}
	$.ajax({
		type : "POST", //请求方式 
		url : url, //	请求URL
		dataType : "json", // 返回参数类型
		async : false, //是否异步请求   true：异步  , 反之：不异步
		cache : false, //是否缓存此页面   true：缓存  , 反之：不缓存
		success : function(data) {
			success(data);
		},
		error : function(e) {
			fail(e);
		}
	});
}


/***
 * reqPostHasParameter : POST带参数请求
 * url : 请求url
 * parameter ：请求参数
 * success : 成功之后请求方法
 * fail :    失败之后请求方法
 */
function reqPostHasParameter(url,parameter,success,fail){
	if(!url){
		return ;
	}
	$.ajax({
		type : "POST", //请求方式 
		url : url, //	请求URL
		data : parameter,
		dataType : "json", // 返回参数类型
		async : false, //是否异步请求   true：异步  , 反之：不异步
		cache : false, //是否缓存此页面   true：缓存  , 反之：不缓存
		success : function(data) {
			success(data);
		},
		error : function(e) {
			fail(e);
		}
	});
}