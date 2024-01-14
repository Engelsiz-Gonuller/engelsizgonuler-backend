package dev.melis.engelsizgonuller.controller.session;

import dev.melis.engelsizgonuller.services.model.user.UserRole;

public record SessionDetails(
        long id,
        String email,
        String  role
) {
    SessionDetails(long id, String email, UserRole role){
        this(id,email, role.name());
    }
}
