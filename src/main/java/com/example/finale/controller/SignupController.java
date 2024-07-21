
package com.example.finale.controller;

import com.example.finale.dto.ResetPassword;
import com.example.finale.dto.SignupDTO;
import com.example.finale.dto.UserDTO;
import com.example.finale.entities.Commande;
import com.example.finale.entities.PasswordResetToken;
import com.example.finale.entities.User;
import com.example.finale.service.jwt.user.PasswordResetTokenService;
import com.example.finale.service.jwt.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
public class SignupController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordResetTokenService tokenService;
    @Autowired
    private JavaMailSender emailSender;


    @CrossOrigin(origins = "http://localhost:4200")

    @PostMapping("/sign-up")
    public ResponseEntity<?> signupUser(@RequestBody SignupDTO signupDTO) {


        if (userService.hasUserWithEmail(signupDTO.getEmail().toString())) {
            return new ResponseEntity<>("User already exist", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDTO createUser = userService.createUser(signupDTO);
        if (createUser == null) {
            return new ResponseEntity<>("user not created , Come later !" , HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(createUser,HttpStatus.CREATED);


    }




    @PostMapping("/forgot-password")

    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> emailMap) {
        User user = userService.getUserByEmail(emailMap.get("email"));
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailMap.get("email"));
        System.out.println(emailMap.get("email"));
        PasswordResetToken token = tokenService.createToken(user);

        // Créer une instance de PasswordResetResponse et définir l'e-mail et le token
        ResetPassword response = new ResetPassword();
        response.setEmail(emailMap.get("email"));
        response.setToken(token.getToken());

        mailMessage.setSubject("Reset Password ");
        mailMessage.setFrom("zakaria.bensalem@etudiant-fst.utm.tn");
        mailMessage.setText("To reset your password, please click on the link below:\n\n"
                + "http://localhost:4200/#/reset_password\n\n"
                + "With Token: " + token.getToken());
        emailSender.send(mailMessage);



        return ResponseEntity.ok().body(response);

    }


    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> resetRequest) {
        String token = resetRequest.get("token");
        String newPassword = resetRequest.get("newPassword");

        System.out.println(token);

        PasswordResetToken resetToken = tokenService.findByToken(token);
        System.out.println(resetToken);

        if (resetToken == null ) {
            return new ResponseEntity<>("Invalid or expired token", HttpStatus.BAD_REQUEST);
        }

        User user = resetToken.getUser();
        String encodedPassword = new BCryptPasswordEncoder().encode(newPassword);
        userService.updatePassword(user, encodedPassword);
        System.out.println(user);

        tokenService.deleteToken(resetToken);

        return new ResponseEntity<>("Password reset successfully", HttpStatus.OK);
    }



}

