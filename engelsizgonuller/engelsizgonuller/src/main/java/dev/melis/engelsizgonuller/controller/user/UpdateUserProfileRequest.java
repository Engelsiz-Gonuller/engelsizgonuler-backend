package dev.melis.engelsizgonuller.controller.user;

import dev.melis.engelsizgonuller.services.profile.UpdateUserProfileServiceRequest;

import java.time.LocalDate;

public record UpdateUserProfileRequest(
        String name,
        String surname,
        String about,
        LocalDate birthday,
        String job
) {
    UpdateUserProfileServiceRequest toServiceRequest(){
        return new UpdateUserProfileServiceRequest()
                .setAboutUser(about)
                .setUserJob(job)
                .setBirthday(birthday)
                .setUsername(name)
                .setUserSurname(surname);
    }
}
