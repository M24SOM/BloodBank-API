package so.edu.uct.BloodBank.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.dto.DonorDTO;
import so.edu.uct.BloodBank.dto.ReceiptDTO;
import so.edu.uct.BloodBank.model.*;
import so.edu.uct.BloodBank.service.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController @CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/receipt")
public class ReceiptResource {
    @Autowired
    ReceiptService receiptService;

    @Autowired
    BloodTypeService bloodTypeService;


    @Autowired
    StateService stateService;

    @Autowired
    HospitalService hospitalService;

    @Autowired
    RecordService recordService;

    // 1. Get All Receipts

    @GetMapping()
    public List<Receipt> allReceipt(){
        return receiptService.getAllReceipts();
    }

    // 2. Get Specific Receipt By ID

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getReceipt(@PathVariable Long id){
        Receipt getReceipt = receiptService.getReceiptById(id);
        if (getReceipt == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Receipt Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok().body(receiptService.getReceiptById(id));
    }

    // 3. Get Specific Receipt By phone

    @GetMapping(value = "/phone/{phone}")
    public ResponseEntity<?> getReceiptByPhone(@PathVariable String phone){
        List<Receipt> getReceiptByPhone = receiptService.getReceiptByPhone(phone);
        if (getReceiptByPhone == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Receipt Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok().body(receiptService.getReceiptByPhone(phone));
    }
    // 4. Save Receipt

    @PostMapping(value = "/add")
    public ResponseEntity<?> saveReceipt(@RequestBody Receipt receipt){
        BloodType findBloodType = bloodTypeService.getBloodTypeById(receipt.getBloodType().getId());
        State findState = stateService.getStateById(receipt.getState().getId());
        Hospital findHospital = hospitalService.getHospitalById(receipt.getHospital().getId());
        if (findState == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "State Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        if (findBloodType == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "BloodType Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        if (findHospital == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Hospital Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        if (Objects.equals(receipt.getBloodType().getId(), findBloodType.getId()) && Objects.equals(receipt.getState().getId(), findState.getId())){
            return ResponseEntity.ok().body(receiptService.saveReceipt(receipt));
        } else {
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "BloodType Or State Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
    }


    // 5. Update Specific Receipt By ID


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateReceipt(@RequestBody Receipt receipt, @PathVariable Long id) {
        Receipt updatedReceipt = receiptService.getReceiptById(id);
        if (updatedReceipt == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Receipt Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        BloodType findBloodType = bloodTypeService.getBloodTypeById(receipt.getBloodType().getId());
        State findState = stateService.getStateById(receipt.getState().getId());
        Hospital findHospital = hospitalService.getHospitalById(receipt.getHospital().getId());

        if (findState == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "State Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        if (findBloodType == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "BloodType Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        if (findHospital == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Hospital Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        if (Objects.equals(receipt.getBloodType().getId(), findBloodType.getId()) && Objects.equals(receipt.getState().getId(), findState.getId())) {
            updatedReceipt.setName(receipt.getName());
            updatedReceipt.setDateBirth(receipt.getDateBirth());
            updatedReceipt.setMobileNo(receipt.getMobileNo());
            updatedReceipt.setBloodType(receipt.getBloodType());
            updatedReceipt.setState(receipt.getState());
            return ResponseEntity.ok().body(receiptService.saveReceipt(updatedReceipt));
        }else {
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "BloodType Or State Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
    };

    // 6. Delete Receipt Donor By ID

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteReceipt(@PathVariable Long id){

        Receipt deleteReceipt = receiptService.getReceiptById(id);
        if (deleteReceipt == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Donor Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        List<Records> findDonor = recordService.getDonorPerRecords(deleteReceipt.getId().toString());
        if (findDonor == null){
            receiptService.deleteReceipt(id);
        return ResponseEntity.ok().body(deleteReceipt);
        } else {
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Receipt is Linked To This Records");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
    }


    // 7. Get Donor Per State

    @GetMapping(value = "/hospital/{hospital}")
    public ResponseEntity<?> getAllReceiptPerHospital(@PathVariable String hospital){
        List<Receipt> getAllReceiptPerHospital = receiptService.getReceiptByHospital(hospital);
        if (getAllReceiptPerHospital == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Receipt Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        for (Receipt receipt : getAllReceiptPerHospital) {
            System.out.println(receipt.getName());
        }
        List<ReceiptDTO> receiptDTOS =  getAllReceiptPerHospital.stream()
                .map(receipts -> {
                    ReceiptDTO receiptDTO = new ReceiptDTO();
                    receiptDTO.setId(receipts.getId());
                    receiptDTO.setName(receipts.getName());
                    receiptDTO.setDateBirth(receipts.getDateBirth());
                    receiptDTO.setMobileNo(receipts.getMobileNo());
                    receiptDTO.setBloodType(receipts.getBloodType().getName());
                    receiptDTO.setState(receipts.getState().getName());
                    receiptDTO.setHospital(receipts.getHospital().getName());
                    receiptDTO.setCreatedAt(receipts.getCreatedAt().toString());
                    receiptDTO.setUpdatedAt(receipts.getUpdatedAt().toString());
                    return receiptDTO;
                } ).collect(Collectors.toList());

        return ResponseEntity.ok().body(receiptDTOS);
    }

}







