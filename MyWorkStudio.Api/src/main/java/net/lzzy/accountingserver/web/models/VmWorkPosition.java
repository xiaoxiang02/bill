package net.lzzy.accountingserver.web.models;

import lombok.Getter;
import lombok.Setter;
import net.lzzy.accountingserver.entities.WorkPosition;

import java.util.UUID;

/**
 * @author hp
 */
@Getter
@Setter
public class VmWorkPosition {
    private UUID id;
    private String workName;
    private int staffs;
    private String description;

    private VmWorkPosition(){};

    public static VmWorkPosition create(WorkPosition workPosition){
        VmWorkPosition vmWorkPosition = new VmWorkPosition();
        vmWorkPosition.setId(workPosition.getId());
        vmWorkPosition.setWorkName(workPosition.getWorkName());
        vmWorkPosition.setStaffs(workPosition.getStaffs());
        vmWorkPosition.setDescription(workPosition.getDescription());
        return vmWorkPosition;
    }
}
