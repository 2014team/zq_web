<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  	<%@include file="/WEB-INF/page/admin/common/head_layui.jsp" %>
  	<link rel="stylesheet" href="/admin/css/user_list.css?t=<%=new java.util.Date().getTime() %>">
  	<script type="text/javascript" src="/admin/js/user_list.js?t=<%=new java.util.Date().getTime() %>"></script>
  	
  </head>
  
   <body>
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">演示</a>
        <a>
          <cite>导航元素</cite></a>
      </span>
      <a class="layui-btn layui-btn-primary layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:38px">ဂ</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
          <input class="layui-input" placeholder="开始日" name="start" id="start">
          <input class="layui-input" placeholder="截止日" name="end" id="end">
          <input type="text" name="username"  placeholder="请输入用户名" autocomplete="off" class="layui-input">
          <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
      </div>
      
       <!-- 列表 -->	
      <table class="layui-hide" id="table_list" lay-filter="table_list" ></table>
    </div>
    
    
    <script type="text/html" id="toolbar">
      <div class="layui-btn-container toolbar">
         <button class="layui-btn layui-btn-sm layui-btn-danger" data-type="batchDdel" >批量删除</button>
         <button class="layui-btn layui-btn-sm"  data-type="add" ><i class="layui-icon"></i>增加</button>
     </div>
	</script>
   
   
    <!-- 操作模板 -->
    <script type="text/html" id="operateBarTpl">
		<div class="class="td-manage">
              <a title="状态修改" lay-event="updateValidFlag" href="javascript:;">
					<i class="layui-icon">&#xe62f;</i>
				</a>
              <a title="编辑"  lay-event="edit" href="javascript:;">
                <i class="layui-icon">&#xe642;</i>
              </a>
              <a title="删除"  lay-event="del" href="javascript:;">
                <i class="layui-icon">&#xe640;</i>
              </a>
		</div>
	</script>

	<!-- 状态模板 -->
    <script type="text/html" id="validFlagTpl">
		{{# if(d.validFlag == 0){ }}
			<div class="td-status">
				<span class="layui-btn layui-btn-normal layui-btn-mini">已启用</span>
			</div>	
		{{#}else if(d.validFlag == 1){ }}
			<div class="td-status">
			    <span class="layui-btn layui-btn-normal layui-btn-mini layui-bg-red">已停用</span>
			</div>			
		{{#  }	}}
	</script>
    
    <script>
    
   

      /*用户-删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              $(obj).parents("tr").remove();
              layer.msg('已删除!',{icon:1,time:1000});
          });
      }



      function delAll (argument) {

        var data = tableCheck.getData();
  
        layer.confirm('确认要删除吗？'+data,function(index){
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
      }
    </script>
  </body>

</html>
