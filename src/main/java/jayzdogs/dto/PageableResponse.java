package jayzdogs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Andrew Boytsov
 * @date 21.01.2019
 */

@Data
@AllArgsConstructor
public class PageableResponse<T> {

    private T data;
    private long count;

}
