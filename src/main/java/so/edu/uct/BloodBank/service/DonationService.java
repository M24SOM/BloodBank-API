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

    public List<Donation> getAllDonations(){
        return donationRepository.findAll();
    }

    public Long getDonationBloodTypeCc(){
        return donationRepository.findByBloodType();
    }

    public Donation getDonationById(Long id){
        return donationRepository.findById(id).get();
    }

    public Donation saveDonation(Donation donation){
        return donationRepository.save(donation);
    }

    public void deleteDonation(Long id){
        donationRepository.deleteById(id);
    }
}
