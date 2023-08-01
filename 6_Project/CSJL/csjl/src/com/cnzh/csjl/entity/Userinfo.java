package com.cnzh.csjl.entity;

import java.sql.*;
import java.util.List;
import com.cnzh.csjl.util.StrUtil;
import java.util.HashMap;
import java.util.*;
import java.io.Serializable;
   /**
    * Userinfo
    * 由chenp 的CSMMAACToolv4.0.5生成
    *实体
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 


public class Userinfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long userInfo_Id;//用户详细信息ID
	private String reallyName;//姓名
	private Boolean sex;//性别
	private Boolean babySex;//小孩性别
	private Object baByBirthday;//小孩出生日期
	private Integer baByWeight;//小孩体重
	private Long equipment_ID;//绑定的设备ID
	private Timestamp genTime;//创建时间
	private Timestamp editTime;//修改时间
	private List<User> userlist;
	/**
	* 获取 用户详细信息ID
	* @return  userInfo_Id 用户详细信息ID
	* 由CSMMAACToolv4.0.5生成
	*/
	public Long getUserInfo_Id(){
		return userInfo_Id;
	}
	/**
	* 设置 用户详细信息ID
	* @param  userInfo_Id 用户详细信息ID
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setUserInfo_Id(Long userInfo_Id){
	this.userInfo_Id=userInfo_Id;
	}
	/**
	* 获取 姓名
	* @return  reallyName 姓名
	* 由CSMMAACToolv4.0.5生成
	*/
	public String getReallyName(){
		return reallyName;
	}
	/**
	* 设置 姓名
	* @param  reallyName 姓名
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setReallyName(String reallyName){
	this.reallyName=reallyName;
	}
	/**
	* 获取 性别
	* @return  sex 性别
	* 由CSMMAACToolv4.0.5生成
	*/
	public Boolean getSex(){
		return sex;
	}
	/**
	* 设置 性别
	* @param  sex 性别
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setSex(Boolean sex){
	this.sex=sex;
	}
	/**
	* 获取 小孩性别
	* @return  babySex 小孩性别
	* 由CSMMAACToolv4.0.5生成
	*/
	public Boolean getBabySex(){
		return babySex;
	}
	/**
	* 设置 小孩性别
	* @param  babySex 小孩性别
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setBabySex(Boolean babySex){
	this.babySex=babySex;
	}
	/**
	* 获取 小孩出生日期
	* @return  baByBirthday 小孩出生日期
	* 由CSMMAACToolv4.0.5生成
	*/
	public Object getBaByBirthday(){
		return baByBirthday;
	}
	/**
	* 设置 小孩出生日期
	* @param  baByBirthday 小孩出生日期
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setBaByBirthday(Object baByBirthday){
	this.baByBirthday=baByBirthday;
	}
	/**
	* 获取 小孩体重
	* @return  baByWeight 小孩体重
	* 由CSMMAACToolv4.0.5生成
	*/
	public Integer getBaByWeight(){
		return baByWeight;
	}
	/**
	* 设置 小孩体重
	* @param  baByWeight 小孩体重
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setBaByWeight(Integer baByWeight){
	this.baByWeight=baByWeight;
	}
	/**
	* 获取 绑定的设备ID
	* @return  equipment_ID 绑定的设备ID
	* 由CSMMAACToolv4.0.5生成
	*/
	public Long getEquipment_ID(){
		return equipment_ID;
	}
	/**
	* 设置 绑定的设备ID
	* @param  equipment_ID 绑定的设备ID
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setEquipment_ID(Long equipment_ID){
	this.equipment_ID=equipment_ID;
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
	* 获取 修改时间
	* @return  editTime 修改时间
	* 由CSMMAACToolv4.0.5生成
	*/
	public Timestamp getEditTime(){
		return editTime;
	}
	/**
	* 设置 修改时间
	* @param  editTime 修改时间
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setEditTime(Timestamp editTime){
	this.editTime=editTime;
	}
	/**
	* 获取字符格式修改时间
	* @return  editTime 修改时间
	* 由CSMMAACToolv4.0.5生成
	*/
	public String getEditTimeString(){
		return  StrUtil.Timesamp2String(editTime);
	}
	public void setUser(List<User> userlist){
	this.userlist=userlist;
	}
	public List<User> getUserlist(){
		return userlist;
	}
/**
	 * 转换为hashmap
	 * 由CSMMAACToolv4.0.5 生成
	 * @return
	 */
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != userInfo_Id) {
			map.put("userInfo_Id", userInfo_Id);
		}
		if (null != reallyName) {
			map.put("reallyName", reallyName);
		}
		if (null != sex) {
			map.put("sex", sex);
		}
		if (null != babySex) {
			map.put("babySex", babySex);
		}
		if (null != baByBirthday) {
			map.put("baByBirthday", baByBirthday);
		}
		if (null != baByWeight) {
			map.put("baByWeight", baByWeight);
		}
		if (null != equipment_ID) {
			map.put("equipment_ID", equipment_ID);
		}
		if (null != genTime) {
			map.put("genTime", genTime);
		}
		if (null != editTime) {
			map.put("editTime", editTime);
		}
		return map;
		}
	@Override //By生成工具v4.0.5
	public String toString(){
	return "Userinfo [userInfo_Id=" +userInfo_Id+",reallyName=\"" +reallyName+"\",sex=" +sex+",babySex=" +babySex+",baByBirthday=" +baByBirthday+",baByWeight=" +baByWeight+",equipment_ID=" +equipment_ID+",genTime=\"" + StrUtil.Timesamp2String(genTime)+"\",editTime=\"" + StrUtil.Timesamp2String(editTime)+"\",Userlist=" +userlist+""+"]"; 
	} 
}

