﻿/*列表数据*/
const LIST = getAminUrl('admin/MENU/LIST')
/*列表删除*/
const DELETE = getAminUrl('admin/MENU/DELETE');
/*编辑*/
const EDIT = getAminUrl('admin/MENU/EDIT');
/*批量删除*/
const BATCH_DELETE = getAminUrl('admin/MENU/BATCH/DELETE');
/*查找*/
const GET = getAminUrl('admin/MENU/GET');
//行对象
var rowObj = "";

/*初始化layui*/
layui.use([ 'table', 'form', 'laydate' ], function() {
	    var table  = layui.table,
		form = layui.form,
		laydate = layui.laydate;

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
			/*{
				checkbox : true
			},*/
			{
				field : 'indexId',
				title : '序号',
				type : 'numbers',
				width : 75,
				sort : true,
			}, {
				field : 'menuName',
				title : '名称'
			}, {
				field : 'validFlag',
				title : '状态',
				templet : '#validFlagTpl'
			}, {
				field : 'sortId',
				title : '排序',
				sort : true
			}
			, {
				align : 'left',
				toolbar : '#operateBarTpl',
				title : '操作'
			}

		] ]
		  ,id: 'tableId',
		  done: function (res, curr, count) {
			  debugger
			  var that = this.elem.next();
	            console.log(this.elem)
	            console.log(that)
	            
			  res.data.forEach(function (item, index) {
				  debugger
				  	  var menuId = item.menuId;
				  	  var fid = item.parentId;
				  
	                  var tr = that.find(".layui-table-box tbody tr[data-index='" + index + "']");
	                  tr.attr({"cate-id":menuId,"fid":fid});
	                  
	                  
	                  
	                  var html='';
	                  if (item.childList) {
	                	  tr.find("td[data-field=menuName]").children("div").prepend('<i class="layui-icon x-show" status="false" onclick="showOrHide('+menuId+',this)"></i>')
	                	  var list = item.childList;
	                	  for(var item in list){
	                		  var parentId = list[item].parentId;
	                		  var menuId = list[item].menuId;
	                		  var menuName = list[item].menuName;
	                		  var sortId = list[item].sortId;
	                		  var validFlag = list[item].validFlag;
	                		  tr.after('<tr style="display: none;" data-index="'+item+'" cate-id="'+menuId+'" fid="'+parentId+'" class=""><td data-field="indexId"><div class="layui-table-cell laytable-cell-1-indexId laytable-cell-numbers"></div></td><td data-field="menuName"><div class="layui-table-cell laytable-cell-1-menuName">&nbsp;&nbsp;&nbsp;&nbsp;<i class="layui-icon x-show" status="false" onclick="showOrHide()"></i>管理员管理</div></td><td data-field="validFlag" data-content="0"><div class="layui-table-cell laytable-cell-1-validFlag">  <div class="td-status"> <span class="layui-btn layui-btn-normal layui-btn-mini">已启用</span> </div>  </div></td><td data-field="sortId"><div class="layui-table-cell laytable-cell-1-sortId">1</div></td><td data-field="4" align="left" data-off="true"><div class="layui-table-cell laytable-cell-1-4"> <div class="class=" td-manage"=""> <a title="状态修改" lay-event="updateValidFlag" href="javascript:;"> <i class="layui-icon"></i> </a> <a title="编辑" lay-event="edit" href="javascript:;"> <i class="layui-icon"></i> </a> <a title="删除" lay-event="del" href="javascript:;"> <i class="layui-icon"></i> </a> </div> </div></td></tr>');
	                	  };
	                	  
	                  }
	                  
	                
	                  
	                  
	                 

	            });
			  
		  }
	});
	
	//监听行工具条 
	table.on('tool(table_list)', function(obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
		rowObj = obj;
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
		if (layEvent === 'updateValidFlag') { //修改有效标识
			userValidFlag(obj);
		} else if (layEvent === 'edit') {
			edit(obj)//列表编辑
		} else if (layEvent === 'del') {
			del(obj);//列表删除
		}
	});
	
	
	/*搜索*/
	$('#search_id').on('click', function(){
        var roleName = $('#search_input').val();
		      //执行重载
		      table.reload( 'tableId',{
		      	method:"post",
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: {
		        	roleName: roleName
		        }
		      }, 'data');
   
     }); 
});


function showOrHide(menuId,obj){
	debugger
	
	var status = $(obj).attr("status");
	var child = $("tr[fid="+menuId+"]");
	if(status == "false"){
		$(obj).attr("status","true").html("");
		child.show();
	}else if(status == "true"){
		$(obj).attr("status","false").html("");;
		child.hide();
	}
	$//("tr[fid="+menuId+"]").toggle();
}


/*删除*/
function del(obj) {
	var rightId = obj.data.rightId;
	layer.confirm("确认要删除吗？", function(index) {
		reqPostHasParameter(DELETE, {
			"rightId" : rightId
		}, function(result) {
			if (result.code == 200) {
				layer.msg(result.msg, {
					icon : 1,
					time : 1000
				},function(){
					obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
					layer.close(index);		
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

/*编辑*/
function edit(obj) {
	 
	var url = EDIT;
	var title = '新增';
	if(obj){
		var rightId = obj.data.rightId;
		url = EDIT + "?rightId=" + rightId;
		 title = '修改';
	}	
	x_admin_show(title, url);
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
			array.push(e.rightId);
		 })
		reqPostHasParameter(BATCH_DELETE, {"rightIdArr":array},function(result) {
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

/*更新行数据*/
function updateRowData(obj){
	 var reqData = obj.field;
	 reqPostHasParameter(GET, {"rightId":reqData.rightId}, function(result) {
		 reqData = result.data.rightDTO;
		 rowObj.update({
		   		rightId : reqData.rightId,
		   		rule : reqData.rule,
		   		rightName : reqData.rightName,
		   		rightCategoryName : reqData.rightCategoryName,
		   		rightCategoryId : reqData.rightCategoryId,
				sortId : reqData.sortId
			});	
	 }, function(e) {
		 console.log(e);
	 })
}

/*表格重载*/
function updateTableData(){
	layui.table.reload('tableId', {
	       page: {
	         curr:1 //重新从第 1 页开始
	       }
	     }, 'data'); 
}



