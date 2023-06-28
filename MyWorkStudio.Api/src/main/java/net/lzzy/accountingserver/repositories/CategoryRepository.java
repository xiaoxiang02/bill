package net.lzzy.accountingserver.repositories;

import net.lzzy.accountingserver.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

/**
 * @author hp
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Integer countByCategoryName(String categoryName);

    List<Category> findAllByOrderByIdDesc();
}
