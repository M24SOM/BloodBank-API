package so.edu.uct.BloodBank.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import so.edu.uct.BloodBank.model.Donation;
import so.edu.uct.BloodBank.repository.DonationRepository;
import java.util.List;

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

    public Long getDonationBloodTypeCc(){
        return donationRepository.findByBloodType();
    }

}
