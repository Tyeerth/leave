package cn.jxust.leave.po;


import cn.jxust.leave.utils.TimeUtils;

import java.sql.Timestamp;

public class LeaveForm {

  private String id;
  private Timestamp startTime;
  private Timestamp endTime;
  private String reason;
  private Integer days;
  private Timestamp submitTime;
  private String type;
  private Integer studentId;
  private String state;


  @Override
  public String toString() {
    return "LeaveForm{" +
            "id=" + id +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", reason='" + reason + '\'' +
            ", days=" + days +
            ", submitTime=" + submitTime +
            ", type='" + type + '\'' +
            ", studentId=" + studentId +
            ", state='" + state + '\'' +
            '}';
  }

  /**
   * @Description  String time = "2012-02-21T13:21"; 前端传来数据类型皆为字符串类型
   *         这里为了将时间的String类型数据转换为Timestamp类型
   *         经测试重载setStartTime()方法无效
   *         但重写setStartTime()方法有效,但封装数据库数据时会出现错误,不建议使用
   *         未使用注解测试过,待尝试
   **/
  public void setStartTimeToString(String startTimeToString) {

    this.startTime = TimeUtils.getStringToTimestamp(startTimeToString);
  }

  public void setEndTimeToString(String endTimeToString) {
    this.endTime = TimeUtils.getStringToTimestamp(endTimeToString);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Timestamp getStartTime() {
    return startTime;
  }

  public void setStartTime(Timestamp startTime) {
    this.startTime = startTime;
  }

  public Timestamp getEndTime() {
    return endTime;
  }

  public void setEndTime(Timestamp endTime) {
    this.endTime = endTime;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }


  public Integer getDays() {
    return days;
  }

  public void setDays(Integer days) {
    this.days = days;
  }

  public Timestamp getSubmitTime() {
    return submitTime;
  }

  public void setSubmitTime(Timestamp submitTime) {
    this.submitTime = submitTime;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public Integer getStudentId() {
    return studentId;
  }

  public void setStudentId(Integer studentId) {
    this.studentId = studentId;
  }


  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

}
