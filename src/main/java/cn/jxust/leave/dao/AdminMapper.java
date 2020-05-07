package cn.jxust.leave.dao;

import cn.jxust.leave.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 这是管理员的dao层的mapper接口
 * @author tyeerth
 * @date 2019/11/13 - 19:27
 */
@Repository
public interface AdminMapper extends BaseMapper<Employee> {
//    /**
//     * 修改管理员的信息。
//     * @param admin
//     */
//     void updateAdmin(Employee admin);

    /**
     * 实现管理员登入功能。
     * @param username
     * @return
     */
//    Employee getAdminByUserName(@Param(value = "username") String username);
}
