package cn.jxust.leave.po;

import lombok.*;

import java.io.Serializable;

/**
 * 用户登入日志
 * @author tyeerth
 * @date 2020/2/22 - 9:41
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@ToString
public class LoginLog implements Serializable {

    //主键
    private String LoginLogID;

    private String userId;

    private String logintime;

    private int success;
}
