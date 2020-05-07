package cn.jxust.leave.service.impl;

import cn.jxust.leave.dao.LeaveFormMapper;
import cn.jxust.leave.dao.StudentMapper;
import cn.jxust.leave.pojo.Employee;
import cn.jxust.leave.pojo.LeaveForm;
import cn.jxust.leave.pojo.Process;
import cn.jxust.leave.pojo.Student;
import cn.jxust.leave.service.LeaveFormService;
import cn.jxust.leave.service.ProcessService;
import cn.jxust.leave.service.StudentService;
import cn.jxust.leave.utils.TimeUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author JJ
 * @date 2019/11/10 - 15:08
 */
@Service
public class LeaveFormServiceImpl implements LeaveFormService {

    @Resource(name="leaveFormMapper")
    LeaveFormMapper leaveFormMapper;

    @Resource(name = "processServiceImpl")
    ProcessService processService;

    @Autowired
    StudentService studentService;

    @Autowired
    private StudentMapper studentMapper;
    int x =100;

    private Date formatDate(String dateS){
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(dateS);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 接收传来的leaveForm,通过start_time和end_time判断请假天数,然后根据天数预创建请假流程
     * @param leaveForm
     * @throws Exception
     */
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class,timeout=1,isolation= Isolation.DEFAULT)
    public void saveLeaveForm(LeaveForm leaveForm) throws Exception {

        //自己维护leave_form的主键
        String uuid = UUID.randomUUID().toString();
        leaveForm.setId(uuid);

        //计算得到请假天数
        int duringTime = TimeUtils.getDuringTime(leaveForm.getStartTime(), leaveForm.getEndTime());
        leaveForm.setDays(duringTime);

        //插入表单提交的时间
        leaveForm.setSubmitTime(new Timestamp(new Date().getTime()));

        //将请假表单存入数据库
        leaveFormMapper.saveLeaveForm(leaveForm);

        /*
        权限对应的id
        1   学生
        2   班长
        3   班主任
        4   学院(校区)领导
        5   学工部
        6   学校主管领导
        7   辅导员
        8   管理员
         */


        List<Integer> list = new ArrayList<Integer>();

        //请假天数在三天之内的由班长和辅导员审核。
        if(duringTime<3){
            list.add(2);
            list.add(7);
        }
        //请假天数在三天到七天之内的由班长学院领导审核。
        else if(duringTime>=3 && duringTime<=7){
            list.add(2);
            list.add(4);
        }
        //请假天数在七天到十五天的由班长、学院领导、学工部审核。
        else if(duringTime>7 && duringTime<=15){
            list.add(2);
            list.add(4);
            list.add(5);
        }
        //请假天数大于十五天的由班长、学院领导、学工部、学校主管领导审核。
        else if(duringTime>15){
            list.add(2);
            list.add(4);
            list.add(5);
            list.add(6);
        }
            //根据请假表的id查到学生的信息。
        Student student = studentMapper.getstudentByLeaveFormId(leaveForm);
            //查询自己的班长是谁
        Student monitor = studentMapper.queryMonitor(student);
            //查询自己的辅导员是谁。
        Employee instructor =studentMapper.queryInstructor(student);
            //查询自己的学院领导是谁
        Employee academyManager = studentMapper.queryAcademyMaster(student);
            //查询自己的所属的学工部是由谁管理谁。
        Employee affairManager = studentMapper.queryAffairManager();
            //查询自己的学校主管领导是谁。
        Employee campusManager = studentMapper.queryCampusManager(student);
        int n=list.size();

        for(int i=0;i<n;i++){

            Process process = new Process();
            process.setId(uuid+"_"+(i+1));
            process.setLeaveFormId(leaveForm.getId());
            //可以把这里当成判断流程表执行顺序的条件。
            //累加算法
                x+=i;
            System.out.println("X=" +x);
            process.setApproverGroupId(i);
            if (list.get(i)==2){
                //请假流程是给当前学生的班长审核。
                process.setApproverId(monitor.getId());
            }else if(list.get(i)==4){
                //当前请假流程是给学院领导审核的。
                process.setApproverId(campusManager.getId());
            }else if (list.get(i)==5){
                //当前流程是给学工部审核的。
                process.setApproverId(affairManager.getId());
            }else if (list.get(i)==6){
                //当前流程是给学校主管领导审核的
                process.setApproverId(academyManager.getId());
            }else{
                //当前流程是给辅导员审核的。
                process.setApproverId(instructor.getId());
            }


            //判断是否有上一个请假流程。
            if(i==0){
                //无上一流程。
                process.setPreProcessId("0");
                //审核已经激活。。
                process.setState("1");
            }else{
                process.setPreProcessId(uuid+"_"+i);
                //设置当前状态为未激活。
                process.setState("0");
            }
            //判断是否有下一个请假流程。
            if(i==n-1){
                //无下一个请假流程。
                process.setNextProcessId("0");
            }else{
                process.setNextProcessId(uuid+"_"+i+2);
            }

            processService.saveProcess(process);
        }


    }

    /**
     * 传入表单id删除表单
     * @param id
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class ,timeout = 1)
    public void deleteLeaveForm(String id) throws Exception {

        //先删除表单对应的流程
        processService.deleteProcess(id);
        //删除请假表单
        leaveFormMapper.deleteLeaveForm(id);
    }

    /**
     * 根据表单id获取请假表单信息
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public LeaveForm getLeaveFormInfo(String id) throws Exception {
        return leaveFormMapper.getLeaveFormInfo(id);
    }



    /**
     * ===========================================================================================
     */

    /**
     * @Description 通过student_id查询,该学生的请假信息
     * @Author   lcs
     **/
     @Override
     public LeaveForm getLeaveFormByStudentId(Integer studentId){
         return leaveFormMapper.selectLeaveFormByStudentId(studentId);
     }

