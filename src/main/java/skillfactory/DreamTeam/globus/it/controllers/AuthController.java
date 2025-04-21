package skillfactory.DreamTeam.globus.it.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import skillfactory.DreamTeam.globus.it.dto.auth.SignInRequest;
import skillfactory.DreamTeam.globus.it.dto.auth.Token;
import skillfactory.DreamTeam.globus.it.dto.profile.ProfileCreationRequests;
import skillfactory.DreamTeam.globus.it.services.ProfileService;
import skillfactory.DreamTeam.globus.it.services.auth.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final ProfileService profileService;

    @PostMapping("/signIn")
    public Token signIn(@RequestBody SignInRequest request) {
        return authService.signIn(request);
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String token) {
        if (token == null) return;
        authService.logout(token.substring(7));
    }

    @PostMapping("/signUp")
    public Token signUp(@RequestBody ProfileCreationRequests.CreateUser request) {
        profileService.createUser(request);
        return authService.signIn(new SignInRequest(request.login(), request.password()));
    }



}
