package jayzdogs.dto.converter;

import jayzdogs.dto.CuratorDto;
import jayzdogs.entity.Curator;
import jayzdogs.dto.CuratorWithDogsCount;

/**
 * @author andrey
 * @date 17.01.19
 */

public final class CuratorConverter {

    public static Curator toModel(CuratorDto dto) {
        return Curator.builder()
                .id(dto.getId())
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .dogsLimit(dto.getDogsLimit())
                .build();
    }

    public static CuratorDto toDto(Curator curator, int dogsCount) {
        return CuratorDto.builder()
                .id(curator.getId())
                .name(curator.getName())
                .phoneNumber(curator.getPhoneNumber())
                .dogsLimit(curator.getDogsLimit())
                .dogsCount(dogsCount)
                .build();
    }

    public static CuratorDto toDto(CuratorWithDogsCount curatorWithDogsCount) {
        Curator curator = curatorWithDogsCount.getCurator();
        int dogsCount = curatorWithDogsCount.getCount();
        return CuratorDto.builder()
                .id(curator.getId())
                .name(curator.getName())
                .phoneNumber(curator.getPhoneNumber())
                .dogsLimit(curator.getDogsLimit())
                .dogsCount(dogsCount)
                .build();
    }

}
