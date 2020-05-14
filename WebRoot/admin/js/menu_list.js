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
			}
			, {
				align : 'left',
				toolbar : '#operateBarTpl',
				title : '操作'
			}

		] ]
		  ,id: 'tableId',
		  done: function (res, curr, count) {
			    var that = this.elem.next();
	            //console.log(this.elem)
	            //console.log(that)
			    if(res.data){
			        
					  res.data.forEach(function (item, index) {
						  	  var f_menuId = item.menuId;
						  	  var f_fid = item.parentId;
						  
			                  var tr = that.find(".layui-table-box tbody tr[data-index='" + index + "']");
			                  tr.attr({"cate-id":f_menuId,"fid":f_fid});
			                  
			                  
			                  var html='';
			                  if (item.childList) {
			                	  tr.find("td[data-field=menuName]").children("div").prepend('<i class="layui-icon x-show" status="false" onclick="showOrHide('+f_menuId+',this)"></i>')
			                	  var list = item.childList;
			                	  for(var item in list){
			                		  var parentId = list[item].parentId;
			                		  var menuId = list[item].menuId;
			                		  var menuName = list[item].menuName;
			                		  var sortId = list[item].sortId;
			                		  var validFlag = list[item].validFlag;
			                		  var childList = list[item].childList;
			                		  
			                		  var validFlag_class = "layui-btn layui-btn-normal layui-btn-mini";
			                		  var validFlag_name = "已启用";
			                		  if(validFlag == 1){
			                			  validFlag_class += " layui-bg-red";
			                			  validFlag_name = "已停用";
			                		  }	 
			                		  
			                		  
			                		  //二级菜单处理
			                		  if(childList){
			                			  tr.after('<tr style="display: none;"  cate-id="'+menuId+'" fid="'+parentId+'" class=""><td data-field="indexId"><div class="layui-table-cell laytable-cell-1-indexId laytable-cell-numbers"></div></td><td data-field="menuName"><div class="layui-table-cell laytable-cell-1-menuName">&nbsp;&nbsp;&nbsp;&nbsp;<i class="layui-icon x-show" status="false" onclick="showOrHide('+menuId+',this)"></i>'+menuName+'</div></td><td data-field="validFlag" data-content="0"><div class="layui-table-cell laytable-cell-1-validFlag">  <div class="td-status"> <span class="'+validFlag_class+'">'+validFlag_name+'</span> </div>  </div></td><td data-field="sortId"><div class="layui-table-cell laytable-cell-1-sortId">'+sortId+'</div></td><td data-field="4" align="left" data-off="true"><div class="layui-table-cell laytable-cell-1-4"> <div  td-manage"=""> <a title="编辑" onclick="edit('+menuId+')" href="javascript:;"> <i class="layui-icon"></i> </a> <a title="删除" onclick="del('+menuId+')" href="javascript:;"> <i class="layui-icon" style="font-size: 20px;"></i> </a> </div> </div></td></tr>');
			                			  
			                			  var c_tr = that.find("tr[cate-id="+menuId+"]");
				                		  //三级菜单处理
				                		  if(childList){
				                			  for(var citem in childList){
				                				  var c_parentId = childList[citem].parentId;
				    	                		  var c_menuId = childList[citem].menuId;
				    	                		  var c_menuName = childList[citem].menuName;
				    	                		  var c_sortId = childList[citem].sortId;
				    	                		  var c_validFlag = childList[citem].validFlag;
				    	                		  var c_childList = childList[citem].childList;
				    	                		  
				    	                		  var c_validFlag_class = "layui-btn layui-btn-normal layui-btn-mini";
				    	                		  var c_validFlag_name = "已启用";
				    	                		  if(c_validFlag == 1){
				    	                			  c_validFlag_class += " layui-bg-red";
				    	                			  c_validFlag_name = "已停用";
				    	                		  }	  
				    	                		  c_tr.after('<tr style="display: none;"  cate-id="'+c_menuId+'" fid="'+c_parentId+'" class=""><td data-field="indexId"><div class="layui-table-cell laytable-cell-1-indexId laytable-cell-numbers"></div></td><td data-field="menuName"><div class="layui-table-cell laytable-cell-1-menuName">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├'+c_menuName+'</div></td><td data-field="validFlag" data-content="0"><div class="layui-table-cell laytable-cell-1-validFlag">  <div class="td-status"> <span class="'+c_validFlag_class+'">'+c_validFlag_name+'</span> </div>  </div></td><td data-field="sortId"><div class="layui-table-cell laytable-cell-1-sortId">'+c_sortId+'</div></td><td data-field="4" align="left" data-off="true"><div class="layui-table-cell laytable-cell-1-4"> <div  td-manage"=""> <a title="编辑" onclick="edit('+c_menuId+')" href="javascript:;"> <i class="layui-icon" style="font-size: 20px;"></i> </a> <a title="删除" onclick="del('+c_menuId+')" href="javascript:;"> <i class="layui-icon" style="font-size: 20px;"></i> </a> </div> </div></td></tr>');
				                			  }
				                		  }else{
				                			  //
				                		  }
			                			  
			                		  }else{
			                			  tr.after('<tr style="display: none;"  cate-id="'+menuId+'" fid="'+parentId+'" class=""><td data-field="indexId"><div class="layui-table-cell laytable-cell-1-indexId laytable-cell-numbers"></div></td><td data-field="menuName"><div class="layui-table-cell laytable-cell-1-menuName">&nbsp;&nbsp;&nbsp;&nbsp;├'+menuName+'</div></td><td data-field="validFlag" data-content="0"><div class="layui-table-cell laytable-cell-1-validFlag">  <div class="td-status"> <span class="'+validFlag_class+'">'+validFlag_name+'</span> </div>  </div></td><td data-field="sortId"><div class="layui-table-cell laytable-cell-1-sortId">'+sortId+'</div></td><td data-field="4" align="left" data-off="true"><div class="layui-table-cell laytable-cell-1-4"> <div td-manage> <a title="编辑" onclick="edit('+menuId+')" href="javascript:;"> <i class="layui-icon" style="font-size: 20px;"></i> </a> <a title="删除" onclick="del('+menuId+')" href="javascript:;"> <i class="layui-icon" style="font-size: 20px;"></i> </a> </div> </div></td></tr>');
			                		  
			                		  }
			                		  
			                	  };
			                  }

			            });
			    }
	       
			  
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
        var menuName = $('#search_input').val();
		      //执行重载
		      table.reload( 'tableId',{
		      	method:"post",
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: {
		        	menuName: menuName
		        }
		      }, 'data');
   
     }); 
});


