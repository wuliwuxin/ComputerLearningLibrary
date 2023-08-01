package com.cnzh.csjl.dao;

import java.util.List;
import java.util.*;
import com.cnzh.csjl.entity.User;
   /**
    * UserDao
    * 由chenp 的CSMMAACToolv4.0.5生成
    *DAO(数据访问对象)
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Mar 10 12:11:55 CST 2018 By chenp
    */ 


public interface UserDao{
	public int save(User user);
	public User findById(long user_Id);
	public User findByuserName(String userName);
	public User findByphoneNumber(Integer phoneNumber);
	public User findByemail(String email);
	public List<User> findAll();
	public List<User> dynamicSelect(Map<String,Object> map);
	public int dynamicUpdate(User user);
	public long getUserCount(User user);
	public int deleteById(long priId);
	public User  findUnionByequipment_Id(long equipment_Id);
	public User  findUnionByresult_Id(long result_Id);
	public User  findUnionByuserInfo_Id(long userInfo_Id);
}

