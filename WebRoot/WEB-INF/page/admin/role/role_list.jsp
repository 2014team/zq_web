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
        <i class="layui-icon" style="line-height:38px">ဂ</i></a>
    </div>

    <div class="x-body">
	     <div class="layui-row">
	       <div class="layui-form layui-col-md12 x-so">
		       <div class="layui-input-inline">
		            <select id="rightCategoryId">
		                 <option value="" >请选择分类</option>
		              	  <c:forEach items="${rightCategoryList}" var="item">
		                  	<option value="${item.categoryId}" >${item.categoryName}</option>
		                  </c:forEach>	
		            </select>
		        </div>
	         
	         <input type="text" id="search_input" placeholder="请输入角色名" autocomplete="off" class="layui-input">
	         <button class="layui-btn"  id="search_id"><i class="layui-icon">&#xe615;</i></button>
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

  </body>
</html>