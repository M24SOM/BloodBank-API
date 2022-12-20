package so.edu.uct.BloodBank.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.BloodType;
import so.edu.uct.BloodBank.model.Donor;
import so.edu.uct.BloodBank.model.Receipt;
import so.edu.uct.BloodBank.service.BloodTypeService;
import so.edu.uct.BloodBank.service.DonorService;
import so.edu.uct.BloodBank.service.ReceiptService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController @CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/bloodType")
public class BloodTypeResource {
    @Autowired
    BloodTypeService bloodTypeService;

    @Autowired
    DonorService donorService;;

    @Autowired
    ReceiptService receiptService;


    // 1. Get All Blood Types
    @GetMapping()
    public List<BloodType> allBloodTypes(){
        return bloodTypeService.getAllBloodTypes();
    }


    // 2. Get Specific Blood Type By ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getBloodType(@PathVariable Long id){
        BloodType getBloodType = bloodTypeService.getBloodTypeById(id);
        if (getBloodType == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "BloodType Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok().body(bloodTypeService.getBloodTypeById(id));
    }


    // 3. Save Blood Type
    @PostMapping()
    public BloodType saveBloodType(@RequestBody BloodType bloodType){
        return bloodTypeService.saveBloodType(bloodType);
    }

    // 4. Update Specific Blood Type By ID
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateBloodType(@RequestBody BloodType bloodType, @PathVariable Long id) {
        BloodType updatedBloodType = bloodTypeService.getBloodTypeById(id);
        if (updatedBloodType == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "BloodType Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        updatedBloodType.setName(bloodType.getName());
        return  ResponseEntity.ok().body(bloodTypeService.saveBloodType(updatedBloodType));
    };

    // 5. Delete Specific Blood Type By ID
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteBloodType(@PathVariable Long id){
        BloodType deleteBloodType = bloodTypeService.getBloodTypeById(id);
        if (deleteBloodType == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "BloodType Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        List<Donor> findDonor = donorService.getDonorByBloodType(deleteBloodType.getId().toString());
        List<Receipt> findReceipt = receiptService.getReceiptByBloodType(deleteBloodType.getId().toString());
        System.out.println(findDonor.isEmpty());
        for (Donor donor : findDonor) {
            System.out.println(donor.getName());
        }
        System.out.println(findReceipt.isEmpty());
        for (Receipt receipt : findReceipt) {
            System.out.println(receipt.getName());
        }
        if (findDonor.isEmpty() && findReceipt.isEmpty()){
            bloodTypeService.deleteBloodType(id);
            return ResponseEntity.ok().body(deleteBloodType);
        } else {
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Donor or Receipt Linked To This Blood Type");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }

    }


}







