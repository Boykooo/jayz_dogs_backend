package jayzdogs.service;

import jayzdogs.dto.CuratorWithDogsCount;
import jayzdogs.dto.DogDto;
import jayzdogs.dto.NewDogDto;
import jayzdogs.dto.PageableResponse;
import jayzdogs.dto.converter.DogConverter;
import jayzdogs.entity.Curator;
import jayzdogs.entity.Dog;
import jayzdogs.exception.DogLimitException;
import jayzdogs.repository.DogRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author andrey
 * @date 17.01.19
 */

@Service
public class DogService {

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private CuratorService curatorService;

    public PageableResponse getAll(Long curatorId, int page, int size, String sortField, String sortDirection) {
        Sort sort = Strings.isNotEmpty(sortField) && Strings.isNotEmpty(sortDirection)
                ? Sort.by(Sort.Direction.fromString(sortDirection), sortField)
                : Sort.unsorted();
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        List<Dog> all = dogRepository.findAllByCurator(new Curator(curatorId), pageRequest);
        List<DogDto> dogs = all.stream().map(DogConverter::toDto).collect(Collectors.toList());
        return new PageableResponse(dogs, dogRepository.countByCurator(new Curator(curatorId)));
    }

    public DogDto getById(Long id) {
        Dog dog = dogRepository.findById(id).orElse(null);
        return DogConverter.toDto(dog);
    }

    public DogDto create(NewDogDto newDogDto) throws DogLimitException {
        CuratorWithDogsCount curator = curatorService.getByIdWithDogsCount(newDogDto.getCuratorId());
        if (curator == null || !curator.canTakeDog()) {
            throw new DogLimitException();
        }
        Dog dog = DogConverter.toModel(newDogDto, curator.getCurator());
        dog = dogRepository.save(dog);
        return DogConverter.toDto(dog);
    }

    public DogDto update(NewDogDto dogDto) {
        Dog dog = DogConverter.toModel(dogDto);
        dog.setCurator(new Curator(dogDto.getCuratorId()));
        dog = dogRepository.save(dog);
        return DogConverter.toDto(dog);
    }

    public void delete(Long id) {
        dogRepository.deleteById(id);
    }

}
