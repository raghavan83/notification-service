package com.edureka.hotelreservationsystem.notification_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edureka.hotelreservationsystem.notification_service.entity.Notification;
import com.edureka.hotelreservationsystem.notification_service.repository.NotificationRepository;

@Service
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	public List<Notification> getAllNotifications() {
		return notificationRepository.findAll();
	}

	public Notification getNotificationById(Long id) {
		return notificationRepository.findById(id).orElse(null);
	}

	public Notification addNotification(Notification notification) {
		return notificationRepository.save(notification);
	}

	public Notification updateNotification(Long id, Notification notification) {
		if (notificationRepository.existsById(id)) {
			notification.setId(id); // Ensure the correct ID is set for update
			return notificationRepository.save(notification);
		} else {
			return null;
		}
	}

	public boolean deleteNotification(Long id) {
		if (notificationRepository.existsById(id)) {
			notificationRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
