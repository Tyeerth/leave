package cn.jxust.leave.dao;

import cn.jxust.leave.po.LeaveForm;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    @Select("select role from role where id=#{id}")
    String selectRoleNameById(Integer id);



    List<LeaveForm> selectLeaveFormListForEmployee(@Param("id") Integer id);





}