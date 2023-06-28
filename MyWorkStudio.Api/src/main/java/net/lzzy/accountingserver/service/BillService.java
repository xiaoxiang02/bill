package net.lzzy.accountingserver.service;

import net.lzzy.accountingserver.constant.Messages;
import net.lzzy.accountingserver.entities.Bill;

import java.util.List;

/**
 * @author hp
 */
public interface BillService {
    List<Bill> get();

    List<Bill> get(int page, int size);

    Bill add(Bill bill);

    Bill update(Bill bill);

    Messages delete(Integer id);

    Bill getById(Integer id);

    Integer count();

}
