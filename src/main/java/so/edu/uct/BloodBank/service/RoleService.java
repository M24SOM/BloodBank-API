package so.edu.uct.BloodBank.service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import so.edu.uct.BloodBank.model.Role;
import so.edu.uct.BloodBank.model.User;
import so.edu.uct.BloodBank.repository.RoleRepository;
import so.edu.uct.BloodBank.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional @Slf4j
public class RoleService {
    private final UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;


    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }


    public Role getRoleById(Long id){
        return (Role) roleRepository.findById(id).get();
    }

    public Role saveRole(Role role){
        return roleRepository.save(role);
    }

    public void addRoleToUser(String username,String roleName){
        User user =  userRepository.findByUsername(username);
        Role role =  roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    public void deleteRole(Long id){
        roleRepository.deleteById(id);
    }
}
