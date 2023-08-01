package com.cnzh.csjl.common;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.cnzh.shili.CitckingQuite;
import com.cnzh.shili.CitckingQuite.ImagesBean;
import com.cnzh.shili.CitckingQuite.ImagesBean.ClassifiersBean;
import com.cnzh.shili.CitckingQuite.ImagesBean.ClassifiersBean.ClassesBean;
import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

public class IBM {
	
	public static String getResult(String imageUri) {
		/*String url="https://watson-api-explorer.mybluemix.net/visual-recognition/api/v3/classify?"
				+ "api_key=aM-BnbyYPfVQI-a6OaJ6uIpM2hA-ltWO-t8PxYHNWA5E&"
				+ "version=2018-03-19&"
				+ "url="+imageUri+"&"
				+ "owners=me&"
				+ "classifier_ids=DefaultCustomModel_324562129&"
				+ "threshold=0.5";*/
		String classx=null;
		double score=0;
		/*String responseContent = HttpClientUtil.getInstance()  
	              .sendHttpsGet(url);
	    System.out.println("reponse content:" + responseContent);*/
		IamOptions options = new IamOptions.Builder().apiKey("aM-BnbyYPfVQI-a6OaJ6uIpM2hA-ltWO-t8PxYHNWA5E").build();
		VisualRecognition service = new VisualRecognition("2018-03-19",options);
        //service.setApiKey("aM-BnbyYPfVQI-a6OaJ6uIpM2hA-ltWO-t8PxYHNWA5E");
            ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                    .url(imageUri)
                    .imagesFilename("l.jpg")
                    .threshold((float) 0.0)
                    .classifierIds(Arrays.asList("DefaultCustomModel_324562129"))
                    //.owners(Arrays.asList("me"))
                    .build();
            ClassifiedImages response = service.classify(classifyOptions).execute();
            System.out.println(response);
            String responseContent=response.toString();
	    Gson gson = new Gson();
	    CitckingQuite citckingQuite=gson.fromJson(responseContent, CitckingQuite.class);
	    List<ImagesBean> lImagesBeans=citckingQuite.getImages();
	    Iterator<ImagesBean> iterator=lImagesBeans.iterator();
	    while(iterator.hasNext()) {
	    	ImagesBean imagesBean=iterator.next();
	    	List<ClassifiersBean> lClassifiersBeans=imagesBean.getClassifiers();
	    	Iterator<ClassifiersBean> it=lClassifiersBeans.iterator();
	    	while(it.hasNext()) {
	    		ClassifiersBean classifiersBean=it.next();
	    		List<ClassesBean> lBeans=classifiersBean.getClasses();
	    		Iterator<ClassesBean> iterator2=lBeans.iterator();
	    		while(iterator2.hasNext()) {
	    			ClassesBean classesBean=iterator2.next();
	    			classx=classesBean.getClassX();
	    			score=classesBean.getScore();
	    		}
	    	}
	    }
	    return classx+score;
	}

}
