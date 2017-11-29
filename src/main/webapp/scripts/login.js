$(function(){
	$("#loginButton").click(loginFunction);
});

function loginFunction(){
	var account = $("#account").val();
	var password = $("#password").val();
	var code = $("#code").val();
	var data = {"account":account,"password":password,"code":code};
}