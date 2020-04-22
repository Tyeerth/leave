package cn.jxust.leave.po;

import lombok.*;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@ToString
public class Student implements Serializable {
    private Integer id;

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


    private String dormitory;

    private Integer roleId;
    private Role role;

    private String mail;

    private String state;

    private Integer majorId;
    private Major major;

    private String clazz;


    private Integer grade;

}