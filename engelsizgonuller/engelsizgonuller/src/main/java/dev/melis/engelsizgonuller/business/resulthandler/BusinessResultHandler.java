package dev.melis.engelsizgonuller.business.resulthandler;

import dev.melis.engelsizgonuller.business.ResponseMessage;
import dev.melis.engelsizgonuller.business.result.OperationFailureReason;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BusinessResultHandler {

    public static ResponseEntity<?> handleFailureReason(OperationFailureReason reason, String message){
        return switch (reason){
            case NOT_FOUND -> new ResponseEntity<>(new ResponseMessage().setMessage(message),HttpStatus.NOT_FOUND);
            case CONFLICT -> new ResponseEntity<>( new ResponseMessage().setMessage(message),HttpStatus.CONFLICT);
            case UNAUTHORIZED -> new ResponseEntity<>(new ResponseMessage().setMessage(message),HttpStatus.UNAUTHORIZED);
            case PRECONDITION_FAILED -> new ResponseEntity<>(new ResponseMessage().setMessage(message),HttpStatus.PRECONDITION_FAILED);
            default -> new ResponseEntity<>(new ResponseMessage().setMessage(message),HttpStatus.INTERNAL_SERVER_ERROR);
        };
    }
}
