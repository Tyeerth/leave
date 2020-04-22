package cn.jxust.leave.po;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@ToString
public class Guardian {

  private Integer id;
  private String name;
  private String phoneNumber;
  private Integer studentId;


}
