package com.asus.evlessleeping.wiget;

public class GetAccessToken {
	public static void main(String[] args) {
		
		String responseContent = HttpClientUtil.getInstance()  
                .sendHttpPost("https://open.ys7.com/api/lapp/token/get", "appKey=e3de88c8aaec4cd7b21287e30e71cc97&appSecret=e39461e03947ee2284062c6991b9f529");  
        System.out.println("reponse content:" + responseContent);  
		
	}
	
}
