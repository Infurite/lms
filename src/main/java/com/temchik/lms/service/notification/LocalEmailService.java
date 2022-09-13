package com.temchik.lms.service.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LocalEmailService implements EmailService {

    @Override
    public void send(String email, String message) {
        log.info("Email message '".concat(message).concat("' was sent to ").concat(email));
    }
}