function showOrHide(menuId,obj){
	
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
function del(menuId) {
	layer.confirm("确认要删除吗？", function(index) {
		reqPostHasParameter(DELETE, {
			"menuId" : menuId
		}, function(result) {
			if (result.code == 200) {
				layer.msg(result.msg, {
					icon : 1,
					time : 1000
				},function(){
					$("tr[cate-id="+menuId+"]").remove();
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
function edit(menuId,menuName) {
	 
	var url = EDIT;
	var title = '新增';
	if(menuId){
		url = EDIT + "?menuId=" + menuId+"&menuName="+menuName;
		 title = '修改';
	}	
	x_admin_show(title, url);
};


/*更新行数据*/
function updateRowData(obj){
	
	 var reqData = obj.field;
	 reqPostHasParameter(GET, {"menuId":reqData.menuId}, function(result) {
			 
		 var parentId = result.data.menuDto.parentId;
		 var menuId = result.data.menuDto.menuId;
		 var tr = $("tr[cate-id="+menuId+"]");
		 
		 var menuName = reqData.menuName;
		 var validFlag = reqData.validFlag;
		 var sortId = reqData.sortId;
		 
		 // menuName处理
		 var menuNameDiv = tr.children("td[data-field=menuName]").children("div");
		 var divHtml = menuNameDiv.html();
		 if(divHtml.lastIndexOf("</i>") != -1){
			 menuName = divHtml.substring(0,divHtml.lastIndexOf("</i>")+4)+menuName
		 }else if(divHtml.lastIndexOf("├") != -1){
			 menuName = divHtml.substring(0,divHtml.lastIndexOf("├")+1)+menuName
		 }
		 menuNameDiv.html(menuName);
		 
		 
		 // validFlag处理
		 var validFlagDiv = tr.children("td[data-field=validFlag]").children("div").children("div").children("span");
		  if(validFlag == 1){
			  validFlagDiv.addClass("layui-bg-red").html("已停用"); ;
		  }	else{
			  validFlagDiv.removeClass("layui-bg-red").html("已启用"); ;
		  }
		 
		  var sortIdDiv = tr.children("td[data-field=sortId]").children("div");
		  sortIdDiv.html(sortId);
		  
		  
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



