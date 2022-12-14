package so.edu.uct.BloodBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import so.edu.uct.BloodBank.model.Donor;
import so.edu.uct.BloodBank.repository.DonorRepository;
import java.util.List;

@Service
public class DonorService {
    @Autowired
    DonorRepository donorRepository;

    public List<Donor> getAllDonors(){
        return donorRepository.findAll();
    }

    public Donor getDonorById(Long id){
        return donorRepository.findById(id).get();
    }

    public List<Donor> getDonorByMobileNo(String phone){
        return donorRepository.findByMobileNo(phone);
    }

    public List<Donor> getDonorByState(String phone){
        return donorRepository.findByState(phone);
    }

    public Long sumOfDonor(){
        return donorRepository.sumOfDonor();
    }

    public Donor saveDonor(Donor donor){
        return donorRepository.save(donor);
    }

    public void deleteDonor(Long id){
        donorRepository.deleteById(id);
    }
}
