package dev.melis.engelsizgonuller.support.resulthandler;

import dev.melis.engelsizgonuller.support.ResponseMessage;
import dev.melis.engelsizgonuller.support.result.OperationFailureReason;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BusinessResultHandler {

    public static ResponseEntity<?> handleFailureReason(OperationFailureReason reason, String message){
        return switch (reason){
            case NOT_FOUND -> new ResponseEntity<>(new ResponseMessage().setMessage(message),HttpStatus.NOT_FOUND);
            case CONFLICT -> new ResponseEntity<>( new ResponseMessage().setMessage(message),HttpStatus.CONFLICT);
            case UNAUTHORIZED -> new ResponseEntity<>(new ResponseMessage().setMessage(message),HttpStatus.UNAUTHORIZED);
            case PRECONDITION_FAILED -> new ResponseEntity<>(new ResponseMessage().setMessage(message),HttpStatus.PRECONDITION_FAILED);
            case ALREADY_REPORTED -> new ResponseEntity<>(new ResponseMessage().setMessage(message),HttpStatus.ALREADY_REPORTED);
            default -> new ResponseEntity<>(new ResponseMessage().setMessage(message),HttpStatus.INTERNAL_SERVER_ERROR);
        };
    }
}
