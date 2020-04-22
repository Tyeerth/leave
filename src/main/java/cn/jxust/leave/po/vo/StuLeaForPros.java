package cn.jxust.leave.po.vo;

import lombok.*;

import java.sql.Timestamp;
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@ToString
public class StuLeaForPros {
    private Integer id;
    private String cardNumber;
    private String role;
    private String name;
    private String major;
    private String clazz;
    private Integer grade;
    private String phoneNumber;
    private Timestamp startTime;
    private Timestamp endTime;
    private String leaveFormState;


    private String processState;
    private String processId;
    private String isApproved ;
    private String comment;
    private String preProcess;
    private String preProcessId;
    private String nextProcess;
    private String nextProcessId;
}
