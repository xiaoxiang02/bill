package net.lzzy.accountingserver.web.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import net.lzzy.accountingserver.entities.Bill;

import java.util.Date;

/**
 * @author hp
 */
@Getter
@Setter
public class VmBill {
    private Integer id;
    private Double money;
    private String notes;
    private Integer typeId;
    private String typeName;
    private Integer categoryId;
    private String categoryName;
    @JsonFormat(timezone="GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(timezone="GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private VmBill(){};

    public static VmBill create(Bill bill){
        VmBill vmBill = new VmBill();
        vmBill.setId(bill.getId());
        vmBill.setMoney(bill.getMoney());
        vmBill.setNotes(bill.getNotes());
        vmBill.setCategoryId(bill.getCategory().getId());
        vmBill.setCategoryName(bill.getCategory().getCategoryName());
        vmBill.setTypeId(bill.getType().getId());
        vmBill.setTypeName(bill.getType().getTypeName());
        vmBill.setCreateTime(bill.getCreateTime());
        vmBill.setUpdateTime(bill.getUpdateTime());
        return vmBill;
    }
}

