package io.turntabl.reportingservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.turntabl.reportingservices.models.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {}
