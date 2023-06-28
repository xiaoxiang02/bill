package net.lzzy.accountingserver.service;

import net.lzzy.accountingserver.entities.Type;
import net.lzzy.accountingserver.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hp
 */
@Service
public class TypeServiceImpl implements TypeService{

    private final TypeRepository typeRepository;

    @Autowired
    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public Type add(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Type getById(Integer id) {
        return typeRepository.findById(id).orElse(null);
    }

    @Override
    public boolean isTypeNameOccupied(String typeName) {
        return typeRepository.countByTypeName(typeName) > 0;
    }

    @Override
    public Integer count() {
        return typeRepository.countAllBy();
    }

    @Override
    public List<Type> get() {
        return typeRepository.findAllByOrderByIdDesc();
    }
}
