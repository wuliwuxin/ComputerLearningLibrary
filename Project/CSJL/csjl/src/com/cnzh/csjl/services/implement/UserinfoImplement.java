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
    * UserinfoImplement
    * 由chenp 的CSMMAACToolv4.0.5生成
    *Services 实现类。
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 

@Service
public class UserinfoImplement implements UserinfoService {

	@Resource
	private UserinfoDao userinfoDao;

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil addUserinfo(String reallyName,Boolean sex,Boolean babySex,Object baByBirthday,Integer baByWeight,Long equipment_ID) {
		ResultUtil apiresult = new ResultUtil();
     Userinfo userinfo = new Userinfo();	       userinfo.setReallyName(reallyName);
	       userinfo.setSex(sex);
	       userinfo.setBabySex(babySex);
	       userinfo.setBaByBirthday(baByBirthday);
	       userinfo.setBaByWeight(baByWeight);
	       userinfo.setEquipment_ID(equipment_ID);
		  try {
        int res =userinfoDao.save(userinfo);
		   apiresult.setStatus(res);
        apiresult.setMsg(res==1?"成功":"失败");
         apiresult.setData(res==1?userinfo:"");
		  }catch (Exception e) {
       e.printStackTrace();
			apiresult.setMsg("saveUserinfo出错！");
		}  		return apiresult;
	}

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil updateUserinfo(Long userInfo_Id,String reallyName,Boolean sex,Boolean babySex,Object baByBirthday,Integer baByWeight,Long equipment_ID) {
		ResultUtil apiresult = new ResultUtil();
     Userinfo userinfo = new Userinfo();	       userinfo.setUserInfo_Id(userInfo_Id);
	       userinfo.setReallyName(reallyName);
	       userinfo.setSex(sex);
	       userinfo.setBabySex(babySex);
	       userinfo.setBaByBirthday(baByBirthday);
	       userinfo.setBaByWeight(baByWeight);
	       userinfo.setEquipment_ID(equipment_ID);
		  try {
        int res =userinfoDao.dynamicUpdate(userinfo);
		   apiresult.setStatus(res);
        apiresult.setMsg(res==1?"成功":"失败");
         apiresult.setData(res==1?userinfo:"");
		  }catch (Exception e) {
       e.printStackTrace();
			apiresult.setMsg("dynamicUpdateUserinfo出错！");
		}  		return apiresult;
	}

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getUserinfoById(long userInfo_Id) {
		ResultUtil apiresult = new ResultUtil();
		Userinfo userinfo=userinfoDao.findById(userInfo_Id);
		apiresult.setData(userinfo);    
		apiresult.setStatus(userinfo!=null?1:0);
		apiresult.setMsg(userinfo!=null?"成功":"未能获取id为"+userInfo_Id+"的数据。");
		return apiresult;
	}
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getAll() {
		ResultUtil apiresult = new ResultUtil();
		List<Userinfo> userinfo=userinfoDao.findAll();
		apiresult.setData(userinfo);    
		apiresult.setStatus(userinfo!=null?1:0);
		apiresult.setMsg(userinfo!=null?"成功":"未能获取数据。");
		return apiresult;
	}
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil deleteUserinfoById(long userInfo_Id) {
		ResultUtil apiresult = new ResultUtil();
  	 try {
		    apiresult.setStatus( userinfoDao.deleteById(userInfo_Id));
		    apiresult.setMsg( apiresult.getStatus()==1?"删除成功！":"删除失败！");
		  }catch (Exception e) {
       e.printStackTrace();
			apiresult.setMsg("删除Userinfo出错！");
		}		return apiresult;
	}
public ResultUtil selectUserinfo(Userinfo userinfo,Integer pageNow, Integer pageSize,String orderByCase) {
		ResultUtil apiresult = new ResultUtil();
 Map<String,Object> map=userinfo.toMap();
	       if(null!=pageNow&&null!=pageSize){
	       map.put("startPos", (pageNow-1)*pageSize);
	       map.put("pageSize", pageSize);
	       }
           if(null!=orderByCase)
	       map.put("orderByCase", orderByCase);
try {
			List<Userinfo> list = userinfoDao.dynamicSelect(map);
			long count = userinfoDao.getUserinfoCount(userinfo);
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
			apiresult.setMsg("dynamic查询Userinfo出错！");
		}
		return apiresult;       }
}

