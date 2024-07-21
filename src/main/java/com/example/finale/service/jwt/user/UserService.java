package com.example.finale.service.jwt.user;

import com.example.finale.dto.SignupDTO;
import com.example.finale.dto.UserDTO;
import com.example.finale.entities.Commande;
import com.example.finale.entities.User;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface UserService {
    UserDTO createUser(SignupDTO signupDTO);
    boolean hasUserWithEmail(String email);
    User getUserByEmail(String email); // Ajoutez cette méthode
    void updatePassword(User user, String newPassword);
    public List<User> getAllUsers();
    public User getUserById(Long userId);
    public boolean isUserAdmin(Long userId) ;


}
