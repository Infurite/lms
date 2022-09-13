package com.temchik.lms.event.user.event;

import com.temchik.lms.model.user.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

@Getter
public abstract class UserEvent extends ApplicationEvent {

    private final UUID userId;
    private final String email;

    public UserEvent(Object source, User user) {
        super(source);
        this.userId = user.getId();
        this.email = user.getEmail();
    }
}
