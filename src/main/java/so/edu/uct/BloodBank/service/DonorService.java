package so.edu.uct.BloodBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import so.edu.uct.BloodBank.dto.DonorDTO;
import so.edu.uct.BloodBank.model.Donor;
import so.edu.uct.BloodBank.model.State;
import so.edu.uct.BloodBank.repository.DonorRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonorService {
    @Autowired
    DonorRepository donorRepository;

    // 1. Get All Donors


    public List<Donor> getAllDonors(){
        return donorRepository.findAll();
    }
    // 2. Get Specific Donor By ID

    public Donor getDonorById(Long id){
        return donorRepository.findById(id).get();
    }
    // 3. Get Specific Donor By phone

    public List<Donor> getDonorByMobileNo(String phone){
        return donorRepository.findByMobileNo(phone);
    }

    // 4. Save Receipt


    public Donor saveDonor(Donor donor){
        return donorRepository.save(donor);
    }


    // 5. Delete Specific Donor By ID
    public void deleteDonor(Long id){
        donorRepository.deleteById(id);
    }


    // 6. Get Sum of Donor For Dashboard

    public Long sumOfDonor(){
        return donorRepository.sumOfDonor();
    }
}
