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
    * AtImplement
    * 由chenp 的CSMMAACToolv4.0.5生成
    *Services 实现类。
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 

@Service
public class AtImplement implements AtService {

	@Resource
	private AtDao atDao;

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil addAt(String accessToken) {
		ResultUtil apiresult = new ResultUtil();
     At at = new At();	       at.setAccessToken(accessToken);
		  try {
        int res =atDao.save(at);
		   apiresult.setStatus(res);
        apiresult.setMsg(res==1?"成功":"失败");
         apiresult.setData(res==1?at:"");
		  }catch (Exception e) {
       e.printStackTrace();
			apiresult.setMsg("saveAt出错！");
		}  		return apiresult;
	}

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil updateAt(Long aT_id,String accessToken) {
		ResultUtil apiresult = new ResultUtil();
     At at = new At();	       at.setAT_id(aT_id);
	       at.setAccessToken(accessToken);
		  try {
        int res =atDao.dynamicUpdate(at);
		   apiresult.setStatus(res);
        apiresult.setMsg(res==1?"成功":"失败");
         apiresult.setData(res==1?at:"");
		  }catch (Exception e) {
       e.printStackTrace();
			apiresult.setMsg("dynamicUpdateAt出错！");
		}  		return apiresult;
	}

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getAtById(long aT_id) {
		ResultUtil apiresult = new ResultUtil();
		At at=atDao.findById(aT_id);
		apiresult.setData(at);    
		apiresult.setStatus(at!=null?1:0);
		apiresult.setMsg(at!=null?"成功":"未能获取id为"+aT_id+"的数据。");
		return apiresult;
	}
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getAll() {
		ResultUtil apiresult = new ResultUtil();
		List<At> at=atDao.findAll();
		apiresult.setData(at);    
		apiresult.setStatus(at!=null?1:0);
		apiresult.setMsg(at!=null?"成功":"未能获取数据。");
		return apiresult;
	}
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil deleteAtById(long aT_id) {
		ResultUtil apiresult = new ResultUtil();
  	 try {
		    apiresult.setStatus( atDao.deleteById(aT_id));
		    apiresult.setMsg( apiresult.getStatus()==1?"删除成功！":"删除失败！");
		  }catch (Exception e) {
       e.printStackTrace();
			apiresult.setMsg("删除At出错！");
		}		return apiresult;
	}
public ResultUtil selectAt(At at,Integer pageNow, Integer pageSize,String orderByCase) {
		ResultUtil apiresult = new ResultUtil();
 Map<String,Object> map=at.toMap();
	       if(null!=pageNow&&null!=pageSize){
	       map.put("startPos", (pageNow-1)*pageSize);
	       map.put("pageSize", pageSize);
	       }
           if(null!=orderByCase)
	       map.put("orderByCase", orderByCase);
try {
			List<At> list = atDao.dynamicSelect(map);
			long count = atDao.getAtCount(at);
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
			apiresult.setMsg("dynamic查询At出错！");
		}
		return apiresult;       }
}

