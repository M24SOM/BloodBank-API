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

    public List<BloodType> getAllBloodTypes(){
        return bloodTypeRepository.findAll();
    }


    public BloodType getBloodTypeById(Long id){
        return bloodTypeRepository.findById(id).get();
    }

    public BloodType saveBloodType(BloodType bloodType){
        return bloodTypeRepository.save(bloodType);
    }

    public void deleteBloodType(Long id){
        bloodTypeRepository.deleteById(id);
    }
}
