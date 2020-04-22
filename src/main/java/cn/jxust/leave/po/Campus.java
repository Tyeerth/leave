package cn.jxust.leave.po;


import lombok.*;

import java.util.ArrayList;
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@ToString
public class Campus {

  private Integer id;
  private String campus;
  private ArrayList<cn.jxust.leave.po.Student> students;


}
