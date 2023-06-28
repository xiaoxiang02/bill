package net.lzzy.accountingserver.service;

import net.lzzy.accountingserver.entities.WorkPosition;
import net.lzzy.accountingserver.repositories.WorkPositionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author hp
 */
@SpringBootTest
public class WorkPositionTest {

    @Autowired
    private WorkPositionRepository repository;


    @Test
    void create(){
        WorkPosition workPosition = new WorkPosition();
        workPosition.setWorkName("统计员");
        workPosition.setStaffs(0);
        workPosition.setDescription("负责统计所录入数据信息");
        repository.save(workPosition);
    }
}
