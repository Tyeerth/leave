package cn.jxust.leave.po.vo;

import cn.jxust.leave.po.Academy;
import cn.jxust.leave.po.Campus;
import cn.jxust.leave.po.Major;
import cn.jxust.leave.po.Role;
import lombok.*;

import java.sql.Timestamp;

/**
 * @author tyeerth
 * @date 2019/12/12 - 8:39
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@ToString
public class LeaveFormStudent {

    private Integer grade;
    private String id;
    private Timestamp startTime;
    private Timestamp endTime;
    private String reason;
    private Integer days;
    private Timestamp submitTime;
    private String type;
    private Integer studentId;
    private String state;

    private String dormitory;
    private Integer roleId;
    private Role role;
    private String mail;
    private String name;

    private String gender;

    private Integer academyId;
    private Academy academy;

    private Integer campusId;
    private Campus campus;



    private String university;

    private String cardNumber;

    private String password;

    private String address;

    private String phoneNumber;

    private Integer buildingId;


    private Integer majorId;
    private Major major;

    private String clazz;



}
