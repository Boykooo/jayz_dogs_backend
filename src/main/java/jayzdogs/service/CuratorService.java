package jayzdogs.service;

import jayzdogs.dto.CuratorDto;
import jayzdogs.dto.CuratorWithDogsCount;
import jayzdogs.dto.PageableResponse;
import jayzdogs.dto.converter.CuratorConverter;
import jayzdogs.entity.Curator;
import jayzdogs.repository.CuratorRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public PageableResponse getAll(int page, int size, String sortField, String sortDirection) {
        Sort sort = Strings.isNotEmpty(sortField) && Strings.isNotEmpty(sortDirection)
                ? Sort.by(Sort.Direction.fromString(sortDirection), sortField)
                : Sort.unsorted();
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        List<CuratorWithDogsCount> all = curatorRepository.getAllWithDogsCount(pageRequest);
        List<CuratorDto> curators = all.stream().map(CuratorConverter::toDto).collect(Collectors.toList());
        return new PageableResponse(curators, curatorRepository.count());
    }

    public Curator getById(Long id) {
        Optional<Curator> curator = curatorRepository.findById(id);
        return curator.get();
    }

    public CuratorWithDogsCount getByIdWithDogsCount(Long curatorId) {
        return curatorRepository.getByIdWithDogsCount(curatorId);
    }

    public void delete(Long id) {
        this.curatorRepository.deleteById(id);
    }

}
