package skillfactory.DreamTeam.globus.it.services;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import skillfactory.DreamTeam.globus.it.dao.entities.bank.BankEntity;
import skillfactory.DreamTeam.globus.it.dao.repositories.BankRepository;
import skillfactory.DreamTeam.globus.it.dto.bank.BankCreationRequests;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BankService {
    private final BankRepository bankRepository;

    public BankEntity createBank(BankCreationRequests.CreateBank request) {
        return bankRepository.save(
            BankEntity.builder()
                .name(request.name())
                .build(
        ));
    }

    public Optional<BankEntity> findById(Long id) {
        return bankRepository.findById(id);
    }
}
