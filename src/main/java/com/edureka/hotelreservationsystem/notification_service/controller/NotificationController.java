package com.edureka.hotelreservationsystem.notification_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edureka.hotelreservationsystem.notification_service.entity.Notification;
import com.edureka.hotelreservationsystem.notification_service.service.NotificationService;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@PostMapping("/send")
	public ResponseEntity<Notification> makeNotification(@RequestBody Notification notification) {
		return ResponseEntity.ok(this.notificationService.sendNotification(notification));
	}

	// Retrieve all notifications
	@GetMapping
	public ResponseEntity<List<Notification>> getAllNotifications() {
		List<Notification> notifications = notificationService.getAllNotifications();
		return new ResponseEntity<>(notifications, HttpStatus.OK);
	}

	// Get a Notification by ID
	@GetMapping("/{id}")
	public ResponseEntity<Notification> getNotificationById(@PathVariable Long id) {
		Notification notification = notificationService.getNotificationById(id);
		if (notification != null) {
			return new ResponseEntity<>(notification, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Update a Notification
	@PutMapping("/{id}")
	public ResponseEntity<Notification> updateNotification(@PathVariable Long id,
			@RequestBody Notification notification) {
		Notification updatedNotification = notificationService.updateNotification(id, notification);
		if (updatedNotification != null) {
			return new ResponseEntity<>(updatedNotification, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Delete a Notification
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
		boolean deleted = notificationService.deleteNotification(id);
		if (deleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
