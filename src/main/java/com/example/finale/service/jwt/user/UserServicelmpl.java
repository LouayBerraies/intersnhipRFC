package com.example.finale.service.jwt.user;

import com.example.finale.dto.SignupDTO;
import com.example.finale.dto.UserDTO;
import com.example.finale.entities.Commande;
import com.example.finale.entities.User;
import com.example.finale.enums.UserRole;
import com.example.finale.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class UserServicelmpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(SignupDTO signupDTO) {
        User user = new User();
        user.setName(signupDTO.getName());
        user.setEmail(signupDTO.getEmail());
        user.setUserRole(UserRole.USER);
        user.setPassword(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));
        User createdUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(createdUser.getId());
        userDTO.setName(createdUser.getName());
        userDTO.setEmail(createdUser.getEmail());
        userDTO.setUserRole(createdUser.getUserRole());
        return userDTO;
    }
    @Override
    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }




    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email) != null ;
    }
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findFirstByEmail(email); // Assurez-vous que cette méthode est implémentée dans UserRepository
    }
    @Override
    public User getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null);
    }
    @Override
    public boolean isUserAdmin(Long userId) {
        User user = getUserById(userId);
        if (user != null) {
            System.out.println("User Role: " + user.getUserRole());
            return user.getUserRole().equalsIgnoreCase("ADMIN"); // Utilise equalsIgnoreCase pour ignorer la casse
        } else {
            System.out.println("User not found with ID: " + userId);
            return false;
        }
    }



    @Override
    public void updatePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        userRepository.save(user);
    }
}

