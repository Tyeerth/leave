package cn.jxust.leave.service;

import cn.jxust.leave.po.Student;
import cn.jxust.leave.utils.ResultEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author tyeerth
 * @date 2019/11/10 - 19:07
 */
public interface StudentService {
    Student getStudentById(Integer id);

    /**
     * 查询学生登入信息。
     * @param map
     * @return
     */
    Student login(Map<String, Object> map);

    /**
     * 查询学生总数
     * @param map
     * @return
     */
    Long getTotal(Map<String, Object> map);

    /**
     * 分页查询所有学生。
     * @param map
     * @return
     */
    List<Student> find(Map<String, Object> map);

    /**
     * 删除学生。
     * @param s
     */
    void delete(String s);

    /**
     * 修改学生信息。
     * @param student
     */
    void updateStudent(Student student);


    /**
     * 根据学生的一卡通号查询到相应的学生信息。
     * @param cardNumber
     * @return
     */
    Student getStudentByCardNumber(String cardNumber);

    /**
     * 管理员实现添加学生。
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
     * 查找所有学生
     */
    List<Student> queryAllStudent();

    Long getStudentTotal(Map<String, Object> map);

    /**
     * 指定条件查找学生
     * @param map
     * @return
     */
    List<Student> queryMapStudent(Map<String, Object> map);

    Student getstudentBId(Integer id);

    ResultEntity<String> getStudent(String username, String password);

    /**
     * 查询所有的学生信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Student> getTotalStudent(Integer pageNum, Integer pageSize);
}
