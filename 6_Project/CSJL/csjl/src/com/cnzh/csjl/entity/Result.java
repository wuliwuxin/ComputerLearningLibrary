package com.cnzh.csjl.entity;

import java.sql.*;
import java.util.List;
import com.cnzh.csjl.util.StrUtil;
import java.util.HashMap;
import java.util.*;
import java.io.Serializable;
   /**
    * Result
    * 由chenp 的CSMMAACToolv4.0.5生成
    *实体
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 


public class Result implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long result_Id;//结果表ID
	private Integer quiltCount;//踢被子次数
	private Integer nightWakeCount;//夜间醒来次数
	private Timestamp genTime;//创建时间
	private Timestamp editTime;//修改时间
	private List<User> userlist;
	/**
	* 获取 结果表ID
	* @return  result_Id 结果表ID
	* 由CSMMAACToolv4.0.5生成
	*/
	public Long getResult_Id(){
		return result_Id;
	}
	/**
	* 设置 结果表ID
	* @param  result_Id 结果表ID
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setResult_Id(Long result_Id){
	this.result_Id=result_Id;
	}
	/**
	* 获取 踢被子次数
	* @return  quiltCount 踢被子次数
	* 由CSMMAACToolv4.0.5生成
	*/
	public Integer getQuiltCount(){
		return quiltCount;
	}
	/**
	* 设置 踢被子次数
	* @param  quiltCount 踢被子次数
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setQuiltCount(Integer quiltCount){
	this.quiltCount=quiltCount;
	}
	/**
	* 获取 夜间醒来次数
	* @return  nightWakeCount 夜间醒来次数
	* 由CSMMAACToolv4.0.5生成
	*/
	public Integer getNightWakeCount(){
		return nightWakeCount;
	}
	/**
	* 设置 夜间醒来次数
	* @param  nightWakeCount 夜间醒来次数
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setNightWakeCount(Integer nightWakeCount){
	this.nightWakeCount=nightWakeCount;
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
		if (null != result_Id) {
			map.put("result_Id", result_Id);
		}
		if (null != quiltCount) {
			map.put("quiltCount", quiltCount);
		}
		if (null != nightWakeCount) {
			map.put("nightWakeCount", nightWakeCount);
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
	return "Result [result_Id=" +result_Id+",quiltCount=" +quiltCount+",nightWakeCount=" +nightWakeCount+",genTime=\"" + StrUtil.Timesamp2String(genTime)+"\",editTime=\"" + StrUtil.Timesamp2String(editTime)+"\",Userlist=" +userlist+""+"]"; 
	} 
}

