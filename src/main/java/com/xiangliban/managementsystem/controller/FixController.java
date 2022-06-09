package com.xiangliban.managementsystem.controller;

import com.xiangliban.managementsystem.pojo.Fix.*;
import com.xiangliban.managementsystem.service.CalculateSalary;
import com.xiangliban.managementsystem.service.FixService;
import com.xiangliban.managementsystem.service.TimelineProcessor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘元驰
 * @Date: 2021/12/13/21:41
 * @Description:
 */

@Api(tags = "报修接口类")
@RestController
@CrossOrigin
public class FixController {

    @Autowired
    private FixService fixService;

    @ApiOperation("根据指定的维修人员id,查询维修工单并合并提供该工单的报修详细信息    [Admin and Worker]")
    @GetMapping("/fix/allFixDetailsByWorkerId")
    public List<FixOrderWithDetails> selectAllFixDetailsByWorkerId(String fixWorkerId) {
        return fixService.selectAllFixDetailsByWorkerId(fixWorkerId);
    }

    @ApiOperation("查询所有未处理的报修申请,仅提供少量信息 [Admin and Worker]")
    @GetMapping("/fix/allUnprocessedOrder")
    public List<FixOrderWithBrief> selectAllUnprocessedOrder() {
        return fixService.selectAllUnprocessedOrder();
    }

    @ApiOperation("根据工单id,获得并分析时间线  [Admin and Worker]")
    @GetMapping("/fix/analysisTimeline")
    public List<FixTimeline> analysisTimeline(String fixOrderId) {
        String rawData = fixService.analysisTimeline(fixOrderId);
        return TimelineProcessor.processTimeline(rawData);
    }

    @ApiOperation("查询所有维修工单 [Admin Only]")
    @GetMapping("/fix/allFixOrder")
    public List<FixOrder> selectAllFixOrder() {
        return fixService.selectAllFixOrder();
    }

    @ApiOperation("查询所有报修申请 [Admin Only]")
    @GetMapping("/fix/allFixRequest")
    public List<FixRequest> selectAllFixRequest() {
        return fixService.selectAllFixRequest();
    }

    @ApiOperation("查询全部维修工单并合并提供该工单的报修详细信息  [Admin Only]")
    @GetMapping("/fix/allFixDetails")
    public List<FixOrderWithDetails> selectAllFixDetails() {
        return fixService.selectAllFixDetails();
    }

    @ApiOperation("根据指定的用户id,查询全部维修工单并合并提供该工单的报修详细信息    [Villager Only]")
    @GetMapping("/fix/allFixDetailsByUserId")
    public List<FixOrderWithDetails> selectAllFixDetailsByUserId(String fixUserId) {
        return fixService.selectAllFixDetailsByUserId(fixUserId);
    }

    @ApiOperation("查询所有维修部门 [Admin Only]")
    @GetMapping("/fix/selectAllDepartments")
    public List<FixDepartment> selectAllDepartments() {
        return fixService.selectAllDepartments();
    }

    @ApiOperation("获取系统中工单状态个数 [Admin Only]")
    @GetMapping("/fix/getFixStatusNumber")
    public List<FixStatusDiagram> getFixStatusNumber() {
        return fixService.getFixStatusNumber();
    }

    @ApiOperation("获取系统中每日被完成工单个数 [Admin Only]")
    @GetMapping("/fix/getDailyFixNumber")
    public List<FixDailyLine> getDailyFixNumber() {
        return fixService.getDailyFixNumber();
    }

    @ApiOperation("获取系统中工作人员完成工单个数 [Admin Only]")
    @GetMapping("/fix/getWorkerFixNumber")
    public List<FixWorkerDiagram> getWorkerFixNumber() {
        return fixService.getWorkerFixNumber();
    }

    @ApiOperation("获取系统中维修部门完成工单个数 [Admin Only]")
    @GetMapping("/fix/getDepartFixNumber")
    public List<FixWorkerDiagram> getDepartFixNumber() {
        return fixService.getDepartFixNumber();
    }

