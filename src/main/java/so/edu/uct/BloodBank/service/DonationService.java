package so.edu.uct.BloodBank.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import so.edu.uct.BloodBank.model.Donation;
import so.edu.uct.BloodBank.model.Donor;
import so.edu.uct.BloodBank.model.State;
import so.edu.uct.BloodBank.repository.DonationRepository;
import java.util.List;
import java.util.Optional;

@Service
public class DonationService {
    @Autowired
    DonationRepository donationRepository;

    // 1. Get All Donations

    public List<Donation> getAllDonations(){
        return donationRepository.findAll();
    }


    // 2. Get Specific Donation By ID

    public Donation getDonationById(Long id){
        Optional<Donation> getDonationById = donationRepository.findById(id);
        if (getDonationById.isEmpty()){
            return null;
        }
        return donationRepository.findById(id).get();

    }

    // 3. Save Donation

    public Donation saveDonation(Donation donation){
        return donationRepository.save(donation);
    }

    // 4. Delete Specific Donation By ID

    public void deleteDonation(Long id){
        donationRepository.deleteById(id);
    }

    // 5. Get Sum of Donation Cc For Dashboard

    public String getDonationBloodTypeCc(){
        Long getDonationBloodTypeCc = donationRepository.findByBloodType();
        if (getDonationBloodTypeCc == null){
            return "0";
        }
        return donationRepository.findByBloodType().toString();
    }

    // 6. Get Donors in Donation
    public List<Donation> getDonorPerDonation(String donor){
        return donationRepository.findByDonor(donor);
    }

}
