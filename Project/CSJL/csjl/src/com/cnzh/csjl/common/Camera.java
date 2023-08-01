package com.cnzh.csjl.common;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.format.datetime.joda.DateTimeFormatterFactoryBean;

import com.cnzh.csjl.util.ResultUtil;
import com.cnzh.shili.AccessTokenResult;
import com.cnzh.shili.Picture;
import com.cnzh.shili.AccessTokenResult.DataBean;
import com.cnzh.shili.AtBen;
import com.cnzh.shili.EquitmentResult;
import com.cnzh.shili.Picture.ImageDataBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Camera {
	public static void getAccessToken() {
		String responseContent = HttpClientUtil.getInstance()  
              .sendHttpPost("http://47.106.71.156:8080/csjl/At/1");
//		String responseContent = HttpClientUtil.getInstance()  
//                .sendHttpPost("https://open.ys7.com/api/lapp/token/get", "appKey=e3de88c8aaec4cd7b21287e30e71cc97&appSecret=e39461e03947ee2284062c6991b9f529");
//		String responseContent ="{\"data\":{\"accessToken\":\"at.0lnnhm2b92recbyq4j3iwqx2297w87y6-3d24nnt6h9-0san4ef-hsbuvckoe\",\"expireTime\":1521164974349},\"code\":\"200\",\"msg\":\"操作成功!\"}";
        System.out.println("reponse content:" + responseContent);
        Gson gson1=new Gson();
        AtBen atBen=gson1.fromJson(responseContent, AtBen.class);
        com.cnzh.shili.AtBen.DataBean dataBean=atBen.getData();
        String accessToken = "";
        if(null!=dataBean.getAccessToken()) {
        	accessToken=dataBean.getAccessToken().toString();
        }
        else {
        	String responseContent2 = HttpClientUtil.getInstance()  
        			.sendHttpPost("https://open.ys7.com/api/lapp/token/get", "appKey=e3de88c8aaec4cd7b21287e30e71cc97&appSecret=e39461e03947ee2284062c6991b9f529");
			Gson gson=new Gson();
			java.lang.reflect.Type type = new TypeToken<AccessTokenResult>() {}.getType();
			AccessTokenResult result=gson.fromJson(responseContent, type);
			DataBean data=result.getData();
			String responseContent3 = HttpClientUtil.getInstance()  
        			.sendHttpPost("http://localhost:8080/csjl-Test/Equipment/updateEquipment?equipment_Id=1&accessToken="+data.getAccessToken());
			getAccessToken();
		}     
	}
	
	public static void getImage() {
		try {
			String accessToken=FileUtils.readLineNumber("D:/carmear/carmear_info.txt",1);
			String responseContent = HttpClientUtil.getInstance()  
	                .sendHttpPost("https://open.ys7.com/api/lapp/device/capture", accessToken+"&deviceSerial=166007633&channelNo=1");
			Gson gson=new Gson();
			java.lang.reflect.Type type = new TypeToken<Picture>() {}.getType();
			Picture result=gson.fromJson(responseContent, type);
			if(Integer.parseInt(result.getCode())==10002) {
				getAccessToken();
				accessToken=FileUtils.readLineNumber("D:/carmear/carmear_info.txt",1);
				responseContent = HttpClientUtil.getInstance()  
		                .sendHttpPost("https://open.ys7.com/api/lapp/device/capture", accessToken+"&deviceSerial=166007633&channelNo=1");
				result=gson.fromJson(responseContent, type);
				ImageDataBean data=result.getData();
				String url="";
				url=data.getPicUrl();
				String filepath = "D:\\Image\\"+HttpDownloadUtil.getRandomFileName()+".jpg";  
		        HttpDownloadUtil.download(url, filepath);
			}
			else {
			ImageDataBean data=result.getData();
			String url="";
			url=data.getPicUrl();
			System.out.println(url);
			ComputerViewApi.analyzeImage(url);
			String filepath = "D:\\Image\\"+HttpDownloadUtil.getRandomFileName()+".jpg";  
	        HttpDownloadUtil.download(url, filepath);
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
