package com.edureka.hotelreservationsystem.notification_service.service;

import java.time.LocalDateTime;
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

	public Notification sendNotification(Notification notification) {
		// Logic to send notification (email, SMS, etc.)
        // Example: Sending email, if notificationType is EMAIL

        // Here we would integrate with an email/SMS gateway
        boolean isSent = sendEmail(notification);

        if (isSent) {
            notification.setStatus("SENT");
        } else {
            notification.setStatus("FAILED");
        }
        
        notification.setSentAt(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    private boolean sendEmail(Notification notification) {
        // Email sending logic
        return true; // Simulating a successful email send
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
