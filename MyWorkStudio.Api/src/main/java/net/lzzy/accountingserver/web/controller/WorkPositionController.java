package net.lzzy.accountingserver.web.controller;

import com.alibaba.fastjson.JSONObject;
import net.lzzy.accountingserver.constant.ApiConstant;
import net.lzzy.accountingserver.constant.Messages;
import net.lzzy.accountingserver.entities.WorkPosition;
import net.lzzy.accountingserver.service.WorkPositionService;
import net.lzzy.accountingserver.utils.StringUtils;
import net.lzzy.accountingserver.web.models.ApiResult;
import net.lzzy.accountingserver.web.models.VmWorkPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hp
 */
@CrossOrigin
@RestController
@RequestMapping(ApiConstant.ROUTE_WORK_POSITION_ROOT)
public class WorkPositionController {
    private final WorkPositionService workPositionService;

    @Autowired
    public WorkPositionController(WorkPositionService workPositionService) {
        this.workPositionService = workPositionService;
    }

    /**检查岗位是否存在(前端）*/
    @GetMapping(ApiConstant.ROUTE_WORK_POSITION_EXISTS)
    public ApiResult isWokePositionExists(@PathVariable String workPosition){
        boolean exists = workPositionService.isWorkNameOccupied(workPosition);
        return new ApiResult(true, exists ? "岗位已存在" : "", exists);
    }

    /**添加岗位*/
    @PostMapping(ApiConstant.ROUTE_WORK_POSITION_ADD)
    public ApiResult add(@RequestBody JSONObject json) {
        String workName = json.getString(ApiConstant.KEY_WORK_POSITION_WORK_NAME);
        String description = json.getString(ApiConstant.KEY_WORK_POSITION_DESCRIPTION);
        if (StringUtils.isEmpty(workName) || StringUtils.isEmpty(description)){
            return new ApiResult(false, Messages.INCOMPLETE_INFO.toString(), null);
        }
        if (workPositionService.isWorkNameOccupied(workName)) {
            return new ApiResult(false, "岗位已存在", null);
        }
        WorkPosition workPosition = new WorkPosition();
        workPosition.setWorkName(workName);
        workPosition.setStaffs(0);
        workPosition.setDescription(description);
        return new ApiResult(true, Messages.SUCCESS.toString(), VmWorkPosition.create(workPositionService.add(workPosition)));
    }
}
