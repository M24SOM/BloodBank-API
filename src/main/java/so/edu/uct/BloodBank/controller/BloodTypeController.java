package so.edu.uct.BloodBank.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.BloodType;
import so.edu.uct.BloodBank.service.BloodTypeService;

import java.util.List;

@RestController
//@RequestMapping("/bloodType")
public class BloodTypeController {
    @Autowired
    BloodTypeService bloodTypeService;

    @GetMapping("/bloodType")
    public List<BloodType> allBloodTypes(){
        return bloodTypeService.getAllBloodTypes();
    }

    @GetMapping("/bloodType/{id}")
    public BloodType getBloodType(@PathVariable Long id){
        return bloodTypeService.getBloodTypeById(id);
    }

    @PostMapping("/bloodType")
    public BloodType saveBloodType(@RequestBody BloodType bloodType){
        return bloodTypeService.saveBloodType(bloodType);
    }

    @PutMapping("/bloodType/{id}")
    public BloodType updateBloodType(@RequestBody BloodType bloodType, @PathVariable Long id) {
        BloodType UpBloodType = bloodTypeService.getBloodTypeById(id);
        UpBloodType.setName(bloodType.getName());
        return bloodTypeService.saveBloodType(UpBloodType);
    };

    @DeleteMapping("bloodType/{id}")
    public BloodType deleteBloodType(@PathVariable Long id){
        BloodType deleteBloodType = bloodTypeService.getBloodTypeById(id);
        bloodTypeService.deleteBloodType(id);
        return deleteBloodType;
    }

}







