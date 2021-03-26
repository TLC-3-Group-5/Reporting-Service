package io.turntabl.reportingservices.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Table
@Entity(name = "Notification")
public class Notification {
  
  @Id
  @SequenceGenerator(
    name = "notification_sequence",
    sequenceName = "notification_sequence",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "notification_sequence"
  )
  @Column(
    name = "id",
    nullable = false,
    updatable = false
  )
  private long id;

  @Column(
    name = "msg_type",
    nullable = false,
    updatable = false
  )
  private String msgType;

  @Column
  private String body;

  @Column(name = "actor_user_id")
  @ManyToOne
  @JoinColumn(name = "client_id")
  @JsonBackReference
  private long actorUserId;

  @Column(
    name = "should_notify"
  )
  private boolean shouldNotify;

  @Column(name = "recepient_user_id")
  @ManyToOne
  @JoinColumn(name = "client_id")
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
