package com.temchik.lms.service.notification;

import com.temchik.lms.common.exception.BusinessException;
import com.temchik.lms.event.user.event.UserApprovedEvent;
import com.temchik.lms.event.user.event.UserCreatedEvent;
import com.temchik.lms.event.user.event.UserEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final EmailService emailService;

    public void send(UserEvent event) {
        if (event instanceof UserCreatedEvent) {
            UserCreatedEvent createdEvent = (UserCreatedEvent) event;
            emailService.send(createdEvent.getEmail(), "Please approve email with token ".concat(createdEvent.getToken()));
        } else if (event instanceof UserApprovedEvent) {
            UserApprovedEvent approvedEvent = (UserApprovedEvent) event;
            emailService.send(approvedEvent.getEmail(), "Thanks for approve your email! Enjoy your life");
        } else {
            throw new BusinessException("Incorrect event!");
        }
    }
}
