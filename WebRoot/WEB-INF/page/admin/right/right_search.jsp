<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/page/admin/common/head_layui.jsp" %>
<div class="x-body">
     <div class="layui-row">
       <div class="layui-form layui-col-md12 x-so">
         <input class="layui-input" placeholder="开始日" name="startDate" id="startDate">
         <input class="layui-input" placeholder="截止日" name="endDate" id="endDate">
       <div class="layui-input-inline">
            <select>
              <option>全部类别</option>
              <c:forEach items="${rightCategoryList}" var="item">
                  	<option value="${item.categoryId}" >${item.categoryName}</option>
                 </c:forEach>	
            </select>
        </div>
         
         <input type="text" id="search_input" placeholder="请输入权限名称" autocomplete="off" class="layui-input">
         <button class="layui-btn"  id="search_id"><i class="layui-icon">&#xe615;</i></button>
       </div>
  </div>