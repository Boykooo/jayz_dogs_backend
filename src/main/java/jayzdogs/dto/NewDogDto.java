package jayzdogs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author andrey
 * @date 17.01.19
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewDogDto extends DogDto {

    @NotNull
    private Long curatorId;

}
