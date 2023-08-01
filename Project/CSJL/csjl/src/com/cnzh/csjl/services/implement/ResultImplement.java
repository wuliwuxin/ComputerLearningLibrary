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
    * ResultImplement
    * 由chenp 的CSMMAACToolv4.0.5生成
    *Services 实现类。
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 

@Service
public class ResultImplement implements ResultService {

	@Resource
	private ResultDao resultDao;

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil addResult(Integer quiltCount,Integer nightWakeCount) {
		ResultUtil apiresult = new ResultUtil();
     Result result = new Result();	       result.setQuiltCount(quiltCount);
	       result.setNightWakeCount(nightWakeCount);
		  try {
        int res =resultDao.save(result);
		   apiresult.setStatus(res);
        apiresult.setMsg(res==1?"成功":"失败");
         apiresult.setData(res==1?result:"");
		  }catch (Exception e) {
       e.printStackTrace();
			apiresult.setMsg("saveResult出错！");
		}  		return apiresult;
	}

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil updateResult(Long result_Id,Integer quiltCount,Integer nightWakeCount) {
		ResultUtil apiresult = new ResultUtil();
     Result result = new Result();	       result.setResult_Id(result_Id);
	       result.setQuiltCount(quiltCount);
	       result.setNightWakeCount(nightWakeCount);
		  try {
        int res =resultDao.dynamicUpdate(result);
		   apiresult.setStatus(res);
        apiresult.setMsg(res==1?"成功":"失败");
         apiresult.setData(res==1?result:"");
		  }catch (Exception e) {
       e.printStackTrace();
			apiresult.setMsg("dynamicUpdateResult出错！");
		}  		return apiresult;
	}

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getResultById(long result_Id) {
		ResultUtil apiresult = new ResultUtil();
		Result result=resultDao.findById(result_Id);
		apiresult.setData(result);    
		apiresult.setStatus(result!=null?1:0);
		apiresult.setMsg(result!=null?"成功":"未能获取id为"+result_Id+"的数据。");
		return apiresult;
	}
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getAll() {
		ResultUtil apiresult = new ResultUtil();
		List<Result> result=resultDao.findAll();
		apiresult.setData(result);    
		apiresult.setStatus(result!=null?1:0);
		apiresult.setMsg(result!=null?"成功":"未能获取数据。");
		return apiresult;
	}
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil deleteResultById(long result_Id) {
		ResultUtil apiresult = new ResultUtil();
  	 try {
		    apiresult.setStatus( resultDao.deleteById(result_Id));
		    apiresult.setMsg( apiresult.getStatus()==1?"删除成功！":"删除失败！");
		  }catch (Exception e) {
       e.printStackTrace();
			apiresult.setMsg("删除Result出错！");
		}		return apiresult;
	}
public ResultUtil selectResult(Result result,Integer pageNow, Integer pageSize,String orderByCase) {
		ResultUtil apiresult = new ResultUtil();
 Map<String,Object> map=result.toMap();
	       if(null!=pageNow&&null!=pageSize){
	       map.put("startPos", (pageNow-1)*pageSize);
	       map.put("pageSize", pageSize);
	       }
           if(null!=orderByCase)
	       map.put("orderByCase", orderByCase);
try {
			List<Result> list = resultDao.dynamicSelect(map);
			long count = resultDao.getResultCount(result);
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
			apiresult.setMsg("dynamic查询Result出错！");
		}
		return apiresult;       }
}

