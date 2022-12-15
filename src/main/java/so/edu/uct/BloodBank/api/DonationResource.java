package so.edu.uct.BloodBank.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.Donation;
import so.edu.uct.BloodBank.service.DonationService;

import java.util.List;

@RestController @CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/donation")
public class DonationResource {
    @Autowired
    DonationService donationService;

    // 1. Get All Donations

    @GetMapping()
    public List<Donation> allDonations(){
        return donationService.getAllDonations();
    }

    // 2. Get Specific Donation By ID

    @GetMapping(value = "/{id}")
    public Donation getDonation(@PathVariable Long id){
        return donationService.getDonationById(id);
    }

    // 3. Save Donation

    @PostMapping(value = "/add")
    public Donation saveDonation(@RequestBody Donation donation){

        return donationService.saveDonation(donation);
    }
    // 4. Update Specific Donation By ID

    @PutMapping(value = "/{id}")
    public Donation updateDonation(@RequestBody Donation donation, @PathVariable Long id) {
        Donation updatedDonation = donationService.getDonationById(id);
        updatedDonation.setCc(donation.getCc());
        updatedDonation.setBloodType(donation.getBloodType());
        updatedDonation.setDonor(donation.getDonor());
        updatedDonation.setDate(donation.getDate());
        return donationService.saveDonation(updatedDonation);
    };
    // 5. Delete Specific Donation By ID

    @DeleteMapping(value = "/{id}")
    public Donation deleteDonation(@PathVariable Long id){
        Donation deleteDonor = donationService.getDonationById(id);
        donationService.deleteDonation(id);
        return deleteDonor;
    }

    // 6. Get Sum of Donation Cc // Test
    @GetMapping(value = "/bloodTypeCc")
    public Long getDonationBloodTypeCc(){
        System.out.println(donationService.getDonationBloodTypeCc());
        return donationService.getDonationBloodTypeCc();
    }

}







