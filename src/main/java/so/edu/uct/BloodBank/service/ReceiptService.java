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

    public List<Receipt> getAllReceipts(){
        return receiptRepository.findAll();
    }

    public Receipt getReceiptById(Long id){
        return receiptRepository.findById(id).get();
    }

    public List<Receipt> getReceiptByPhone(String phone){
        return receiptRepository.findByMobileNo(phone);
    }

    public Receipt saveReceipt(Receipt donor){
        return receiptRepository.save(donor);
    }

    public void deleteReceipt(Long id){
        receiptRepository.deleteById(id);
    }
}
