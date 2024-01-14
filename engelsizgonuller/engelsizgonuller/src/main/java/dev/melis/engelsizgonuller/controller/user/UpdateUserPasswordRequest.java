package dev.melis.engelsizgonuller.controller.user;

import dev.melis.engelsizgonuller.services.profile.UpdatePasswordServiceRequest;
import dev.melis.engelsizgonuller.services.profile.UpdateUserProfileServiceRequest;

public record UpdateUserPasswordRequest(
        String newPassword,
        String oldPassword
) {
    UpdatePasswordServiceRequest toServiceRequest(){
        return new UpdatePasswordServiceRequest()
                .setNewPassword(newPassword)
                .setOldPassword(oldPassword);
    }
}
