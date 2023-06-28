package net.lzzy.accountingserver.service;

import net.lzzy.accountingserver.entities.Category;
import net.lzzy.accountingserver.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hp
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public boolean isCategoryNameOccupied(String categoryName) {
        return categoryRepository.countByCategoryName(categoryName) >0;
    }

    @Override
    public List<Category> get() {
        return categoryRepository.findAllByOrderByIdDesc();
    }
}
