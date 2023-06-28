package net.lzzy.accountingserver.repositories;

import net.lzzy.accountingserver.entities.Bill;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hp
 */
public interface BillRepository extends JpaRepository<Bill, Integer> {
    /**获取所有账单*/
    List<Bill> findAllByOrderByCreateTimeAsc();
    /**分页获取所有账单*/
    List<Bill> findAllByOrderByCreateTimeAsc(Pageable pageable);

    Integer countAllBy();


}
