package mcserver.spring.user.service;

import java.util.Optional;
import mcserver.spring.user.dto.user.UserRequest;
import mcserver.spring.user.model.DiscordUser;
import mcserver.spring.user.repository.DiscordUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiscordUserService {
    private final DiscordUserRepository userRepository;

    public DiscordUserService(DiscordUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public DiscordUser register(UserRequest request) {
        if (this.userRepository.existsDiscordUsersByDiscordId(request.discordId())) {
            throw new IllegalArgumentException("이미 등록된 Discord ID입니다.");
        } else {
            DiscordUser user = new DiscordUser();
            user.setDiscordId(request.discordId());
            user.setUserName(request.userName());
            return (DiscordUser)this.userRepository.save(user);
        }
    }

    @Transactional
    public DiscordUser updateDiscordUser(UserRequest request) {
        Optional<DiscordUser> existingUserOpt = this.userRepository.findById(request.discordId());
        DiscordUser user = (DiscordUser)existingUserOpt.orElseGet(() -> this.register(request));
        user.setUserName(request.userName());
        user.setMinecraftName(request.minecraftName());
        return (DiscordUser)this.userRepository.save(user);
    }

    @Transactional(
            readOnly = true
    )
    public DiscordUser getById(Long id) {
        return (DiscordUser)this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 ID의 유저가 존재하지 않습니다."));
    }
}
