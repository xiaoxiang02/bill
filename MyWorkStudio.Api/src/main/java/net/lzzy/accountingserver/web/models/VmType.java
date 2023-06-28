package net.lzzy.accountingserver.web.models;

import lombok.Getter;
import lombok.Setter;
import net.lzzy.accountingserver.entities.Type;

/**
 * @author hp
 */
@Getter
@Setter
public class VmType {
    private Integer id;
    private String typeName;

    private VmType(){}

    public static VmType create(Type type){
        VmType vmType = new VmType();
        vmType.setId(type.getId());
        vmType.setTypeName(type.getTypeName());
        return  vmType;
    }
}
