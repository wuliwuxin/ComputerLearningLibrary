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
    * NightdataController
    * 由chenp 的CSMMAACToolv4.0.5生成
    *控制器Controller
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 
@Controller
@RequestMapping("/Nightdata")
public class NightdataController{
	@Resource
	private NightdataService nightdataService;  //根据id删除记录
  //由CSMMAACTool自动创建，请修改！
	@RequestMapping(value="/{nightData_Id}",method=RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value="删除记录",notes="根据id删除记录。")
	public ResultUtil loadByNightdataId(@PathVariable("nightData_Id") int nightData_Id){
		ResultUtil result=nightdataService.deleteNightdataById(nightData_Id);
		return result;
	}
  //根据id加载信息
  //由CSMMAACTool自动创建，请修改！
	@RequestMapping(value="/{nightData_Id}",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="获取记录",notes="根据id得到数据。")
	public ResultUtil deleteById(@PathVariable("nightData_Id") int nightData_Id){
		ResultUtil result=nightdataService.getNightdataById(nightData_Id);
		return result;
	}
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="获取所有记录",notes="得到全部数据。")
	public ResultUtil loadAll(){
		ResultUtil result=nightdataService.getAll();
		return result;
	}
    //添加数据信息
    //由CSMMAACTool自动生成！
	@RequestMapping(value = "/addNightdata", method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="添加数据",notes="添加信息")
	public ResultUtil addNightdata(
	@ApiParam(value = "设备ID",required=false) @RequestParam(required=false) Long equipment_Id,
	@ApiParam(value = "图像名",required=false) @RequestParam(required=false) String imageName,
	@ApiParam(value = "室温",required=false) @RequestParam(required=false) Float indoorTemperature,
	@ApiParam(value = "体温",required=false) @RequestParam(required=false) Float bodyTemperature){
   ResultUtil result =new ResultUtil();
 result=nightdataService.addNightdata(
equipment_Id,imageName,indoorTemperature,bodyTemperature);
		return result;
	}
    //更新信息
    //由CSMMAACTool自动生成！
	@RequestMapping(value = "/updateNightdata", method=RequestMethod.PUT)
	@ResponseBody
	@ApiOperation(value="更新数据",notes="通过id更新数据")
	public ResultUtil updateNightdataById(
	@ApiParam(value = "夜晚数据表ID",required=false) @RequestParam(required=false) Long nightData_Id,
	@ApiParam(value = "设备ID",required=false) @RequestParam(required=false) Long equipment_Id,
	@ApiParam(value = "图像名",required=false) @RequestParam(required=false) String imageName,
	@ApiParam(value = "室温",required=false) @RequestParam(required=false) Float indoorTemperature,
	@ApiParam(value = "体温",required=false) @RequestParam(required=false) Float bodyTemperature){
   ResultUtil result =new ResultUtil();
 if (null == nightData_Id) {
		result.setMsg("Nightdata_Id字段不能为空");
		return result;
	} result=nightdataService.updateNightdata(nightData_Id,equipment_Id,imageName,indoorTemperature,bodyTemperature);
		return result;
	}
// 动态查询
	// 由CSMMAACToolv4.0.5自动生成！
	@RequestMapping(value = "/selectNightdata", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "查询数据", notes = "通过各项参数查询数据")
	public ResultUtil selectNightdata(	@ApiParam(value = "夜晚数据表ID",required=false) @RequestParam(required=false) Long nightData_Id,
	@ApiParam(value = "设备ID",required=false) @RequestParam(required=false) Long equipment_Id,
	@ApiParam(value = "图像名",required=false) @RequestParam(required=false) String imageName,
	@ApiParam(value = "室温",required=false) @RequestParam(required=false) Float indoorTemperature,
	@ApiParam(value = "体温",required=false) @RequestParam(required=false) Float bodyTemperature,
	@ApiParam(value = "开始采集时间",required=false) @RequestParam(required=false) Timestamp StartacquisitionTime,
	@ApiParam(value = "结束采集时间",required=false) @RequestParam(required=false) Timestamp EndacquisitionTime,
	@ApiParam(value = "开始导入时间",required=false) @RequestParam(required=false) Timestamp StartgenTime,
	@ApiParam(value = "结束导入时间",required=false) @RequestParam(required=false) Timestamp EndgenTime,
	@ApiParam(value = "排序") @RequestParam(required=false) String orderByCase,
	@ApiParam(value = "排序方式") @RequestParam(required=false) Boolean desc,
	@ApiParam(value = "页码") @RequestParam(required=false) Integer pageNow,
	@ApiParam(value = "分页大小") @RequestParam(required=false) Integer pageSize
   ){
		if (null != orderByCase) {
			orderByCase += desc ? " desc" : " asc";
		}
     Nightdata nightdata = new Nightdata();	       nightdata.setNightData_Id(nightData_Id);
	       nightdata.setEquipment_Id(equipment_Id);
	       nightdata.setImageName(imageName);
	       nightdata.setIndoorTemperature(indoorTemperature);
	       nightdata.setBodyTemperature(bodyTemperature);
		ResultUtil apiresult = new ResultUtil();
		apiresult = nightdataService.selectNightdata(nightdata,pageNow, pageSize,orderByCase);
		return apiresult;
	}
	
	//Roger 根据设备id加载数据
		@RequestMapping(value="/getequipmentId/{equipment_Id}",method=RequestMethod.GET)
		@ResponseBody
		@ApiOperation(value="获取记录",notes="根据equipmentid得到数据。")
		public ResultUtil getByEquipmentId(@PathVariable("equipment_Id") int equipment_Id){
			ResultUtil result=nightdataService.getNightdataByequipmentId(equipment_Id);
			return result;
		}
 }
