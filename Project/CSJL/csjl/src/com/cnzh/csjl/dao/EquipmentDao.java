package com.cnzh.csjl.dao;

import java.util.List;
import java.util.*;
import com.cnzh.csjl.entity.User;
import com.cnzh.csjl.entity.Nightdata;
import com.cnzh.csjl.entity.Daytimedata;
import com.cnzh.csjl.entity.Equipment;
   /**
    * EquipmentDao
    * 由chenp 的CSMMAACToolv4.0.5生成
    *DAO(数据访问对象)
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 


public interface EquipmentDao{
	public int save(Equipment equipment);
	public Equipment findById(long equipment_Id);
	public List<Equipment> findAll();
	public List<Equipment> dynamicSelect(Map<String,Object> map);
	public int dynamicUpdate(Equipment equipment);
	public long getEquipmentCount(Equipment equipment);
	public int deleteById(long priId);
	public List<Daytimedata>  findUnionByDaytimedata_Id(long daytimeData_Id);
	public List<Nightdata>  findUnionByNightdata_Id(long nightData_Id);
	public List<User>  findUnionByUser_Id(long user_Id);
}

