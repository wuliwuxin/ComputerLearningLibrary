package com.cnzh.csjl.dao;

import java.util.List;
import java.util.*;
import com.cnzh.csjl.entity.Daytimedata;
   /**
    * DaytimedataDao
    * 由chenp 的CSMMAACToolv4.0.5生成
    *DAO(数据访问对象)
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 


public interface DaytimedataDao{
	public int save(Daytimedata daytimedata);
	public Daytimedata findById(long daytimeData_Id);
	public List<Daytimedata> findByEquipmentId(long equipment_Id);
	public List<Daytimedata> findAll();
	public List<Daytimedata> dynamicSelect(Map<String,Object> map);
	public int dynamicUpdate(Daytimedata daytimedata);
	public long getDaytimedataCount(Daytimedata daytimedata);
	public int deleteById(long priId);
	public Daytimedata  findUnionByequipment_Id(long equipment_Id);
}

