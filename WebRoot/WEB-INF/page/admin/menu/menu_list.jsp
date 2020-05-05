<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  		<%@include file="/WEB-INF/page/admin/common/head_layui.jsp" %>
  		<link rel="stylesheet" href="/admin/css/list.css?t=<%=new java.util.Date().getTime() %>">
  		<script type="text/javascript" src="/admin/js/menu_list.js?t=<%=new java.util.Date().getTime() %>"></script>
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
        <i class="layui-icon" style="line-height:38px">ဂ</i></a>
    </div>

    <div class="x-body">
	     <div class="layui-row">
	       <div class="layui-form layui-col-md12 x-so" >
	         <div class="layui-input-inline">
	         <input type="text" id="search_input" placeholder="请输入角色名" autocomplete="off" class="layui-input">
	         <button class="layui-btn"  id="search_id"><i class="layui-icon">&#xe615;</i></button>
	       </div>
	       </div>
	  </div>
      
       <!-- 列表 -->	
     <table class="layui-hide" id="table_list" lay-filter="table_list" ></table> 
    </div>
    
    <script type="text/html" id="toolbar">
      <div class="layui-btn-container toolbar">
         <button class="layui-btn layui-btn-sm layui-btn-danger" onclick="batchDel()" >批量删除</button>
         <button class="layui-btn layui-btn-sm"  onclick="edit()" ><i class="layui-icon"></i>增加</button>
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
	
    <script type="text/html" id="validFlagTpl">
		<tr cate-id="1" fid="0">
            <td>
              <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id="2"><i class="layui-icon"></i></div>
            </td>
            <td>1</td>
            <td>
              <i class="layui-icon x-show" status="false"></i>
              产品管理
            </td>
            <td><input type="text" class="layui-input x-sort" name="order" value="1"></td>
            
          </tr>
	</script>

  </body>
</html>