/*列表*/
const USER_LIST = getAminUrl('admin/USER/LIST')
/*状态修改*/
const USER_VALIDFLAG = getAminUrl('admin/USER/VALIDFLAG');
/*删除*/
const USER_DELETE = getAminUrl('admin/USER/DELETE');
/*编辑*/
const USER_EDIT = getAminUrl('admin/USER/EDIT');

//行对象
var rowObj = "";


layui.use([ 'table', 'form', 'laydate' ], function() {
	var table = layui.table,
		form = layui.form,
		laydate = layui.laydate;

	/*日历选择器*/
	laydate.render({
		elem : '#start',
		done : function(value, date) { //监听日期被切换

		}
	});

	laydate.render({
		elem : '#end', //指定元素
		done : function(value, date, endDate) {}
	});

	table.render({
		elem : '#table_list',
		url : USER_LIST,
		toolbar : '#toolbar',
		method : "post",
		page : { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
			layout : [ 'limit', 'count', 'prev', 'page', 'next', 'skip' ], //自定义分页布局 //,curr: 5 //设定初始在第 5 页
			limit : 10, //每页显示的条数
			groups : 5, //步长
			first : '首页', //不显示首页
			last : '尾页', //不显示尾页
			prev : '上一页',
			next : '下一页'
		},
		cols : [ [
			{
				checkbox : true
			},
			{
				field : 'indexId',
				title : '序号',
				type : 'numbers',
				width : 75,
				sort : true,
			}
			, {
				field : 'userName',
				title : '用户名'
			}
			, {
				field : 'password',
				title : '密码'
			}, {
				field : 'updateDate',
				title : '创建日期',
				templet : function(d) {
					return date.toDateString(d.updateDate, 'yyyy-MM-dd');
				}
			}, {
				field : 'validFlag',
				title : '状态',
				templet : '#validFlagTpl'
			}, {
				field : 'sortId',
				title : '排序'
			}

			, {
				align : 'left',
				toolbar : '#operateBarTpl',
				title : '操作'
			}

		] ]
	});
	
	//监听行工具条 
	table.on('tool(table_list)', function(obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
		rowObj = obj;
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
		if (layEvent === 'updateValidFlag') { //修改有效标识
			userValidFlag(obj);
		} else if (layEvent === 'edit') {
			edit(obj)
		} else if (layEvent === 'del') {
			del(obj);
		}
	});
	
	
	//监听Bar
	$('.toolbar .layui-btn').on('click', function(){
	    var type = $(this).data('type');
	    active[type] ? active[type].call(this) : '';
	  });
	
	var active = {
			batchDdel: function(){ //获取选中数据
		    	debugger
		      var checkStatus = table.checkStatus('table_list')
		      ,data = checkStatus.data;
		      layer.alert(JSON.stringify(data));
		    }
		    ,add: function(){ 
		    	edit()
		    }
		  };
	

});

/*状态修改*/
function userValidFlag(obj) {
	var confirmTile = '确认要停用吗？';
	var data = obj.data; //获得当前行数据
	var userId = data.userId;
	var validFlag = data.validFlag
	if (validFlag == 0) {
		validFlag = 1;
	} else {
		confirmTile = '确认要启用吗？';
		validFlag = 0;
	}
	layer.confirm(confirmTile, function(index) {
		reqPostHasParameter(USER_VALIDFLAG, {
			"userId" : userId,
			"validFlag" : validFlag
		}, function(result) {

			if (result.code == 200) {
				layer.msg(result.msg, {
					icon : 1,
					time : 1000
				});

				var reqData = result.data;
				obj.update({
					userId : reqData.userId,
					userName : reqData.userName,
					password : reqData.password,
					isAdmin : reqData.isAdmin,
					validFlag : reqData.validFlag,
					sortId : reqData.sortId,
				});
			} else {
				layer.msg(result.msg, {
					icon : 2,
					time : 1000
				});
			}
		}, function(e) {
			console.log(e);
		})
	});
}
;

/*删除*/
function del(obj) {
	var userId = obj.data.userId;
	layer.confirm("删除行吗", function(index) {
		reqPostHasParameter(USER_DELETE, {
			"userId" : userId
		}, function(result) {
			if (result.code == 200) {
				layer.msg(result.msg, {
					icon : 1,
					time : 1000
				},function(){
					obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
					x_admin_close();	
				});
				
			} else {
				layer.msg(result.msg, {
					icon : 2,
					time : 1000
				});
			}
		}, function(e) {
			console.log(e);
		})
	});
};

/*修改*/
function edit(obj) {
	var url = USER_EDIT;
	var title = '新增用户';
	if(obj){
		userId = obj.data.userId;
		url = USER_EDIT + "?userId=" + userId;
		 title = '修改用户';
	}	
	x_admin_show(title, url);
};

/*更新行数据*/
function updateRowData(obj){
	 var reqData = obj.field;
   	 rowObj.update({
		userId : reqData.userId,
		userName : reqData.userName,
		password : reqData.password,
		isAdmin : reqData.isAdmin,
		validFlag : reqData.validFlag,
		sortId : reqData.sortId,
	});
}

