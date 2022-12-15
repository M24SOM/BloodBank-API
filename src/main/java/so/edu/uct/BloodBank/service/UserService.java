package so.edu.uct.BloodBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import so.edu.uct.BloodBank.model.User;
import so.edu.uct.BloodBank.repository.UserRepository;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    // 1. Get All Users


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // 2. Get Specific User By ID

    public User getUserById(Long id){
        return userRepository.findById(id).get();
    }

    // 3. Get Specific User By username

    public User getUserByUsername(String username){
       return userRepository.findByUsername(username);
    }

    // 4. Save User
    public User saveUser(User user){
        return userRepository.save(user);
    }

    // 5. Delete Specific User By ID

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
