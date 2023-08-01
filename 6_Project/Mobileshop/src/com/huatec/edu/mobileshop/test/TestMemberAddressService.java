package com.huatec.edu.mobileshop.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huatec.edu.mobileshop.entity.MemberAddress;
import com.huatec.edu.mobileshop.service.MemberAddressService;
import com.huatec.edu.mobileshop.util.Result;

public class TestMemberAddressService {
	//获取MemberAddressService
	String conf="applicationContext.xml";
	ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
	MemberAddressService addressService=ac.getBean("memberAddressServiceImpl",MemberAddressService.class);
	
	@Test
	public void test4(){
		Result result=addressService.updateAddress(17, "广东省", "深圳市", "罗湖区", "国贸", 
				"13567892345", "王爽");
		System.out.println(result.getMsg());
	}
	@Test
	public void test3(){
		Result result=addressService.deleteAddressById(10);
		System.out.println(result.getMsg());
	}
	@Test
	public void test2(){
		Result result=addressService.loadAddressById(2);
		System.out.println(result.getMsg());
		MemberAddress ma=(MemberAddress) result.getData();
		System.out.println(ma);
	}
	@Test
	public void test1(){
		Result result=addressService.addAddress(3, "广西省", "桂林市", "", "", "", "");
		System.out.println(result.getMsg());
		MemberAddress ma=(MemberAddress) result.getData();
		System.out.println(ma);
	}
}
