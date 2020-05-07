package cn.jxust.leave.service;

import cn.jxust.leave.pojo.Employee;

import java.util.List;
import java.util.Map;

/**
 * 员工服务层的接口。
 * @author tyeerth
 * @date 2019/11/13 - 19:05
 **/
public interface EmployeeService {


    int addEmployee(Employee employee);

    int deleteEmployeeById(Integer id);

    Employee getEmployeeById(Integer id);

    /**
     * 实现员工登入功能。
     * @param username
     * @return
     */
    Employee getEmployeeByUsername(String username);

    /**
     * @Description   分页查询
     *               范围从(offset+1)---(offset+limit)
     *               如offset=10，limit=5的时候，就会从数据库第11行记录开始返回5条查询结果
     * @param offset 返回记录行的偏移量
     * @param limit 返回记录最大行数
     * @Author   lcs
     * @Date 13:39 2019/11/12
     **/
    List<Employee> getEmployeeList(Integer offset, Integer limit);

    int getEmployeeCount();

    /**
     * @Description 其使用的最佳条件, 如先用getEmployeeById查询到employee,
     * 在用set方法对employee里的属性修改,后调用该方法进行update更新
     **/
    int updateEmployee(Employee employee);
    /**
     * @Description 采用动态sql语句设计,只要id存在, 可以任意针对某一属性值修改
     **/
    int updateEmployeeByIdIfNecessary(Integer id, Employee employee);

    /**
     * 管理员查找员工。
     * @param employee
     * @return
     */
    List<Employee> queryEmployee(Employee employee);

    /**
     * 管理员根据员工id删除员工。
     * @param s
     */
    void delete(String s);

    /**
     * 删除中间表的内容。
     * @param s
     */
    void deleteMidle_employee(String s);


    int getClasssInfoCount();


    int getCountEmployee(Map<String, Object> map);

    List<Employee> getMapEmployee(Map<String, Object> map);

    Employee getEmployee(Integer id);
}
