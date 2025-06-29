package mcserver.spring.user.dto.token;

import mcserver.spring.user.model.Token;

public record TokenResult(String token) {
    public static TokenResult from(Token token) {
        return new TokenResult(token.getToken());
    }
}
