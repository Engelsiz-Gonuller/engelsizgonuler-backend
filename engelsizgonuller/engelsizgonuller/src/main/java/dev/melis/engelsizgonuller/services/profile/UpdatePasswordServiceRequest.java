package dev.melis.engelsizgonuller.services.profile;

import dev.melis.engelsizgonuller.support.result.UpdateResult;
import lombok.Getter;

@Getter
public class UpdatePasswordServiceRequest {

    private String newPassword;
    private String oldPassword;

    public UpdatePasswordServiceRequest setNewPassword(String password){
        this.newPassword=password;
        return this;
    }
    public UpdatePasswordServiceRequest setOldPassword(String password){
        this.oldPassword=password;
        return this;
    }
}
