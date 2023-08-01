package com.cnzh.csjl.services;

import com.cnzh.csjl.util.ResultUtil;
import com.cnzh.csjl.entity.*;

   /**
    * NightdataService
    * 由chenp 的CSMMAACToolv4.0.5生成
    *服务接口。
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 
	//CSMMAACToolv4.0.5自动生成
public interface NightdataService {
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil addNightdata(Long equipment_Id,String imageName,Float indoorTemperature,Float bodyTemperature);
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil updateNightdata(Long nightData_Id,Long equipment_Id,String imageName,Float indoorTemperature,Float bodyTemperature);

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getNightdataById(long nightData_Id);
	
	//Roger
	public ResultUtil getNightdataByequipmentId(long equipment_Id);

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getAll();

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil deleteNightdataById(long nightData_Id);
     /* 通过参数得到记录*
     * 
	//CSMMAACToolv4.0.5自动生成
     * @return 结果类
     */
	public ResultUtil selectNightdata(Nightdata nightdata,Integer pageNow, Integer pageSize,String orderByCase);}

