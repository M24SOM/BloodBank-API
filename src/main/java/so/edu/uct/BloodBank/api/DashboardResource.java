package so.edu.uct.BloodBank.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.State;
import so.edu.uct.BloodBank.service.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/state")
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

    @GetMapping("/dashboard")
    public Map dashboard(){
        Map<String, String> map = new HashMap<>();
        Long ccDonation = donationService.getDonationBloodTypeCc();
        Long ccRecord = recordService.getRecordBloodTypeCc();
        Long sumOfHospital = hospitalService.sumOfHospital();
        Long sumOfDonor = donorService.sumOfDonor();
        Long sumOfReceipt = receiptService.sumOfReceipt();
        String numOfCc = String.valueOf(ccDonation - ccRecord);
        map.put("Donor", String.valueOf(sumOfDonor));
        map.put("Receipts", String.valueOf(sumOfReceipt));
        map.put("CC ", numOfCc);
        map.put("Hospital  ", String.valueOf(sumOfHospital));
        System.out.println(map);
        return map;
    }


}







