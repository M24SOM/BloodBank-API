package so.edu.uct.BloodBank.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.Donor;
import so.edu.uct.BloodBank.model.Receipt;
import so.edu.uct.BloodBank.model.Records;
import so.edu.uct.BloodBank.service.ReceiptService;
import so.edu.uct.BloodBank.service.RecordService;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController @CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/record")
public class RecordResource {
    @Autowired
    RecordService recordService;

    @Autowired
    ReceiptService receiptService;

    // 1. Get All Records

    @GetMapping()
    public List<Records> allRecords(){
        return recordService.getAllRecords();
    }

    // 2. Get Specific Record By ID

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getRecord(@PathVariable Long id){
        Records getRecord = recordService.getRecordById(id);
        if (getRecord == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Record Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok().body(recordService.getRecordById(id));
    }

    // 3. Save Record


    @PostMapping(value = "/add")
    public ResponseEntity<?> saveRecord(@RequestBody Records records){
        Receipt findReceipt = receiptService.getReceiptById(records.getReceipt().getId());
        if (findReceipt == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Receipt Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        if (Objects.equals(records.getReceipt().getId(), findReceipt.getId())) {
            return ResponseEntity.ok().body(recordService.saveRecord(records));
        }else {
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Receipt Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
    }

    // 4. Update Specific Record By ID

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateRecord(@RequestBody Records records, @PathVariable Long id) {
        Records updatedRecord = recordService.getRecordById(id);
        if (updatedRecord == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Donation Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        Receipt findReceipt = receiptService.getReceiptById(records.getReceipt().getId());
        if (findReceipt == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Receipt Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        if (Objects.equals(records.getReceipt().getId(), findReceipt.getId())) {
            updatedRecord.setCc(records.getCc());
            updatedRecord.setReceipt(records.getReceipt());
            updatedRecord.setDate(records.getDate());
            return ResponseEntity.ok().body(recordService.saveRecord(updatedRecord));
        }else {
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Receipt Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
    };
    // 5. Delete Specific Record By ID

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteRecord(@PathVariable Long id){
        Records deleteRecord = recordService.getRecordById(id);
        if (deleteRecord == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Donation Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        recordService.deleteRecord(id);
        return ResponseEntity.ok().body(deleteRecord);
    }
    // 6. Get Sum of Record Cc // Test

    @GetMapping(value = "/bloodTypeCc")
    public String getRecordBloodTypeCc(){
        return recordService.getRecordBloodTypeCc();
    }

}







