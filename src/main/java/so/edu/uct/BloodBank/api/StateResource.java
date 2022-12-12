package so.edu.uct.BloodBank.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.model.State;
import so.edu.uct.BloodBank.service.StateService;

import java.util.List;
@RestController
//@RequestMapping("/state")
public class StateResource {
    @Autowired
    StateService stateService;

    @GetMapping("/state")
    public List<State> allStates(){
        return stateService.getAllStates();
    }

    @GetMapping("/state/{id}")
    public State getState(@PathVariable Long id){
        return stateService.getStateById(id);
    }

    @PostMapping("/state")
    public State saveState(@RequestBody State state){
        return stateService.saveState(state);
    }

    @PutMapping("/state/{id}")
    public State updateState(@RequestBody State state, @PathVariable Long id) {
        State Upstate = stateService.getStateById(id);
        Upstate.setName(state.getName());
        return stateService.saveState(Upstate);
    };

    @DeleteMapping("state/{id}")
    public State deleteState(@PathVariable Long id){
        State deleteState = stateService.getStateById(id);
        stateService.deleteState(id);
        return deleteState;
    }

}







