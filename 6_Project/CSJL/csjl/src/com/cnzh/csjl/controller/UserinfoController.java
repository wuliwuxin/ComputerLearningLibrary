package com.cnzh.csjl.controller;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestParam;
import com.wordnik.swagger.annotations.ApiParam;
import com.cnzh.csjl.services.*;
import com.cnzh.csjl.util.*;
import com.cnzh.csjl.entity.*;
import java.sql.*;
   /**
    * UserinfoController
    * 由chenp 的CSMMAACToolv4.0.5生成
    *控制器Controller
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 
@Controller
@RequestMapping("/Userinfo")
public class UserinfoController{
	@Resource
	private UserinfoService userinfoService;  //根据id删除记录
  //由CSMMAACTool自动创建，请修改！
	@RequestMapping(value="/{userInfo_Id}",method=RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value="删除记录",notes="根据id删除记录。")
	public ResultUtil loadByUserinfoId(@PathVariable("userInfo_Id") int userInfo_Id){
		ResultUtil result=userinfoService.deleteUserinfoById(userInfo_Id);
		return result;
	}
  //根据id加载信息
  //由CSMMAACTool自动创建，请修改！
	@RequestMapping(value="/{userInfo_Id}",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="获取记录",notes="根据id得到数据。")
	public ResultUtil deleteById(@PathVariable("userInfo_Id") int userInfo_Id){
		ResultUtil result=userinfoService.getUserinfoById(userInfo_Id);
		return result;
	}
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="获取所有记录",notes="得到全部数据。")
	public ResultUtil loadAll(){
		ResultUtil result=userinfoService.getAll();
		return result;
	}
    //添加数据信息
    //由CSMMAACTool自动生成！
	@RequestMapping(value = "/addUserinfo", method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="添加数据",notes="添加信息")
	public ResultUtil addUserinfo(
	@ApiParam(value = "姓名",required=false) @RequestParam(required=false) String reallyName,
	@ApiParam(value = "性别",required=false) @RequestParam(required=false) Boolean sex,
	@ApiParam(value = "小孩性别",required=false) @RequestParam(required=false) Boolean babySex,
	@ApiParam(value = "小孩出生日期",required=false) @RequestParam(required=false) Object baByBirthday,
	@ApiParam(value = "小孩体重",required=false) @RequestParam(required=false) Integer baByWeight,
	@ApiParam(value = "绑定的设备ID",required=false) @RequestParam(required=false) Long equipment_ID){
   ResultUtil result =new ResultUtil();
 result=userinfoService.addUserinfo(
reallyName,sex,babySex,baByBirthday,baByWeight,equipment_ID);
		return result;
	}
    //更新信息
    //由CSMMAACTool自动生成！
	@RequestMapping(value = "/updateUserinfo", method=RequestMethod.PUT)
	@ResponseBody
	@ApiOperation(value="更新数据",notes="通过id更新数据")
	public ResultUtil updateUserinfoById(
	@ApiParam(value = "用户详细信息ID",required=false) @RequestParam(required=false) Long userInfo_Id,
	@ApiParam(value = "姓名",required=false) @RequestParam(required=false) String reallyName,
	@ApiParam(value = "性别",required=false) @RequestParam(required=false) Boolean sex,
	@ApiParam(value = "小孩性别",required=false) @RequestParam(required=false) Boolean babySex,
	@ApiParam(value = "小孩出生日期",required=false) @RequestParam(required=false) Object baByBirthday,
	@ApiParam(value = "小孩体重",required=false) @RequestParam(required=false) Integer baByWeight,
	@ApiParam(value = "绑定的设备ID",required=false) @RequestParam(required=false) Long equipment_ID){
   ResultUtil result =new ResultUtil();
 if (null == userInfo_Id) {
		result.setMsg("Userinfo_Id字段不能为空");
		return result;
	} result=userinfoService.updateUserinfo(userInfo_Id,reallyName,sex,babySex,baByBirthday,baByWeight,equipment_ID);
		return result;
	}
// 动态查询
	// 由CSMMAACToolv4.0.5自动生成！
	@RequestMapping(value = "/selectUserinfo", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "查询数据", notes = "通过各项参数查询数据")
	public ResultUtil selectUserinfo(	@ApiParam(value = "用户详细信息ID",required=false) @RequestParam(required=false) Long userInfo_Id,
	@ApiParam(value = "姓名",required=false) @RequestParam(required=false) String reallyName,
	@ApiParam(value = "性别",required=false) @RequestParam(required=false) Boolean sex,
	@ApiParam(value = "小孩性别",required=false) @RequestParam(required=false) Boolean babySex,
	@ApiParam(value = "小孩出生日期",required=false) @RequestParam(required=false) Object baByBirthday,
	@ApiParam(value = "小孩体重",required=false) @RequestParam(required=false) Integer baByWeight,
	@ApiParam(value = "绑定的设备ID",required=false) @RequestParam(required=false) Long equipment_ID,
	@ApiParam(value = "开始创建时间",required=false) @RequestParam(required=false) Timestamp StartgenTime,
	@ApiParam(value = "结束创建时间",required=false) @RequestParam(required=false) Timestamp EndgenTime,
	@ApiParam(value = "开始修改时间",required=false) @RequestParam(required=false) Timestamp StarteditTime,
	@ApiParam(value = "结束修改时间",required=false) @RequestParam(required=false) Timestamp EndeditTime,
	@ApiParam(value = "排序") @RequestParam(required=false) String orderByCase,
	@ApiParam(value = "排序方式") @RequestParam(required=false) Boolean desc,
	@ApiParam(value = "页码") @RequestParam(required=false) Integer pageNow,
	@ApiParam(value = "分页大小") @RequestParam(required=false) Integer pageSize
   ){
		if (null != orderByCase) {
			orderByCase += desc ? " desc" : " asc";
		}
     Userinfo userinfo = new Userinfo();	       userinfo.setUserInfo_Id(userInfo_Id);
	       userinfo.setReallyName(reallyName);
	       userinfo.setSex(sex);
	       userinfo.setBabySex(babySex);
	       userinfo.setBaByBirthday(baByBirthday);
	       userinfo.setBaByWeight(baByWeight);
	       userinfo.setEquipment_ID(equipment_ID);
		ResultUtil apiresult = new ResultUtil();
		apiresult = userinfoService.selectUserinfo(userinfo,pageNow, pageSize,orderByCase);
		return apiresult;
	}
 }
