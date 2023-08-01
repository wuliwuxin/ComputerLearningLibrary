package com.huatec.edu.mobileshop.service;

import com.huatec.edu.mobileshop.util.Result;

public interface MemberAddressService {
	//新增会员地址信息
	public Result addAddress(int memberId,String provice,String city,
			String region,String addr,String mobile,String receiver);
	//根据会员id加载会员地址信息（关联查询）
	public Result loadAddressesByMemberId(int memberId);
	//根据id加载地址信息
	public Result loadAddressById(int addressId);
	//根据id更新
	public Result updateAddress(int addressId,String provice,String city,
			String region,String addr,String mobile,String receiver);
	//根据id删除
	public Result deleteAddressById(int addressId);
	
}
