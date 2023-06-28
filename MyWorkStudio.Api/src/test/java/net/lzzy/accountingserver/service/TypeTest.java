package net.lzzy.accountingserver.service;

import net.lzzy.accountingserver.entities.Type;
import net.lzzy.accountingserver.repositories.TypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author hp
 */
@SpringBootTest
public class TypeTest {

    @Autowired
    private TypeRepository repository;

    @Test
    void crate(){
        Type type = new Type();
        type.setTypeName("收入");
        repository.save(type);
    }
}
