package so.edu.uct.BloodBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import so.edu.uct.BloodBank.model.Donation;
import so.edu.uct.BloodBank.model.Records;
import so.edu.uct.BloodBank.repository.DonationRepository;
import so.edu.uct.BloodBank.repository.RecordsRepository;

import java.util.List;

@Service
public class RecordService {
    @Autowired
    RecordsRepository recordsRepository;

    // 1. Get All Records

    public List<Records> getAllRecords(){
        return recordsRepository.findAll();
    }

    // 2. Get Specific Record By ID

    public Records getRecordById(Long id){
        return recordsRepository.findById(id).get();
    }

    // 3. Save Record

    public Records saveRecord(Records records){
        return recordsRepository.save(records);
    }

    // 4. Delete Specific Record By ID

    public void deleteRecord(Long id){
        recordsRepository.deleteById(id);
    }

    // 5. Get Sum of Record Cc For Dashboard

    public Long getRecordBloodTypeCc(){
        return recordsRepository.findByBloodType();
    }
}
