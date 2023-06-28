package net.lzzy.accountingserver.service;

import net.lzzy.accountingserver.entities.WorkPosition;

import java.util.List;
import java.util.UUID;

/**
 * @author hp
 */
public interface WorkPositionService {
//
//    List<WorkPosition> get();
//
//    List<WorkPosition> get(int page, int size);
//
//    Integer count();

    boolean isWorkNameOccupied(String workName);

    WorkPosition add(WorkPosition workPosition);

    WorkPosition getById(UUID id);

}
