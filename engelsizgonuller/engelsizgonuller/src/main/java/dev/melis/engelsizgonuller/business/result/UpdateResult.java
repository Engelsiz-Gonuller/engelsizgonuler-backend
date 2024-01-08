package dev.melis.engelsizgonuller.business.result;

public class UpdateResult {

    private OperationResult result;
    private OperationFailureReason reason;

    private String message;

    private UpdateResult() {
    }

    public String getMessage() {
        return message;
    }

    public OperationResult getResult() {
        return result;
    }

    public OperationFailureReason getReason() {
        return reason;
    }

    public UpdateResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public UpdateResult setResult(OperationResult result) {
        this.result = result;
        return this;
    }

    public UpdateResult setReason(OperationFailureReason reason) {
        this.reason = reason;
        return this;
    }

    public static UpdateResult success() {
        return new UpdateResult()
                .setResult(OperationResult.SUCCESS);
    }

    public static UpdateResult success(String message) {
        return new UpdateResult()
                .setResult(OperationResult.SUCCESS)
                .setMessage(message);
    }

    public static UpdateResult failure(OperationFailureReason reason){
        return new UpdateResult()
                .setReason(reason);

    }
    public static UpdateResult failure(OperationFailureReason reason,String message){
        return new UpdateResult()
                .setReason(reason)
                .setMessage(message);

    }
}
