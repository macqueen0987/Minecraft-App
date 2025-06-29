package mcserver.spring.user.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(@NotNull Long discordId, @NotBlank String userName, String minecraftName) {
}
