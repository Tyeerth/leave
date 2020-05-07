package cn.jxust.leave.dao;

import cn.jxust.leave.pojo.Process;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author JJ
 * @date 2019/11/12 8:53
 */
@Repository
public interface ProcessMapper {

    void saveProcess(Process process) throws Exception;

    void updateProcess(Process process) throws Exception;

    void deleteProcess(@Param("leaveFormId") String leaveFormId) throws Exception;


    /**
     * 查询需要当前用户审批的请假流程
     * @return
     */
    List<Process> queryAllProcess(Map<String, Object> map);

    /**
     * 员工查询已经审核过的请假流程。
     * @param map
     * @return
     */
    List<Process> queryApprovedProcess(Map<String, Object> map);

    /**
     * 员工查询正在审核的流程
     * @param map
     * @return
     */
    List<Process> queryApprovingProcess(Map<String, Object> map);

    /**
     * 员工查询当前未审核的流程。
     * @param map
     * @return
     */
    List<Process> queryUnapprovedProcess(Map<String, Object> map);

    /**
     * 查询请假表对应的请假流程。
     * @param id
     * @return
     */
    List<Process> queryProcessByLeaveFormId(String id);


    /**
     * 删除剩余的请假流程表
     *
     */
    void deleteRestProcess(Map<String, Object> map);

//    List<StuLeaForPros> selectStuLeaForProsForEmployee(int studentId);
//
//    StuLeaForPros selectStuLeaForProsForMonitor(int studentId);
}