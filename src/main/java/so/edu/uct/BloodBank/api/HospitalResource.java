package so.edu.uct.BloodBank.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.Donor;
import so.edu.uct.BloodBank.model.Hospital;
import so.edu.uct.BloodBank.model.Receipt;
import so.edu.uct.BloodBank.model.State;
import so.edu.uct.BloodBank.service.HospitalService;
import so.edu.uct.BloodBank.service.ReceiptService;
import so.edu.uct.BloodBank.service.StateService;

import java.util.HashMap;
import java.util.List;

@RestController @CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/hospital")
public class HospitalResource {
    @Autowired
    HospitalService hospitalService;

    @Autowired
    StateService stateService;

    @Autowired
    ReceiptService receiptService;

    // 1. Get All Hospitals

    @GetMapping()
    public List<Hospital> allHospital(){
        return hospitalService.getAllHospitals();
    }

    // 2. Get Specific Hospital By ID

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getHospital(@PathVariable Long id){
        Hospital getHospital = hospitalService.getHospitalById(id);
        if (getHospital == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Hospital Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok().body(hospitalService.getHospitalById(id));
    }
    // 3. Save Hospital


    @PostMapping()
    public ResponseEntity<?> saveHospital(@RequestBody Hospital hospital){
        State findState = stateService.getStateById(hospital.getState().getId());
        if (findState == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "State Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok().body(hospitalService.saveHospital(hospital));
    }

    // 4. Update Specific Hospital By ID

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateHospital(@RequestBody Hospital hospital, @PathVariable Long id) {
        Hospital updatedHospital = hospitalService.getHospitalById(id);
        if (updatedHospital == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Hospital Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        State findState = stateService.getStateById(hospital.getState().getId());
        if (findState == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "State Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        updatedHospital.setName(hospital.getName());
        updatedHospital.setState(hospital.getState());
        return ResponseEntity.ok().body(hospitalService.saveHospital(updatedHospital));
    }
    // 5. Delete Specific Hospital By ID
    // todo
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteHospital(@PathVariable Long id){
        Hospital deleteHospital = hospitalService.getHospitalById(id);
        if (deleteHospital == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "State Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        List<Receipt> findReceipt = receiptService.getReceiptByHospital(deleteHospital.getId().toString());
        for (Receipt receipt : findReceipt) {
            System.out.println(receipt.getName());
        }
        if (findReceipt.isEmpty()){
            hospitalService.deleteHospital(id);
            return ResponseEntity.ok().body(deleteHospital);
        } else {
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Receipt  is Linked To This Hospital");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
    }

}







