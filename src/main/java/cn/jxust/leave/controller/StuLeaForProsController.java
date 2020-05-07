//package cn.jxust.leave.controller;
//
//import cn.jxust.leave.po.vo.StuLeaForPros;
//import cn.jxust.leave.service.LeaveFormService;
//import cn.jxust.leave.service.StuLeaForProsService;
//import cn.jxust.leave.utils.JsonMsg;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.List;
//
///**
// * @Description
// *
// * @Author   lcs
// * @Date 22:29 2019/11/15
// **/
//@Controller
//@RequestMapping("/StuLeaForPros")
//public class StuLeaForProsController {
//
//    @Autowired
//    StuLeaForProsService stuLeaForProsService;
//    @Autowired
//    LeaveFormService leaveFormService;
//
//
//
//    /**
//     * 查询
//     * @param pageNo 查询指定页码包含的数据
//     * @return
//     */
//    @RequestMapping(value = "/getCancelForLeave", method = RequestMethod.GET)
//    public ModelAndView getCancelForLeave(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo){
//        ModelAndView mv = new ModelAndView("CancelForLeave");
//        int limit = 5;
//        // 记录的偏移量(即从第offset行记录开始查询)，
//        // 如第1页是从第1行(offset=(21-1)*5=0,offset+1=0+1=1)开始查询；
//        // 第2页从第6行(offset=(2-1)*5=5,offset+1=5+1=6)记录开始查询
//        int offset = (pageNo-1)*limit;
//        //获取指定页数包含的学生信息
//        List<StuLeaForPros> leaveForms = stuLeaForProsService.getStuLeaForProsList(offset, limit);
//
//
//        //获取总的记录数
//        int totalItems = stuLeaForProsService.getStuLeaForProsCount();
//        //获取总的页数
//        int temp = totalItems / limit;
//        int totalPages = (totalItems % limit == 0) ? temp : temp+1;
//        //当前页数
//        int curPage = pageNo;
//
//        //将上述查询结果放到Model中，在JSP页面中可以进行展示
//        mv.addObject("leaveForms", leaveForms)
//                .addObject("totalItems", totalItems)    //获取总的记录数
//                .addObject("totalPages", totalPages)  //总的页数
//                .addObject("curPage", curPage);   //curPage当前页数
//
//        return mv;
//    }
//
//
//    /**
//     * @Description  这里会将传递过来cardNumber的,进行模糊查询,
//     *                 查询出所有符合条件的值
//     *
//     * @param cardNumber 一卡通号
//     **/
//
//    @RequestMapping(value = "/getCancelForLeaveByCardNumber", method = RequestMethod.GET)
//    public ModelAndView getCancelForLeaveByCardNumber(@RequestParam("cardNumber") String cardNumber){
//        ModelAndView mv = new ModelAndView("CancelForLeave");
//        List<StuLeaForPros>  leaveForms = stuLeaForProsService.getStuLeaForProsByCardNumberLike(cardNumber);
//        //将上述查询结果放到Model中，在JSP页面中可以进行展示
//        mv.addObject("leaveForms", leaveForms)
//                .addObject("totalItems", 1)
//                .addObject("totalPages", 1)  //
//                .addObject("curPage", 1);   //curPage当前页数
//        return mv;
//    }
//
//    /**
//     * 根据cardNumber查询员工信息,确认是否存在该用户
//     * TODO  这是初步的简单判断,后续将继续优化
//     */
//    @RequestMapping(value = "/getLeaveFormCardNumber/{cardNumber}", method = RequestMethod.GET)
//    @ResponseBody
//    public JsonMsg getLeaveFormByCardNumber(@PathVariable("cardNumber") String cardNumber){
//
//        List<StuLeaForPros> stuLeaForPros
//                = stuLeaForProsService.getStuLeaForProsByCardNumberLike(cardNumber);
//        if(stuLeaForPros!=null){
//            return JsonMsg.success();
//        }else{
//            return JsonMsg.fail().addInfo("cardNumber_error",
//                    "该学生未请假或者你输入的一卡通错误");
//        }
//    }
//
//
//    @RequestMapping(value = "/toIndex", method = RequestMethod.GET)
//    public ModelAndView getIndex(String cardNumber){
//        ModelAndView mv = new ModelAndView("employee/index");
//        List<StuLeaForPros>  leaveForms = stuLeaForProsService.getStuLeaForProsList(0, 5);  //
//        System.out.println(leaveForms);
//        //将上述查询结果放到Model中，在JSP页面中可以进行展示
//        mv.addObject("leaveForms", leaveForms)
//                .addObject("todayCounts", leaveFormService.getLeaveFormCountsByDays(1))
//                .addObject("yesterdayCounts", leaveFormService.getLeaveFormCountsByDays(2))
//                .addObject("thisWeekCounts", leaveFormService.getLeaveFormCountsByDays(3))
//                .addObject("lastWeekCounts", leaveFormService.getLeaveFormCountsByDays(4))
//                .addObject("daysData", leaveFormService.selectWeekLeaveFormCountsByDays());
//
//       // System.out.println(leaveFormService.selectWeekLeaveFormCountsByDays());
//
//
//        return mv;
//    }
//}
//   /*   Date day=new Date();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(df.format(day));*/