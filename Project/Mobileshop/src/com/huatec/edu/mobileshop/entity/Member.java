package com.huatec.edu.mobileshop.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Member implements Serializable {
	private Integer member_id;//编号
	private String uname;//姓名
	private String password;//密码
	private String email;//邮箱
	private Integer sex;//性别，男：0，女：1
	private String mobile;//手机号
	private Timestamp regtime;//注册时间
	private Timestamp lastlogin;//最后登录时间
	private String image;//头像
	
	//关联属性，因为一个会员有多个会员地址，所以是list类型
	private List<MemberAddress> memberAddresses;
	//get、set方法
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Timestamp getRegtime() {
		return regtime;
	}
	public void setRegtime(Timestamp regtime) {
		this.regtime = regtime;
	}
	public Timestamp getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(Timestamp lastlogin) {
		this.lastlogin = lastlogin;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public List<MemberAddress> getMemberAddresses() {
		return memberAddresses;
	}
	public void setMemberAddresses(List<MemberAddress> memberAddresses) {
		this.memberAddresses = memberAddresses;
	}
	//toStirng方法
	public String toString() {
		return "Member [member_id=" + member_id + ", uname=" + uname + ", password=" + password + ", email=" + email
				+ ", sex=" + sex + ", mobile=" + mobile + ", regtime=" + regtime + ", lastlogin=" + lastlogin
				+ ", image=" + image + "]";
	}
	
}
