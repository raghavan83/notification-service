package com.edureka.hotelreservationsystem.notification_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.edureka.hotelreservationsystem.customerservice.dto.EventMessage;
import com.edureka.hotelreservationsystem.notification_service.dto.Customer;
import com.edureka.hotelreservationsystem.notification_service.entity.Notification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EventListener {
	
	@Autowired
	private NotificationService notificationService;

	 private static final Logger logger = LoggerFactory.getLogger(EventListener.class);

	    /**
	     * Listens to messages from the Kafka topic and processes them.
	     * 
	     * @param eventMessage The deserialized EventMessage object
	     */
	    @KafkaListener(topics = "customer-topic", groupId = "notification-service-group-id")
	    public void listen(@Payload EventMessage eventMessage) {
	        // Log the received event message
	        logger.info("Received event: {}", eventMessage);

	        // Process the event message
	        handleEvent(eventMessage);
	    }

	    /**
	     * Processes the received EventMessage.
	     * 
	     * @param eventMessage The EventMessage to process
	     */
	    private void handleEvent(EventMessage eventMessage) {
	    	
	    	Notification notification = new Notification();
	    	notification.setNotificationType("EMAIL");
	    	if(eventMessage.getEventType().equalsIgnoreCase("UserRegistered")){
	    		notification.setMessage("USER_REGISTERED");
	    	}else {
	    		notification.setMessage("USER_UPDATED");
	    	}
	    	
	    	ObjectMapper objectMapper = new ObjectMapper();
	    	
	    	Customer customer = null;
			try {
				customer = objectMapper.readValue(eventMessage.getEventMessage(), Customer.class);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	notification.setCustomerId(customer.getId());
	    	
	    	this.notificationService.sendNotification(notification);
	    }

}