    @ApiOperation("获取指定维修部门的所有员工    [Admin Only]")
    @GetMapping("/fix/selectAllWorkersByDepartment")
    public List<FixWorker> selectAllWorkersByDepartment(String fixDepartmentId) {
        return fixService.selectAllWorkersByDepartment(fixDepartmentId);
    }

    @ApiOperation("新增一个报修申请,同时初始化一个维修工单 [Villager Only]")
    @PostMapping("/fix/addFixRequest")
    public void addFixRequest(@RequestBody FixRequest fixRequest) {
        // 新建报修申请并得到生成的报修表id
        String orderId = "Order#";
        orderId += fixService.addFixRequest(fixRequest);
        // 初始化该工单
        FixOrder fixOrder = new FixOrder();
        fixOrder.setFixOrderId(orderId);            // 设置新生成的工单id
        fixOrder.setFixId(fixRequest.getFixId());   // 设置已经生成的报修表id
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss 工单被创建%");
        String currentStatus = tempDate.format(new java.util.Date());   // 设置时间线节点
        fixOrder.setFixTimeline(currentStatus);
        fixService.addFixOrder(fixOrder);
    }

    @ApiOperation("根据选中的单号进行接单,即将订单状态修改为2已派单并自动填入工作人员id [Worker Only]")
    @PostMapping("/fix/takeOrderByOrderIdAndWorkerId")
    public int takeOrderByOrderIdAndWorkerId(String fixWorkerId, String fixOrderId) {
        // 生成当前时间节点
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss 已派单%");
        String currentStatus = tempDate.format(new java.util.Date());
//        currentStatus += (fixWorkerDepartment + "|" + fixWorkerName + "%");
        return fixService.takeOrderByOrderIdAndWorkerId(fixWorkerId, currentStatus, fixOrderId);
    }

    @ApiOperation("工作人员出发,即将订单状态修改为3进行中并自动填入时间线 [Worker Only]")
    @PostMapping("/fix/ongoingOrderByOrderId")
    public void ongoingOrderByOrderId(String fixOrderId) {
        // 生成当前时间节点
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss 进行中,工作人员已出发%");
        String currentStatus = tempDate.format(new java.util.Date());
        fixService.ongoingOrderByOrderId(currentStatus, fixOrderId);
    }

    @ApiOperation("工作人员确认维修完成并输入耗材,即将订单状态修改为4已完成并自动填入信息 [Worker Only]")
    @PostMapping("/fix/endOrderByOrderIdAndInfo")
    public void endOrderByOrderIdAndInfo(String fixMaterial, String fixMaterialCost, String fixOrderId) throws ParseException {
        // 生成当前时间节点
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss 已完成%");
        String currentStatus = tempDate.format(new java.util.Date());
        // 根据时间计算工时费5r/1h,不足一小时按一小时记
        String rawData = fixService.analysisTimeline(fixOrderId);
        String fixLaborCost = CalculateSalary.calculateSalary(rawData);
        fixService.endOrderByOrderIdAndInfo(fixMaterial, fixMaterialCost, fixLaborCost, currentStatus, fixOrderId);
    }

    @ApiOperation("根据选中的单号,强制关闭工单   [Villager Only]")
    @PostMapping("/fix/forceCloseOrder")
    public int forceCloseOrder(String fixOrderId) {
        // 生成当前时间节点
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss 用户关闭工单%");
        String currentStatus = tempDate.format(new java.util.Date());
        return fixService.forceCloseOrder(fixOrderId, currentStatus);
    }

    @ApiOperation("指定员工和工单,为其强制派单   [Admin Only]")
    @PostMapping("/fix/forceAssignOrder")
    public int forceAssignOrder(String fixWorkerId, String fixOrderId) {
        // 生成当前时间节点
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss 已强制派单%");
        String currentStatus = tempDate.format(new java.util.Date());
        return fixService.forceAssignOrder(fixWorkerId, currentStatus, fixOrderId);
    }

}