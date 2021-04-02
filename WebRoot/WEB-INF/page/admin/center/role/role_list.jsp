<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  	<%@include file="/WEB-INF/page/admin/common/head_layui.jsp" %>
  		<link rel="stylesheet" href="/admin/css/list.css?t=<%=new java.util.Date().getTime() %>">
  	<script type="text/javascript" src="/admin/js/role_list.js?t=<%=new java.util.Date().getTime() %>"></script>
  </head>
  
   <body>
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="/admin/user/list/ui">角色管理</a>
        <a>
          <cite>列表</cite></a>
      </span>
      <a class="layui-btn layui-btn-primary layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:38px">&#xe669;</i></a>
    </div>

    <div class="x-body">
	     <div class="layui-row">
	       <div class="layui-form layui-col-md12 x-so">
	      	 <r:auth menuName="角色管理/查询" menuUrl="角色管理/查询"> 	
		         <div class="layui-input-inline">
		         	<input type="text" id="search_input" placeholder="请输入角色名" autocomplete="off" class="layui-input">
		      	 </div>
		         <div class="layui-input-inline">
		         	<button class="layui-btn"  id="search_id"><i class="layui-icon">&#xe615;</i></button>
		        </div>
	        </r:auth>
	       </div>
	  </div>
      
       <!-- 列表 -->	
      <table class="layui-hide" id="table_list" lay-filter="table_list" ></table>
    </div>
    
    <script type="text/html" id="toolbar">
      <div class="layui-btn-container toolbar">
		<r:auth menuName="角色管理/批量删除" menuUrl="角色管理/批量删除"> 		
         	<button class="layui-btn layui-btn-sm layui-btn-danger" onclick="batchDel()" >批量删除</button>
        </r:auth> 
		<r:auth menuName="角色管理/增加" menuUrl="角色管理/增加"> 	
  			<button class="layui-btn layui-btn-sm"  onclick="edit()" ><i class="layui-icon"></i>增加</button>
    	 </r:auth> 
	</div>
	</script>
   
   
    <!-- 操作模板 -->
    <script type="text/html" id="operateBarTpl">
		<div class="class="td-manage">
			<r:auth menuName="角色管理/分配权限" menuUrl="角色管理/分配权限"> 	  
				<a title="分配权限" lay-event="right" href="javascript:;">
					<i class="layui-icon" style="font-size: 20px;">&#xe672;</i>
			  </a>
			</r:auth> 	
			<r:auth menuName="角色管理/编辑" menuUrl="角色管理/编辑"> 	
              <a title="编辑"  lay-event="edit" href="javascript:;">
                <i class="layui-icon" style="font-size: 20px;">&#xe642;</i>
              </a>
			</r:auth> 	
			<r:auth menuName="角色管理/删除" menuUrl="角色管理/删除"> 	
              <a title="删除"  lay-event="del" href="javascript:;">
                <i class="layui-icon" style="font-size: 20px;">&#xe640;</i>
              </a>
			</r:auth> 	
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

  </body>
</html>
