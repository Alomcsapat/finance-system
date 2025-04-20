package skillfactory.DreamTeam.globus.it.services.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import skillfactory.DreamTeam.globus.it.dto.auth.WalletUserDetails;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationProvider authenticationProvider;

    private final JwtService jwtService;

    public String login(String email, String password) {
        Authentication authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(email.toLowerCase(), password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtService.generateJwtToken(authentication);
    }

    public void logout(String accessToken) {
        jwtService.blockToken(accessToken);
        String email = jwtService.getEmailFromJwtToken(accessToken);
    }

}
