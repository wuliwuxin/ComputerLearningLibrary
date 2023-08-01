package com.cnzh.csjl.entity;

import java.util.List;
import java.util.HashMap;
import java.util.*;
import java.io.Serializable;
   /**
    * Equipment
    * 由chenp 的CSMMAACToolv4.0.5生成
    *实体
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 


public class Equipment implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long equipment_Id;//设备ID
	private String equipmentName;//设备名
	private List<Daytimedata> daytimedatalist;
	private List<Nightdata> nightdatalist;
	private List<User> userlist;
	/**
	* 获取 设备ID
	* @return  equipment_Id 设备ID
	* 由CSMMAACToolv4.0.5生成
	*/
	public Long getEquipment_Id(){
		return equipment_Id;
	}
	/**
	* 设置 设备ID
	* @param  equipment_Id 设备ID
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setEquipment_Id(Long equipment_Id){
	this.equipment_Id=equipment_Id;
	}
	/**
	* 获取 设备名
	* @return  equipmentName 设备名
	* 由CSMMAACToolv4.0.5生成
	*/
	public String getEquipmentName(){
		return equipmentName;
	}
	/**
	* 设置 设备名
	* @param  equipmentName 设备名
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setEquipmentName(String equipmentName){
	this.equipmentName=equipmentName;
	}
	public void setDaytimedata(List<Daytimedata> daytimedatalist){
	this.daytimedatalist=daytimedatalist;
	}
	public List<Daytimedata> getDaytimedatalist(){
		return daytimedatalist;
	}
	public void setNightdata(List<Nightdata> nightdatalist){
	this.nightdatalist=nightdatalist;
	}
	public List<Nightdata> getNightdatalist(){
		return nightdatalist;
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
		if (null != equipment_Id) {
			map.put("equipment_Id", equipment_Id);
		}
		if (null != equipmentName) {
			map.put("equipmentName", equipmentName);
		}
		return map;
		}
	@Override //By生成工具v4.0.5
	public String toString(){
	return "Equipment [equipment_Id=" +equipment_Id+",equipmentName=\"" +equipmentName+"\",Daytimedatalist=" +daytimedatalist+",Nightdatalist=" +nightdatalist+",Userlist=" +userlist+""+"]"; 
	} 
}

