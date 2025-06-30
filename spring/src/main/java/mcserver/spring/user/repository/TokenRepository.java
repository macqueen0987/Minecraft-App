package mcserver.spring.user.repository;

import mcserver.spring.user.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    boolean existsByDiscordId(Long discordId);

    boolean existsByToken(String token);

    Token findByToken(String token);
}
