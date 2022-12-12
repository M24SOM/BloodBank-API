package so.edu.uct.BloodBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import so.edu.uct.BloodBank.model.User;
import so.edu.uct.BloodBank.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    public User getUserById(Long id){
        return userRepository.findById(id).get();
    }


    public UserDetails getUserByUsername(String username){
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User not Found in The Database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return  new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }


    public User getUserByUsernameU(String username){
       return  userRepository.findByUsername(username);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
