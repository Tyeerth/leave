package cn.jxust.leave.security;

import cn.jxust.leave.constant.LoginConsts;
import cn.jxust.leave.exceptionResolver.LoginFailedException;
import cn.jxust.leave.pojo.Student;
import cn.jxust.leave.service.StudentService;
import cn.jxust.leave.utils.ContextHolderUtils;
import cn.jxust.leave.utils.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tyeerth
 * @date 2020/5/1 - 15:50
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private StudentService studentService;
    private static  final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    /**
     总目标：根据表单提交的用户名查询User对象，并装配角色、权限等信息
     */
    @Override
    public UserDetails loadUserByUsername(
            // 表单提交的用户名
            String username
            ) throws UsernameNotFoundException {
        HttpSession session = ContextHolderUtils.getRequest().getSession();
        String password = ContextHolderUtils.getRequest().getParameter("password");
        String captcha = ContextHolderUtils.getRequest().getParameter("captcha");

        Student studentByCardNumber = studentService.getStudentByCardNumber(username);
        session.setAttribute(LoginConsts.STUDENT,studentByCardNumber);
        session.setAttribute("captcha",captcha);

        if (username == null || "".equals(username)) {
            throw new LoginFailedException("用户名不能为空");
        }
        if (studentByCardNumber ==null){
            throw new LoginFailedException("当前用户不存在");
        }
        if (!MD5Utils.md5(password).equals(studentByCardNumber.getPassword())){
            throw new LoginFailedException("用户名和密码不匹配");
        }
        // 2.给Admin设置角色权限信息
        List<GrantedAuthority> authorities = new ArrayList<>();
        //添加用户权限
        authorities.add(new SimpleGrantedAuthority(studentByCardNumber.getRole()));

//        authorities.add(new SimpleGrantedAuthority("UPDATE"));
        logger.info("从session域中获取到的密码"+password);

        return new SecurityStudent(studentByCardNumber, authorities);
    }
}
