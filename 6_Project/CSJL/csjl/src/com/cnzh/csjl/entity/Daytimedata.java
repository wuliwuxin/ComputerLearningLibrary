package com.cnzh.csjl.entity;

import java.sql.*;
import com.cnzh.csjl.util.StrUtil;
import java.util.HashMap;
import java.util.*;
import java.io.Serializable;
   /**
    * Daytimedata
    * 由chenp 的CSMMAACToolv4.0.5生成
    *实体
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 


public class Daytimedata implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long daytimeData_Id;//白天数据表ID
	private Long equipment_Id;//设备ID
	private String imageName;//图像名
	private Float indoorTemperature;//室温
	private Float bodyTemperature;//体温
	private Object acquisitionTime;//采集时间
	private Timestamp genTime;//导入时间
	private Equipment equipment;
	/**
	* 获取 白天数据表ID
	* @return  daytimeData_Id 白天数据表ID
	* 由CSMMAACToolv4.0.5生成
	*/
	public Long getDaytimeData_Id(){
		return daytimeData_Id;
	}
	/**
	* 设置 白天数据表ID
	* @param  daytimeData_Id 白天数据表ID
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setDaytimeData_Id(Long daytimeData_Id){
	this.daytimeData_Id=daytimeData_Id;
	}
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
	* 获取 图像名
	* @return  imageName 图像名
	* 由CSMMAACToolv4.0.5生成
	*/
	public String getImageName(){
		return imageName;
	}
	/**
	* 设置 图像名
	* @param  imageName 图像名
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setImageName(String imageName){
	this.imageName=imageName;
	}
	/**
	* 获取 室温
	* @return  indoorTemperature 室温
	* 由CSMMAACToolv4.0.5生成
	*/
	public Float getIndoorTemperature(){
		return indoorTemperature;
	}
	/**
	* 设置 室温
	* @param  indoorTemperature 室温
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setIndoorTemperature(Float indoorTemperature){
	this.indoorTemperature=indoorTemperature;
	}
	/**
	* 获取 体温
	* @return  bodyTemperature 体温
	* 由CSMMAACToolv4.0.5生成
	*/
	public Float getBodyTemperature(){
		return bodyTemperature;
	}
	/**
	* 设置 体温
	* @param  bodyTemperature 体温
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setBodyTemperature(Float bodyTemperature){
	this.bodyTemperature=bodyTemperature;
	}
	/**
	* 获取 采集时间
	* @return  acquisitionTime 采集时间
	* 由CSMMAACToolv4.0.5生成
	*/
	public Object getAcquisitionTime(){
		return acquisitionTime;
	}
	/**
	* 设置 采集时间
	* @param  acquisitionTime 采集时间
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setAcquisitionTime(Object acquisitionTime){
	this.acquisitionTime=acquisitionTime;
	}
	/**
	* 获取 导入时间
	* @return  genTime 导入时间
	* 由CSMMAACToolv4.0.5生成
	*/
	public Timestamp getGenTime(){
		return genTime;
	}
	/**
	* 设置 导入时间
	* @param  genTime 导入时间
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setGenTime(Timestamp genTime){
	this.genTime=genTime;
	}
	/**
	* 获取字符格式导入时间
	* @return  genTime 导入时间
	* 由CSMMAACToolv4.0.5生成
	*/
	public String getGenTimeString(){
		return  StrUtil.Timesamp2String(genTime);
	}
	public void setEquipment(Equipment equipment){
	this.equipment=equipment;
	}
	public Equipment getEquipment(){
		return equipment;
	}
/**
	 * 转换为hashmap
	 * 由CSMMAACToolv4.0.5 生成
	 * @return
	 */
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != daytimeData_Id) {
			map.put("daytimeData_Id", daytimeData_Id);
		}
		if (null != equipment_Id) {
			map.put("equipment_Id", equipment_Id);
		}
		if (null != imageName) {
			map.put("imageName", imageName);
		}
		if (null != indoorTemperature) {
			map.put("indoorTemperature", indoorTemperature);
		}
		if (null != bodyTemperature) {
			map.put("bodyTemperature", bodyTemperature);
		}
		if (null != acquisitionTime) {
			map.put("acquisitionTime", acquisitionTime);
		}
		if (null != genTime) {
			map.put("genTime", genTime);
		}
		return map;
		}
	@Override //By生成工具v4.0.5
	public String toString(){
	return "Daytimedata [daytimeData_Id=" +daytimeData_Id+",equipment_Id=" +equipment_Id+",imageName=\"" +imageName+"\",indoorTemperature=" +indoorTemperature+",bodyTemperature=" +bodyTemperature+",acquisitionTime=" +acquisitionTime+",genTime=\"" + StrUtil.Timesamp2String(genTime)+"\",Equipment="  +equipment+""+"]"; 
	} 
}

