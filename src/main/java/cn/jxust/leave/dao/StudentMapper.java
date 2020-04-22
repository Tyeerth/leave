package cn.jxust.leave.dao;

import cn.jxust.leave.po.Employee;
import cn.jxust.leave.po.LeaveForm;
import cn.jxust.leave.po.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StudentMapper  {
    /**
     * 根据学生id查询学生。
     * @param id
     * @return
     */
    Student getStudentById(Integer id);

    /**
     * 查询学生登入信息。
     * @param map
     * @return
     */
    Student login(Map<String, Object> map);

    /**
     * 实现查询学生总数。
     * @param map
     * @return
     */
    Long getTotal(Map<String, Object> map);

    /**
     * 实现分页查询学生信息。
     * @param map
     * @return
     */
    List<Student> find(Map<String, Object> map);

    /**
     * 实现删除学生。
     * @param s
     */
    void delete(String s);

    /**
     * 实现修改学生信息。
     * @param student
     */
    void update(Student student);

    /**
     * 根据学生的一卡通号查询到相应的学生。
     * @param cardNumber
     * @return
     */
    Student getStudentByCardNumber(@Param(value = "cardNumber") String cardNumber);

    /**
     * 管理员实现添加学生
     * @param student
     * @return
     */
    int addStudent(Student student);

    /**
     * 管理员根据指定的条件查询学生。
     * @param student
     * @return
     */
    List<Student> queryStudent(Student student);

    /**
     * 批量插入学生信息。
     * @param studentList
     */
    void insertInfoBatch(List<Student> studentList);

    /**
     * 根据请假表中的学生id查到对应的学生信息
     * @param leaveForm
     * @return
     */
    Student getstudentByLeaveFormId(LeaveForm leaveForm);

    /**
     * 学生查到自己的班长是谁。
     * @param student
     * @return
     */
    Student queryMonitor(Student student);

    /**
     * 查询到学生的辅导员是谁
     * @param student
     * @return
     */
    Employee queryInstructor(Student student);

    /**
     * 查询自己的学院领导是谁。
     * @param student
     * @return
     */
    Employee queryAcademyMaster(Student student);

    /**
     * 查询学工部
     * @return
     */
    Employee queryAffairManager();

    /**
     * 查询自己学校的主管领导是谁
     * @param student
     * @return
     */
    Employee queryCampusManager(Student student);

    /**
     * 查找所有学生
     */
    List<Student> queryAllStudent();

    /**
     * 查找学生总数
     * @return
     */
    Long getStudentTotal(Map<String, Object> map);

    /**
     * 分页查找学生信息
     * @param map
     * @return
     */
    List<Student> queryMapStudent(Map<String, Object> map);

    /**
     * 根据id查询学生
     * @param id
     * @return
     */
    Student getstudentBBid(Integer id);

    /**
     * 查找学生
     * @param username
     * @param password
     * @return
     */
    Student getStudentById(String username, String password);

    /**
     * 查询所有的学生信息
     * @return
     */
    List<Student> getTotalStudent();
}