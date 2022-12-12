package so.edu.uct.BloodBank.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.Receipt;
import so.edu.uct.BloodBank.service.ReceiptService;

import java.util.List;

@RestController
//@RequestMapping("/receipt")
public class ReceiptResource {
    @Autowired
    ReceiptService receiptService;

    @GetMapping("/receipt")
    public List<Receipt> allReceipt(){
        return receiptService.getAllReceipts();
    }

    @GetMapping("/receipt/{id}")
    public Receipt getReceipt(@PathVariable Long id){
        return receiptService.getReceiptById(id);
    }

    @GetMapping("/receipt/phone/{phone}")
    public List<Receipt> getReceiptByPhone(@PathVariable String phone){
        return receiptService.getReceiptByPhone(phone);
    }

    @PostMapping("/receipt")
    public Receipt saveReceipt(@RequestBody Receipt receipt){
        return receiptService.saveReceipt(receipt);
    }

    @PutMapping("/receipt/{id}")
    public Receipt updateReceipt(@RequestBody Receipt receipt, @PathVariable Long id) {
        Receipt UpReceipt = receiptService.getReceiptById(id);
        UpReceipt.setName(receipt.getName());
        UpReceipt.setDateBirth(receipt.getDateBirth());
        UpReceipt.setMobileNo(receipt.getMobileNo());
        UpReceipt.setBloodType(receipt.getBloodType());
        UpReceipt.setState(receipt.getState());
        return receiptService.saveReceipt(UpReceipt);
    };

    @DeleteMapping("receipt/{id}")
    public Receipt deleteReceipt(@PathVariable Long id){
        Receipt deleteReceipt = receiptService.getReceiptById(id);
        receiptService.deleteReceipt(id);
        return deleteReceipt;
    }

}







