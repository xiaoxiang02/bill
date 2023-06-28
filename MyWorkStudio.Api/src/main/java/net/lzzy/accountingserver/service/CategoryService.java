package net.lzzy.accountingserver.service;

import net.lzzy.accountingserver.entities.Category;

import java.util.List;

/**
 * @author hp
 */
public interface CategoryService {

    Category add(Category category);

    Category getById(Integer id);

    boolean isCategoryNameOccupied(String categoryName);

    List<Category> get();
}
