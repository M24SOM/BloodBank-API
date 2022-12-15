package so.edu.uct.BloodBank.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.Donor;
import so.edu.uct.BloodBank.service.DonorService;
import so.edu.uct.BloodBank.service.StateService;

import java.util.List;

@RestController @CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/donor")
public class DonorResource {
    @Autowired
    DonorService donorService;


    // 1. Get All Donors

    @GetMapping()
    public List<Donor> allDonor(){
        return donorService.getAllDonors();
    }

    // 2. Get Specific Donor By ID

    @GetMapping(value = "/{id}")
    public Donor getDonor(@PathVariable Long id){
        return donorService.getDonorById(id);
    }

    // 3. Get Specific Donor By phone

    @GetMapping(value = "/phone/{phone}")
    public List<Donor> getDonorByPhone(@PathVariable String phone){
        return donorService.getDonorByMobileNo(phone);
    }

    // 4. Save Donor

    @PostMapping(value = "/add")
    public Donor saveDonor(@RequestBody Donor donor){
        return donorService.saveDonor(donor);
    }


    // 5. Update Specific Donor By ID

    @PutMapping(value = "/{id}")
    public Donor updateDonor(@RequestBody Donor donor, @PathVariable Long id) {
        Donor updatedDonor = donorService.getDonorById(id);
        updatedDonor.setName(donor.getName());
        updatedDonor.setBloodType(donor.getBloodType());
        updatedDonor.setDateBirth(donor.getDateBirth());
        updatedDonor.setWeight(donor.getWeight());
        updatedDonor.setMobileNo(donor.getMobileNo());
        updatedDonor.setState(donor.getState());
        updatedDonor.setIsHealthy(donor.getIsHealthy());
        return donorService.saveDonor(updatedDonor);
    };

    // 6. Delete Specific Donor By ID

    @DeleteMapping(value = "/{id}")
    public Donor deleteDonor(@PathVariable Long id){
        Donor deleteDonor = donorService.getDonorById(id);
        donorService.deleteDonor(id);
        return deleteDonor;
    }

}







