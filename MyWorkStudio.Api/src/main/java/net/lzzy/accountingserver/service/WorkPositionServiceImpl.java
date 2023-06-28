package net.lzzy.accountingserver.service;

import net.lzzy.accountingserver.entities.WorkPosition;
import net.lzzy.accountingserver.repositories.WorkPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author hp
 */
@Service
public class WorkPositionServiceImpl implements WorkPositionService {

    private final WorkPositionRepository workPositionRepository;

    @Autowired
    public WorkPositionServiceImpl(WorkPositionRepository workPositionRepository){
        this.workPositionRepository = workPositionRepository;
    }

//    @Override
//    public List<WorkPosition> get() {
//        return workPositionRepository.findAllByOrderByUpdateTimeDesc();
//    }
//
//    @Override
//    public List<WorkPosition> get(int page, int size) {
//        return workPositionRepository.findAllByOrderByUpdateTimeDesc(PageRequest.of(page, size));
//    }
//
//    @Override
//    public Integer count() {
//        return workPositionRepository.countAllBy();
//    }

    @Override
    public boolean isWorkNameOccupied(String workName) {
        return workPositionRepository.countByWorkName(workName) > 0;
    }

    @Override
    public WorkPosition add(WorkPosition workPosition) {
        return workPositionRepository.save(workPosition);
    }

    @Override
    public WorkPosition getById(UUID id) {
        return workPositionRepository.findById(id).orElse(null);
    }

}
