//package cn.jxust.leave.controller;
//
//
//import cn.jxust.leave.pojo.Employee;
//import cn.jxust.leave.service.EmployeeService;
//import cn.jxust.leave.utils.JsonMsg;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.List;
//
///**
// * 教职工的controller
// * @author tyeerth
// * @date 2019/11/13 - 19:05
// */
//
//@Controller
//@RequestMapping("/employee")
//public class EmployeeController {
//    @Autowired
//    EmployeeService employeeService;
//
//    /**
//     * 跳转到修改员工信息页面
//     * @param request
//     * @return
//     */
//    @RequestMapping("/toAccountSet")
//    @ResponseBody
//    public ModelAndView accountSet(HttpServletRequest request){
//        Employee employeeBySession = (Employee) request.getSession().getAttribute("employee");
//        Employee employee = employeeService.getEmployeeById(employeeBySession.getId());
//        ModelAndView mv =new ModelAndView();
////        mv.addObject("role",employee.getRole());
//        mv.addObject("username",employee.getUsername());
//        mv.addObject("name",employee.getName());
//        //跳转到StudentAccountjsp页面。
//        mv.setViewName("/employee/accountSet");
//        return mv;
//    }
//    /**
//     * 修改员工信息。
//     * @param password
//     * @param newPassword
//     * @param newPasswords
//     * @param request
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(value = "/updateEmployee ",method = RequestMethod.POST)
//    @ResponseBody
//    public JsonMsg updateStudent(@RequestParam("password")String password, @RequestParam("newPassword")String newPassword,
//                                 @RequestParam("newPasswords") String newPasswords,
//                                 HttpServletRequest request) {
//
//        Employee employeeBySession = (Employee) request.getSession().getAttribute("employee");
//        Employee employee = employeeService.getEmployeeById(employeeBySession.getId());
//        if (password.equals(employee.getPassword())){
//            if (newPassword.equals(newPasswords)){
//
//                Employee employee1=new Employee();
//                employee1.setId(employeeBySession.getId());
//                employee1.setPassword(newPassword);
//
//                employeeService.updateEmployee(employee1);
//
//                return JsonMsg.success().addInfo("update_success","修改密码成功！");
//            }else {
//                return JsonMsg.fail().addInfo("import_error","两次密码输入不一致");
//            }
//        }else {
//            return JsonMsg.fail().addInfo("query_error","原密码输入错误");
//        }
//    }
//    /**
//     * 跳转到员工个人信息页面的同时显示员工信息。
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/getEmployee")
//    public ModelAndView getStudent(HttpServletRequest request){
//        ModelAndView mv = new ModelAndView("employee/employeeInfo");
//        Employee employee = (Employee) request.getSession().getAttribute("employee");
//        Employee employee1 = employeeService.getEmployeeById(employee.getId());
//        System.out.println("通过当前session获取到的员工信息"+employee.toString());
//        if (employee1.getGender().equals("1")){
//            employee1.setGender("男");
//        }
//        if (employee1.getGender().equals("0")){
//            employee1.setGender("女");
//        }
//        mv.addObject("em", employee1);
//        return mv;
//    }
//    /**
//     * 员工添加操作
//     */
//    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonMsg addEmployee(Employee employee) {
//        if (employee == null) {
//            int res = employeeService.addEmployee(employee);
//            if (res == 1) {
//                return JsonMsg.success();
//            } else {
//                return JsonMsg.fail();
//            }
//        } else {
//            return JsonMsg.fail().addInfo("employee_null_error", "用户名为空");
//        }
//
//    }
//
//
//    /**
//     * 员工删除操作
//     */
//    @RequestMapping(value = "/deleteEmployeeById/{id}", method = RequestMethod.DELETE)
//    @ResponseBody
//    public JsonMsg deleteEmployeeById(@PathVariable("id") Integer id) {
//        int res = 0;
//        if (id > 0) {
//            res = employeeService.deleteEmployeeById(id);
//        }
//        if (res != 1) {
//            return JsonMsg.fail().addInfo("deleteEmployee_error", "员工删除异常");
//        }
//        return JsonMsg.success();
//    }
//
//
//    /**
//     * 根据id查询员工信息
//     */
//    @RequestMapping(value = "/getEmployeeByUsername/{username}", method = RequestMethod.GET)
//    @ResponseBody
//    public JsonMsg getEmployeeByUsername(@PathVariable("username") String username) {
//        Employee employee = employeeService.getEmployeeByUsername(username);
//        if (employee != null) {
//            return JsonMsg.success().addInfo("employee", employee);
//        } else {
//            return JsonMsg.fail();
//        }
//    }
//
//    /**
//     * 新增记录后，查询最新的页数
//     *
//     * @param limit 返回记录最大行数
//     * @return
//     */
//    @RequestMapping(value = "/getTotalPages", method = RequestMethod.GET)
//    @ResponseBody
//    public JsonMsg getTotalPage(@RequestParam(value = "limit", defaultValue = "5") int limit) {
//        int totalItems = employeeService.getEmployeeCount();
//        //获取总的页数
//        int temp = totalItems / limit;
//        int totalPages = (totalItems % limit == 0) ? temp : temp + 1;
//        return JsonMsg.success().addInfo("totalPages", totalPages);
//    }
//
//    /**
//     * 查询
//     *
//     * @param pageNo 查询指定页码包含的数据
//     * @param limit  返回记录最大行数
//     * @return
//     */
//    @RequestMapping(value = "/getEmpList", method = RequestMethod.GET)
//    public ModelAndView getEmp(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
//                               @RequestParam(value = "limit", defaultValue = "5") int limit) {  //defaultValue默认值
//        ModelAndView mv = new ModelAndView("employee_xxx");//TODO 页面名,未定
//        //int limit = 5;  //可以写死,不过最好按需求
//        // 记录的偏移量(即从第offset行记录开始查询)，
//        // 如第1页是从第1行(offset=(21-1)*5=0,offset+1=0+1=1)开始查询；
//        // 第2页从第6行(offset=(2-1)*5=5,offset+1=5+1=6)记录开始查询
//        int offset = (pageNo - 1) * limit;
//        //获取指定页数包含的员工信息
//        List<Employee> employees = employeeService.getEmployeeList(offset, limit);
//        //获取总的记录数
//        int totalItems = employeeService.getEmployeeCount();
//        //获取总的页数
//        int temp = totalItems / limit;
//        int totalPages = (totalItems % limit == 0) ? temp : temp + 1;
//        //当前页数
//        int curPage = pageNo;
//
//        //将上述查询结果放到Model中，在JSP页面中可以进行展示
//        mv.addObject("employees", employees)
//                .addObject("totalItems", totalItems)  //totalItems 总的记录数
//                .addObject("totalPages", totalPages)  //totalPages 总页数
//                .addObject("curPage", curPage);       //curPage  当前页数
//        return mv;
//    }
//
//    /**
//     * 教职工退出登入
//     * @param session
//     * @return
//     */
//    @RequestMapping("/logout")
//    public String logout(HttpSession session){
//        session.invalidate();
//        return "employeeLogin";
//    }
//
//    /**
//     * 更改员工信息
//     * @param id
//     * @param employee
//     * @return
//     */
//    @RequestMapping(value = "/updateEmployeeById/{id}", method = RequestMethod.PUT)
//    @ResponseBody
//    public JsonMsg updateEmployeeById(@PathVariable("id") Integer id, Employee employee) {
//        Employee employee_id = employeeService.getEmployeeById(id);
//        if (employee_id.getUsername() != employee.getUsername()) {
//            Employee employees = employeeService.getEmployeeByUsername(employee.getUsername());
//            if (employees != null) {
//                return JsonMsg.fail().addInfo("name_reg_error", "用户名重复");
//            }
//        }
//        int res = employeeService.updateEmployeeByIdIfNecessary(id, employee);
//        if (res == 1) {
//            return JsonMsg.success();
//        } else {
//            return JsonMsg.fail();
//        }
//    }
//
//
//    /**
//     * 查询
//     */
//    @RequestMapping(value = "/toClassInfo", method = RequestMethod.GET)
//    public ModelAndView getClassInfo(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
//                                     @RequestParam(value = "limit", defaultValue = "5") int limit) {
//        ModelAndView mv = new ModelAndView("employee/classInfo");
//        int offset = (pageNo - 1) * limit;
//        List<EmpLeaFrom> empLeaFromList=employeeService.getClasssInfoList(offset, limit);
//        //获取总的记录数
//        int totalItems = employeeService.getClasssInfoCount();
//
//        //获取总的页数
//        int temp = totalItems / limit;
//        int totalPages = (totalItems % limit == 0) ? temp : temp + 1;
//        //当前页数
//        int curPage = pageNo;
//        //
//        List<Academy> academyList=employeeService.getAcademyList();
//        //将上述查询结果放到Model中，在JSP页面中可以进行展示
//        mv.addObject("empLeaFromList", empLeaFromList)
//                .addObject("totalItems", totalItems)  //totalItems 总的记录数
//                .addObject("totalPages", totalPages)  //totalPages 总页数
//                .addObject("curPage", curPage)      //curPage  当前页数
//                .addObject("academyList", academyList);
//        return mv;
//    }
//
//    /**
//     * 查询
//     */
//    @RequestMapping(value = "/toClassInfoSelect", method = RequestMethod.GET)
//    public ModelAndView toClassInfoSelect(@RequestParam(value = "campus_id")Integer campus_id,
//                                          @RequestParam(value = "academy_id")Integer academy_id){
//
//        ModelAndView mv = new ModelAndView("employee/classInfo");
//   System.out.println(campus_id+" / "+academy_id);
//        List<EmpLeaFrom> empLeaFromList=employeeService.getClasssInfoListLimt(campus_id, academy_id);
//        //获取总的记录数
//        int totalItems = empLeaFromList.size();
//        //
//        List<Academy> academyList=employeeService.getAcademyList();
//        //将上述查询结果放到Model中，在JSP页面中可以进行展示
//        mv.addObject("empLeaFromList", empLeaFromList)
//                .addObject("totalItems", totalItems)  //totalItems 总的记录数
//                .addObject("totalPages", 1)  //totalPages 总页数
//                .addObject("curPage", 1)      //curPage  当前页数
//                .addObject("academyList", academyList);
//        return mv;
//    }
//}
