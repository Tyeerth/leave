//package cn.jxust.leave.controller;
//
//import cn.jxust.leave.constant.LoginConsts;
//import cn.jxust.leave.pojo.Employee;
//import cn.jxust.leave.pojo.LeaveForm;
//import cn.jxust.leave.pojo.Process;
//import cn.jxust.leave.pojo.Student;
//import cn.jxust.leave.service.LeaveFormService;
//import cn.jxust.leave.service.ProcessService;
//import cn.jxust.leave.service.RoleService;
//import com.github.pagehelper.PageHelper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @Description 员工查询到对应信息的请假表
// *
// * @Author   tyeerth
// * @Date 16:43 2019/12/10
// **/
//@Controller
//@RequestMapping("/role")
//public class RoleController {
//
//    @Autowired
//    RoleService roleService;
//
//    @Autowired
//    private ProcessService processService;
//
//    @Autowired
//    private LeaveFormService leaveFormService;
//
//    /**
//     * 领导同意请假申请
//     * @return
//     */
//    @RequestMapping("/approveLeaveForm")
//    @ResponseBody
//    public String approveLeaveForm(HttpServletRequest request){
//        //查询到需要自己审核的流程
//        List<Process> processList = processService.queryAllProcess();
//        //通过流程的id获取到请假表单中的内容。
//
//        //改变请假表单的is_approved属性（1:审核通过;0:审核不通过）
//
//        //添加comment请假表的审核意见。
//
//        return null;
//    }
//
//    /**
//     * 员工查询已经审核完成的请假表
//     * @param request
//     * @return
//     */
//    @RequestMapping("/queryApprovedLeave")
//    @ResponseBody
//    public ModelAndView queryApprovedLeave(@RequestParam(value = "pageNo", defaultValue = "1") String pageNo, HttpServletRequest request){
//        //获取当前登入的员工信息。
//        Employee employee =  (Employee) request.getSession().getAttribute(LoginConsts.employee);
//        PageBean pageBean = new PageBean(Integer.parseInt(pageNo),5);
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("employeeId",employee.getId());
//        map.put("start", pageBean.getStart());
//        map.put("size", 5);
//        //查询到自己已经审核通过的流程。
//        List<Process> processList = processService.queryApprovedProcess(map);
//        //通过请假流程中的leaveform_id 查到对应的请假表。
//        List<LeaveFormStudent> leaveFormList =  new ArrayList<LeaveFormStudent>();
//        int total =0 ;
//        for(Process process : processList){
//            //查询到请假表
//            LeaveForm leaveForm =leaveFormService.queryLeaveFormById(process.getLeaveFormId());
//            total += 1;
//            Student student = leaveFormService.queryStudentByLeaveForm(leaveForm.getStudentId());
//            LeaveFormStudent leaveFormStudent = new LeaveFormStudent();
//            leaveFormStudent.setStartTime(leaveForm.getStartTime());
//            leaveFormStudent.setEndTime(leaveForm.getEndTime());
//            leaveFormStudent.setReason(leaveForm.getReason());
//            leaveFormStudent.setDays(leaveForm.getDays());
//            leaveFormStudent.setClazz(student.getClazz());
//            leaveFormStudent.setPhoneNumber(student.getPhoneNumber());
//            leaveFormStudent.setName(student.getName());
//            leaveFormStudent.setCardNumber(student.getCardNumber());
////            leaveFormStudent.setMajor(student.getMajor());
//            leaveFormStudent.setState(leaveForm.getState());
//            leaveFormStudent.setId(leaveForm.getId());
//            leaveFormList.add(leaveFormStudent);
//        }
//        int totalPages = (int) Math.ceil(total/5.00);
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("/employee/approvedLeaveForm");
//        mv.addObject("leaveFormList", leaveFormList)
//                .addObject("totalItems", total)  //totalItems 总的记录数
//                .addObject("totalPages", totalPages)  //totalPages 总页数
//                .addObject("curPage", pageNo)      //curPage  当前页数
//                .addObject("employeeName",employee.getName());
//        return mv;
//    }
//
//    /**
//     * 员工查询审批中的请假表。
//     * @param request
//     * @return LeaveForm
//     */
//    @RequestMapping("/queryApprovingLeave")
//    @ResponseBody
//    public ModelAndView queryApprovingLeave(@RequestParam(value = "pageNo", defaultValue = "1") String pageNo, HttpServletRequest request){
//        Employee employee = (Employee) request.getSession().getAttribute(LoginConsts.employee);
//        PageBean pageBean = new PageBean(Integer.parseInt(pageNo),5);
//        Map<String,Object> map = new HashMap<String,Object>();
//        ModelAndView mv =new ModelAndView();
//        map.put("employeeId",employee.getId());
//        map.put("start", pageBean.getStart());
//        map.put("size", 5);
//        //查询到自己正在审核的流程。
//        List<Process> processList = processService.queryApprovingProcess(map);
//        //通过请假流程中的leaveform_id 查到对应的请假表。
//        List<LeaveFormStudent> leaveFormList =  new ArrayList<LeaveFormStudent>();
//        int total=0;
//        for(Process process : processList){
//            //查询到请假表
//            LeaveForm leaveForm =leaveFormService.queryLeaveFormById(process.getLeaveFormId());
//            total+=1;
//            Student student = leaveFormService.queryStudentByLeaveForm(leaveForm.getStudentId());
//            LeaveFormStudent leaveFormStudent = new LeaveFormStudent();
//            leaveFormStudent.setStartTime(leaveForm.getStartTime());
//            leaveFormStudent.setId(leaveForm.getId());
//            leaveFormStudent.setEndTime(leaveForm.getEndTime());
//            leaveFormStudent.setReason(leaveForm.getReason());
//            leaveFormStudent.setDays(leaveForm.getDays());
//            leaveFormStudent.setClazz(student.getClazz());
//            leaveFormStudent.setPhoneNumber(student.getPhoneNumber());
//            leaveFormStudent.setName(student.getName());
//            leaveFormStudent.setCardNumber(student.getCardNumber());
////            leaveFormStudent.setMajor(student.getMajor());
//            leaveFormStudent.setState(leaveForm.getState());
//            leaveFormList.add(leaveFormStudent);
//        }
//        int totalPages = (int) Math.ceil(total/5.00);
//        mv.setViewName("/employee/approvingLeaveForm");
//        mv.addObject("leaveFormList", leaveFormList)
//                .addObject("totalItems", total)  //totalItems 总的记录数
//                .addObject("totalPages", totalPages)  //totalPages 总页数
//                .addObject("curPage", pageNo) //curPage  当前页数
//                .addObject("employeeName",employee.getName());
//        return mv;
//    }
//
//    /**
//     * 员工查询待审核的请假表
//     * @param request
//     * @return
//     */
//    @RequestMapping("/queryUnapprovedLeave")
//    public ModelAndView queryUnapprovedLeave(HttpServletRequest request, @RequestParam(value = "pageNo", defaultValue = "1")
//            String pageNo){
//        Employee employee = (Employee) request.getSession().getAttribute(LoginConsts.employee);
//        PageBean pageBean = new PageBean(Integer.parseInt(pageNo),5);
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("employeeId",employee.getId());
//        map.put("start", pageBean.getStart());
//        map.put("size", 5);
//        //查询到自己已经审核通过的流程。
//        List<Process> processList = processService.queryUnapprovedProcess(map);
//        //通过请假流程中的leaveform_id 查到对应的请假表。
////        List<LeaveForm> leaveFormList =  new ArrayList<LeaveForm>();
//        List<LeaveFormStudent> leaveFormList = new ArrayList<>();
//        int total =0;
//        for(Process process : processList){
//            //查询到请假表
//            LeaveForm leaveForm =leaveFormService.queryLeaveFormById(process.getLeaveFormId());
//            total+=1;
//            Student student = leaveFormService.queryStudentByLeaveForm(leaveForm.getStudentId());
//            LeaveFormStudent leaveFormStudent = new LeaveFormStudent();
//            leaveFormStudent.setStartTime(leaveForm.getStartTime());
//            leaveFormStudent.setEndTime(leaveForm.getEndTime());
//            leaveFormStudent.setReason(leaveForm.getReason());
//            leaveFormStudent.setDays(leaveForm.getDays());
//            leaveFormStudent.setClazz(student.getClazz());
//            leaveFormStudent.setPhoneNumber(student.getPhoneNumber());
//            leaveFormStudent.setName(student.getName());
//            leaveFormStudent.setCardNumber(student.getCardNumber());
////            leaveFormStudent.setMajor(student.getMajor());
//            leaveFormStudent.setState(leaveForm.getState());
//            leaveFormStudent.setId(leaveForm.getId());
//            leaveFormList.add(leaveFormStudent);
//        }
//        int totalPages = (int) Math.ceil(total/5.00);
//       // PageHelper.startPage(page,5);
//       // PageResult res= new PageResult((long) leaveFormList.size(),leaveFormList);
//        ModelAndView mv =new ModelAndView();
//        mv.setViewName("/employee/unApprovedLeaveForm");
//        mv.addObject("leaveFormList", leaveFormList)
//                .addObject("totalItems", total)  //totalItems 总的记录数
//                .addObject("totalPages", totalPages)  //totalPages 总页数
//                .addObject("curPage", pageNo)  //curPage  当前页数
//                .addObject("employeeName",employee.getName());
//        return mv;
//    }
//    /**
//     * 班长查询正在审核的请假表
//     */
//    @RequestMapping("/monitorQueryApprovingLeaveForm")
//    @ResponseBody
//    public PageResult monitorQueryApprovingLeaveForm(HttpServletRequest request, int page){
//        Student monitor =(Student) request.getSession().getAttribute(LoginConsts.STUDENT);
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("employeeId",monitor.getId());
//        //查询到自己已经审核通过的流程。
//        List<Process> processList = processService.queryApprovingProcess(map);
//        //通过请假流程中的leaveform_id 查到对应的请假表。
//        //通过请假流程中的leaveform_id 查到对应的请假表。
//        List<LeaveFormStudent> leaveFormList =  new ArrayList<LeaveFormStudent>();
//        for(Process process : processList){
//            //查询到请假表
//            LeaveForm leaveForm =leaveFormService.queryLeaveFormById(process.getLeaveFormId());
//            Student student = leaveFormService.queryStudentByLeaveForm(leaveForm.getStudentId());
//            LeaveFormStudent leaveFormStudent = new LeaveFormStudent();
//            leaveFormStudent.setStartTime(leaveForm.getStartTime());
//            leaveFormStudent.setEndTime(leaveForm.getEndTime());
//            leaveFormStudent.setReason(leaveForm.getReason());
//            leaveFormStudent.setDays(leaveForm.getDays());
//            leaveFormStudent.setClazz(student.getClazz());
//            leaveFormStudent.setPhoneNumber(student.getPhoneNumber());
//            leaveFormStudent.setName(student.getName());
//            leaveFormStudent.setCardNumber(student.getCardNumber());
////            leaveFormStudent.setMajor(student.getMajor());
//            leaveFormList.add(leaveFormStudent);
//        }
//        PageHelper.startPage(page,5);
//        PageResult res= new PageResult((long) leaveFormList.size(),leaveFormList);
//        return res;
//    }
//    /**
//     * 班长查询已经审核过的请假表
//     */
//    @RequestMapping("/monitorQueryApprovedLeaveForm")
//    @ResponseBody
//    public PageResult monitorQueryApprovedLeaveForm(HttpServletRequest request, int page){
//        Student monitor =(Student) request.getSession().getAttribute(LoginConsts.STUDENT);
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("employeeId",monitor.getId());
//        //查询到自己已经审核通过的流程。
//        List<Process> processList = processService.queryApprovedProcess(map);
//        //通过请假流程中的leaveform_id 查到对应的请假表。
//        List<LeaveFormStudent> leaveFormList =  new ArrayList<LeaveFormStudent>();
//        for(Process process : processList){
//            //查询到请假表
//            LeaveForm leaveForm =leaveFormService.queryLeaveFormById(process.getLeaveFormId());
//            Student student = leaveFormService.queryStudentByLeaveForm(leaveForm.getStudentId());
//            LeaveFormStudent leaveFormStudent = new LeaveFormStudent();
//            leaveFormStudent.setStartTime(leaveForm.getStartTime());
//            leaveFormStudent.setEndTime(leaveForm.getEndTime());
//            leaveFormStudent.setReason(leaveForm.getReason());
//            leaveFormStudent.setDays(leaveForm.getDays());
//            leaveFormStudent.setClazz(student.getClazz());
//            leaveFormStudent.setPhoneNumber(student.getPhoneNumber());
//            leaveFormStudent.setName(student.getName());
//            leaveFormStudent.setCardNumber(student.getCardNumber());
////            leaveFormStudent.setMajor(student.getMajor());
//            leaveFormList.add(leaveFormStudent);
//        }
//        PageHelper.startPage(page,5);
//        PageResult res= new PageResult((long) leaveFormList.size(),leaveFormList);
//        return res;
//    }
//}
