<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
  <head>
  	<%@include file="/WEB-INF/page/admin/common/head_layui.jsp" %>
  	<script type="text/javascript" src="/admin/js/log_list.js?t=<%=new java.util.Date().getTime() %>"></script>
  </head>
  <body> 
     <div class="x-body">
        <div  class="layui-form layui-form-pane">
        
                <div class="layui-form-item">
                    <label for="name" class="layui-form-label">
                        操作
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" readonly="readonly"
                        autocomplete="off" class="layui-input" maxlength="10" value="${logDto.methodDescrible }">
                    </div>
                </div>
                
               <div class="layui-form-item">
                    <label for="name" class="layui-form-label">
                        	操作人	
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" readonly="readonly"
                        autocomplete="off" class="layui-input" maxlength="10" value="${logDto.operator }">
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <label for="name" class="layui-form-label">
                日期
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" readonly="readonly"
                        autocomplete="off" class="layui-input" maxlength="10" value="<fmt:formatDate value='${logDto.createDate}' pattern='yyyy-MM-dd HH:mm:ss' />">
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <label for="name" class="layui-form-label">
              请求方法名
                    </label>
                    <div class="layui-input-block">
                        <input type="text" readonly="readonly"
                        autocomplete="off" class="layui-input" maxlength="10" value="${logDto.requestMethod}">
                    </div>
                </div>
                
                 <div class="layui-form-item layui-form-text">
                    <label for="desc" class="layui-form-label">
                       异常详情
                    </label>
                    <div class="layui-input-block" >
                        <textarea readonly="readonly" class="layui-textarea" >${logDto.exceptionDetail }</textarea>
                    </div>
                </div>
                
                
                <div class="layui-form-item layui-form-text">
                    <label for="desc" class="layui-form-label">
                        请求参数
                    </label>
                    <div class="layui-input-block" >
                        <textarea readonly="readonly" class="layui-textarea">${logDto.requestParams}</textarea>
                    </div>
                </div>
                
                <div class="layui-form-item layui-form-text">
                    <label for="desc" class="layui-form-label">
                        错误信息
                    </label>
                    <div class="layui-input-block" >
                        <textarea readonly="readonly" class="layui-textarea" >${logDto.exceptionCode }</textarea>
                    </div>
                </div>
                
            </div>
    </div>
  </body>
  
</html>