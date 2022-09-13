package com.temchik.lms.event.user.listener;

import com.temchik.lms.event.user.event.UserApprovedEvent;
import com.temchik.lms.event.user.event.UserCreatedEvent;
import com.temchik.lms.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Async
@Component
@RequiredArgsConstructor
public class UserEventListener {

    private final NotificationService service;

    @EventListener
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        service.send(event);
    }

    @EventListener
    public void handleUserApprovedEvent(UserApprovedEvent event) {
        service.send(event);
    }
}
