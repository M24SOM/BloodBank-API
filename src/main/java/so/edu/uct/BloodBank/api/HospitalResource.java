package so.edu.uct.BloodBank.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.Hospital;
import so.edu.uct.BloodBank.service.HospitalService;

import java.util.List;

@RestController @CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/hospital")
public class HospitalResource {
    @Autowired
    HospitalService hospitalService;

    // 1. Get All Hospitals

    @GetMapping()
    public List<Hospital> allHospital(){
        return hospitalService.getAllHospitals();
    }

    // 2. Get Specific Hospital By ID

    @GetMapping(value = "/{id}")
    public Hospital getHospital(@PathVariable Long id){
        return hospitalService.getHospitalById(id);
    }
    // 3. Save Hospital


    @PostMapping()
    public Hospital saveHospital(@RequestBody Hospital hospital){
        return hospitalService.saveHospital(hospital);
    }

    // 4. Update Specific Hospital By ID

    @PutMapping(value = "/{id}")
    public Hospital updateHospital(@RequestBody Hospital hospital, @PathVariable Long id) {
        Hospital updatedHospital = hospitalService.getHospitalById(id);
        updatedHospital.setName(hospital.getName());
        updatedHospital.setState(hospital.getState());
        return hospitalService.saveHospital(updatedHospital);
    };
    // 5. Delete Specific Hospital By ID

    @DeleteMapping(value = "/{id}")
    public Hospital deleteHospital(@PathVariable Long id){
        Hospital deleteHospital = hospitalService.getHospitalById(id);
        hospitalService.deleteHospital(id);
        return deleteHospital;
    }

}







