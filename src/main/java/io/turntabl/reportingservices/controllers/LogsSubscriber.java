package io.turntabl.reportingservices.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import io.turntabl.reportingservices.models.Notification;
import io.turntabl.reportingservices.services.NotificationService;

@Service
public class LogsSubscriber implements MessageListener {

  @Autowired
  private NotificationService notificationService;

  @Override
  public void onMessage(Message message, byte[] pattern) {
    try {
      ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
          false);
      Notification notification = objectMapper.readValue(message.toString(), Notification.class);

      System.out.println("NEW NOTIFICATION");
      System.out.println(notification);
      
      notificationService.createNotification(notification);
    } catch (JsonProcessingException e) {
      
      e.printStackTrace();
    }
  }

}
