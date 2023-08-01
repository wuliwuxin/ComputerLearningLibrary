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
    * NightdataImplement
    * 由chenp 的CSMMAACToolv4.0.5生成
    *Services 实现类。
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 

@Service
public class NightdataImplement implements NightdataService {

	@Resource
	private NightdataDao nightdataDao;

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil addNightdata(Long equipment_Id,String imageName,Float indoorTemperature,Float bodyTemperature) {
		ResultUtil apiresult = new ResultUtil();
     Nightdata nightdata = new Nightdata();	       nightdata.setEquipment_Id(equipment_Id);
	       nightdata.setImageName(imageName);
	       nightdata.setIndoorTemperature(indoorTemperature);
	       nightdata.setBodyTemperature(bodyTemperature);
		  try {
        int res =nightdataDao.save(nightdata);
		   apiresult.setStatus(res);
        apiresult.setMsg(res==1?"成功":"失败");
         apiresult.setData(res==1?nightdata:"");
		  }catch (Exception e) {
       e.printStackTrace();
			apiresult.setMsg("saveNightdata出错！");
		}  		return apiresult;
	}

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil updateNightdata(Long nightData_Id,Long equipment_Id,String imageName,Float indoorTemperature,Float bodyTemperature) {
		ResultUtil apiresult = new ResultUtil();
     Nightdata nightdata = new Nightdata();	       nightdata.setNightData_Id(nightData_Id);
	       nightdata.setEquipment_Id(equipment_Id);
	       nightdata.setImageName(imageName);
	       nightdata.setIndoorTemperature(indoorTemperature);
	       nightdata.setBodyTemperature(bodyTemperature);
		  try {
        int res =nightdataDao.dynamicUpdate(nightdata);
		   apiresult.setStatus(res);
        apiresult.setMsg(res==1?"成功":"失败");
         apiresult.setData(res==1?nightdata:"");
		  }catch (Exception e) {
       e.printStackTrace();
			apiresult.setMsg("dynamicUpdateNightdata出错！");
		}  		return apiresult;
	}

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getNightdataById(long nightData_Id) {
		ResultUtil apiresult = new ResultUtil();
		Nightdata nightdata=nightdataDao.findById(nightData_Id);
		apiresult.setData(nightdata);    
		apiresult.setStatus(nightdata!=null?1:0);
		apiresult.setMsg(nightdata!=null?"成功":"未能获取id为"+nightData_Id+"的数据。");
		return apiresult;
	}
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getAll() {
		ResultUtil apiresult = new ResultUtil();
		List<Nightdata> nightdata=nightdataDao.findAll();
		apiresult.setData(nightdata);    
		apiresult.setStatus(nightdata!=null?1:0);
		apiresult.setMsg(nightdata!=null?"成功":"未能获取数据。");
		return apiresult;
	}
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil deleteNightdataById(long nightData_Id) {
		ResultUtil apiresult = new ResultUtil();
  	 try {
		    apiresult.setStatus( nightdataDao.deleteById(nightData_Id));
		    apiresult.setMsg( apiresult.getStatus()==1?"删除成功！":"删除失败！");
		  }catch (Exception e) {
       e.printStackTrace();
			apiresult.setMsg("删除Nightdata出错！");
		}		return apiresult;
	}
public ResultUtil selectNightdata(Nightdata nightdata,Integer pageNow, Integer pageSize,String orderByCase) {
		ResultUtil apiresult = new ResultUtil();
 Map<String,Object> map=nightdata.toMap();
	       if(null!=pageNow&&null!=pageSize){
	       map.put("startPos", (pageNow-1)*pageSize);
	       map.put("pageSize", pageSize);
	       }
           if(null!=orderByCase)
	       map.put("orderByCase", orderByCase);
try {
			List<Nightdata> list = nightdataDao.dynamicSelect(map);
			long count = nightdataDao.getNightdataCount(nightdata);
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
			apiresult.setMsg("dynamic查询Nightdata出错！");
		}
		return apiresult;       }

//Roger
public ResultUtil getNightdataByequipmentId(long equipment_Id) {
	ResultUtil apiresult = new ResultUtil();
	List<Nightdata> nightdata=nightdataDao.findByEquipmentId(equipment_Id);
	apiresult.setData(nightdata);    
	apiresult.setStatus(nightdata!=null?1:0);
	apiresult.setMsg(nightdata!=null?"成功":"未能获取id为"+equipment_Id+"的数据。");
	return apiresult;
}
}

