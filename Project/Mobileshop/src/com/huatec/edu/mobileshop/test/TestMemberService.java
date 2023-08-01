package com.huatec.edu.mobileshop.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huatec.edu.mobileshop.entity.Member;
import com.huatec.edu.mobileshop.service.MemberService;
import com.huatec.edu.mobileshop.util.Result;

public class TestMemberService {
	//获取MemberService
	String conf="applicationContext.xml";
	ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
	MemberService memberService=ac.getBean("memberServiceImpl",MemberService.class);
	
	@Test
	public void test5(){
		Result result=memberService.updatePwd(5, "123", "123456");
		System.out.println(result.getMsg());
	}
	@Test
	public void test4(){
		Result result=memberService.loadMemberById(6);
		System.out.println(result.getMsg());
		Member m=(Member) result.getData();
		System.out.println(m);
	}
	@Test
	public void test3(){
		Result result=memberService.updateMember(5, "ww", "ww@qq.com", 1, "13643219876");
		System.out.println(result.getMsg());
	}
	@Test
	public void test2(){
		Result result=memberService.checkLogin("test", "123");
		System.out.println("status:"+result.getStatus());
		System.out.println("msg:"+result.getMsg());
		Member m=(Member) result.getData();
		System.out.println(m);
	}
	//测试registMember方法
	@Test
	public void test1(){
		Result result=memberService.registMember("test", "123", "test@qq.com");
		System.out.println("status:"+result.getStatus());
		System.out.println("msg:"+result.getMsg());
		Member m=(Member) result.getData();
		System.out.println(m);
	}
}
