package so.edu.uct.BloodBank.api;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import so.edu.uct.BloodBank.config.JwtUtils;
import so.edu.uct.BloodBank.dao.TokenDao;
import so.edu.uct.BloodBank.dao.UserDao;
import so.edu.uct.BloodBank.dto.LoginRequest;
import so.edu.uct.BloodBank.entites.UserModel;
import so.edu.uct.BloodBank.model.Role;
import so.edu.uct.BloodBank.model.Token;
import so.edu.uct.BloodBank.model.User;
import so.edu.uct.BloodBank.repository.UserRepository;
import so.edu.uct.BloodBank.service.RoleService;
import so.edu.uct.BloodBank.service.UserService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController @RequiredArgsConstructor @CrossOrigin(origins = "*", maxAge = 3600)
//@RequestMapping("/auth")
public class UserResource {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    UserDao userDao;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    TokenDao tokenDao;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    // 1. Get All Users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    // 2. Get User By ID
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok().body( userService.getUserById(id));
    }

    // 3. Get User By ID
    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username){
        System.out.println(userService.getUserByUsernameU(username));
        return ResponseEntity.ok().body(userService.getUserByUsernameU(username));
    }


    // 4. Add User
    @PostMapping("/user/Register")
    public @ResponseBody Object register(@RequestBody UserModel userModel)
    {
        if (userRepository.findByUsername(userModel.getUsername()) == null) {
            userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
            return userDao.create(userModel);
        }
        Map<String, String> lhm = new LinkedHashMap<>();
        lhm.put("message", "User with this username already exists!");

        return ResponseEntity.status(400).body(lhm);
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


    @PostMapping("/user/Login")
    public  ResponseEntity<?> login(@RequestBody LoginRequest loginRequest)
    {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = (User) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie((User) userDetails);
        Token refreshToken = tokenDao.createRefreshToken(user.getId());
        String accessToken = jwtUtils.generateTokenFromUsername(user.getUsername());
        ResponseCookie jwtRefreshCookie = jwtUtils.generateRefreshJwtCookie(refreshToken.getToken());
        return  ResponseEntity.ok().body("accessToken");
//        return ResponseEntity.ok()
//                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
//                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
//                .body(accessToken);
    }

    // 7. Save Role

    @PostMapping("/role/add")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        return  ResponseEntity.status(201).body(roleService.saveRole(role));
    }


    // 8. Add Role To a User
//    @PutMapping("/role/addToUser")
//    public ResponseEntity<?> addRoleToUser(@RequestBody RoleUserForm form){
//        roleService.addRoleToUser(form.getUsername(), form.getRoleName());
//        return  ResponseEntity.ok().build();
//
//    }


}
@Data
class RoleUserForm {
    private String username;
    private String roleName;
}







