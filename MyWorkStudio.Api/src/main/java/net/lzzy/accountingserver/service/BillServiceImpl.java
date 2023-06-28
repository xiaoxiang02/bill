package net.lzzy.accountingserver.service;

import net.lzzy.accountingserver.constant.Messages;
import net.lzzy.accountingserver.entities.Bill;
import net.lzzy.accountingserver.repositories.BillRepository;
import net.lzzy.accountingserver.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author hp
 */
@Service
public class BillServiceImpl implements BillService{

    private final BillRepository billRepository;

    @Autowired
    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public List<Bill> get() {
        return billRepository.findAllByOrderByCreateTimeAsc();
    }

    @Override
    public List<Bill> get(int page, int size) {
        return billRepository.findAllByOrderByCreateTimeAsc(PageRequest.of(page,size));
    }

    @Override
    public Bill getById(Integer id) {
        return billRepository.findById(id).orElse(null);
    }

    @Override
    public Bill add(Bill bill) {
        return billRepository.save(bill);
    }


    @Override
    public Bill update(Bill bill) {
        bill.setUpdateTime(new Date());
        return billRepository.save(bill);
    }

    @Override
    public Messages delete(Integer id) {
        Bill bill = billRepository.findById(id).orElse(null);
        if (bill == null){
            return Messages.WRONG_ID;
        }
        billRepository.delete(bill);
        return Messages.SUCCESS;
    }

    @Override
    public Integer count() {
        return billRepository.countAllBy();
    }
}
