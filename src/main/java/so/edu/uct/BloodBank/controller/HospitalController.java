package so.edu.uct.BloodBank.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.Hospital;
import so.edu.uct.BloodBank.service.HospitalService;

import java.util.List;

@RestController
//@RequestMapping("/hospital")
public class HospitalController {
    @Autowired
    HospitalService hospitalService;

    @GetMapping("/hospital")
    public List<Hospital> allHospital(){
        return hospitalService.getAllHospitals();
    }

    @GetMapping("/hospital/{id}")
    public Hospital getHospital(@PathVariable Long id){
        return hospitalService.getHospitalById(id);
    }

    @PostMapping("/hospital")
    public Hospital saveHospital(@RequestBody Hospital hospital){
        return hospitalService.saveHospital(hospital);
    }

    @PutMapping("/hospital/{id}")
    public Hospital updateHospital(@RequestBody Hospital hospital, @PathVariable String id) {
        Hospital UpHospital = hospitalService.getHospitalById(Long.valueOf(id));
        UpHospital.setName(hospital.getName());
        return hospitalService.saveHospital(UpHospital);
    };

    @DeleteMapping("hospital/{id}")
    public Hospital deleteHospital(@PathVariable Long id){
        Hospital deleteHospital = hospitalService.getHospitalById(id);
        hospitalService.deleteHospital(id);
        return deleteHospital;
    }

}







