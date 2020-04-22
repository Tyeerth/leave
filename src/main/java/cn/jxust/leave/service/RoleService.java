package cn.jxust.leave.service;

import cn.jxust.leave.po.LeaveForm;

import java.util.List;

/**
 * @Description
 *
 * @Author   lcs
 * @Date 22:13 2019/11/14
 **/
public interface RoleService {

    /**
     * @Description 通过Role的id,查询其对应角色的中文
     **/
    String getRoleNameById(Integer id);



    /**
     * @Description 就是让employee表中,对应的角色,
     *              查到其对应管理下的全部学生的LeaveForm表的数据
     **/
    List<LeaveForm> employeeGetLeaveFormById(Integer id);


}
