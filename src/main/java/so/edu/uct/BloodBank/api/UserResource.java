package so.edu.uct.BloodBank.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.dto.LoginRequest;
import so.edu.uct.BloodBank.model.User;
import so.edu.uct.BloodBank.service.UserService;

import java.util.List;
import java.util.Objects;

@RestController @CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserResource {
    @Autowired
    UserService userService;

    // 1. Get All Users

    @GetMapping()
    public List<User> allUsers(){
        return userService.getAllUsers();
    }


    // 2. Get Specific User By ID

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }


    // 3. Save User

    @PostMapping()
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    // 4. Update Specific User By ID

    @PutMapping(value = "/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) {
        User updatedUser = userService.getUserById(id);
        updatedUser.setPassword(user.getPassword());
        return userService.saveUser(updatedUser);
    };

    // 5. Delete Specific User By ID

    @DeleteMapping(value = "/{id}")
    public User deleteUser(@PathVariable Long id){
        User deletedUser = userService.getUserById(id);
        userService.deleteUser(id);
        return deletedUser;
    }

    // 6. Login
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        User user = userService.getUserByUsername(loginRequest.getUsername());
        if (Objects.equals(user.getPassword(), loginRequest.getPassword())) {
            return ResponseEntity.ok().body(user) ;
        } else {
            return ResponseEntity.badRequest().build();
        }


    }

}







