package so.edu.uct.BloodBank.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.Records;
import so.edu.uct.BloodBank.service.RecordService;

import java.util.List;

@RestController @CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/record")
public class RecordResource {
    @Autowired
    RecordService recordService;

    // 1. Get All Records

    @GetMapping()
    public List<Records> allRecords(){
        return recordService.getAllRecords();
    }

    // 2. Get Specific Record By ID

    @GetMapping(value = "/{id}")
    public Records getRecord(@PathVariable Long id){
        return recordService.getRecordById(id);
    }

    // 3. Save Record


    @PostMapping(value = "/add")
    public Records saveRecord(@RequestBody Records records){

        return recordService.saveRecord(records);
    }

    // 4. Update Specific Record By ID

    @PutMapping(value = "/{id}")
    public Records updateRecord(@RequestBody Records records, @PathVariable Long id) {
        Records updatedRecord = recordService.getRecordById(id);
        updatedRecord.setCc(records.getCc());
        updatedRecord.setBloodType(records.getBloodType());
        updatedRecord.setReceipt(records.getReceipt());
        updatedRecord.setDate(records.getDate());
        return recordService.saveRecord(updatedRecord);
    };
    // 5. Delete Specific Record By ID

    @DeleteMapping(value = "/{id}")
    public Records deleteRecord(@PathVariable Long id){
        Records deleteRecord = recordService.getRecordById(id);
        recordService.deleteRecord(id);
        return deleteRecord;
    }
    // 6. Get Sum of Record Cc // Test

    @GetMapping(value = "/bloodTypeCc")
    public Long getRecordBloodTypeCc(){
        System.out.println(recordService.getRecordBloodTypeCc());
        return recordService.getRecordBloodTypeCc();
    }

}







