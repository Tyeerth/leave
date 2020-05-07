//package cn.jxust.leave.controller;
//
//import cn.jxust.leave.constant.LoginConsts;
//import cn.jxust.leave.pojo.LeaveForm;
//import cn.jxust.leave.pojo.Student;
//import cn.jxust.leave.service.LeaveFormService;
//import cn.jxust.leave.service.ProcessService;
//import cn.jxust.leave.service.StudentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 这个类是用来处理请假表单的
// *
// * @author JJ
// * @date 2019/11/10 - 14:29
// */
//@Controller
//@RequestMapping(value ="/leaveForm")
//public class LeaveFormController {
//
//    @Resource(name = "leaveFormServiceImpl")
//    LeaveFormService leaveFormService;
//
//    @Autowired
//    private ProcessService processService;
//
//    @Autowired
//    private StudentService studentService;
//
//    /**
//     * 跳转到学生填写请假表页面
//     * @return
//     */
//    @RequestMapping("/toAddLeaveForm")
//    public ModelAndView toAddLeaveForm(HttpServletRequest request){
//        ModelAndView mv =new ModelAndView();
//        Student student = (Student) request.getSession().getAttribute("student");
//        mv.addObject("name",student.getName());
//        mv.addObject("cardNumber",student.getCardNumber());
//        mv.setViewName("student/addLeaveForm");
//        return mv;
//    }
//    /**
//     * 跳转到请假模板页面
//     * @return
//     */
//    @RequestMapping("/toLeaveFormDownload")
//    public String toLeaveFormDownload(){
//        return "student/leaveFormDownload";
//    }
//
//    /**
//     * 处理表单的提交
//     * 传入startTime,endTime,reason,type,studentId
//     * @param leaveForm
//     */
//    @RequestMapping("/submitLeaveForm")
//    public String submitLeaveForm(LeaveForm leaveForm, HttpServletResponse res) throws Exception {
//        if (leaveForm.getEndTime() == null) {
//            res.setStatus(400);
//            return null;
//        }
//        if (leaveForm.getReason() == null || leaveForm.getReason() == "") {
//            res.setStatus(400);
//            return null;
//        }
//        if (leaveForm.getStartTime() == null) {
//            res.setStatus(400);
//            return null;
//        }
//        if (leaveForm.getStudentId() == null) {
//            res.setStatus(400);
//            return null;
//        }
//        if (leaveForm.getType() == null) {
//            res.setStatus(400);
//            return null;
//        }
//
//        leaveFormService.saveLeaveForm(leaveForm);
//        return null;
//    }
//    /**
//     * 审核中途取消请假
//     * 传入请假表单id,学生studentId
//     * 成功取消请假会将请假表单以及其所对应的流程删除
//     *
//     * 未测试
//     */
//    @RequestMapping("/cancelLeave")
//    @ResponseBody
//    public Map cancelLeave(LeaveForm leaveForm, HttpServletRequest req, HttpServletResponse res) throws Exception {
//        Map map=new HashMap();
//
//        if("".equals(leaveForm.getId()) || leaveForm.getId()==null){
//            map.put("msg","id不能为空");
//            res.setStatus(400);
//            return map;
//        }
//        if(leaveForm.getStudentId()==null){
//            map.put("msg","studentId不能为空");
//            res.setStatus(400);
//            return map;
//        }
//
//        //从session获取当前用户id
//        Student currentUser = (Student) req.getSession().getAttribute(LoginConsts.USER);
//        Integer currentUserId=currentUser.getId();
//        //判断当前用户id是否等于传来的学生studentId
//        if(currentUserId!=leaveForm.getStudentId()){//如果不是则终止请求
//            map.put("msg","当前用户并非此表单的创建者");
//            res.setStatus(400);
//            return map;
//        }
//
//
//        //查询表单的状态state(如果通过接收前端传来的state来判断表单的状态,将会是一个漏洞..)
//        LeaveForm leaveFormInfo = leaveFormService.getLeaveFormInfo(leaveForm.getId());
//
//        //判断请假表单是否正处于审批状态
//        String leaveFormState;
//        if(!(leaveFormState=leaveFormInfo.getState()).equals("0")){//如果不是
//            if(leaveFormState.equals("1")){//代表审核通过
//                map.put("msg","请假表单已审核通过,无法取消,请尝试销假");
//                res.setStatus(400);
//                return map;
//            }else if(leaveFormState.equals("-1")){//代表审批失败
//                map.put("msg","请假表单审核失败,不需取消请假");
//                res.setStatus(400);
//                return map;
//            }
//
//            map.put("msg","未知错误");
//            res.setStatus(400);
//            return map;
//        }
//
//
//        //运行到此处代表正处于审批状态
//
//        //将该请假表单删除,删除前先删除该请假表单对应的流程
//        leaveFormService.deleteLeaveForm(leaveForm.getId());
//        map.put("msg","成功取消");
//        res.setStatus(400);
//        return map;
//    }
//
//    /**
//     * 销假
//     */
//    public void reportBack(){}
//
//
//
//    /**
//     * @Description 对象----->登录的学生----->申请请假
//     * 从前端返回来的时间格式 {"startTime":"2019-11-27T06:06"}
//     * @Author   lcs
//     * @Date 18:16 2019/12/9
//     **/
//    @RequestMapping(value = "/addLeaveForm")
//    @ResponseBody
//    public ModelAndView addLeaveForm(LeaveForm leaveForm, HttpServletRequest request) {
//        ModelAndView mv = new ModelAndView();
//        System.out.println("跳转到了addLeaveForm");
//        Student student = (Student) request.getSession().getAttribute("student");
//        //leaveForm.setId(md5());
//        leaveForm.setStudentId(student.getId());
//
//        try {
//            leaveFormService.insertLeaveForm(leaveForm);
//            mv.setViewName("student/submitSuccess");
//            return mv;
//        } catch (Exception e) {
//            e.printStackTrace();
//            mv.setViewName("student/NotFound");
//            return mv;
//        }
//
//
//    }
//
//    /**
//     * 员工同意请假表表。(员工能审核请假表说明当前的请假流程为1).
//     * 前台传入请假表的信息。
//     * 根据请假表的信息找到对应的全部流程。
//     * 查询出需要自己审核的流程（需要作出以下的判断）。
//     * ps：可以根据流程ApproverGroupId的大小判断流程执行顺序。
//     * 如果你当前的流程的ApproverGroupId是0，就直接审核，要是审核之后就把状态值改为-1。审核同意就把is_approved改为1，并把下一个流程的state设置为1.
//     *                                      不同意就把is_approved改为0，并且把后面的流程表删除。；
//     * 如果你当前的流程ApproverGroupId是1，就需要判断ApproverGroupId是0的流程的状态值是否为-1，且is_approved是否为1。如果不是
//     */
//    @RequestMapping("/employeeApproveLeaveform")
//    public @ResponseBody
//    LeaveForm  employeeApproveLeaveform(@RequestParam("leaveformId") String  id) throws Exception {
//        LeaveForm leaveForm = leaveFormService.queryLeaveFormById(id);
//        //根据请假表查到所有的请假表对应的流程
//        List<Process> processList = processService.queryProcessByLeaveFormId(id);
//
//        for (int i=0;i<processList.size();i++){
//            //找到当前审核的请假流程。
//            if (processList.get(i).getState().equals("1")){
//                processList.get(i).setState("-1");
//                processList.get(i).setIsApproved(1);
//                processService.updateProcess(processList.get(i));
//                //判断是否是最后一个流程。是的话就要改变请假表的状态。
//                if (processList.get(i).getApproverGroupId()==(processList.size()-1)){
//                    leaveForm.setState("1");
//                    leaveFormService.updateLeaveForm(leaveForm);
//                }else {
//                    //不是就就改变下一个流程的状态值
//                    for (int i1=0;i<processList.size();i++){
//                        if (processList.get(i1).getApproverGroupId()==processList.get(i).getApproverGroupId()+1){
//                            processList.get(i1).setState("1");
//                            //保存下一流程
//                            processService.updateProcess(processList.get(i1));
//                        }
//                    }
//                }
//            }
//        }
////        ModelAndView mv = new ModelAndView("/student/submitSuccess");
//        return leaveForm;
//    }
//    /**
//     * 员工拒绝请假申请。
//     */
//    @RequestMapping("/employeeRejectLeaveForm")
//    public @ResponseBody
//    LeaveForm employeeRejectLeaveForm(@RequestParam("leaveFormId") String  id) throws Exception {
//        LeaveForm leaveForm = leaveFormService.queryLeaveFormById(id);
//        //根据请假表查到所有的请假表对应的流程
//        List<Process> processList = processService.queryProcessByLeaveFormId(id);
//        for (int i=0;i<processList.size();i++){
//            //遍历集合中的数据，找到当前处理的流程。
//            if (processList.get(i).getState()=="1"){
//                processList.get(i).setState("-1");
//                processList.get(i).setIsApproved(0);
//                processService.updateProcess(processList.get(i));
//                //修改请假表中的最后结果为不同意。
//                leaveForm.setState("-1");
//                //删除这个流程后面的流程。
//                for (int i1=0;i<processList.size();i++){
//                    if (processList.get(i1).getApproverGroupId()>processList.get(i).getApproverGroupId()){
//                        processService.deleteRestProcess(leaveForm.getId(),processList.get(i1).getApproverGroupId());
//                    }
//                }
//                leaveFormService.updateLeaveForm(leaveForm);
//            }
//        }
////        ModelAndView mv = new ModelAndView("/student/submitSuccess");
//        return leaveForm;
//    }
//
//    /**
//     * 班长同意请假表。
//     * 前台传入请假表、
//     * 查到所有的请假流程表。
//     * 根据请假表的信息查到请假流程（state为1）
//     * 把本张请假流程表改为-1，审核同意就把is_approved改为1。
//     * 把下一张请假流程表（ApproverGroupId是1）的state改为1.
//     * 判断这个请假流程是否还有下一个请假流程。
//     */
//    @RequestMapping("/monitorApproveLeaveForm")
//    @ResponseBody
//    public String monitorApproveLeave(LeaveForm leaveForm , HttpServletRequest request) throws Exception {
//        //根据请假表查到所有的请假表对应的流程
//        List<Process> processList = processService.queryProcessByLeaveFormId(leaveForm.getId());
//        for (int i =0; i<processList.size();i++){
//            if(processList.get(i).getApproverGroupId()==0){
//                //说明这是第一个流程。
//                processList.get(i).setIsApproved(1);
//                processList.get(i).setState("-1");
//                //保存改变后请假流程表
//                processService.updateProcess(processList.get(i));
//                //改变第二个流程的状态
//                for (int i1=0;i<processList.size();i++){
//                    if (processList.get(i1).getApproverGroupId()==1){
//                        processList.get(i1).setPreProcessId("0");
//                        processList.get(i1).setState("1");
//                        //保存这张请假流程表
//                        processService.updateProcess(processList.get(i1));
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 班长审核请假表不通过
//     * @param leaveForm
//     * @return
//     */
//    @RequestMapping("/monitorRejectLeaveForm")
//    @ResponseBody
//    public String monitorRejectLeaveForm(LeaveForm leaveForm) throws Exception {
//        //根据请假表查到所有的请假表对应的流程
//        List<Process> processList = processService.queryProcessByLeaveFormId(leaveForm.getId());
//        for (int i =0; i<processList.size();i++){
//            if(processList.get(i).getApproverGroupId()==0){
//                //说明这是第一个流程。
//                processList.get(i).setIsApproved(0);
//                processList.get(i).setState("-1");
//                //修改请假表的状态。
//                leaveForm.setState("-1");
//                leaveFormService.updateLeaveForm(leaveForm);
//                //保存修改状态之后的请假流程表
//                processService.updateProcess(processList.get(i));
//                //删除这张请假表对应的其它的请假流程
//                for (int i1 =0; i<processList.size();i++){
//                    if (processList.get(i1).getApproverGroupId()!=0){
//                        //删除这张请假流程表
//                        processService.deleteRestProcess(leaveForm.getId(),processList.get(i1).getApproverGroupId());
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    @RequestMapping("/toApproavedLeaveForm")
//    @ResponseBody
//    public ModelAndView toApproavedLeaveForm(@RequestParam(value="pageNo",required=false)String pageNo,
//                                             HttpServletRequest request) throws Exception {
//        //从当前session中获取到学生的请假表信息
//        Student  student = (Student) request.getSession().getAttribute("student");
//        ModelAndView mv = new ModelAndView();
//        PageBean pageBean = new PageBean(Integer.parseInt(pageNo),5);
//        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("start", pageBean.getStart());
//        map.put("size", 5);
//        map.put("id",student.getId());
//        //返回请假表的数量。
//        Long total=leaveFormService.getTotal(student.getId());
//        List<LeaveForm> leaveForms = leaveFormService.getApprovedLeaveFormById(map);
//        List<LeaveFormStudent> list = new ArrayList();
//        for (LeaveForm leaveForm : leaveForms){
//            LeaveFormStudent leaveFormStudent = new LeaveFormStudent();
//            leaveFormStudent.setName(student.getName());
//            leaveFormStudent.setCardNumber(student.getCardNumber());
//            leaveFormStudent.setStartTime(leaveForm.getStartTime());
//            leaveFormStudent.setEndTime(leaveForm.getEndTime());
//            leaveFormStudent.setReason(leaveForm.getReason());
//            leaveFormStudent.setState(leaveForm.getState());
//            list.add(leaveFormStudent);
//        }
//        int totalPages = (int) Math.ceil(total/5.00);
//        mv.setViewName("student/approvedLeaveForm");
//        mv.addObject("leaveFormList",list)
//                .addObject("totalItems", total)  //获取总的记录数
//                .addObject("totalPages", totalPages)  //总的页数
//                .addObject("curPage", pageNo);   //curPage当前页数;
//        return  mv;
//    }
//    @RequestMapping("/toUnApprovedLeaveForm")
//    @ResponseBody
//    public ModelAndView toUnApprovedLeaveForm(@RequestParam(value="pageNo",required=false)String pageNo,
//                                              HttpServletRequest request) throws Exception {
//        //从当前session中获取到学生的请假表信息
//        Student  student = (Student) request.getSession().getAttribute("student");
//        ModelAndView mv = new ModelAndView();
//        PageBean pageBean = new PageBean(Integer.parseInt(pageNo),5);
//        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("start", pageBean.getStart());
//        map.put("size", 5);
//        map.put("id",student.getId());
//        //返回请假表的数量。
//        Long total=leaveFormService.getUnTotal(student.getId());
//        List<LeaveForm> leaveForms = leaveFormService.getUnApprovedLeaveFormById(map);
//        List<LeaveFormStudent> list = new ArrayList();
//        for (LeaveForm leaveForm : leaveForms){
//            LeaveFormStudent leaveFormStudent = new LeaveFormStudent();
//            leaveFormStudent.setName(student.getName());
//            leaveFormStudent.setCardNumber(student.getCardNumber());
//            leaveFormStudent.setStartTime(leaveForm.getStartTime());
//            leaveFormStudent.setEndTime(leaveForm.getEndTime());
//            leaveFormStudent.setReason(leaveForm.getReason());
//            leaveFormStudent.setState(leaveForm.getState());
//            list.add(leaveFormStudent);
//        }
//        int totalPages = (int) Math.ceil(total/5.00);
//        mv.setViewName("student/unApprovedLeaveForm");
//        mv.addObject("leaveFormList",list)
//                .addObject("totalItems", total)  //获取总的记录数
//                .addObject("totalPages", totalPages)  //总的页数
//                .addObject("curPage", pageNo);   //curPage当前页数;
//        return  mv;
//    }
//    @RequestMapping("/toAllLeaveForm")
//    @ResponseBody
//    public ModelAndView toAllLeaveForm(@RequestParam(value="pageNo",required=false)String pageNo,
//                                       HttpServletRequest request) throws Exception {
//        //从当前session中获取到学生的请假表信息
//        Student  student = (Student) request.getSession().getAttribute("student");
//        ModelAndView mv = new ModelAndView();
//        PageBean pageBean = new PageBean(Integer.parseInt(pageNo),5);
//        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("start", pageBean.getStart());
//        map.put("size", 5);
//        map.put("id",student.getId());
//        //返回请假表的数量。
//        Long total=leaveFormService.getAllTotal(student.getId());
//        List<LeaveForm> leaveForms = leaveFormService.getAllLeaveFormById(map);
//        List<LeaveFormStudent> list = new ArrayList();
//        for (LeaveForm leaveForm : leaveForms){
//            LeaveFormStudent leaveFormStudent = new LeaveFormStudent();
//            leaveFormStudent.setName(student.getName());
//            leaveFormStudent.setCardNumber(student.getCardNumber());
//            leaveFormStudent.setStartTime(leaveForm.getStartTime());
//            leaveFormStudent.setEndTime(leaveForm.getEndTime());
//            leaveFormStudent.setReason(leaveForm.getReason());
//            leaveFormStudent.setState(leaveForm.getState());
//            list.add(leaveFormStudent);
//        }
//        int totalPages = (int) Math.ceil(total/5.00);
//        mv.setViewName("student/allLeaveForm");
//        mv.addObject("leaveFormList",list)
//                .addObject("totalItems", total)  //获取总的记录数
//                .addObject("totalPages", totalPages)  //总的页数
//                .addObject("curPage", pageNo);   //curPage当前页数;
//        return  mv;
//    }
//    @RequestMapping("/toApprovingLeaveform")
//    @ResponseBody
//    public ModelAndView toApprovingLeaveform(@RequestParam(value="pageNo",required=false)String pageNo,
//                                             HttpServletRequest request) throws Exception {
//        //从当前session中获取到学生的请假表信息
//        Student  student = (Student) request.getSession().getAttribute("student");
//        ModelAndView mv = new ModelAndView();
//        PageBean pageBean = new PageBean(Integer.parseInt(pageNo),5);
//        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("start", pageBean.getStart());
//        map.put("size", 5);
//        map.put("id",student.getId());
//        //返回请假表的数量。
//        Long total=leaveFormService.getApprovingTotal(student.getId());
//        List<LeaveForm> leaveForms = leaveFormService.getApprovingLeaveFormById(map);
//        List<LeaveFormStudent> list = new ArrayList();
//        for (LeaveForm leaveForm : leaveForms){
//            LeaveFormStudent leaveFormStudent = new LeaveFormStudent();
//            leaveFormStudent.setName(student.getName());
//            leaveFormStudent.setCardNumber(student.getCardNumber());
//            leaveFormStudent.setStartTime(leaveForm.getStartTime());
//            leaveFormStudent.setEndTime(leaveForm.getEndTime());
//            leaveFormStudent.setReason(leaveForm.getReason());
//            leaveFormStudent.setState(leaveForm.getState());
//            list.add(leaveFormStudent);
//        }
//        int totalPages = (int) Math.ceil(total/5.00);
//        mv.setViewName("student/approvingLeaveForm");
//        mv.addObject("leaveFormList",list)
//                .addObject("totalItems", total)  //获取总的记录数
//                .addObject("totalPages", totalPages)  //总的页数
//                .addObject("curPage", pageNo);   //curPage当前页数;
//        return  mv;
//    }
//
//    @RequestMapping("/queryLeaveFormById")
//    public @ResponseBody
//    LeaveFormStudent queryLeaveFormById(@RequestParam("leaveformId") String id , HttpServletResponse response) throws Exception {
//        ModelAndView mv =new ModelAndView();
//        LeaveForm leaveForm1 = leaveFormService.queryLeaveFormById(id);
//        Student student = studentService.getStudentById(leaveForm1.getStudentId());
//        LeaveFormStudent leaveForm = new LeaveFormStudent();
//        leaveForm.setCardNumber(student.getCardNumber());
//        leaveForm.setName(student.getName());
////        leaveForm.setMajor(student.getMajor());
//        leaveForm.setGrade(student.getGrade());
//        leaveForm.setPhoneNumber(student.getPhoneNumber());
//        leaveForm.setStartTime(leaveForm1.getStartTime());
//        leaveForm.setEndTime(leaveForm1.getEndTime());
//        leaveForm.setReason(leaveForm1.getReason());
////        ResponseUtil.write(response,leaveForm);
//        return leaveForm;
//    }
//    @RequestMapping("/deleteLeaveFormById")
//    public @ResponseBody
//    LeaveForm  deleteLeaveFormById(@RequestParam("leaveformId") String id) throws Exception {
//        LeaveForm leaveForm = leaveFormService.queryLeaveFormById(id);
//        //请假表的状态值设置为2表示被删除
//        leaveForm.setState("2");
//        leaveFormService.updateLeaveForm(leaveForm);
////        leaveFormService.deleteLeaveForm(id);
//        return leaveForm ;
//    }
//}
