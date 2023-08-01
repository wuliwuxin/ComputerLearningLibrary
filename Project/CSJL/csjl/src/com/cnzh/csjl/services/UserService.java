package com.cnzh.csjl.services;

import com.cnzh.csjl.util.ResultUtil;
import com.cnzh.csjl.entity.*;

   /**
    * UserService
    * 由chenp 的CSMMAACToolv4.0.5生成
    *服务接口。
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Mar 10 12:11:55 CST 2018 By chenp
    */ 
	//CSMMAACToolv4.0.5自动生成
public interface UserService {
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil addUser(Long userInfo_Id,Long equipment_Id,Long result_Id,String userName,String passWord,Integer phoneNumber,String email,String avatar,String qQId,String weiBoId,String weChatId);
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil updateUser(Long user_Id,Long userInfo_Id,Long equipment_Id,Long result_Id,String userName,String passWord,Integer phoneNumber,String email,String avatar,String qQId,String weiBoId,String weChatId);

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getUserById(long user_Id);
	
	//Roger
	public ResultUtil getUserByUserName(String userName);
			
	//roger
	public ResultUtil getUserByPhoneNumber(Integer phoneNumber);
			
	//roger
	public ResultUtil getUserByEmail (String email);

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getAll();

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil deleteUserById(long user_Id);
     /* 通过参数得到记录*
     * 
	//CSMMAACToolv4.0.5自动生成
     * @return 结果类
     */
	public ResultUtil selectUser(User user,Integer pageNow, Integer pageSize,String orderByCase);}

