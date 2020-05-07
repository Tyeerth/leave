package cn.jxust.leave.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.security.auth.spi.LoginModule;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("leave_form")
public class LeaveForm {

  @TableId(type = IdType.ASSIGN_UUID)
  private String id;
  private java.sql.Timestamp startTime;
  private java.sql.Timestamp endTime;
  private String reason;
  private Integer days;
  private java.sql.Timestamp submitTime;
  private String type;
  private Integer studentId;
  private String state;
  private String toPlace;


}
