/*更新*/
const USER_UPDATE = getAminUrl('admin/CENTER/USER/UPDATE');
/*保存*/
const USER_SAVE = getAminUrl('admin/CENTER/USER/SAVE');


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
		var reqData = obj.field;
		reqPostHasParameter(checkSave() ? USER_UPDATE : USER_SAVE, reqData, function(result) {
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
});

//检查是否保存还是修改操作
function checkSave() {
	var userId = $("#userId").val();
	return userId;
};

//回显Select选值
function echoSelect(){
	echoSelectData("validFlag",$("#validFlag").attr('value'));
	echoSelectData("roleId",$("#roleId").attr('value'));
}