package net.lzzy.accountingserver.repositories;

import net.lzzy.accountingserver.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hp
 */
public interface TypeRepository extends JpaRepository<Type, Integer> {

    Integer countByTypeName(String typeName);

    Integer countAllBy();

    List<Type> findAllByOrderByIdDesc();
}
