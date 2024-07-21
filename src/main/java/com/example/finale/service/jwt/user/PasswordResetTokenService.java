package com.example.finale.service.jwt.user;

import com.example.finale.entities.PasswordResetToken;
import com.example.finale.entities.User;

public interface PasswordResetTokenService {
    PasswordResetToken createToken(User user);
    PasswordResetToken findByToken(String token);
    void deleteToken(PasswordResetToken token);
}