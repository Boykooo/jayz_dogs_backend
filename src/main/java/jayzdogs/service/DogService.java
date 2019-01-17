package jayzdogs.service;

import jayzdogs.dto.CuratorWithDogsCount;
import jayzdogs.dto.DogDto;
import jayzdogs.dto.NewDogDto;
import jayzdogs.dto.converter.DogConverter;
import jayzdogs.entity.Curator;
import jayzdogs.entity.Dog;
import jayzdogs.exception.DogLimitException;
import jayzdogs.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    public List<DogDto> getAll(Long curatorId, int page, int size) {
        List<Dog> dogs = dogRepository.findAllByCurator(new Curator(curatorId), PageRequest.of(page, size));
        return dogs.stream().map(DogConverter::toDto).collect(Collectors.toList());
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

    public DogDto update(DogDto dogDto) {
        Dog dog = DogConverter.toModel(dogDto);
        dog = dogRepository.save(dog);
        return DogConverter.toDto(dog);
    }

    public void delete(Long id) {
        dogRepository.deleteById(id);
    }

}
