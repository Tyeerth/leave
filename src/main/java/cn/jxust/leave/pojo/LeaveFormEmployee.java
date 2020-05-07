package cn.jxust.leave.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("leave_form_employee")
public class LeaveFormEmployee {

  @TableId(type = IdType.ASSIGN_UUID)
  private String id;
  private java.sql.Timestamp startTime;
  private java.sql.Timestamp endTime;
  private String reason;
  private long days;
  private java.sql.Timestamp submitTime;
  private String type;
  private long employeeId;
  private String state;
  private String toPlace;

}
