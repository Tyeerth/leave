package cn.jxust.leave.po.vo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@ToString
public class EmpLeaFrom {
    private String major;
    private String clazz;
    private Integer grade;
    private Integer clazzPeopleCount;
    private Integer todayPeopleCount;
    private Integer yesterdayPeopleCount;
    private Integer thisWeekPeopleCount;

}
