package so.edu.uct.BloodBank.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.Donation;
import so.edu.uct.BloodBank.model.Donor;
import so.edu.uct.BloodBank.service.DonationService;
import so.edu.uct.BloodBank.service.DonorService;

import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController @CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/donation")
public class DonationResource {
    @Autowired
    DonationService donationService;

    @Autowired
    DonorService donorService;

    // 1. Get All Donations

    @GetMapping()
    public List<Donation> allDonations(){
        return donationService.getAllDonations();
    }

    // 2. Get Specific Donation By ID

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getDonation(@PathVariable Long id){
        Donation getDonation = donationService.getDonationById(id);
        if (getDonation == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Donation Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok().body(donationService.getDonationById(id));
    }

    // 3. Save Donation

    @PostMapping(value = "/add")
    public ResponseEntity<?> saveDonation(@RequestBody Donation donation){
        Donor findDonor = donorService.getDonorById(donation.getDonor().getId());
        if (findDonor == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Donor Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        if (Objects.equals(donation.getDonor().getId(), findDonor.getId())){
            return ResponseEntity.ok().body(donationService.saveDonation(donation));
        }else {
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Donor Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
    }
    // 4. Update Specific Donation By ID

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateDonation(@RequestBody Donation donation, @PathVariable Long id) {
        Donation updatedDonation = donationService.getDonationById(id);
        if (updatedDonation == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Donation Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        Donor findDonor = donorService.getDonorById(donation.getDonor().getId());
        if (findDonor == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Donor Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        if (Objects.equals(donation.getDonor().getId(), findDonor.getId())){
            updatedDonation.setCc(donation.getCc());
            updatedDonation.setDonor(donation.getDonor());
            updatedDonation.setDate(donation.getDate());
            return ResponseEntity.ok().body(donationService.saveDonation(updatedDonation));
        }else {
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Donor Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }

    };
    // 5. Delete Specific Donation By ID

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteDonation(@PathVariable Long id){
        Donation deleteDonor = donationService.getDonationById(id);
        if (deleteDonor == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Donation Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        donationService.deleteDonation(id);
        return ResponseEntity.ok().body(deleteDonor);
    }

    // 6. Get Sum of Donation Cc // Test
    @GetMapping(value = "/bloodTypeCc")
    public String getDonationBloodTypeCc(){
        return donationService.getDonationBCc().toString();
    }

}







