package skillfactory.DreamTeam.globus.it.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import skillfactory.DreamTeam.globus.it.dao.entities.profiles.*;
import skillfactory.DreamTeam.globus.it.dao.repositories.ProfileRepository;
import skillfactory.DreamTeam.globus.it.dao.repositories.UserRepository;
import skillfactory.DreamTeam.globus.it.dto.profile.ProfileCreationRequests;
import skillfactory.DreamTeam.globus.it.exceptions.UserAlreadyExistsException;

import java.util.Optional;
import java.util.function.Predicate;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity createUser(ProfileCreationRequests.CreateUser request) {
        assertUniqueUser(request.login());
        return profileRepository.save(
                UserEntity.builder()
                        .name(request.name())
                        .inn(request.inn())
                        .email(Optional.ofNullable(request.email()).filter(Predicate.not(String::isBlank)).orElse(request.login().toLowerCase().trim()))
                        .phone(request.phone())
                        .account(
                                AccountEntity.builder()
                                        .login(request.login())
                                        .password(passwordEncoder.encode(request.password()))
                                        .build()
                        )
                        .build()
        );
    }

    private void assertUniqueUser(String login) {
        if (userRepository.existsByAccountLoginIgnoreCase(login)) {
            throw new UserAlreadyExistsException("User with login " + login + " already exists");
        }
    }

    public PersonEntity createPerson(ProfileCreationRequests.CreatePerson request) {
        return profileRepository.save(
                PersonEntity.builder()
                        .firstName(request.firstName())
                        .lastName(request.lastName())
                        .middleName(request.middleName())
                        .inn(request.inn())
                        .email(request.email())
                        .phone(request.phone())
                        .build()
        );
    }

    public CompanyEntity createCompany(ProfileCreationRequests.CreateCompany request) {
        return profileRepository.save(
                CompanyEntity.builder()
                        .name(request.name())
                        .inn(request.inn())
                        .email(request.email())
                        .phone(request.phone())
                        .build()
        );
    }
}
