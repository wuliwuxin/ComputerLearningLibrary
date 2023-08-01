package com.huatec.edu.mobileshop.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huatec.edu.mobileshop.dao.MemberAddressDao;
import com.huatec.edu.mobileshop.dao.MemberDao;
import com.huatec.edu.mobileshop.entity.Member;
import com.huatec.edu.mobileshop.entity.MemberAddress;
import com.huatec.edu.mobileshop.util.Result;

@Service
public class MemberAddressServiceImpl implements MemberAddressService {
	@Resource
	private MemberAddressDao memberAddressDao;
	@Resource
	private MemberDao memberDao;
	
	//新增会员地址信息
	public Result addAddress(int memberId, String provice, String city, String region, 
			String addr, String mobile,String receiver) {
		Result result=new Result();
		Member member=memberDao.findById(memberId);
		if(member==null){
			result.setStatus(1);
			result.setMsg("不存在此会员");
			return result;
		}
		MemberAddress memberAddress=new MemberAddress();
		memberAddress.setAddress_id(null);
		memberAddress.setMember_id(memberId);
		memberAddress.setProvice(provice);
		memberAddress.setCity(city);
		memberAddress.setRegion(region);
		memberAddress.setAddr(addr);
		memberAddress.setMobile(mobile);
		memberAddress.setReceiver(receiver);
		memberAddress.setCreatime(null);
		memberAddress.setModifytime(null);
		memberAddressDao.save(memberAddress);
		result.setStatus(0);
		result.setMsg("新增会员地址信息成功");
		result.setData(memberAddress);
		return result;
	}
	//根据会员id加载会员地址信息（关联查询）
	public Result loadAddressesByMemberId(int memberId) {
		Result result=new Result();
		//调用关联查询方法findUnion（会员地址表和会员表）
		List<MemberAddress> mas=memberAddressDao.findUnion(memberId);
		//判断是否有会员地址信息
		if(mas.isEmpty()){
			result.setStatus(1);
			result.setMsg("没有会员地址信息");
			result.setData(mas);
			return result;
		}
		result.setStatus(0);
		result.setMsg("查询会员地址信息成功");
		result.setData(mas);
		return result;
	}
	//根据id加载地址信息
	public Result loadAddressById(int addressId) {
		Result result=new Result();
		MemberAddress memberAddress=memberAddressDao.findById(addressId);
		if(memberAddress==null){
			result.setStatus(1);
			result.setMsg("此会员地址不存在");
			return result;
		}
		result.setStatus(0);
		result.setMsg("加载此会员地址信息成功");
		result.setData(memberAddress);
		return result;
	}
	//根据id更新
	public Result updateAddress(int addressId, String provice, String city, 
			String region, String addr, String mobile,String receiver) {
		Result result=new Result();
		MemberAddress checkMA=memberAddressDao.findById(addressId);
		if(checkMA==null){
			result.setStatus(1);
			result.setMsg("此会员地址不存在");
			return result;
		}
		MemberAddress memberAddress=new MemberAddress();
		memberAddress.setAddress_id(addressId);
		memberAddress.setProvice(provice);
		memberAddress.setCity(city);
		memberAddress.setRegion(region);
		memberAddress.setAddr(addr);
		memberAddress.setMobile(mobile);
		memberAddress.setReceiver(receiver);
		Timestamp now=new Timestamp(System.currentTimeMillis());
		memberAddress.setModifytime(null);
		memberAddressDao.dynamicUpdate(memberAddress);
		result.setStatus(0);
		result.setMsg("更新会员地址成功");
		return result;
	}
	//根据id删除
	public Result deleteAddressById(int addressId) {
		Result result=new Result();
		MemberAddress memberAddress=memberAddressDao.findById(addressId);
		if(memberAddress==null){
			result.setStatus(1);
			result.setMsg("此会员地址不存在");
			return result;
		}
		memberAddressDao.deleteById(addressId);
		result.setStatus(0);
		result.setMsg("删除此会员地址成功");
		return result;
	}
	
	
}
