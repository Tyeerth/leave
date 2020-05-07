package cn.jxust.leave.service.impl;

import cn.jxust.leave.dao.EmployeeMapper;
import cn.jxust.leave.pojo.Employee;
import cn.jxust.leave.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description 你可以通过对Service中方法名, 进行基本功能的判断, 复杂的我会提供注解
 *              我对于dao层和Service层采用了的命名规则,如下:
 *              add--->insert;  get--->select;  实体类--->One
 *             目的增加Service层的可读性,同时增加dao层的可利用性
 * @Author tyeerth
 *
 * @Date 13:30 2019/11/12
 **/
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public int addEmployee(Employee employee) {
        return employeeMapper.insertOne(employee);
    }

    @Override
    public int deleteEmployeeById(Integer id) {
        return employeeMapper.deleteOneById(id);
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeMapper.selectOneById(id);
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    @Override
    public Employee getEmployeeByUsername(String username) {
        return employeeMapper.selectOneByUsername(username);
    }

    @Override
    public int updateEmployee(Employee employee) {
        return employeeMapper.updateOne(employee);
    }

    @Override
    public int updateEmployeeByIdIfNecessary(Integer id, Employee employee) {
        return employeeMapper.updateOneByIdIfNecessary(id, employee);
    }

    /**
     * 管理员根据指定的信息查找员工信息。
     * @param employee
     * @return
     */
    @Override
    public List<Employee> queryEmployee(Employee employee) {
        return employeeMapper.queryEmployee(employee);
    }

    /**
     * 管理员删除员工
     * @param s
     */
    public void delete(String s) {
        employeeMapper.deleteEmployeeById(s);
    }

    /**
     * 删除员工时删除中间表
     * @param s
     */
    @Override
    public void deleteMidle_employee(String s) {
        employeeMapper.delteMidle_form(s);
    }

    @Override
    public List<Employee> getEmployeeList(Integer offset, Integer limit) {
        return employeeMapper.selectByLimitAndOffset(offset, limit);
    }
    @Override
    public int getEmployeeCount(){
        return employeeMapper.countOnes();
    }

//    @Override
//    public List<EmpLeaFrom> getClasssInfoList(int offset,int limit){
//        List<EmpLeaFrom> empLeaFromList= employeeMapper.getEmpLeaFromClazz(offset,limit);
//
//        for(EmpLeaFrom empLeaFrom:empLeaFromList) {
//            empLeaFrom.setTodayPeopleCount(employeeMapper.selectLeaveFormCountsByDays(1, empLeaFrom));
//            empLeaFrom.setYesterdayPeopleCount(employeeMapper.selectLeaveFormCountsByDays(2, empLeaFrom));
//            empLeaFrom.setThisWeekPeopleCount(employeeMapper.selectLeaveFormCountsByDays(3, empLeaFrom));
//        }
//
//        return empLeaFromList;
//    }
//    @Override
//    public List<EmpLeaFrom> getClasssInfoListLimt(Integer campus_id,Integer academy_id){
//        List<EmpLeaFrom> empLeaFromList= employeeMapper.getEmpLeaFromClazzLimt(campus_id,academy_id);
//
//        for(EmpLeaFrom empLeaFrom:empLeaFromList) {
//            empLeaFrom.setTodayPeopleCount(employeeMapper.selectLeaveFormCountsByDays(1, empLeaFrom));
//            empLeaFrom.setYesterdayPeopleCount(employeeMapper.selectLeaveFormCountsByDays(2, empLeaFrom));
//            empLeaFrom.setThisWeekPeopleCount(employeeMapper.selectLeaveFormCountsByDays(3, empLeaFrom));
//        }
//
//        return empLeaFromList;
//    }
    @Override
    public int getClasssInfoCount(){
        return employeeMapper.getClasssInfoCount();
    }

//    @Override
//    public List<Academy> getAcademyList(){
//        return employeeMapper.getAcademyList();
//    }

    @Override
    public int getCountEmployee(Map<String, Object> map) {
        return employeeMapper.getCountEmployee(map);
    }

    @Override
    public List<Employee> getMapEmployee(Map<String, Object> map) {
        return employeeMapper.getMapEmployee(map);
    }

    @Override
    public Employee getEmployee(Integer id) {
        return employeeMapper.getEmployeeById(id);
    }
}
