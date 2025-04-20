package skillfactory.DreamTeam.globus.it.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import skillfactory.DreamTeam.globus.it.constants.security.Role;
import skillfactory.DreamTeam.globus.it.dao.entities.auth.PermissionEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.profiles.*;
import skillfactory.DreamTeam.globus.it.dao.repositories.AccountRepository;
import skillfactory.DreamTeam.globus.it.dao.repositories.ProfileRepository;
import skillfactory.DreamTeam.globus.it.dto.auth.WalletUserDetails;
import skillfactory.DreamTeam.globus.it.dto.profile.ProfileCreationRequests;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileService implements UserDetailsService {

    private final ProfileRepository profileRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileEntity create(ProfileCreationRequests.Profile payload) {
        return switch (payload) {
            case ProfileCreationRequests.User req -> profileRepository.save(buildUser(req));
            case ProfileCreationRequests.Person req -> profileRepository.save(buildPerson(req));
            case ProfileCreationRequests.Company req -> profileRepository.save(buildCompany(req));
            default -> throw new IllegalStateException("Unexpected value: " + payload);
        };
    }

    private UserEntity buildUser(ProfileCreationRequests.User request) {
        return UserEntity.builder()
                .name(request.name())
                .inn(request.inn())
                .email(request.email())
                .phone(request.phone())
                .account(
                        AccountEntity.builder()
                                .login(request.login())
                                .password(passwordEncoder.encode(request.password()))
                                .build()
                )
                .build();
    }

    private PersonEntity buildPerson(ProfileCreationRequests.Person request) {
        return PersonEntity.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .middleName(request.middleName())
                .inn(request.inn())
                .email(request.email())
                .phone(request.phone())
                .build();
    }

    private CompanyEntity buildCompany(ProfileCreationRequests.Company request) {
        return CompanyEntity.builder()
                .name(request.name())
                .inn(request.inn())
                .email(request.email())
                .phone(request.phone())
                .build();
    }

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
                .orElseThrow(() -> new UsernameNotFoundException("Error! The user with login " + username + " not found"));
    }
}
