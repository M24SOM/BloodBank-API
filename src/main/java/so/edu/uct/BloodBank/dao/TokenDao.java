package so.edu.uct.BloodBank.dao;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import so.edu.uct.BloodBank.model.Token;
import so.edu.uct.BloodBank.model.User;
import so.edu.uct.BloodBank.repository.TokenRepository;
import so.edu.uct.BloodBank.repository.UserRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenDao {

    @Value("${app.jwt_refresh_token_expire_ms}")
    private Long refreshTokenDurationMs;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;


    public TokenDao(TokenRepository tokenRepository, UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    public Optional<Token> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public Token createRefreshToken(Long userId) {
        Token refreshToken = new Token();
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("Resource not found with "+userId));
        refreshToken.setUser(user);
        refreshToken.setExpiry(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        return tokenRepository.save(refreshToken);
    }

}