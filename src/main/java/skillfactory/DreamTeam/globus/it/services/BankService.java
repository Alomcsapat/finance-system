package skillfactory.DreamTeam.globus.it.services;

import org.springframework.stereotype.Service;

import skillfactory.DreamTeam.globus.it.dao.entities.bank.BankEntity;
import skillfactory.DreamTeam.globus.it.dao.repositories.BankRepository;
import skillfactory.DreamTeam.globus.it.dto.bank.BankCreationRequests;

@Service
public class BankService {
    private BankRepository bankRepository;

    public BankEntity createBank(BankCreationRequests.CreateBank request) {
        return bankRepository.save(
            BankEntity.builder()
                .name(request.name())
                .build(
        ));
    }
}
