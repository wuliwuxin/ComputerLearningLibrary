package com.cnzh.csjl.dao;

import java.util.List;
import java.util.*;
import com.cnzh.csjl.entity.At;
   /**
    * AtDao
    * 由chenp 的CSMMAACToolv4.0.5生成
    *DAO(数据访问对象)
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 


public interface AtDao{
	public int save(At at);
	public At findById(long aT_id);
	public List<At> findAll();
	public List<At> dynamicSelect(Map<String,Object> map);
	public int dynamicUpdate(At at);
	public long getAtCount(At at);
	public int deleteById(long priId);
}

