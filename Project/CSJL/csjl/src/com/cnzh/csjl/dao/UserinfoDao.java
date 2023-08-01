package com.cnzh.csjl.dao;

import java.util.List;
import java.util.*;
import com.cnzh.csjl.entity.User;
import com.cnzh.csjl.entity.Userinfo;
   /**
    * UserinfoDao
    * 由chenp 的CSMMAACToolv4.0.5生成
    *DAO(数据访问对象)
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 


public interface UserinfoDao{
	public int save(Userinfo userinfo);
	public Userinfo findById(long userInfo_Id);
	public List<Userinfo> findAll();
	public List<Userinfo> dynamicSelect(Map<String,Object> map);
	public int dynamicUpdate(Userinfo userinfo);
	public long getUserinfoCount(Userinfo userinfo);
	public int deleteById(long priId);
	public List<User>  findUnionByUser_Id(long user_Id);
}

