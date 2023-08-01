package com.cnzh.csjl.common;

import com.cnzh.shili.AccessTokenResult;
import com.cnzh.shili.AtBen;
import com.google.gson.Gson;

public class AccessToken { 
	
	public static void setAccessToken() {
		String responseContent = HttpClientUtil.getInstance()  
                .sendHttpPost("https://open.ys7.com/api/lapp/token/get", "appKey=e3de88c8aaec4cd7b21287e30e71cc97&appSecret=e39461e03947ee2284062c6991b9f529");  
        System.out.println("reponse content:" + responseContent);  
		Gson gson=new Gson();
		AccessTokenResult accessTokenResult=gson.fromJson(responseContent, AccessTokenResult.class);
		String acesstoken=accessTokenResult.getData().getAccessToken();
        String addAccessToken = HttpClientUtil.getInstance().sendHttpPost("http://47.106.71.156:8080/csjl/At/addAt?accessToken="+acesstoken);
        System.out.println("acesstoken-------"+acesstoken);
	}
	
	public static String getAccessToken() {
		String responseContent = HttpClientUtil.getInstance()  
	              .sendHttpGet("http://47.106.71.156:8080/csjl/At/1");
	        System.out.println("reponse content:" + responseContent);
	        Gson gson1=new Gson();
	        AtBen atBen=gson1.fromJson(responseContent, AtBen.class);
	        com.cnzh.shili.AtBen.DataBean dataBean=atBen.getData();
	        String accessToken = "";
	        if(null!=dataBean.getAccessToken()) {
	        	accessToken=dataBean.getAccessToken().toString();
	        }
	        else {
				setAccessToken();
				getAccessToken();
			}
	     return accessToken;
	}
	
}
