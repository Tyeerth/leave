package cn.jxust.leave.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("process")
public class Process {

  private String id;
  private String leaveFormId;
  private long approverGroupId;
  private String preProcessId;
  private String nextProcessId;
  private long approverId;
  private long isApproved;
  private String comment;
  private String state;

}
