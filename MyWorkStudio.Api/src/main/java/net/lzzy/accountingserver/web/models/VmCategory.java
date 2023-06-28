package net.lzzy.accountingserver.web.models;

import lombok.Getter;
import lombok.Setter;
import net.lzzy.accountingserver.entities.Category;

/**
 * @author hp
 */
@Getter
@Setter
public class VmCategory {
    private Integer id;
    private String categoryName;

    private VmCategory(){};

    public static VmCategory create(Category category){
        VmCategory vmCategory = new VmCategory();
        vmCategory.setId(category.getId());
        vmCategory.setCategoryName(category.getCategoryName());
        return vmCategory;
    }
}
