package com.cnzh.csjl.entity;

import java.sql.*;
import com.cnzh.csjl.util.StrUtil;
import java.util.HashMap;
import java.util.*;
import java.io.Serializable;
   /**
    * At
    * 由chenp 的CSMMAACToolv4.0.5生成
    *实体
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 


public class At implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long aT_id;//密钥ID
	private String accessToken;//AccessToken
	private Timestamp genTime;//创建时间
	/**
	* 获取 密钥ID
	* @return  aT_id 密钥ID
	* 由CSMMAACToolv4.0.5生成
	*/
	public Long getAT_id(){
		return aT_id;
	}
	/**
	* 设置 密钥ID
	* @param  aT_id 密钥ID
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setAT_id(Long aT_id){
	this.aT_id=aT_id;
	}
	/**
	* 获取 AccessToken
	* @return  accessToken AccessToken
	* 由CSMMAACToolv4.0.5生成
	*/
	public String getAccessToken(){
		return accessToken;
	}
	/**
	* 设置 AccessToken
	* @param  accessToken AccessToken
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setAccessToken(String accessToken){
	this.accessToken=accessToken;
	}
	/**
	* 获取 创建时间
	* @return  genTime 创建时间
	* 由CSMMAACToolv4.0.5生成
	*/
	public Timestamp getGenTime(){
		return genTime;
	}
	/**
	* 设置 创建时间
	* @param  genTime 创建时间
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setGenTime(Timestamp genTime){
	this.genTime=genTime;
	}
	/**
	* 获取字符格式创建时间
	* @return  genTime 创建时间
	* 由CSMMAACToolv4.0.5生成
	*/
	public String getGenTimeString(){
		return  StrUtil.Timesamp2String(genTime);
	}
/**
	 * 转换为hashmap
	 * 由CSMMAACToolv4.0.5 生成
	 * @return
	 */
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != aT_id) {
			map.put("aT_id", aT_id);
		}
		if (null != accessToken) {
			map.put("accessToken", accessToken);
		}
		if (null != genTime) {
			map.put("genTime", genTime);
		}
		return map;
		}
	@Override //By生成工具v4.0.5
	public String toString(){
	return "At [aT_id=" +aT_id+",accessToken=\"" +accessToken+"\",genTime=\"" + StrUtil.Timesamp2String(genTime)+"\""+"]"; 
	} 
}

