package cn.jxust.leave.service;

import cn.jxust.leave.po.vo.StuLeaForPros;
import  cn.jxust.leave.po.Process;
import java.util.List;
import java.util.Map;

/**
 * @author JJ
 * @date 2019/11/11 - 16:53
 */

public interface ProcessService {

    void saveProcess(Process process) throws Exception;

    /**
     * @param process
     * @throws Exception
     */
    void updateProcess(Process process) throws Exception;

    void deleteProcess(String leaveFormId) throws Exception;

    /**
     * 查询需要当前用户审批的请假流程
     * @return
     */
    List<Process> queryAllProcess();

    /**
     * 员工查询自己已经审核过的请假流程。
     * @param map
     * @return
     */
    List<Process> queryApprovedProcess(Map<String, Object> map);

    /**
     * 员工查询审批中的请假表
     * @param map
     * @return
     */
    List<Process> queryApprovingProcess(Map<String, Object> map);

    /**
     * 员工查询当前未审核的流程
     * @param map
     * @return
     */
    List<Process> queryUnapprovedProcess(Map<String, Object> map);


    /**
     * 查到请假表对应的请假流程
     * @param id
     */
    List<Process> queryProcessByLeaveFormId(String id);

    /**
     * 删除剩余的请假表
     * @param id
     * @param approverGroupId
     */
    void deleteRestProcess(String id, Integer approverGroupId);

    /**
     * @Description 通过studentId查询,
     *    对应的请假的流程
     * @Author   lcs
     **/
    List<StuLeaForPros> queryStudentProcessesListByStudentId(int studentId);
}
