package so.edu.uct.BloodBank.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.State;
import so.edu.uct.BloodBank.service.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController @CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/dashboard")
public class DashboardResource {
    @Autowired
    RecordService recordService;
    @Autowired
    DonationService donationService;

    @Autowired
    HospitalService hospitalService;

    @Autowired
    DonorService donorService;


    @Autowired
    ReceiptService receiptService;

    // Dashboard
    // 1. Number of Cc =  (Sum of Cc in Donation - Sum of Cc in Records)
    // 2. Number of Donors = Sum of Donors in Donor
    // 3. Number of Donors = Sum of Donors in Donor
    // 4. Number of Receipts = Sum of Receipts in Receipt
    // 5. Number of Hospitals = Sum of Hospitals in Hospital

    @GetMapping()
    public Map dashboard(){
        Map<String, String> map = new HashMap<>();
        Long ccDonation = donationService.getDonationBloodTypeCc();
        Long ccRecord = recordService.getRecordBloodTypeCc();
        Long sumOfHospital = hospitalService.sumOfHospital();
        Long sumOfDonor = donorService.sumOfDonor();
        Long sumOfReceipt = receiptService.sumOfReceipt();
        String numOfCc = String.valueOf(ccDonation - ccRecord);
        map.put("Donor", String.valueOf(sumOfDonor) == null ? String.valueOf(0) : String.valueOf(sumOfDonor) );
        map.put("Receipts", String.valueOf(sumOfReceipt) == null ? String.valueOf(0) : String.valueOf(sumOfReceipt) );
        map.put("CC ", numOfCc == null ? String.valueOf(0) : numOfCc);
        map.put("Hospital  ", String.valueOf(sumOfHospital) == null ? String.valueOf(0) : String.valueOf(sumOfHospital));
        System.out.println(map);
        return map;
    }


}







