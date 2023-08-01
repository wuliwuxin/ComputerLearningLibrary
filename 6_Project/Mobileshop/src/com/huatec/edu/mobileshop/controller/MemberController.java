package com.huatec.edu.mobileshop.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huatec.edu.mobileshop.service.MemberService;
import com.huatec.edu.mobileshop.util.Result;

@Controller
@RequestMapping("/member")
public class MemberController {
	//注入MemberService
	@Resource
	private MemberService memberService;
	//会员注册
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Result regist(String uname,String password,String email){
		System.out.println("uname:"+uname);
		System.out.println("password:"+password);
		System.out.println("email:"+email);
		//调用Service层方法
		Result result=memberService.registMember(uname, password, email);
		//返回结果信息
		return result;
	}
	//会员登录
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Result checkLogin(String uname,String password){
		//调用Service层方法
		Result result=memberService.checkLogin(uname, password);
		//返回结果信息
		return result;
	}
	//根据id加载会员信息
	@RequestMapping(value="/{memberId}",method=RequestMethod.GET)
	@ResponseBody
	public Result loadById(@PathVariable("memberId") int memberId){
		Result result=memberService.loadMemberById(memberId);
		return result;
	}
	//根据id更新会员基本信息
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public Result updateById(Integer memberId,String uname,String email,Integer sex,String mobile){
		System.out.println("memberId:"+memberId);
		System.out.println("uname:"+uname);
		Result result=memberService.updateMember(memberId, uname, email, sex, mobile);
		return result;
	}
	//根据id修改密码
	@RequestMapping(value="/{memberId}",method=RequestMethod.PUT)
	@ResponseBody
	public Result updatePwd(@PathVariable("memberId") int memberId, String oldPwd, String newPwd){
		Result result=memberService.updatePwd(memberId, oldPwd, newPwd);
		return result;
	}
	
}