    @Override
    public List<LeaveForm> studentGetLeaveFormByCardNumber(String cardNumber) {
        return null;
    }

    /**
     * @Description  传入student的cardNumber值,即可查询自己的请假信息
     *                而班长的可以查询,全班所有人的
     * @param cardNumber 一卡通号
     **/
//    @Override
//    public List<LeaveForm> studentGetLeaveFormByCardNumber(String  cardNumber){
//        Student student=studentService.getStudentByCardNumber(cardNumber);
//        if(student!=null){
//            if(student.getRoleId()==2){    //1对应普通学生,2对应班长
//                return  leaveFormMapper.selectLeaveFormListForMonitor(student.getMajorId(),student.getClazz(),student.getGrade());
//            }else{
//                List<LeaveForm> leaveForms=new ArrayList<>();
//                leaveForms.add(leaveFormMapper.selectLeaveFormByStudentId(student.getId()));
//                return leaveForms;
//            }
//
//        }
//        return null;
//    }


    /**
     * @Description
     *
     * @param timeType    时间范围: 1 ==> 今天, 2 ==> 昨天, 3 ==> 本周,4 ==>上周;
     * @Author   lcs
     **/

    @Override
    public int getLeaveFormCountsByDays(int timeType){
        return leaveFormMapper.selectLeaveFormCountsByDays(timeType);
    }

    @Override
    public void insertLeaveForm(LeaveForm leaveForm)throws Exception{
        //自己维护leave_form的主键
        String uuid = UUID.randomUUID().toString();
        leaveForm.setId(uuid);

        //计算得到请假天数
        int duringTime = TimeUtils.getDuringTime(leaveForm.getStartTime(), leaveForm.getEndTime());
        leaveForm.setDays(duringTime);


        //将请假表单存入数据库
        leaveFormMapper.saveLeaveForm(leaveForm);
    }

    /**
     * 查询到对应的请假表
     * @param leaveFormId
     * @return
     */
    @Override
    public LeaveForm queryLeaveFormById(String leaveFormId) {
        return leaveFormMapper.queryLeaveFormById(leaveFormId);
    }

    /**
     * 查询请假表中的学生信息。
     * @param studentId
     * @return
     */
    @Override
    public Student queryStudentByLeaveForm(Integer studentId) {
        return studentMapper.getStudentById(studentId);
    }

    @Override
    public List<LeaveForm> getApprovedLeaveFormByCardNumber() {
        return null;
    }


    /**
     * 返回审核通过的请假表的总数
     * @return
     */
    @Override
    public Long getTotal(Integer Id) {
        return leaveFormMapper.getTotal(Id);
    }

    @Override
    public List<LeaveForm> getApprovedLeaveFormById(Map<String, Object> map) {
        return leaveFormMapper.getApprovedLeaveFormById(map);
    }

    @Override
    public List<LeaveForm> getUnApprovedLeaveFormById(Map<String, Object> map) {
        return leaveFormMapper.getUnApprovedLeaveformById(map);
    }

    @Override
    public Long getUnTotal(Integer id) {
        return leaveFormMapper.getUnTotal(id);
    }

    @Override
    public List<LeaveForm> getAllLeaveFormById(Map<String, Object> map) {
        return leaveFormMapper.getAllLeaveForm(map);
    }

    @Override
    public Long getAllTotal(Integer id) {
        return leaveFormMapper.getAllTotal(id);
    }

    @Override
    public List<LeaveForm> getApprovingLeaveFormById(Map<String, Object> map) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("student_id",map.get("id"));
        queryWrapper.eq("state",0);
        return leaveFormMapper.selectList(queryWrapper);
    }

    @Override
    public Long getApprovingTotal(Integer id) {
        return leaveFormMapper.getApprovingTotal(id);
    }

    @Override
    public List<LeaveForm> getApprovingLeaveForm(Map<String, Object> map) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("student_id",map.get("id"));
        queryWrapper.eq("state",0);
        return leaveFormMapper.selectList(queryWrapper);
    }

    /**
     * 修改请假表的信息
     * @param leaveForm1
     */
    @Override
    public void updateLeaveForm(LeaveForm leaveForm1) {
        leaveFormMapper.updateById(leaveForm1);
    }

    @Override
    public  List<Integer> selectWeekLeaveFormCountsByDays(){
        List<Integer> days=new ArrayList<>();
        for(int i=1;i<=7;i++){
            days.add(leaveFormMapper.selectWeekLeaveFormCountsByDays(i));
        }
        return days;
    }

    /**
     * 获取当前用户需要注销的请假条
     * @param id
     */
    @Override
    public PageInfo<LeaveForm> getCancelLeaveForm(Integer id,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("state",1);
        queryWrapper.eq("student_id",id);
        List<LeaveForm> list = leaveFormMapper.selectList(queryWrapper);
        //封装到PageInfo对象中
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<LeaveForm> getUnApprovedLeaveForm(Integer id,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("state",-1);
        queryWrapper.eq("student_id",id);
        List<LeaveForm> list = leaveFormMapper.selectList(queryWrapper);
        //封装到PageInfo对象中
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<LeaveForm> getStudentApprovingLeaveForm(Integer id, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("state",0);
        queryWrapper.eq("student_id",id);
        List<LeaveForm> list = leaveFormMapper.selectList(queryWrapper);
        //封装到PageInfo对象中
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<LeaveForm> getApprovedLeaveForm(Integer id, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("state",1);
        queryWrapper.eq("student_id",id);
        List<LeaveForm> list = leaveFormMapper.selectList(queryWrapper);
        //封装到PageInfo对象中
        return new PageInfo<>(list);
    }

}
