package com.cnzh.csjl.services;

import com.cnzh.csjl.util.ResultUtil;
import com.cnzh.csjl.entity.*;

   /**
    * UserinfoService
    * 由chenp 的CSMMAACToolv4.0.5生成
    *服务接口。
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 
	//CSMMAACToolv4.0.5自动生成
public interface UserinfoService {
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil addUserinfo(String reallyName,Boolean sex,Boolean babySex,Object baByBirthday,Integer baByWeight,Long equipment_ID);
	//CSMMAACToolv4.0.5自动生成
	public ResultUtil updateUserinfo(Long userInfo_Id,String reallyName,Boolean sex,Boolean babySex,Object baByBirthday,Integer baByWeight,Long equipment_ID);

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getUserinfoById(long userInfo_Id);

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil getAll();

	//CSMMAACToolv4.0.5自动生成
	public ResultUtil deleteUserinfoById(long userInfo_Id);
     /* 通过参数得到记录*
     * 
	//CSMMAACToolv4.0.5自动生成
     * @return 结果类
     */
	public ResultUtil selectUserinfo(Userinfo userinfo,Integer pageNow, Integer pageSize,String orderByCase);}

