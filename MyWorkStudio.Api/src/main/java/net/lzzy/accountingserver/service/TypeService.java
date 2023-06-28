package net.lzzy.accountingserver.service;

import net.lzzy.accountingserver.entities.Type;

import java.util.List;

/**
 * @author hp
 */
public interface TypeService {

    Type add(Type type);

    Type getById(Integer id);

    boolean isTypeNameOccupied(String typeName);

    Integer count();

    List<Type> get();
}
