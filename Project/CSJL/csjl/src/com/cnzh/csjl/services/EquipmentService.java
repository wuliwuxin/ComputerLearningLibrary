package com.cnzh.csjl.services;

import com.cnzh.csjl.util.ResultUtil;
import com.cnzh.csjl.entity.*;

   /**
    * EquipmentService
    * 由chenp 的CSMMAACToolv4.0.5生成
    *服务接口。
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 
	//CSMMAACToolv4.0.5自动生成
public interface EquipmentService {
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil addEquipment(String equipmentName);
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil updateEquipment(Long equipment_Id,String equipmentName);

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getEquipmentById(long equipment_Id);

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getAll();

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil deleteEquipmentById(long equipment_Id);
     /* 通过参数得到记录*
     * 
	//CSMMAACToolv4.0.5自动生成
     * @return 结果类
     */
	public ResultUtil selectEquipment(Equipment equipment,Integer pageNow, Integer pageSize,String orderByCase);}

