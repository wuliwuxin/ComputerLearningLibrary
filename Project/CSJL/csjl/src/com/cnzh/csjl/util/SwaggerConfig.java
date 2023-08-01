package com.cnzh.csjl.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

   /**
    * SwaggerConfig
    * 由chenp 的CSMMAACToolv4.0.5生成
    *ApiDoc工具
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:35 CST 2018 By chenp
    */ 
@Configuration
@EnableSwagger
public class SwaggerConfig{
	private SpringSwaggerConfig springSwaggerConfig;
	@Autowired
	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig){
		this.springSwaggerConfig = springSwaggerConfig;
	}
	@Bean
	public SwaggerSpringMvcPlugin customImplementation(){
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
				.apiInfo(apiInfo())
				.includePatterns(".*?");
	}
	private ApiInfo apiInfo(){
		ApiInfo apiInfo = new ApiInfo(
				"沉睡精灵管理系统",
				"csjlsystem",
				"conanzh",
				"server@conanzh.com",
				"APILicenceType",
				"#");
		return apiInfo;
	}

}

