package com.huatec.edu.mobileshop.dao;

import java.util.List;
import java.util.Map;

import com.huatec.edu.mobileshop.entity.Member;

public interface MemberDao {
	/* 此处的方法名与参数类型需和MemberSqlMap.xml中
	 * 相应元素的id与parameterType的值相同
	 */
	public int save(Member member);//insert元素
	public List<Member> findAll();//select元素
	public int updatePwdById(Map map);//update元素
	public int deleteById(int member_id);//delete元素
	public int dynamicUpdate(Member member);
	public List<Member> findUnion(int member_id);
	public Member findByName(String uname);//根据uname查询
	public Member findByEmail(String email);//根据email查询
	public Member findById(int member_id);//根据id查询
}
