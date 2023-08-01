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
    * UserController
    * 由chenp 的CSMMAACToolv4.0.5生成
    *控制器Controller
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Mar 10 12:11:55 CST 2018 By chenp
    */ 
@Controller
@RequestMapping("/User")
public class UserController{
	@Resource
	private UserService userService;  //根据id删除记录
  //由CSMMAACTool自动创建，请修改！
	@RequestMapping(value="/{user_Id}",method=RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value="删除记录",notes="根据id删除记录。")
	public ResultUtil loadByUserId(@PathVariable("user_Id") int user_Id){
		ResultUtil result=userService.deleteUserById(user_Id);
		return result;
	}
  //根据id加载信息
  //由CSMMAACTool自动创建，请修改！
	@RequestMapping(value="/{user_Id}",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="获取记录",notes="根据id得到数据。")
	public ResultUtil deleteById(@PathVariable("user_Id") int user_Id){
		ResultUtil result=userService.getUserById(user_Id);
		return result;
	}
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="获取所有记录",notes="得到全部数据。")
	public ResultUtil loadAll(){
		ResultUtil result=userService.getAll();
		return result;
	}
    //添加数据信息
    //由CSMMAACTool自动生成！
	@RequestMapping(value = "/addUser", method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="添加数据",notes="添加信息")
	public ResultUtil addUser(
	@ApiParam(value = "用户详细信息ID",required=false) @RequestParam(required=false) Long userInfo_Id,
	@ApiParam(value = "设备ID",required=false) @RequestParam(required=false) Long equipment_Id,
	@ApiParam(value = "结果表ID",required=false) @RequestParam(required=false) Long result_Id,
	@ApiParam(value = "用户名") @RequestParam("userName") String userName,
	@ApiParam(value = "密码") @RequestParam("passWord") String passWord,
	@ApiParam(value = "手机号",required=false) @RequestParam(required=false) Integer phoneNumber,
	@ApiParam(value = "邮箱",required=false) @RequestParam(required=false) String email,
	@ApiParam(value = "头像",required=false) @RequestParam(required=false) String avatar,
	@ApiParam(value = "QQID",required=false) @RequestParam(required=false) String qQId,
	@ApiParam(value = "微博ID",required=false) @RequestParam(required=false) String weiBoId,
	@ApiParam(value = "微信ID",required=false) @RequestParam(required=false) String weChatId){
   ResultUtil result =new ResultUtil();
	if (null == userName) {
		result.setMsg("用户名userName字段不能为空");
		return result;
	}
	if (null == passWord) {
		result.setMsg("密码passWord字段不能为空");
		return result;
	}
 result=userService.addUser(
userInfo_Id,equipment_Id,result_Id,userName,passWord,phoneNumber,email,avatar,qQId,weiBoId,weChatId);
		return result;
	}
    //更新信息
    //由CSMMAACTool自动生成！
	@RequestMapping(value = "/updateUser", method=RequestMethod.PUT)
	@ResponseBody
	@ApiOperation(value="更新数据",notes="通过id更新数据")
	public ResultUtil updateUserById(
	@ApiParam(value = "用户ID",required=false) @RequestParam(required=false) Long user_Id,
	@ApiParam(value = "用户详细信息ID",required=false) @RequestParam(required=false) Long userInfo_Id,
	@ApiParam(value = "设备ID",required=false) @RequestParam(required=false) Long equipment_Id,
	@ApiParam(value = "结果表ID",required=false) @RequestParam(required=false) Long result_Id,
	@ApiParam(value = "用户名",required=false) @RequestParam(required=false) String userName,
	@ApiParam(value = "密码",required=false) @RequestParam(required=false) String passWord,
	@ApiParam(value = "手机号",required=false) @RequestParam(required=false) Integer phoneNumber,
	@ApiParam(value = "邮箱",required=false) @RequestParam(required=false) String email,
	@ApiParam(value = "头像",required=false) @RequestParam(required=false) String avatar,
	@ApiParam(value = "QQID",required=false) @RequestParam(required=false) String qQId,
	@ApiParam(value = "微博ID",required=false) @RequestParam(required=false) String weiBoId,
	@ApiParam(value = "微信ID",required=false) @RequestParam(required=false) String weChatId){
   ResultUtil result =new ResultUtil();
 if (null == user_Id) {
		result.setMsg("User_Id字段不能为空");
		return result;
	} result=userService.updateUser(user_Id,userInfo_Id,equipment_Id,result_Id,userName,passWord,phoneNumber,email,avatar,qQId,weiBoId,weChatId);
		return result;
	}
