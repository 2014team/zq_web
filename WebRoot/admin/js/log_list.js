/*列表数据*/
const LIST = getAminUrl('admin/CENTER/LOG/LIST')
/*批量删除*/
const BATCH_DELETE = getAminUrl('admin/CENTER/LOG/BATCH/DELETE');
/*日志详情*/
const DETAIL = getAminUrl('admin/CENTER/LOG/DETAIL');

/*初始化layui*/
layui.use([ 'table', 'form', 'laydate' ], function() {
	    var table  = layui.table,
		form = layui.form,
		laydate = layui.laydate;

	/*日历选择器*/
	laydate.render({
		elem : '#startDate',
		done : function(value, date) { //监听日期被切换
			$("#startDate").val(value)
		}
	});
	laydate.render({
		elem : '#endDate', //指定元素
		done : function(value, date, endDate) {
			$("#endDate").val(value)
		}
	});

	table.render({
		elem : '#table_list',
		url : LIST,
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
				field : 'methodDescrible',
				title : '操作'
			}
			, {
				field : 'operator',
				title : '操作人'
			}, {
				field : 'logType',
				title : '日志类型',
				templet : function(d) {
					if(d.logType == 0){
						return "操作日志";
					}else if(d.logType == 1){
						return "异常日志";
					}
				},
			},{
				field : 'createDate',
				title : '操作时间',
				templet : function(d) {
					return date.toDateString(d.createDate, 'yyyy-MM-dd HH:mm:ss');
				},
				width : 180
			}, {
				field : 'requestIp',
				title : '操作IP'
			}, {
				align : 'left',
				toolbar : '#operateBarTpl',
				title : '操作'
			}

		] ]
		  ,id: 'tableId'
		, done: function (res, curr, count) {
			   var that = this.elem.next();
			   res.data.forEach(function (item, index) {
					var tr = that.find(".layui-table-box tbody tr[data-index='" + index + "']");
		            tr.attr({"ligId":res.data[index].logId});
					   if(res.data[index].logType == 1){
					       $("tr[ligId="+res.data[index].logId+"]").css("color","red")
					  }
			   });
			}
	});
	
	//监听行工具条 
	table.on('tool(table_list)', function(obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
		if  (layEvent === 'detail') {
			edit(obj)
		} 
	});
	
	/*搜索*/
	$('#search_id').on('click', function(){
        var methodDescrible = $('#search_input').val();
        var logType = $('#logType').val();
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		      //执行重载
		      table.reload( 'tableId',{
		      	method:"post",
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: {
		        	methodDescrible: methodDescrible,
		        	logType: logType,
		            startDate: startDate,
		            endDate: endDate
		        }
		      }, 'data');
   
     }); 
});



/*日志详情*/
function edit(obj) {
	var logId = obj.data.logId;
	url = DETAIL + "?logId=" + logId;
	x_admin_show("日志详情", url);
};



/*批量删除*/
function batchDel() {
	var selectData =layui.table.checkStatus('tableId').data;
	if(selectData.length < 1){	
		layer.msg('请选择要删除的数据！', {icon: 2});
		return false;
	}
	layer.confirm('确认要删除吗？', function(index) {
		var array = new Array();
		$.each(selectData,function(i,e){
			array.push(e.logId);
		 })
		reqPostHasParameter(BATCH_DELETE, {"logIdArr":array},function(result) {
			if (result.code == 200) { //这个是从后台取回来的状态值
				layer.msg(result.msg, {
					icon : 1,
					time : 1000
				},function(){
					layui.table.reload('tableId');
					layer.close(index);	
				});
			}
			
		}, function(e) {
			console.log(e);
		}) 
		
	});
		
   }	

