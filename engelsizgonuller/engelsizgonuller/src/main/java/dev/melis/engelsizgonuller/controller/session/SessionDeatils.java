package dev.melis.engelsizgonuller.controller.session;

import dev.melis.engelsizgonuller.services.model.user.UserRole;

public record SessionDeatils(
        long id,
        String email,
        String  role
) {
    SessionDeatils(long id, String email, UserRole role){
        this(id,email, role.name());
    }
}
