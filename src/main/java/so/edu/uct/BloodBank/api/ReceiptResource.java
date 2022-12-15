package so.edu.uct.BloodBank.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.Receipt;
import so.edu.uct.BloodBank.service.ReceiptService;

import java.util.List;

@RestController @CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/receipt")
public class ReceiptResource {
    @Autowired
    ReceiptService receiptService;

    // 1. Get All Receipts

    @GetMapping()
    public List<Receipt> allReceipt(){
        return receiptService.getAllReceipts();
    }

    // 2. Get Specific Receipt By ID

    @GetMapping(value = "/{id}")
    public Receipt getReceipt(@PathVariable Long id){
        return receiptService.getReceiptById(id);
    }

    // 3. Get Specific Receipt By phone

    @GetMapping(value = "/phone/{phone}")
    public List<Receipt> getReceiptByPhone(@PathVariable String phone){
        return receiptService.getReceiptByPhone(phone);
    }
    // 4. Save Receipt

    @PostMapping()
    public Receipt saveReceipt(@RequestBody Receipt receipt){
        return receiptService.saveReceipt(receipt);
    }


    // 5. Update Specific Receipt By ID


    @PutMapping(value = "/{id}")
    public Receipt updateReceipt(@RequestBody Receipt receipt, @PathVariable Long id) {
        Receipt updatedReceipt = receiptService.getReceiptById(id);
        updatedReceipt.setName(receipt.getName());
        updatedReceipt.setDateBirth(receipt.getDateBirth());
        updatedReceipt.setMobileNo(receipt.getMobileNo());
        updatedReceipt.setBloodType(receipt.getBloodType());
        updatedReceipt.setState(receipt.getState());
        return receiptService.saveReceipt(updatedReceipt);
    };

    // 6. Delete Receipt Donor By ID


    @DeleteMapping(value = "/{id}")
    public Receipt deleteReceipt(@PathVariable Long id){
        Receipt deleteReceipt = receiptService.getReceiptById(id);
        receiptService.deleteReceipt(id);
        return deleteReceipt;
    }

}







