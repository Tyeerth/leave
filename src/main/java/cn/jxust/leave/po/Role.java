package cn.jxust.leave.po;


import lombok.*;

import java.util.ArrayList;
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@ToString
public class Role {

  private Integer id;
  private String role;
  private ArrayList<cn.jxust.leave.po.Student> students;


}
