package io.turntabl.reportingservices.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Table
@Entity(name = "Client")
public class Client {

  @Id
  @SequenceGenerator(name = "client_sequence", sequenceName = "client_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
  @Column(nullable = false, updatable = false)
  private Long id;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  @Column(nullable = false)
  private String name;

  private Double balance;

  @OneToMany(mappedBy = "receipient")
  @JsonManagedReference
  private List<Notification> notifications;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  @Override
  public String toString() {
    return "Client{" + "id=" + id + ", email='" + email + '\'' + ", name='" + name + '\'' + '}';
  }

  public List<Notification> getNotifications() {
    return notifications;
  }

  public void setNotifications(List<Notification> notifications) {
    this.notifications = notifications;
  }
}
