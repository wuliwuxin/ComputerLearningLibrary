package com.cnzh.csjl.dao;

import java.util.List;
import java.util.*;
import com.cnzh.csjl.entity.User;
import com.cnzh.csjl.entity.Result;
   /**
    * ResultDao
    * 由chenp 的CSMMAACToolv4.0.5生成
    *DAO(数据访问对象)
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 


public interface ResultDao{
	public int save(Result result);
	public Result findById(long result_Id);
	public List<Result> findAll();
	public List<Result> dynamicSelect(Map<String,Object> map);
	public int dynamicUpdate(Result result);
	public long getResultCount(Result result);
	public int deleteById(long priId);
	public List<User>  findUnionByUser_Id(long user_Id);
}

