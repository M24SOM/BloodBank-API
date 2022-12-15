package so.edu.uct.BloodBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import so.edu.uct.BloodBank.model.BloodType;
import so.edu.uct.BloodBank.repository.BloodTypeRepository;

import java.util.List;

@Service
public class BloodTypeService {
    @Autowired
    BloodTypeRepository bloodTypeRepository;


    // 1. Get All Blood Types
    public List<BloodType> getAllBloodTypes(){
        return bloodTypeRepository.findAll();
    }

    // 2. Get Specific Blood Type By ID

    public BloodType getBloodTypeById(Long id){
        return bloodTypeRepository.findById(id).get();
    }
    // 3. Save Blood Type

    public BloodType saveBloodType(BloodType bloodType){
        return bloodTypeRepository.save(bloodType);
    }
    // 4. Delete Specific Blood Type By ID

    public void deleteBloodType(Long id){
        bloodTypeRepository.deleteById(id);
    }
}
