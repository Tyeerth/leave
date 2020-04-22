package cn.jxust.leave.po;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@ToString
public class Process {

  private String id;
  private String leaveFormId;
  private Integer approverGroupId;
  private String preProcessId;
  private String nextProcessId;
  private Integer approverId;
  private Integer isApproved;
  private String comment;
  private String state;


}
