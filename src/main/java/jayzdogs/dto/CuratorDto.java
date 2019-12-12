package jayzdogs.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author andrey
 * @date 17.01.19
 */

@Data
@Builder
public class CuratorDto {

    private Long id;
    private String name;
    private String lastname;
    private String phoneNumber;
    private int dogsLimit;
    private int dogsCount;

}
