package cn.jxust.leave.po;


import lombok.*;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@ToString
public class Academy {

  private Integer id;
  private String academy;
  private ArrayList<cn.jxust.leave.po.Student> students;
}
