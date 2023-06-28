package net.lzzy.accountingserver.web.controller;

import com.alibaba.fastjson.JSONObject;
import net.lzzy.accountingserver.constant.ApiConstant;
import net.lzzy.accountingserver.constant.Messages;
import net.lzzy.accountingserver.entities.Category;
import net.lzzy.accountingserver.service.CategoryService;
import net.lzzy.accountingserver.utils.StringUtils;
import net.lzzy.accountingserver.web.models.ApiResult;
import net.lzzy.accountingserver.web.models.VmCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hp
 */
@CrossOrigin
@RestController
@RequestMapping(ApiConstant.ROUTE_CATEGORY_ROOT)
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**查询所有账单用途类别*/
    @GetMapping(ApiConstant.ROUTE_CATEGORY_ALL)
    public ApiResult getCategories(){
        List<VmCategory> categories = new ArrayList<>();
        categoryService.get().forEach(bill -> categories.add(VmCategory.create(bill)));
        return new ApiResult(true, categories.size() + "", categories);
    }

    /**检查账单用途类别是否已存在(前端）*/
    @GetMapping(ApiConstant.ROUTE_CATEGORY_EXISTS)
    public ApiResult isCategoryExists(@PathVariable String category){
        boolean exists = categoryService.isCategoryNameOccupied(category);
        return new ApiResult(true, exists ? "账单类别已存在" : "", exists);
    }

    /**添加账单用途类别*/
    @PostMapping(ApiConstant.ROUTE_CATEGORY_ADD)
    public ApiResult add(@RequestBody JSONObject json) {
        String categoryName = json.getString(ApiConstant.KEY_CATEGORY_NAME);
        if (StringUtils.isEmpty(categoryName)){
            return new ApiResult(false, Messages.INCOMPLETE_INFO.toString(), null);
        }
        if (categoryService.isCategoryNameOccupied(categoryName)) {
            return new ApiResult(false, "账单用途类别已存在", null);
        }
        Category category = new Category();
        category.setCategoryName(categoryName);
        return new ApiResult(true, Messages.SUCCESS.toString(), VmCategory.create(categoryService.add(category)));
    }
}
