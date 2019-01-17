package jayzdogs.repository;

import jayzdogs.entity.Curator;
import jayzdogs.entity.Dog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author andrey
 * @date 17.01.19
 */

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {

    List<Dog> findAllByCurator(Curator curator, Pageable pageable);

}
