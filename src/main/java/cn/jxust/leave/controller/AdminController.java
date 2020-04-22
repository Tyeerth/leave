package cn.jxust.leave.controller;

import cn.jxust.leave.po.*;
import cn.jxust.leave.service.AdminService;
import cn.jxust.leave.service.EmployeeService;
import cn.jxust.leave.service.StudentService;
import cn.jxust.leave.utils.JsonMsg;
import cn.jxust.leave.utils.RandomValidateCode;
import cn.jxust.leave.utils.ResponseUtil;
import cn.jxust.leave.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;

/**
 * 管理员的controller
 * 因为不存在管理员的表，所以用员工表来代替。
 * @author tyeerth
 * @date 2019/11/13 - 19:05
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private  EmployeeService employeeService;
    @Autowired
    private AdminService adminService;

    @RequestMapping("/toAdminInfo")
    public ModelAndView toAccountSet(HttpServletRequest request){
        System.out.println("跳转到管理员信息页面");
        Employee admin =(Employee)request.getSession().getAttribute("admin");
        ModelAndView mv =new ModelAndView("/admin/adminInfo");
        if (admin.getGender()=="1"){
            admin.setGender("男");
        }else {
            admin.setGender("女");
        }
        mv.addObject("name",admin.getName())
                .addObject("role","管理员")
                .addObject("username",admin.getUsername())
                .addObject("sex",admin.getGender())
                .addObject("phoneNumber",admin.getPhoneNumber())
                .addObject("adminEmail","12345@qq.com")
                .addObject("password",admin.getPassword());

        return mv;
    }
    @RequestMapping("/toAllEmpInfo")
    public String toAllEmpInfo(){

        return "/admin/empInfo";
    }

    /**
     * 跳转页面的同时查找所有学生的信息。
     * @return
     */
    @RequestMapping("/toAllStuInfo")
    public ModelAndView toAllStuInfo(@RequestParam(value="pageNo",required=false)String pageNo, @RequestParam(value="academyId",required = false) String academyId, @RequestParam(value="campusId",required = false) String campusId){
//        ModelAndView mv = new ModelAndView("/admin/stuInfo");
        ModelAndView mv = new ModelAndView("/admin/stuInformation");
        PageBean pageBean = new PageBean(Integer.parseInt(pageNo),10);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("start", pageBean.getStart());
        map.put("size", 10);
        map.put("campusId",campusId);
        map.put("academyId",academyId);
        //查找所有学生
        List<Student> studentList = studentService.queryMapStudent(map);
        List<Academy> academyList=employeeService.getAcademyList();
        Long total = studentService.getStudentTotal(map);
        int totalPages = (int) Math.ceil(total/5.00);
        mv.addObject("studentList",studentList)
                .addObject("totalItems", total)  //获取总的记录数
                .addObject("totalPages", totalPages)  //总的页数
                .addObject("curPage", pageNo)
                .addObject("academyList", academyList);   //curPage当前页数;
        return mv;
    }
    @RequestMapping(value = "/findPage" ,method = RequestMethod.POST)
    @ResponseBody
    public PageResult<Student> findPage(@RequestBody Map searchMap, int page, int size ){
        PageBean pageBean = new PageBean(page,size);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("campusId",searchMap.get("campusId"));
        map.put("academyId",searchMap.get("academyId"));
        Long total = studentService.getStudentTotal(map);
        List<Student> studentList = studentService.queryMapStudent(map);
        PageResult<Student> pageResult = new PageResult(total,studentList);
        return pageResult;
    }
    @RequestMapping(value = "/findEmployeePage" ,method = RequestMethod.POST)
    @ResponseBody
    public PageResult<Employee> findEmployeePage(@RequestBody Map searchMap, int page, int size ){
        PageBean pageBean = new PageBean(page,size);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("name",searchMap.get("name"));
        map.put("phoneNumber",searchMap.get("phoneNumber"));
        int total = employeeService.getCountEmployee(map);
        List<Employee> employeeList = employeeService.getMapEmployee(map);
//        List<Student> studentList = studentService.queryMapStudent(map);
        PageResult<Employee> pageResult = new PageResult((long) total,employeeList);
        return pageResult;
    }
    /**
     * 跳转到修改学生信息页面
     * @param request
     * @return
     */
    @RequestMapping("/toAccountSet")
    public ModelAndView accountSet(HttpServletRequest request){
        Employee admin = (Employee) request.getSession().getAttribute("admin");
        ModelAndView mv =new ModelAndView();
        mv.addObject("role",admin.getRole());
        mv.addObject("phoneNumber",admin.getPhoneNumber());
        //跳转到StudentAccountjsp页面。
        mv.setViewName("/admin/accountSet");
        return mv;
    }
    /**
     * 修改管理员信息。
     * @param password
     * @param newPassword
     * @param newPasswords
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateAdmin ",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg updateStudent(@RequestParam("password")String password, @RequestParam("newPassword")String newPassword,
                                 @RequestParam("newPasswords") String newPasswords,
                                 HttpServletRequest request) {

        Employee employee = (Employee) request.getSession().getAttribute("admin");
        if (password.equals(employee.getPassword())){
            if (newPassword.equals(newPasswords)){
                employee.setPassword(newPassword);
                employeeService.updateEmployee(employee);
                return JsonMsg.success().addInfo("update_success","修改密码成功！");
            }else {
                return JsonMsg.fail().addInfo("import_error","两次密码输入不一致");
            }
        }else {
            return JsonMsg.fail().addInfo("query_error","原密码输入错误");
        }
    }

    @RequestMapping("/downTemplate")
    @ResponseBody
    public void down(HttpServletRequest request, HttpServletResponse response) throws Exception{

        String fileName = request.getSession().getServletContext().getRealPath("/")+"WEB-INF/jsp/images/template.xlsx";

        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));

        String filename = "template.xlsx";

        filename = URLEncoder.encode(filename,"UTF-8");

        response.addHeader("Content-Disposition", "attachment;filename=" + filename);

        response.setContentType("multipart/form-data");

        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while((len = bis.read()) != -1){
            out.write(len);
            out.flush();
        }
        out.close();
    }
    /**
     * 根据id查找学生
     */
    @RequestMapping("/findById")
    public @ResponseBody
    Student findById(Integer id){
     return studentService.getstudentBId(id);
    }
    /**
     * 管理员跳转到excel添加学生页面。
     * @return
     */
    @RequestMapping("/toimportExcel")
    public String toimportExcel(){
        System.out.println("成功跳转到导入excel页面。。");
        return "/admin/import";
    }

    @RequestMapping("/toAllClaInfo")
    public String toAllClaInfo() {
        System.out.println("跳转到班级信息页面");
        return "/admin/classInfo";
    }
    @RequestMapping("/toAddStudent")
    public String toAddStudent(){
        System.out.println("跳转到添加学生页面");
        return "/admin/sutAdd";
    }

    /**
     * 管理员实现通过excel表格批量导入学生信息。
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/import")
    public String impotr(HttpServletRequest request, Model model) throws Exception {
        int adminId = 1;
        //获取上传的文件
        MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
        MultipartFile file = multipart.getFile("upload");
        InputStream in = file.getInputStream();
        //数据导入
        adminService.importExcelInfo(in,file);
        in.close();
        return "/admin/submitSuccess";
    }
    /**
     * 管理员根据教职工的担任的角色、姓名、性别、手机号码查询员工。
     * @param employee
     * @param response
     * @return
     */
    @RequestMapping("/queryEmployee")
    @ResponseBody
    public JsonMsg queryEmployee(Employee employee, HttpServletResponse response) throws Exception {
        List<Employee> employeeList = employeeService.queryEmployee(employee);
        if (employeeList==null || employeeList.size()==0){
            return JsonMsg.fail().addInfo("query_fail","无当前查询条件的员工");
        }else{
            return JsonMsg.success().addInfo("employeeList",employeeList);
        }
    }
    @RequestMapping("/findEmployeeById")
    @ResponseBody
    public  Employee findEmployeeById(@RequestParam("id") Integer id){
       return employeeService.getEmployee(id);
    }
    /**
     * 管理员根据校区、学院、年级、专业、一卡通号、姓名查询学生。
     * @param
     * @return
     */
    @RequestMapping(value = "/queryStudent",method = RequestMethod.POST)
    @ResponseBody
    public  JsonMsg queryStudent(@RequestParam(value = "campusId",required = false)Integer campusId,
                                 @RequestParam(value = "academyId",required = false)Integer academyId,
                                 @RequestParam(value = "dataNum",required = false)Integer dataNum,
                                 @RequestParam(value = "current",required = false)Integer current,
                                 HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        PageBean pageBean = new PageBean(current,dataNum);
        Map<String ,Object> map = new HashMap<String ,Object>();
        map.put("campusId",campusId);
        map.put("academyId",academyId);
        System.out.println(academyId);
        map.put("start",pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        List<Student> studentList = studentService.queryMapStudent(map);
        JSONObject result = new JSONObject();

        //查询当前条件的学生总数
        Long total = studentService.getStudentTotal(map);
        if (studentList.size()==0 ||studentList==null){
            return JsonMsg.fail().addInfo("query_fail","无当前查询条件的学生");
        }else{
            return JsonMsg.success().addInfo("studentList",studentList).addInfo("total",total);
        }
    }
    /**
     * 管理员根据校区、学院、年级、专业、一卡通号、姓名查询学生。
     * @param
     * @return
     */
    @RequestMapping(value = "/findStudent",method = RequestMethod.POST)
    @ResponseBody
    public  JsonMsg findStudent(@RequestParam(value = "campusId",required = false)String campusId,
                                 @RequestParam(value = "academyId",required = false)String academyId,
                                 @RequestParam(value = "dataNum",required = false)Integer dataNum,
                                 @RequestParam(value = "current",required = false)Integer current,
                HttpServletResponse response
    ) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        PageBean pageBean = new PageBean(current,dataNum);
        if (current==null){
            current =1;
        }
        Map<String ,Object> map = new HashMap<String ,Object>();
        map.put("campusId",campusId);
        map.put("academyId",academyId);
        System.out.println(academyId);
        map.put("start",pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        List<Student> studentList = studentService.queryMapStudent(map);
        //查询当前条件的学生总数
        Long total = studentService.getStudentTotal(map);
        if (studentList.size()==0 ||studentList==null){
            return JsonMsg.fail().addInfo("query_fail","无当前查询条件的学生");
        }else{
            return JsonMsg.success().addInfo("studentList",studentList).addInfo("total",total);
        }
    }

    /**
     * 管理员实现添加员工
     * @param employee
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addEmployee",method = RequestMethod.POST)
    public  String addEmployee(@RequestBody Employee employee, HttpServletResponse response ) throws Exception {
        System.out.println(employee);
        int total = employeeService.addEmployee(employee);

        JSONObject result = new JSONObject();
        if (total==1){
            result.put("success",true);
        }else {
            result.put("success",false);
        }
        ResponseUtil.write(response,result);
        return  null;
    }
    /**
     * 管理员实现添加学生功能
     * @param student
     * @param response
     * @return
     */
    @RequestMapping(value = "/addStudent",method = RequestMethod.POST)
    @ResponseBody
    public String addStudent(@RequestBody Student student, HttpServletResponse response) throws Exception {
        student.setRoleId(1);
        int total = studentService.addStudent(student);
        JSONObject result = new JSONObject();
        if (total==1){
            result.put("success",true);
        }else {
            result.put("success",false);
        }
        ResponseUtil.write(response,result);
        return null;
    }
    @RequestMapping("/tologin")
    public String tologin(){
        System.out.println("跳转到管理员登入页面");
        return "AdminLogin";
    }
    @RequestMapping("/toLeaveFormDownload")
    public String toLeaveFormDownload(){
        System.out.println("跳转到请假模板页面");
        return "admin/leaveFormDownload";
    }
    /**
     * 管理员实现跳转到首页。
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        System.out.println("成功跳转到首页");
        return "admin/index";
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
            randomValidateCode.getRandcode(request, response);//输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 完成管理员登入功能。
     * @param username
     * @param password
     * @param request
     * @return
     */
    /*@RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg login(@RequestParam("username") String username,@RequestParam("password") String password, HttpServletRequest request){
        //获取输入的验证码
        String captcha_request = request.getParameter("captcha");
        //获取作用域中的验证码
        String captcha_session = (String) request.getSession().getAttribute("CAPTCHA_SESSION");
        //判断验证码是否输入正确
        if (captcha_request.equals(captcha_session)){
            if (username!="" && password!=""){
                //根据用户名查找用户
              Employee admin=  adminService.getAdminByUsername(username);
              //获取用户输入的密码
                String passwords = password;
                //判断用户密码是否输入正确，判断用户是否为管理员。
                if (admin !=null && passwords.equals(admin.getPassword()) && admin.getRoleId()==8){
                    //登入成功，把当前用户存到session中
                    request.getSession().setAttribute("admin",admin);
                    return JsonMsg.success();
                }else {
                    return JsonMsg.fail().addInfo("login_error","用户名或者密码输入错误");
                }
            }else{
                return JsonMsg.fail().addInfo("login_error","用户名或密码不能为空");
            }
        }else {
            return  JsonMsg.fail().addInfo("login_error","验证码输入错误");
        }
    }*/
   /* *//**
     * 完成管理员登入功能。
     * @param request
     * @return
     */
//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    @ResponseBody
//    public JsonMsg login( HttpServletRequest request){
//        Employee admin=new Employee();
//        admin.setUsername(request.getParameter("username"));
//        admin.setPassword(request.getParameter("password"));
//        //获取shiro中的subject对象。
//        Subject currentUser = SecurityUtils.getSubject();
//        //声明为ADMIN的登入类型。
//        final String ADMIN_LOGIN_TYPE = LoginType.ADMIN.toString();
//        //获取输入的验证码
//        String captcha_request = request.getParameter("captcha");
//        //获取作用域中的验证码
//        String captcha_session = (String) request.getSession().getAttribute("CAPTCHA_SESSION");
//        //判断验证码是否输入正确
//        if (captcha_request.equals(captcha_session)){
//            if (admin.getUsername()!="" && admin.getPassword()!=""){
//                //如果用户未登入。
//                if (!currentUser.isAuthenticated()) {
//                    //通过UsernamePasswordToken来封装当前对象的账号密码。
//                    //封装信息到token中。
////                    UsernamePasswordToken token = new UsernamePasswordToken(employee.getUsername(), employee.getPassword());
//                    CustomizedToken token = new CustomizedToken(admin.getUsername(), admin.getPassword(),ADMIN_LOGIN_TYPE);
//                    //记住密码。
////                    token.setRememberMe(true);
//                    try {
//                        //使用subject的方法进行登入。能否登入要去看配置文件中是否有对应的账号和密码。
//                        //会把token传到面的realm中进行比对。
//                        currentUser.login(token);
//                        return JsonMsg.success().addInfo("loginRoleId",3);
//                    }
//                    //直接捕获最大异常。
//                    catch (AuthenticationException ae) {
//                        //unexpected condition?  error?
//                        System.out.println("登入失败。。。" + ae.getMessage());
//                        return JsonMsg.fail().addInfo("login_error", "用户名或者密码输入错误");
//                    }
//                }
//                    //用户已经登入。
//                    return JsonMsg.success();
//            }else{
//                return JsonMsg.fail().addInfo("login_error","用户名或密码不能为空");
//            }
//        }else {
//            return  JsonMsg.fail().addInfo("login_error","验证码输入错误");
//        }
//    }

    /**
     * 管理员修改学生信息。
     * @param student
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateStudent",method = RequestMethod.POST)
    @ResponseBody
    public String updateStudent(@RequestBody Student student , HttpServletResponse response) throws Exception {
        studentService.updateStudent(student);
       JSONObject result =new JSONObject();
        result.put("success",true);
        ResponseUtil.write(response,result);
        return null;
    }
    /**
     * 管理员修改员工信息。
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateEmployee",method = RequestMethod.POST)
    @ResponseBody
    public String updateEmployee(@RequestBody Employee employee , HttpServletResponse response) throws Exception {
        employeeService.updateEmployee(employee);
       JSONObject result =new JSONObject();
        result.put("success",true);
        ResponseUtil.write(response,result);
        return null;
    }
    /**
     * 修改管理员的信息。
     * @return
     * @throws Exception
     */
    @RequestMapping("/update")
    public @ResponseBody
    JsonMsg  update(HttpServletRequest request) throws Exception {
        Employee employee =(Employee) request.getSession().getAttribute("admin");
        Employee admin = new Employee();
        admin.setId(employee.getId());
        admin.setUsername(request.getParameter("inputAccount"));
        admin.setPassword(request.getParameter("inputPwd"));
        if(request.getParameter("sex").equals("男")){
            admin.setGender("1");
        }else {
            admin.setGender("0");
        }
        admin.setPhoneNumber(request.getParameter("inputIphone"));
        admin.setName(request.getParameter("nameinput"));
        System.out.println(admin);
        adminService.update(admin);
    return  JsonMsg.success();
    }
    /**
     * 管理员退出登入
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse resp) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "adminLogin";
    }

    /**
     * 管理员根据员工id删除
     *
     *
     * （删除时删除对应的外键关系）
     * @param id
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteEmployee")
    public  String deleteEmployee(@RequestParam(value="id")String id, HttpServletResponse response) throws Exception {
            employeeService.deleteMidle_employee(id);
            employeeService.delete(id);
        JSONObject result=new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }
    /**
     * 删除学生
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("/delStudent")
    public @ResponseBody
    JsonMsg  delStudent(HttpServletRequest request) throws Exception {

        ServletInputStream inputStream = request.getInputStream();

        String mybooksid = IOUtils.toString(inputStream);

        org.json.JSONArray jsonarr = new org.json.JSONArray(mybooksid);

        for (int i = 0; i < jsonarr.length(); i++) {

            studentService.delete(((JSONObject) jsonarr.get(i)).getString("del_arr"));
        }
        return JsonMsg.success();
    }
    /*public String delete(@RequestBody String ids, HttpServletResponse response)throws Exception{
        //把ids规范化。
        String []idsStr=ids.split(",");
        for(int i=0;i<idsStr.length;i++){
            studentService.delete(idsStr[i]);
        }
        JSONObject result=new JSONObject();

        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }*/
    /**
     * 分页条件查询学生列表
     * @param page
     * @param rows
     * @param student
     * @param response
     * @return
     */
    @RequestMapping("/list")
    public @ResponseBody
    List
    queryStudentList(@RequestParam(value="page",required=false)String page,
                     @RequestParam(value="rows",required=false)String rows,
                     Student student, HttpServletResponse response) throws Exception {
        //前端传入数据---当前页数，用户所选择的页面大小。
        PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String,Object> map=new HashMap<String,Object>();
        // 查询用户名获取,并把数据放到map集合中。
        map.put("id", StringUtil.formatLike(student.getId().toString()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        //查询成功返回分页用户列表
        List<Student> studentList=studentService.find(map);

        //返回用户总的数量。
        Long total=studentService.getTotal(map);
        JSONObject result=new JSONObject();
        JSONArray jsonArray= JSONArray.parseArray(JSON.toJSONString(studentList));
//        JSONArray jsonArray= JsonUtil.toJSON(studentList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }
    @RequestMapping("/deleteStudent")
    @ResponseBody
    public JsonMsg deleteStudent(@RequestParam("id") String id){
        studentService.delete(id);
        return JsonMsg.success();
    }
}
