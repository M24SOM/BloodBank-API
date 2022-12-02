package so.edu.uct.BloodBank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.Receipt;
import so.edu.uct.BloodBank.service.ReceiptService;

import java.util.List;

@RestController
//@RequestMapping("/receipt")
public class ReceiptController {
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

    @PostMapping("/receipt")
    public Receipt saveReceipt(@RequestBody Receipt receipt){
        return receiptService.saveReceipt(receipt);
    }

    @PutMapping("/receipt/{id}")
    public Receipt updateReceipt(@RequestBody Receipt receipt, @PathVariable Long id) {
        Receipt UpHospital = receiptService.getReceiptById(id);
        UpHospital.setName(receipt.getName());
        return receiptService.saveReceipt(UpHospital);
    };

    @DeleteMapping("receipt/{id}")
    public Receipt deleteReceipt(@PathVariable Long id){
        Receipt deleteHospital = receiptService.getReceiptById(id);
        receiptService.deleteReceipt(id);
        return deleteHospital;
    }

}







