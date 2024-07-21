package com.example.finale.service.jwt.user;

import com.example.finale.entities.PasswordResetToken;
import com.example.finale.entities.User;
import com.example.finale.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
    private static final int EXPIRATION_TIME_IN_MINUTES = 30;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Override
    public PasswordResetToken createToken(User user) {
        PasswordResetToken token = new PasswordResetToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(calculateExpiryDate());
        return tokenRepository.save(token);
    }

    @Override
    public PasswordResetToken findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public void deleteToken(PasswordResetToken token) {
        tokenRepository.delete(token);
    }

    private Date calculateExpiryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME_IN_MINUTES);
        return calendar.getTime();
    }

    // Implémentez les autres méthodes du service
}
