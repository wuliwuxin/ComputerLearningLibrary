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
    * DaytimedataImplement
    * 由chenp 的CSMMAACToolv4.0.5生成
    *Services 实现类。
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 

@Service
public class DaytimedataImplement implements DaytimedataService {

	@Resource
	private DaytimedataDao daytimedataDao;

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil addDaytimedata(Long equipment_Id,String imageName,Float indoorTemperature,Float bodyTemperature,Object acquisitionTime) {
		ResultUtil apiresult = new ResultUtil();
     Daytimedata daytimedata = new Daytimedata();	       daytimedata.setEquipment_Id(equipment_Id);
	       daytimedata.setImageName(imageName);
	       daytimedata.setIndoorTemperature(indoorTemperature);
	       daytimedata.setBodyTemperature(bodyTemperature);
	       daytimedata.setAcquisitionTime(acquisitionTime);
		  try {
        int res =daytimedataDao.save(daytimedata);
		   apiresult.setStatus(res);
        apiresult.setMsg(res==1?"成功":"失败");
         apiresult.setData(res==1?daytimedata:"");
		  }catch (Exception e) {
       e.printStackTrace();
			apiresult.setMsg("saveDaytimedata出错！");
		}  		return apiresult;
	}

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil updateDaytimedata(Long daytimeData_Id,Long equipment_Id,String imageName,Float indoorTemperature,Float bodyTemperature,Object acquisitionTime) {
		ResultUtil apiresult = new ResultUtil();
     Daytimedata daytimedata = new Daytimedata();	       daytimedata.setDaytimeData_Id(daytimeData_Id);
	       daytimedata.setEquipment_Id(equipment_Id);
	       daytimedata.setImageName(imageName);
	       daytimedata.setIndoorTemperature(indoorTemperature);
	       daytimedata.setBodyTemperature(bodyTemperature);
	       daytimedata.setAcquisitionTime(acquisitionTime);
		  try {
        int res =daytimedataDao.dynamicUpdate(daytimedata);
		   apiresult.setStatus(res);
        apiresult.setMsg(res==1?"成功":"失败");
         apiresult.setData(res==1?daytimedata:"");
		  }catch (Exception e) {
       e.printStackTrace();
			apiresult.setMsg("dynamicUpdateDaytimedata出错！");
		}  		return apiresult;
	}

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getDaytimedataById(long daytimeData_Id) {
		ResultUtil apiresult = new ResultUtil();
		Daytimedata daytimedata=daytimedataDao.findById(daytimeData_Id);
		apiresult.setData(daytimedata);    
		apiresult.setStatus(daytimedata!=null?1:0);
		apiresult.setMsg(daytimedata!=null?"成功":"未能获取id为"+daytimeData_Id+"的数据。");
		return apiresult;
	}
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getAll() {
		ResultUtil apiresult = new ResultUtil();
		List<Daytimedata> daytimedata=daytimedataDao.findAll();
		apiresult.setData(daytimedata);    
		apiresult.setStatus(daytimedata!=null?1:0);
		apiresult.setMsg(daytimedata!=null?"成功":"未能获取数据。");
		return apiresult;
	}
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil deleteDaytimedataById(long daytimeData_Id) {
		ResultUtil apiresult = new ResultUtil();
  	 try {
		    apiresult.setStatus( daytimedataDao.deleteById(daytimeData_Id));
		    apiresult.setMsg( apiresult.getStatus()==1?"删除成功！":"删除失败！");
		  }catch (Exception e) {
       e.printStackTrace();
			apiresult.setMsg("删除Daytimedata出错！");
		}		return apiresult;
	}
public ResultUtil selectDaytimedata(Daytimedata daytimedata,Integer pageNow, Integer pageSize,String orderByCase) {
		ResultUtil apiresult = new ResultUtil();
 Map<String,Object> map=daytimedata.toMap();
	       if(null!=pageNow&&null!=pageSize){
	       map.put("startPos", (pageNow-1)*pageSize);
	       map.put("pageSize", pageSize);
	       }
           if(null!=orderByCase)
	       map.put("orderByCase", orderByCase);
try {
			List<Daytimedata> list = daytimedataDao.dynamicSelect(map);
			long count = daytimedataDao.getDaytimedataCount(daytimedata);
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
			apiresult.setMsg("dynamic查询Daytimedata出错！");
		}
		return apiresult;       }

//Roger
public ResultUtil getDaytimedataByequipmentId(long equipment_Id) {
	ResultUtil apiresult = new ResultUtil();
	List<Daytimedata> daytimedata=daytimedataDao.findByEquipmentId(equipment_Id);
	apiresult.setData(daytimedata); 
	apiresult.setStatus(daytimedata!=null?1:0);
	apiresult.setMsg(daytimedata!=null?"成功":"未能获取id为"+equipment_Id+"的数据。");
	return apiresult;
}
}

