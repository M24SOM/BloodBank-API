package so.edu.uct.BloodBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import so.edu.uct.BloodBank.model.Receipt;
import so.edu.uct.BloodBank.repository.ReceiptRepository;

import java.util.List;

@Service
public class ReceiptService {
    @Autowired
    ReceiptRepository receiptRepository;

    // 1. Get All Receipts

    public List<Receipt> getAllReceipts(){
        return receiptRepository.findAll();
    }

    // 2. Get Specific Receipt By ID

    public Receipt getReceiptById(Long id){
        return receiptRepository.findById(id).get();
    }

    // 3. Get Specific Receipt By phone

    public List<Receipt> getReceiptByPhone(String phone){
        return receiptRepository.findByMobileNo(phone);
    }

    // 4. Save Receipt

    public Receipt saveReceipt(Receipt donor){
        return receiptRepository.save(donor);
    }

    // 5. Delete Specific Receipt By ID

    public void deleteReceipt(Long id){
        receiptRepository.deleteById(id);
    }


    // 6. Get Sum of Receipt For Dashboard

    public Long sumOfReceipt(){
        return receiptRepository.sumOfReceipt();
    }
}
