package net.lzzy.accountingserver.web.controller;

import com.alibaba.fastjson.JSONObject;
import net.lzzy.accountingserver.constant.ApiConstant;
import net.lzzy.accountingserver.constant.Messages;
import net.lzzy.accountingserver.entities.Type;
import net.lzzy.accountingserver.service.TypeService;
import net.lzzy.accountingserver.utils.StringUtils;
import net.lzzy.accountingserver.web.models.ApiResult;
import net.lzzy.accountingserver.web.models.VmType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hp
 */
@CrossOrigin
@RestController
@RequestMapping(ApiConstant.ROUTE_TYPE_ROOT)
public class TypeController {
    private final TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    /**查询所有账单类型*/
    @GetMapping(ApiConstant.ROUTE_TYPE_ALL)
    public ApiResult getTypes(){
        List<VmType> types = new ArrayList<>();
        typeService.get().forEach(bill -> types.add(VmType.create(bill)));
        return new ApiResult(true, types.size() + "", types);
    }
    /**检查账单类型是否已存在(前端）*/
    @GetMapping(ApiConstant.ROUTE_TYPE_EXISTS)
    public ApiResult isCategoryExists(@PathVariable String type){
        boolean exists = typeService.isTypeNameOccupied(type);
        return new ApiResult(true, exists ? "账单类型已存在" : "", exists);
    }

    /**添加账单类型*/
    @PostMapping(ApiConstant.ROUTE_TYPE_ADD)
    public ApiResult add(@RequestBody JSONObject json) {
        String typeName = json.getString(ApiConstant.KEY_TYPE_NAME);
        if (StringUtils.isEmpty(typeName)){
            return new ApiResult(false, Messages.INCOMPLETE_INFO.toString(), null);
        }
        if (typeService.isTypeNameOccupied(typeName)) {
            return new ApiResult(false, "账单类型已存在", null);
        }
        Type type = new Type();
        type.setTypeName(typeName);
        return new ApiResult(true, Messages.SUCCESS.toString(), VmType.create(typeService.add(type)));
    }
}
