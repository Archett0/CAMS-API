package com.xiangliban.managementsystem.service;

import com.xiangliban.managementsystem.dao.mapper.FixMapper;
import com.xiangliban.managementsystem.pojo.Fix.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘元驰
 * @Date: 2021/12/13/21:50
 * @Description:
 */

@Repository
public class FixService {

    @Autowired
    private FixMapper fixMapper;

    public FixMapper getFixMapper() {
        return fixMapper;
    }

    public void setFixMapper(FixMapper fixMapper) {
        this.fixMapper = fixMapper;
    }

    // 查询
    // 根据指定的维修人员id,查询维修工单并合并提供该工单的报修详细信息
    public List<FixOrderWithDetails> selectAllFixDetailsByWorkerId(String fixWorkerId) {
        return fixMapper.selectAllFixDetailsByWorkerId(fixWorkerId);
    }

    // 查询所有未处理的报修申请,仅提供少量信息
    public List<FixOrderWithBrief> selectAllUnprocessedOrder() {
        return fixMapper.selectAllUnprocessedOrder();
    }

    // 根据工单id,获得并分析时间线
    public String analysisTimeline(String fixOrderId) {
        return fixMapper.analysisTimeline(fixOrderId);
    }

    // 查询所有维修工单
    public List<FixOrder> selectAllFixOrder() {
        return fixMapper.selectAllFixOrder();
    }

    // 查询所有报修申请
    public List<FixRequest> selectAllFixRequest() {
        return fixMapper.selectAllFixRequest();
    }

    // 查询全部维修工单并合并提供该工单的报修详细信息
    public List<FixOrderWithDetails> selectAllFixDetails() {
        return fixMapper.selectAllFixDetails();
    }

    // 根据指定的用户id,查询全部维修工单并合并提供该工单的报修详细信息
    public List<FixOrderWithDetails> selectAllFixDetailsByUserId(String fixUserId) {
        return fixMapper.selectAllFixDetailsByUserId(fixUserId);
    }

    // 获取所有维修部门
    public List<FixDepartment> selectAllDepartments() {
        return fixMapper.selectAllDepartments();
    }

    // 获取系统中工单状态个数
    public List<FixStatusDiagram> getFixStatusNumber() {
        return fixMapper.getFixStatusNumber();
    }

    // 获取系统中工作人员完成工单个数
    public List<FixWorkerDiagram> getWorkerFixNumber() {
        return fixMapper.getWorkerFixNumber();
    }

    // 获取系统中维修部门完成工单个数
    public List<FixWorkerDiagram> getDepartFixNumber() {
        return fixMapper.getDepartFixNumber();
    }

    // 获取系统中每日被完成工单个数
    public List<FixDailyLine> getDailyFixNumber() {
        // 获取全部已完成订单
        List<FixDailyDiagram> rawData = fixMapper.getDailyFixNumber();
        // 获取当前订单信息
        for (FixDailyDiagram curRawData : rawData) {
            String rawTimeLine = curRawData.getFixTimeline(); // 获取当前订单时间线信息
            String processedTime = rawTimeLine.substring(rawTimeLine.length() - 24, rawTimeLine.length() - 14); // 处理当前时间线,仅留下完成时间
            curRawData.setFixTimeline(processedTime);   // 保存处理过的时间
        }
        // 初始化处理好的数据
        List<FixDailyLine> processedData = new ArrayList<>();
        FixDailyLine fixDailyLine = new FixDailyLine(); // 给出头一个时间节点
        fixDailyLine.setFixDate(rawData.get(0).getFixTimeline());   // 设置修理时间
        fixDailyLine.setFixNumber(1);   // 设置修理完成次数为1
        processedData.add(fixDailyLine);
        // 设置处理好的数据
        for (int i = 1; i < rawData.size(); ++i) {
            String currentDate = rawData.get(i).getFixTimeline();   // 拿到当前工单的完成日期
            boolean checked = false;
            for (FixDailyLine processedDatum : processedData) {
                if (currentDate.equals(processedDatum.getFixDate())) {  // 若这个日期已经有记录
                    processedDatum.setFixNumber(processedDatum.getFixNumber() + 1);
                    checked = true;
                    break;
                }
            }
            if (!checked) {
                FixDailyLine newDailyLine = new FixDailyLine(); // 给出一个新时间节点
                newDailyLine.setFixDate(rawData.get(i).getFixTimeline());   // 设置修理时间
                newDailyLine.setFixNumber(1);   // 设置修理完成次数为1
                processedData.add(newDailyLine);
            }
        }
        Collections.sort(processedData);
        return processedData;
    }

    // 获取指定维修部门的所有员工
    public List<FixWorker> selectAllWorkersByDepartment(String fixDepartmentId) {
        return fixMapper.selectAllWorkersByDepartment(fixDepartmentId);
    }

    // 添加与初始化
    // 新增一个报修申请
    public String addFixRequest(FixRequest fixRequest) {
        // 生成一个基于时间戳的id作为主键
        String id = IdConsturctor.idConsturctor();
        fixRequest.setFixId(id);
        // 生成当前时间戳,作为创建时间
        String createTime = CurrentTimeGenerator.timeGenerator();
        fixRequest.setFixSubmitTime(createTime);
        fixMapper.addFixRequest(fixRequest);
        return id;
    }

    // 初始化一个维修工单
    public void addFixOrder(FixOrder fixOrder) {
        fixMapper.addFixOrder(fixOrder);
    }

    // 修改
    // 根据选中的单号进行接单,即将订单状态修改为2已派单并自动填入工作人员id
    public int takeOrderByOrderIdAndWorkerId(String fixWorkerId, String fixTimeline, String fixOrderId) {
        fixMapper.takeOrderByOrderIdAndWorkerId(fixWorkerId, fixTimeline, fixOrderId);
        return 1;
    }

    // 工作人员出发,即将订单状态修改为3进行中并自动填入时间线
    public void ongoingOrderByOrderId(String fixTimeline, String fixOrderId) {
        fixMapper.ongoingOrderByOrderId(fixTimeline, fixOrderId);
    }

    // 工作人员确认维修完成并输入耗材,即将订单状态修改为4已完成并自动填入信息
    public void endOrderByOrderIdAndInfo(String fixMaterial, String fixMaterialCost, String fixLaborCost, String fixTimeline, String fixOrderId) {
        fixMapper.endOrderByOrderIdAndInfo(fixMaterial, fixMaterialCost, fixLaborCost, fixTimeline, fixOrderId);
    }

    // 根据选中的单号,强制关闭工单
    public int forceCloseOrder(String fixOrderId, String fixTimeline) {
        fixMapper.forceCloseOrder(fixOrderId, fixTimeline);
        return 1;
    }

    // 指定员工和工单,为其强制派单
    public int forceAssignOrder(String fixWorkerId, String fixTimeline, String fixOrderId) {
        fixMapper.forceAssignOrder(fixWorkerId, fixTimeline, fixOrderId);
        return 1;
    }

}
