package com.cnzh.csjl.services.implement;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnzh.csjl.dao.*;
import com.cnzh.csjl.entity.*;
import java.util.*;
import com.cnzh.csjl.util.Page;
import com.cnzh.csjl.services.*;
import java.util.List;
import com.cnzh.csjl.util.ResultUtil;
   /**
    * EquipmentImplement
    * 由chenp 的CSMMAACToolv4.0.5生成
    *Services 实现类。
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 

@Service
public class EquipmentImplement implements EquipmentService {

	@Resource
	private EquipmentDao equipmentDao;

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil addEquipment(String equipmentName) {
		ResultUtil apiresult = new ResultUtil();
     Equipment equipment = new Equipment();	       equipment.setEquipmentName(equipmentName);
		  try {
        int res =equipmentDao.save(equipment);
		   apiresult.setStatus(res);
        apiresult.setMsg(res==1?"成功":"失败");
         apiresult.setData(res==1?equipment:"");
		  }catch (Exception e) {
       e.printStackTrace();
			apiresult.setMsg("saveEquipment出错！");
		}  		return apiresult;
	}

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil updateEquipment(Long equipment_Id,String equipmentName) {
		ResultUtil apiresult = new ResultUtil();
     Equipment equipment = new Equipment();	       equipment.setEquipment_Id(equipment_Id);
	       equipment.setEquipmentName(equipmentName);
		  try {
        int res =equipmentDao.dynamicUpdate(equipment);
		   apiresult.setStatus(res);
        apiresult.setMsg(res==1?"成功":"失败");
         apiresult.setData(res==1?equipment:"");
		  }catch (Exception e) {
       e.printStackTrace();
			apiresult.setMsg("dynamicUpdateEquipment出错！");
		}  		return apiresult;
	}

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getEquipmentById(long equipment_Id) {
		ResultUtil apiresult = new ResultUtil();
		Equipment equipment=equipmentDao.findById(equipment_Id);
		apiresult.setData(equipment);    
		apiresult.setStatus(equipment!=null?1:0);
		apiresult.setMsg(equipment!=null?"成功":"未能获取id为"+equipment_Id+"的数据。");
		return apiresult;
	}
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getAll() {
		ResultUtil apiresult = new ResultUtil();
		List<Equipment> equipment=equipmentDao.findAll();
		apiresult.setData(equipment);    
		apiresult.setStatus(equipment!=null?1:0);
		apiresult.setMsg(equipment!=null?"成功":"未能获取数据。");
		return apiresult;
	}
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil deleteEquipmentById(long equipment_Id) {
		ResultUtil apiresult = new ResultUtil();
  	 try {
		    apiresult.setStatus( equipmentDao.deleteById(equipment_Id));
		    apiresult.setMsg( apiresult.getStatus()==1?"删除成功！":"删除失败！");
		  }catch (Exception e) {
       e.printStackTrace();
			apiresult.setMsg("删除Equipment出错！");
		}		return apiresult;
	}
public ResultUtil selectEquipment(Equipment equipment,Integer pageNow, Integer pageSize,String orderByCase) {
		ResultUtil apiresult = new ResultUtil();
 Map<String,Object> map=equipment.toMap();
	       if(null!=pageNow&&null!=pageSize){
	       map.put("startPos", (pageNow-1)*pageSize);
	       map.put("pageSize", pageSize);
	       }
           if(null!=orderByCase)
	       map.put("orderByCase", orderByCase);
try {
			List<Equipment> list = equipmentDao.dynamicSelect(map);
			long count = equipmentDao.getEquipmentCount(equipment);
			pageSize=null!=pageSize?pageSize:(int)(count&0xffffffff);
			pageNow=null!=pageNow?pageNow:1;
			Page page=new Page(count, pageNow);
			     page.setPageSize(pageSize);
			apiresult.setPage(page);
			apiresult.setStatus(list.size()&1);
			apiresult.setMsg(list.size() > 0 ? "成功" : "失败");
			apiresult.setData(list.size() > 0 ? list : "");
		} catch (Exception e) {
			e.printStackTrace();
			apiresult.setMsg("dynamic查询Equipment出错！");
		}
		return apiresult;       }
}

