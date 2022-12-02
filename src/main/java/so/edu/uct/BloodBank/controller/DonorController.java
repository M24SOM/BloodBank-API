package so.edu.uct.BloodBank.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.Donor;
import so.edu.uct.BloodBank.service.DonorService;
import java.util.List;

@RestController
//@RequestMapping("/donor")
public class DonorController {
    @Autowired
    DonorService donorService;

    @GetMapping("/donor")
    public List<Donor> allDonor(){
        return donorService.getAllDonors();
    }

    @GetMapping("/donor/{id}")
    public Donor getDonor(@PathVariable Long id){
        return donorService.getDonorById(id);
    }

    @PostMapping("/donor")
    public Donor saveDonor(@RequestBody Donor donor){
        return donorService.saveDonor(donor);
    }

    @PutMapping("/donor/{id}")
    public Donor updateDonor(@RequestBody Donor donor, @PathVariable Long id) {
        Donor UpHospital = donorService.getDonorById(id);
        UpHospital.setName(donor.getName());
        return donorService.saveDonor(UpHospital);
    };

    @DeleteMapping("donor/{id}")
    public Donor deleteDonor(@PathVariable Long id){
        Donor deleteHospital = donorService.getDonorById(id);
        donorService.deleteDonor(id);
        return deleteHospital;
    }

}







