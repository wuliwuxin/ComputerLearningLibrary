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
   /**
    * EquipmentController
    * 由chenp 的CSMMAACToolv4.0.5生成
    *控制器Controller
    * Chenp-Spring-MVC-Mysql-ApiAutoCreateTool。
    * Sat Apr 07 11:13:36 CST 2018 By chenp
    */ 
@Controller
@RequestMapping("/Equipment")
public class EquipmentController{
	@Resource
	private EquipmentService equipmentService;  //根据id删除记录
  //由CSMMAACTool自动创建，请修改！
	@RequestMapping(value="/{equipment_Id}",method=RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value="删除记录",notes="根据id删除记录。")
	public ResultUtil loadByEquipmentId(@PathVariable("equipment_Id") int equipment_Id){
		ResultUtil result=equipmentService.deleteEquipmentById(equipment_Id);
		return result;
	}
  //根据id加载信息
  //由CSMMAACTool自动创建，请修改！
	@RequestMapping(value="/{equipment_Id}",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="获取记录",notes="根据id得到数据。")
	public ResultUtil deleteById(@PathVariable("equipment_Id") int equipment_Id){
		ResultUtil result=equipmentService.getEquipmentById(equipment_Id);
		return result;
	}
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="获取所有记录",notes="得到全部数据。")
	public ResultUtil loadAll(){
		ResultUtil result=equipmentService.getAll();
		return result;
	}
    //添加数据信息
    //由CSMMAACTool自动生成！
	@RequestMapping(value = "/addEquipment", method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="添加数据",notes="添加信息")
	public ResultUtil addEquipment(
	@ApiParam(value = "设备名") @RequestParam("equipmentName") String equipmentName){
   ResultUtil result =new ResultUtil();
	if (null == equipmentName) {
		result.setMsg("设备名equipmentName字段不能为空");
		return result;
	}
 result=equipmentService.addEquipment(
equipmentName);
		return result;
	}
    //更新信息
    //由CSMMAACTool自动生成！
	@RequestMapping(value = "/updateEquipment", method=RequestMethod.PUT)
	@ResponseBody
	@ApiOperation(value="更新数据",notes="通过id更新数据")
	public ResultUtil updateEquipmentById(
	@ApiParam(value = "设备ID",required=false) @RequestParam(required=false) Long equipment_Id,
	@ApiParam(value = "设备名",required=false) @RequestParam(required=false) String equipmentName){
   ResultUtil result =new ResultUtil();
 if (null == equipment_Id) {
		result.setMsg("Equipment_Id字段不能为空");
		return result;
	} result=equipmentService.updateEquipment(equipment_Id,equipmentName);
		return result;
	}
// 动态查询
	// 由CSMMAACToolv4.0.5自动生成！
	@RequestMapping(value = "/selectEquipment", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "查询数据", notes = "通过各项参数查询数据")
	public ResultUtil selectEquipment(	@ApiParam(value = "设备ID",required=false) @RequestParam(required=false) Long equipment_Id,
	@ApiParam(value = "设备名",required=false) @RequestParam(required=false) String equipmentName,
	@ApiParam(value = "排序") @RequestParam(required=false) String orderByCase,
	@ApiParam(value = "排序方式") @RequestParam(required=false) Boolean desc,
	@ApiParam(value = "页码") @RequestParam(required=false) Integer pageNow,
	@ApiParam(value = "分页大小") @RequestParam(required=false) Integer pageSize
   ){
		if (null != orderByCase) {
			orderByCase += desc ? " desc" : " asc";
		}
     Equipment equipment = new Equipment();	       equipment.setEquipment_Id(equipment_Id);
	       equipment.setEquipmentName(equipmentName);
		ResultUtil apiresult = new ResultUtil();
		apiresult = equipmentService.selectEquipment(equipment,pageNow, pageSize,orderByCase);
		return apiresult;
	}
 }
