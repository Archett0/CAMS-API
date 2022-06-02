package com.xiangliban.managementsystem.dao.mapper;

import com.xiangliban.managementsystem.pojo.Fix.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘元驰
 * @Date: 2021/12/13/21:41
 * @Description:
 */

@Mapper     // 表示mybatis的mapper类
@Repository // 整合进spring
public interface FixMapper {

    // 查询
    // 根据指定的维修人员id,查询维修工单并合并提供该工单的报修详细信息
    List<FixOrderWithDetails> selectAllFixDetailsByWorkerId(String fixWorkerId);

    // 查询所有未处理的报修申请,仅提供少量信息
    List<FixOrderWithBrief> selectAllUnprocessedOrder();

    // 根据工单id,获得并分析时间线
    String analysisTimeline(String fixOrderId);

    // 查询所有维修工单
    List<FixOrder> selectAllFixOrder();

    // 查询所有报修申请
    List<FixRequest> selectAllFixRequest();

    // 查询全部维修工单并合并提供该工单的报修详细信息
    List<FixOrderWithDetails> selectAllFixDetails();

    // 根据指定的用户id,查询全部维修工单并合并提供该工单的报修详细信息
    List<FixOrderWithDetails> selectAllFixDetailsByUserId(String fixUserId);

    // 获取所有维修部门
    List<FixDepartment> selectAllDepartments();

    // 获取系统中工单状态个数
    List<FixStatusDiagram> getFixStatusNumber();

    // 获取系统中每日被完成工单个数
    List<FixDailyDiagram> getDailyFixNumber();

    // 获取系统中工作人员完成工单个数
    List<FixWorkerDiagram> getWorkerFixNumber();

    // 获取系统中维修部门完成工单个数
    List<FixWorkerDiagram> getDepartFixNumber();

    // 获取指定维修部门的所有员工
    List<FixWorker> selectAllWorkersByDepartment(String fixDepartmentId);

    // 添加与初始化
    // 新增一个报修申请
    void addFixRequest(@Param("fixRequest") FixRequest fixRequest);

    // 初始化一个维修工单
    void addFixOrder(@Param("fixOrder") FixOrder fixOrder);

    // 修改
    // 根据选中的单号进行接单,即将订单状态修改为2已派单并自动填入工作人员id
    void takeOrderByOrderIdAndWorkerId(String fixWorkerId, String fixTimeline, String fixOrderId);

    // 工作人员出发,即将订单状态修改为3进行中并自动填入时间线
    void ongoingOrderByOrderId(String fixTimeline, String fixOrderId);

    // 工作人员确认维修完成并输入耗材,即将订单状态修改为4已完成并自动填入信息
    void endOrderByOrderIdAndInfo(String fixMaterial, String fixMaterialCost, String fixLaborCost, String fixTimeline, String fixOrderId);

    // 根据选中的单号,强制关闭工单
    void forceCloseOrder(String fixOrderId, String fixTimeline);

    // 指定员工和工单,为其强制派单
    void forceAssignOrder(String fixWorkerId, String fixTimeline, String fixOrderId);


    // 删除（可能并不有这个需求）
}
