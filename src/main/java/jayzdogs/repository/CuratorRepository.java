package jayzdogs.repository;

import jayzdogs.entity.Curator;
import jayzdogs.dto.CuratorWithDogsCount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author andrey
 * @date 17.01.19
 */

@Repository
public interface CuratorRepository extends JpaRepository<Curator, Long> {

    @Query(
            value = "select new jayzdogs.dto.CuratorWithDogsCount(c, count(d)) " +
                    "from Curator c " +
                    "left outer join c.dogs d " +
                    "group by c "
    )
    List<CuratorWithDogsCount> getAllWithDogsCount(Pageable pageable);

    @Query(
            value = "select new jayzdogs.dto.CuratorWithDogsCount(c, count(d)) " +
                    "from Curator c " +
                    "left outer join c.dogs d " +
                    "where c.id = :id " +
                    "group by c "
    )
    CuratorWithDogsCount getByIdWithDogsCount(@Param("id") Long id);

}
