package com.cnzh.csjl.entity;

import java.sql.*;
import com.cnzh.csjl.util.StrUtil;
import java.util.HashMap;
import java.util.*;
import java.io.Serializable;
   /**
    * User
    * 由chenp 的CSMMAACToolv4.0.5生成
    *实体
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Tue Mar 20 20:41:18 CST 2018 By chenp
    */ 


public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long user_Id;//用户ID
	private Long userInfo_Id;//用户详细信息ID
	private Long equipment_Id;//设备ID
	private Long result_Id;//结果表ID
	private String userName;//用户名
	private String passWord;//密码
	private Integer phoneNumber;//手机号
	private String email;//邮箱
	private String avatar;//头像
	private String qQId;//QQID
	private String weiBoId;//微博ID
	private String weChatId;//微信ID
	private Timestamp genTime;//创建时间
	private Timestamp editTime;//修改时间
	private Equipment equipment;
	private Result result;
	private Userinfo userinfo;
	/**
	* 获取 用户ID
	* @return  user_Id 用户ID
	* 由CSMMAACToolv4.0.5生成
	*/
	public Long getUser_Id(){
		return user_Id;
	}
	/**
	* 设置 用户ID
	* @param  user_Id 用户ID
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setUser_Id(Long user_Id){
	this.user_Id=user_Id;
	}
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
	* 获取 用户名
	* @return  userName 用户名
	* 由CSMMAACToolv4.0.5生成
	*/
	public String getUserName(){
		return userName;
	}
	/**
	* 设置 用户名
	* @param  userName 用户名
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setUserName(String userName){
	this.userName=userName;
	}
	/**
	* 获取 密码
	* @return  passWord 密码
	* 由CSMMAACToolv4.0.5生成
	*/
	public String getPassWord(){
		return passWord;
	}
	/**
	* 设置 密码
	* @param  passWord 密码
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setPassWord(String passWord){
	this.passWord=passWord;
	}
	/**
	* 获取 手机号
	* @return  phoneNumber 手机号
	* 由CSMMAACToolv4.0.5生成
	*/
	public Integer getPhoneNumber(){
		return phoneNumber;
	}
	/**
	* 设置 手机号
	* @param  phoneNumber 手机号
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setPhoneNumber(Integer phoneNumber){
	this.phoneNumber=phoneNumber;
	}
	/**
	* 获取 邮箱
	* @return  email 邮箱
	* 由CSMMAACToolv4.0.5生成
	*/
	public String getEmail(){
		return email;
	}
	/**
	* 设置 邮箱
	* @param  email 邮箱
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setEmail(String email){
	this.email=email;
	}
	/**
	* 获取 头像
	* @return  avatar 头像
	* 由CSMMAACToolv4.0.5生成
	*/
	public String getAvatar(){
		return avatar;
	}
	/**
	* 设置 头像
	* @param  avatar 头像
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setAvatar(String avatar){
	this.avatar=avatar;
	}
	/**
	* 获取 QQID
	* @return  qQId QQID
	* 由CSMMAACToolv4.0.5生成
	*/
	public String getQQId(){
		return qQId;
	}
	/**
	* 设置 QQID
	* @param  qQId QQID
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setQQId(String qQId){
	this.qQId=qQId;
	}
	/**
	* 获取 微博ID
	* @return  weiBoId 微博ID
	* 由CSMMAACToolv4.0.5生成
	*/
	public String getWeiBoId(){
		return weiBoId;
	}
	/**
	* 设置 微博ID
	* @param  weiBoId 微博ID
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setWeiBoId(String weiBoId){
	this.weiBoId=weiBoId;
	}
	/**
	* 获取 微信ID
	* @return  weChatId 微信ID
	* 由CSMMAACToolv4.0.5生成
	*/
	public String getWeChatId(){
		return weChatId;
	}
	/**
	* 设置 微信ID
	* @param  weChatId 微信ID
	* 由CSMMAACToolv4.0.5生成
	*/
	public void setWeChatId(String weChatId){
	this.weChatId=weChatId;
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
	public void setEquipment(Equipment equipment){
	this.equipment=equipment;
	}
	public Equipment getEquipment(){
		return equipment;
	}
	public void setResult(Result result){
	this.result=result;
	}
	public Result getResult(){
		return result;
	}
	public void setUserinfo(Userinfo userinfo){
	this.userinfo=userinfo;
	}
	public Userinfo getUserinfo(){
		return userinfo;
	}
/**
	 * 转换为hashmap
	 * 由CSMMAACToolv4.0.5 生成
	 * @return
	 */
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != user_Id) {
			map.put("user_Id", user_Id);
		}
		if (null != userInfo_Id) {
			map.put("userInfo_Id", userInfo_Id);
		}
		if (null != equipment_Id) {
			map.put("equipment_Id", equipment_Id);
		}
		if (null != result_Id) {
			map.put("result_Id", result_Id);
		}
		if (null != userName) {
			map.put("userName", userName);
		}
		if (null != passWord) {
			map.put("passWord", passWord);
		}
		if (null != phoneNumber) {
			map.put("phoneNumber", phoneNumber);
		}
		if (null != email) {
			map.put("email", email);
		}
		if (null != avatar) {
			map.put("avatar", avatar);
		}
		if (null != qQId) {
			map.put("qQId", qQId);
		}
		if (null != weiBoId) {
			map.put("weiBoId", weiBoId);
		}
		if (null != weChatId) {
			map.put("weChatId", weChatId);
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
		return "User [user_Id=" + user_Id + ",userInfo_Id=" + userInfo_Id + ",equipment_Id=" + equipment_Id
				+ ",result_Id=" + result_Id + ",userName=\"" + userName + "\",passWord=\"" + passWord
				+ "\",phoneNumber=" + phoneNumber + ",email=\"" + email + "\",avatar=\"" + avatar + "\",qQId=\"" + qQId
				+ "\",weiBoId=\"" + weiBoId + "\",weChatId=\"" + weChatId + "\",genTime=\""
				+ StrUtil.Timesamp2String(genTime) + "\",editTime=\"" + StrUtil.Timesamp2String(editTime)
				+ "\",Equipment=" + equipment + ",Result=" + result + ",Userinfo=" + userinfo + "" + "]";
	} 
}

