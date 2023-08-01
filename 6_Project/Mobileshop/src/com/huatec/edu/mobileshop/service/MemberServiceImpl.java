package com.huatec.edu.mobileshop.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huatec.edu.mobileshop.dao.MemberDao;
import com.huatec.edu.mobileshop.entity.Member;
import com.huatec.edu.mobileshop.util.MSUtil;
import com.huatec.edu.mobileshop.util.Result;

@Service
public class MemberServiceImpl implements MemberService {
	//注入MemberDao
	@Resource
	private MemberDao memberDao;
	
	//会员注册
	public Result registMember(String uname, String password, String email) {
		System.out.println("service");
		Result result=new Result();
		//判断uname是否注册过
		Member checkMember1=memberDao.findByName(uname);
		if(checkMember1!=null){
			result.setStatus(1);
			result.setMsg("此用户名已存在");
			return result;
		}
		//判断email是否注册过
		Member checkMember2=memberDao.findByEmail(email);
		if(checkMember2!=null){
			result.setStatus(1);
			result.setMsg("此邮箱已存在");
			return result;
		}
		//装载参数
		Member member=new Member();
		member.setMember_id(null);
		member.setUname(uname);
		//密码加密
		String md5_pwd=MSUtil.md5(password);
		member.setPassword(md5_pwd);
		member.setEmail(email);
		member.setSex(0);//默认为男
		member.setMobile("");
		member.setRegtime(null);
		member.setLastlogin(null);
		member.setImage("");
		//调用memberDao的save方法将数据存入数据库
		memberDao.save(member);
		//装载结果信息
		result.setStatus(0);
		result.setMsg("注册成功");
		result.setData(member);
		return result;
	}
	
	//会员登录
	public Result checkLogin(String uname, String password) {
		Result result=new Result();
		Member member=memberDao.findByName(uname);
		//判断用户是否存在
		if(member==null){
			result.setStatus(1);
			result.setMsg("此用户不存在");
			return result;
		}
		//判断密码是否正确
		String md5_pwd=MSUtil.md5(password);
		if(!md5_pwd.equals(member.getPassword())){
			result.setStatus(1);
			result.setMsg("密码错误");
			return result;
		}
		//更新最后登录的时间
		int memberId=member.getMember_id();
		Member updateMember=new Member();
		updateMember.setMember_id(memberId);
		 //获取当前系统时间
		Timestamp now=new Timestamp(System.currentTimeMillis());
		updateMember.setLastlogin(now);
		memberDao.dynamicUpdate(updateMember);
		member.setLastlogin(memberDao.findById(memberId).getLastlogin());
		result.setStatus(0);
		result.setMsg("用户名和密码正确");
		result.setData(member);
		return result;
	}
	
	//根据id加载会员信息
	public Result loadMemberById(int memberId) {
		Result result=new Result();
		Member member=memberDao.findById(memberId);
		//判断此会员是否存在
		if(member==null){
			result.setStatus(1);
			result.setMsg("此会员不存在");
			return result;
		}
		result.setStatus(0);
		result.setMsg("加载此会员信息成功");
		result.setData(member);
		return result;
	}
	
	//根据id更新会员基本信息
	public Result updateMember(int memberId, String uname, String email, int sex, String mobile) {
		Result result=new Result();
		//判断是否存在此会员
		Member checkMember=memberDao.findById(memberId);
		if(checkMember==null){
			result.setStatus(1);
			result.setMsg("不存在此会员");
			return result;
		}
		//装载需要完善的参数
		Member member=new Member();
		member.setUname(uname);
		member.setEmail(email);
		member.setSex(sex);
		member.setMobile(mobile);
		member.setMember_id(memberId);
		//调用动态更新的Dao层接口
		memberDao.dynamicUpdate(member);
		result.setStatus(0);
		result.setMsg("更新会员基本信息成功");
		return result;
	}
	
	//根据id修改密码
	public Result updatePwd(int memberId, String oldPwd, String newPwd) {
		Result result=new Result();
		Member checkMember=memberDao.findById(memberId);
		if(checkMember==null){
			result.setStatus(1);
			result.setMsg("不存在此用户");
			return result;
		}
		//判断输入的原密码是否正确
		System.out.println(oldPwd);
		String md5_oldPwd=MSUtil.md5(oldPwd);
		System.out.println("原密码加密："+md5_oldPwd);
		System.out.println("数据库中原密码："+checkMember.getPassword());
		if(!md5_oldPwd.equals(checkMember.getPassword())){
			result.setStatus(1);
			result.setMsg("输入的原密码有误");
			return result;
		}
		//更新密码
		Member member=new Member();
		member.setMember_id(memberId);
		member.setPassword(MSUtil.md5(newPwd));
		memberDao.dynamicUpdate(member);
		result.setStatus(0);
		result.setMsg("修改密码成功");
		return result;
	}
	
	
}
