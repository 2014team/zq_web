const LOGIN_SUBMIT = getAminUrl('admin/LOGIN/SUBMIT')
const INDEX = getAminUrl('admin/INDEX')


$(function() {
	$("#login").click(function() {
		login();
		return false;
	})
})

/*登录*/
function login() {
	var userName = $("#user_name").val();
	var password = $("#password").val();
	if (userName && password) {
		reqPostHasParameter(LOGIN_SUBMIT, {"userName":userName,"password":password},function(result) {
			if (result.code == 200) {
				$(location).attr('href', INDEX);
			} else {
				alert(result.msg);
			}
			
		}, function(e) {
			console.log(e);
		})
		

	} else {
		$("#login_form").removeClass('shake_effect');
		setTimeout(function() {
			$("#login_form").addClass('shake_effect')
		}, 1);
	}
}