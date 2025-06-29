package mcserver.spring.user.service;

import java.time.LocalDateTime;
import java.util.Optional;
import mcserver.spring.user.model.DiscordUser;
import mcserver.spring.user.model.Token;
import mcserver.spring.user.repository.DiscordUserRepository;
import mcserver.spring.user.repository.TokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TokenService {
    private final TokenRepository tokenRepository;
    private final DiscordUserRepository discordUserRepository;

    public TokenService(TokenRepository tokenRepository, DiscordUserRepository discordUserRepository) {
        this.tokenRepository = tokenRepository;
        this.discordUserRepository = discordUserRepository;
    }

    @Transactional
    public Token getToken(long discordId) {
        Optional<Token> existingToken = this.tokenRepository.findById(discordId);
        if (existingToken.isPresent()) {
            return (Token)existingToken.get();
        } else {
            DiscordUser user = (DiscordUser)this.discordUserRepository.findById(discordId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
            Token token = new Token(discordId);
            return (Token)this.tokenRepository.save(token);
        }
    }

    @Transactional
    public boolean isValidToken(String tokenValue) {
        Optional<Token> token = Optional.ofNullable(this.tokenRepository.findByToken(tokenValue));
        if (token.isEmpty()) {
            return false;
        } else {
            Token existingToken = (Token)token.get();
            LocalDateTime updatedAt = existingToken.getUpdatedAt();
            LocalDateTime now = LocalDateTime.now();
            return updatedAt != null && now.minusHours(1L).isBefore(updatedAt);
        }
    }
}
