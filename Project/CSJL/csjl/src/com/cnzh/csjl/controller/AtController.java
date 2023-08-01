package com.cnzh.csjl.controller;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestParam;
import com.wordnik.swagger.annotations.ApiParam;
import com.cnzh.csjl.services.*;
import com.cnzh.csjl.util.*;
import com.cnzh.csjl.entity.*;
import java.sql.*;
   /**
    * AtController
    * 由chenp 的CSMMAACToolv4.0.5生成
    *控制器Controller
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 
@Controller
@RequestMapping("/At")
public class AtController{
	@Resource
	private AtService atService;  //根据id删除记录
  //由CSMMAACTool自动创建，请修改！
	@RequestMapping(value="/{aT_id}",method=RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value="删除记录",notes="根据id删除记录。")
	public ResultUtil loadByAtId(@PathVariable("aT_id") int aT_id){
		ResultUtil result=atService.deleteAtById(aT_id);
		return result;
	}
  //根据id加载信息
  //由CSMMAACTool自动创建，请修改！
	@RequestMapping(value="/{aT_id}",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="获取记录",notes="根据id得到数据。")
	public ResultUtil deleteById(@PathVariable("aT_id") int aT_id){
		ResultUtil result=atService.getAtById(aT_id);
		return result;
	}
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="获取所有记录",notes="得到全部数据。")
	public ResultUtil loadAll(){
		ResultUtil result=atService.getAll();
		return result;
	}
    //添加数据信息
    //由CSMMAACTool自动生成！
	@RequestMapping(value = "/addAt", method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="添加数据",notes="添加信息")
	public ResultUtil addAt(
	@ApiParam(value = "AccessToken",required=false) @RequestParam(required=false) String accessToken){
   ResultUtil result =new ResultUtil();
 result=atService.addAt(
accessToken);
		return result;
	}
    //更新信息
    //由CSMMAACTool自动生成！
	@RequestMapping(value = "/updateAt", method=RequestMethod.PUT)
	@ResponseBody
	@ApiOperation(value="更新数据",notes="通过id更新数据")
	public ResultUtil updateAtById(
	@ApiParam(value = "密钥ID",required=false) @RequestParam(required=false) Long aT_id,
	@ApiParam(value = "AccessToken",required=false) @RequestParam(required=false) String accessToken){
   ResultUtil result =new ResultUtil();
 if (null == aT_id) {
		result.setMsg("At_Id字段不能为空");
		return result;
	} result=atService.updateAt(aT_id,accessToken);
		return result;
	}
// 动态查询
	// 由CSMMAACToolv4.0.5自动生成！
	@RequestMapping(value = "/selectAt", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "查询数据", notes = "通过各项参数查询数据")
	public ResultUtil selectAt(	@ApiParam(value = "密钥ID",required=false) @RequestParam(required=false) Long aT_id,
	@ApiParam(value = "AccessToken",required=false) @RequestParam(required=false) String accessToken,
	@ApiParam(value = "开始创建时间",required=false) @RequestParam(required=false) Timestamp StartgenTime,
	@ApiParam(value = "结束创建时间",required=false) @RequestParam(required=false) Timestamp EndgenTime,
	@ApiParam(value = "排序") @RequestParam(required=false) String orderByCase,
	@ApiParam(value = "排序方式") @RequestParam(required=false) Boolean desc,
	@ApiParam(value = "页码") @RequestParam(required=false) Integer pageNow,
	@ApiParam(value = "分页大小") @RequestParam(required=false) Integer pageSize
   ){
		if (null != orderByCase) {
			orderByCase += desc ? " desc" : " asc";
		}
     At at = new At();
	       at.setAccessToken(accessToken);
		ResultUtil apiresult = new ResultUtil();
		apiresult = atService.selectAt(at,pageNow, pageSize,orderByCase);
		return apiresult;
	}
 }