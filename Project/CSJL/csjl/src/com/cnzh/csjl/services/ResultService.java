package com.cnzh.csjl.services;

import com.cnzh.csjl.util.ResultUtil;
import com.cnzh.csjl.entity.*;

   /**
    * ResultService
    * 由chenp 的CSMMAACToolv4.0.5生成
    *服务接口。
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 
	//CSMMAACToolv4.0.5自动生成
public interface ResultService {
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil addResult(Integer quiltCount,Integer nightWakeCount);
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil updateResult(Long result_Id,Integer quiltCount,Integer nightWakeCount);

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getResultById(long result_Id);

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getAll();

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil deleteResultById(long result_Id);
     /* 通过参数得到记录*
     * 
	//CSMMAACToolv4.0.5自动生成
     * @return 结果类
     */
	public ResultUtil selectResult(Result result,Integer pageNow, Integer pageSize,String orderByCase);}

