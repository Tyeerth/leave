package cn.jxust.leave.dao;

import cn.jxust.leave.pojo.LeaveForm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 *
 * @author JJ
 * @date 2019/11/12 18:52
 */
@Repository
public interface LeaveFormMapper extends BaseMapper<LeaveForm> {

    void saveLeaveForm(LeaveForm leaveForm) throws Exception;

    void deleteLeaveForm(@Param("id") String id) throws Exception;

    LeaveForm getLeaveFormInfo(@Param("id") String id) throws Exception;



    /**
     * =============================================================================
     */


    LeaveForm selectLeaveFormByStudentId(@Param("studentId") Integer studentId);

    List<LeaveForm> selectLeaveFormListForMonitor(@Param("majorId") Integer majorId,
                                                  @Param("clazz") String clazz,
                                                  @Param("grade") Integer grade);


    /**
     * @Description
     *
     * @param timeType    时间范围: 1 ==> 今天, 2 ==> 昨天, 3 ==> 本周,4 ==>上周;
     * @Author   lcs
     **/
    int selectLeaveFormCountsByDays(@Param("timeType") int timeType);

    int selectWeekLeaveFormCountsByDays(@Param("timeType") int timeType);


    /**
     * 查询到对应的请假表
     * @param leaveFormId
     */
    LeaveForm queryLeaveFormById(String leaveFormId);

    /**
     * 查询已经审核过的请假表
     * @param cardNumber
     * @return
     */
    List<LeaveForm> queryApprovedLeaveForm(Integer Id);

    /**
     * 返回审核通过的请假表数量
     * @return
     */
    Long getTotal(Integer Id);

    List<LeaveForm> getApprovedLeaveFormById(Map<String, Object> map);

    List<LeaveForm> getUnApprovedLeaveformById(Map<String, Object> map);

    /**
     * 查询审核未通过的请假总数
     * @param id
     * @return
     */
    Long getUnTotal(Integer id);

    /**
     * 获取当前用户所有的请假记录
     * @param map
     * @return
     */
    List<LeaveForm> getAllLeaveForm(Map<String, Object> map);

    /**
     * 查询请假记录的总数
     * @param id
     * @return
     */
    Long getAllTotal(Integer id);

    /**
     * 查询正在审核的请假表
     * @param map
     * @return
     */
    List<LeaveForm> getApprovingLeaveForm(Map<String, Object> map);

    /**
     * 查询正在审核的请假表的数量
     * @param id
     * @return
     */
    Long getApprovingTotal(Integer id);

    /**
     * 查询正在审核的请假表
     * @param map
     * @return
     */
//    List<LeaveForm> getApprovingLeaveFormById(Map<String, Object> map);

    /**
     * 修改请假表的信息
     *
     * @param leaveForm1
     */
    void updateLeaveForm(LeaveForm leaveForm1);
}