package com.cnzh.csjl.services;

import com.cnzh.csjl.util.ResultUtil;
import com.cnzh.csjl.entity.*;

   /**
    * DaytimedataService
    * 由chenp 的CSMMAACToolv4.0.5生成
    *服务接口。
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 
	//CSMMAACToolv4.0.5自动生成
public interface DaytimedataService {
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil addDaytimedata(Long equipment_Id,String imageName,Float indoorTemperature,Float bodyTemperature,Object acquisitionTime);
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil updateDaytimedata(Long daytimeData_Id,Long equipment_Id,String imageName,Float indoorTemperature,Float bodyTemperature,Object acquisitionTime);

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getDaytimedataById(long daytimeData_Id);
	
	//Roger
	public ResultUtil getDaytimedataByequipmentId(long equipment_Id);

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getAll();

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil deleteDaytimedataById(long daytimeData_Id);
     /* 通过参数得到记录*
     * 
	//CSMMAACToolv4.0.5自动生成
     * @return 结果类
     */
	public ResultUtil selectDaytimedata(Daytimedata daytimedata,Integer pageNow, Integer pageSize,String orderByCase);}

