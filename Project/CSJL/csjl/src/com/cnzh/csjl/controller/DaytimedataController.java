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
    * DaytimedataController
    * 由chenp 的CSMMAACToolv4.0.5生成
    *控制器Controller
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 
@Controller
@RequestMapping("/Daytimedata")
public class DaytimedataController{
	@Resource
	private DaytimedataService daytimedataService;  //根据id删除记录
  //由CSMMAACTool自动创建，请修改！
	@RequestMapping(value="/{daytimeData_Id}",method=RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value="删除记录",notes="根据id删除记录。")
	public ResultUtil loadByDaytimedataId(@PathVariable("daytimeData_Id") int daytimeData_Id){
		ResultUtil result=daytimedataService.deleteDaytimedataById(daytimeData_Id);
		return result;
	}
  //根据id加载信息
  //由CSMMAACTool自动创建，请修改！
	@RequestMapping(value="/{daytimeData_Id}",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="获取记录",notes="根据id得到数据。")
	public ResultUtil deleteById(@PathVariable("daytimeData_Id") int daytimeData_Id){
		ResultUtil result=daytimedataService.getDaytimedataById(daytimeData_Id);
		return result;
	}
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="获取所有记录",notes="得到全部数据。")
	public ResultUtil loadAll(){
		ResultUtil result=daytimedataService.getAll();
		return result;
	}
    //添加数据信息
    //由CSMMAACTool自动生成！
	@RequestMapping(value = "/addDaytimedata", method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="添加数据",notes="添加信息")
	public ResultUtil addDaytimedata(
	@ApiParam(value = "设备ID",required=false) @RequestParam(required=false) Long equipment_Id,
	@ApiParam(value = "图像名",required=false) @RequestParam(required=false) String imageName,
	@ApiParam(value = "室温",required=false) @RequestParam(required=false) Float indoorTemperature,
	@ApiParam(value = "体温",required=false) @RequestParam(required=false) Float bodyTemperature,
	@ApiParam(value = "采集时间",required=false) @RequestParam(required=false) Object acquisitionTime){
   ResultUtil result =new ResultUtil();
 result=daytimedataService.addDaytimedata(
equipment_Id,imageName,indoorTemperature,bodyTemperature,acquisitionTime);
		return result;
	}
    //更新信息
    //由CSMMAACTool自动生成！
	@RequestMapping(value = "/updateDaytimedata", method=RequestMethod.PUT)
	@ResponseBody
	@ApiOperation(value="更新数据",notes="通过id更新数据")
	public ResultUtil updateDaytimedataById(
	@ApiParam(value = "白天数据表ID",required=false) @RequestParam(required=false) Long daytimeData_Id,
	@ApiParam(value = "设备ID",required=false) @RequestParam(required=false) Long equipment_Id,
	@ApiParam(value = "图像名",required=false) @RequestParam(required=false) String imageName,
	@ApiParam(value = "室温",required=false) @RequestParam(required=false) Float indoorTemperature,
	@ApiParam(value = "体温",required=false) @RequestParam(required=false) Float bodyTemperature,
	@ApiParam(value = "采集时间",required=false) @RequestParam(required=false) Object acquisitionTime){
   ResultUtil result =new ResultUtil();
 if (null == daytimeData_Id) {
		result.setMsg("Daytimedata_Id字段不能为空");
		return result;
	} result=daytimedataService.updateDaytimedata(daytimeData_Id,equipment_Id,imageName,indoorTemperature,bodyTemperature,acquisitionTime);
		return result;
	}
// 动态查询
	// 由CSMMAACToolv4.0.5自动生成！
	@RequestMapping(value = "/selectDaytimedata", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "查询数据", notes = "通过各项参数查询数据")
	public ResultUtil selectDaytimedata(	@ApiParam(value = "白天数据表ID",required=false) @RequestParam(required=false) Long daytimeData_Id,
	@ApiParam(value = "设备ID",required=false) @RequestParam(required=false) Long equipment_Id,
	@ApiParam(value = "图像名",required=false) @RequestParam(required=false) String imageName,
	@ApiParam(value = "室温",required=false) @RequestParam(required=false) Float indoorTemperature,
	@ApiParam(value = "体温",required=false) @RequestParam(required=false) Float bodyTemperature,
	@ApiParam(value = "采集时间",required=false) @RequestParam(required=false) Object acquisitionTime,
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
     Daytimedata daytimedata = new Daytimedata();	       daytimedata.setDaytimeData_Id(daytimeData_Id);
	       daytimedata.setEquipment_Id(equipment_Id);
	       daytimedata.setImageName(imageName);
	       daytimedata.setIndoorTemperature(indoorTemperature);
	       daytimedata.setBodyTemperature(bodyTemperature);
	       daytimedata.setAcquisitionTime(acquisitionTime);
		ResultUtil apiresult = new ResultUtil();
		apiresult = daytimedataService.selectDaytimedata(daytimedata,pageNow, pageSize,orderByCase);
		return apiresult;
	}
	
	//Roger 根据设备id加载数据
	@RequestMapping(value="/getequipmentId/{equipment_Id}",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="获取记录",notes="根据equipmentid得到数据。")
	public ResultUtil getByEquipmentId(@PathVariable("equipment_Id") int equipment_Id){
		ResultUtil result=daytimedataService.getDaytimedataByequipmentId(equipment_Id);
		return result;
	}
 }
