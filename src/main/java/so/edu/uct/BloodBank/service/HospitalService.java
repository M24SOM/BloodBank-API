package so.edu.uct.BloodBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import so.edu.uct.BloodBank.model.Donor;
import so.edu.uct.BloodBank.model.Hospital;
import so.edu.uct.BloodBank.repository.HospitalRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {
    @Autowired
    HospitalRepository hospitalRepository;

    // 1. Get All Hospitals

    public List<Hospital> getAllHospitals(){
        return hospitalRepository.findAll();
    }

    // 2. Get Specific Hospital By ID


    public Hospital getHospitalById(Long id){
        Optional<Hospital> getHospitalById = hospitalRepository.findById(id);
        if (getHospitalById.isEmpty()){
            return null;
        }
        return hospitalRepository.findById(id).get();
    }

    // 3. Save Donation

    public Hospital saveHospital(Hospital hospital){
        return hospitalRepository.save(hospital);
    }

    // 4. Delete Specific Hospital By ID

    public void deleteHospital(Long id){
        hospitalRepository.deleteById(id);
    }


    // 5. Get Sum of Hospital For Dashboard

    public Long sumOfHospital(){
        return hospitalRepository.sumOfHospital();
    }

    // 8. Get Specific Donor By State

    public List<Hospital> getDonorByState(String state){
        return hospitalRepository.findByState(state);
    }
}
