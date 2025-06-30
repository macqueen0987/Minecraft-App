package mcserver.spring.user.repository;

import mcserver.spring.user.model.DiscordUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscordUserRepository extends JpaRepository<DiscordUser, Long> {
    boolean existsDiscordUsersByDiscordId(Long discordId);

    DiscordUser findByDiscordId(Long discordId);
}
