package net.lzzy.accountingserver.web.controller;

import com.alibaba.fastjson.JSONObject;
import net.lzzy.accountingserver.constant.ApiConstant;
import net.lzzy.accountingserver.constant.Messages;
import net.lzzy.accountingserver.entities.Bill;
import net.lzzy.accountingserver.entities.Category;
import net.lzzy.accountingserver.entities.Type;
import net.lzzy.accountingserver.service.BillService;
import net.lzzy.accountingserver.service.CategoryService;
import net.lzzy.accountingserver.service.TypeService;
import net.lzzy.accountingserver.utils.StringUtils;
import net.lzzy.accountingserver.web.models.ApiResult;
import net.lzzy.accountingserver.web.models.VmBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hp
 */
@CrossOrigin
@RestController
@RequestMapping(ApiConstant.ROUTE_BILL_ROOT)
public class BillController {
    private final BillService billService;
    private final TypeService typeService;
    private final CategoryService categoryService;

    @Autowired
    public BillController(BillService billService, TypeService typeService, CategoryService categoryService) {
        this.billService = billService;
        this.typeService = typeService;
        this.categoryService = categoryService;
    }

    /**查询所有账单*/
    @GetMapping(ApiConstant.ROUTE_BILL_ALL)
    public ApiResult getBills(){
        List<VmBill> bills = new ArrayList<>();
        billService.get().forEach(bill -> bills.add(VmBill.create(bill)));
        return new ApiResult(true, bills.size() + "", bills);
    }

    /**添加或更改账单*/
    @PostMapping(ApiConstant.ROUTE_BILL_ADD)
    public ApiResult add(@RequestBody JSONObject json){
        return addOrUpdate(json, null);
    }
    @PostMapping(ApiConstant.ROUTE_BILL_UPDATE)
    public ApiResult update(@PathVariable Integer id, @RequestBody JSONObject json){
        return addOrUpdate(json, id);
    }
    public ApiResult addOrUpdate(@RequestBody JSONObject json, Integer id){
        Double money = json.getDouble(ApiConstant.KEY_BILL_MONEY);
        String notes = json.getString(ApiConstant.KEY_BILL_NOTES);
        String typeId = json.getString(ApiConstant.KEY_BILL_TYPE_ID);
        String categoryId = json.getString(ApiConstant.KEY_BILL_CATEGORY_ID);
        if (StringUtils.isEmpty(money.toString()) ||
            StringUtils.isEmpty(notes) ||
            StringUtils.isEmpty(typeId) ||
            StringUtils.isEmpty(categoryId)){
            return new ApiResult(false, Messages.INCOMPLETE_INFO.toString(), null);
        }
        Type type = typeService.getById(Integer.valueOf(typeId));
        Category category = categoryService.getById(Integer.valueOf(categoryId));
        if (type == null || category == null){
            return new ApiResult(false, Messages.WRONG_ID.toString(), null);
        }
        /**判断添加还是更新*/
        boolean create = id == null;
        Bill bill;
        if (create){
            bill = new Bill();
            bill.setType(type);
            bill.setCategory(category);
        }else {
            bill = billService.getById(id);
            if (bill == null){
                return new ApiResult(false, Messages.WRONG_ID.toString(), null);
            }
        }
        bill.setMoney(money);
        bill.setNotes(notes);
        if (create){
            return new ApiResult(true, "添加账单成功",  VmBill.create(billService.add(bill)));
        }else {
            Bill update = billService.update(bill);
            if (update == null){
                return new ApiResult(false, Messages.INCOMPLETE_INFO.toString(), false);
            }
            return new ApiResult(true, "账单更新成功", VmBill.create(billService.update(bill)));
        }
    }

    /**删除账单*/
    @PostMapping(ApiConstant.ROUTE_BILL_DELETE)
    public ApiResult delete(@RequestBody JSONObject json){
        try {
            String strBillId = json.getString(ApiConstant.KEY_BILL_ID);
            if (StringUtils.isEmpty(strBillId)){
                return new ApiResult(false, Messages.INCOMPLETE_INFO.toString(), null);
            }
            Integer billId = Integer.valueOf(strBillId);
            Messages msg = billService.delete(billId);
            return new ApiResult(msg == Messages.SUCCESS, msg.toString(), null);
        }catch (Exception e){
            return new ApiResult(false, Messages.WRONG_ID.toString(), e.getMessage());
        }
    }
}
