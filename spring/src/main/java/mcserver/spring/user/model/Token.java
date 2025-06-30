package mcserver.spring.user.model;

import jakarta.persistence.*;
import lombok.*;
import mcserver.spring.common.model.BaseEntity;
import mcserver.spring.user.util.TokenGenerator;

@Entity
@Table(name = "tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Token extends BaseEntity {
    @Id
    @Column(name = "discord_id", nullable = false, unique = true, updatable = false)
    private Long discordId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "discord_id")
    private DiscordUser user;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    public Token(long discordId) {
        this.discordId = discordId;
        this.token = TokenGenerator.generateToken(12);
    }
}
