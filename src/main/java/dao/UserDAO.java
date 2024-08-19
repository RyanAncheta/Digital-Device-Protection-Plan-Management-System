package dao;

import model.RegisteredProduct;
import model.User;

import java.util.List;

public interface UserDAO {
    User validateUser(String username, String password);
    void registerUser(User user);
    List<RegisteredProduct> getUserDevices(int userId); 
    List<User> searchUsers(String query);
    List<User> getAllUsers();
    User getUserById(int userId);
    boolean isUsernameTaken(String username);
    boolean isEmailTaken(String email);
}
