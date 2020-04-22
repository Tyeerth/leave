package cn.jxust.leave.po;


import lombok.*;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@ToString
public class Employee implements Serializable {

  private Integer id;
  private String name;
  private String gender;
  private Integer roleId;
  private Role role;
  private String phoneNumber;
  private String username;
  private String password;


}
