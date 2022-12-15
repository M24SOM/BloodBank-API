package so.edu.uct.BloodBank.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.State;
import so.edu.uct.BloodBank.service.StateService;

import java.util.List;
@RestController @CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/state")
public class StateResource {
    @Autowired
    StateService stateService;

    // 1. Get All States

    @GetMapping()
    public List<State> allStates(){
        return stateService.getAllStates();
    }


    // 2. Get Specific State By ID

    @GetMapping(value = "/{id}")
    public State getState(@PathVariable Long id){
        return stateService.getStateById(id);
    }


    // 3. Save State

    @PostMapping()
    public State saveState(@RequestBody State state){
        return stateService.saveState(state);
    }

    // 4. Update Specific State By ID

    @PutMapping(value = "/{id}")
    public State updateState(@RequestBody State state, @PathVariable Long id) {
        State updatedState = stateService.getStateById(id);
        updatedState.setName(state.getName());
        return stateService.saveState(updatedState);
    };

    // 5. Delete Specific State By ID

    @DeleteMapping(value = "/{id}")
    public State deleteState(@PathVariable Long id){
        State deleteState = stateService.getStateById(id);
        stateService.deleteState(id);
        return deleteState;
    }

}







