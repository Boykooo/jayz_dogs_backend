package jayzdogs.exception;

/**
 * @author andrey
 * @date 17.01.19
 */

public class DogLimitException extends BaseException {

    public DogLimitException() {
        super(ExceptionType.DOG_LIMIT);
    }

}
