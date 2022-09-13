package com.temchik.lms.event.user.event;

import com.temchik.lms.model.user.UserToken;
import lombok.Getter;

@Getter
public class UserCreatedEvent extends UserEvent {

    private final String token;

    public UserCreatedEvent(Object source, UserToken userToken) {
        super(source, userToken.getUser());
        this.token = userToken.getToken();
    }
}
