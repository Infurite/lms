package com.temchik.lms.event.user.event;

import com.temchik.lms.model.user.User;

public class UserApprovedEvent extends UserEvent {

    public UserApprovedEvent(Object source, User user) {
        super(source, user);
    }
}
