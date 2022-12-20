package so.edu.uct.BloodBank.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.Donor;
import so.edu.uct.BloodBank.model.Hospital;
import so.edu.uct.BloodBank.model.Receipt;
import so.edu.uct.BloodBank.model.State;
import so.edu.uct.BloodBank.service.DonorService;
import so.edu.uct.BloodBank.service.HospitalService;
import so.edu.uct.BloodBank.service.ReceiptService;
import so.edu.uct.BloodBank.service.StateService;
import java.util.HashMap;
import java.util.List;
@RestController @CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/state")
public class StateResource {
    @Autowired
    StateService stateService;

    @Autowired
    DonorService donorService;;

    @Autowired
    ReceiptService receiptService;

    @Autowired
    HospitalService hospitalService;

    // 1. Get All States

    @GetMapping()
    public List<State> allStates(){
        return stateService.getAllStates();
    }


    // 2. Get Specific State By ID

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getState(@PathVariable Long id){
        State getState = stateService.getStateById(id);
        if (getState == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "State Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }

        return ResponseEntity.ok().body(stateService.getStateById(id));
    }


    // 3. Save State

    @PostMapping()
    public State saveState(@RequestBody State state){
        return stateService.saveState(state);
    }

    // 4. Update Specific State By ID

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateState(@RequestBody State state, @PathVariable Long id) {
        State updatedState = stateService.getStateById(id);
        if (updatedState == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "State Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        updatedState.setName(state.getName());
        return ResponseEntity.ok().body(stateService.saveState(updatedState));
    };

    // 5. Delete Specific State By ID

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteState(@PathVariable Long id){
        State deleteState = stateService.getStateById(id);
        if (deleteState == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "State Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        List<Donor> findDonor = donorService.getDonorByState(deleteState.getId().toString());
        List<Receipt> findReceipt = receiptService.getReceiptByState(deleteState.getId().toString());
        List<Hospital> findHospital = hospitalService.getDonorByState(deleteState.getId().toString());
        for (Donor donor : findDonor) {
            System.out.println(donor.getName());
        }
        System.out.println(findReceipt.isEmpty());
        for (Receipt receipt : findReceipt) {
            System.out.println(receipt.getName());
        }
        for (Hospital hospital : findHospital) {
            System.out.println(hospital.getName());
        }

        if (findDonor.isEmpty() && findReceipt.isEmpty() && findHospital.isEmpty()){
            stateService.deleteState(id);
            return ResponseEntity.ok().body(deleteState);
        } else {
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Donor or Receipt or Hospital is Linked To This State");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        }

}







