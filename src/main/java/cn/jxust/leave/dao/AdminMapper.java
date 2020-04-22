package cn.jxust.leave.dao;

import cn.jxust.leave.po.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 这是管理员的dao层的mapper接口
 * @author tyeerth
 * @date 2019/11/13 - 19:27
 */
@Repository
public interface AdminMapper {
    /**
     * 修改管理员的信息。
     * @param admin
     */
     void updateAdmin(Employee admin);

    /**
     * 实现管理员登入功能。
     * @param username
     * @return
     */
    Employee getAdminByUserName(@Param(value = "username") String username);
}
