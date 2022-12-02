package so.edu.uct.BloodBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import so.edu.uct.BloodBank.model.Hospital;
import so.edu.uct.BloodBank.repository.HospitalRepository;

import java.util.List;

@Service
public class HospitalService {
    @Autowired
    HospitalRepository hospitalRepository;


    public List<Hospital> getAllHospitals(){
        return hospitalRepository.findAll();
    }


    public Hospital getHospitalById(Long id){
        return hospitalRepository.findById(id).get();
    }

    public Hospital saveHospital(Hospital hospital){
        return hospitalRepository.save(hospital);
    }

    public void deleteHospital(Long id){
        hospitalRepository.deleteById(id);
    }
}
