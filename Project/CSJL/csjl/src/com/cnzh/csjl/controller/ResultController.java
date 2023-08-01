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
    * ResultController
    * 由chenp 的CSMMAACToolv4.0.5生成
    *控制器Controller
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 
@Controller
@RequestMapping("/Result")
public class ResultController{
	@Resource
	private ResultService resultService;  //根据id删除记录
  //由CSMMAACTool自动创建，请修改！
	@RequestMapping(value="/{result_Id}",method=RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value="删除记录",notes="根据id删除记录。")
	public ResultUtil loadByResultId(@PathVariable("result_Id") int result_Id){
		ResultUtil result=resultService.deleteResultById(result_Id);
		return result;
	}
  //根据id加载信息
  //由CSMMAACTool自动创建，请修改！
	@RequestMapping(value="/{result_Id}",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="获取记录",notes="根据id得到数据。")
	public ResultUtil deleteById(@PathVariable("result_Id") int result_Id){
		ResultUtil result=resultService.getResultById(result_Id);
		return result;
	}
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="获取所有记录",notes="得到全部数据。")
	public ResultUtil loadAll(){
		ResultUtil result=resultService.getAll();
		return result;
	}
    //添加数据信息
    //由CSMMAACTool自动生成！
	@RequestMapping(value = "/addResult", method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="添加数据",notes="添加信息")
	public ResultUtil addResult(
	@ApiParam(value = "踢被子次数",required=false) @RequestParam(required=false) Integer quiltCount,
	@ApiParam(value = "夜间醒来次数",required=false) @RequestParam(required=false) Integer nightWakeCount){
   ResultUtil result =new ResultUtil();
 result=resultService.addResult(
quiltCount,nightWakeCount);
		return result;
	}
    //更新信息
    //由CSMMAACTool自动生成！
	@RequestMapping(value = "/updateResult", method=RequestMethod.PUT)
	@ResponseBody
	@ApiOperation(value="更新数据",notes="通过id更新数据")
	public ResultUtil updateResultById(
	@ApiParam(value = "结果表ID",required=false) @RequestParam(required=false) Long result_Id,
	@ApiParam(value = "踢被子次数",required=false) @RequestParam(required=false) Integer quiltCount,
	@ApiParam(value = "夜间醒来次数",required=false) @RequestParam(required=false) Integer nightWakeCount){
   ResultUtil result =new ResultUtil();
 if (null == result_Id) {
		result.setMsg("Result_Id字段不能为空");
		return result;
	} result=resultService.updateResult(result_Id,quiltCount,nightWakeCount);
		return result;
	}
// 动态查询
	// 由CSMMAACToolv4.0.5自动生成！
	@RequestMapping(value = "/selectResult", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "查询数据", notes = "通过各项参数查询数据")
	public ResultUtil selectResult(	@ApiParam(value = "结果表ID",required=false) @RequestParam(required=false) Long result_Id,
	@ApiParam(value = "踢被子次数",required=false) @RequestParam(required=false) Integer quiltCount,
	@ApiParam(value = "夜间醒来次数",required=false) @RequestParam(required=false) Integer nightWakeCount,
	@ApiParam(value = "开始创建时间",required=false) @RequestParam(required=false) Timestamp StartgenTime,
	@ApiParam(value = "结束创建时间",required=false) @RequestParam(required=false) Timestamp EndgenTime,
	@ApiParam(value = "开始修改时间",required=false) @RequestParam(required=false) Timestamp StarteditTime,
	@ApiParam(value = "结束修改时间",required=false) @RequestParam(required=false) Timestamp EndeditTime,
	@ApiParam(value = "排序") @RequestParam(required=false) String orderByCase,
	@ApiParam(value = "排序方式") @RequestParam(required=false) Boolean desc,
	@ApiParam(value = "页码") @RequestParam(required=false) Integer pageNow,
	@ApiParam(value = "分页大小") @RequestParam(required=false) Integer pageSize
   ){
		if (null != orderByCase) {
			orderByCase += desc ? " desc" : " asc";
		}
     Result result = new Result();	       result.setResult_Id(result_Id);
	       result.setQuiltCount(quiltCount);
	       result.setNightWakeCount(nightWakeCount);
		ResultUtil apiresult = new ResultUtil();
		apiresult = resultService.selectResult(result,pageNow, pageSize,orderByCase);
		return apiresult;
	}
 }
