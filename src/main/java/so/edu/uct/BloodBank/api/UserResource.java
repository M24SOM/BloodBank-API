package so.edu.uct.BloodBank.api;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.config.JwtUtil;
import so.edu.uct.BloodBank.dto.AuthenticationRequest;
import so.edu.uct.BloodBank.model.Role;
import so.edu.uct.BloodBank.model.User;
import so.edu.uct.BloodBank.service.RoleService;
import so.edu.uct.BloodBank.service.UserService;

import java.util.List;

@RestController @RequiredArgsConstructor
//@RequestMapping("/auth")
public class UserResource {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;

    // 1. Get All Users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    // 2. Get User By ID
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    // 3. Get User By ID
    @GetMapping("/user/{username}")
    public ResponseEntity<UserDetails> getUserByUsername(@PathVariable String username){
        return ResponseEntity.ok().body(userService.getUserByUsername(username));
    }


    // 4. Add User
    @PostMapping("/user/add")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return  ResponseEntity.status(201).body(userService.saveUser(user));
    }

    // 5. Change Password

    @PutMapping("/user/{username}")
    public ResponseEntity<User> changePassword(@RequestBody User user, @PathVariable String username) {
        User requestUser =  userService.getUserByUsernameU(username);
        requestUser.setPassword(user.getPassword());
        return ResponseEntity.status(200).body(userService.saveUser(requestUser));
    };

    // 6. Delete User

    @DeleteMapping("user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        User deleteUser = userService.getUserById(id);
        userService.deleteUser(id);
        return ResponseEntity.status(204).build();
    }

    // 7. Login

    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        final UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());

        if (user != null) {
            return ResponseEntity.ok().body(jwtUtil.generateToken(user.getUsername()));
        }
        else {
            return ResponseEntity.status(404).body("Bad Credential, please try again.");
        }

    }

    // 7. Save Role

    @PostMapping("/role/add")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        return  ResponseEntity.status(201).body(roleService.saveRole(role));
    }


    // 8. Add Role To a User
    @PutMapping("/role/addToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleUserForm form){
        roleService.addRoleToUser(form.getUsername(), form.getRoleName());
        return  ResponseEntity.ok().build();

    }


}
@Data
class RoleUserForm {
    private String username;
    private String roleName;
}







