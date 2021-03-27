package io.turntabl.reportingservices.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "Notification")
@Table(name = "Notifications")
public class Notification {

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  @Column(name = "id", nullable = false, updatable = false)
  private long id;

  @Column(name = "msg_type", nullable = false, updatable = false, length = 50)
  private String msgType;

  @Column(length = 500)
  private String body;

  @ManyToOne
  @JoinColumn(name = "client_id", insertable = false, updatable = false)
  @JsonBackReference
  private long actorUserId;

  @Column(name = "should_notify")
  private boolean shouldNotify;

  @ManyToOne
  @JoinColumn(name = "client_id", insertable = false, updatable = false)
  @JsonBackReference
  private long receipientUserId;

  @Column(name = "datetime")
  private LocalDateTime datetime;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getMsgType() {
    return msgType;
  }

  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public long getActorUserId() {
    return actorUserId;
  }

  public void setActorUserId(long actorUserId) {
    this.actorUserId = actorUserId;
  }

  public boolean isShouldNotify() {
    return shouldNotify;
  }

  public void setShouldNotify(boolean shouldNotify) {
    this.shouldNotify = shouldNotify;
  }

  public long getReceipientUserId() {
    return receipientUserId;
  }

  public void setReceipientUserId(long receipientUserId) {
    this.receipientUserId = receipientUserId;
  }

  public LocalDateTime getDatetime() {
    return datetime;
  }

  public void setDatetime(LocalDateTime datetime) {
    this.datetime = datetime;
  }

}
