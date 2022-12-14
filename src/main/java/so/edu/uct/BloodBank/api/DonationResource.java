package so.edu.uct.BloodBank.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.Donation;
import so.edu.uct.BloodBank.service.DonationService;

import java.util.List;

@RestController
//@RequestMapping("/donation")
public class DonationResource {
    @Autowired
    DonationService donationService;

    @GetMapping("/donation")
    public List<Donation> allDonations(){
        return donationService.getAllDonations();
    }

    @GetMapping("/donation/{id}")
    public Donation getDonation(@PathVariable Long id){
        return donationService.getDonationById(id);
    }


    @PostMapping("/donation/add")
    public Donation saveDonation(@RequestBody Donation donation){

        return donationService.saveDonation(donation);
    }

    @PutMapping("/donation/{id}")
    public Donation updateDonation(@RequestBody Donation donation, @PathVariable Long id) {
        Donation UpDonation = donationService.getDonationById(id);
        UpDonation.setCc(donation.getCc());
        UpDonation.setBloodType(donation.getBloodType());
        UpDonation.setDonor(donation.getDonor());
        UpDonation.setBloodType(donation.getBloodType());
        UpDonation.setType(donation.getType());
        return donationService.saveDonation(UpDonation);
    };

    @DeleteMapping("donation/{id}")
    public Donation deleteDonation(@PathVariable Long id){
        Donation deleteDonor = donationService.getDonationById(id);
        donationService.deleteDonation(id);
        return deleteDonor;
    }

    @GetMapping("/donation/bloodTypeCc/{query}")
    public Long getDonationBloodTypeCc(@PathVariable Long query){
        System.out.println(donationService.getDonationBloodTypeCc());
        return donationService.getDonationBloodTypeCc();
    }

}







