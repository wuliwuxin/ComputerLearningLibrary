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
    * UserImplement
    * 由chenp 的CSMMAACToolv4.0.5生成
    *Services 实现类。
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 

@Service
public class UserImplement implements UserService {

	@Resource
	private UserDao userDao;

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil addUser(Long userInfo_Id,Long equipment_Id,Long result_Id,String userName,String passWord,Integer phoneNumber,String email,String avatar,String qQId,String weiBoId,String weChatId) {
		ResultUtil apiresult = new ResultUtil();
     User user = new User();	       user.setUserInfo_Id(userInfo_Id);
	       user.setEquipment_Id(equipment_Id);
	       user.setResult_Id(result_Id);
	       user.setUserName(userName);
	       user.setPassWord(passWord);
	       user.setPhoneNumber(phoneNumber);
	       user.setEmail(email);
	       user.setAvatar(avatar);
	       user.setQQId(qQId);
	       user.setWeiBoId(weiBoId);
	       user.setWeChatId(weChatId);
		  try {
        int res =userDao.save(user);
		   apiresult.setStatus(res);
        apiresult.setMsg(res==1?"成功":"失败");
         apiresult.setData(res==1?user:"");
		  }catch (Exception e) {
       e.printStackTrace();
			apiresult.setMsg("saveUser出错！");
		}  		return apiresult;
	}

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil updateUser(Long user_Id,Long userInfo_Id,Long equipment_Id,Long result_Id,String userName,String passWord,Integer phoneNumber,String email,String avatar,String qQId,String weiBoId,String weChatId) {
		ResultUtil apiresult = new ResultUtil();
     User user = new User();	       user.setUser_Id(user_Id);
	       user.setUserInfo_Id(userInfo_Id);
	       user.setEquipment_Id(equipment_Id);
	       user.setResult_Id(result_Id);
	       user.setUserName(userName);
	       user.setPassWord(passWord);
	       user.setPhoneNumber(phoneNumber);
	       user.setEmail(email);
	       user.setAvatar(avatar);
	       user.setQQId(qQId);
	       user.setWeiBoId(weiBoId);
	       user.setWeChatId(weChatId);
		  try {
        int res =userDao.dynamicUpdate(user);
		   apiresult.setStatus(res);
        apiresult.setMsg(res==1?"成功":"失败");
         apiresult.setData(res==1?user:"");
		  }catch (Exception e) {
       e.printStackTrace();
			apiresult.setMsg("dynamicUpdateUser出错！");
		}  		return apiresult;
	}

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getUserById(long user_Id) {
		ResultUtil apiresult = new ResultUtil();
		User user=userDao.findById(user_Id);
		apiresult.setData(user);    
		apiresult.setStatus(user!=null?1:0);
		apiresult.setMsg(user!=null?"成功":"未能获取id为"+user_Id+"的数据。");
		return apiresult;
	}
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getAll() {
		ResultUtil apiresult = new ResultUtil();
		List<User> user=userDao.findAll();
		apiresult.setData(user);    
		apiresult.setStatus(user!=null?1:0);
		apiresult.setMsg(user!=null?"成功":"未能获取数据。");
		return apiresult;
	}
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil deleteUserById(long user_Id) {
		ResultUtil apiresult = new ResultUtil();
  	 try {
		    apiresult.setStatus( userDao.deleteById(user_Id));
		    apiresult.setMsg( apiresult.getStatus()==1?"删除成功！":"删除失败！");
		  }catch (Exception e) {
       e.printStackTrace();
			apiresult.setMsg("删除User出错！");
		}		return apiresult;
	}
public ResultUtil selectUser(User user,Integer pageNow, Integer pageSize,String orderByCase) {
		ResultUtil apiresult = new ResultUtil();
 Map<String,Object> map=user.toMap();
	       if(null!=pageNow&&null!=pageSize){
	       map.put("startPos", (pageNow-1)*pageSize);
	       map.put("pageSize", pageSize);
	       }
           if(null!=orderByCase)
	       map.put("orderByCase", orderByCase);
try {
			List<User> list = userDao.dynamicSelect(map);
			long count = userDao.getUserCount(user);
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
			apiresult.setMsg("dynamic查询User出错！");
		}
		return apiresult;       }

//Roger
	public ResultUtil getUserByUserName(String userName) {
		ResultUtil apiresult = new ResultUtil();
		User user=userDao.findByuserName(userName);
		apiresult.setData(user);    
		apiresult.setStatus(user!=null?1:0);
		apiresult.setMsg(user!=null?"成功":"未能获取userName为"+userName+"的数据。");
		return apiresult;
	}

	//roger
	public ResultUtil getUserByPhoneNumber(Integer phoneNumber) {
		ResultUtil apiresult = new ResultUtil();
		User user=userDao.findByphoneNumber(phoneNumber);
		apiresult.setData(user);    
		apiresult.setStatus(user!=null?1:0);
		apiresult.setMsg(user!=null?"成功":"未能获取phoneNumber为"+phoneNumber+"的数据。");
		return apiresult;
	}

	//roger
	public ResultUtil getUserByEmail (String email) {
		ResultUtil apiresult = new ResultUtil();
		User user=userDao.findByemail(email);
		apiresult.setData(user);    
		apiresult.setStatus(user!=null?1:0);
		apiresult.setMsg(user!=null?"成功":"未能获取email为"+email+"的数据。");
		return apiresult;
	}
}

