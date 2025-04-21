package skillfactory.DreamTeam.globus.it.services.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import skillfactory.DreamTeam.globus.it.constants.security.Role;
import skillfactory.DreamTeam.globus.it.dao.entities.auth.PermissionEntity;
import skillfactory.DreamTeam.globus.it.dao.repositories.AccountRepository;
import skillfactory.DreamTeam.globus.it.dto.auth.WalletUserDetails;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsProvider implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByLoginIgnoreCase(username.trim())
                .map(account -> WalletUserDetails.builder()
                        .userId(account.getUser().getId())
                        .accountId(account.getId())
                        .authorities(account.getPermissions().stream().map(PermissionEntity::getRole).map(Role::name).map(SimpleGrantedAuthority::new).collect(Collectors.toSet()))
                        .username(account.getLogin())
                        .password(account.getPassword())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Error! The user with signIn " + username + " not found"));
    }
}
