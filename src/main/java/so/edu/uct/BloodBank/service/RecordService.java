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

    public List<Records> getAllRecords(){
        return recordsRepository.findAll();
    }

    public Long getRecordBloodTypeCc(){
        return recordsRepository.findByBloodType();
    }


    public Records getRecordById(Long id){
        return recordsRepository.findById(id).get();
    }

    public Records saveRecord(Records records){
        return recordsRepository.save(records);
    }

    public void deleteRecord(Long id){
        recordsRepository.deleteById(id);
    }
}
