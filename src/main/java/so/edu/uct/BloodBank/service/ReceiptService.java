package so.edu.uct.BloodBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import so.edu.uct.BloodBank.model.Donor;
import so.edu.uct.BloodBank.model.Receipt;
import so.edu.uct.BloodBank.repository.ReceiptRepository;

import java.util.List;
import java.util.Optional;

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
        Optional<Receipt> getReceiptById = receiptRepository.findById(id);
        if (getReceiptById.isEmpty()){
            return null;
        }
        return receiptRepository.findById(id).get();
    }

    // 3. Get Specific Receipt By phone

    public List<Receipt> getReceiptByPhone(String phone){
        List<Receipt> getDonorByMobileNo = receiptRepository.findByMobileNo(phone);
        if (getDonorByMobileNo.isEmpty()){
            return null;
        }
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



    // 7. Get Specific Receipt By Blood Type

    public List<Receipt> getReceiptByBloodType(String bloodType){
        return receiptRepository.findByBloodType(bloodType);
    }


    // 8. Get Specific Receipt By State

    public List<Receipt> getReceiptByState(String state){
        return receiptRepository.findByState(state);
    }

    // 8. Get Specific Receipt By Hospital

    public List<Receipt> getReceiptByHospital(String hospital){
        List<Receipt> getReceiptByHospital = receiptRepository.findByHospital(hospital);
        if (getReceiptByHospital.isEmpty()){
            return null;
        }
        return receiptRepository.findByHospital(hospital);
    }
}
