$(document).ready(function(){
	$('.carousel').carousel();
	
	submitLogin = function(saltStr){
		var username= $('#username').val();
		var pwd= $('#password').val();
		if($.trim(username).length == 0 || $.trim(pwd).length == 0){
			alert('Username/password cannot be empty.');
			return false;
		} 
		console.log('saltStr', saltStr);
		var encSaltPass=encryptLoginPassword(saltStr,username,password);
		var encSaltSHAPass=encryptSha2LoginPassword(md5keystring,username,password);
		console.log('encSaltPass', encSaltPass);
		return false;
	};
});