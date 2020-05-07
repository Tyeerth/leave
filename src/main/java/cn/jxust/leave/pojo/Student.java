package cn.jxust.leave.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("student")
public class Student {
  @TableId(type = IdType.AUTO)
  private Integer id;
  private String name;
  private String gender;
  private String academy;
  private String campus;
  private String university;
  private String cardNumber;
  private String password;
  private String address;
  private String phoneNumber;
  private Integer buildingId;
  private String dormitory;
  private String role;
  private String mail;
  private String state;
  private String major;
  private String clazz;
  private Integer grade;



}
