<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
  <head>
  	<%@include file="/WEB-INF/page/admin/common/head_layui.jsp" %>
  </head>
   <body>
    <div class="x-body">
        <form class="layui-form">
          <input type="hidden" name="userId"  value="${entity.userId}" /> 
        
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
                  <input type="password" name=password value="${entity.password }" lay-verify="required|password"
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
                 <select name="validFlag" lay-verify="required">
				  <option value="0"  ${entity.validFlag eq 0 ? 'selected' :''}>启用</option>
				  <option value="1" ${entity.validFlag eq 1? 'selected' :''}>停用</option>
				 </select>  
			  </div>
		  </div>	
		  
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>角色
              </label>
               <div class="layui-input-inline">
                 <select name="roleId" lay-verify="required">
				  <option value="0" >超级管理员</option>
				  <option value="1" >普通管理员</option>
				 </select>  
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
    <script>
        /*更新*/
		const USER_UPDATE = getAminUrl('admin/USER/UPDATE');
        layui.use(['form','layer'], function(){
           $ = layui.jquery;
           var form = layui.form
          ,layer = layui.layer
          //自定义验证规则
          form.verify({
             userName: function(value){
              if(value.length > 25){
                return '不能超过25个字符';
              }
            },
            password: function(value){
              if(value.length > 25){
                return '不能超过25个字符';
              }
            }
            ,integer: [
               /^[1-9]\d*$/
               , '只能输入正整数'
            ]
          });
    
    		//保存
    		form.on('submit(editSave)', function(obj) {
    		debugger
    			   var reqData = obj.field;
    				reqGetHasParameter(USER_UPDATE, reqData, function(result) {
    					if (result.code == 200) {
    						layer.msg(result.msg, {
    							icon : 1,
    							time : 1000
    						}, function() {
    							x_admin_close();
    							//更新行数据
    							window.parent.updateRowData(obj)
    						});
    
    					} else {
    						layer.msg(result.msg, {
    							icon : 2,
    							time : 1000
    						});
    					}
    				}, function(e) {
    					console.log(e);
    				})
    
    				return false;
    			});
    	});
    </script>
  </body>

</html>