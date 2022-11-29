package so.edu.uct.BloodBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import so.edu.uct.BloodBank.model.State;
import so.edu.uct.BloodBank.repository.StateRepository;

import java.util.List;
@Service
public class StateService {
    @Autowired
    StateRepository stateRepository;

    public List<State> getAllStates(){
        return stateRepository.findAll();
    }


    public State getStateById(Long id){
        return stateRepository.findById(id).get();
    }

    public State saveState(State state){
        return stateRepository.save(state);
    }

    public void deleteState(Long id){
        stateRepository.deleteById(id);
    }
}
