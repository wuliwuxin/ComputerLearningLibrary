package com.huatec.edu.mobileshop.test;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huatec.edu.mobileshop.dao.MemberAddressDao;
import com.huatec.edu.mobileshop.entity.MemberAddress;

public class TestMemberAddressDao {
	//获取MemberDao
	String conf="applicationContext.xml";
	ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
	MemberAddressDao memberAddressDao=ac.getBean("memberAddressDao",MemberAddressDao.class);
	
	@Test
	public void test3(){
		MemberAddress ma=new MemberAddress();
		ma.setAddress_id(16);
		ma.setProvice("广东省");
		ma.setCity("深圳市");
		ma.setRegion("龙岗区");
		ma.setAddr("坂田");
		ma.setMobile("13923456789");
		ma.setReceiver("王双双");
		Timestamp now=new Timestamp(System.currentTimeMillis());
		ma.setModifytime(now);
		memberAddressDao.dynamicUpdate(ma);
	}
	@Test
	public void test2(){
		MemberAddress ma=memberAddressDao.findById(2);
		System.out.println(ma);
	}
	@Test
	public void test1(){
		List<MemberAddress> mas=memberAddressDao.findUnion(2);
		for(MemberAddress ma:mas){
			System.out.println(ma);
			System.out.println(ma.getMember().getUname());
		}
	}
}
