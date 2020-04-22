package cn.jxust.leave.service;

import cn.jxust.leave.po.LeaveForm;
import cn.jxust.leave.po.Student;

import java.util.List;
import java.util.Map;


/**
 * @author JJ
 * @date 2019/11/10 - 15:07
 */
public interface LeaveFormService {


    void saveLeaveForm(LeaveForm leaveForm) throws Exception;

    void deleteLeaveForm(String id) throws Exception;

    LeaveForm getLeaveFormInfo(String id) throws Exception;



    /**
     * =============================================================================
     */
    LeaveForm getLeaveFormByStudentId(Integer studentId);



    List<LeaveForm> studentGetLeaveFormByCardNumber(String cardNumber);

    int getLeaveFormCountsByDays(int timeType);

    void insertLeaveForm(LeaveForm leaveForm)throws Exception;


    LeaveForm queryLeaveFormById(String leaveFormId);

    /**
     * 查询请假表中的学生信息
     * @param studentId
     * @return
     */
    Student queryStudentByLeaveForm(Integer studentId);

    /**
     * 查询已经审核通过的请假表
     * @return
     */
    List<LeaveForm> getApprovedLeaveFormByCardNumber();

    /**
     * 返回请假表的总数
     * @return
     */
    Long getTotal(Integer Id);

    List<LeaveForm> getApprovedLeaveFormById(Map<String, Object> map);

    /**
     * 查询当前为审核的请假表
     * @param map
     * @return
     */
    List<LeaveForm> getUnApprovedLeaveFormById(Map<String, Object> map);

    Long getUnTotal(Integer id);

    /**
     * 查询所有的请假记录
     * @param map
     * @return
     */
    List<LeaveForm> getAllLeaveFormById(Map<String, Object> map);

    Long getAllTotal(Integer id);

    List<LeaveForm> getApprovingLeaveFormById(Map<String, Object> map);

    Long getApprovingTotal(Integer id);

    List<LeaveForm> getApprovingLeaveForm(Map<String, Object> map);

    /**
     * 修改请假表
     * @param leaveForm1
     */
    void updateLeaveForm(LeaveForm leaveForm1);

    List<Integer> selectWeekLeaveFormCountsByDays();
}
