package net.lzzy.accountingserver.service;

import net.lzzy.accountingserver.entities.Category;
import net.lzzy.accountingserver.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author hp
 */
@SpringBootTest
public class CategoryTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    void crate(){
        Category category = new Category();
        category.setCategoryName("餐饮");
        repository.save(category);
    }
}
