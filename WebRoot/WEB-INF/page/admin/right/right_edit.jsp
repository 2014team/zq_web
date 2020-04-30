<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
  <head>
  	<%@include file="/WEB-INF/page/admin/common/head_layui.jsp" %>
  	<script type="text/javascript" src="/admin/js/right_edit.js?t=<%=new java.util.Date().getTime() %>"></script>
  </head>
   <body>
    <div class="x-body">
        <div class="layui-form">
          <input type="hidden" name="rightId" id="rightId" value="${rightDTO.rightId}" /> 
        
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>规则
              </label>
              <div class="layui-input-inline" style="width: 600px">
                  <input type="text" name="rule" value="${rightDTO.rule }" lay-verify="required" maxlength="60"
                  autocomplete="off" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  1到60个字符
              </div>
          </div>
          
          
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>名称
              </label>
              <div class="layui-input-inline">
                  <input type="text" name="rightName" value="${rightDTO.rightName }" lay-verify="required" maxlength="10"
                  autocomplete="off" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  1到10个字符
              </div>
          </div>
          
		  
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>所属类别
              </label>
               <div class="layui-input-inline">
				 <div class="layui-col-md6">
		              <select name="rightCategoryId" id="rightCategoryId" value="${rightDTO.rightCategoryId }" lay-filter="rightCategoryId" lay-verify="required"  lay-search>
		                  <option value="" >请选择类别</option>
		                  <c:forEach items="${rightCategoryList}" var="item">
		                   <option value="${item.categoryId}" >${item.categoryName}</option>
		                 </c:forEach>	
		              </select>
		          </div>
			  </div>
		  </div>	
		  
		  
		  <div class="layui-form-item">
				<label for="L_pass" class="layui-form-label"> <span
					class="x-red">*</span>排序
				</label>
				<div class="layui-input-inline">
					<input type="text" name="sortId" value="${(empty rightDTO.sortId or rightDTO.sortId eq 0) ? 1:  rightDTO.sortId }" maxlength="10"
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