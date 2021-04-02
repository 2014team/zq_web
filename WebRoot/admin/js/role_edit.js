/*更新*/
const UPDATE = getAminUrl('admin/CENTER/ROLE/UPDATE');
/*保存*/
const SAVE = getAminUrl('admin/CENTER/ROLE/SAVE');

$(function(){
	//回显Select选值	
	echoSelect();
	
	

});



/*初始化layui*/
layui.use([ 'form', 'layer' ], function() {
	$ = layui.jquery;
	var form = layui.form,
		layer = layui.layer
	//自定义验证规则
	form.verify({
		integer : [
			/^[1-9]\d*$/
			, '只能输入正整数'
		]
	});

	//保存
	form.on('submit(editSave)', function(obj) {
		//将页面全部复选框选中的值拼接到一个数组中
	    var arr_categoryId = [];
	    var arr_rightId = [];
	    
	    $("input[lay-filter-catetory]").next("div.layui-unselect.layui-form-checkbox.layui-form-checked").each(function() {
	    	arr_categoryId.push($(this).prev("input").val().trim());
	    });
	    $("input[lay-filter-right]").next("div.layui-unselect.layui-form-checkbox.layui-form-checked").each(function() {
	    	arr_rightId.push($(this).prev("input").val().trim());
	    });
	    
	    
		var reqData = obj.field;
		if(arr_categoryId && arr_categoryId.length > 0){
			reqData['categoryIdArr'] = arr_categoryId;
		}
		if(arr_rightId && arr_rightId.length > 0){
			reqData['rightIdArr'] = arr_rightId;
			
		}
		
		reqPostHasParameter(checkSave() ? UPDATE : SAVE, reqData, function(result) {
			if (result.code == 200) {
				layer.msg(result.msg, {
					icon : 1,
					time : 1000
				}, function() {
					x_admin_close();

					//检查是否保存还是修改操作
					if (checkSave()) {
						//修改,更新行数据
						window.parent.updateRowData(obj);
					} else {
						//保存，重载列表
						window.parent.updateTableData();
					}


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
	
	// checkbox监听categoryId
	form.on('checkbox(categoryId)', function(data){//用于监听复选框
		var roleId = $("#roleId").val();
		var divInput = $(data.elem);
		var divObj = $(data.elem).next("div");
		var divClass = $(data.elem).next("div").attr("class");
		var categoryIds  =$("input[lay-filter-right="+data.value+"]");
		
		var showClss = "layui-unselect layui-form-checkbox layui-form-checked";
		var hideClss = "layui-unselect layui-form-checkbox";
		
		//新增选中
		if(data.elem.checked){
			divObj.attr("class",showClss);
			categoryIds.next("div").attr("class",showClss);
			divInput.attr("checked","checked");
			categoryIds.attr("checked","checked");
		//选择取消
		}else if(!data.elem.checked){
			divObj.attr("class",hideClss);
			categoryIds.next("div").attr("class",hideClss);
			divInput.removeAttr("checked");
			categoryIds.removeAttr("checked");
		}
	 });
	
	// checkbox监听rightId
	form.on('checkbox(rightId)', function(data){//用于监听复选框
		var divObj = $(data.elem).next("div");
		var catetoryId = $(data.elem).attr("lay-filter-right");
		var categoryIds  =$("input[lay-filter-catetory="+catetoryId+"]");
		var divClass = $(data.elem).next("div").attr("class");
		
		var divInput = $(data.elem);
		var showClss = "layui-unselect layui-form-checkbox layui-form-checked";
		var hideClss = "layui-unselect layui-form-checkbox";
		
		if(data.elem.checked){
			divObj.attr("class",showClss);
			categoryIds.next("div").attr("class",showClss);
			divInput.attr("checked","checked");
			categoryIds.attr("checked","checked");
		}else if(!data.elem.checked){
			divObj.attr("class",hideClss);
			categoryIds.next("div").attr("class",hideClss);
			var categoryIdsDiv  =$("input[lay-filter-right="+catetoryId+"]").next("div.layui-unselect.layui-form-checkbox.layui-form-checked");
			if(categoryIdsDiv && categoryIdsDiv.length >0){
				categoryIds.next("div").attr("class",showClss);
			}
			
		}
	 });
	
	
});

//检查是否保存还是修改操作
function checkSave() {
	var roleId = $("#roleId").val();
	return roleId;
};



//回显Select选值
function echoSelect(){
	echoSelectData("validFlag",$("#validFlag").attr('value'));
	
}


function bodyInit(){
	echocheckbox("hivCategoryId","categoryId");
	echocheckbox("hivRightId","rightId");
	
	
}

//checkbox回显
function echocheckbox(echoValueId,filterId){
	var hivCategoryIdArr = "";
	var hivCategoryId = $("#"+echoValueId).val();
	if(hivCategoryId){
		var layFilterCategoryId = $("input[lay-filter="+filterId+"]")
		if(layFilterCategoryId){
			hivCategoryIdArr = hivCategoryId.split(",");
			if(hivCategoryIdArr && hivCategoryIdArr.length > 0){
				for(var i = 0; i < hivCategoryIdArr.length; i ++){
					layFilterCategoryId.each(function(){
						if(hivCategoryIdArr[i] == $(this).val()){
							$(this).attr("checked","checked");
							$(this).next("div").attr("class","layui-unselect layui-form-checkbox layui-form-checked");
						}
					});
				}
			}
			
		}
	}
	
}

