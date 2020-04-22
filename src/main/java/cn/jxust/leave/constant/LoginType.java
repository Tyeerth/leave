package cn.jxust.leave.constant;

/**
 * 枚举类来表示当前用户的登入类型。
 * 这里只创建学生、员工、管理员
 * @author tyeerth
 * @date 2019/12/4 - 18:54
 */
public enum LoginType {
    STUDENT("Student"),
    ADMIN("Admin"),
    EMPLOYEE("Employee");
    private String type;

    private LoginType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type.toString();
    }
}
