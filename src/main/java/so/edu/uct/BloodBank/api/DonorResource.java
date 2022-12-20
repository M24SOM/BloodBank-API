package so.edu.uct.BloodBank.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.dto.DonorDTO;
import so.edu.uct.BloodBank.model.*;
import so.edu.uct.BloodBank.service.BloodTypeService;
import so.edu.uct.BloodBank.service.DonationService;
import so.edu.uct.BloodBank.service.DonorService;
import so.edu.uct.BloodBank.service.StateService;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController @CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/donor")
public class DonorResource {
    @Autowired
    DonorService donorService;


    @Autowired
    BloodTypeService bloodTypeService;


    @Autowired
    StateService stateService;

    @Autowired
    DonationService donationService;




    // 1. Get All Donors

    @GetMapping()
    public List<Donor> allDonor(){
        return donorService.getAllDonors();
    }

    // 2. Get Specific Donor By ID

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getDonor(@PathVariable Long id){
        Donor getDonor = donorService.getDonorById(id);
        if (getDonor == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Donor Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok().body(donorService.getDonorById(id));
    }

    // 3. Get Specific Donor By phone

    @GetMapping(value = "/phone/{phone}")
    public ResponseEntity<?> getDonorByPhone(@PathVariable String phone){
        List<Donor> getDonorByPhone = donorService.getDonorByMobileNo(phone);
        if (getDonorByPhone == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Donor Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok().body(donorService.getDonorByMobileNo(phone));
    }

    // 4. Save Donor

    @PostMapping(value = "/add")
    public ResponseEntity<?> saveDonor(@RequestBody Donor donor){
        BloodType findBloodType = bloodTypeService.getBloodTypeById(donor.getBloodType().getId());
        State findState = stateService.getStateById(donor.getState().getId());
        if (findState == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "State Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        if (findBloodType == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "BloodType Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        if (Objects.equals(donor.getBloodType().getId(), findBloodType.getId()) && Objects.equals(donor.getState().getId(), findState.getId())
        && donor.getDateBirth() != null && donor.getIsHealthy() != null){
            return ResponseEntity.ok().body(donorService.saveDonor(donor));
        } else {
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "BloodType Or State Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
    }


    // 5. Update Specific Donor By ID

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateDonor(@RequestBody Donor donor, @PathVariable Long id) {
        Donor updatedDonor = donorService.getDonorById(id);
        if (updatedDonor == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Donor Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        BloodType findBloodType = bloodTypeService.getBloodTypeById(donor.getBloodType().getId());
        State findState = stateService.getStateById(donor.getState().getId());
        if (findState == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "State Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        if (findBloodType == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "BloodType Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        if (Objects.equals(donor.getBloodType().getId(), findBloodType.getId()) && Objects.equals(donor.getState().getId(), findState.getId())){
            // Date
            java.util.Date date = new java.util.Date();

            updatedDonor.setName(donor.getName());
            updatedDonor.setBloodType(donor.getBloodType());
            updatedDonor.setDateBirth(donor.getDateBirth());
            updatedDonor.setWeight(donor.getWeight());
            updatedDonor.setMobileNo(donor.getMobileNo());
            updatedDonor.setState(donor.getState());
            updatedDonor.setIsHealthy(donor.getIsHealthy());
            updatedDonor.setUpdatedAt(new Date(date.getDate()));
            return ResponseEntity.ok().body(donorService.saveDonor(updatedDonor));
        } else {
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "BloodType Or State Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }

    };

    // 6. Delete Specific Donor By ID
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteDonor(@PathVariable Long id){
        Donor deleteDonor = donorService.getDonorById(id);
        if (deleteDonor == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Donor Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        List<Donation> findDonor = donationService.getDonorPerDonation(deleteDonor.getId().toString());
        if (findDonor == null){
            donorService.deleteDonor(id);
            return ResponseEntity.ok().body(deleteDonor);
        } else {
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Donor is Linked To This Donation");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }



    }

    // 7. Get Donor Per State

    @GetMapping(value = "/state/{state}")
    public ResponseEntity<?> getAllDonorPerState(@PathVariable String state){
        List<Donor> getAllDonorPerState = donorService.getDonorByState(state);
        if (getAllDonorPerState == null){
            HashMap<String, String> response = new HashMap<>();
            // Add Keys and Values
            response.put("status", "404");
            response.put("message", "Donor Not Found");
            System.out.println(response);
            return ResponseEntity.status(404).body(response);
        }
        List<DonorDTO> donorDTOS =  getAllDonorPerState.stream()
                .map(donors -> {
                    DonorDTO donorDTO = new DonorDTO();
                    donorDTO.setId(donors.getId());
                    donorDTO.setName(donors.getName());
                    donorDTO.setDateBirth(donors.getDateBirth());
                    donorDTO.setWeight(donors.getWeight());
                    donorDTO.setMobileNo(donors.getMobileNo());
                    donorDTO.setBloodType(donors.getBloodType().getName());
                    donorDTO.setState(donors.getState().getName());
                    donorDTO.setIsHealthy(donors.getIsHealthy());
                    donorDTO.setCreatedAt(donors.getCreatedAt().toString());
                    donorDTO.setUpdatedAt(donors.getUpdatedAt().toString());
                    return donorDTO;
                } ).collect(Collectors.toList());

        return ResponseEntity.ok().body(donorDTOS);
    }

}







