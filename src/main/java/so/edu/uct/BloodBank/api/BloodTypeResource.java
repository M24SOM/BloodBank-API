package so.edu.uct.BloodBank.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.BloodType;
import so.edu.uct.BloodBank.service.BloodTypeService;
import java.util.List;

@RestController @CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/bloodType")
public class BloodTypeResource {
    @Autowired
    BloodTypeService bloodTypeService;

    // 1. Get All Blood Types
    @GetMapping()
    public List<BloodType> allBloodTypes(){
        return bloodTypeService.getAllBloodTypes();
    }


    // 2. Get Specific Blood Type By ID
    @GetMapping(value = "/{id}")
    public BloodType getBloodType(@PathVariable Long id){
        return bloodTypeService.getBloodTypeById(id);
    }


    // 3. Save Blood Type
    @PostMapping()
    public BloodType saveBloodType(@RequestBody BloodType bloodType){
        return bloodTypeService.saveBloodType(bloodType);
    }

    // 4. Update Specific Blood Type By ID
    @PutMapping(value = "/{id}")
    public BloodType updateBloodType(@RequestBody BloodType bloodType, @PathVariable Long id) {
        BloodType updatedBloodType = bloodTypeService.getBloodTypeById(id);
        updatedBloodType.setName(bloodType.getName());
        return bloodTypeService.saveBloodType(updatedBloodType);
    };

    // 5. Delete Specific Blood Type By ID
    @DeleteMapping(value = "/{id}")
    public BloodType deleteBloodType(@PathVariable Long id){
        BloodType deleteBloodType = bloodTypeService.getBloodTypeById(id);
        bloodTypeService.deleteBloodType(id);
        return deleteBloodType;
    }

}







