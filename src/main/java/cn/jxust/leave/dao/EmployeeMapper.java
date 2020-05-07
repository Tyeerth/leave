package cn.jxust.leave.dao;

import cn.jxust.leave.pojo.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description  这里使用了Mapper映射文件和注解两种方式
 *              其中Delete推荐使用注解,update推荐使用Mapper映射文件
 * @Author   lcs
 * @Date 13:15 2019/11/12
 **/
@Repository
public interface EmployeeMapper {
    String TABLE_NAME = "employee";
    //INSERT
    String INSERT_FIELDS = "name,gender,role_id,phone_number,username,password";
    //SELECT
    String SELECT_NAME_PASSWORD = "username,password";
    String SELECT_NAME_PASSWORD_ROLE = "username,password,phone_number,role_id";
    String SELECT_ALL_LIST = "id," + INSERT_FIELDS;

    /**
     * =================================增加============================================
     */
    int insertOne(Employee employee);

    /**
     * =================================删除============================================
     * xxx_id=#{xxxId}
     */
    @Delete({"DELETE FROM", TABLE_NAME, "WHERE id=#{id}"})
    int deleteOneById(@Param("id") Integer id);

    /**
     * =================================查询============================================
     */

    Employee selectOneById(@Param("id") Integer id);


    /**
     * 实现员工登入功能
     * @param username
     * @return
     */
    Employee selectOneByUsername(@Param("username") String username);

    @Select({"SELECT", SELECT_ALL_LIST, "FROM", TABLE_NAME, "LIMIT #{offset}, #{limit}"})
    List<Employee> selectByLimitAndOffset(@Param("offset") Integer offset,
                                          @Param("limit") Integer limit);

    @Select({"select count(*) from", TABLE_NAME})
    int countOnes();

    /**
     * =================================修改============================================
     * 因为在update中用注解的话,对拼接SQL语句十分困难,所以建议使用xxxMapper.xml,方便调用动态sql语句
     */

    //已在Mapper映射文件中,配置
    int updateOneByIdIfNecessary(@Param("id") Integer id,
                                 @Param("e") Employee employee);

    /**
     * 员工修改个人信息
     * @param employee
     * @return
     */
    int updateOne(Employee employee);  //不是动态 SQL,不能进行非空判断

    /**
     * 管理员根据指定条件查找教职工信息。
     * @param employee
     * @return
     */
    List<Employee> queryEmployee(Employee employee);

    /**
     * 管理员根据员工id删除对应的员工，并删除中间表。
     * @param s
     */
    void deleteEmployeeById(String s);

    /**
     * 删除员工的同时删除中间表。
     * @param s
     */
    void delteMidle_form(String s);


    /**
     * @Description
     *
     * @param timeType    时间范围: 1 ==> 今天, 2 ==> 昨天, 3 ==> 本周
     * @Author   lcs
     **/
//    int selectLeaveFormCountsByDays(@Param("timeType") int timeType, @Param("e") EmpLeaFrom empLeaFrom);

    int getClasssInfoCount();


    /**
     * 指定条件查询员工总数
     * @param map
     * @return
     */
    int getCountEmployee(Map<String, Object> map);

    /**
     * 指定条件查询学生
     * @param map
     * @return
     */
    List<Employee> getMapEmployee(Map<String, Object> map);

    /**
     * 根据员工id查询员工
     * @param id
     * @return
     */
    Employee getEmployeeById(Integer id);
}
