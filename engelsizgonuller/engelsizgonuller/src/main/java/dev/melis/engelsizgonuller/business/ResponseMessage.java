package dev.melis.engelsizgonuller.business;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ResponseMessage {
    private String message;

    public ResponseMessage setMessage(String message){
        this.message=message;
        return this;
    }
}
