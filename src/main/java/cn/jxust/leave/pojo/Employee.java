package cn.jxust.leave.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("employee")
public class Employee {

  private Integer id;
  private String name;
  private String gender;
  private Integer roleId;
  private String phoneNumber;
  private String username;
  private String password;

}
