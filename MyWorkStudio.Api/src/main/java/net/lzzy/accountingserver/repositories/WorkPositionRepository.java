package net.lzzy.accountingserver.repositories;

import net.lzzy.accountingserver.entities.WorkPosition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author hp
 */
public interface WorkPositionRepository extends JpaRepository<WorkPosition, UUID> {
    Integer countByWorkName(String workName);

//    List<WorkPosition> findAllByOrderByUpdateTimeDesc();
//    List<WorkPosition> findAllByOrderByUpdateTimeDesc(Pageable pageable);
//
//    Integer countAllBy();
}
