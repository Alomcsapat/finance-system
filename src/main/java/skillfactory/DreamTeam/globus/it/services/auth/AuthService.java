package skillfactory.DreamTeam.globus.it.services.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import skillfactory.DreamTeam.globus.it.dto.auth.SignInRequest;
import skillfactory.DreamTeam.globus.it.dto.auth.Token;
import skillfactory.DreamTeam.globus.it.dto.auth.WalletUserDetails;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationProvider authenticationProvider;

    private final JwtService jwtService;

    public Token signIn(SignInRequest request) {
        Authentication authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(request.email().trim().toLowerCase(), request.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        WalletUserDetails userDetails = (WalletUserDetails) authentication.getPrincipal();
        return Token.builder()
                .token(jwtService.generateJwtToken(authentication))
                .accountId(userDetails.getAccountId())
                .userId(userDetails.getUserId())
                .build();
    }

    public void logout(String accessToken) {
        jwtService.blockToken(accessToken);
        String email = jwtService.getEmailFromJwtToken(accessToken);
    }

}
