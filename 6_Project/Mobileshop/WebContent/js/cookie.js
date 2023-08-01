//JS操作cookies方法!
//写cookies
function setCookie(name,value,lostTime){
    // var time = 1;
	var time = lostTime*60*60*1000;
	if(lostTime == -1){
		time = -1;
	}
	
    var exp = new Date();
    // exp.setTime(exp.getTime() + 4*60*60*1000);//4小时失效
    exp.setTime(exp.getTime() + time);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString()+";path='/'";
}
 
function getCookie(name){
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg)){
        return unescape(arr[2]);
    }else{
        return null;
    }
    
function delCookie(name){
	setCookie(name, 1, -1);
}
}