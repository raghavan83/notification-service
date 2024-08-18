package com.edureka.hotelreservationsystem.notification_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edureka.hotelreservationsystem.notification_service.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>{

}
