package com.cnzh.csjl.services;

import com.cnzh.csjl.util.ResultUtil;
import com.cnzh.csjl.entity.*;

   /**
    * AtService
    * 由chenp 的CSMMAACToolv4.0.5生成
    *服务接口。
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 
	//CSMMAACToolv4.0.5自动生成
public interface AtService {
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil addAt(String accessToken);
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil updateAt(Long aT_id,String accessToken);

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getAtById(long aT_id);

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getAll();

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil deleteAtById(long aT_id);
     /* 通过参数得到记录*
     * 
	//CSMMAACToolv4.0.5自动生成
     * @return 结果类
     */
	public ResultUtil selectAt(At at,Integer pageNow, Integer pageSize,String orderByCase);}

