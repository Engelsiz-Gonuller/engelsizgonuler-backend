package dev.melis.engelsizgonuller.support;

import lombok.Getter;

@Getter
public class ResponseMessage {
    private String message;

    public ResponseMessage setMessage(String message){
        this.message=message;
        return this;
    }
}
