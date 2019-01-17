package jayzdogs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author andrey
 * @date 17.01.19
 */

@Getter
public class BaseException extends Exception {

    private int code;
    private String message;

    public BaseException(ExceptionType type) {
        this.code = type.getCode();
        this.message = type.getMessage();
    }

}
