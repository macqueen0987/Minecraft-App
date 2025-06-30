package mcserver.spring.user.controller;

import mcserver.spring.common.annotation.RequireInternalHeader;
import mcserver.spring.user.dto.token.TokenResult;
import mcserver.spring.user.dto.user.UserRequest;
import mcserver.spring.user.dto.user.UserResult;
import mcserver.spring.user.model.DiscordUser;
import mcserver.spring.user.model.Token;
import mcserver.spring.user.service.DiscordUserService;
import mcserver.spring.user.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/users"})
@RequireInternalHeader
public class DiscordUserController {
    private final DiscordUserService userService;
    private final TokenService tokenService;

    public DiscordUserController(DiscordUserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping({"/register"})
    public ResponseEntity<UserResult> register(@RequestBody UserRequest request) {
        DiscordUser user = this.userService.register(request);
        return ResponseEntity.ok(UserResult.from(user));
    }

    @PostMapping({"/update"})
    public ResponseEntity<UserResult> update(@RequestBody UserRequest request) {
        DiscordUser user = this.userService.updateDiscordUser(request);
        return ResponseEntity.ok(UserResult.from(user));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<UserResult> getUser(@PathVariable Long id) {
        DiscordUser user = this.userService.getById(id);
        return ResponseEntity.ok(UserResult.from(user));
    }

    @PostMapping({"/{id}/token"})
    public ResponseEntity<TokenResult> updateToken(@PathVariable Long id) {
        Token token = this.tokenService.getToken(id);
        return ResponseEntity.ok(TokenResult.from(token));
    }
}
