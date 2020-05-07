package cn.jxust.leave.controller;


import cn.jxust.leave.constant.LoginConsts;
import cn.jxust.leave.pojo.LeaveForm;
import cn.jxust.leave.pojo.Student;
import cn.jxust.leave.service.LeaveFormService;
import cn.jxust.leave.service.StudentService;
import cn.jxust.leave.utils.RandomValidateCode;
import cn.jxust.leave.utils.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tyeerth
 * @date 2019/11/11 - 10:23
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {

    /**
     * 存放学生的logg常量。
     */
    private static  final Logger logger =  LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;

    @Autowired
    LeaveFormService leaveFormService;

    @GetMapping("/getApprovingLeaveForm")
    @ResponseBody
    public ResultEntity<PageInfo<LeaveForm>> getApprovingLeaveForm(
            HttpServletRequest request,// pageNum默认值使用1
                                                          @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
                                                          // pageSize默认值使用5
                                                          @RequestParam(value="pageSize", defaultValue="5") Integer pageSize){
        Student student = (Student) request.getSession().getAttribute(LoginConsts.STUDENT);
        PageInfo<LeaveForm> pageInfo = leaveFormService.getStudentApprovingLeaveForm(student.getId(),pageNum,pageSize);
        return ResultEntity.successWithData(pageInfo);
    }
    @GetMapping("/getApprovedLeaveForm")
    @ResponseBody
    public ResultEntity<PageInfo<LeaveForm>> getApprovedLeaveForm(
            HttpServletRequest request,// pageNum默认值使用1
                                                          @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
                                                          // pageSize默认值使用5
                                                          @RequestParam(value="pageSize", defaultValue="5") Integer pageSize){
        Student student = (Student) request.getSession().getAttribute(LoginConsts.STUDENT);
        PageInfo<LeaveForm> pageInfo = leaveFormService.getApprovedLeaveForm(student.getId(),pageNum,pageSize);
        return ResultEntity.successWithData(pageInfo);
    }
    @GetMapping("/getUnApprovedLeaveForm")
    @ResponseBody
    public ResultEntity<PageInfo<LeaveForm>> getUnApprovedLeaveForm(
            HttpServletRequest request,// pageNum默认值使用1
                                                          @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
                                                          // pageSize默认值使用5
                                                          @RequestParam(value="pageSize", defaultValue="5") Integer pageSize){
        Student student = (Student) request.getSession().getAttribute(LoginConsts.STUDENT);
        PageInfo<LeaveForm> pageInfo = leaveFormService.getUnApprovedLeaveForm(student.getId(),pageNum,pageSize);
        return ResultEntity.successWithData(pageInfo);
    }
    /**
     * 分页条件查询同班学生列表
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    @ResponseBody
    public ResultEntity<PageInfo<Student>>
    queryStudentList(// pageNum默认值使用1
                     @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
                     // pageSize默认值使用5
                     @RequestParam(value="pageSize", defaultValue="5") Integer pageSize) throws Exception {
        PageInfo<Student> pageInfo = studentService.getTotalStudent(pageNum,pageSize);
        return ResultEntity.successWithData(pageInfo);
    }
    @GetMapping("/getCancelForLeave")
    @ResponseBody
    public ResultEntity<PageInfo<LeaveForm>> getCancelForLeave(
            @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
            // pageSize默认值使用5
            @RequestParam(value="pageSize", defaultValue="5") Integer pageSize,HttpServletRequest request
    ){
        Student student= (Student)request.getSession().getAttribute(LoginConsts.STUDENT);
        PageInfo<LeaveForm> pageInfo = leaveFormService.getCancelLeaveForm(student.getId(),pageNum,pageSize);
        if (pageInfo.getTotal()==0){
            return ResultEntity.failed("当前学生未请假");
        }else {
            return ResultEntity.successWithData(pageInfo);
        }
    }
    /**
     * 学生销假
     * @return
     */
    @RequestMapping("/cancelLeaveForm")
    @ResponseBody
    public ResultEntity<String> cancelLeaveForm(HttpServletRequest request) throws Exception {
        Student student = (Student) request.getSession().getAttribute(LoginConsts.STUDENT);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id",student.getId());
        List<LeaveForm> leaveForm= leaveFormService.getApprovingLeaveForm(map);
        for (LeaveForm leaveForm1 : leaveForm){
            leaveForm1.setState("-2");
            leaveFormService.updateLeaveForm(leaveForm1);
        }
        return ResultEntity.successWithData("销假成功");
    }

    /**
     * 修改学生信息。
     * @param password
     * @param newPassword
     * @param newPasswords
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateStudent ",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity<String> updateStudent(@RequestParam("password")String password, @RequestParam("newPassword")String newPassword,
                                 @RequestParam("newPasswords") String newPasswords,
                                 HttpServletRequest request) {
        Student studentBySession = (Student) request.getSession().getAttribute(LoginConsts.STUDENT);
        Student studentById = studentService.getStudentById(studentBySession.getId());
        if (password.equals(studentById.getPassword())){
            if (newPassword.equals(newPasswords)){
                Student stu=new Student();
                stu.setId(studentBySession.getId());
                stu.setPassword(newPassword);
                studentService.updateStudent(stu);
                return ResultEntity.successWithData("密码修改成功");
            }else {
                return ResultEntity.failed("两次密码输入不一致");
            }
        }else {
            return ResultEntity.failed("原密码输入错误");
        }
    }

    /**
     * 跳转到修改学生信息页面
     * @param request
     * @return
     * @Author   lcs
     */
    @RequestMapping("/toStudentLeaveForm")
    public ModelAndView toStudentLeaveForm(HttpServletRequest request){
        Student student = (Student) request.getSession().getAttribute("student");
        System.out.println(student);
        ModelAndView mv =new ModelAndView();
        mv.addObject("name",student.getName());
        mv.addObject("cardNumber",student.getCardNumber());
        //跳转到StudentAccountjsp页面。
        mv.setViewName("/student/studentLeaveForm");
        return mv;
    }


    /**
     * 测试根据用户ID查询用户信息。
     * @param id
     * @return
     */
//    @RequestMapping("/getStudentById/{id}")
//    @ResponseBody
//    public ResultEntity<Student> getStudentById(@PathVariable("id") Integer id){
//        Student student = studentService.getStudentById(id);
//        if (student==null){
//            return ResultEntity.failed("无当前ID的学生");
//        }else{
//            return ResultEntity.successWithData(student);
//        }
//    }
    @GetMapping("/getStudentByID")
    @ResponseBody
    public ResultEntity<Student> getStudentById(@RequestParam("id") Integer id){
        Student student = studentService.getStudentById(id);
        if (student==null){
            return ResultEntity.failed("无当前ID的学生");
        }else{
            return ResultEntity.successWithData(student);
        }
    }
    @RequestMapping("/test/getStudent")
    @ResponseBody
    public Student TestGetstudent(){
        return  studentService.getStudentById(2);
    }
    /**
     * 跳转到学生个人信息页面的同时显示学生信息。
     * @param request
     * @return
     */
    @RequestMapping(value = "/getStudent")
    @ResponseBody
    public ResultEntity<Student> getStudent(HttpServletRequest request){
        Student student = (Student) request.getSession().getAttribute(LoginConsts.STUDENT);
        Student studentById = studentService.getStudentById(student.getId());
        System.out.println("通过当前session获取到的学生信息"+student.toString());
        if (studentById.getGender().equals("1")){
            studentById.setGender("男");
        }
        if (studentById.getGender().equals("0")){
            studentById.setGender("女");
        }
        if (studentById.getState().equals("1")){
            studentById.setState("在读");
        }
        if(studentById.getState().equals("0")){
            studentById.setState("毕业");
        }
        if (studentById.getState().equals("-1")){
            studentById.setState("退学");
        }
        return ResultEntity.successWithData(studentById);
    }
    /**
     * 获取生成验证码显示到 UI 界面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value="/checkCode")
    public void checkCode(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");
        //设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            //输出图片方法
            randomValidateCode.getRandcode(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
