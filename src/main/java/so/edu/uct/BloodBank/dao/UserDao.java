package so.edu.uct.BloodBank.dao;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import so.edu.uct.BloodBank.dto.UserModel;
import so.edu.uct.BloodBank.model.User;
import so.edu.uct.BloodBank.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDao {
    private final UserRepository userRepository;



    public User create(UserModel userModel)
    {
        return userRepository.save(modelToEntity(userModel));
    }

    public User findByUsername(String username)
    {

        return (User) userRepository.findByUsername(username);
    }

    User modelToEntity(UserModel userModel)
    {
        User user = new User();
        user.setId(userModel.getId());
        user.setName(userModel.getName());
        user.setUsername(userModel.getUsername());
        user.setPassword(userModel.getPassword());
        return user;
    }
}