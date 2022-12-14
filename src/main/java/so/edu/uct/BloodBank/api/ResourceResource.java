package so.edu.uct.BloodBank.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.Records;
import so.edu.uct.BloodBank.service.RecordService;

import java.util.List;

@RestController @CrossOrigin(origins = "*", maxAge = 3600)
//@RequestMapping("/record")
public class ResourceResource {
    @Autowired
    RecordService recordService;

    @GetMapping("/record")
    public List<Records> allRecords(){
        return recordService.getAllRecords();
    }

    @GetMapping("/record/{id}")
    public Records getRecord(@PathVariable Long id){
        return recordService.getRecordById(id);
    }


    @PostMapping("/record/add")
    public Records saveRecord(@RequestBody Records records){

        return recordService.saveRecord(records);
    }

    @PutMapping("/record/{id}")
    public Records updateRecord(@RequestBody Records records, @PathVariable Long id) {
        Records UpDonation = recordService.getRecordById(id);
        UpDonation.setCc(records.getCc());
        UpDonation.setBloodType(records.getBloodType());
        UpDonation.setReceipt(records.getReceipt());
        UpDonation.setBloodType(records.getBloodType());
        UpDonation.setType(records.getType());
        return recordService.saveRecord(UpDonation);
    };

    @DeleteMapping("record/{id}")
    public Records deleteRecord(@PathVariable Long id){
        Records deleteRecord = recordService.getRecordById(id);
        recordService.deleteRecord(id);
        return deleteRecord;
    }

    @GetMapping("/record/bloodTypeCc/{query}")
    public Long getRecordBloodTypeCc(@PathVariable Long query){
        System.out.println(recordService.getRecordBloodTypeCc());
        return recordService.getRecordBloodTypeCc();
    }

}







