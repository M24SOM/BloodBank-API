package so.edu.uct.BloodBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import so.edu.uct.BloodBank.model.State;
import so.edu.uct.BloodBank.repository.StateRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StateService {
    @Autowired
    StateRepository stateRepository;

    // 1. Get All States

    public List<State> getAllStates(){
        return stateRepository.findAll();
    }

    // 2. Get Specific State By ID

    public State getStateById(Long id){
        Optional<State> getStateById = stateRepository.findById(id);
        if (getStateById.isEmpty()){
            return null;
        }
        return stateRepository.findById(id).get();
    }
    // 3. Save State

    public State saveState(State state){
        return stateRepository.save(state);
    }


    // 4. Delete Specific State By ID

    public void deleteState(Long id){
        stateRepository.deleteById(id);
    }
}
