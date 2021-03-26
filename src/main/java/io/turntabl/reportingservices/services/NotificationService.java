package io.turntabl.reportingservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.turntabl.reportingservices.models.Notification;
import io.turntabl.reportingservices.repositories.NotificationRepository;

@Service
public class NotificationService {
  
  private final NotificationRepository notificationRepository;

  @Autowired
  public NotificationService(NotificationRepository nr) {
    notificationRepository = nr;
  }

  public void createNotification(Notification n) {
    notificationRepository.save(n);
  }

}
