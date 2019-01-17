package jayzdogs.dto.converter;

import jayzdogs.dto.DogDto;
import jayzdogs.dto.NewDogDto;
import jayzdogs.entity.Curator;
import jayzdogs.entity.Dog;

/**
 * @author andrey
 * @date 17.01.19
 */

public final class DogConverter {

    public static DogDto toDto(Dog dog) {
        return DogDto.builder()
                .id(dog.getId())
                .name(dog.getName())
                .age(dog.getAge())
                .sex(dog.getSex())
                .breed(dog.getBreed())
                .build();
    }

    public static Dog toModel(DogDto dto) {
        return Dog.builder()
                .id(dto.getId())
                .age(dto.getAge())
                .breed(dto.getBreed())
                .name(dto.getName())
                .sex(dto.getSex())
                .build();
    }

    public static Dog toModel(DogDto dto, Curator curator) {
        return Dog.builder()
                .id(dto.getId())
                .age(dto.getAge())
                .breed(dto.getBreed())
                .name(dto.getName())
                .sex(dto.getSex())
                .curator(curator)
                .build();
    }

}
