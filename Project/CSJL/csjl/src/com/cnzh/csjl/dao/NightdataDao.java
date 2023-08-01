package com.cnzh.csjl.dao;

import java.util.List;
import java.util.*;
import com.cnzh.csjl.entity.Nightdata;
   /**
    * NightdataDao
    * 由chenp 的CSMMAACToolv4.0.5生成
    *DAO(数据访问对象)
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 


public interface NightdataDao{
	public int save(Nightdata nightdata);
	public Nightdata findById(long nightData_Id);
	public List<Nightdata> findByEquipmentId(long equipment_Id);
	public List<Nightdata> findAll();
	public List<Nightdata> dynamicSelect(Map<String,Object> map);
	public int dynamicUpdate(Nightdata nightdata);
	public long getNightdataCount(Nightdata nightdata);
	public int deleteById(long priId);
	public Nightdata  findUnionByequipment_Id(long equipment_Id);
}

