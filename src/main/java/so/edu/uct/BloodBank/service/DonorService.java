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

    public List<Donor> getAllDonors(){
        return donorRepository.findAll();
    }

    public Donor getDonorById(Long id){
        return donorRepository.findById(id).get();
    }

    public List<Donor> getDonorByMobileNo(String phone){
        return donorRepository.findByMobileNo(phone);
    }

//    public List<DonorDTO> getDonorByState(Long query){
//        DonorDTO donorDTO =  donorRepository.findByState(query).stream()
//                .map(donors -> {
//                    DonorDTO donorDTO1 = new DonorDTO();
//                    donorDTO1.setName(donors.getName());
//                    donorDTO1.setState(donors.getState().getName());
//                    return donorDTO1;
//                } ).collect(Collectors.toList());
//        return ;
//    }

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
