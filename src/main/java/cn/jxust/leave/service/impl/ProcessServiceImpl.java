package cn.jxust.leave.service.impl;

import cn.jxust.leave.constant.LoginConsts;
import cn.jxust.leave.dao.ProcessMapper;
import cn.jxust.leave.po.Employee;
import cn.jxust.leave.po.Process;
import cn.jxust.leave.po.vo.StuLeaForPros;
import cn.jxust.leave.service.ProcessService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JJ
 * @date 2019/11/11 - 16:55
 */
@Service
public class ProcessServiceImpl implements ProcessService {

    @Resource(name = "processMapper")
    ProcessMapper processMapper;

    @Override
    public void saveProcess(Process process) throws Exception {
        processMapper.saveProcess(process);
    }

    @Override
    public void updateProcess(Process process) throws Exception {
        processMapper.updateProcess(process);
    }

    @Override
    public void deleteProcess(String leaveFormId) throws Exception {
        processMapper.deleteProcess(leaveFormId);
    }

    /**
     * 查询需要当前用户审批的请假流程
     * 注意：这里使用了shiro安全框架中在服务层中获取到session。
     * @return
     */
    @Override
    public List<Process> queryAllProcess() {
        Subject currentUser = SecurityUtils.getSubject();
        Session currentUserSession = currentUser.getSession();
        Map<String,Object> map = new HashMap<>();
        //先判断出当前登入用户的roleid。
        Employee employee = (Employee)currentUserSession.getAttribute(LoginConsts.employee);
        Integer roleId = employee.getRoleId();
        //获取当前员工的id。
        Integer employeeId = employee.getId();
        int processState =1;
        map.put("approveId",employeeId);
        map.put("processState",processState);
        //查询所有未审核的请假流程表、请假流程表中approver_group_id是否为当前登入用户的roleid、
        // 请假流程表中的approver_id是否为当前用户的id，请假流程表中的状态为1（已激活）

        return processMapper.queryAllProcess(map);
    }

    /**
     * 员工查询已经审核通过的请假流程。
     * @param map
     * @return
     */
    @Override
    public List<Process> queryApprovedProcess(Map<String, Object> map) {
         return processMapper.queryApprovedProcess(map);
    }

    /**
     * 员工查询正在审核中的请假流程。
     * @param map
     * @return
     */
    @Override
    public List<Process> queryApprovingProcess(Map<String, Object> map) {
        return processMapper.queryApprovingProcess(map);
    }

    /**
     * 员工查询当前未审核的流程
     * @param map
     * @return
     */
    @Override
    public List<Process> queryUnapprovedProcess(Map<String, Object> map) {
        return processMapper.queryUnapprovedProcess(map);
    }

    /**
     * 查到请假表对应的流程
     * @param id
     */
    @Override
    public List<Process> queryProcessByLeaveFormId(String id) {
        return processMapper.queryProcessByLeaveFormId(id);
    }

    /**
     * 删除剩余的请假流程表
     * @param id
     * @param approverGroupId
     */
    @Override
    public void deleteRestProcess(String id, Integer approverGroupId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("leaveFormId",id);
        map.put("approverGroupId",approverGroupId);
        processMapper.deleteRestProcess(map);
    }

    @Override
    public List<StuLeaForPros> queryStudentProcessesListByStudentId(int studentId){
        List<StuLeaForPros> stuLeaForProsList = processMapper.selectStuLeaForProsForEmployee(studentId);
        stuLeaForProsList.add(processMapper.selectStuLeaForProsForMonitor(studentId));
        return stuLeaForProsList;
    }
}
