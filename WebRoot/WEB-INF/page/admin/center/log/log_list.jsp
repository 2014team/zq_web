<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  	<%@include file="/WEB-INF/page/admin/common/head_layui.jsp" %>
  	<link rel="stylesheet" href="/admin/css/list.css?t=<%=new java.util.Date().getTime() %>">
  	<script type="text/javascript" src="/admin/js/log_list.js?t=<%=new java.util.Date().getTime() %>"></script>
  </head>
  
   <body>
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="/admin/user/list/ui">日志管理</a>
        <a>
          <cite>列表</cite></a>
      </span>
      <a class="layui-btn layui-btn-primary layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:38px">&#xe669;</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row">
        <div class="layui-form layui-col-md12 x-so">
        	<r:auth menuName="日志列表/查询" menuUrl="日志列表/查询"> 	
	          <div class="layui-input-inline">
	          <input class="layui-input" placeholder="开始日" name="startDate" id="startDate" readonly>
	          <input class="layui-input" placeholder="截止日" name="endDate" id="endDate" readonly>
	         </div>
	          <div class="layui-input-inline">
		             <select id="logType" lay-search>
		                 <option value="" >日志类型</option>
		                 <option value="0" >操作日志</option>
		                 <option value="1" >异常日志</option>
		              	 	
		            </select>
		          </div>
	           <div class="layui-input-inline">
	          	<input type="text" id="search_input" placeholder="请输入操作" autocomplete="off" class="layui-input">
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
		<r:auth menuName="日志列表/批量删除" menuUrl="日志列表/批量删除">
         <button class="layui-btn layui-btn-sm layui-btn-danger" onclick="batchDel()" >批量删除</button>
    		</r:auth>
     </div>
</script>
   
   
    <!-- 操作模板 -->
    <script type="text/html" id="operateBarTpl">
		<div class="td-manage">
			<r:auth menuName="日志列表/查看详情" menuUrl="日志列表/查看详情">
              <a title="查看详情" lay-event="detail" href="javascript:;">
					<i class="layui-icon"  style="font-size: 20px;">&#xe615;</i>
			  </a>
     		</r:auth>
		</div>
	</script>
  </body>
</html>
