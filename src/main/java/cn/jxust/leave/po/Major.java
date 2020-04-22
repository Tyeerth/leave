package cn.jxust.leave.po;


import lombok.*;

import java.util.ArrayList;
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@ToString
public class Major {

  private Integer id;
  private String major;
  private ArrayList<cn.jxust.leave.po.Student> students;

}
