package cn.jxust.leave.security;

import cn.jxust.leave.pojo.Student;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author tyeerth
 * @date 2020/5/1 - 22:38
 */
public class SecurityStudent  extends User {
    private static final long serialVersionUID = 1L;
    private Student originalStudent;
    public SecurityStudent(Student student,Collection<GrantedAuthority> authorities) {
        super(student.getCardNumber(), student.getPassword(), true, true, true, true, authorities);
        this.originalStudent = student;
    }
    public Student getOriginalStudent() {
        return originalStudent;
    }
}
