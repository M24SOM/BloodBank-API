package so.edu.uct.BloodBank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import so.edu.uct.BloodBank.model.Role;
import so.edu.uct.BloodBank.model.User;
import so.edu.uct.BloodBank.service.RoleService;
import so.edu.uct.BloodBank.service.UserService;

import java.util.ArrayList;

@SpringBootApplication
public class BloodBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloodBankApplication.class, args);
	}


//	@Bean
//	CommandLineRunner run(UserService userService, RoleService roleService){
//
//		return args -> {
//			roleService.saveRole(new Role(null, "ROLE_USER"));
//			roleService.saveRole(new Role(null, "ROLE_ADMIN"));
//			roleService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
//
//
//
//			userService.saveUser(new User(null, "Mohamed Mahad", "mmfarah",
//					"12345", new ArrayList<>()));
//			userService.saveUser(new User(null, "Bakar Mahad", "bakar81",
//					"12345", new ArrayList<>()));
//			userService.saveUser(new User(null, "Omar Mahad", "mazul",
//					"12345", new ArrayList<>()));
//
//
//			roleService.addRoleToUser("mmfarah", "ROLE_USER");
//			roleService.addRoleToUser("mmfarah", "ROLE_ADMIN");
//			roleService.addRoleToUser("mmfarah", "ROLE_SUPER_ADMIN");
//			roleService.addRoleToUser("bakar81", "ROLE_ADMIN");
//			roleService.addRoleToUser("mazul", "ROLE_USER");
//		};
//	}

}
