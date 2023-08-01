package com.cnzh.csjl.common;

import com.cnzh.shili.Picture;
import com.cnzh.shili.Picture.ImageDataBean;
import com.google.gson.Gson;

public class Image {
	
	public static String getPicture() {
		String responseContent = HttpClientUtil.getInstance()  
                .sendHttpPost("https://open.ys7.com/api/lapp/device/capture", "accessToken="+AccessToken.getAccessToken()+"&deviceSerial=166007633&channelNo=1");  
        System.out.println("reponse content:" + responseContent);
        Gson gson=new Gson();
        Picture result=gson.fromJson(responseContent, Picture.class);
        ImageDataBean data=result.getData();
		String url="";
		url=data.getPicUrl();
		return url;
		/*String filepath = "D:\\Image\\"+HttpDownloadUtil.getRandomFileName()+".jpg";  
        HttpDownloadUtil.download(url, filepath);*/
	}
	
	public static void main(String[] args) {
		String url=getPicture();
		System.out.println(url);
	}

}
