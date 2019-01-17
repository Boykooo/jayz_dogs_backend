package jayzdogs.dto;

import jayzdogs.entity.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author andrey
 * @date 17.01.19
 */

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DogDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Sex sex;

    @NotNull
    private int age;

    @NotNull
    private String breed;

}
