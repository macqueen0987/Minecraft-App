package mcserver.spring.user.dto.user;

import mcserver.spring.user.model.DiscordUser;

public record UserResult(Long discordId, String userName, String minecraftName) {
    public static UserResult from(DiscordUser user) {
        return new UserResult(user.getDiscordId(), user.getUserName(), user.getMinecraftName());
    }
}
