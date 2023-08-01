package com.huatec.edu.mobileshop.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huatec.edu.mobileshop.service.MemberAddressService;
import com.huatec.edu.mobileshop.util.Result;

@Controller
@RequestMapping("/member_address")
public class MemberAddressController {
	//注入MemberService
	@Resource
	private MemberAddressService addressService;
	
	//新增会员地址信息
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Result add(int memberId, String provice, String city, String region, 
			String addr, String mobile,String receiver){
		Result result=
				addressService.addAddress(memberId, provice, city, region, addr, mobile, receiver);
		return result;
	}
	//根据会员id加载会员地址信息（关联查询）
	@RequestMapping(value="/member/{memberId}",method=RequestMethod.GET)
	@ResponseBody
	public Result loadByMemberId(@PathVariable("memberId") int memberId){
		Result result=addressService.loadAddressesByMemberId(memberId);
		return result;
	}
	//根据id加载地址信息
	@RequestMapping(value="/{addressId}",method=RequestMethod.GET)
	@ResponseBody
	public Result loadById(@PathVariable("addressId") int addressId){
		Result result=addressService.loadAddressById(addressId);
		return result;
	}
	//根据id更新
	@RequestMapping(value="/{addressId}",method=RequestMethod.PUT)
	@ResponseBody
	public Result updateById(@PathVariable("addressId") int addressId,String provice, 
			String city, String region, String addr, String mobile,String receiver){
		Result result=addressService.updateAddress(addressId, provice, city, 
				region, addr, mobile, receiver);
		return result;
	}
	//根据id删除
	@RequestMapping(value="/{addressId}",method=RequestMethod.DELETE)
	@ResponseBody
	public Result delete(@PathVariable("addressId") int addressId){
		Result result=addressService.deleteAddressById(addressId);
		return result;
	}
}
