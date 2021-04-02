<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
  <head>
  	<%@include file="/WEB-INF/page/admin/common/head_layui.jsp" %>
  	<script type="text/javascript" src="/admin/js/menu_edit.js?t=<%=new java.util.Date().getTime() %>"></script>
  </head>
   <body>
    <div class="x-body">
        <div class="layui-form">
          <input type="hidden" name="menuId" id="menuId" value="${menuDto.menuId}" /> 
        
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>名称
              </label>
              <div class="layui-input-inline">
                  <input type="text" name="menuName" value="${menuDto.menuName}" lay-verify="required" maxlength="10"
                  autocomplete="off" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  1到10个字符
              </div>
          </div>
          
          
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  	资源URL
              </label>
              <div class="layui-input-inline" style="width: 600px">
                  <input type="text" name="menuUrl" value="${menuDto.menuUrl }"  maxlength="60"
                  autocomplete="off" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  1到60个字符
              </div>
          </div>
          
		  
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>类型
              </label>
               <div class="layui-input-inline">
                 <select name="menuType" id="menuType" value="${menuDto.menuType}" lay-verify="required">
				  <option value="0" >菜单</option>
				  <option value="1" >按钮</option>
				 </select>  
			  </div>
		  </div>	
		  
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>状态
              </label>
               <div class="layui-input-inline">
                 <select name="validFlag" id="validFlag" value="${menuDto.validFlag}" lay-verify="required">
				  <option value="0" >启用</option>
				  <option value="1" >停用</option>
				 </select>  
			  </div>
		  </div>	

          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  	父级菜单
              </label>
               <div class="layui-input-inline">
                 <select name="parentId" id="parentId" value="${menuDto.parentId}"  lay-search>
				 		<option value="">请选择菜单</option>
				 	<c:forEach items="${menuDtoList}" var="item">
				 		 <option value="${item.menuId}" >${item.menuName}</option>
				 	</c:forEach>
				 </select>  
			  </div>
		  </div>	
		  
		  
		  <div class="layui-form-item">
				<label for="L_pass" class="layui-form-label"> <span
					class="x-red">*</span>排序
				</label>
				<div class="layui-input-inline">
					<input type="text" name="sortId" value="${(empty menuDto.sortId or menuDto.sortId eq 0) ? 1:  menuDto.sortId }" maxlength="10"
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
      </div>
    </div>
  </body>
  
</html>