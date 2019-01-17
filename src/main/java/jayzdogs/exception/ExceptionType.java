package jayzdogs.exception;

/**
 * @author andrey
 * @date 17.01.19
 */

public enum ExceptionType {

    DOG_LIMIT(1, "Curator can't take more dogs");

    private int code;
    private String message;

    ExceptionType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