// 动态查询
	// 由CSMMAACToolv4.0.5自动生成！
	@RequestMapping(value = "/selectUser", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "查询数据", notes = "通过各项参数查询数据")
	public ResultUtil selectUser(	@ApiParam(value = "用户ID",required=false) @RequestParam(required=false) Long user_Id,
	@ApiParam(value = "用户详细信息ID",required=false) @RequestParam(required=false) Long userInfo_Id,
	@ApiParam(value = "设备ID",required=false) @RequestParam(required=false) Long equipment_Id,
	@ApiParam(value = "结果表ID",required=false) @RequestParam(required=false) Long result_Id,
	@ApiParam(value = "用户名",required=false) @RequestParam(required=false) String userName,
	@ApiParam(value = "密码",required=false) @RequestParam(required=false) String passWord,
	@ApiParam(value = "手机号",required=false) @RequestParam(required=false) Integer phoneNumber,
	@ApiParam(value = "邮箱",required=false) @RequestParam(required=false) String email,
	@ApiParam(value = "头像",required=false) @RequestParam(required=false) String avatar,
	@ApiParam(value = "QQID",required=false) @RequestParam(required=false) String qQId,
	@ApiParam(value = "微博ID",required=false) @RequestParam(required=false) String weiBoId,
	@ApiParam(value = "微信ID",required=false) @RequestParam(required=false) String weChatId,
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
		ResultUtil apiresult = new ResultUtil();
		apiresult = userService.selectUser(user,pageNow, pageSize,orderByCase);
		return apiresult;
	}
	
	//登录
			@RequestMapping(value="/Login",method=RequestMethod.GET)
			@ResponseBody
			@ApiOperation(value="获取密码",notes="根据用户名，邮箱号，电话号获取密码")
			public ResultUtil getPasswordByStudentIdOrSomething(
					@ApiParam(value = "账号",required=false) @RequestParam(required=false) String account,
					@ApiParam(value = "密码",required=false) @RequestParam(required=false) String passWord
					){
				ResultUtil apiresult = new ResultUtil();
				String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";  
		        String ph = "^1[3|4|5|7|8][0-9]{9}$";
		        if(null==account) {
		        	apiresult.setMsg("用户名为空");
		        	return apiresult;
		        }
		        if (account.matches(ph)) {
		        	User user = new User();
		        	apiresult=userService.getUserByPhoneNumber(Integer.parseInt(account));
		        	user=(User) apiresult.getData();
		        	if (user==null) {
						apiresult.setMsg("用户不存在");
						return apiresult;
					}
		        	if(user.getPassWord().equals(passWord)) 
		        		return apiresult;
		        	else {
		        		apiresult.setData(null);
		        		apiresult.setStatus(0);
		        		apiresult.setMsg("密码错误");
		                return apiresult;
					}
				}
		        if (account.matches(em)) {
		        	User user = new User();
		        	apiresult=userService.getUserByEmail(account);
		        	user=(User) apiresult.getData();
		        	if (user==null) {
						apiresult.setMsg("用户不存在");
						return apiresult;
					}
		        	if(user.getPassWord().equals(passWord)) 
		        		return apiresult;
		        	else {
		        		apiresult.setData(null);
		        		apiresult.setStatus(0);
		        		apiresult.setMsg("密码错误");
		                return apiresult;
					}
		        }
		        else {
		        	User user = new User();
		        	apiresult=userService.getUserByUserName(account);
		        	user=(User) apiresult.getData();
		        	if (user==null) {
						apiresult.setMsg("用户不存在");
						return apiresult;
					}
		        	if(user.getPassWord().equals(passWord)) 
		        		return apiresult;
		        	else {
		        		apiresult.setData(null);
		        		apiresult.setStatus(0);
		        		apiresult.setMsg("密码错误");
		                return apiresult;
					}
				}
			}
			//注册
			@RequestMapping(value = "/Register", method = RequestMethod.POST)
			@ResponseBody
			@ApiOperation(value = "注册", notes = "注册")
			public ResultUtil Register(
					@ApiParam(value = "用户详细信息ID",required=false) @RequestParam(required=false) Long userInfo_Id,
					@ApiParam(value = "设备ID",required=false) @RequestParam(required=false) Long equipment_Id,
					@ApiParam(value = "结果表ID",required=false) @RequestParam(required=false) Long result_Id,
					@ApiParam(value = "用户名") @RequestParam("userName") String userName,
					@ApiParam(value = "密码") @RequestParam("passWord") String passWord,
					@ApiParam(value = "手机号",required=false) @RequestParam(required=false) Integer phoneNumber,
					@ApiParam(value = "邮箱",required=false) @RequestParam(required=false) String email,
					@ApiParam(value = "头像",required=false) @RequestParam(required=false) String avatar,
					@ApiParam(value = "QQID",required=false) @RequestParam(required=false) String qQId,
					@ApiParam(value = "微博ID",required=false) @RequestParam(required=false) String weiBoId,
					@ApiParam(value = "微信ID",required=false) @RequestParam(required=false) String weChatId){
				ResultUtil result = new ResultUtil();
				if (null == userName) {
					result.setMsg("用户名userName字段不能为空");
					return result;
				}
				if (null == passWord) {
					result.setMsg("密码passWord字段不能为空");
					return result;
				}
				if (null==userService.getUserByUserName(userName).getData()) {
					result=userService.addUser(
							userInfo_Id,equipment_Id,result_Id,userName,passWord,phoneNumber,email,avatar,qQId,weiBoId,weChatId);
					return result;
				}
				else{
					result.setMsg("该用户已存在");
					return result;
					}
			}
	
 }
