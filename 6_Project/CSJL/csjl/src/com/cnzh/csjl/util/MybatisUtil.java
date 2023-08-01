package com.cnzh.csjl.util;
import java.io.InputStream;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
   /**
    * MybatisUtil
    * 由chenp 的CSMMAACToolv4.0.5生成
    *    
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:35 CST 2018 By chenp
    */ 
public class MybatisUtil {
	public static SqlSession getSqlSession(){
	SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
	ClassLoader loader=MybatisUtil.class.getClassLoader();
	InputStream instream = loader.getResourceAsStream("SqlMapConfig.xml");
	SqlSessionFactory factory =builder.build(instream);
	return factory.openSession();
	}
}

