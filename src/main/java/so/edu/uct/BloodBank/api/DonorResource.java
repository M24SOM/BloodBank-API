package so.edu.uct.BloodBank.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.Donor;
import so.edu.uct.BloodBank.service.DonorService;
import so.edu.uct.BloodBank.service.StateService;

import java.util.List;

@RestController @CrossOrigin(origins = "*", maxAge = 3600)
//@RequestMapping("/donor")
public class DonorResource {
    @Autowired
    DonorService donorService;

    @Autowired
    StateService stateService;

    @GetMapping("/donor")
    public List<Donor> allDonor(){
        return donorService.getAllDonors();
    }

    @GetMapping("/donor/{id}")
    public Donor getDonor(@PathVariable Long id){
        return donorService.getDonorById(id);
    }

    @GetMapping("/donor/phone/{phone}")
    public List<Donor> getDonorByPhone(@PathVariable String phone){
        return donorService.getDonorByMobileNo(phone);
    }

//    @GetMapping("/donor/state/{state}")
//    public List<Donor> getDonorByState(@PathVariable Long state){
//        State findbyState = stateService.getStateById(state);
//        System.out.println(findbyState.getName());
//        System.out.println(donorService.getDonorByState(findbyState));
//        return donorService.getDonorByState(findbyState);
//    }

//    @GetMapping("/donor/state/{state}")
//    public List<Donor> getDonorByState(@PathVariable Long state){
//        return donorService.getDonorByState(state);
//    }


    @PostMapping("/donor/add")
    public Donor saveDonor(@RequestBody Donor donor){
        return donorService.saveDonor(donor);
    }

    @PutMapping("/donor/{id}")
    public Donor updateDonor(@RequestBody Donor donor, @PathVariable Long id) {
        Donor UpDonor = donorService.getDonorById(id);
        UpDonor.setName(donor.getName());
        UpDonor.setBloodType(donor.getBloodType());
        UpDonor.setDateBirth(donor.getDateBirth());
        UpDonor.setWeight(donor.getWeight());
        UpDonor.setMobileNo(donor.getMobileNo());
        UpDonor.setState(donor.getState());
        UpDonor.setIsHealthy(donor.getIsHealthy());
        return donorService.saveDonor(UpDonor);
    };

    @DeleteMapping("donor/{id}")
    public Donor deleteDonor(@PathVariable Long id){
        Donor deleteDonor = donorService.getDonorById(id);
        donorService.deleteDonor(id);
        return deleteDonor;
    }

}







