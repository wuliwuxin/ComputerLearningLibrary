package com.cnzh.csjl.common;

import java.util.Iterator;
import java.util.List;

import com.cnzh.shili.AtBen.DataBean;
import com.cnzh.shili.DaytimedataBen;
import com.google.gson.Gson;

public class Test {

	public static void main(String[] args) {
		/*String result=IBM.getResult(Image.getPicture());
		System.out.println(result);*/
		String responseContent = HttpClientUtil.getInstance()  
                .sendHttpGet("http://47.106.71.156:8080/csjl/Daytimedata/getequipmentId/2");
		Gson gson=new Gson();
		DaytimedataBen daytimedataBen= gson.fromJson(responseContent, DaytimedataBen.class);
		List<com.cnzh.shili.DaytimedataBen.DataBean> lDataBeans=daytimedataBen.getData();
		Iterator<com.cnzh.shili.DaytimedataBen.DataBean> it=lDataBeans.iterator();
		while(it.hasNext()) {
			com.cnzh.shili.DaytimedataBen.DataBean dataBean=it.next();
			if(dataBean.getBodyTemperature()>=38) {
				try {
					Push.wenduMessage();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
