package cn.jxust.leave.controller;


import cn.jxust.leave.HandlerExceptionResolver.SysException;
import cn.jxust.leave.constant.LoginConsts;
import cn.jxust.leave.po.LeaveForm;
import cn.jxust.leave.po.PageBean;
import cn.jxust.leave.po.Student;
import cn.jxust.leave.po.vo.LeaveFormStudent;
import cn.jxust.leave.service.LeaveFormService;
import cn.jxust.leave.service.StudentService;
import cn.jxust.leave.utils.JsonMsg;
import cn.jxust.leave.utils.RandomValidateCode;
import cn.jxust.leave.utils.ResultEntity;
import cn.jxust.leave.utils.TimeUtils;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
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

     /**
     * 分页条件查询同班学生列表
     * @param pageNum
     * @param request
     * @param
     * @return
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

    /**
     * 学生销假
     * @return
     */
    @RequestMapping("cancelLeaveForm")
    @ResponseBody
    public ResultEntity<String> cancelLeaveForm(HttpServletRequest request) throws Exception {
        System.out.println("销假成功");
        Student student = (Student) request.getSession().getAttribute(LoginConsts.STUDENT);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id",student.getId());
        List<LeaveForm> leaveForm= leaveFormService.getApprovingLeaveForm(map);
        for (LeaveForm leaveForm1 : leaveForm){
            leaveForm1.setState("1");
            leaveFormService.updateLeaveForm(leaveForm1);
        }
        return ResultEntity.successWithData("销假成功");
    }
    @RequestMapping("/list2")
    public ModelAndView
    queryStudentList(HttpServletRequest request) throws Exception {
        ModelAndView mv =new ModelAndView();
        List<Student> list = studentService.find(null);
        mv.addObject("list",list);
        return mv;
    }


    @RequestMapping("/toLeaveApply")
    public ModelAndView leaveApply(ModelAndView mv,
                                   @RequestParam(value = "viewName",required = true)String viewName){
        mv.setViewName(viewName);
        System.out.println("跳转到申请请假页面");
        return mv ;
    }
    @RequestMapping("/tologin")
    public String tologin(){
        System.out.println("跳转到登入界面");
        return "login";
    }

    @RequestMapping("/toImport")
    public String  toImport(){
        System.out.println("跳转到导入页面");
        return "student/import2";
    }
    @RequestMapping("/toImport1")
    public String  toImport1(){
        System.out.println("跳转到导入页面");
        return "student/newCalendar";
    }
    @RequestMapping("/toCalender")
    public String toCalender(){
        System.out.println("跳转到校历页面");
        return "student/Calender";
    }
    @RequestMapping("/toBreakWinter")
    public String toBreakWinder(){
        System.out.println("跳转到冬季作息表");
        return  "student/breakWinter";
    }
    @RequestMapping("/toBreakSummer")
    public String toBreakSummer(){
        System.out.println("跳转到冬季作息表");
        return  "student/breakSummer";
    }
    /**
     * 跳转到销假页面
     * @return
     */
    @RequestMapping("/getCancelForLeave")
    @ResponseBody
    public ResultEntity<List<LeaveFormStudent>> toCancelForLeave(HttpServletRequest request){
        System.out.println("跳转到销假页面");
        Student student = (Student) request.getSession().getAttribute(LoginConsts.STUDENT);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id",student.getId());
        List<LeaveForm> leaveForm= leaveFormService.getApprovingLeaveForm(map);
        List<LeaveFormStudent> list= new LinkedList<LeaveFormStudent>();
        for(LeaveForm leaveForm1: leaveForm){
         LeaveFormStudent student1 = new LeaveFormStudent();
         student1.setReason(leaveForm1.getReason());
            //计算得到请假天数
            int duringTime = TimeUtils.getDuringTime(leaveForm1.getStartTime(), leaveForm1.getEndTime());
            student1.setGrade(student.getGrade());
         student1.setDays(duringTime);
         student1.setStartTime(leaveForm1.getStartTime());
         student1.setEndTime(leaveForm1.getEndTime());
         student1.setCardNumber(student.getCardNumber());
         student1.setClazz(student.getClazz());
         student1.setPhoneNumber(student.getPhoneNumber());
         student1.setName(student.getName());
         student1.setSubmitTime(leaveForm1.getSubmitTime());
         list.add(student1);
        }
        return ResultEntity.successWithData(list);
    }
    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping("toIndex")
    public  String toIndex(){
        System.out.println("成功跳转到首页");
        return "student/index";
    }
    /**
     * 跳转到首页。
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        System.out.println("成功跳转到首页");
        return "student/index";
    }
    @RequestMapping("/teseException")
    public String teseException() throws SysException {
        System.out.println("teseException方法执行了");
        try {
            int a = 10/0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SysException("查询用户错误");
        }
        return "success";
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
     */
    @RequestMapping("/toAccountSet")
    public ModelAndView accountSet(HttpServletRequest request){
        Student student = (Student) request.getSession().getAttribute("student");
        Student studentById = studentService.getStudentById(student.getId());
        ModelAndView mv =new ModelAndView();
        mv.addObject("role",studentById.getRole());
        mv.addObject("cardNumber",studentById.getCardNumber());
        //跳转到StudentAccountjsp页面。
        mv.setViewName("/student/accountSet");
        return mv;
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
     * 学生退出登入。
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session)throws Exception{
        session.invalidate();
        //重定向到首页。
        return "login";
    }
    /**
     * 测试根据用户ID查询用户信息。
     * @param id
     * @return
     */
    @RequestMapping(value = "/getStudentById", method = RequestMethod.GET)
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
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity<String> dologin(@RequestParam("username")String username,
                                        @RequestParam(value = "captcha",required = false)String captcha,
                                        @RequestParam("password")String password, HttpServletRequest request) {
        String captcha_request = request.getParameter("captcha");
        String CAPTCHA_session = (String) request.getSession().getAttribute("CAPTCHA_SESSION");
        if (captcha_request!=CAPTCHA_session){
            return ResultEntity.failed("验证码输入错误");
        }else {
            if (username.equals("")||username==null||password.equals("")||password==null){
                return ResultEntity.failed("用户名和密码不能为空");
            }
            Student student = studentService.getStudentByCardNumber(username);
            if (student==null){
                return ResultEntity.failed("用户名或密码输入错误");
            }else {
                request.getSession().setAttribute(LoginConsts.STUDENT,student);
                if (student.getPassword().equals(password)){
                    return ResultEntity.successWithData("登入成功");
                }else {
                    return ResultEntity.failed("用户名或密码输入错误");
                }
            }
        }
    }
}