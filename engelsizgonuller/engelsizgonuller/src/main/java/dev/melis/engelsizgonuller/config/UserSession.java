package dev.melis.engelsizgonuller.config;

import dev.melis.engelsizgonuller.services.model.user.User;
import dev.melis.engelsizgonuller.services.model.user.UserRole;

public record UserSession(
        long id,
        String username,
        UserRole role,
        User user
) {
}
