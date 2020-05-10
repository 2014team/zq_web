<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<%@include file="/WEB-INF/page/admin/common/head_layui.jsp"%>
</head>
<body>
	<div id="role_div" class="demo-tree-more"></div>
</body>
<script>   

const RIGHT_SAVE = getAminUrl('admin/ROLE/RIGHT/SAVE');

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
   ,oncheck: function(obj){
   
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
			/* if (result.code == 200) { //这个是从后台取回来的状态值
				layer.msg(result.msg, {
					icon : 1,
					time : 1000
				});
			}else{
				layer.msg(result.msg, {
					icon : 2,
					time : 1000
				});
			} */
			console.log(result);
		}, function(e) {
			console.log(e);
		}) 
  	 }
	
  });
  
  tree.setChecked('roleDiv', [${echoMenuId}]); //批量勾选 id 为 2、3 的节点
  
});

</script>
</html>