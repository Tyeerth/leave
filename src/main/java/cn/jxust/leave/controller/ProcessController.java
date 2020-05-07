//package cn.jxust.leave.controller;
//
//import cn.jxust.leave.po.Process;
//import cn.jxust.leave.po.Student;
//import cn.jxust.leave.po.vo.StuLeaForPros;
//import cn.jxust.leave.service.ProcessService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
///**
// * 这个类用来接收对请假流程相关的请求的
// * @author JJ
// * @date 2019/11/16 - 18:11
// */
//@Controller
//@RequestMapping("/process")
//public class ProcessController {
//    @Autowired
//    private  ProcessService processService;
//        /*
//        权限对应的id
//        1   学生
//        2   班长
//        3   班主任
//        4   学院(校区)领导
//        5   学工部
//        6   学校主管领导
//        7   辅导员
//        8   管理员
//         */
//
//    /**
//     * 通过这个接口来查询出需要自己审批的流程
//     * @param request
//     * @return
//     */
//    @RequestMapping("/queryRequiredApprovedProcesses")
//    @ResponseBody
//    public List<Process> queryRequiredApprovedProcesses(HttpServletRequest request){
//        //查询出需要自己审核的流程。
//        List<Process> processList = processService.queryAllProcess();
//        return processList;
//    }
//    /**
//     * @Description 学生通过自己的id
//     *       查询出当前流程,如老师是否批假等.
//     * @Author   lcs
//     **/
//    @RequestMapping("/queryStudentProcesses")
//
//    public ModelAndView queryStudentProcesses(HttpServletRequest request){
//        Student student = (Student) request.getSession().getAttribute("student");
//        ModelAndView mv = new ModelAndView("student/leaveFormProcess");
//
//        List<StuLeaForPros> processList=processService.queryStudentProcessesListByStudentId(student.getId());
//        //List<StuLeaForPros> processList=processService.queryStudentProcessesListByStudentId(1555);
//
//        mv.addObject("processList",processList );
//        return mv;
//    }
//
//
//}
