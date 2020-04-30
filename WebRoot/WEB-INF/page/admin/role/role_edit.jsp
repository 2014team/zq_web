<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
  <head>
  	<%@include file="/WEB-INF/page/admin/common/head_layui.jsp" %>
  	<script type="text/javascript" src="/admin/js/right_edit.js?t=<%=new java.util.Date().getTime() %>"></script>
  </head>
   <body>
     <div class="x-body">
        <div  class="layui-form layui-form-pane">
                <div class="layui-form-item">
                    <label for="name" class="layui-form-label">
                        <span class="x-red">*</span>角色名
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" name="roleName" lay-verify="required"
                        autocomplete="off" class="layui-input" maxlength="10">
                    </div>
                    <div class="layui-form-mid layui-word-aux">
                  		1到10个字符
             		 </div>
                </div>
                
                <div class="layui-form-item layui-form-text">
                    <label for="desc" class="layui-form-label">
                        <span class="x-red">*</span>描述
                    </label>
                    <div class="layui-input-block" s>
                        <textarea placeholder="请输入内容"  name="description" class="layui-textarea" maxlength="200" lay-verify="required"></textarea>
                    </div>
                    <div class="layui-form-mid layui-word-aux">
                  		1到200个字符
             	 	</div>
                </div>
                
                <div class="layui-form-item">
                    <label for="name" class="layui-form-label">
                        <span class="x-red">*</span> 状态
                    </label>
	                    <div class="layui-input-block">
		                   <select name="validFlag" id="validFlag" lay-verify="required">
		                        <option value="0" >启用</option>
				 				<option value="1" >停用</option>
		                    </select>
                 		 </div>
                </div>
                
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">
                     	   拥有权限
                    </label>
                    <table  class="layui-table layui-input-block">
                        <tbody>
                        <c:forEach items="${rightList}" var="item">
                        	 <tr>
                                <td>
                                    <input type="checkbox" name="like1[write]" lay-skin="primary" title="${item.categoryName}">
                                </td>
                                <td>
                                    <div class="layui-input-block">
                                    	<c:forEach items="${item.rightDtoList }" var="chileItem">
	                                        <input name="id[]" lay-skin="primary" type="checkbox" value="2" title="${chileItem.rightName}"> 
                                    	</c:forEach>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                
                
				  <div class="layui-form-item">
						<label for="L_pass" class="layui-form-label"> <span
							class="x-red">*</span>排序
						</label>
						<div class="layui-input-inline">
							<input type="text" name="sortId" value="${(empty userDTO.sortId or userDTO.sortId eq 0) ? 1:  userDTO.sortId }" maxlength="10"
								lay-verify="required|integer" autocomplete="off"
								class="layui-input">
						</div>
						<div class="layui-form-mid layui-word-aux">数字(越小越靠前)</div>
				  </div>
              
                <div class="layui-form-item">
	                <button class="layui-btn" lay-submit lay-filter="editSave"> 保存</button>
	            </div>
            </div>
    </div>
  </body>
  
</html>