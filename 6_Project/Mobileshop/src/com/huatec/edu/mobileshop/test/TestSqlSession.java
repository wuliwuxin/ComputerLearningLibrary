package com.huatec.edu.mobileshop.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSqlSession {
	@Test
	public void testSessionFactory(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		SqlSessionFactory ssf=ac.getBean("ssf",SqlSessionFactory.class);
		SqlSession session=ssf.openSession();
		System.out.println(session);
		session.close();
	}
}
