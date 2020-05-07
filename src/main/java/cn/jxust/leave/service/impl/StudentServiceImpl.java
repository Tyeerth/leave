package cn.jxust.leave.service.impl;

import cn.jxust.leave.dao.StudentMapper;
import cn.jxust.leave.pojo.Student;
import cn.jxust.leave.service.StudentService;
import cn.jxust.leave.utils.ResultEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author tyeerth
 * @date 2019/11/10 - 19:09
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 根据学生的id查询学生信息。
     * @param id
     * @return
     */
    @Override
    public Student getStudentById(Integer id) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return studentMapper.selectOne(queryWrapper);
    }

    /**
     * 查询学生登入信息。
     * @param map
     * @return
     */
    @Override
    public Student login(Map<String, Object> map) {
        return studentMapper.login(map);
    }

    /**
     * 管理员实现查询学生总数。
     * @param map
     * @return
     */
    @Override
    public Long getTotal(Map<String, Object> map) {
        return studentMapper.getTotal(map);
    }

    /**
     * 管理员实现查询所有的学生。
     * @param map
     * @return
     */
    @Override
    public List<Student> find(Map<String, Object> map) {
        return studentMapper.find(map);
    }

    /**
     * 管理员实现删除学生。
     * @param s
     */
    @Override
    public void delete(String s) {
        studentMapper.delete(s);
    }

    /**
     * 实现修改学生信息
     * @param student
     */
    @Override
    public void updateStudent(Student student) {
        studentMapper.update(student);
    }

    /**
     * 根据学生的一卡通号来查询相应的学生信息。
     * @param cardNumber
     * @return
     */
    @Override
    public Student getStudentByCardNumber(String cardNumber){
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("card_Number",cardNumber);
       return studentMapper.selectOne(queryWrapper);
    }

    /**
     * 管理员实现添加学生功能。
     * @param student
     * @return
     */
    @Override
    public int addStudent(Student student) {
        return  studentMapper.addStudent(student);
    }

    /**
     * 管理员根据指定的条件查询学生信息。
     * @param student
     * @return
     */
    @Override
    public List<Student> queryStudent(Student student) {
       return studentMapper.queryStudent(student);
    }



    @Override
    public List<Student> queryAllStudent() {
        return studentMapper.queryAllStudent();
    }

    @Override
    public Long getStudentTotal(Map<String, Object> map) {
        return studentMapper.getStudentTotal(map);
    }

    @Override
    public List<Student> queryMapStudent(Map<String, Object> map) {
        return null;
    }

    /**
     * 查到学生
//     * @param map
     * @return
     */
//    @Override
//    public List<Student> queryMapStudent(Map<String, Object> map) {
//        List<Student> studentList = studentMapper.queryMapStudent(map);
//        for (Student student : studentList){
//            Academy academy = new Academy();
//            Campus campus = new Campus();
//            switch (student.getAcademyId()){
//                case  1: academy.setAcademy("信息学院");break;
//                case  2: academy.setAcademy("文法学院");break;
//                case  3: academy.setAcademy("建测学院");break;
//                case  4: academy.setAcademy("理学院");break;
//                case  5: academy.setAcademy("外语外贸学院");break;
//                case  6: academy.setAcademy("机电学院");break;
//                case  7: academy.setAcademy("材料冶金化学学部");break;
//                case  8: academy.setAcademy("商学院");break;
//                case  9: academy.setAcademy("资源与环境工程学院");break;
//                case  10: academy.setAcademy("马克思主义学院");break;
//                case  11: academy.setAcademy("能源与机械工程学院");break;
//                case  12: academy.setAcademy("电气工程学院");break;
//                case  13: academy.setAcademy("软件工程学院");break;
//                case  14: academy.setAcademy("经济管理学院");break;
//            }
//            switch (student.getCampusId()){
//                case 1 :campus.setCampus("红旗校区");break;
//                case 2 :campus.setCampus("黄金校区");break;
//                case 3 :campus.setCampus("南昌校区");break;
//                case 4 :campus.setCampus("西校区");break;
//            }
//            student.setCampus(campus);
//            student.setAcademy(academy);
//        }
//        return studentList;
//    }

    @Override
    public Student getstudentBId(Integer id) {
        return studentMapper.getstudentBBid(id);
    }

    @Override
    public ResultEntity<String> getStudent(String username, String password) {
        return null;
    }

    @Override

    public PageInfo<Student> getTotalStudent(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> list = studentMapper.selectList(null);
        //封装到PageInfo对象中
        return new PageInfo<>(list);
    }
}
