<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<%@include file="/WEB-INF/page/admin/common/head_layui.jsp"%>
</head>

 <body> 
     <div class="x-body">
        <div  class="layui-form layui-form-pane">
				<div id="role_div" class="demo-tree-more"></div>
				 <div class="layui-form-item">
				 </div>
                <div class="layui-form-item">
	                <button class="layui-btn" lay-submit lay-filter="editSave"> 保存</button>
	            </div>
            </div>
    </div>
  </body>
  
<script>   

const RIGHT_SAVE = getAminUrl('admin/CENTER/ROLE/RIGHT/SAVE');
layui.use(['tree', 'util', 'form'], function(){
  var tree = layui.tree
  ,layer = layui.layer
  ,util = layui.util
  form = layui.form
  //模拟数据
  ,data = ${menuTreeDtoList};
  //基本演示
  tree.render({
    elem: '#role_div'
    ,data:data
    ,showCheckbox: true  //是否显示复选框
    ,id: 'roleDiv'
    ,isJump: true //是否允许点击节点时弹出新窗口跳转
   	/* ,oncheck: function(obj){
	    console.log(obj.data); //得到当前点击的节点数据
	    console.log(obj.checked); //得到当前节点的展开状态：open、close、normal
	    console.log(obj.elem); //得到当前节点元素
  	} */
  });
  
  	tree.setChecked('roleDiv', [${echoMenuId}]); //批量勾选 id 为 2、3 的节点
  
  	//保存
	form.on('submit(editSave)', function(obj) {
		var selectData = tree.getChecked('roleDiv');
		var array = new Array();
		$.each(selectData,function(i,e){
			array.push(e.id);
			if(e.children && e.children.length > 0){
				var e_1 =  e.children;
				$(e_1).each(function(i,e){
					array.push(e.id);
					if(e.children && e.children.length > 0){
						$(e.children).each(function(i,e){
							array.push(e.id);
						});
					}
				});
			}
		 })
		 reqPostHasParameter(RIGHT_SAVE, {"menuIdArr":array,"roleId":${roleId}+""},function(result) {
			 if (result.code == 200) { //这个是从后台取回来的状态值
				layer.msg(result.msg, {
					icon : 1,
					time : 2000
				},function(){x_admin_close()});
			}else{
				layer.msg(result.msg, {
					icon : 2,
					time : 1000
				});
			} 
			console.log(result);
		}, function(e) {
			console.log(e);
		}) 
	});
  
});

</script>
</html>