package cn.jxust.leave.service.impl;

import cn.jxust.leave.dao.RoleMapper;
import cn.jxust.leave.pojo.Employee;
import cn.jxust.leave.pojo.LeaveForm;
import cn.jxust.leave.service.EmployeeService;
import cn.jxust.leave.service.LeaveFormService;
import cn.jxust.leave.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 *
 * @Author   lcs
 * @Date 22:12 2019/11/14
 **/

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    LeaveFormService leaveFormService;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public String getRoleNameById(Integer id){
        return roleMapper.selectRoleNameById(id);
    }

    @Override
    public List<LeaveForm> employeeGetLeaveFormById(Integer  id){
        Employee employee =employeeService.getEmployeeById(id);
        if(employee!=null){
            return roleMapper.selectLeaveFormListForEmployee(id);
        }

        return null;
    }


}
