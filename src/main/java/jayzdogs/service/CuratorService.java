package jayzdogs.service;

import jayzdogs.dto.CuratorDto;
import jayzdogs.dto.converter.CuratorConverter;
import jayzdogs.entity.Curator;
import jayzdogs.dto.CuratorWithDogsCount;
import jayzdogs.repository.CuratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author andrey
 * @date 17.01.19
 */

@Service
public class CuratorService {

    @Autowired
    private CuratorRepository curatorRepository;

    public CuratorDto createOrUpdate(CuratorDto dto) {
        Curator curator = CuratorConverter.toModel(dto);
        curator = curatorRepository.save(curator);
        return CuratorConverter.toDto(curator, 0);
    }

    public List<CuratorDto> getAll(int page, int size) {
        List<CuratorWithDogsCount> all = curatorRepository.getAllWithDogsCount(PageRequest.of(page, size));
        return all.stream().map(CuratorConverter::toDto).collect(Collectors.toList());
    }

    public Curator getById(Long id) {
        Optional<Curator> curator = curatorRepository.findById(id);
        return curator.get();
    }

    public CuratorWithDogsCount getByIdWithDogsCount(Long curatorId) {
        return curatorRepository.getByIdWithDogsCount(curatorId);
    }

}
