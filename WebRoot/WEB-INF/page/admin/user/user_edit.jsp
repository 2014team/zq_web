<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
  <head>
  	<%@include file="/WEB-INF/page/admin/common/head_layui.jsp" %>
  	<script type="text/javascript" src="/admin/js/user_edit.js?t=<%=new java.util.Date().getTime() %>"></script>
  </head>
   <body>
    <div class="x-body">
        <form class="layui-form">
          <input type="hidden" name="userId" id="userId" value="${entity.userId}" /> 
        
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>用户名
              </label>
              <div class="layui-input-inline">
                  <input type="text" name="userName" value="${entity.userName }" lay-verify="required|userName"
                  autocomplete="off" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  1到25个字符
              </div>
          </div>
          
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>密码
              </label>
              <div class="layui-input-inline">
                  <input type="text" name="password" value="${entity.password }" lay-verify="required|password"
                  autocomplete="off" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  1到25个字符
              </div>
          </div>
          
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>状态
              </label>
               <div class="layui-input-inline">
                 <select name="validFlag" id="validFlag" value="${entity.validFlag}" lay-verify="required">
				  <option value="0" >启用</option>
				  <option value="1" >停用</option>
				 </select>  
			  </div>
		  </div>	
		  
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>角色
              </label>
               <div class="layui-input-inline">
				 <div class="layui-col-md6">
		              <select name="roleId" id="roleId" value="${entity.roleId}" lay-verify="required"  lay-search>
		                 <option value="" >请选择角色</option>
		                 <option value="0" >超级管理员</option>
				 		 <option value="1" >普通管理员</option>
		              </select>
		          </div>
			  </div>
		  </div>	
		  
		  
		  <div class="layui-form-item">
				<label for="L_pass" class="layui-form-label"> <span
					class="x-red">*</span>排序
				</label>
				<div class="layui-input-inline">
					<input type="text" name="sortId" value="${(empty entity.sortId or entity.sortId eq 0) ? 1:  entity.sortId }" maxlength="10"
						lay-verify="required|integer" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">数字(越小越靠前)</div>
		  </div>
			  
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
               <button class="layui-btn" lay-submit lay-filter="editSave"> 保存</button>
          </div>
      </form>
    </div>
  </body>
  
</html>