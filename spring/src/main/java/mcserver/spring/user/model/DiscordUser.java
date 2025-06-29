package mcserver.spring.user.model;

import jakarta.persistence.*;
import lombok.*;
import mcserver.spring.common.model.BaseEntity;

@Entity
@Table(name = "discord_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscordUser extends BaseEntity {
    @Id
    @Column(name = "discord_id", nullable = false, unique = true, updatable = false)
    private Long discordId;

    @Column(name = "discord_name", nullable = false)
    private String userName;

    @Column(name = "minecraft_name", nullable = true)
    private String minecraftName;
}
